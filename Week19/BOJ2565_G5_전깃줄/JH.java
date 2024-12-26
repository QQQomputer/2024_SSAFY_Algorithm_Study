package BOJ2565_G5_전깃줄;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH {
	static int N,ans;
	static int [][] arr;
	static int [] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()) + 1;
		arr = new int[N][2];
		dp = new int[N];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

		ans = 1;

		// LIS DP
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[i][1] > arr[j][1] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			if (ans < dp[i])
				ans = dp[i];
		}

		System.out.println(--N-ans);
	}
}