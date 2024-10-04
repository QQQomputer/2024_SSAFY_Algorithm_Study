package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	양의 실수
	(0,0) -> (dx,?)
	y*y=(dx*dx)+(dy*dy);
	
	
	y=-ax+b;
	y=cx;
	
	x=b/(a+c)
	y=((c-a)(b/(a+c))+b)/2
	
	(dx,10)
	
	만약 dx 가 5라면
	
	
	3,000,000,000
	3000000000 3000000000 1000000000
	3000000000 100 1
*/
public class BOJ2022_G4_사다리 {
	static double x, y, c, min;
	static double xx, yy, cc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		x = Double.parseDouble(st.nextToken());
		y = Double.parseDouble(st.nextToken());
		c = Double.parseDouble(st.nextToken());
		xx = x * x;
		yy = y * y;
		min = Math.min(x, y);
		
		System.out.println((double)Math.round(bs(0,min)*1000)/1000.0);
	}
	static double bs(double l, double h) {
		
		while(h - l > 1e-6) {
			double mid = (l+h)/2; // 밑 길이
			double midmid = mid*mid;
			
			//두변 합 == 밑변 길이
			//두변 합 > 밑변 길이	밑변 길이 김
			//두변 합 < 밑변 길이	밑변 길이 짧음			
			double two = c*(Math.sqrt(xx-midmid)+Math.sqrt(yy-midmid));
			double one = (Math.sqrt(xx-midmid)*Math.sqrt(yy-midmid));
			
			if(two>=one) {
				h=mid;
			}else if(two<one) {
				l=mid;
			}
		}
		
		return (l + h) / 2;
	}
}
