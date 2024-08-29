package BOJ6603_S2_로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
	09:07
	문제 잘 읽기
	
	포함되는 수 나열 됨
	

*/
class JH {
	static int M;
	static int [] arr;
	static boolean [] isUsed;
	static int [] subArr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;

		subArr = new int[6];
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		while(M>0) {			
			sb.setLength(0);
			arr = new int[M];
			isUsed = new boolean[M];
			for (int i = 0; i < M; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			subset(0,0);
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			
			System.out.println(sb.toString());	
		}
	}
	static void subset(int n, int idx) {
		if(n==6) {
			for (int i = 0; i < 6; i++)
				sb.append(subArr[i]).append(" ");
			sb.setLength(sb.length()-1);
			sb.append("\n");
			return;
		}
		for (int i = idx; i < M; i++) {
			if((6-n-1)>(M-i-1))break;
			if(!isUsed[i]) {
				isUsed[i]=true;
				subArr[n]=arr[i];
				subset(n+1,i+1);
			 	isUsed[i]=false;
			}
		}
	}
}