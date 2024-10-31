import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N,idx;
	static int [][] arr;
	static int [] lis;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		lis = new int[N+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr,(o1, o2) -> o1[0]-o2[0]);
		
		for (int i = 0; i < N; i++) {
			int cur = arr[i][1];
			if(lis[idx]<cur) {
				lis[++idx]=cur;
			}else if(lis[idx]>cur) {
				//작은 수 보다 idx 높아야하는 idx 최솟값 
				lis[bs(1,idx,arr[i][1])]=cur;
			}
		}
		
		System.out.println(N-idx);
	}
	static int bs(int l, int r, int res) {
		while(l<r) {
			int mid = (l+r)/2;
			
			if(lis[mid]<res) {
				l = mid+1;
			}else {
				r = mid;
			}
		}
		return l;
	}
}