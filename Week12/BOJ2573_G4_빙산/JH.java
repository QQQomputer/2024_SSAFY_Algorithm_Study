package BOJ2573_G4_빙산;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	빙산을 그림 1과 같이 2차원 배열에 표시
	
	
	#review
	
	
	
*/
class JH {
	static int N,M,ans,round;
	static int [] arr;
	static int [][] board;
	static boolean [][] checked;
	static int [] dy = {-1,0,1,0};
	static int [] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board=new int[N][N];
		checked=new boolean[N][N];
		
		Queue<int[]> q = new ArrayDeque<>();
//		q.offer(new int[] {1,1,1});
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
				if(board[i][j]==0)continue;				
				q.offer(new int[] {i,j,board[i][j]});
				checked[i][j]=true;
			}
		}
		
		int size = 1;

		boolean flag = false;
		
		root:while(!q.isEmpty()) {
			
			if(--size==0) {
				size = q.size();
				
				boolean [][] ch = new boolean [N][];
				for (int i = 0; i < N; i++)
					ch[i]=Arrays.copyOf(checked[i], M);
				
				int cnt=size;
				flag = false;
				for (int [] ar : q) {
					int i = ar[0];
					int j = ar[1];
					if(!ch[i][j])continue;		
					if(bfs(i,j,ch,cnt)==0) {
						flag=true;
						break;
					}else {
						break root;
					}
				}
				round++;
			}
			
			
			int [] info = q.poll();
			int y = info[0];
			int x = info[1];
			int h = info[2];
			
			for (int i = 0; i < 4; i++) {
				int yy = y+dy[i];
				int xx = x+dx[i];				
				if(check(yy,xx) || checked[yy][xx])continue;
				h--;
				if(h==0)break;
			}		
			
		}
		
		System.out.println(flag?round:0);
	}
	static int bfs(int y, int x, boolean[][] ch,int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {y,x});
		
		while(!q.isEmpty()) {
			int [] info = q.poll();
			y = info[0];
			x = info[1];
			cnt--;
			for (int i = 0; i < 4; i++) {
				
				int yy = y + dy[i];
				int xx = x + dx[i];
				if(check(yy,xx) || !ch[yy][xx])continue;
				ch[yy][xx]=false;
				q.offer(new int[] {yy,xx});
			}
		}
		
		return cnt;
	}
	static boolean check(int y, int x) {
		return y<0 || y>=N || x<0 || x>=M;
	}
}