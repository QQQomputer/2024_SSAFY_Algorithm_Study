package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 	가로 체크
 	세로 체크
 	방 체크
 	
 	
 */
public class BOJ2580_G4_스도쿠 {

	static int N, ans;
	static int [][] board;
	static boolean [][] row, col,room;
	static int [] cnt,rowSum,colSum,roomSum;
	static int zero;
	static int [][] pos;
	static boolean done;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		cnt = new int[10];
		Arrays.fill(cnt, 9);
		board=new int[9][9];
		row=new boolean[9][10];
		col=new boolean[9][10];
		room=new boolean[9][10];
		rowSum=new int[9];
		colSum=new int[9];
		roomSum=new int[9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());

				if(board[i][j]==0) zero++;
				else {
					rowSum[i]+=board[i][j];
					colSum[j]+=board[i][j];
					roomSum[i/3*3+j/3]+=board[i][j];
					row[i][board[i][j]]=true;
					col[j][board[i][j]]=true;
					room[i/3*3+j/3][board[i][j]]=true;
					cnt[board[i][j]]--;
				}
			}
		}
		
		int idx = 0;
		pos=new int[zero][2];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(board[i][j]==0) {
					pos[idx][0]=i;
					pos[idx++][1]=j;
				}
			}
		}
		
		recursion(0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void recursion(int depth) {
		if(zero==depth) {
			done=true;
			return;
		}

		int r = pos[depth][0];
		int c = pos[depth][1];
		for (int i = 1; i < 10; i++) {
			if(cnt[i]==0)continue;
			if(rowSum[r]+i>55)break;
			if(colSum[c]+i>55)break;
			if(roomSum[r/3*3+c/3]+i>55)break;
			if(row[r][i])continue;
			if(col[c][i])continue;
			if(room[r/3*3+c/3][i])continue;
			
			cnt[i]--;
			rowSum[r]+=i;
			colSum[c]+=i;
			roomSum[r/3*3+c/3]+=i;
			row[r][i]=true;
			col[c][i]=true;
			room[r/3*3+c/3][i]=true;
			
			board[r][c]=i;
			recursion(depth+1);
			if(done)return;
			
			cnt[i]++;
			rowSum[r]-=i;
			colSum[c]-=i;
			roomSum[r/3*3+c/3]-=i;
			row[r][i]=false;
			col[c][i]=false;
			room[r/3*3+c/3][i]=false;
		}
	}
}
