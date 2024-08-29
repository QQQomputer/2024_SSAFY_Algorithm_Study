package BOJ14889_S1_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	23:51
	13:03
	1 <= Sij <= 100
	
	2	1	
	3	3	
	4	6	

	1차원 배열에 담을 수 있을까?
	
	4
	4	8	9	12	13	14
	5	9	6	6	10	7
	
	38000
	
#
DP스러운 요소가 있다고 생각되었다.
가지 치지 못한 부분이 있었다.
if(ans==0)return; 부분을  "System.exit(0);" 자바 정상 종료를 활용하여 대체할 수 있었다.
*/
class JH {
	static int N,M,sum,ans;
	static int [][] board;
	static boolean [] isUsed;
	static int [] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
//		int T = Integer.parseInt(br.readLine());
		ans=19_001;
		N = Integer.parseInt(br.readLine());
		M = N/2;
		board = new int[N][N];
		isUsed = new boolean[N];
		arr = new int [M+1];
		sum=0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int r,c;
				if(i<j) {r=i;c=j;}
				else {r=j;c=i;}
				board[r][c]+=Integer.parseInt(st.nextToken()); 
			}
		}
		
//		for (int i = 0; i < N; i++)
//			for (int j = i+1; j < N; j++)
//				sum+=board[i][j];
		
//		for (int i = 0; i < N; i++)
//			System.out.println(Arrays.toString(board[i]));
		
//		System.out.println("sum : "+sum);
		
		subset(0,0);

		System.out.println(ans);
	}
	static void subset(int n, int s) {
		if(n==M) {
			int s2 = 0;
			for (int j = 0; j < N; j++) {
				if(!isUsed[j]) {
					for (int k = j+1; k < N; k++) {
						if(!isUsed[k]) {
							s2 += board[j][k];
						}
					}
				}
			}
//			System.out.println(s);
//			System.out.println("s : "+Math.abs(s-s2));
//			System.out.println(Arrays.toString(isUsed));
			ans=Math.min(ans, Math.abs(s-s2));
			return;
		}
		for (int i = arr[n]; i < N; i++) {
			if(!isUsed[i]) {
				isUsed[i]=true;
				arr[n+1]=i;
				int plus = 0;
				for (int j = 1; j <= n; j++)
					plus += board[arr[j]][arr[n+1]];
				subset(n+1,s+plus);
				if(ans==0)return;
				isUsed[i]=false;
			}
		}
	}

}