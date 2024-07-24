import java.util.*;
import java.io.*;

class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N,idx,ans, max, sum;
    static int [] arr;
	public static void main(String args[]) throws Exception
	{
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++)
		{
			max=0;
			idx=0;
			Integer.parseInt(br.readLine());
            
            
            String [] str = br.readLine().split(" ");
            arr = new int[str.length+1];
            
            for (int i = 0; i < str.length; i++)
				arr[Integer.parseInt(str[i])]++;

            for (int i = 0; i < arr.length; i++)
            	if(max<=arr[i]) {
            		max=arr[i];
            		idx=i;
            	}            
            System.out.println(max + ", "+idx);
			sb.append("#"+t+" "+idx+"\n"); 
		}
		System.out.println(sb.toString());
	}
}
