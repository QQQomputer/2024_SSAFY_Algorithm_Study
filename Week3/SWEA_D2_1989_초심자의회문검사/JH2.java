package SWEA_D2_1989_초심자의회문검사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//10m
//메서드o 135ms -> 메서드x 101ms
public class JH2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N,ans;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {			
			ans = 1;
			String str = br.readLine();
			int l = 0;
			int r = str.length()-1;
			while(l<r) {
				if(str.charAt(l) != str.charAt(r)) {
					ans = 0;
					break;
				}
				l++; r--;
			}
			
			//ans = isPalindrome(br.readLine());
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
//	static int isPalindrome(String str) {
//		int l = 0;
//		int r = str.length()-1;
//		while(l<r) {
//			if(str.charAt(l) != str.charAt(r))
//				return 0;
//			l++; r--;
//		}		
//		return 1;
//	}
}
