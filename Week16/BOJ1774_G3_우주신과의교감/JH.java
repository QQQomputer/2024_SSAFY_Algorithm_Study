package BOJ1774_G3_우주신과의교감;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
	#start
	
	#end
	
	#concepion
	mst
	
	
	
	#review
	
	
	
*/
class JH {
	static int N, M;
	static double ans;
	static Node[] arr;
	static int[] p;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class Edge implements Comparable<Edge> {
		int a, b;
		double w;

		public Edge(int a, int b, double w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);// minheap
		}
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();

		p = new int[N];
		for (int i = 1; i < N; i++)
			p[i] = i;

		arr = new Node[N];

		for (int i = 0; i < N; i++)
			arr[i] = new Node(readInt(), readInt());

		// 가중치 계산
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				Node a = arr[i];
				Node b = arr[j];
				long dy = a.y - b.y;
				long dx = a.x - b.x;
				pq.offer(new Edge(i, j, dx * dx + dy * dy));				
			}
		}

		// pq로 union
		int E = N - 1;
		// 연결 된 곳 먼저 연결
		for (int i = 0; i < M; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int fa = findSet(a);
			int fb = findSet(b);
			if (fa != fb) {
				unionSet(fa, fb);
				if (--E == 0)
					break;
			}
		}
		if (E > 0)
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				int fa = findSet(e.a);
				int fb = findSet(e.b);
				if (fa != fb) {
					unionSet(fa, fb);
					ans += Math.sqrt(e.w);
					if (--E == 0)
						break;
				}
			}

		System.out.printf("%.2f", Math.round(ans * 100) / 100.0);
	}

	static int findSet(int x) {
		if (p[x] != x)
			p[x] = findSet(p[x]);
		return p[x];
	}

	static void unionSet(int x, int y) {
		p[y] = x;
	}

	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while (read < '0' || read > '9')
			read = System.in.read();
		while (read >= '0' && read <= '9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		return result;
	}
}