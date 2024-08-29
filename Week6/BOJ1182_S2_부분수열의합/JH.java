package BOJ1182_S2_부분수열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	10:25
	10:37
	
	순열 조합 부분집합(subset) dfs bfs
*/
class JH {
	static int N,M,ans;
	static int [] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans=0;		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i]=Integer.parseInt(st.nextToken());
		
		subset(0,0,0);
		
		System.out.println(ans);
	}
	static void subset(int idx,int cnt, int s) {
		if(cnt>0 && s==M) ans++;

		if(idx==N)return;
		
		for (int i = idx; i < N; i++) subset(i+1,cnt+1,s+arr[i]);

		
//		subset(idx+1,cnt+1,s+arr[idx]);
//		subset(idx+1,cnt,s);
	}
}