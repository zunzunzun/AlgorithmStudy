import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17143번 낚시왕

class Shark{// 위 아래 오른 왼
	int r, c, s, d, z, index;
	Shark(int r, int c, int s, int d, int z, int index){
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
		this.index = index;
	}
}

public class Main{
	static int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken())+1;
		int C = Integer.parseInt(st.nextToken())+1;
		int M = Integer.parseInt(st.nextToken()); // 상어 수
		
		int map[][] = new int[R][C];
		Shark[] sharks = new Shark[M+1];
		
		int answer = 0;
		
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			sharks[i] = new Shark(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()) - 1, // 방향
					Integer.parseInt(st.nextToken()),
					i);
			
			map[sharks[i].r][sharks[i].c] = i;
			
			// 이동속도 줄이기
			if(sharks[i].d <= 1)
				sharks[i].s = sharks[i].s % ((R-2) * 2);
			else
				sharks[i].s = sharks[i].s % ((C-2) * 2);
		}
		
		for(int i=1;i<C;i++) {
			// 상어 잡기
			for(int j=1;j<R;j++) {
				if(map[j][i] != 0) {
					answer += sharks[map[j][i]].z;
					sharks[map[j][i]].z = -1; // 죽은 상어의 무게는 -1로 설정
					map[j][i] = 0;
					break;
				}
			}
			
			map = new int[R][C];
			
			// 상어 이동
			for(int k=1;k<=M;k++) {
				if(sharks[k].z == -1) continue;
				
				int speed = sharks[k].s;
				
				while(speed > 0) {
					int[] next = {sharks[k].r + speed*DIR[sharks[k].d][0],
							sharks[k].c + speed*DIR[sharks[k].d][1]};
					// 경계 충돌
					if(next[0] < 1)	{
						int over = 1 - next[0];
						sharks[k].r = 1;
						speed = over;
						sharks[k].d = 1;
					}
					else if(next[0] > R-1) {
						int over = (R-1) - next[0];
						sharks[k].r = R-1;
						speed = -over;
						sharks[k].d = 0;
					}
					else if(next[1] < 1) {
						int over = 1 - next[1];
						sharks[k].c = 1;
						speed = over;
						sharks[k].d = 2;
					}
					else if(next[1] > C-1) {
						int over = (C-1) - next[1];
						sharks[k].c = C-1;
						speed = -over;
						sharks[k].d = 3;
					}
					else {
						sharks[k].r = next[0];
						sharks[k].c = next[1];
						speed = 0;
					}
				}
				
				// 상어 잡아먹기
				int ro = sharks[k].r;
				int co = sharks[k].c;
				if(map[ro][co] != 0) {
					int prevShark = map[ro][co];
					
					if(sharks[prevShark].z > sharks[k].z) { // 이미 와있던 상어가 더 클 때
						sharks[k].z = -1;
					}
					else { // 현재 들어올 상어가 더 클 때
						sharks[prevShark].z = -1;
						map[ro][co] = sharks[k].index;
					}
				}
				else
					map[ro][co] = sharks[k].index;
			}// 상어 이동 끝

		}// 상어 게임 끝
		System.out.printf("%d\n", answer);
	}
}