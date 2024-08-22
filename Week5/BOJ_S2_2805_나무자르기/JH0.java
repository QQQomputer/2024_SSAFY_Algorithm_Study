package BOJ_S2_2805_나무자르기;

import java.io.IOException;

// H < 1,000,000,000
public class JH0 {
	static int N,M,l,r;
	static int [] arr;
	public static void main(String[] args) throws IOException {		
		N = read();
		M = read();
		arr= new int [N];
		//int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = read();
			//if(max<arr[i])max=arr[i];
		}
		l = 1;	r = 1_000_000_000;//max;
		binarySearch();	
		System.out.println(l-1);
	}
	static void binarySearch() {
		while(l<=r) {
			int mid=(l+r)/2;
			long sum = 0;
			for (int i = N-1; i >= 0; i--) {
				int n= arr[i]-mid;
				if(n>0)sum+=n;
			}
			if(sum>M)		l=mid+1;
			else if(sum<M)	r=mid-1;
			else {			l=mid+1;	break;}
		}
	}
    public static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}