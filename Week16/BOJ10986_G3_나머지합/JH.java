package BOJ10986_G3_나머지합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	#start
	
	#end
	
	#concepion
	골3 그냥 sw 하면 틀리지 않을까?
	
	N 100만개
	sw로 하면 시간은 충분 할듯 => 아님
	
	
	1,000,000,000
	
	1~N 경우의 수
	
	#review
	
	
	
*/
class JH {
	static int N, M;
	static long ans;
	static int[] arr;
	static int[] cntArr;

	public static void main(String[] args) throws IOException {

		N = readInt();
		M = readInt();// 나머지
		cntArr = new int[M];
		arr = new int[N];
		arr[0] = readInt() % M;
		cntArr[arr[0]]++;
		for (int i = 1; i < N; i++) {
			arr[i] = (arr[i - 1] + readInt()) % M;
			cntArr[arr[i]]++;
		}
		ans += cntArr[0];
		for (int i = 0; i < M; i++) {
			if (cntArr[i] == 0)
				continue;
			ans += 1L * cntArr[i] * (cntArr[i] - 1) / 2;
		}

		System.out.println(ans);
	}

	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		while (read < '0' || read > '9')
			read = System.in.read();
		while (read >= '0' && read <= '9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		return result;
	}
}