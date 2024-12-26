package PGS42892_LV3_길찾기게임;
import java.io.*;
import java.util.*;
/*
	#start
	
	#end
	
	#concepion
	tree 만들고 정답 배열 만드는 방식
	
	1. 배열을 노드배열로 만들기
	2. 배열 y좌표, x좌료 순으로 정렬
	3. 높이 바뀔 때마다
	
	#review
	
	
	
*/
class JH {
	static int[][] nodeinfo = {{5, 4}, {3, 3}, {2, 2}, {1, 1}};
	static class Node{
		Node l,r;
		int n,h,w,max,min;
		public Node(int n, int h, int w, int max, int min) {
			this.n = n;
			this.h = h;
			this.w = w;
			this.max = max;
			this.min = min;
		}		
	}
	static Node root;
	static int idx;
	static int[][] ans;
	public static void main(String[] args) {
		ans = new int[2][nodeinfo.length]; // 전위 순회, 후위 순회 결과
		
		// 노드 번호까지 담을 2차원 배열 생성
		int arr [][] = new int[nodeinfo.length][3];
		
		for (int i = 0; i < arr.length; i++) {
			System.arraycopy(nodeinfo[i], 0, arr[i], 0, 2);
			arr[i][2]=i+1;
		}
		
		Arrays.sort(arr,(o1,o2)->o2[1]-o1[1] ==0 ? o1[0]-o2[0] :o2[1]-o1[1]);
		
		// 루트노드
		root = new Node(arr[0][2], arr[0][1], arr[0][0], 100_001, -1);
		Node cur = root;
		
		// 이전 노드들
		Queue<Node> q = new ArrayDeque<>();
		
		// 트리 노드 삽입
		for (int i = 1; i < arr.length; i++) {
			int h = arr[i][1];
			int w = arr[i][0];
			int n = arr[i][2];
			
			// 현재 노드의 left 노드일 경우
			if (cur.l==null && cur.w > w && cur.min < w) {
				cur.l = new Node(n, h, w, cur.w, cur.min);
				q.offer(cur.l);
			}
			// 현재 노드의 right 노드일 경우
			else if (cur.r==null && cur.w < w && cur.max > w) {
				cur.r = new Node(n, h, w, cur.max, cur.w);
				q.offer(cur.r);
				cur = q.poll();
			}
			// 현재 노드가 부모가 아닐 경우
			else {
				cur = q.poll();
				i--;
			}
		}
		
		//전위 순회
		preOrder(root);
		
		//후위 순회
		idx=0;
		postOrder(root);

		System.out.println(Arrays.deepToString(ans));
	}
	
	static void preOrder(Node n) {
		if(n==null)return;
		ans[0][idx++]=n.n;
		preOrder(n.l);
		preOrder(n.r);
	}
	
	static void postOrder(Node n) {
		if(n==null)return;
		postOrder(n.l);
		postOrder(n.r);
		ans[1][idx++]=n.n;
	}
}}