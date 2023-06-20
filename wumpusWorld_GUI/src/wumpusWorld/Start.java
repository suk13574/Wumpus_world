package wumpusWorld;

import java.util.Arrays;
import java.util.Random;

class Agent{
	static String direction;
	static int arrowCount = 3;
	static int x;
	static int y;
	static int[][] visit = new int[6][6]; //�湮�ߴ��� üũ
	static String[][] IKnow = new String[6][6]; //���� �˰��ִ� ���� ����
	static int wumpusDie; //wumpus �׾����� Ȯ��
	static int randomTurn; //���� random turn�ߴ���
	
	//����Ŭ üũ ����
	static int cyclenum;
	
	public void initialAgent() {
		direction = "EAST";
		x = 1;
		y = 1;
		
		//�湮���� �ʱ�ȭ
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				visit[i][j] = 0;
			}
		}
		//����Ŭ ü�� ���� �ʱ�ȭ
		cyclenum = 0;
	}
	
	//ó���� �湮�� ���� ����, ���� ����
	public void initialVisit() {
		for(int i = 0; i < 6; i ++) {
			for(int j=0;j<6;j++) {
				visit[i][j] = 0;
				IKnow[i][j] = "NULL";
			}
		}
	}
	
	public String getDirection() {
		return direction;
	}
	
	public int getArrow() {
		return arrowCount;
	}
	
	//x y �� ��ȯ
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	
	public void writeXY(int[] arr) {
		x = arr[0];
		y = arr[1];
	}

	public int getVisit(int i, int j) {
		return visit[i][j];
	}
	public String getIKnow(int i, int j) {
		return IKnow[i][j];
	}
	
	//���� �Լ�(write)
	public void writeVisit() {
		visit[x][y] = 1;
	}
	public void writeIKnow(String s) {
		IKnow[x][y] = s;
	}
	public void writeDirection(String s) {
		direction = s;
	}
	public int shootArrow() {
		if(arrowCount > 0) {
			arrowCount = arrowCount - 1;
			return 1;
		}
		else return 0;
	}
}



public class Start {
	Newstate world = new Newstate(); //grid ��������
	Percept p = new Percept(); //percept �޾ƿ��� ���� ��ü
	Agent agent = new Agent(); //agent ��������� ��ü
	Climb climb = new Climb(); //�ǵ��ư���
	static int buttoncheck; //��ư ������ 1, �� ������ 0
	
	int x;
	int y;
	
	int cycle = 1;
	
	//���� �� �غ�
	public void preStart() {
		agent.initialAgent(); //��ġ, ���� �ʱⰪ����
		agent.initialVisit(); //���� �湮�� �� ����
		Agent.randomTurn = 0; //�������� 0
	}
	
	
	//����
	public int StartWorld() throws InterruptedException {
		//�� �޾ƿ�
		x = agent.getx();
		y = agent.gety();
		

		//���� ����
		Swing swing = new Swing();
		swing.frame.setVisible(true);
			
		//1. ���� ���� �ҷ��´�
		String nowState = world.getGrid(x, y);
			
		//2. �湮üũ�� ���� ���� ���� ���� �߰�
		agent.writeVisit();
		if(Agent.IKnow[x][y] == "NULL") agent.writeIKnow(nowState);
		System.out.print("\n(" + x + ", " + y + ")�湮/ ");
			
		//3. percept ����
		int[] check = p.check(x, y);
		System.out.print(Arrays.toString(check)+"/ ");
			
		//4. WUMPUS�� PITCH ������ ����
		if(nowState == "WUMPUS") {
			System.out.print(" WUMPUS ������. DIE");
			Die();
			return 1;
		}
		if(nowState == "PITCH") {
			System.out.print(" PITCH �߰�. DIE");
			Die();
			return 1;
		}
			
		//4. �� ��Ȳ���� reasoning
		String direction = agent.getDirection();
			
		//4-1. ���̸� BUMP! ������ ���ư��� turn
		if(check[PERCEPT_num.BUMP.ordinal()] == 1) {
			System.out.print(" �� �߰�. �� ���ڷ� ���ư���.");
			//������ ���ư���
			int[] backXY;
			backXY = Back(direction);
			//x = backXY[0];
			//y = backXY[1];
			agent.writeXY(backXY);
			//Turn -> �ǵ��ư��� �Ұ���
			return 1;
		}
		//4-2. gold �߰��ϸ� ����
		if(Newstate.grid[x][y] == "GOLD") {
			System.out.print(" GOLD �߰�. Grab\n");
			Newstate.grid[x][y] = "NOGOLD";
			return 0;
		}
		//4-3. ���� ���� Ȯ�����ֱ�
		int[] nextXY = Next(direction);
		int nextX = nextXY[0];
		int nextY = nextXY[1];
		//���� ���� ���� Ȯ�� - NULL�̸� �𸣴� ��
		String nextState = agent.getIKnow(nextX, nextY);
		System.out.print(" Next block: "+nextState);
			
		//���� ���� ���� �湮�ϸ� ���� Ȯ���� ���� ����
		if(Agent.visit[nextX][nextY] == 1 && Agent.randomTurn == 0) {
			Random r = new Random();
			if(r.nextInt(100)<50) {
				direction = Turn();
				agent.writeDirection(direction);
				System.out.print(", �������� �湮���� TURN, ����: "+direction);
				nextX = nextXY[0];
				nextY = nextXY[1];
				//���� ���� ���� Ȯ�� - NULL�̸� �𸣴� ��
				nextState = agent.getIKnow(nextX, nextY);
				System.out.print(" ���� ����");
				Agent.randomTurn = 1;
				return 1;
			}
			else {
				System.out.print(" ����: "+direction);
			}
		}
		Agent.randomTurn = 0;
		
		String nextDirection; //Turn�ϸ� ���� ���� ����
		if(nextState == "WALL") { //���̸� turn
			System.out.print(" ������ ���� �ֱ���. ACTION: TURN->");
			nextDirection = Turn();
			agent.writeDirection(nextDirection); //���⾲��
			System.out.print(" ����: "+nextDirection);
		}
		else if(nextState == "WUMPUS") { //wumpus ������ ���
			System.out.print(" ������ wumpus�� �ֱ���. " + "ACTION: Shoot");
			shoot();
			System.out.print(" , ACTION: GoForward");
		}
		else if(nextState == "PITCH") { //pitch ������ ���ϱ�
			System.out.print(" ������ pitch�� �ֱ���. TURN->");
			nextDirection = Turn();
			agent.writeDirection(nextDirection);
			System.out.print(", ����: "+nextDirection);
		}
		else { //NULL - �������� �𸣰ų� NONE�� �����ϴ�.
			System.out.print(" ACTION: GoForward");
			System.out.print(" ����: "+direction);		
			agent.writeXY(nextXY); //GoForward
			Agent.wumpusDie = 0; //wumpusDie �ʱ�ȭ
		}
		return 1;
	} //Startwworld ��
	
	
	public void Die() { //wumpus�� picth ������ ����
		agent.initialAgent();
	}
	
	
	//���� �ִ� ��ġ ��ȯ
	public int[] Back(String direction) {
		if(direction == "EAST") return new int[] {x-1,y};
		else if(direction == "WEST") return new int[] {x+1, y};
		else if(direction == "NORTH") return new int[] {x, y-1};
		else return new int[] {x, y+1};
	}
	
	//���� ���� ��ġ �˷���
	public int[] Next(String direction) {
		if(direction == "EAST") return new int[] {x+1,y};
		else if(direction == "WEST") return new int[] {x-1, y};
		else if(direction == "NORTH") return new int[] {x, y+1};
		else return new int[] {x, y-1};
	}
	
	
	//wumpus ���δ�
	public void shoot() {
		//1. ȭ�� �Ѱ� ����Ͽ� ���� ���� ���� wumpus -> none
		int possible = agent.shootArrow();
		if(possible == 1) {
			String direction = agent.getDirection();
			int[] nextXY=Next(direction);
			Newstate.grid[nextXY[0]][nextXY[1]] = "WUMPUSDIE";
			Agent.IKnow[nextXY[0]][nextXY[1]] = "NONE";
			System.out.print(" SCREAM");
			Agent.wumpusDie = 1; //wumpus �׾���.
		}
		else {
			String nextDirection = Turn();
			agent.writeDirection(nextDirection);
		}
		
	}
	
	
	public String Turn() {
		String direction = agent.getDirection();
		Random r = new Random();
		
		if(r.nextInt(100)<50) {
			return TurnLeft(direction);
		}
		else return TurnRight(direction);
	}

	
	public String TurnLeft(String direction) {
		//String direction = agent.getDirection();
		String next_direction;
		if(direction == "EAST") {
			next_direction = "NORTH";
			//j++;
		}
		else if(direction == "WEST") {
			next_direction = "SOUTH";
			//j--;
		}
		else if(direction == "NORTH") {
			next_direction = "WEST";
			//i--;
		}
		else {
			next_direction = "EAST";
			//i++;
		}
		return next_direction;
	}
	
	public String TurnRight(String direction) {
		String next_direction;
		if(direction == "EAST") {
			next_direction = "SOUTH";
		}
		else if(direction == "WEST") {
			next_direction = "NORTH";
		}
		else if(direction == "NORTH") {
			next_direction = "EAST";
		}
		else {
			next_direction = "WEST";
		}
		return next_direction;
	}
	
	//����Ŭ üũ�ؾ��ϴ��� Ȯ��
	public int cycleCheck(int i, int j) {
		if (i == 1 || j == 1) {
			return 1;
		} 
		else if (i == 4 || j == 4) {
			return 1;
		}
		else return 0; //���̻� ����Ŭ üũ x
		
	}
	
	//����Ŭ ���� ������ Ȯ�� (�� üũ���� 1 ������ �ϴ� ��)
	public void countCycle(int i, int j) {
		if (Newstate.grid[i][j] != "WALL") {
			if (Agent.visit[i][j] == 0) {
				Agent.cyclenum = Agent.cyclenum + 1;
			}
		}
	}
	
	//����Ŭ ������ ������ ��
	public String cycleOut(int i, int j) {
		if(i == 1) return "EAST";
		else if(i == 4) return "WEST";
		else if(j == 1) return "NORTH";
		else return "SOUTH";
	}
}
