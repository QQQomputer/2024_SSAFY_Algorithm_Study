package SWEA_D2_2001_파리퇴치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JH {
	static int N,M,S,max;
	static int [] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			S = N-M+1;
			max = 0;
			
			//1차 배열로 생성
			board = new int[N*N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					board[i*N+j]=Integer.parseInt(st.nextToken());//알칼제
			}
			
			for (int r = 0; r < S; r++)
				for (int c = 0; c < S; c++)
					max = Math.max(max, kill(r,c));
			
			System.out.println("#"+t+" "+max);
		}
	}
	// board[r][c] 퇴치 시
	// return 잡히는 파리의 수;
	static int kill(int r, int c) {
		int sum = 0;
		
		for (int dr = 0; dr < M; dr++)
			for (int dc = 0; dc < M; dc++)
				sum += board[(r+dr)*N+(c+dc)];
		
		return sum;
	}

}
