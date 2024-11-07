package BOJ15730_P5_수영장사장님;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import sample.test.Node;

/*
	#start
	09
	#end
	
	#concepion
	boolean[10000][100][100] 3차 체크배열 190MB 불가
	100*100 => long[10000][100][2] 에 담는다면? 15.25
	
	1. 층별 bfs
	2. 
	
	
	#review
	
	
	
*/
class JH {
	static int N, M, ans, N1, M1, minH;
	static long[][] checked;
	static int[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static class Node implements Comparable<Node> {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return board[o.r][o.c] - board[this.r][this.c];
		}
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		N1 = N - 1;
		M1 = M - 1;
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = readInt();
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 1; i < N1; i++) {
			for (int j = 1; j < M1; j++) {
				Node n = new Node(i, j);
				pq.offer(n);
			}
		}
		
		checked = new long[N][2];
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int f = board[n.r][n.c] + 1;
			ans += bfs(n.r, n.c, f);
		}
		System.out.println(ans);
	}

	/*
	 * sr : 시작행, sc: 시작열, f: 층
	 */
	static int bfs(int sr, int sc, int f) {
		long b = 1L<<(sc%50);
		if ((checked[sr][sc/50]&b)==b)
			return 0;

		int cnt	 = 0, sum = 0;
		long[][] bfsChecked = new long[N1][2];
		bfsChecked[sr][sc/50] |= b;
		minH = 10000;
		Queue<Integer> q = new ArrayDeque<>();
		
		// r(100), c(100)
		// 2^7 2^7
		q.offer(sc << 7 | sr);
		while (!q.isEmpty()) {
			int info = q.poll();
			int r = info & 127;
			int c = (info >> 7) & 127;
			cnt++;
			sum+=board[r][c];
			
			for (int i = 0; i < 4; i++) {
				int rr = r + dr[i];
				int cc = c + dc[i];
				// 겉 테두리 도착 시
				if (check(rr, cc)) {
					// f보다 낮으면 return 0;
					if (board[rr][cc] < f)
						return 0;
					if (minH > board[rr][cc])
						minH = board[rr][cc];
					continue;
				}
				// 높이 같거나 높을 시
				if (board[rr][cc] >= f) {
					if (minH > board[rr][cc])
						minH = board[rr][cc];
					continue;
				}
				// 이미
				int idx = cc/50;
				long bit = 1L<<(cc%50);
				if((bfsChecked[rr][idx]&bit)==bit)continue;
				bfsChecked[rr][idx] |= bit;
				q.offer(cc << 7 | rr);			
			}

		}

		for (int i = 1; i < N1; i++) {
			for (int j = 0; j < 2; j++) {
				checked[i][j]|=bfsChecked[i][j];
			}
		}
		
		return cnt * minH - sum;
	}

	// 기존 체크 배열 보다 한칸씩 줄임
	static boolean check(int r, int c) {
		return r <= 0 || r >= N1 || c <= 0 || c >= M1;
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