package SWEA_D2_1984_중간평균값구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//4m
public class JH {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,ans;
	static int [] arr;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		arr = new int[10];
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			ans = 0;
			for (int i = 0; i < 10; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
				ans+=arr[i];
			}
			Arrays.sort(arr);
			ans-=arr[0];
			ans-=arr[9];
			ans=(int)Math.round(1.0*ans/8.0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
