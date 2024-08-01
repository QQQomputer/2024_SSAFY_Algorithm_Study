package SWEA_D2_1974_스도쿠검증;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JH {
	static int N,M,S,max;
	static int [][] board;
	static int [] check;
	
	//팔방+가운데
	static int [] dr = {-1,-1,-1,0,0,0,1,1,1};
	static int [] dc = {-1,0,1,-1,0,1,-1,0,1};
	
	//구역별 가운데
	static int [] pr = {1,1,1,4,4,4,7,7,7};
	static int [] pc = {1,4,7,1,4,7,1,4,7};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {			
			board = new int[9][9];
			check = new int[10];
			int idx = 1;
			
			boolean flag = false;
			
			//board 값 넣기 + 가로행 check
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					check[board[i][j]]++;
					if(check[board[i][j]]>idx)
						flag=true;
				}
				idx++;
			}
			
			//세로행 check
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					check[board[j][i]]++;
					if(check[board[j][i]]>idx)
						flag=true;
				}
				idx++;
			}
			
			//구역별 체크
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int n = board[pr[i]+dr[j]][pc[i]+dc[j]];
					check[n]++;
					if(check[n]>idx)
						flag=true;
				}
				idx++;
			}			
			
			if(flag)
				System.out.println("#"+t+" "+0);
			else
				System.out.println("#"+t+" "+1);
		}
	}

}
