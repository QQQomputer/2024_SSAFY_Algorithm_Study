package BOJ14888_S1_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
14:50
15:24

recursion으로 연산자 순열 구해서 완탐

나눗셈 devide zero 괜찮음

최대, 최소 나오는 조건...

최대 양
최소 음

가지치기 조건을 생각해봤으나 생각나지 않아 완탐 실행

#
100ms

 */
class JH {
	static int N,opCnt,max,min,ans;
	static int [] arr;
	static int [] op = new int[4];
	static List<int[]> list = new ArrayList<>();
	static int [] used;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		//정수
		for (int i = 0; i < N; i++)
			arr[i]=Integer.parseInt(st.nextToken());
		
		opCnt = N-1;
		used = new int[opCnt];
		st = new StringTokenizer(br.readLine());
		//연산자	+ - * /
		for (int i = 0; i < 4; i++)
			op[i]=Integer.parseInt(st.nextToken());
		
		recursion(0);
		
		for (int [] ar : list) {
			int cur = arr[0];
			for (int i = 0; i < opCnt; i++) {
				if(ar[i]==0) {
					cur+=arr[i+1];
				}else if(ar[i]==1) {
					cur-=arr[i+1];
				}else if(ar[i]==2) {
					cur*=arr[i+1];
				}else if(ar[i]==3) {
					cur/=arr[i+1];
				}
			}
			
			if(max<cur) 	max = cur;
			if(min>cur) 	min = cur;
		}
		
		System.out.println(max);
		System.out.println(min);
	}
	static void recursion(int n) {
		if(n==opCnt) {
			list.add(Arrays.copyOf(used, opCnt));
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(op[i]>0) {
				op[i]--;
				used[n]=i;
				recursion(n+1);
				op[i]++;
			}
		}
	}
}