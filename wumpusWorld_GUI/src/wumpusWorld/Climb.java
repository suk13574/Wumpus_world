package wumpusWorld;


public class Climb {
	Newstate world = new Newstate(); //grid ��������
	Percept p = new Percept(); //percept �޾ƿ��� ���� ��ü
	Agent agent = new Agent(); //agent ��������� ��ü
	int x;
	int y;
	int[] nextXY = new int[2];
	
	static int[][] check = new int[6][6];
		
	int swingGrab = 0; //�� ��Ҵ°�?
	
	public Climb() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				check[i][j] = 0;
			}
		}
	}
	
	//swing grab ����
	public int SwingGrab() throws InterruptedException {
		if (swingGrab == 1) return 1;
		else return 0;
	}
	
	public int DoClimb() throws InterruptedException {
			//�� ��Ҵ�.
			swingGrab = 1;
			
			//�� �޾ƿ�
			x = agent.getx();
			y = agent.gety();
			
			//1. (1, 1)�̸� ��
			if (x == 1 && y == 1) {
				return 0;
			}
			
			//2. x = 1 �ƴϰ� ���� ������ �� none -> (x--,y)�� ��
			if (x > 1  && (Agent.IKnow[x - 1][y] == "NONE" || Agent.IKnow[x - 1][y] == "NOGOLD" ||
					Agent.IKnow[x - 1][y] == "GOLD" || Agent.IKnow[x - 1][y] == "WUMPUSDIE") && check[x - 1][y] == 0) {
				check[x - 1][y] = 1;
				nextXY[0] = x - 1;
				nextXY[1] = y;
				agent.writeXY(nextXY);
				Agent.direction = "WEST";
			}
			
			//3. y = 1 �ƴϰ� ���� ������ �� none -> (x, y--)�� ��
			else if (y > 1 && (Agent.IKnow[x][y - 1] == "NONE" || Agent.IKnow[x][y - 1] == "NOGOLD" ||
					Agent.IKnow[x][y - 1] == "GOLD" || Agent.IKnow[x][y - 1] == "WUMPUSDIE") && check[x][y - 1] == 0) {
				check[x][y - 1] = 1;
				nextXY[0] = x;
				nextXY[1] = y - 1;
				agent.writeXY(nextXY);
				Agent.direction = "SOUTH";
			}
			
			//4. x, y �� ���� �� ���� (x++, y)�� ��, �տ� pitch �ְų� �𸣸� y++;
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
			//���� ����
			Swing swing = new Swing();
			swing.frame.setVisible(true);
			
			System.out.println("("+nextXY[0]+", "+nextXY[1]+")�� �̵�");
			return 1;
	}
}//class ��
