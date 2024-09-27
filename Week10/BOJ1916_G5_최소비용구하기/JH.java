package BOJ1916_G5_최소비용구하기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
		N(1 ≤ N ≤ 1,000)
		버스의 개수 M(1 ≤ M ≤ 100,000)
		M+2줄 버스의 정보
	
	#review
	
	
	
*/
class JH {
	static int N,M, ans;
	static int [] arr;
	static ArrayList<Node> [] adj;
	static int[] dist;
	static final int INF = 987654321;
	static class Node implements Comparable<Node>{
		int v,w;
		
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		ans = 0;
		arr = new int[N];
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[a].add(new Node(b, w));
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		dijkstra(s,e);
		System.out.println(dist[e]);
	}
	static void dijkstra(int start, int end) {
		PriorityQueue<Node> q= new PriorityQueue<>();
		q.offer(new Node(start, 0));
		boolean [] visit = new boolean[N+1];
		dist[start] = 0;
		while(!q.isEmpty()) {
			Node curr =  q.poll();
			if(visit[curr.v]) continue;
			visit[curr.v]= true;
			
			for (Node node : adj[curr.v]) {
				int v = node.v;
				int w = node.w;
				
				if(!visit[v] && dist[v] > dist[curr.v]+w) {
					dist[v]= dist[curr.v]+ w;
					q.offer(new Node(v, dist[v]));
				}
			}
		}
	}
}