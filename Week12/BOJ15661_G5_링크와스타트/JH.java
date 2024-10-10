package BOJ15661_G5_링크와스타트;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	축구는 평일 오후에 하고 의무 참석도 아니다. 
	스타트 팀과 링크 팀
	
	#review
	
	
	
*/
class JH {
	static int N,sum,ans;
	static int [][] board;
	static boolean [] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		sum = 0;
		ans = Integer.MAX_VALUE;
		board=new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
				sum+=board[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				board[i][j]+=board[j][i];
			}
		}
		
		int n = (1<<N)-1;
		check=new boolean[n+1];
		for (int i = 1; i < n; i++) {
			if(check[i])continue;
//			System.out.println(Integer.toBinaryString(i) + " " + Integer.toBinaryString(((~i)&n)));
			check[i]=check[((~i)&n)]=true;
			int a = (calcStat(i));
			int b= (calcStat((~i)&n));
			int gap = Math.abs(a-b);
			ans = Math.min(ans, gap);
			if(ans==0)break;
		}
		
		System.out.println(ans);
	}
	public static int calcStat(int n) {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			int s1 = 1<<i;
			if((n & s1) != s1) continue;
			for (int j = i+1; j < N; j++) {
				int s2 = 1<<j;
				if((n & s2) != s2) continue;
				sum+=board[i][j];
			}
		}
		
		return sum;
	}
}