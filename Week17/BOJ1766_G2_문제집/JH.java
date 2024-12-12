package BOJ1766_G2_문제집;
import java.io.*;
import java.util.*;
/*
	#start
	
	#end
	
	#concepion
	
	
	#review
	
	
	
*/
public class JH {
    static int N,M;
    static List<Integer> [] arr;
    static int [] in;
    public static void main(String[] args) throws IOException {
        // 코드를 작성해주세요
        StringBuilder sb = new StringBuilder();
        N = readInt();
        M = readInt();
        arr = new ArrayList[N+1];
        in = new int[N+1];
        for(int i = 1 ;i<=N;i++) arr[i]= new ArrayList<>();
        
        for(int i = 0 ;i<M;i++){
            int A = readInt();
            int B = readInt();
            arr[A].add(B);
            in[B]++;
        }
        PriorityQueue<Integer> pq  = new PriorityQueue<>();
        for(int i = 1 ;i<=N;i++)
            if(in[i]==0)pq.offer(i);

        while(!pq.isEmpty()){
            int n = pq.poll();
            sb.append(n).append(" ");
            for(int i = 0 ;i<arr[n].size();i++){
                int idx = arr[n].get(i);
                if(--in[idx]==0)pq.offer(idx);
            }
        }
        System.out.println(sb);
    }
    static int readInt() throws IOException{
        int result = 0;
        int read = System.in.read();
        
        while(read <'0' || read>'9')read = System.in.read();
        
        while(read>='0'&&read<='9'){
            result = result * 10 +read-'0';
            read = System.in.read();
        }
        return result;
    }
}