package BOJ3665_G1_최종순위;
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
	
	
	#review
	
	
	
*/
class JH {
	static int T,N,M,ans;
	static int [] arr;
	static List<Integer> [] adj;
	static int [] deg;
	public static void main(String[] args) throws IOException {
		
		T = readInt();
		
		for (int t = 0; t < T; t++) {
			StringBuilder sb = new StringBuilder();
			N = readInt();
			adj= new ArrayList[N+1];
			deg = new int[N+1];
			for (int i = 0; i <= N; i++) adj[i]=new ArrayList<>();
			arr = new int[N+1];
			for (int i = 1; i <= N; i++) {
				arr[i]=readInt();
				deg[i]+=i;
				for (int j = 1; j < i; j++) {
					adj[j].add(i);
				}
			}
			M=readInt();
			//순서 바뀜
			for (int i = 0; i < M; i++) {
				int in = readInt();
				int out = readInt();
				if(adj[in].contains(out)) {
					adj[in].remove(out);
					adj[out].add(in);
					deg[out]--;
					deg[in]++;
				}else {
					adj[out].remove(in);
					adj[in].add(out);
					deg[in]--;
					deg[out]++;
				}
			}
			
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				if(deg[i]==0) {
					q.offer(i);
				}
			}
			
			int cnt = 0;
			while(!q.isEmpty()) {
				if(q.size()>1) {
					
					break;
				}
				int n = q.poll();
				sb.append(n).append(" ");
				cnt++;
				for (int a : adj[n]) {
					deg[a]--;
					if(deg[a]==0) q.offer(a);
				}
			}
            if(cnt != N){
            	System.out.println("IMPOSSIBLE");
            } else {
            	System.out.println(sb.toString());
            }
		}
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