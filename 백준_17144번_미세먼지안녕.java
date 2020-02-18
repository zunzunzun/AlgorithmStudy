import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준_17144번_미세먼지안녕!

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		int[][] machine = new int[2][2];
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		int[][] temp = new int[R][C];
		int m = 0;
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1)	{
					machine[m][0] = i;
					machine[m][1] = j;
					m++;
				}
			}
		}
		
		for(int t=0;t<T;t++) {
			// 확산
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					int dust = map[i][j]/5;
					if(dust > 0) {
						for(int k=0;k<DIR.length;k++) {
							int row = i+DIR[k][0];
							int col = j+DIR[k][1];
							if(row < 0 || col < 0 || row >= R || col >= C
									|| map[row][col] == -1) continue;
							
							map[i][j] -= dust;
							temp[row][col] += dust;
						}
					}
				} // 열
			} // 행
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					map[i][j] += temp[i][j];
					temp[i][j] = 0;
				}
			}
			// 확산 끝

			// 공기 청정기 위쪽
			int[] cur = {machine[0][0]-1, machine[0][1]};
			// 왼쪽 벽
			while(cur[0] - 1 >= 0) {
				map[cur[0]][cur[1]] = map[cur[0]-1][cur[1]];
				cur[0]--;
			}
			// 위쪽 벽
			while(cur[1] + 1 <= C-1) {
				map[cur[0]][cur[1]] = map[cur[0]][cur[1]+1];
				cur[1]++;
			}
			// 오른쪽 벽
			while(cur[0] + 1 <= machine[1][0] - 1) {
				map[cur[0]][cur[1]] = map[cur[0]+1][cur[1]];
				cur[0]++;
			}
			// 아래쪽
			while(cur[1] - 1 >= 0) {
				if(map[cur[0]][cur[1]-1] == -1)
					map[cur[0]][cur[1]] = 0;
				else
					map[cur[0]][cur[1]] = map[cur[0]][cur[1]-1];
				cur[1]--;
			}
			
			// 공기 청정기 아래쪽
			cur[0] = machine[1][0]+1;
			cur[1] = machine[1][1];
			// 왼쪽 벽
			while(cur[0] + 1 <= R-1) {
				map[cur[0]][cur[1]] = map[cur[0]+1][cur[1]];
				cur[0]++;
			}
			// 아래쪽 벽
			while(cur[1] + 1 <= C-1) {
				map[cur[0]][cur[1]] = map[cur[0]][cur[1]+1];
				cur[1]++;
			}
			// 오른쪽 벽
			while(cur[0] - 1 >= machine[0][0] + 1) {
				map[cur[0]][cur[1]] = map[cur[0]-1][cur[1]];
				cur[0]--;
			}
			// 위쪽
			while(cur[1] - 1 >= 0) {
				if(map[cur[0]][cur[1]-1] == -1)
					map[cur[0]][cur[1]] = 0;
				else
					map[cur[0]][cur[1]] = map[cur[0]][cur[1]-1];
				cur[1]--;
			}
		
		} // 시간
		int answer = 2;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
	}
}