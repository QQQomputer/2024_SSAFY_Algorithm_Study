import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
	static int N, ans;
	static int[][] board;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	
	// 위치 별 모래 양
	static int[] drr = { -2, -1, -1, 0, 0, 0, 0, 1, 1, -1 };
	static int[] dcc = { 0, -1, 1, -2, -1, 1, 2, -1, 1, 0 };
	static int[] p = { 5, 10, 10, 2, 7, 7, 2, 1, 1};

	public static void main(String[] args) throws IOException {
		N = readInt();
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = readInt();
			}
		}
		int cr, cc, r, c;
		r = cr = N / 2;
		c = cc = N / 2;

		int dir = 0;
		int d = 1;
		while (!(r == 0 && c == 0)) {
			int cnt = 0;
			r = r + dr[dir];
			c = c + dc[dir];

			// 모래 이동
			for (int i = 0; i < drr.length; i++) {
				int rrr = 0, ccc = 0;
				switch (dir) {
				case 0:
					rrr = r + dcc[i];
					ccc = c + drr[i];
					break;
				case 1:
					rrr = r - drr[i];
					ccc = c + dcc[i];
					break;
				case 2:
					rrr = r + dcc[i];
					ccc = c - drr[i];
					break;
				case 3:
					rrr = r + drr[i];
					ccc = c + dcc[i];
					break;
				}
				if (i == p.length) {
					if (check(rrr, ccc))
						ans += board[r][c] - cnt;
					else {
						board[rrr][ccc] += board[r][c] - cnt;
					}
				} else {
					if (check(rrr, ccc)) {
						int n = (board[r][c] * p[i]) / 100;
						ans += n;
						cnt += n;
					}
						
					else {
						int n = (board[r][c] * p[i]) / 100;
						board[rrr][ccc] += n;
						cnt += n;
					}
				}
			}

			board[r][c] = 0;

			// 방향 바꾸기
			if (dr[dir] != 0) {
				if (cr + dr[dir] * d == r)
					dir++;
				if (dir > 3) {
					dir = 0;
					d++;
				}
			}
			if (dc[dir] != 0) {
				if (cc + dc[dir] * d == c)
					dir++;
			}
		}

		System.out.println(ans);
	}

	static boolean check(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}

	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while (read > '9' || read < '0') {
			read = System.in.read();
		}
		while (read >= '0' && read <= '9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		return result;
	}
}