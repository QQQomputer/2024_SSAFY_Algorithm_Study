import java.util.*;
import java.io.*;

class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N,idx;
    static long ans, max, sum;
    static long [] arr;
	public static void main(String args[]) throws Exception
	{
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++)
		{
			N = Integer.parseInt(br.readLine());
            arr = new long[N];
            ans = 0;
            max = 0;
            idx = 0;
            sum = 0;
            
            st = new StringTokenizer(br.readLine()); 
            
            for(int n = 0; n < N; n++){
                int num = Integer.parseInt(st.nextToken());
                if(max<num){
                    max=num;
                    idx=n;
                }
                sum+=num;
                
                //누적합
                arr[n]=sum;
            }
            
            //증가 없는 경우
            if(idx == 0) {
            	sb.append("#"+t+" "+ans+"\n"); 
            	continue;
            }
            long cur = arr[idx-1];

            ans += (max*idx)-cur;
            
            int start = idx+1;
            
			while(start<N){				
                max = 0;              
                for(int n = start; n < N; n++){
                	int price = (int)(arr[n]-arr[n-1]);
                    if(max<price){
                        max=price;
                        idx=n;
                    }                    
                }
                
                if(idx>start)
                	ans += (max*(idx-start))-(arr[idx-1]-arr[start-1]);
    
                start  = idx+1;
            }
			sb.append("#"+t+" "+ans+"\n"); 
		}
		System.out.println(sb.toString());
	}
}
