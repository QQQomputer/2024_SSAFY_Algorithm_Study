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
	 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000) => readInt 메서드 구현
	 
	 32,000*32,000*4 => 4gb 터짐
	
	#review
	메서드 호출이 1번 뿐인데 시간차이가 유의미하게 나는듯
	
	
*/
class JH {
	static int N,M;
	static int [] out;
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		out=new int[N+1];
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj[i]= new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
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
		
		while(!q.isEmpty() && cnt!=0) {
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
	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while(read>'9'|| read<'0')read = System.in.read();
		while(read>='0' && read <='9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		return result;
	}
}
