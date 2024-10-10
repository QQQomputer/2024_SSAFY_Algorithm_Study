package BOJ1516_G3_게임개발;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/*
	#start
	
	#end
	
	#concepion
	성문 크래프트 Zerg
	모든 건물을 짓는데 걸리는 최소의 시간
	
	
	
	#review
	
	
	
*/
class JH {
	static int N,ans;
	static int [] arr;
	static int [] cnt;
	static List<Integer> [] adj;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		N = readInt();
		ans=0;
		arr=new int[N+1];
		cnt=new int[N+1];

		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<>();

		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			arr[i]=readInt();
			int cur = readInt();
			if(cur==-1) {
				q.offer(new int[] {i,arr[i]});
			}else				
				while(cur!=-1) {
					cnt[i]++;
					adj[cur].add(i);
					cur = readInt();
				}
		}
		
		int size = 1;
		while(!q.isEmpty()) {
			if(--size==0) {
				size = q.size();
				ans++;
			}
			
			int [] info = q.poll();
			
			if(--info[1]>0) {
				q.offer(info);
				continue;
			}
			
			int in = info[0];
			arr[in]=ans;
			for (int n : adj[in]) {
				if(--cnt[n]==0)
					q.offer(new int[] {n,arr[n]});
			}
		}

		for (int i = 1; i <= N; i++)
			sb.append(arr[i]).append("\n");
		
		System.out.println(sb);
	}
	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		boolean isMinus = false;
		while(read<'0'||read>'9') {
			if(read=='-')isMinus=true;
			read = System.in.read();
		}
		while(read>='0'&&read<='9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		return isMinus?-result:result;
	}
}