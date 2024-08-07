package SWEA_D2_1979_어디에단어가들어갈수있을까;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//17m
public class JH {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N,K,ans;
	static int [][] board;
	public static void main(String[] args) throws IOException {
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			ans=0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//가로 확인
			for (int i = 0; i < N; i++) {				
				for (int j = 0; j < N-K+1; j++) {
					if(board[i][j]==1) {
						int sum = 1;
						for (int k = j+1; k < N; k++) {
							if(board[i][k]!=1)
								break;
							sum++;
						}
						if(sum==K)
							ans++;
						j+=sum;//sum-1
					}					
				}
			}
			
			//세로 확인
			for (int i = 0; i < N; i++) {				
				for (int j = 0; j < N-K+1; j++) {
					if(board[j][i]==1) {
						int sum = 1;
						for (int k = j+1; k < N; k++) {
							if(board[k][i]!=1)
								break;
							sum++;
						}
						if(sum==K)
							ans++;
						j+=sum;//sum-1
					}					
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
