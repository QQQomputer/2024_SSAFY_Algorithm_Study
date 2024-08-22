package SWEA_D2_1959_두개의숫자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//10m
public class JH {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,ans;
	static int [] A;
	static int [] B;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans=0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			A=new int[N];
			B=new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				A[i]=Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				B[i]=Integer.parseInt(st.nextToken());

			boolean isBig = N>M;
			
			if(isBig) {
				for (int i = 0; i < N-M+1; i++) {
					int sum = 0;
					for (int j = 0; j < M; j++)
						sum+=A[i+j]*B[j];
					ans=Math.max(ans, sum);
				}
			}else {
				for (int i = 0; i < M-N+1; i++) {
					int sum = 0;
					for (int j = 0; j < N; j++)
						sum+=B[i+j]*A[j];
					ans=Math.max(ans, sum);
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
