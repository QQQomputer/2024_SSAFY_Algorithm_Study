package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	
*/
public class BOJ11664_G5_선분과점 {
	static int N, ans;
	static int [][] pnt = new int[3][3]; // x,y,z
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for (int i = 0; i < 3; i++) {
			pnt[i][0]=Integer.parseInt(st.nextToken());
			pnt[i][1]=Integer.parseInt(st.nextToken());
			pnt[i][2]=Integer.parseInt(st.nextToken());
		}
		
		System.out.println(calc());		
	}
	static double calc() {
		int abX = pnt[1][0]-pnt[0][0];
		int abY = pnt[1][1]-pnt[0][1];
		int abZ = pnt[1][2]-pnt[0][2];

		int acX = pnt[2][0]-pnt[0][0];
		int acY = pnt[2][1]-pnt[0][1];
		int acZ = pnt[2][2]-pnt[0][2];
		
		double ab = abX*abX+abY*abY+abZ*abZ;
		if(ab==0)return acX*acX+acY*acY+acZ*acZ;
		double abac = Math.abs(abX*acX)+Math.abs(abY*acY)+Math.abs(abZ*acZ);
		System.out.println(0.0+abac/ab);
		System.out.println(abac+" "+ab);
		double t =  Math.max(0, Math.min(1, (double)(abac/ab)));
		
        double distance = Math.sqrt(Math.pow(pnt[2][0]-(pnt[0][0]+t*abX), 2) + Math.pow(pnt[2][1]-(pnt[0][1]+t*abY), 2) + Math.pow(pnt[2][2]-(pnt[0][2]+t*abZ), 2));
        return distance;
	}
}
