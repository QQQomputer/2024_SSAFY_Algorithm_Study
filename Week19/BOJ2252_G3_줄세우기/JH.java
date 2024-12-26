package BOJ2252_G3_줄세우기;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH {
	static int N,M,ans;
	static int [] cnt;
	static List<Integer> adj [];
	static Queue<Integer> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		N = readInt();
		M = readInt();
		
		cnt = new int[N+1];
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			int in = readInt();
			int out = readInt();
			cnt[out]++;
			adj[in].add(out);
		}
		
		for (int i = 1; i <= N; i++)
			if(cnt[i]==0) q.offer(i);

		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			
			for (int i : adj[cur])
				if(--cnt[i] == 0)
					q.offer(i);
		}
		
		System.out.println(sb);
	}
	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		
		while(read < '0' || read>'9')read = System.in.read();
		
		while(read>='0'&&read<='9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		
		return result;
	}
}