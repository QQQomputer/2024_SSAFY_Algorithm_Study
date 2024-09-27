package BOJ2252_G3_줄세우기;
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
	 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000) => readInt 메서드 구현
	 
	 32,000*32,000*4 => 4gb 터짐
	
	#review
	
	
	
*/
class JH2 {
	static int N,M;
	static int [] out;
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		out=new int[N+1];
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj[i]= new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			out[b]++;
		}
		
		sort();
		System.out.println(sb);
	}
	static void sort() {
		Queue<Integer> q = new ArrayDeque<>();
		int cnt = N;
		for (int i = 1; i <= N; i++) {
			if(out[i]==0) {
				q.offer(i);
				cnt--;
				sb.append(i).append(" ");
			}
		}
		
		root:while(!q.isEmpty() && cnt!=0) {
			int o = q.poll();
			int size = adj[o].size();
			for (int i = 0; i < size; i++) {
				int in = adj[o].get(i);
				if(--out[in]==0) {
					q.offer(in);
					sb.append(in).append(" ");
					cnt--;

					adj[o].remove(i);
					size--;
					i--;
				}
			}
		}
	}
}
