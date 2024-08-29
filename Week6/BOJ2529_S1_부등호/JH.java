package BOJ2529_S1_부등호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
11:07
9876543210
0123456789

최대 최소 찾기
아래서 위로 
위에서 아래로 탐색 시 멈추는 로직

1h30m
216ms

#
- 백트래킹을 의식하면서 풀었지만 가지치기 더 할 수 있었던 문제
	=> 백트래킹으로 다시 한번 풀기
 */
class JH {
	static int N,cur,ans;
	static boolean [] dir;
	static boolean [] isUsed;
	static int [] ord;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine())+1;
		dir = new boolean [N];
		ord = new int [N];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++)
			dir[i] = st.nextToken().charAt(0)=='<'?true:false;

		flag = false;
		isUsed = new boolean [10];
		findMax(0);		
//		str = Arrays.toString(ord);
//		String max = str.substring(1, str.length()-1).replace(", ", "");
		for (int i = 0; i < N; i++)
			sb.append(ord[i]);
		sb.append("\n");
		
		flag = false;
		isUsed = new boolean [10];
		findMin(0);
//		String str = Arrays.toString(ord);
//		String min = str.substring(1, str.length()-1).replace(", ", "");
		for (int i = 0; i < N; i++)
			sb.append(ord[i]);
		sb.append("\n");

		System.out.println(sb.toString());
	}
	static void findMin(int n) {
		if(n==N) {
			for (int i = 1; i < N; i++) {
				if(dir[i]) {
					if(ord[i-1]>ord[i])return;
				}
				else {
					if(ord[i-1]<ord[i])return;
				}
			}
			flag=true;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(!isUsed[i]) {
				isUsed[i]=true;		
				ord[n]=i;
				findMin(n+1);
				if(flag)return;
				isUsed[i]=false;		
			}
		}
	}
	static void findMax(int n) {
		if(n==N) {
			for (int i = 1; i < N; i++) {
				if(dir[i]) {
					if(ord[i-1]>ord[i])return;
				}
				else {
					if(ord[i-1]<ord[i])return;
				}
			}
			flag=true;
			return;
		}
		for (int i = 9; i >= 9-(N-1) ; i--) {
			if(!isUsed[i]) {
				isUsed[i]=true;		
				ord[n]=i;
				findMax(n+1);
				if(flag)return;
				isUsed[i]=false;		
			}
		}
	}
}