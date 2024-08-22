package SWEA_D2_1983_조교의성적매기기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//20m
public class JH {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N,M,ans;
	static double [] arr;
	static String [] str = {"A+","A0","A-","B+","B0","B-","C+","C0","C-","D0"};
	public static void main(String[] args) throws IOException {
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans=0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr=new double[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());
				int k=Integer.parseInt(st.nextToken());
				arr[i]=(7.0/20*n+9.0/20*m+1.0/5*k);
			}
			double d = arr[M-1];
			for (int i = 0; i < N; i++)
				if(d<arr[i])ans++;
			int gradeMan = N/10;			
			
			sb.append("#").append(t).append(" ").append(str[ans/gradeMan]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}