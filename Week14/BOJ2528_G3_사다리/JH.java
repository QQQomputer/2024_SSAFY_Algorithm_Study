package BOJ2528_G3_사다리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	#start
	17:18
	#end
	
	#concepion
	똑같은 칸 이동하기 때문에 무조건 -> <- 방향을 가질때만 만남
	* 예외 1 - 같은 방향인데 이미 붙어있는 경우
	
	-> <- 방향으로 만들고
	두칸의 간격/2 로 사용 턴 계산
	
	그 위에서도 똑같이
	if(n==N) 도착
	
	
	방향은 전체 턴에 계산
	현재 막대 위치 => 전체 턴 % 사이클
	방향 => 사이클 / 길이 == 1? 반대 : 기본방향 
	만약 전체 길이 12
	4 0 
	5 1
	
	50턴째는?
	
	(12-4)*2	16	
	(12-5)*2	14	
	
	
	+ 활용 가능 조건
	두 막대 길이가 전체길이를 넘을 경우 체크 안해도 무조건 true
	
	#review
	
	
	
*/
class JH {
	static int N, L, ans;
	static Stick[] s;

	static class Stick {
		int l;
		boolean dir;

		public Stick(int l, boolean dir) {
			this.l = l;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		s = new Stick[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s[i] = new Stick(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0) == '0');
		}

		for (int i = 0; i < N - 1; i++) {
			if (s[i].l == L)
				;
			else if (s[i + 1].l == L)
				i++;
			else
				while (!upCalc(ans, s[i], s[i + 1]))
					ans++;
		}

		System.out.println(ans);
	}

	static boolean upCalc(int cur, Stick s1, Stick s2) {
		// cycle 구하기
		int c1 = getCycle(cur, s1);
		int c2 = getCycle(cur, s2);
		// 방향 구하기
		boolean d1 = getDir(c1) ? !s1.dir : s1.dir;
		boolean d2 = getDir(c2) ? !s2.dir : s2.dir;
		// 움직임 구하기
		int m1 = getPos(c1);
		int m2 = getPos(c2);

		// s1 막대의 좌표 a1, a2
		// s2 막대의 좌표 b1, b2
		int a1, a2, b1, b2;
		if (d1) {
			a1 = m1;
			a2 = s1.l + m1;
		} else {
			a1 = L - (s1.l + m1);
			a2 = L - m1;
		}
		if (d2) {
			b1 = m2;
			b2 = s2.l + m2;
		} else {
			b1 = L - (s2.l + m2);
			b2 = L - m2;
		}

		// 최대 길이 <= 두 막대 합길이 => 겹침
		int len = Math.max(b2 - a1, a2 - b1);
		return len <= s1.l + s2.l;
	}

	static boolean getDir(int c) {
		return c / L == 0;
	}

	static int getPos(int c) {
		return c % L;
	}

	static int getCycle(int cur, Stick s) {
		return cur % ((L - s.l) * 2);
	}

}