package BOJ16198_S1_에너지모으기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	84ms
	
*/
class JH {
	static int N,M, ans;
	static int [] arr;
	static boolean [] used;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		ans=0;
		N = Integer.parseInt(br.readLine());
		M = N-2;
		arr = new int[N];
		used= new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		dfs(0,0);
		System.out.println(ans);
	}
	static void dfs(int n,int sum) {
		if(n==M) {
			ans = Math.max(ans, sum);
			return;
		}
		for (int i = 1; i < N-1; i++) {
			if(!used[i]) {
				used[i]=true;
				int l = i-1;
				while(used[l])l--;
				int r = i+1;
				while(used[r])r++;
				dfs(n+1,sum+(arr[l]*arr[r]));
				used[i]=false;
			}
		}
	}
}
