package BOJ1976_G4_여행가자;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	#start
	12:23
	#end
	
	#concepion	
	도시들의 개수와 도시들 간의 연결 여부
	도시의 수 N, N은 200이하
	여행 계획에 속한 도시들의 수 M
	같은 도시를 여러 번 방문하는 것도 가능하다.
	
	i번째 줄의 j번째 수는 
	i번 도시와 j번 도시의 연결 정보를 의미
	인접행렬
	
	
	
	
	#review
	
	
	
*/
class JH {
	static int N,M;
	static boolean ans = true;
	static int [] arr;
	static int [] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		if(M>1) {
			p = new int[N+1];	
			for (int i = 1; i <= N; i++) p[i]=i;

			Queue<int[]> q = new ArrayDeque<>();
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < i; j++) {
					if(st.nextToken().charAt(0)==49) {
						q.offer(new int[] {i,j});
					}
				}
			}
			
			int cnt = 0;
			while (!q.isEmpty()) {
				int [] info = q.poll();
				int fx = findSet(info[0]);
				int fy = findSet(info[1]);
				
				if(fx!=fy) {
					union(fx,fy);					
					if(++cnt==N-1)break;
				}
			}
			
			arr = new int[M];
			
			st = new StringTokenizer(br.readLine());
			arr[0]=Integer.parseInt(st.nextToken());			
			for (int i = 1; i < M; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
				if(findSet(arr[i-1])!=findSet(arr[i])) {
					ans=false;
					break;
				}
			}
			
		}
		
		System.out.println(ans?"YES":"NO");
	}
	static int findSet(int x) {
		if(p[x]!=x)p[x]=findSet(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[y]=x;
	}
}
