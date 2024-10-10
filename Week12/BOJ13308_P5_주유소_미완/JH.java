package BOJ13308_P5_주유소;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	#start
		09:10
	#end
	
	#concepion
		- 문제 파악
		N개의 도시
		서로 다른 두 도시를 양방향으로 직접 연결하는 M개의 도로
		기름통의 크기는 무제한
		1km마다 1리터의 기름
		각 도시에는 단 하나의 주유소
		도시마다 주유소의 리터당 가격은 다를 수 
		
		도시의 수N(2 ≤ N ≤ 2,500)과 도로의 수 M(1 ≤ M ≤ 4,000)
		각 도시 주유소의 리터당 가격이 L <= 2500 자연수
		도시번호, 도시번호, 도로 길이    (최대 하나만 존재)
	
		-구상
		도로 가격 정보 담기		
		이동 시 거리 정보 담기	2500*2500*4  배열로 담아도 되지만 불필요한 작업이 일어날 수 있어 adj arraylist 사용
		N도시 까지 오기 위한 최소 금액 갱신
		
		-방식
		bfs, 최소 스패닝, 다익스트라
		도로 길이 정보 뿐만아니라 주유 정보도 들어가야 함으로 bfs
		LIS?
		크루스칼
		
		1. 가격 낮은 도시 순 고르기 (출발지 가격 까지)
		2. 출발지 잇기, 이어 나가면서 다른 주유소 가격 체크
		3. 선택 도시에서 bfs
		
		-전략
		가장 싼 주유소에서
		
		-완전탐색
		
		
		bfs 돌려서 그냥 갈 때 도로 길이
	
	#review
	
	
	
*/
class JH {
	static int N,M,ans;
	static int [] arr;
	static List<Node> [] adj;
	static int [][] board;
	static boolean [][] checked;
	static class Node implements Comparable<Node>{
		int b,w;

		public Node(int b, int w) {
			super();
			this.b = b;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w-o.w;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//st = new StringTokenizer(br.readLine());
		//Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		for(int i = 1 ;i<=N;i++)
			adj[i]=new ArrayList<>();
		
		arr=new int[N+1];
		Queue<Node> qq = new PriorityQueue<>();
		board=new int[N][N];
		checked=new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
			qq.offer(new Node(i, arr[i]));
		}
		        
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[a].add(new Node(b, w));
			adj[b].add(new Node(a, w));
		}
		
		
		
		
		
		System.out.println(ans);
	}
}