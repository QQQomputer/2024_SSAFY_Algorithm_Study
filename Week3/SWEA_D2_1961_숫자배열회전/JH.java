package SWEA_D2_1961_숫자배열회전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JH {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N,ans,r,c;
	static int [][] board;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append("\n");
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			ans=0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());//i*N+j+1;
				}
			}
			
			int maxIdx = N-1;
			for (int i = 0; i < N; i++) {	
				//90도
				for (int j = 0; j < N; j++) {
					r = maxIdx-j;	// N-1-j
					c = i;			// i
					sb.append(board[r][c]);
				}
				sb.append(" ");
				//180도
				for (int j = 0; j < N; j++) {
					r = maxIdx-i;	// N-1-i
					c = maxIdx-j;	// N-1-j
					sb.append(board[r][c]);
				}
				sb.append(" ");
				//270도
				for (int j = 0; j < N; j++) {
					r = j;			// j
					c = maxIdx-i;	// N-1-i
					sb.append(board[r][c]);
				}
				sb.append("\n");
			}			
		}
		
		System.out.println(sb.toString());
	}
}