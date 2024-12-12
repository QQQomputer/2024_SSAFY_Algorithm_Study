package BOJ21609_G2_상어중학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	#start
	
	#end
	
	#concepion
	N×N인 격자
	초기에 격자의 모든 칸에는 블록 하나씩
	블록은 검은색 블록, 무지개 블록, 일반 블록
	일반 블록은 M가지 색상
	색은 M이하의 자연수로 표현
	검은색 블록은 -1, 무지개 블록은 0으로 표현
	(i, j)는 격자의 i번 행, j번 열을 의미
	|r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸 (r1, c1)과 (r2, c2)를 인접한 칸 => 맨하튼거리
	
	[그룹] (인접해야 함)
	일반 블록 적어도 하나 이상, but 일반 블록의 색은 모두 같아야 함
	검은색 블록 포함 x
	무지개 블록은 얼마나 들어있든 상관없음
	블록의 개수 >= 2
	기준 블록 => 일반 블록 중 행의 번호가 가장 작은 블록 > 열의 번호가 가장 작은 블록
	
	[오토 플레이 기능]
	블록 그룹이 존재하는 동안 계속해서 반복
	큰 블록 그룹 > 포함된 무지개 블록의 수가 가장 많은 블록 그룹 > 기준 블록의 행이 가장 큰 것을 > 열이 가장 큰 것
	찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득
	격자에 중력이 작용
	격자가 90도 반시계 방향으로 회전
	다시 격자에 중력이 작용
	
	[입력]
	첫째 줄에 격자 한 변의 크기 N, 색상의 개수 M (1 ≤ N ≤ 20, 1 ≤ M ≤ 5)
	
	
	입력 board > ```그룹 찾기 > 칸수 점수``` > 그룹 제거 > 중력,회전,중력 > ```그룹 찾기 > 칸수 점수``` ...
	획득 점수 출력
	
	일반 블럭 한개 이상 포함 된 블럭 2개 이상 그룹
	
	최대 400칸 2칸씩 사라지면 200 번 반복
	
	회전 어떻게?
	
	
	중력은 어떻게?
	아래서 부터 올라가면서 아래로 떨어뜨리기  19번 떨어뜨리기 20열동안 380 x 200 / 76,000번
	
	#review
	틀린 부분
	1. 무지개 블럭 visited 처리
	2. 기준 블럭 "일반 블럭"만
	
*/
class JH {
	static int N, M, ans, N1, cnt;
	static boolean[][] vst;
	static int[][] board;
	static int cur = 0;
	static int cy, cx;
	
	static int [][] group;
	static int [][] selectedGroup;
	
	// 큰 블록 그룹 > 포함된 무지개 블록의 수가 가장 많은 블록 그룹 > 기준 블록의 행이 가장 큰 것을 > 열이 가장 큰 것
	static int[] data;
	static int[] selected;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		N1 = N - 1;

		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		// 그룹이 없을 때 까지
		while (cnt >= 0) {
			ans += cnt*cnt;
			vst = new boolean[N][N];
			
			// [오토 플레이]
			// 그룹 찾기
			find();
			
			// 점수 카운트
			cnt = selected[0];
			
			// 그룹 삭제
			clean();

			// 정렬 (중력,회전,중력)
			gravity();
			
			cur = (cur + 1) % 4;
			
			gravity();
			
		}

		System.out.println(ans);
	}

	// 중력
	static void gravity() {
		for (int x = 0; x < N; x++) {
			int idx = N1;// 아래로 떨어질 위치
			for (int y = N1; y >= 0; y--) {
				getPos(y, x);
				if (board[cy][cx] == -1)
					idx = y - 1;
				else if (board[cy][cx] == -2)
					;
				else {
					int color = board[cy][cx];
					board[cy][cx] = -2;
					getPos(idx, x);
					board[cy][cx] = color;
					idx--;
				}
			}
		}
	}

	static void clean() {
		if(selectedGroup==null)return;
		for (int i = 0; i < selected[0]; i++) {
			int sy = selectedGroup[i][0];
			int sx = selectedGroup[i][1];
			board[sy][sx]=-2;
		}
	}
	
	// 큰 블록 그룹 > 포함된 무지개 블록의 수가 가장 많은 블록 그룹 > 기준 블록의 행이 가장 큰 것을 > 열이 가장 큰 것
	static void find() {
		selected = new int[4];
		selected[0]=-1;
		selectedGroup = null;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				data = new int[4];
				
				bfs(y, x);
				
				if(data[0]>1) 
					compare();
			}
		}
	}
	
	static void compare() {
		for (int i = 0; i < 4; i++) {
			if(selected[i]<data[i]) {
				selected = data;
				selectedGroup = group;
				return;
			}else if(selected[i]>data[i]) 
				return;
		}
	}

	static void bfs(int y, int x) {
		getPos(y, x);
		if (vst[cy][cx] || board[cy][cx] < 1) return;
		
		data[2] = y; data[3] = x;
		group = new int[N * N][2];
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { y, x });
		vst[cy][cx] = true;
		int std = board[cy][cx];
		
		group[data[0]][0]=cy;
		group[data[0]][1]=cx;
		data[0]++;
		
		boolean [][] v = new boolean[N][N];
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int iy = info[0];
			int ix = info[1];
			for (int i = 0; i < 4; i++) {
				int yy = iy + dy[i];
				int xx = ix + dx[i];
				getPos(yy, xx);
				if (check(cy, cx) || vst[cy][cx] || v[cy][cx] || board[cy][cx] < 0) continue;
				
				if(board[cy][cx] != 0) {
					//일반 블록의 색은 모두 같아야 함
					if (board[cy][cx] != std) continue;
					else vst[cy][cx] = true;
				}
				else
					v[cy][cx] = true;
				
				
				group[data[0]][0]=cy;
				group[data[0]][1]=cx;
				
				//블록 그룹 개수
				data[0]++;
				//포함된 무지개 블록의 수
				if(board[cy][cx]==0) 
					data[1]++;
				else {
					//기준 블록의 행
					if(data[2]>yy) data[2]=yy;
					
					//기준 블록의 열
					if(data[3]>xx) data[3]=xx;
				}
				
				q.offer(new int[] { yy, xx });
			}
		}
	}
	
	static boolean check(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}

	// 방향에 따른 좌표 (원하는 좌표 -> 실제 좌표)
	static void getPos(int y, int x) {
		switch (cur) {
		case 0:
			cy = y;
			cx = x;
			break;
		case 1:
			cy = x;
			cx = N1 - y;
			break;
		case 2:
			cy = N1 - y;
			cx = N1 - x;
			break;
		case 3:
			cy = N1 - x;
			cx = y;
			break;
		}
	}
	
	// 방향에 따른 좌표 "반대로" (원하는 좌표 -> 실제 좌표)
	static void getPosReverse(int y, int x) {
		switch (cur) {
		case 0:
			cy = y;
			cx = x;
			break;
		case 1:
			cy = N1 - x;
			cx = y;
			break;
		case 2:
			cy = N1 - y;
			cx = N1 - x;
			break;
		case 3:
			cy = x;
			cx = N1 - y;
			break;
		}
	}
	
	// 확인용
	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				getPos(i,j);
				System.out.print((board[cy][cx]==-2?"*":board[cy][cx])+" ");
			}
			System.out.println();
		}
		System.out.println(cnt);
	}
}


/*



'''
- 먼저 확인 해봐야 할 것
    1. size가 동일한 블록 그룹이 여러 개가 나올 수 있다
    2. 기존 블록은 행,열이 가장 작은 블록으로
       동일한 size의 여러 블록 그룹에서는, 무지개 블록,기존 블록의 행,열이 큰 순으로 선택

- 확인 했는데도 틀린다면 아래 예시를 참고

4 2
1 2 0 1
0 -1 -1 -1
1 0 -1 1
2 0 0 -1
ans:40
>>> visited를 사용한다면, 매번 0의 visited만 초기화 시켜줘야 함
    이미 하고 있었다면, 0의 위치가 매번 달라지므로 다시 탐색을 했는지 확인해볼것

5 2
2 -1 -1 -1 0
-1 0 -1 1 2
-1 0 -1 0 -1
2 1 0 -1 -1
0 -1 1 2 1
ans:37
>>> 출력값이 33으로 나온다면, 무지개 블럭을 기준으로 삼지 않았는지 확인해볼것
'''



*/