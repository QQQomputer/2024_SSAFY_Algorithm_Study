package SWEA_D2_1945_간단한소인수분해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//8m
public class JH {
	static int N,ans, a, b, c, d, e;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			check(N);
			sb.append("#").append(t).append(" ")
			.append(a).append(" ")
			.append(b).append(" ")
			.append(c).append(" ")
			.append(d).append(" ")
			.append(e).append(" ")
			.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void check(int n) {
		 a=b=c=d=e=0;
		while(n>0 && n%2==0) {
			a++;
			n/=2;
		}
		while(n>0 && n%3==0) {
			b++;
			n/=3;
		}
		while(n>0 && n%5==0) {
			c++;
			n/=5;
		}
		while(n>0 && n%7==0) {
			d++;
			n/=7;
		}
		while(n>0 && n%11==0) {
			e++;
			n/=11;
		}
	}
}