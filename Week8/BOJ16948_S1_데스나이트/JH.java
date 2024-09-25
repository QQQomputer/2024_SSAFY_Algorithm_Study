package BOJ16948_S1_데스나이트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	68ms
*/
class JH {
	static int N, ans;
	static int [] dr = {-2,-2,0,0,2,2};
	static int [] dc = {-1,1,-2,2,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		int r = Math.abs(r1-r2);
		int c = Math.abs(c1-c2);
		
		// "r은 짝수여야 하고" && "c 는 r/2일때 짝수면 짝수 || 홀수면 홀수"
		
		if(r%2==0 && ((r/2%2==0 && c%2==0)||(r/2%2==1 && c%2==1))) {
			int n = r/2;
			int cur = 0;
			for (int i = 0; i < n; i++) {
				if(cur<=c) {
					cur++;
				}else {
					cur--;
				}
			}
			int nc = Math.abs(c-cur);
			ans = n+nc/2;
		}else {
			ans = -1;
		}
		System.out.println(ans);
	}
}
