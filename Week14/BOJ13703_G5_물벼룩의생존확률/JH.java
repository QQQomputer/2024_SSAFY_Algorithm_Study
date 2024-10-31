package BOJ13703_G5_물벼룩의생존확률;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	 k 센티미터 아래에 있는 물벼룩
	1초마다 각각 1/2의 확률로 위 또는 아래로 1 센티미터 이동
	물벼룩은 수면 - bye
	
	살아남는 경우의 수 - S
	
	
	
	k=0 일경우 바로 죽음
	절반 가능 (2^n) => 나머지 절반 경우의 수만 구하기
	
	우리는 0에서 시작한다고 생각
	위로 올라 갈 경우 +
	아래로 갈 경우 -
	행 k 도착 시 죽음
	
	#review
	
	
	
*/
class JH {
	static int K,N;
	static long ans;
	static long [][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		//깊이
		K = Integer.parseInt(st.nextToken());
		//시간
		N = Integer.parseInt(st.nextToken());
		
        if(K==0) {
			System.out.println(ans);
			return;
		}
        
		//깊이, 시간
		dp=new long[N+K+2][N+1];
		
		//시작 상태 - 0초
		dp[K][0]=1;
		
		int t = 0;
		
		while(t++<N) {
				for (int i = K+t; i>=K-t && i>0 ; i-=2) {
					dp[i][t]=dp[i-1][t-1]+dp[i+1][t-1];
				}
		}
		
		
		//출력 결과
//		for (int i = 0; i <= N+K; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		
		for (int i = 1; i < dp.length; i++) {
			ans+=dp[i][N];
		}
		
		System.out.println(ans);
	}
}