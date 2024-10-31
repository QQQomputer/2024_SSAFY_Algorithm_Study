import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
	static int N, hyphenCnt, len;
	static long ans;
	static char[] c;
	static Status[] cc;
	static Status[] hyphen;
	static int[] A = { 'A', 'E', 'I', 'O', 'U' };
	static class Status {
		boolean check;

		public Status(boolean check) {
			this.check = check;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		c = br.readLine().toCharArray();

		len = c.length;

		if (len == 1) {
			ans = 26;
		} else if (len == 2) {
			if (c[0] == '_') {
				if (c[1] == '_') {
					ans = 26 * 26;
				}
			} else if (c[1] == '_') {
				ans = 26;
			}
		} else {
			int idx = 0;
			hyphen = new Status[10];
			for (int i = 0; i < 10; i++)
				hyphen[i]=new Status(false);

			cc=new Status[c.length];
			for (int i = 0; i < c.length; i++) {
				if(c[i]=='_') {	
					cc[i]=hyphen[hyphenCnt++];
				}else if(isAEIOU(c[i])) {
					cc[i]=new Status(true);
				}else {
					cc[i]=new Status(false);
				}
			}
			dfs(0);
		}

		System.out.println(ans);
	}

	static void dfs(int n) {
		if (n == hyphenCnt) {
			//
			if(check()) {
				//값 구하기
				long valA = 1;
				long valB = 1;
				// 자음 수
				int cntB = 0;
				for (int i = 0; i < hyphenCnt; i++) {
					if(hyphen[i].check) {
						valA*=5;
					}else {
						valB*=21;
						cntB++;
					}
				}
				
				//L 하나 이상인지 체크
				boolean flag = checkL();
				
				//기존에 있으면 바로 정답
				if(flag) {

				}
				//없으면 자음 중에 L을 가진 경우의수 구하기
				//자음이 없으면 더하지 말고 return
				else {
					if(cntB==0)return;
					else valB-= Math.pow(20, cntB);
				}
				ans+=valA*valB;
			}
			return;
		}

		hyphen[n].check = true;
		dfs(n + 1);
		hyphen[n].check = false;
		dfs(n + 1);
	}
	static boolean checkL() {
		for (int i = 0; i < len; i++) {
			if(c[i]=='L')return true;
		}
		return false;
	}
	static boolean check() {
		boolean bef = cc[0].check;
		int befCnt = 1;
		int nc = 1;
		for (int i = 1; i < len; i++) {
			if (bef == cc[i].check) {
				if(befCnt++>1)return false;
			} else {
				bef = cc[i].check;
				befCnt=1;
			}
		}
		
		return true;
	}
	static boolean isAEIOU(char c) {
		for (int i = 0; i < 5; i++) {
			if (A[i] == c)
				return true;
		}
		return false;
	}
}