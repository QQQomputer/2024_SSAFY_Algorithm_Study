package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
	01:15
	
	N개의 스위치와 N개의 전구
	
	
100010
000100
	
	
	011010
	000110
	
	001110
	000100
	
	100010
	101100
	110000
	111111
	000110
	111110
	001110
	
	
	100010
	100010
	100010
	
	
	00100000000000000000
	00011000000000000000
	00011000000000000000
	00010110000000000000
	00001010000000000000
	00010101100000000000
	00010101011000000000
	00010101010110000000
	00010101010101100000
	00010101010101011000
	00010101010101010110
	00010101010101010101
	
	
	00000000001010100000
	00000000000000000000
	00000000000000000000
	00000000000000000000
*/
public class BOJ2138_G4_전구와스위치 {
	static int N, ans;
	static int [] arr1, arr2, goal;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		arr1=new int[N];
		goal=new int[N];
		ans = Integer.MAX_VALUE;
		char [] c = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			arr1[i]=c[i]-'0';
		}
		c = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) goal[i]=c[i]-'0';
		arr2 = Arrays.copyOf(arr1, N);
		arr2[0]^=1;
		arr2[1]^=1;
		int cnt = 0;
		for (int i = 0; i < N-2; i++) {
			if(arr1[i] != goal[i]) {
				switchBtn(i);
				cnt++;
			}
		}
		if(arr1[N-2] != goal[N-2]) {
			switchLastBtn(N-2);
			cnt++;
		}
		
		if(arr1[N-1] == goal[N-1])
			ans = cnt;
		
		arr1=arr2;
		cnt = 1;
		for (int i = 0; i < N-2; i++) {
			if(arr1[i] != goal[i]) {
				switchBtn(i);
				cnt++;
			}
		}
		if(arr1[N-2] != goal[N-2]) {
			switchLastBtn(N-2);
			cnt++;
		}
		
		if(arr1[N-1] == goal[N-1])
			ans = Math.min(cnt, ans);
		
		if(ans == Integer.MAX_VALUE)
			ans = -1;
		
		System.out.println(ans);
	}
	static void switchBtn(int i) {
		arr1[i]^=1;
		arr1[i+1]^=1;
		arr1[i+2]^=1;
	}
	static void switchLastBtn(int i) {
		arr1[i]^=1;
		arr1[i+1]^=1;
	}
}
