import java.util.Scanner;

public class 수들의_합2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr= new int[N];
		int[] arrSum = new int[N];
		int sum = 0;
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
			arrSum[i] = sum;
		}
		
		int answer = 0;
		
		for(int i=0;i<N;i++) {
			if(arr[i] + (arrSum[N-1] - arrSum[i]) < M)
				break;
			
			int start = i;
			int end = N-1;
			while(start<=end) {
				int mid = (start + end)/2;
				int cur = arr[i] + (arrSum[mid] - arrSum[i]);
				if(cur == M) {
					answer++;
					break;
				}
				else if(cur < M) 
					start = mid+1;
				else 
					end = mid - 1;
			}
		}
		
		System.out.println(answer);
		
		sc.close();
	}
	
}
