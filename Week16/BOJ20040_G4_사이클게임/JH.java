package BOJ20040_G4_사이클게임;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	선 플레이어가 홀수 번째 차례를, 후 플레이어가 짝수 번째 차례
	게임 시작 시 0 부터 n − 1 까지 고유한 번호가 부여된 평면 상의 점 n 개
	
	3	n	50M
	3	m	100M
	1	i	m
	1	m	
	
	기울기가 같고 절편이 같다면
	
	
	#review
	
	
	
*/
class JH {
	static int N, M, ans, cnt;
	static int[] p;
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();

		p = new int[N];
		for (int i = 1; i < N; i++) p[i] = i;

		while (cnt<M) {
			int fx = findSet(readInt());
			int fy = findSet(readInt());
			++cnt;
			if (fx != fy) {
				union(fx, fy);
			} else {
				System.out.println(cnt);
				return;
			}
		}
		System.out.println(0);
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

	static int findSet(int x) {
		if (p[x] != x)
			p[x] = findSet(p[x]);
		return p[x];
	}

	static void union(int x, int y) {
		p[y] = x;
	}
}