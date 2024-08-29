package BOJ1339_G4_단어수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*	13:30
 	14:45
 가장 단어길이가 긴 순으로 알파벳에 9,8,7 순서로 부여
 단어 길이 최대 8
 
 길이 같은데 알파벳 서로 다를 경우
 	1. 같은 길이열에 더 많은 경우 선택
 	
#
파고파고 들어가는 로직  
=> 미리 계산 후 sort방식으로 변경


 */
class JH {
	static int N,sum,ans;
	static int [] arr;
	static boolean [] c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[26];
		for (int i = 0; i < N; i++) {
			char [] c = br.readLine().toCharArray();
			int len = c.length-1;
			for (int j = 0; j < c.length; j++,len--)
				arr[c[j]-'A']+=Math.pow(10, len);			
		}
		sum=0;
		for (int i = 0; i < 26; i++) if(arr[i]>0)sum++;
		
		Arrays.sort(arr);
		
		int max_num = 9;
		for (int i = 25; i >= 25-(sum-1); i--)
			ans+=arr[i]*max_num--;

		System.out.println(ans);
	}
}