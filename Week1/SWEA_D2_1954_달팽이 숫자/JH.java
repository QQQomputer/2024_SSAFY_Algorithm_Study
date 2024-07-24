import java.util.*;
import java.io.*;

class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N,idx, ans, max, sum;
    static int [][] arr;
    //1 - 1
    //2 - 1,2,	4,	3
    //3 - 1,2,3,	6,9,	8,7,	4,	5
    //4 - 1,2,3,4,	8,12,16,	15,14,13,	9,5,	6,7,	11	,10
    
    //static int [] arr = {10,9,9,8,8,7,7,6,6,5,5,4,4,3,3,2,2,1,1};
	public static void main(String args[]) throws Exception
	{
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++)
		{
			sb.append("#"+t+"\n"); 			
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			int r = 0;
			int c = 0;
			int direct = 0;
			int [] moveR = {0,1,0,-1};
			int [] moveC = {1,0,-1,0};
			
			arr[0][0]=1;
			
			for (int i = 1; i < N*N; i++) {
				r+=moveR[direct%4];
				c+=moveC[direct%4];
				if(!check(r,c)|| arr[r][c]!=0) {
					r-=moveR[direct%4];
					c-=moveC[direct++%4];
					i--;
					continue;
				}
				arr[r][c]=i+1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j]+" ");
				}
				sb.setLength(sb.length()-1);
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	static boolean check(int row, int col) {
		return row>=0&&row<N&&col>=0&&col<N;
	}
}
