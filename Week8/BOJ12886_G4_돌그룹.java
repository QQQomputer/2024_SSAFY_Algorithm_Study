package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	bfs
	1000000000
*/
public class BOJ12886_G4_돌그룹 {
	static int N,ans;
	static Queue<Node> q;
//	static boolean [][][] checked=new boolean[1501][1501][1501]; 
	static Map<Long,Boolean> checked = new HashMap<>();
	static class Node {
		short a,b,c;
		long val;

		public Node(short a, short b, short c) {
			this.a = (short) Math.min(Math.min(a, b), c);
			this.c = (short) Math.max(Math.max(a, b), c);
			this.b = (short) (a + b + c - this.a - this.c);
			this.val = a<<32 | b<<16 | c;
		}
		boolean check() {
			return checked.get(val)!=null;
		}
		void checked() {
			checked.put(val, true);
		}
		boolean compare() {
			return (this.a==this.b)&&(this.b==this.c);	
		}
		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", c=" + c + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Node node = new Node(Short.parseShort(st.nextToken()), Short.parseShort(st.nextToken()), Short.parseShort(st.nextToken()));
		if((node.a+node.b+node.c)%3!=0) {
		}else if(node.compare()) {
			ans=1;
		}else {
			q = new ArrayDeque<>();
			q.offer(node);
			node.checked();
			while(!q.isEmpty()) {
				if(offerNode(q.poll())) {
					ans=1;
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
	static boolean offerNode(Node node) {
		short a = node.a, b = node.b, c = node.c;
		//a vs b
		if(a<b) {
			node = new Node((short)(a+a), (short)(b-a), c);
			if(!node.check()) {
				if(node.compare())return true;
				node.checked();
				q.offer(node);
			}
		}
		
		//b vs c
		if(b<c) {
			node = new Node(a, (short)(b+b), (short)(c-b));
			if(!node.check()) {
				if(node.compare())return true;
				node.checked();
				q.offer(node);
			}
		}
		
		//c vs a
		if(a<c) {
			node = new Node((short)(a+a), b, (short)(c-a));
			if(!node.check()) {
				if(node.compare())return true;
				node.checked();
				q.offer(node);
			}
		}
		return false;
	}
}
