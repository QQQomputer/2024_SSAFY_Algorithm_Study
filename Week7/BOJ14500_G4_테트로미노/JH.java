package BOJ14500_G4_테트로미노;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	돌리기, 반전	
	
	1. 일자 4개
		2가지
	2. 정사각형
		1가지
	3. ㄱ자
		8가지
	4. ㄹ자
		4개
	5. ㅗ자
		4개
	
	500*500*19
	
	484ms
*/
class JH {
	static int N,M, ans;
	static int [][][] board;
	static int [][] dr = {{0,0,1,1},{0,0,0,0},{0,1,2,1},{0,1,2,2},{0,1,1,2}};
	static int [][] dc = {{0,1,0,1},{0,1,2,3},{0,0,0,1},{0,0,0,1},{0,0,1,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// (4 ≤ N, M ≤ 500)
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = 0;
		
		//4방 * 2(반전) => 8개
		board = new int[8][][];
		board[0] = new int[N][M];board[1] = new int[M][N];board[2] = new int[N][M];board[3] = new int[M][N];
		board[4] = new int[M][N];board[5] = new int[N][M];board[6] = new int[M][N];board[7] = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n =Integer.parseInt(st.nextToken()); //1,000을 넘지 않는 자연수
				
				//4방
				board[0][i][j]=board[1][M-1-j][i]=board[2][N-1-i][M-1-j]=board[3][j][N-1-i]=n;
				
				//반전
				board[4][j][i]=board[5][i][M-1-j]=board[6][M-1-j][N-1-i]=board[7][N-1-i][j]=n;	
			}
		}
		
		findMax();
		
		System.out.println(ans);
	}
	static void findMax() {
		// i == 0	회전x
		// i == 1	가로 세로
		// i == 2	4방
		// i == 3	4방 * 2(반전)
		// i == 4	2방 * 2(반전)
		for (int i = 0; i < 5; i++) {
			int [] drr = dr[i];
			int [] dcc = dc[i];
			int max = 8;
			if(i==2)max=4; else if(i==1)max=2; else if(i==0)max=1;
			for (int j = 0; j < max; j++) {
				int NN = board[j].length;
				int MM = board[j][0].length;				
				row:for (int r = 0; r < NN && r >= 0; r++) {
					for (int c = 0; c < MM; c++) {
						int sum = 0;
						for (int f = 0; f < 4; f++) {
							int rr = r+drr[f];
							int cc = c+dcc[f];
							 if(check(rr, cc,NN,MM)) continue row;
							 sum+=board[j][rr][cc];
						}
						if(ans<sum)ans=sum;
					}
				}
			}
		}
	}
	static boolean check(int r, int c,int N, int M) {
		return r<0 || r>=N || c<0 || c>=M;
	}
}
