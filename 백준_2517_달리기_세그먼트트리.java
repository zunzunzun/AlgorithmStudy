import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// 백준 2517 달리기
// 머지소트 & 세그먼트 트리 풀이 법 중
// 세그먼트 트리 사용
class Player implements Comparable<Player>{
	int index;
	int speed;
	Player(int index, int speed){
		this.index = index;
		this.speed = speed;
	}
	
	public int compareTo(Player other) {
		if(this.speed > other.speed)
			return 1;
		else if(this.speed == other.speed)
			return 0;
		else
			return -1;
	}
}

public class Main {
	static Player[] arr;
	static int[] result;
	static int[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new Player[N];
		result = new int[N];
		
		StringBuilder sb = new StringBuilder();
		
		tree = new int[(int)Math.pow(2, getHeight(arr)+1) - 1];
		for(int i=0;i<N;i++) 
			arr[i] = new Player(i, Integer.parseInt(br.readLine()));
		
		Arrays.sort(arr);
		
		for(int i=0;i<N;i++) {
			result[arr[i].index] = arr[i].index + 1 - query(0, 0, arr.length-1, arr[i].index);
			update(0, 0, arr.length-1, arr[i].index);
		}
		
		for(int i=0;i<N;i++) 
			sb.append(result[i] + "\n");
		
		System.out.println(sb);
	}
	
	// 선수 1명 쿼리요청 -> 선수 1명으로 트리 업데이트 - 반복
	// 선수 넣는 순서는 실력이 안 좋은 선수부터 넣는다.
	// 선수가 리프노드에 들어가는 위치는 선수들의 현재 순위를 기준으로 들어간다.
	// ex) 1등이면 왼쪽에서 맨 앞 노드 2등 3등은 오른쪽 리프노드를 목적지로 들어감
	// 자신의 위치를 찾아 들어가면서 지나치는 노드에 1씩 더해준다.
	public static void update(int node, int start, int end, int index) {
		if(index < start || end < index) return;
		
		tree[node]++;
		if(start == end) return;
		int mid = (start+end)/2;
		
		update(node*2+1, start, mid, index);
		update(node*2+2, mid+1, end, index);
	}
	
	// 이미 앞에 들어와서 트리를 업데이트 할 때 쓰인 선수들은 
	// 현재 선수보다 실력이 안 좋은 선수이다.
	// 그러므로 각 노드들에 있는 값은 등수 범위에서 현재 선수보다 실력이 안 좋은 선수들의 수를 뜻한다.
	// 이때 탐색 범위를 0부터 현재 선수의 등수-1(index매개변수) 로 잡고 탐색을 하게 되면
	// 1등부터 바로 앞 등수 범위에서 자신보다 실력이 낮은 선수의 수를 파악할 수 있다.
	public static int query(int node, int start, int end, int index) {
		if(start > index) return 0;
		else if(end <= index) return tree[node];
		
		int mid = (start+end)/2;
		return query(node*2+1, start, mid, index) + 
				query(node*2+2, mid+1, end, index);
	}
	
	public static int getHeight(Player[] arr) {
		int length = arr.length;
		int height = 0;
		while(length > 0) {
			height++;
			length/=2;
		}
		
		return height;
	}
}
