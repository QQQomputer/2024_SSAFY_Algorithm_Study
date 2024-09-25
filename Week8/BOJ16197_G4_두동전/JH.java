package BOJ16197_G4_두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	N×M 크기의 보드
	4개 버튼
	
	o: 동전
	.: 빈 칸
	#: 벽
	
	440ms
*/
class JH {
	static int N,M,ans;
	static boolean [][] wall;
	static int [] dy= {-1,1,0,0};
	static int [] dx= {0,0,-1,1};
	static boolean done;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans=1;		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		wall = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			char [] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(c[j]=='o')q.offer(new int[] {i,j});
				else if(c[j]=='#')wall[i][j]=true;
			}
		}
		
		bfs(q);
		
		System.out.println(ans);
	}
	static void bfs(Queue<int[]> q) {
		//맨처음 동전 수
		int size = 2;//q.size();
		wroot:while(!q.isEmpty()) {
			int repeat = q.size()/size;
			
			for (int r = 0; r < repeat; r++) {
				Queue<int[]> qq = new ArrayDeque<>();
				for (int i = 0; i < size; i++) {
					qq.offer(q.poll());
				}
				
				root:for (int i = 0; i < 4; i++) {
					int sum = 0;
					Queue<int[]> add = new ArrayDeque<>();
					for (int j = 0; j < size; j++) {
						int [] info = qq.poll();
						int y = info[0]+dy[i];
						int x = info[1]+dx[i];
						if(check(y,x)) {
							if(done) {
								done = false;
								qq.offer(info);
								continue root;
							}
							done =true;
							qq.offer(info);
							continue;
						}
						if(wall[y][x]) {
							if(++sum==size) {
								qq.offer(info);
								continue root;
							}
							add.offer(info);
						}else {
							add.offer(new int[] {y,x});
						}
						
						qq.offer(info);
					}
					
					if(done) break wroot;
					
					int [] a = add.poll();				
					int [] b = add.poll();
					
					if(a[0]!=b[0] || a[1] != b[1]) {
						q.add(a);
						q.add(b);
					}				
				}
			}
			if(++ans == 11)break;
		}
		if(ans == 11)ans=-1;
	}
	
	static boolean check(int r, int c) {
		return r<0||r>=N||c<0||c>=M;
	}
}
