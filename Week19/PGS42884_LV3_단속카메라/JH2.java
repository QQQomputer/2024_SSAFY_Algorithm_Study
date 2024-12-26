package PGS42884_LV3_단속카메라;
import java.util.Arrays;
/*
	#start
	
	#end
	
	#concepion	
	진입 기준 정렬 방식
	
	#review
	
	
	
*/
class JH2 {
	static int[][] routes = { { -5, 3 }, { -3, 0 }, { 0, 4 }, { 2, 5 } };	
	public static void main(String[] args) {
        int ans = 1;
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0] == 0? o1[1] - o2[1] :o1[0] - o2[0]);

        int point = routes[0][1];
		
		for (int i = 1; i < routes.length; i++) {
			if(routes[i][0] <=point)
				point=Math.min(point, routes[i][1]);
			else {
				point = routes[i][1];
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}