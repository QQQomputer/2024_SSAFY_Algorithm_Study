package BOJ1941_G3_소문난칠공주;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
class JH_한붓그리기고려안함 {
	static int N, ans;
	static int[] isLim;
	static int[] checked;
	// 상 우 하 좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static List<Integer> res = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// st = new StringTokenizer(br.readLine());
		// Integer.parseInt(st.nextToken());
		N = 5;

		isLim = new int[N];

		//S: 이다솜 Y: 임도연
		// 파 나누기(bit) - 0: 이다솜 1: 임도연
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			int n = 0;
			for (int j = 0; j < N; j++) {
				n |= (c[j] - 'S'==0?0:1) << j;
			}
			isLim[i]=n;
		}
		for (int i = 0; i < N; i++) {
		    String binary = Integer.toBinaryString(isLim[i]);
		    // 5자리에 맞추기 위해 앞에 0을 추가
		    while (binary.length() < 5) {
		        binary = "0" + binary;
		    }
		    System.out.println(binary);
		}
		
		checked=new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.println("@@@@@@@@");
				int bx = 1<<j;
				checked[i]|=bx;
				dfs(i,j,((isLim[i]&bx)==bx)?1:0,1,1<<(i*N+j));
			}
		}

		System.out.println(ans);
		
		for (int i = 0; i < N; i++) {
		    String binary = Integer.toBinaryString(checked[i]);
		    // 5자리에 맞추기 위해 앞에 0을 추가
		    while (binary.length() < 5) {
		        binary = "0" + binary;
		    }
		    System.out.println(binary);
		}
		for (int i = 0; i < res.size(); i++) {
		    String binary = Integer.toBinaryString(res.get(i));
		    // 5자리에 맞추기 위해 앞에 0을 추가
		    while (binary.length() < 31) {
		        binary = "0" + binary;
		    }
		    System.out.println(binary);
		}
	}
	//sum 임다연파 count
	static void dfs(int y, int x, int sum, int n, int bit) {
		if(sum>3)return;
		if(n==7) {
			for(int nn : res)
				if(nn==bit)return;
			res.add(bit);
			System.out.println("###"+ans);
			for (int i = 0; i < N; i++) {
			    String binary = Integer.toBinaryString(checked[i]);
			    // 5자리에 맞추기 위해 앞에 0을 추가
			    while (binary.length() < 5) {
			        binary = "0" + binary;
			    }
			    System.out.println(binary);
			}
			ans++;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int yy = y+dy[i];
			int xx = x+dx[i];
			if(check(yy,xx))continue;
			
			int by = checked[yy];
			int bx = 1<<xx;
			if((checked[yy]&bx)==bx)continue;
			checked[yy]|=bx;
			
			dfs(yy,xx,((isLim[yy]&bx)==bx)?sum+1:sum,n+1, (bit|(1<<(yy*N+xx))));
			checked[yy]=by;
		}
	}
	static boolean check(int y, int x) {
		return y<0||y>=N||x<0||x>=N;
	}
}