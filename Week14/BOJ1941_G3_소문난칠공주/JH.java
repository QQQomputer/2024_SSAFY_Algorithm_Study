package BOJ1941_G3_소문난칠공주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH {
	static int N, ans;
	static int isLi;
	// 상 우 하 좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static List<Integer> res = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// st = new StringTokenizer(br.readLine());
		// Integer.parseInt(st.nextToken());
		N = 5;

//		isLim = new int[N];

		// S: 이다솜 Y: 임도연
		// 파 나누기(bit) - 0: 이다솜 1: 임도연
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			int n = 0;
			for (int j = 0; j < N; j++) {
				n |= (c[j] - 'S' == 0 ? 0 : 1) << j;
			}
			isLi |= (n << i);
		}

		subset(0, isLi, 0, 0);

		System.out.println(ans);

	}

	static void subset(int n, int bit, int cur, int cnt) {
		if (cnt > 3)
			return;
		if (n == 7) {
			int checked = bit ^ isLi;
			bfs(cur - 1, checked);
		}

		for (int i = cur; i < 25; i++) {
			int b = (1 << i);
			if ((bit & b) == b)
				subset(n + 1, n & ~(1 << i), i + 1, cnt);
			else
				subset(n + 1, n & ~(1 << i), i + 1, cnt + 1);
		}
	}

	// sum 임다연파 count
	static void bfs(int cur, int bit) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		int sy = cur/N;
		int sx = cur%N;
		q.offer(new int[] {sy,sx});
		bit = (bit & ~(1 << cur));
		int cnt = 7;
		while(!q.isEmpty()) {
			int [] info = q.poll();
			int y = info[0];
			int x = info[1];
			cnt--;
			for (int i = 0; i < 4; i++) {
				int yy = y+dy[i];
				int xx = x+dx[i];
				if(check(yy,xx))continue;
				int ccur = yy*N+xx;
				int bb = (1<<ccur);
				if((bit&(bb))!=bb)continue;
				bit = (bit & ~(1 << cur));
				q.offer(new int[] {yy,xx});
			}
		}
		if(cnt==0)ans++;
	}

	static boolean check(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}