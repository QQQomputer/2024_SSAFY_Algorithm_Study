package SWEA_D2_1966_숫자를정렬하자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
//15m
public class JH {
	static int N;
	static int [][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new PriorityQueue<>();			
			for (int i = 0; i < N ; i++)
				q.offer(Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++)
				sb.append(q.poll()).append(" ");
			sb.setLength(sb.length()-1);	
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}