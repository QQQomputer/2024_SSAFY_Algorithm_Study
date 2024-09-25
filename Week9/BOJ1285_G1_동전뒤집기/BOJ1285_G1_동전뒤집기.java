package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	18:00
	뒷면(T)이 위를 향하는 동전 개수를 최소
	3
	HHT
	THH
	THT
	
*/
public class BOJ1285_G1_동전뒤집기 {
	static int N, ans;
	static int [] masking;
	static int [] backup;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		ans = Integer.MAX_VALUE;
//		int a = 0x00ff00ff;		
//		System.out.println(a);
//		System.out.println(Integer.toBinaryString(a));

		N=Integer.parseInt(br.readLine());
		masking = new int[N];
		backup = new int[N];
		for (int i = 0; i < N; i++) {
			int bit = 0;
			char c [] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if(c[j]=='T')bit|=1<<j;
			}
			backup[i]=bit;
		}
		
		//debugging
//		for (int i = 0; i < N; i++) System.out.println(String.format("%"+N+"s", Integer.toBinaryString(masking[i])).replace(" ", "0"));
		
		int subset = 1<<N-1;//모든 행 뒤집는 경우
		for (int i = subset; i > 0; i--) {
			System.arraycopy(backup, 0, masking, 0, N);
			for (int j = 0; j < N; j++) {
				int com = 1<<j;
				if((i&com) == com) masking[j] = ~masking[j];
			}
			
			// 열 확인
			int num = check();
			ans = Math.min(num, ans);
		}
		
		System.out.println(ans);
	}
	static int check() {
		int sum=0;
		
		for (int col = 0; col < N; col++) {
			int cnt = 0;
			int com = 1<<col;
			for (int row = 0; row < N; row++)
				if((masking[row] & com) == com)cnt++;
			sum+=Math.min(cnt, N-cnt);
		}
		
		return sum;
	}
	//32bit 계산
	// 참고 : https://blog.naver.com/zords/221476995246
//	static int bitCount(int b) {
//	    b = (b & 0x55555555) + ((b >> 1) & 0x55555555);
//	    b = (b & 0x33333333) + ((b >> 2) & 0x33333333);
//	    b = (b & 0x0F0F0F0F) + ((b >> 4) & 0x0F0F0F0F);
//	    b = (b & 0x00FF00FF) + ((b >> 8) & 0x00FF00FF);
//	    b = (b & 0x0000FFFF) + ((b >> 16) & 0x0000FFFF);
//	    return b;
//	}
}
/*
HTHH
THTH
HHHT
HTTH
*/