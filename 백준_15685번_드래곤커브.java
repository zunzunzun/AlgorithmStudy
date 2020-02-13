import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준_15685번_드래곤 커브

class Point{
	int x, y;
	Point(){
		x = -1;
		y = -1;
	}
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main{
	static final int[][] DIRS = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	static final int MAX_LEN = 1030; // 2의 승수로 용이 커짐
	static int dragons[][];
	static boolean[][] map = new boolean[101][101];
	static ArrayList<Integer>[] al;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dragons = new int[MAX_LEN][2];
		
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			int pivot = 1;
			
			dragons[0][0] = x;
			dragons[0][1] = y;
			
			dragons[pivot][0] = x + DIRS[d][0];
			dragons[pivot][1] = y + DIRS[d][1];
			
			// 여기까지 0세대
			
			for(int j=0;j<g;j++) {
				int deltaX = -dragons[pivot][0];
				int deltaY = -dragons[pivot][1];
				// 1. 현재까지 만들어진 모양 복사 후 회전
				int[][] temp = new int[pivot][2];
				
				for(int k=0;k<pivot;k++) {
					temp[k][0] = dragons[k][0];
					temp[k][1] = dragons[k][1];
				}
				
				//System.arraycopy(dragons, 0, temp, 0, pivot); // 2차원 배열은 이렇게 넣으면 주소가 복사 됨;;
				for(int k=0;k<pivot;k++) {
					// 1. 원점 이동
					temp[k][0] += deltaX;
					temp[k][1] += deltaY;
					
					// 2. 회전
					// 좌표축이 x축 반전이라서 회전 행렬이 좀 달라짐
					int nextX = -temp[k][1]; // x' = -y
					int nextY = temp[k][0]; // y' = x
					temp[k][0] = nextX;
					temp[k][1] = nextY;
					
					// 3. 원래 자리로 이동
					temp[k][0] -= deltaX;
					temp[k][1] -= deltaY; 
				}
		
				// 4. 이동 된 모양을 원래의 dragon에 붙여넣기
				for(int k=1;k<=pivot;k++) {
					dragons[pivot+k] = temp[pivot-k];
				}
				
				pivot += pivot;
				
				// 반복
			}
			
			for(int j=0;j<=pivot;j++) {
				int mapX = dragons[j][0];
				int mapY = dragons[j][1];
				map[mapX][mapY] = true;
			}
		}
		int answer = 0;
		
		for(int i=0;i<map.length-1;i++) {
			for(int j=0;j<map.length-1;j++) {
				if(map[i][j] && map[i+1][j+1] && 
						map[i+1][j] && map[i][j+1]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}