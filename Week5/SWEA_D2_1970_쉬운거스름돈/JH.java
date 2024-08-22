package SWEA_D2_1970_쉬운거스름돈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//15m
public class JH {
	static int N,ans, a, b, c, d, e, f ,g ,h;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			check(N);
			sb.append("#").append(t).append("\n")
			.append(a).append(" ")
			.append(b).append(" ")
			.append(c).append(" ")
			.append(d).append(" ")
			.append(e).append(" ")
			.append(f).append(" ")
			.append(g).append(" ")
			.append(h)
			.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void check(int n) {
		 a=b=c=d=e=f=g=h=0;
		while(n>=50_000) {
			a=n/50_000;
			n-=50_000*a;
		}
		while(n>=10_000) {
			b=n/10_000;
			n-=10_000*b;
		}
		while(n>=5_000) {
			c=n/5_000;
			n-=5_000*c;
		}
		while(n>=1_000) {
			d=n/1_000;
			n-=1_000*d;
		}
		while(n>=500) {
			e=n/500;
			n-=500*e;
		}
		while(n>=100) {
			f=n/100;
			n-=100*f;
		}
		while(n>=50) {
			g=n/50;
			n-=50*g;
		}
		while(n>=10) {
			h=n/10;
			n-=10*h;
		}
	}
}