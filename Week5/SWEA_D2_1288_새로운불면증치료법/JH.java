package SWEA_D2_1288_새로운불면증치료법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//5m
public class JH {
	static int N,ans;
	static boolean [] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new boolean[10];
			int i,sum = 0;
			for (i = N;; i+=N) {
				sum+=check(i);
				if(sum==10)break;
			}			
			sb.append("#").append(t).append(" ").append(i).append("\n");
		}
		System.out.println(sb.toString());
	}
	static int check(int n) {
		int sum = 0;
		while(n>0) {
			if(!arr[n%10]) {
				arr[n%10]=true;
				sum++;
			}
			n/=10;
		}
		return sum;
	}
}