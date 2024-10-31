import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, K, N1;
	static long ans = 1,mirrorSum;
	static long [] sd;
	static int[] dr = { 1, 0, 0, -1 };
	static int[] dc = { 0, -1, 1, 0 };
	static char[] a = { 'D', 'L', 'R', 'U' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		N1 = N-1;
		sd = new long[N];
		sd[0]=1;
		mirrorSum = 1L*N*N+1;
		calcSD(N-1);

		int r = 0; 
		int c = 0;
		char [] ccc = br.readLine().toCharArray();
		for (int i = 0; i < K; i++) {
			int d = ccc[i] / 6 % 11;
			r = r+dr[d];
			c = c+dc[d];
			
			int depth = r+c;
			
			int rr = r;
			int cc = c;
			boolean isMirror = false;
			
			if(depth>N1) {
				isMirror = true;
				// 0 1 => 5 4
				rr = N1 - r;
				cc = N1 - c;
				depth = rr+cc;
			}
			// r+c 가 홀수 : ↙  짝수 : ↗
			// 홀수 => r counting
			// 짝수 => c counting
			long num = sd[depth]+(depth%2==0?cc:rr);
			
			// 16 0,5
			// 21 5,0
			
			ans+=isMirror?mirrorSum-num:num;
		}
		System.out.println(ans);
	}
	// 계차 수열
	static void calcSD(int idx) {
		if(idx != 1)calcSD(idx-1);
		sd[idx]=sd[idx-1]+idx;
	}
}