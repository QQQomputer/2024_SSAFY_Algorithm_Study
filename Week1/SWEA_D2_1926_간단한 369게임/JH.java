import java.util.*;
import java.io.*;

class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N,idx, ans, max, sum;
    static int [] arr;
    static String [] str = {"","-","--","---"};
	public static void main(String args[]) throws Exception {
		N = Integer.parseInt(br.readLine());
		
        for (int i = 1; i <= N; i++) {	
        	int num = i;
        	
        	int cnt = 0;
        	
        	while(num>0) {
        		if(num%10!=0 && num%10%3==0)
    				cnt++;
        		num/=10;
        	}
        	
        	// 1번 방법
        	// str[cnt]		//String [] str = {"","-","--","---"} 배열 선언 필수
        	sb.append(cnt==0?i:str[cnt]).append(" ");
        	
        	// 2번 방법
        	// String.format("%"+cnt+"s", "").replaceAll(" ", "-")  
        	//sb.append(cnt==0?i:String.format("%"+cnt+"s", "").replaceAll(" ", "-")).append(" ");
        	
        	// 3번 방법 				=> jdk 11버전 이상
        	// "-".repeat(cnt)
        	//sb.append(cnt==0?i: "-".repeat(cnt)).append(" ");
        }
        sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
