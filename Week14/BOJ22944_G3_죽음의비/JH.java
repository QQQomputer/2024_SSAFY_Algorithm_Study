package BOJ22944_G3_죽음의비;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH {
	static int N,ans;
	static int [] arr;
	static int [][] board;
	static boolean [][] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		
		arr=new int[N];
		board=new int[N][N];
		checked=new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		
		
		System.out.println(ans);
	}
}