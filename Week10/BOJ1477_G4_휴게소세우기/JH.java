package BOJ1477_G4_휴게소세우기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	#start
	23:00
	#end
	
	#concepion
	유료 고속도로 => 가중치?
	고속도로에 휴게소를 N개 
	휴게소를 M개 더 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소
	N+M < L
	
	완탐?
	
	이분탐색
	
	
	#review
	
	
	
*/
class JH {
	static int N,M,L,ans;
	static int [] arr;
	static int [][] board;
	static boolean [][] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr=new int[N+M];
		
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
