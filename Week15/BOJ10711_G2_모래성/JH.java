import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int H, W, ans;
	static int[][] board;
	static boolean[][] map;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		board = new int[H][W];
		map = new boolean[H][W];
		Queue<int[]> w = new ArrayDeque<>();
		for (int i = 0; i < H; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if (c[j] == '.')
					continue;
				board[i][j] = c[j] - '0';
				map[i][j] = true;// 성벽만
			}
		}
		Queue<int[]> tmp = new ArrayDeque<>();
		// 파도
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (!map[i][j])
					continue;
				int cnt = 0;//주변에 높이가 0인곳
				for (int k = 0; k < 8; k++) {
					int rr = i + dr[k];
					int cc = j + dc[k];
					if (board[rr][cc] <= 0) {
						cnt++;
					}
				}
				tmp.offer(new int[] {i,j,cnt});

			}
		}
		
		while(!tmp.isEmpty()) {
			int [] info = tmp.poll();
			int r = info[0];
			int c = info[1];
			int cnt = info[2];
			
			board[r][c] -= cnt;
			if (board[r][c] <= 0)
				w.offer(new int[] { r, c });// 녹은 곳
		}

		bfs(w);

		System.out.println(ans);
	}

	// 성벽 , 녹은 곳
	static void bfs(Queue<int[]> w) {
		int size = 1;
		int bef = -1;
		Queue<int[]> q = new ArrayDeque<>();
		
		while (!w.isEmpty()) {
			if(--size==0) {
				size = w.size();
				if(bef==size)return;
				
				ans++;
			}
			
			int [] info = w.poll();
			int r = info[0];
			int c = info[1];			
			
			for (int i = 0; i < 8; i++) {
				int rr = r + dr[i];
				int cc = c + dc[i];
				
				if(map[rr][cc]) {
					q.offer(new int[] {rr,cc});
				}
			}
			
			if(size==1) {
				while(!q.isEmpty()) {
					int [] info1 = q.poll();
					int r1 = info1[0];
					int c1 = info1[1];					
					if(--board[r1][c1]==0) {
						w.offer(info1);
						map[r1][c1]=false;
					}
				}
			}
			
		}
	}
}