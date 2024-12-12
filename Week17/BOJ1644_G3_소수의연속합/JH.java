package BOJ1644_G3_소수의연속합;
import java.io.*;
import java.util.*;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
public class JH {
    static int N,ans, cnt;
    static long sum;
    static int [] arr;
    static boolean [] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        checked = new boolean[N+1];
        arr = new int[N+1];
        eratos();
        
        int count = -1;
        int s = 0;
        int e = 0;
        
        while(true){
            if(e>cnt)break;
            if(N<=sum){
                if(N==sum)ans++;
                sum-=arr[s++];
            }else{
                sum+=arr[e++];
            }
        }
        
        System.out.println(ans);
    }
    static void eratos(){
        for(int i = 2;i<=N;i++){
            if(!checked[i]){
                arr[cnt++]=i;
                for(int j = i+i;j<=N;j+=i)checked[j]=true;
            }
        }
    }
}