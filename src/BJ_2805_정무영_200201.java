import java.util.*;

public class ³ª¹«ÀÚ¸£±â{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int[] arr = new int[N];
        int max = 0;
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
            max = max > arr[i] ? max : arr[i];
        }
        int answer = 0;
        int start = 0;
        int end = max;
        int mid;
        while(start<=end){
            mid = (start+end)/2;
            
            long sum = 0;
            long upperSum = 0;
            for(int height : arr){
                int dist = height - mid;
                sum += dist > 0 ? dist : 0;
                dist--;
                upperSum += dist > 0 ? dist : 0;
            }
            
            if(sum>=M && upperSum<M){
                answer = mid;
                break;
            }
            else if(sum<M)
                end = mid - 1;
            else
                start = mid + 1;
        }
        
        System.out.println(answer);
        
        sc.close();
    }
}
