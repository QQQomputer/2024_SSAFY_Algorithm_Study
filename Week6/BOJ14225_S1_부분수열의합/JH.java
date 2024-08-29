package BOJ14225_S1_부분수열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/*
	12:55
	
	subset돌려서 합을 hashset에 넣어 중복 제거
	=> int[]로 만들어서 정렬 후 정답 도출
	int 배열과 idx를 맞추기 위해 
	
	13:15 정답
	
	
*/
class JH {
	static int N,ans;
	static int [] arr;
	static Integer [] cases;
	static Set<Integer> set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		set = new HashSet<>();
		set.add(0);
		ans=0;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i]=Integer.parseInt(st.nextToken());
		
		subset(0,0);
		
		cases = set.toArray(new Integer[0]);
		Arrays.sort(cases);
		
		int i;
		for (i = 1; i < cases.length; i++)
			if(cases[i]!=i) break;
		ans=i;
		System.out.println(ans);
	}
	static void subset(int n, int s) {
		if(n==N) {
			set.add(s);
			return;
		}
		subset(n+1,s+arr[n]);
		subset(n+1,s);
	}
}