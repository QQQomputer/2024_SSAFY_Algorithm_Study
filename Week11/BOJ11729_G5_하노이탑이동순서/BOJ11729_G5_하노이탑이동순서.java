package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
	
*/
public class BOJ11729_G5_하노이탑이동순서 {
	static int N, ans;
	static StringBuilder sb = new StringBuilder();
	static Disk[] disks;
	static class Disk{
		int cur;
		public Disk() {
			cur=1;
		}
		void move(int target) {	
			sb.append(cur).append(" ").append(target).append("\n");
			cur=target;
			ans++;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
		N = Integer.parseInt(br.readLine());
		disks=new Disk[N];
		for(int i = 0;i<N;i++)disks[i]=new Disk();
		move(N-1, 3);
		System.out.println(ans);
		System.out.println(sb);
	}
	static void move(int num, int target) {
		if(num<0)return;
		
		int other=6-disks[num].cur-target;
		
		//내 위에 있다면 먼저 치우기
		move(num-1, other);

		//disk를 목적지로
		disks[num].move(target);
		
		//나보다 낮은 disk 위로 쌓기
		move(num-1, target);
	}
}
