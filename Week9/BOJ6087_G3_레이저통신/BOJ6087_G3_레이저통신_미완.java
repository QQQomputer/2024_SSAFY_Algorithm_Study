package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
	09:24
	
	BFS 코너 돌때마다 VAL+1
	
*/
public class BOJ6087_G3_레이저통신 {
	static int W,H, ans;
	static class Node implements Comparable<Node>{
		int y,x,val,dir;
		public Node(int y, int x, int val, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.val = val;
			this.dir = dir;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.val-o.val;
		}
	}
	static char [][] checked;
	static int [][][] count;
	static int[][] pnt;
	static int [] dy = {-1,0,1,0};
	static int [] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		pnt = new int[2][4];
		ans = Integer.MAX_VALUE;
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		checked = new char[H][W];
		count = new int[4][H][W];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(count[i][j], Integer.MAX_VALUE);
            }
        }
		// 배열 받기
		for (int i = 0; i < H; i++) {
			checked[i] = br.readLine().toCharArray();
		}
		int cnt = 0;
		// C구역 2개 얻기
		root:for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(checked[i][j]=='C') {
					pnt[cnt][0]=i;
					pnt[cnt++][1]=j;
					if(cnt==2)break root;
				}
			}
		}
		
		int y1 = pnt[0][0];
		int x1 = pnt[0][1];
		int y2 = pnt[1][0];
		int x2 = pnt[1][1];
		System.out.println(y1+" "+x1+" "+y2+" "+x2);
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(y1,x1,0,-1));
		while(!q.isEmpty()) {
			Node info = q.poll();
			int y = info.y;
			int x = info.x;
			int val = info.val;
			int dir = info.dir;
			
			if(y==y2 && x==x2) {
				System.out.println(1);
				ans = Math.min(ans, val);
			}
			for (int i = 0; i < 4; i++) {
				int yy = y+dy[i];
				int xx = x+dx[i];
				int valval = (dir==i?val:val+1);
				if(check(yy,xx) || checked[yy][xx]!='.' || Math.abs(dir - i) == 2)continue;
				if(count[i][yy][xx] > valval) {
					q.offer(new Node(yy,xx,valval,i));
					count[i][yy][xx]=valval;
				}
			}
		}
		System.out.println(ans);
	}
	static boolean check(int y, int x) {
		return y<0 || y>=H || x<0 || x>=W;
	}
}
