import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15686_이준상_200219 {
	static class Coordinate {
		int row;
		int col;
		public Coordinate(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int N;
	static int M;
	static ArrayList<Coordinate> houses = new ArrayList<>();
	static ArrayList<Coordinate> chickens = new ArrayList<>();
	static ArrayList<int[]> combination = new ArrayList<>();
	static int[] arr; // 조합을 구하기 위해 사용될 임시값들이 저장될 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) houses.add(new Coordinate(i, j));
				else if (input == 2) chickens.add(new Coordinate(i, j));
			}
		}
			
		comb(chickens.size(), M); // 조합 구하기
		
		int min = Integer.MAX_VALUE;
		for(int[] item : combination) { // 조합의 경우의 수만큼 구하기. 전수조사
			int sum = 0;
			for(Coordinate house : houses) {
				sum += getDistance(house, item);
			}
			if (min > sum) min = sum;
		}
		
		System.out.println(min);
	} // end of main
	
	// 집 좌표값과 치킨집 리스트를 주었을 때 가장 가까운 거리를 리턴하는 메소드
	public static int getDistance(Coordinate c, int[] array) {
		int ret = Integer.MAX_VALUE;
		for(int index : array) {
			Coordinate chicken = chickens.get(index);
			int temp = Math.abs(c.row - chicken.row) + Math.abs(c.col - chicken.col);
			if (ret > temp) ret = temp;
		}
		return ret;
	}
	
	public static void comb(int n, int c) {
		if (c == 0) {
			int[] temp = arr.clone();
			combination.add(temp);
		} else if (n < c) {
			return;
		} else {
			arr[c - 1] = n - 1;
			comb(n - 1, c - 1);
			comb(n - 1, c);
		}
	}
} // end of class
