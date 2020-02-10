// 부분합
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int[] arr = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = 0;
		int sum = 0;
		int minLen = Integer.MAX_VALUE;
		for(;s<N;s++) {
			while(sum<S && e<N) {
				sum += arr[e++];
			}
			
			if(sum >= S) {
				int curLen = e-s;
				minLen = minLen < curLen ? minLen : curLen; 
			}
			
			sum -= arr[s];
		}
		
		if(minLen == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(minLen);
	}
	
}
