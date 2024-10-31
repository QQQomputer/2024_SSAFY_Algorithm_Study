package BOJ1660_G5_캡틴이다솜;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
	#start
	09:13
	
	#end
	
	#concepion
	대포알은 반드시 사면체 모양
	사면체를 만드는 방법은 길이가 N인 정삼각형 모양
	사면체를 가능한 최소 개수 만큼
	
	
	
	
	#review
	
	
	
*/
class Main {
	static int N,ans,len,max;
	static int [] dp1;
	static int [] dp;
	static int [] minDP;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int len = N+1>4?N+1:4;
		dp1=new int[len];
		dp=new int[len];
		minDP=new int[N+1];
		dp1[1]=1;dp1[2]=3;dp[1]=1;
		tabulation(2);
        
		Arrays.fill(minDP, Integer.MAX_VALUE);
		dp(N,0,max);

		System.out.println(minDP[0]);
	}
	static void dp(int cur, int cnt, int idx) {	
		if(cur<0)return;
		if(minDP[cur]<=cnt)return;
		minDP[cur]=cnt;		
		
		for (int i = idx; i > 0; i--) {
			dp(cur-dp[i],cnt+1, i);
		}
	}
	
	static void tabulation(int n) {
		dp1[n] = dp1[n-1] + n;
		dp[n] = dp[n-1]+dp1[n];
		if(dp[n]<N)tabulation(n+1);
		else max = n;
	}
}