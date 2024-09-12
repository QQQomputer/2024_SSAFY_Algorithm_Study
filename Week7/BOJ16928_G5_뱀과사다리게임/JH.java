package BOJ16928_G5_뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
	72ms
	
*/
class JH {
	static final int INF = 987654321;
	static int N,M,V,ans;
	static List<Node>[] adjList;
	static int[] dist;
	static int [] arr;
	static class Node implements Comparable<Node>{
		int v,w;		
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans=0;		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = 101;
		dist = new int[V];
		arr = new int[V];
		adjList = new ArrayList[V];
		for (int i = 1; i < V; i++)adjList[i]=new ArrayList<>();
		
		for (int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			int in = Integer.parseInt(st.nextToken());
			arr[out]=in;
		}
		
		for (int i = 1; i < 100; i++) {
			for (int j = 1; j <= 6; j++) {
				if(i+j>100)break;
				if(arr[i+j]==0)
					adjList[i].add(new Node(i+j, 1));
				else
					adjList[i].add(new Node(arr[i+j], 1));
			}
		}
		
		Arrays.fill(dist, INF);
		
		dijkstra(1);
		if(dist[100]==INF)
			ans = -1;
		else 
			ans = dist[100];

		System.out.println(ans);
	}
	static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(start, 0));
		dist[start]=0;
		root:while(!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.v == 100) break; 
			if(dist[curr.v]<curr.w)continue;	
			for (Node node : adjList[curr.v]) {
				if(dist[node.v]>dist[curr.v]+node.w) {
					dist[node.v] = dist[curr.v]+node.w;
					if(node.v==100)break root;
					q.offer(new Node(node.v, dist[node.v]));
				}
			}			
		}
	}
}
