package wumpusWorld;

import java.util.*;


public class Newstate {
	
	static String[][] grid = new String[6][6];
	int WUMPUS_num = 0;
	int PITCH_num = 0;
	static int gold_x;
	static int gold_y;
	
	public void Generate() {
		Random r = new Random();
		
		//gold ����
		gold_x = r.nextInt(4) + 1;
		gold_y = r.nextInt(4) + 1;
		
		//(1,1) (2,1) (3,1)���� ���� x
		while ((gold_x + gold_y) <= 3) {
			gold_x = r.nextInt(4) + 1;
			gold_y = r.nextInt(4) + 1;
		}
		grid[gold_x][gold_y] = "GOLD";
		
		
		// Wumpus�� Pitch Ȯ��
		boolean wumpus_p = false;
		boolean pitch_p = false;
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				//������
				if (i == 0 || j == 0) {
					grid[i][j] = "WALL";
					continue;
				}
				if (i == 5 || j == 5) {
					grid[i][j] = "WALL";
					continue;
				}
				// (1,1), (2,1) (1,2)�� ����!
				if ((i + j) <= 3) {
					grid[i][j] = "NONE";
					continue;
				}
				
				//gold ������ �н�! 
				//if(grid[i][j] == "GOLD") continue;
				if(i == gold_x) {
					if(j == gold_y) {
						grid[i][j] = "GOLD";
						continue;
					}
				}
				
				
				//Wumpus�� pitch ����
				if(r.nextInt(100)<15) wumpus_p = true;
				if(r.nextInt(100)<15) pitch_p = true;
				if(wumpus_p == true) {
					if(pitch_p == true) { //wumpus�� pitch ���� ����
						//wumpus�� pitch �� �� �ϳ� 50%Ȯ���� ���� ����
						if(r.nextInt(10)<5) {
							if(WUMPUS_num < 3) {
								grid[i][j] = "WUMPUS";
								WUMPUS_num = WUMPUS_num + 1;
							}
						}
						else {
							if(PITCH_num < 3) {
								grid[i][j] = "PITCH";
								PITCH_num = PITCH_num + 1;
							}
							
						}
					}
					else { //wumpus ����
						grid[i][j] = "WUMPUS";
					}
				}
				else if(pitch_p == true) { //picth ����
					grid[i][j] = "PITCH";
				}
				else {
					grid[i][j] = "NONE";
				}
				
				//Wumpus picth Ȯ�� �ʱ�ȭ
				wumpus_p = false;
				pitch_p = false;
			}
		}
		
		
		//gold �� ���� ���� �ձ�
		if (gold_x == 1) { // x�� 1�̸� x+1 �վ��ֱ�
			grid[gold_x + 1][gold_y] = "NONE";
		} else 
			grid[gold_x - 1][gold_y] = "NONE";
	}
	
	
	public String getGrid(int i,int j) {
		return grid[i][j];
	}

}