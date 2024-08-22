package SWEA_D2_1928_Base64Decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.StringTokenizer;
//15m
public class JH {
	//36 => 27
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,ans;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			byte [] bb = Base64.getDecoder().decode(br.readLine());
			for (byte b : bb)
				sb.append((char)b);			
			sb.append("\n");
		}		
		System.out.println(sb.toString());
	}
}