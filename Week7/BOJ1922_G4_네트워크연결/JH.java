package BOJ1922_G4_네트워크연결;

import java.io.IOException;
import java.util.PriorityQueue;
/*
	132ms
*/
class JH {
	static int V,E, ans;
	static int [] p;
	static PriorityQueue<Edge> adj;
	static class Edge implements Comparable<Edge>{
		int a,b,w;
		
		public Edge(int a,int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException {
		V = readInt();
		E = readInt();
		
		adj = new PriorityQueue<>();
		
		p = new int[V+1];
		for (int i = 1; i <= V; i++) {
			p[i]=i;
		}
		
		for (int i = 0; i < E; i++) {
			int a = readInt();
			int b = readInt();
			int w = readInt();
			adj.offer(new Edge(a, b, w));
		}
		int cnt = V-1;
		int sum = 0;
		while(!adj.isEmpty()) { 
			Edge e = adj.poll();
			
			int a = e.a;
			int b = e.b;
			
			int fa = findSet(a);
			int fb = findSet(b);
			
			if(fa!=fb) {
				union(fa, fb);
				cnt--;
				sum+=e.w;
			}
			if(cnt==0)break;
		}
		System.out.println(sum);
	}
	static int findSet(int x) {
		if(p[x]!=x)p[x]=findSet(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[y]=x;
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
