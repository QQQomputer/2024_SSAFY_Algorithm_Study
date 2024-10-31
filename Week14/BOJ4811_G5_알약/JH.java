package BOJ4811_G5_알약;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH {
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		dp = new long[31][31];
		Arrays.fill(dp[0], 1);
		make(30);

//		for (long[] a : dp) System.out.println(Arrays.toString(a));

		int n = Integer.parseInt(br.readLine());
		while (n != 0) {
			sb.append(dp[n][n]).append("\n");
			n = Integer.parseInt(br.readLine());
		}

		System.out.println(sb);
	}

	static void make(int n) {
		if (n != 1)
			make(n - 1);

		for (int i = 1; i <= n; i++)
			dp[i][n] = dp[i - 1][n] + dp[i][n - 1];
		return;
	}
}