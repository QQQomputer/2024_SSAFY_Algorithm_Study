package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	4자리 소수
	
	1000~9999 사이의 소수 => 1061개 존재
	
	1033 8179
	
	1033에서 각 자리수별 0~9까지 숫자 넣어서 소수 된다면 q에 넣고 bfs 식으로 돌리기
	
*/
public class BOJ1963_G4_소수경로 {
	static int N, ans;
	static boolean [] org=new boolean[10000];
	static boolean [] arr=new boolean[10000];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		eratos();	
		
		for (int i = 0; i < N; i++) {
			System.arraycopy(org, 1000, arr, 1000, 9000);
			ans=0;
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			bfs(start, end);		
			sb.append(ans==-1?"Impossible":ans).append("\n");
		}
		System.out.println(sb);
	}
	static void bfs(int start, int end) {
		if(start==end)return;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		arr[start]=true;
		int size = 1;
		while(!q.isEmpty()) {
			if(--size==0) {
				size = q.size();
				ans++;
			}
			int cur = q.poll();
			
			int [] dd = new int[4];
			//1의 자리(짝수 제외)
			dd[0] = cur%10;
			
			//10의 자리
			dd[1] = cur/10%10;
			
			//100의 자리
			dd[2] = cur/100%10;
			
			//1000의 자리
			dd[3] = cur/1000;
			
			int [] digits = Arrays.copyOf(dd,4);
			
			for (int d = 0; d < 4; d++) {
				int st = ((d%3==0)?1:0);
				int delta = ((d==0)?2:1);
				for (int i = st; i < 10; i+=delta) {
					digits[d]=i;
					int newNum = calc(digits[0], digits[1], digits[2], digits[3]);
					if(!arr[newNum]) {
						if(newNum==end)return;
						arr[newNum]=true;
						q.offer(newNum);
					}
				}
				digits[d]=dd[d];
			}			
		}
		ans=-1;
	}
	static int calc(int d1, int d2, int d3, int d4) {
		return d4*1000+d3*100+d2*10+d1;
	}
	static void eratos() {
		for (int i = 2; i < 10000; i++)
			if(!org[i])
				for (int j = i*2; j < 10000; j+=i) org[j]=true;
	}
}
