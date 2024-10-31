import java.io.*;
import java.util.*;

class Main {
	static int N, ans;
	static int[][] arr;

	public static void main(String[] args) throws IOException{
		
		N = nextInt();
		
		arr = new int[N][2];
		
		arr[0][0]=nextInt();
		arr[0][1]=nextInt();
		
		for (int i = 1; i < N; i++) {
			int x=nextInt();
			int y=nextInt();
			
			if(x<=arr[i-1][1]) {
				arr[i][0]=arr[i-1][0];
				arr[i][1]=Math.max(arr[i-1][1],y);
			}else {
				ans+=arr[i-1][1]-arr[i-1][0];
				arr[i][0] = x;
				arr[i][1] = y;
			}
		}
		ans+=arr[N-1][1]-arr[N-1][0];
		System.out.println(ans);
	}
	static int readInt() throws IOException {
		int result = 0;
		int read = System.in.read();
		boolean isMinus = false;
		while(read<'0'||read>'9') {
			if(read=='-')isMinus=true;
			read = System.in.read();
		}
		while(read>='0'&&read<='9') {
			result = result * 10 + read - '0';
			read = System.in.read();
		}
		return isMinus?-result:result;
	}
    private static byte[] buffer = new byte[1 << 16];
    private static int bufferPointer = 0, bytesRead = 0;

    private static int read() throws IOException {
        if (bufferPointer == bytesRead) {
            bufferPointer = 0;
            bytesRead = System.in.read(buffer);
            if (bytesRead == -1) return -1;
        }
        return buffer[bufferPointer++];
    }

    private static int nextInt() throws IOException {
        int result = 0;
        int c = read();
        
        // 공백 문자를 건너뜁니다.
        while (c <= ' ') c = read();
        
        // 양수 또는 음수 판별
        boolean negative = (c == '-');
        if (negative) c = read();

        // 숫자 부분 읽기
        do {
            result = result * 10 + (c - '0');
            c = read();
        } while (c >= '0' && c <= '9');
        
        return negative ? -result : result;
    }
}