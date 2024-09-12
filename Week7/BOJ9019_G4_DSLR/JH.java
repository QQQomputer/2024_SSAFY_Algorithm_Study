package BOJ9019_G4_DSLR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	2080ms
*/
class JH {
	static int N, ans,res;
	static boolean [] checked;
	static char [] arr = {'D','S','L','R'};
	static class Node{
		Node pre;
		int val;
		char c;
		public Node(Node pre, int val, char c) {
			super();
			this.pre = pre;
			this.val = val;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringBuilder sb = new StringBuilder();
			checked= new boolean[10000];
			st = new StringTokenizer(br.readLine());
			Node last = bfs(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			while(last.pre != null) {
				sb.insert(0, last.c);
				last = last.pre;
			}
			System.out.println(sb);
		}
	}
	static Node bfs(int start, int result) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(null, start,'F'));
		checked[start]=true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			int val = node.val;
			for(char i : arr) {
				switch (i) {
					
					case 'D': {
						res = DD(val);
						break;
					}
					case 'S': {
						res = SS(val);
						break;
					}
					case 'L': {
						
						res = LL(val);
						break;
					}
					case 'R': {
						res = RR(val);
						break;
					}
				}
				if(res==result)return new Node(node, res, i);
				if(checked[res])continue;
				checked[res]=true;
				q.offer(new Node(node, res, i));
			}
		}
		return null;
	}
	static int DD(int n) {
		return 2*n%10000;
	}
	static int SS(int n) {
		return (n+9999)%10000;
	}
	static int LL(int n) {
		int num = 1000;//nums[digit];
		return n%num*10+n/num;
	}
	static int RR(int n) {
		int num = 1000;
		return num*(n%10)+n/10;
	}
}
