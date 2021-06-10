package wumpusWorld;

import java.util.Arrays;

public class mainClass {

	public static void main(String[] args) throws InterruptedException {
		Newstate world = new Newstate();
		Climb climb = new Climb();
		int buttonFindGold; //금을 찾았는지 체크
		
		//초기 생성
		world.Generate();
		for (int j = 5; j >= 0; j--) {
			for (int i = 0; i < 6; i++) {
				System.out.print("(" + i + ", " + j + ")" + world.getGrid(i, j) + " ");
			}
			System.out.println("\n");
		}
		System.out.println("=================");
		
		
		//각 위치 Percept 인식
		Percept p = new Percept();
		int[] check;
		for (int j = 5; j >= 0; j--) {
			for (int i = 0; i < 6; i++) {
				check = p.check(i,j);
				System.out.print(Arrays.toString(check)+" ");
			}
			System.out.println("\n");
		}
		System.out.println("=================");
		
		
		//금 찾으러 출발
		Start s = new Start();
		s.preStart();
		buttonFindGold = 1; //아직 못 찾음
		
		
		//버튼 이벤트 처리
		if (buttonFindGold == 1) { //buttonFindGold = 1, 아직 금 못 찾음, 탐험 계속 진행
			buttonFindGold = s.StartWorld();
		}
		else {
			//buttonFindGold = 0, 금 찾음, (1,1)로 되돌아가기
			climb.DoClimb();
		}
	}
}
