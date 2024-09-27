package BOJ11722_S2_가장긴감소하는부분수열;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	#start
	2024-09-25 11:32
	#end
	
	#concepion
	- count 배열
	- LinkedList
	- 역순 v
	
	#review
	
	
	
*/
class JH {
	static int N,ans;
	static int [] arr,dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		N = Integer.parseInt(br.readLine());
		
		arr=new int[N];
		dp=new int[N];
		Arrays.fill(dp, 0);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i]=Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(arr[i]<arr[j])
					dp[i] = Math.max(dp[i],dp[j]+1);
		
		ans=0;
		for (int i = 0; i < N; i++) 
			if(dp[i]>ans)ans=dp[i];		
		
		System.out.println(ans);
	}
}
