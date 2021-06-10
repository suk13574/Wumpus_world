package wumpusWorld;

import java.util.Arrays;
import java.util.Random;

class Agent{
	static String direction;
	static int arrowCount = 3;
	static int x;
	static int y;
	static int[][] visit = new int[6][6]; //방문했는지 체크
	static String[][] IKnow = new String[6][6]; //내가 알고있는 정보 저장
	static int wumpusDie; //wumpus 죽었는지 확인
	static int randomTurn; //전에 random turn했는지
	
	//사이클 체크 변수
	static int cyclenum;
	
	public void initialAgent() {
		direction = "EAST";
		x = 1;
		y = 1;
		
		//방문격자 초기화
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				visit[i][j] = 0;
			}
		}
		//사이클 체즈 변수 초기화
		cyclenum = 0;
	}
	
	//처음에 방문한 격자 없음, 정보 없음
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
	
	//x y 값 반환
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
	
	//쓰는 함수(write)
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
	Newstate world = new Newstate(); //grid 정보있음
	Percept p = new Percept(); //percept 받아오기 위한 객체
	Agent agent = new Agent(); //agent 정보저장된 객체
	Climb climb = new Climb(); //되돌아간다
	static int buttoncheck; //버튼 누르면 1, 금 잡으면 0
	
	int x;
	int y;
	
	int cycle = 1;
	
	//시작 전 준비
	public void preStart() {
		agent.initialAgent(); //위치, 방향 초기값으로
		agent.initialVisit(); //현재 방문한 곳 없음
		Agent.randomTurn = 0; //랜덤돌기 0
	}
	
	
	//시작
	public int StartWorld() throws InterruptedException {
		//값 받아옴
		x = agent.getx();
		y = agent.gety();
		

		//스윙 실행
		Swing swing = new Swing();
		swing.frame.setVisible(true);
			
		//1. 현재 정보 불러온다
		String nowState = world.getGrid(x, y);
			
		//2. 방문체크와 현재 격자 상태 정보 추가
		agent.writeVisit();
		if(Agent.IKnow[x][y] == "NULL") agent.writeIKnow(nowState);
		System.out.print("\n(" + x + ", " + y + ")방문/ ");
			
		//3. percept 얻어옴
		int[] check = p.check(x, y);
		System.out.print(Arrays.toString(check)+"/ ");
			
		//4. WUMPUS나 PITCH 있으면 죽음
		if(nowState == "WUMPUS") {
			System.out.print(" WUMPUS 만났다. DIE");
			Die();
			return 1;
		}
		if(nowState == "PITCH") {
			System.out.print(" PITCH 발견. DIE");
			Die();
			return 1;
		}
			
		//4. 각 상황에서 reasoning
		String direction = agent.getDirection();
			
		//4-1. 벽이면 BUMP! 전으로 돌아가서 turn
		if(check[PERCEPT_num.BUMP.ordinal()] == 1) {
			System.out.print(" 벽 발견. 전 격자로 돌아간다.");
			//전으로 돌아간다
			int[] backXY;
			backXY = Back(direction);
			//x = backXY[0];
			//y = backXY[1];
			agent.writeXY(backXY);
			//Turn -> 되돌아가서 할거임
			return 1;
		}
		//4-2. gold 발견하면 멈춤
		if(Newstate.grid[x][y] == "GOLD") {
			System.out.print(" GOLD 발견. Grab\n");
			Newstate.grid[x][y] = "NOGOLD";
			return 0;
		}
		//4-3. 다음 격자 확인해주기
		int[] nextXY = Next(direction);
		int nextX = nextXY[0];
		int nextY = nextXY[1];
		//다음 격자 정보 확인 - NULL이면 모르는 것
		String nextState = agent.getIKnow(nextX, nextY);
		System.out.print(" Next block: "+nextState);
			
		//만약 다음 격자 방문하면 랜덤 확률로 방향 돈다
		if(Agent.visit[nextX][nextY] == 1 && Agent.randomTurn == 0) {
			Random r = new Random();
			if(r.nextInt(100)<50) {
				direction = Turn();
				agent.writeDirection(direction);
				System.out.print(", 다음격자 방문했음 TURN, 방향: "+direction);
				nextX = nextXY[0];
				nextY = nextXY[1];
				//다음 격자 정보 확인 - NULL이면 모르는 것
				nextState = agent.getIKnow(nextX, nextY);
				System.out.print(" 랜덤 돌기");
				Agent.randomTurn = 1;
				return 1;
			}
			else {
				System.out.print(" 방향: "+direction);
			}
		}
		Agent.randomTurn = 0;
		
		String nextDirection; //Turn하면 다음 방향 있음
		if(nextState == "WALL") { //벽이면 turn
			System.out.print(" 다음에 벽이 있구나. ACTION: TURN->");
			nextDirection = Turn();
			agent.writeDirection(nextDirection); //방향쓰기
			System.out.print(" 방향: "+nextDirection);
		}
		else if(nextState == "WUMPUS") { //wumpus 있으면 쏘기
			System.out.print(" 다음에 wumpus가 있구나. " + "ACTION: Shoot");
			shoot();
			System.out.print(" , ACTION: GoForward");
		}
		else if(nextState == "PITCH") { //pitch 있으면 피하기
			System.out.print(" 다음에 pitch가 있구나. TURN->");
			nextDirection = Turn();
			agent.writeDirection(nextDirection);
			System.out.print(", 방향: "+nextDirection);
		}
		else { //NULL - 다음정보 모르거나 NONE로 안전하다.
			System.out.print(" ACTION: GoForward");
			System.out.print(" 방향: "+direction);		
			agent.writeXY(nextXY); //GoForward
			Agent.wumpusDie = 0; //wumpusDie 초기화
		}
		return 1;
	} //Startwworld 끝
	
	
	public void Die() { //wumpus나 picth 만나면 죽음
		agent.initialAgent();
	}
	
	
	//전에 있던 위치 반환
	public int[] Back(String direction) {
		if(direction == "EAST") return new int[] {x-1,y};
		else if(direction == "WEST") return new int[] {x+1, y};
		else if(direction == "NORTH") return new int[] {x, y-1};
		else return new int[] {x, y+1};
	}
	
	//다음 격자 위치 알려줌
	public int[] Next(String direction) {
		if(direction == "EAST") return new int[] {x+1,y};
		else if(direction == "WEST") return new int[] {x-1, y};
		else if(direction == "NORTH") return new int[] {x, y+1};
		else return new int[] {x, y-1};
	}
	
	
	//wumpus 죽인다
	public void shoot() {
		//1. 화살 한개 사용하여 다음 격자 상태 wumpus -> none
		int possible = agent.shootArrow();
		if(possible == 1) {
			String direction = agent.getDirection();
			int[] nextXY=Next(direction);
			Newstate.grid[nextXY[0]][nextXY[1]] = "WUMPUSDIE";
			Agent.IKnow[nextXY[0]][nextXY[1]] = "NONE";
			System.out.print(" SCREAM");
			Agent.wumpusDie = 1; //wumpus 죽었다.
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
	
	//사이클 체크해야하는지 확인
	public int cycleCheck(int i, int j) {
		if (i == 1 || j == 1) {
			return 1;
		} 
		else if (i == 4 || j == 4) {
			return 1;
		}
		else return 0; //더이상 사이클 체크 x
		
	}
	
	//사이클 격자 도는지 확인 (위 체크에서 1 나오면 하는 것)
	public void countCycle(int i, int j) {
		if (Newstate.grid[i][j] != "WALL") {
			if (Agent.visit[i][j] == 0) {
				Agent.cyclenum = Agent.cyclenum + 1;
			}
		}
	}
	
	//사이클 있으면 안으로 들어감
	public String cycleOut(int i, int j) {
		if(i == 1) return "EAST";
		else if(i == 4) return "WEST";
		else if(j == 1) return "NORTH";
		else return "SOUTH";
	}
}
