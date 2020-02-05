// 달리기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Player{
	int speed;
	int index;
	Player(int speed, int index){
		this.speed = speed;
		this.index = index;
	}
}

public class Main {
	
	static Player[] arr;
	static int[] result;
	static Player[] sorted;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		
		arr = new Player[N];
		result = new int[N];
		sorted = new Player[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = new Player(Integer.parseInt(br.readLine()), i);
			result[i] = i+1;
		}
		
		
		mergeSort(0, arr.length-1);
		
		for(int i=0;i<N;i++) {
			answer.append(result[i]+"\n");
		}
		
		System.out.print(answer);
	}

	private static void mergeSort(int l, int r) {
		if(l<r) {
			int mid = (l+r)/2;
			mergeSort(l, mid);
			mergeSort(mid+1, r);
			merge(l, mid, r);
		}
		
	}

	private static void merge(int l, int mid, int r) {
		int i = l;
		int j = mid+1;
		int k = 0;
		
		while(i<=mid && j<=r) {
			if(arr[i].speed < arr[j].speed)
				sorted[k++] = arr[i++];
			else {
				result[arr[j].index] -= i-l;
				sorted[k++] = arr[j++];
			}
		}
		
		while(j<=r) {
			result[arr[j].index] -= i-l;
			sorted[k++] = arr[j++];
		}
		
		while(i<=mid) 
			sorted[k++] = arr[i++];
		
		System.arraycopy(sorted, 0, arr, l, k);
	}
}
