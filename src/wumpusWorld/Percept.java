package wumpusWorld;

import java.util.Arrays;

enum PERCEPT_num {STENCH, BREEZE, GLITTER, BUMP, SCREAM}

public class Percept {
	public int i;
	public int j;
	public Newstate world = new Newstate();
	Agent agent = new Agent();
	String nowState;
	
	//���� ������ ������ �ش�.
	//1. Stench - i+-1 j+-1�� WUMPUS�� ������ STENCH -> ���߿� ���
	//2. BREEZE - i+-1 j+-1�� PITCH�� ������ BREEZE -> ���߿� ���
	//3. GLITTER - ���� ���ڰ� GOLD 
	//4. BUMP - ���� ���ڰ� WALL -> ���� ���
	//5. SCREAM
	
	//[Stench, Breeze, Glitter, Bump, Scream]
	public int[] sensor = new int[5];

	public int[] check(int i, int j) {
		this.i = i;
		this.j = j;
		
		nowState = world.getGrid(i,j);
		Arrays.fill(sensor,0);
		
		//scream
		if(Agent.wumpusDie == 1) {
			sensor[PERCEPT_num.SCREAM.ordinal()] = 1;
		}
		
		if(nowState == "WALL") { //���� ���� ��
			sensor[PERCEPT_num.BUMP.ordinal()] = 1;
		}
		else {
			//gold
			if (nowState == "GOLD") sensor[PERCEPT_num.GLITTER.ordinal()] = 1;
			int check = 0;
			//stench
			check = aroundCheck("WUMPUS");
			if(nowState == "WUMPUS") check = 1;
			if(check == 1) sensor[PERCEPT_num.STENCH.ordinal()] = 1;
			//breeze
			check = aroundCheck("PITCH");
			if(nowState == "PITCH") check = 1;
			if(check == 1) sensor[PERCEPT_num.BREEZE.ordinal()] = 1;
		}
	
		return sensor;
	}
	
	
	//���� ���ڿ��� �����¿� ���캻��
	public int aroundCheck(String str) {
		String check;
		int return_num = 0;
		//(i + 1, j)
		check = world.getGrid(i+1,j);
		if(check == str) return_num = 1;
		//(i - 1, j)
		check = world.getGrid(i-1,j);
		if(check == str) return_num = 1;
		//(i, j + 1)
		check = world.getGrid(i,j+1);
		if(check == str) return_num = 1;
		//(i, j - 1)
		check = world.getGrid(i,j-1);
		if(check == str) return_num = 1;
		
		return return_num;
	}
}
