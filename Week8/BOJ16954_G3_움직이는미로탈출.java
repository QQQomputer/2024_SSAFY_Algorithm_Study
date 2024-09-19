package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	12:01
	01:15
	
	크기가 8×8인 체스판에서 탈출하는 게임
	욱제의 캐릭터는 가장 왼쪽 아랫 칸 > 가장 오른쪽 윗 칸
	
	"1초에 인접한 한 칸" 또는 "대각선 방향으로 인접한 한 칸"
	현재 위치에 서 있을 수 있다.
	
	1초 동안 욱제의 캐릭터가 먼저 이동하고, 그 다음 벽이 이동
	
	가장 오른쪽 윗 칸에 도착할 수 있으면 1, 없으면 0을 출력한다.
	
	#
	bfs start
	1. 7 0 #일 경우 0 출력
	
	9방 => 6방 한칸씩 내려와서 아래로 내려가는 것은 의미가 없음
	
	15,0 => 8+t,7
	
	#
	9방 => 6방 으로 했더니 예외 케이스 존재 했음
	
*/
public class BOJ16954_G3_움직이는미로탈출 {

	static int N, ans;
	static char [][] board = new char[16][8];
	static char [] cleanArea = new char[8];
	static int [] dr = {-1,-1,-1,0,0,0,1,1,1};
	static int [] dc = {1,0,-1,1,0,-1,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(cleanArea, '.');
		for (int i = 0; i < 8; i++) board[i]=cleanArea;
		for (int i = 8; i < 16; i++) board[i]=br.readLine().toCharArray();
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {7,0,0});
		root:while(!q.isEmpty()) {
			int[] info = q.poll();
			int y = info[0];
			int x = info[1];
			int t = info[2];
			for (int i = 0; i < 9; i++) {
				int yy = dr[i]+y;
				int xx = dc[i]+x;
				if(check(yy,xx))continue;
				if(board[8-t+yy][xx]=='#')continue;
				if(t==7) {
					ans=1;
					break root;
				}
				if(board[7-t+yy][xx]=='#')continue;
				q.offer(new int[] {yy,xx,t+1});
			}
		}
		System.out.println(ans);
	}
	static boolean check(int y, int x) {
		return y<0 || y>=8 || x<0 || x>=8;
	}
}
