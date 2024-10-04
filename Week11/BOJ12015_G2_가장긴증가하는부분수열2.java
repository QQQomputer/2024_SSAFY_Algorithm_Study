package unsolved;

import java.io.IOException;

public class BOJ12015_G2_가장긴증가하는부분수열2 {
	static int N, ans;
	static int[] arr, dp;
	public static void main(String[] args) throws IOException {
		N = readInt();
		arr = new int[N];
		dp = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = readInt();

		int cur = 0;
		dp[cur] = arr[0];
//		System.out.println(Arrays.toString(dp));
		for (int i = 1; i < N; i++) {
			if (dp[cur] > arr[i]) {
				dp[bs(0, cur, arr[i])] = arr[i];
			} else if (dp[cur] < arr[i]) {
				dp[++cur] = arr[i];
			}
//			System.out.println(Arrays.toString(dp));
		}
		System.out.println(cur + 1);
	}

	// lower bound
	// 타겟보다 같거나 큰 값이 나오는 처음 위치
	static int bs(int l, int r, int res) {
		while (l < r) {
			int mid = (l + r) / 2;
			if (dp[mid] >= res) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}

		return l;
	}
	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while(read<'0'||read>'9')read = System.in.read();
		while(read>='0' && read <= '9') {
			result = result * 10 + read-'0';
			read = System.in.read();
		}
		return result;
	}
}
