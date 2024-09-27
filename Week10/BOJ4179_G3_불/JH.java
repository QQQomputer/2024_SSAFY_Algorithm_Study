package BOJ4179_G3_불;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	#start
	23:15
	#end
	
	#concepion
	지훈이의 위치
	불이 붙은 위치를 감안
	지훈이가 불에 타기전에 탈출할 수 있는지의 여부,  얼마나 빨리 탈출할 수 있는지
	지훈, 불 상하좌우 이동
	벽
	
	bfs
	불 사방 등록
	
	
	#review
	
	
	
*/
class JH {
	static int N,M,ans;
	static char [][] board;
	static boolean [][] checked;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		//Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board=new char[N][M];
		checked=new boolean[N][M];

//		Queue<int[]> jj = new ArrayDeque<>();
//		Queue<int[]> ff = new ArrayDeque<>();
		Queue<int[]> q = new ArrayDeque<>();
		int [] pos = new int[3];
		for (int i = 0; i < N; i++) {
			char [] c =br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(c[j]!='.') {
					checked[i][j]=true;
					if(c[j]=='F')q.offer(new int[]{i,j,0});
					else if(c[j]=='J')pos= new int[] {i,j,1};
				}
			}
		}
			
		q.offer(pos);
		
		bfs(q);
		
		System.out.println(ans>0?++ans:"IMPOSSIBLE");
	}
	static void bfs(Queue<int[]> q) {//Queue<int[]> jj, Queue<int[]> ff) {
		int size = q.size();
		Queue<int[]> tmp = new ArrayDeque<>();
		while(!q.isEmpty()) {
			int [] info = q.poll();
			for (int i = 0; i < 4; i++) {
				int yy = info[0]+dy[i];
				int xx = info[1]+dx[i];
				
				//탈출
				if(info[2]==1 && check(yy,xx))return;
				
				if(check(yy,xx) || checked[yy][xx])continue;
				checked[yy][xx]=true;
				
				if(info[2]==0) tmp.offer(new int[] {yy,xx,0});
				else tmp.offer(new int[] {yy,xx,1});
			}
			
			if(--size==0) {
				ans++;
				size = tmp.size();
				q=tmp;
				tmp = new ArrayDeque<>();
			}
		}
		ans = -1;
	}
	static boolean check(int y, int x) {
		return y<0 || y>=N || x<0 || x>=M;
	}
}
