package BOJ22944_G3_죽음의비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class JH {
	static int N, H, D, ans, idx, curToNext;
	static Node[] arr;
	static int[][] distance;
	static char[] board;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new Node[12];

		idx = 1;
		Node end = null;
		for (int i = 0; i < N; i++) {
			board = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (board[j] == 'U') {
					arr[idx++] = new Node(i, j);
				} else if (board[j] == 'S') {
					arr[0] = new Node(i, j);
				} else if (board[j] == 'E') {
					end = new Node(i, j);
				}
			}
		}

		arr[idx++] = end;
		distance = new int[idx][idx];

		for (int i = 0; i < idx; i++) {
			for (int j = i + 1; j < idx; j++) {
				int dis = dis(arr[i], arr[j]);
				distance[i][j] = dis;
				distance[j][i] = dis;
			}
		}

		for (int i = 0; i < idx; i++) {
			distance[i][i] = Integer.MAX_VALUE;
		}

		bfs();

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
//			Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);
		q.offer(new int[] { 0, H, 0, 0, 0 });

		int end = (idx - 1);
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int id = info[0];
			int h = info[1];
			int bit = info[2];
			int move = info[3];
			int um = info[4];

//				if (id == end) {
//					ans = move;
//					return;
//				}

			// (시작, 끝) 비 안오는 곳
			for (int i = 0; i < idx; i += end) {
				int b = (1 << i);
				// 비 안옴
				curToNext = distance[id][i] - 1;
				if (curToNext >= h + um)
					continue;

				if (end == i)
					ans = Math.min(ans, move + distance[id][i]);
				else
				// 우산보다 이동 거리 클 경우
				if (curToNext - um > 0) {
					q.offer(new int[] { i, h - (curToNext - um), bit, move + distance[id][i], 0 });
				}
				// 우산보다 이동 거리 작을 경우
				else {
					q.offer(new int[] { i, h, bit, move + distance[id][i], um - curToNext });
				}

			}

			// (우산)
			for (int i = 1; i < end; i++) {
				int b = (1 << i);
				boolean flag = (bit & b) == b;
				// 우산 이미 가져간 칸
				if (flag) {
					curToNext = distance[id][i];
					if (curToNext >= h + um)
						continue;
					// 우산보다 이동 거리 클 경우
					if (curToNext - um > 0) {
						q.offer(new int[] { i, h - (curToNext - um), bit, move + distance[id][i], 0 });
					}
					// 우산보다 이동 거리 작을 경우
					else {
						q.offer(new int[] { i, h, bit, move + distance[id][i], um - curToNext });
					}
				}
				// 우산 가져가는 칸
				else {
					curToNext = distance[id][i] - 1;
					if (curToNext >= h + um)
						continue;
					q.offer(new int[] { i, h - ((curToNext - um) > 0 ? curToNext - um : 0), bit | b,
							move + distance[id][i], D - 1 });
				}
			}
		}
	}

	static int dis(Node a, Node b) {
		int y1 = a.y;
		int x1 = a.x;
		int y2 = b.y;
		int x2 = b.x;
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
}