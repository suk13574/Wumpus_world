package wumpusWorld;


public class Climb {
	Newstate world = new Newstate(); //grid 정보있음
	Percept p = new Percept(); //percept 받아오기 위한 객체
	Agent agent = new Agent(); //agent 정보저장된 객체
	int x;
	int y;
	int[] nextXY = new int[2];
	
	static int[][] check = new int[6][6];
		
	int swingGrab = 0; //금 잡았는가?
	
	public Climb() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				check[i][j] = 0;
			}
		}
	}
	
	//swing grab 유지
	public int SwingGrab() throws InterruptedException {
		if (swingGrab == 1) return 1;
		else return 0;
	}
	
	public int DoClimb() throws InterruptedException {
			//금 잡았다.
			swingGrab = 1;
			
			//값 받아옴
			x = agent.getx();
			y = agent.gety();
			
			//1. (1, 1)이면 끝
			if (x == 1 && y == 1) {
				return 0;
			}
			
			//2. x = 1 아니고 다음 가려는 곳 none -> (x--,y)로 감
			if (x > 1  && (Agent.IKnow[x - 1][y] == "NONE" || Agent.IKnow[x - 1][y] == "NOGOLD" ||
					Agent.IKnow[x - 1][y] == "GOLD" || Agent.IKnow[x - 1][y] == "WUMPUSDIE") && check[x - 1][y] == 0) {
				check[x - 1][y] = 1;
				nextXY[0] = x - 1;
				nextXY[1] = y;
				agent.writeXY(nextXY);
				Agent.direction = "WEST";
			}
			
			//3. y = 1 아니고 다음 가려는 곳 none -> (x, y--)로 감
			else if (y > 1 && (Agent.IKnow[x][y - 1] == "NONE" || Agent.IKnow[x][y - 1] == "NOGOLD" ||
					Agent.IKnow[x][y - 1] == "GOLD" || Agent.IKnow[x][y - 1] == "WUMPUSDIE") && check[x][y - 1] == 0) {
				check[x][y - 1] = 1;
				nextXY[0] = x;
				nextXY[1] = y - 1;
				agent.writeXY(nextXY);
				Agent.direction = "SOUTH";
			}
			
			//4. x, y 다 줄일 수 없음 (x++, y)로 감, 앞에 pitch 있거나 모르면 y++;
			else {
				if (x < 5 && ((Agent.IKnow[x + 1][y] == "NONE") || (Agent.IKnow[x + 1][y] == "NOGOLD" ||
						Agent.IKnow[x + 1][y] == "GOLD" || Agent.IKnow[x + 1][y] == "WUMPUSDIE"))) {
					check[x + 1][y] = 1;
					nextXY[0] = x + 1;
					nextXY[1] = y;
					agent.writeXY(nextXY);
					Agent.direction = "EAST";
				}
				else if (y < 5 && ((Agent.IKnow[x][y + 1] == "NONE") || (Agent.IKnow[x][y + 1] == "NOGOLD" ||
						Agent.IKnow[x][y + 1] == "GOLD" || Agent.IKnow[x][y + 1] == "WUMPUSDIE"))) {
					check[x][y + 1] = 1;
					nextXY[0] = x;
					nextXY[1] = y + 1;
					agent.writeXY(nextXY);
					Agent.direction = "NORTH";
				}
			}
			//스윙 실행
			Swing swing = new Swing();
			swing.frame.setVisible(true);
			
			System.out.println("("+nextXY[0]+", "+nextXY[1]+")로 이동");
			return 1;
	}
}//class 끝
