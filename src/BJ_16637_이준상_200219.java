import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16637_이준상_200219 {
	private static int n;
	private static char[] operator;
	private static int[] operand;
	private static int max;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		operator = new char[n / 2];
		operand = new int[n / 2 + 1];
		
		int index1 = 0; // operator 배열을 관리할 인덱스
		int index2 = 0; // operand 배열을 관리할 인덱스
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				operator[index1++] = ch;
			} else {
				operand[index2++] = ch - '0';
			}
		}
		
		max = Integer.MIN_VALUE;
		dfs(0, operand[0]);
		System.out.println(max);
		
	} // end of main
	
	public static int calc(int a, char op, int b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 0;
	}
	
	// DFS로 구현
	public static void dfs(int index, int value) {
		if (index >= n / 2) {
			if (value > max) max = value;
			return;
		} else {
			// 현재 계산뒤에 괄호가 없다고 가정해서 계산
			int temp = calc(value, operator[index], operand[index + 1]);
			dfs(index + 1, temp);
			if (index + 1 < n / 2) { // 배열 인덱스를 넘어가지 않기 위한 if문
				// 뒤에 계산식이 괄호가 있다고 가정해서 계산 
				temp = calc(value, operator[index], calc(operand[index + 1], operator[index + 1], operand[index + 2]));
				dfs(index + 2, temp);
			}
		}
	}
	
} // end of class 
