package BOJ2931_G2_가스관;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] s = new int[2];
	static int[] e = new int[2];
	static int N, M, dir, cnt, Mcnt, Zcnt,Mdir,Zdir;
	static char[][] board;
	static boolean[][][] visited;
	static boolean[][] dirOpen = new boolean[125][];
	static int[] block = { '|', '-', '1', '2', '3', '4', '+' };
	static boolean isDone;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		//블럭모양 별 오픈 방향 true false
		setting();

//		System.out.println(Arrays.toString(dirOpen['1']));
		
		
		//입력
		board = new char[N][];
		visited = new boolean[N][M][4];
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			board[i] = c;
			for (int j = 0; j < M; j++) {
				if (c[j] == 'M') {
					s[0] = i;
					s[1] = j;
				} else if (c[j] == 'Z') {
					e[0] = i;
					e[1] = j;
				} else if (c[j] != '.') {
					boolean[] bool = dirOpen[c[j]];
					for (int k = 0; k < 4; k++)
						if (bool[k])
							visited[i][j][k] = true;
					cnt++;
					if (c[j] == '+') cnt++;
				}
			}
		}

		// 시작
		for (int i = 0; i < 4; i++) {
			int rr = s[0]+dr[i];
			int cc = s[1]+dc[i];
			if (check(rr, cc) || board[rr][cc] == '.')continue;
			visited[s[0]][s[1]][i] = true;
		}
		
		// 끝
		for (int i = 0; i < 4; i++) {
			int rr = e[0]+dr[i];
			int cc = e[1]+dc[i];
			if (check(rr, cc) || board[rr][cc] == '.')continue;
			visited[e[0]][e[1]][i] = true;
		}
		
		// M에서 끊긴 점까지
		dfsM(0, s[0], s[1]);
		
		isDone = false;

		// Z에서 끊긴 점까지
		dfsZ(0, e[0], e[1]);
		
		
		for(int i = 0 ;i<4;i++)
			if(visited[s[0]][s[1]][i]) Mdir = i;

		for(int i = 0 ;i<4;i++)
			if(visited[e[0]][e[1]][i]) Zdir = i;

//		if(board[s[0]][s[1]]=='M')
//			Mdir = getDir(s[0], s[1], e[0], e[1]);
//		
//		if(board[e[0]][e[1]]=='Z')
//			Zdir = getDir(e[0], e[1],s[0], s[1]);
		
		System.out.println("Mdir : "+ Mdir+ " board : "+board[s[0]][s[1]]);
		System.out.println("Zdir : "+ Zdir+ " board : "+board[e[0]][e[1]]);
		
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}

		Zdir = Zdir % 2 == 0 ? Zdir + 1 : Zdir - 1;
		
		int mr = s[0]+dr[Mdir]+1;
		int mc = s[1]+dc[Mdir]+1;
		
		Mdir = Mdir % 2 == 0 ? Mdir + 1 : Mdir - 1;
		
		// 7가지 블록 중 모든 cnt를 충족하는 값(dfs 돌리기)
//		System.out.println(Mcnt+" "+Zcnt+ " cnt : "+cnt);
		if (Mcnt + Zcnt == cnt) {
			for (int i = 0; i < 6; i++) {

				if(i==1) {
					System.out.println((char)block[i]);
					System.out.println("Mdir : "+ Mdir);
					System.out.println("Zdir : "+ Zdir);
					System.out.println(dirOpen[block[i]][Mdir]);
					System.out.println(dirOpen[block[i]][Zdir]);
				}
				
				if(dirOpen[block[i]][Mdir]&&dirOpen[block[i]][Zdir]) {
					sb.append(mr).append(" ").append(mc).append(" ").append((char)block[i]).append("\n");
				}
			}

		} else {
			sb.append(mr).append(" ").append(mc).append(" ").append("+");
		}
		System.out.println(sb);
	}

	static void setting() {
		for (int i = 0; i < block.length; i++) {
			int b = block[i];
			if (b == '|')
				dirOpen[b] = new boolean[] { true, true, false, false };
			else if (b == '-')
				dirOpen[b] = new boolean[] { false, false, true, true };
			else if (b == '+')
				dirOpen[b] = new boolean[] { true, true, true, true };
			else if (b == '1')
				dirOpen[b] = new boolean[] { false, true, false, true };
			else if (b == '2')
				dirOpen[b] = new boolean[] { true, false, false, true };
			else if (b == '3')
				dirOpen[b] = new boolean[] { true, false, true, false };
			else if (b == '4')
				dirOpen[b] = new boolean[] { false, true, true, false };
		}
	}

	static void dfsM(int n, int r, int c) {
//		System.out.println();
//		if (n == cnt - 1) {
//			Mcnt = n;
//			isDone = true;
//			s[0]=r;s[1]=c;
//			return;
//		}
//		System.out.println("dfsM : "+r+" "+c);
		// setDir
		for (int i = 0; i < 4; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];
			// 밖으로? || 맨땅?
			if (check(rr, cc) || board[rr][cc] == '.' || board[rr][cc] == 'Z' )
				continue;
//			System.out.println(rr+ " dfsM " +cc);
			// 내가 가는 방향 뚫려있음?
			int d = getDir(r, c, rr, cc);
			int rd = d % 2 == 0 ? d + 1 : d - 1;
//			System.out.println(d+" - "+rd);
//			System.out.println(visited[r][c][d] +" - "+ visited[rr][cc][rd]);
			if (!visited[r][c][d] || !visited[rr][cc][rd])
				continue;
			visited[r][c][d] = false;
			visited[rr][cc][rd] = false;
			dfsM(n+1, rr, cc);
			if (isDone)
				return;
		}

		Mcnt = n;
		isDone = true;
		s[0]=r;s[1]=c;
		return;
	}

	static void dfsZ(int n, int r, int c) {
//		System.out.println();
//		if (n == cnt - 1) {
//			Zcnt = n;
//			isDone = true;
//			return;
//		}
//		System.out.println("dfsZ : "+r+" "+c);
		// setDir
		for (int i = 0; i < 4; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];
			// 밖으로? || 맨땅?
			if (check(rr, cc) || board[rr][cc] == '.' || board[rr][cc] == 'M' )
				continue;
//			System.out.println(rr+ " dfsZ " +cc);
			// 내가 가는 방향 뚫려있음?
			int d = getDir(r, c, rr, cc);
			int rd = d % 2 == 0 ? d + 1 : d - 1;
//			System.out.println(d+" - "+rd);
//			System.out.println(visited[r][c][d] +" - "+ visited[rr][cc][rd]);
			if (!visited[r][c][d] || !visited[rr][cc][rd])
				continue;
			visited[r][c][d] = false;
			visited[rr][cc][rd] = false;
			if(board[rr][cc]=='+') {
				int rrr = rr + dr[i];
				int ccc = cc + dc[i];
				if(board[rr][cc] != '.' && true) {
					
				}
				else {
					Zcnt = n;
					isDone = true;
					e[0]=rr;e[1]=cc;
					return;
				}
				
			}
			dfsZ(n+1, rr, cc);
			if (isDone)
				return;
		}

		Zcnt = n;
		isDone = true;
		e[0]=r;e[1]=c;
		return;
	}

	// 상하좌우
	static int getDir(int r, int c, int rr, int cc) {
		if (r > rr)
			return 0;
		if (r < rr)
			return 1;
		if (c > cc)
			return 2;
		else //		if (c < cc)
			return 3;
	}

	static boolean check(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}

/*

7 5
.....
.....
.---Z
|...M
|...|
2---3
.....


5 15
1Z...1--4......
|....|..|......
2----.1-+-----4
......|.|.....|
......2-3....M3

정올
https://jungol.co.kr/problem/2944

*/