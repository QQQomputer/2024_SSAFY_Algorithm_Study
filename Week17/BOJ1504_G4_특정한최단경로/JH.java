package BOJ1504_G4_특정한최단경로;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/*
	#start
	
	#end
	
	#concepion
	방향성이 없는 그래프
	1번 정점에서 N번 정점으로 최단 거리
	임의로 주어진 두 정점은 반드시 통과
	한번 이동했던 간선도 다시 이동
	
	[입력]
	정점의 개수 N과 간선의 개수 E (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000)
	a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c (1 ≤ c ≤ 1,000) 
	반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2 (v1 ≠ v2, v1 ≠ N, v2 ≠ 1)
	
	
	#review
	
	
	
*/
class JH {
	static final int INF = 987654321;
	static int N,E,ans,v1,v2,R1,R2;
	static int [] dist;
	static List<Node> [] adj;
	static boolean flag;
	static class Node implements Comparable<Node>{
		int v,w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException {
		N = readInt();
		E = readInt();
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		adj = new ArrayList[N+1];
		for(int i = 1;i<=N;i++)adj[i] = new ArrayList<>();
		
		for (int i = 0; i < E; i++) {
			int u = readInt();
			int v = readInt();
			int w = readInt();
			adj[u].add(new Node(v, w));
			adj[v].add(new Node(u, w));
		}
		
		v1 = readInt();
		v2 = readInt();
		
		//st -> v1, v2
		dijkstra(1);
		if(dist[v1]==INF || dist[v2]==INF)flag = true;
		else {
			R1+=dist[v1];
			R2+=dist[v2];
		}
		
		//v1 -> v2
		if(!flag) {
			Arrays.fill(dist, INF);
			dijkstra(v1);
			if(dist[v2]==INF || dist[N]==INF)flag = true;
			else {
				R1+=dist[v2];
				R2+=dist[v2]+dist[N];
			}
		}
		//v2 -> ed
		if(!flag) {
			Arrays.fill(dist, INF);
			dijkstra(v2);
			if(dist[N]==INF)flag = true;
			else {
				R1+=dist[N];
			}
		}
		System.out.println(flag?-1:R1>R2?R2:R1);
	}
	static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean [] vst = new boolean[N+1];
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(!vst[curr.v]) {
				vst[curr.v]=true;
				for(Node node : adj[curr.v]) {
					if(!vst[node.v] && dist[node.v] > dist[curr.v] + node.w) {
						dist[node.v]=dist[curr.v] + node.w;
						pq.add(new Node(node.v, dist[node.v]));
					}
				}
			}
		}
	}
	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while(read<'0'||read>'9') {
			read = System.in.read();
		}
		while(read>='0'&&read<='9') {
			result = 10*result + read-'0';
			read = System.in.read();
		}
		return result;
	}
}