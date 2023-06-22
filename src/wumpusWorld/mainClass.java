package wumpusWorld;

import java.util.Arrays;

public class mainClass {

	public static void main(String[] args) throws InterruptedException {
		Newstate world = new Newstate();
		Climb climb = new Climb();
		int buttonFindGold; //���� ã�Ҵ��� üũ
		
		//�ʱ� ����
		world.Generate();
		for (int j = 5; j >= 0; j--) {
			for (int i = 0; i < 6; i++) {
				System.out.print("(" + i + ", " + j + ")" + world.getGrid(i, j) + " ");
			}
			System.out.println("\n");
		}
		System.out.println("=================");
		
		
		//�� ��ġ Percept �ν�
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
		
		
		//�� ã���� ���
		Start s = new Start();
		s.preStart();
		buttonFindGold = 1; //���� �� ã��
		
		
		//��ư �̺�Ʈ ó��
		if (buttonFindGold == 1) { //buttonFindGold = 1, ���� �� �� ã��, Ž�� ��� ����
			buttonFindGold = s.StartWorld();
		}
		else {
			//buttonFindGold = 0, �� ã��, (1,1)�� �ǵ��ư���
			climb.DoClimb();
		}
	}
}
