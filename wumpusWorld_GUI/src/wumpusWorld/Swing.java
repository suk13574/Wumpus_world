package wumpusWorld;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class Swing {

	JFrame frame;
	
	static Newstate world = new Newstate(); //init
	static Start start = new Start(); //let's get the gold
	static Climb climb = new Climb(); //after gold grab
	static Agent agent = new Agent(); //agent 정보저장된 객체
	static Percept percept = new Percept(); //percept	

	//Loading agent (x,y)
	int x = agent.getx();
	int y = agent.gety();
	
	//Loading percept
	int[] check = percept.check(x, y);
	
	public Swing() throws InterruptedException {
		initialize();
	}


	void initialize() throws InterruptedException {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Insert Image
		//Block
		ImageIcon Block_wall = new ImageIcon(this.getClass().getResource("Block_wall.jpg")); //wall
		ImageIcon Block_pitch = new ImageIcon(this.getClass().getResource("Block_pitch.jpg")); //pitch
		ImageIcon Block_wumpus = new ImageIcon(this.getClass().getResource("Block_wumpus.jpg")); //wumpus
		ImageIcon Block_wumpusDie = new ImageIcon(this.getClass().getResource("Block_wumpusDie.jpg")); //wumpus die
		ImageIcon Block_gold = new ImageIcon(this.getClass().getResource("Block_gold.jpg")); //gold
		ImageIcon Block_noneGold = new ImageIcon(this.getClass().getResource("Block_noneGold.jpg")); //after gold grab
		ImageIcon Block_none = new ImageIcon(this.getClass().getResource("Block_none.jpg")); //none
		ImageIcon Block_finish = new ImageIcon(this.getClass().getResource("Block_finish.jpg")); //Finish
		
		//Percept check (0,5)
		ImageIcon Percept_none = new ImageIcon(this.getClass().getResource("Percept_none.jpg")); //none
		ImageIcon Percept_bump = new ImageIcon(this.getClass().getResource("Percept_bump.jpg")); //bump
		ImageIcon Percept_breeze  = new ImageIcon(this.getClass().getResource("Percept_breeze.jpg")); //breeze
		ImageIcon Percept_stench  = new ImageIcon(this.getClass().getResource("Percept_stench.jpg")); //stench
		ImageIcon Percept_breezeStench  = new ImageIcon(this.getClass().getResource("Percept_breezeStench.jpg")); //breeze stench
		ImageIcon Percept_grab  = new ImageIcon(this.getClass().getResource("Percept_grab.jpg")); //grab
		ImageIcon Percept_grabBreeze = new ImageIcon(this.getClass().getResource("Percept_grabBreeze.jpg")); //grab breeze
		ImageIcon Percept_grabStench = new ImageIcon(this.getClass().getResource("Percept_grabStench.jpg")); //grab stench
		ImageIcon Percept_grabBreezeStench = new ImageIcon(this.getClass().getResource("Percept_grabBreezeStench.jpg")); //grab breeze stench
		ImageIcon Percept_scream = new ImageIcon(this.getClass().getResource("Percept_scream.jpg")); //scream
		ImageIcon Percept_screamBreeze = new ImageIcon(this.getClass().getResource("Percept_screamBreeze.jpg")); //scream breeze
		ImageIcon Percept_screamStench = new ImageIcon(this.getClass().getResource("Percept_screamStench.jpg")); //scream stench
		ImageIcon Percept_screamBreezeStench = new ImageIcon(this.getClass().getResource("Percept_screamBreezeStench.jpg")); //scream breeze stench
		
		//Compass direction (5,5)
		ImageIcon Compass_North = new ImageIcon(this.getClass().getResource("Compass_North.jpg")); //North
		ImageIcon Compass_South = new ImageIcon(this.getClass().getResource("Compass_South.jpg")); //South
		ImageIcon Compass_East = new ImageIcon(this.getClass().getResource("Compass_East.jpg")); //East
		ImageIcon Compass_West = new ImageIcon(this.getClass().getResource("Compass_West.jpg")); //West
		
		//Human direction
		ImageIcon HumanD_north = new ImageIcon(this.getClass().getResource("HumanD_north.jpg")); //human North
		ImageIcon HumanD_south = new ImageIcon(this.getClass().getResource("HumanD_south.jpg")); //human South
		ImageIcon HumanD_east = new ImageIcon(this.getClass().getResource("HumanD_east.jpg")); //human East
		ImageIcon HumanD_west = new ImageIcon(this.getClass().getResource("HumanD_west.jpg")); //human West
		
		//Human percept
		ImageIcon HumanP_bump = new ImageIcon(this.getClass().getResource("HumanP_bump.jpg")); //human wall meet
		ImageIcon HumanP_stench = new ImageIcon(this.getClass().getResource("HumanP_stench.jpg")); //human stench
		ImageIcon HumanP_breeze = new ImageIcon(this.getClass().getResource("HumanP_breeze.jpg")); //human breeze
		ImageIcon HumanP_breezeStench = new ImageIcon(this.getClass().getResource("HumanP_breezeStench.jpg")); //human breeze and stench
		ImageIcon HumanP_climbs = new ImageIcon(this.getClass().getResource("HumanP_climbs.jpg")); //human climb
		ImageIcon HumanP_grab = new ImageIcon(this.getClass().getResource("HumanP_grab.jpg")); //human grab
		ImageIcon HumanP_grabBreeze = new ImageIcon(this.getClass().getResource("HumanP_grabBreeze.jpg")); //human grab breeze
		ImageIcon HumanP_grabStench = new ImageIcon(this.getClass().getResource("HumanP_grabStench.jpg")); //human grab stench
		ImageIcon HumanP_grabBreezeStench = new ImageIcon(this.getClass().getResource("HumanP_grabBreezeStench.jpg")); //human grab breeze stench
		ImageIcon HumanP_scream = new ImageIcon(this.getClass().getResource("HumanP_scream.jpg")); //human scream
		ImageIcon HumanP_screamBreeze = new ImageIcon(this.getClass().getResource("HumanP_screamBreeze.jpg")); //human scream
		ImageIcon HumanP_screamStench = new ImageIcon(this.getClass().getResource("HumanP_screamStench.jpg")); //human scream
		ImageIcon HumanP_screamBreezeStench = new ImageIcon(this.getClass().getResource("HumanP_screamBreezeStench.jpg")); //human scream
		ImageIcon HumanP_die = new ImageIcon(this.getClass().getResource("HumanP_die.jpg")); //human die

		//Arrow
		ImageIcon Arrow_three = new ImageIcon(this.getClass().getResource("Arrow_three.jpg")); // Arrow three
		ImageIcon Arrow_two = new ImageIcon(this.getClass().getResource("Arrow_two.jpg")); // Arrow two
		ImageIcon Arrow_one = new ImageIcon(this.getClass().getResource("Arrow_one.jpg")); // Arrow one
		ImageIcon Arrow_none = new ImageIcon(this.getClass().getResource("Arrow_none.jpg")); // Arrow none
		
		//방문 격자 Check
		ImageIcon Check_yes = new ImageIcon(this.getClass().getResource("Check_yes.jpg")); // 간적 yes
		ImageIcon Check_no = new ImageIcon(this.getClass().getResource("Check_no.jpg")); // 간적 no
		
		
		
		//Image setting
		//Block
		Image wall = Block_wall.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Wall = new ImageIcon(wall);
		Image pitch = Block_pitch.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Pitch = new ImageIcon(pitch);
		Image wumpus = Block_wumpus.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Wumpus = new ImageIcon(wumpus);
		Image wumpusDie = Block_wumpusDie.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon WumpusDie = new ImageIcon(wumpusDie);
		Image gold = Block_gold.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Gold = new ImageIcon(gold);
		Image noneGold = Block_noneGold.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon NoneGold = new ImageIcon(noneGold);
		Image none = Block_none.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon None = new ImageIcon(none);
		Image finish = Block_finish.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Finish = new ImageIcon(finish);
		
		//Percept check (0,5)
		Image p_None = Percept_none.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_None = new ImageIcon(p_None);
		Image p_Bump = Percept_bump.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_Bump = new ImageIcon(p_Bump);
		Image p_Breeze = Percept_breeze.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_Breeze = new ImageIcon(p_Breeze);
		Image p_Stench = Percept_stench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_Stench = new ImageIcon(p_Stench);
		Image p_BreezeStench = Percept_breezeStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_BreezeStench = new ImageIcon(p_BreezeStench);
		Image p_Grab = Percept_grab.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_Grab = new ImageIcon(p_Grab);
		Image p_GrabBreeze = Percept_grabBreeze.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_GrabBreeze = new ImageIcon(p_GrabBreeze);
		Image p_GrabStench = Percept_grabStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_GrabStench = new ImageIcon(p_GrabStench);
		Image p_GrabBreezeStench = Percept_grabBreezeStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_GrabBreezeStench = new ImageIcon(p_GrabBreezeStench);
		Image p_Scream = Percept_scream.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_Scream = new ImageIcon(p_Scream);
		Image p_ScreamBreeze = Percept_screamBreeze.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_ScreamBreeze = new ImageIcon(p_ScreamBreeze);
		Image p_ScreamStench = Percept_screamStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_ScreamStench = new ImageIcon(p_ScreamStench);
		Image p_ScreamBreezeStench = Percept_screamBreezeStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon P_ScreamBreezeStench = new ImageIcon(p_ScreamBreezeStench);
		
		//Compass direction (5,5)
		Image north = Compass_North.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon NORTH = new ImageIcon(north);
		Image south= Compass_South.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon SOUTH = new ImageIcon(south);
		Image east= Compass_East.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon EAST = new ImageIcon(east);
		Image west= Compass_West.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon WEST = new ImageIcon(west);
		
		//Human direction
		Image humanN = HumanD_north.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon HumanN = new ImageIcon(humanN);
		Image humanS = HumanD_south.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon HumanS = new ImageIcon(humanS);
		Image humanE = HumanD_east.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon HumanE = new ImageIcon(humanE);
		Image humanW = HumanD_west.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon HumanW = new ImageIcon(humanW);
		
		//Human percept
		Image bump = HumanP_bump.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Bump = new ImageIcon(bump);
		Image stench = HumanP_stench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Stench = new ImageIcon(stench);
		Image breeze = HumanP_breeze.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Breeze = new ImageIcon(breeze);
		Image breezeStench = HumanP_breezeStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon BreezeStench = new ImageIcon(breezeStench);
		Image climbs = HumanP_climbs.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Climbs = new ImageIcon(climbs);
		Image grab = HumanP_grab.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Grab = new ImageIcon(grab);
		Image grabBreeze = HumanP_grabBreeze.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon GrabBreeze = new ImageIcon(grabBreeze);
		Image grabStench = HumanP_grabStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon GrabStench = new ImageIcon(grabStench);
		Image grabBreezeStench = HumanP_grabBreezeStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon GrabBreezeStench = new ImageIcon(grabBreezeStench);
		Image scream = HumanP_scream.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon Scream = new ImageIcon(scream);
		Image screamBreeze = HumanP_screamBreeze.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon ScreamBreeze = new ImageIcon(screamBreeze);
		Image screamStench = HumanP_screamStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon ScreamStench = new ImageIcon(screamStench);
		Image screamBreezeStench = HumanP_screamBreezeStench.getImage().getScaledInstance(115,115,Image.SCALE_DEFAULT);
		ImageIcon ScreamBreezeStench = new ImageIcon(screamBreezeStench);
		Image die = HumanP_die.getImage().getScaledInstance(115, 115, Image.SCALE_DEFAULT);
		ImageIcon Die = new ImageIcon(die);

		//Arrow
		Image arrow_3 = Arrow_three.getImage().getScaledInstance(115, 60, Image.SCALE_DEFAULT);
		ImageIcon Arrow_3 = new ImageIcon(arrow_3);
		Image arrow_2 = Arrow_two.getImage().getScaledInstance(115, 60, Image.SCALE_DEFAULT);
		ImageIcon Arrow_2 = new ImageIcon(arrow_2);
		Image arrow_1 = Arrow_one.getImage().getScaledInstance(115, 60, Image.SCALE_DEFAULT);
		ImageIcon Arrow_1 = new ImageIcon(arrow_1);
		Image arrow_0 = Arrow_none.getImage().getScaledInstance(115, 60, Image.SCALE_DEFAULT);
		ImageIcon Arrow_0 = new ImageIcon(arrow_0);
		
		//방문 격자 Check
		Image check_yes = Check_yes.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon Check_Yes = new ImageIcon(check_yes);
		Image check_no = Check_no.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon Check_No = new ImageIcon(check_no);
		
		
		
		//Create Label
		//(0,5) Percept check
		JLabel G05 = new JLabel(world.getGrid(0, 5));
		G05.setBounds(0, 0, 114, 110);
		G05.setHorizontalAlignment(SwingConstants.CENTER);
		if (check[PERCEPT_num.SCREAM.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) G05.setIcon(P_ScreamBreezeStench);
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1) G05.setIcon(P_ScreamBreeze);
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) G05.setIcon(P_ScreamStench);
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1) G05.setIcon(P_Scream);
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) G05.setIcon(P_GrabBreezeStench);
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1) G05.setIcon(P_GrabBreeze);
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) G05.setIcon(P_GrabStench);
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1) G05.setIcon(P_Grab);
		else if (check[PERCEPT_num.BREEZE.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) G05.setIcon(P_BreezeStench);
		else if (check[PERCEPT_num.BREEZE.ordinal()] == 1) G05.setIcon(P_Breeze);
		else if (check[PERCEPT_num.STENCH.ordinal()] == 1) G05.setIcon(P_Stench);
		else if (check[PERCEPT_num.BUMP.ordinal()] == 1) G05.setIcon(P_Bump);
		else G05.setIcon(P_None);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(G05);
		
		//(1,5) ~ (5,5)
		JLabel G15 = new JLabel(world.getGrid(1, 5));
		G15.setBounds(114, 0, 114, 110);
		G15.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(1, 5)) {case "WALL" : G15.setIcon(Wall); break;}
		frame.getContentPane().add(G15);
		JLabel G25 = new JLabel(world.getGrid(2, 5));
		G25.setBounds(228, 0, 114, 110);
		G25.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(2, 5)) {case "WALL" : G25.setIcon(Wall); break;}
		frame.getContentPane().add(G25);
		JLabel G35 = new JLabel(world.getGrid(3, 5));
		G35.setBounds(342, 0, 114, 110);
		G35.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(3, 5)) {case "WALL" : G35.setIcon(Wall); break;}
		frame.getContentPane().add(G35);
		JLabel G45 = new JLabel(world.getGrid(4, 5));
		G45.setBounds(456, 0, 114, 110);
		G45.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(4, 5)) {case "WALL" : G45.setIcon(Wall); break;}
		frame.getContentPane().add(G45);
		JLabel G55 = new JLabel(world.getGrid(5, 5));
		G55.setBounds(570, 0, 114, 110);
		G55.setHorizontalAlignment(SwingConstants.CENTER);
		switch (agent.getDirection()) {
		case "NORTH" : G55.setIcon(NORTH); break;
		case "SOUTH" : G55.setIcon(SOUTH); break;
		case "EAST" : G55.setIcon(EAST); break;
		case "WEST" : G55.setIcon(WEST); break; }
		frame.getContentPane().add(G55);

		//(0,4) ~ (5,4)
		JLabel G04 = new JLabel(world.getGrid(0, 4));
		G04.setBounds(0, 110, 114, 110);
		G04.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(0, 4)) {case "WALL" : G04.setIcon(Wall); break;}
		frame.getContentPane().add(G04);
		JLabel G14 = new JLabel(world.getGrid(1, 4));
		G14.setBounds(114, 110, 114, 110);
		G14.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(1, 4)) {
		case "WALL" : G14.setIcon(Wall); break;
		case "PITCH" : G14.setIcon(Pitch); break;
		case "NONE" : G14.setIcon(None); break;
		case "WUMPUS" : G14.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G14.setIcon(WumpusDie); break;
		case "GOLD" : G14.setIcon(Gold); break;
		case "NOGOLD" : G14.setIcon(NoneGold); break;}
		frame.getContentPane().add(G14);
		JLabel G24 = new JLabel(world.getGrid(2, 4));
		G24.setBounds(228, 110, 114, 110);
		G24.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(2, 4)) {
		case "WALL" : G24.setIcon(Wall); break;
		case "PITCH" : G24.setIcon(Pitch); break;
		case "NONE" : G24.setIcon(None); break;
		case "WUMPUS" : G24.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G24.setIcon(WumpusDie); break;
		case "GOLD" : G24.setIcon(Gold); break;
		case "NOGOLD" : G24.setIcon(NoneGold); break;}
		frame.getContentPane().add(G24);
		JLabel G34 = new JLabel(world.getGrid(3, 4));
		G34.setBounds(342, 110, 114, 110);
		G34.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(3, 4)) {
		case "WALL" : G34.setIcon(Wall); break;
		case "PITCH" : G34.setIcon(Pitch); break;
		case "NONE" : G34.setIcon(None); break;
		case "WUMPUS" : G34.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G34.setIcon(WumpusDie); break;
		case "GOLD" : G34.setIcon(Gold); break;
		case "NOGOLD" : G34.setIcon(NoneGold); break;}
		frame.getContentPane().add(G34);
		JLabel G44 = new JLabel(world.getGrid(4, 4));
		G44.setBounds(456, 110, 114, 110);
		G44.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(4, 4)) {
		case "WALL" : G44.setIcon(Wall); break;
		case "PITCH" : G44.setIcon(Pitch); break;
		case "NONE" : G44.setIcon(None); break;
		case "WUMPUS" : G44.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G44.setIcon(WumpusDie); break;
		case "GOLD" : G44.setIcon(Gold); break;
		case "NOGOLD" : G44.setIcon(NoneGold); break;}
		frame.getContentPane().add(G44);
		JLabel G54 = new JLabel(world.getGrid(5, 4));
		G54.setBounds(570, 110, 114, 110);
		G54.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(5, 4)) {case "WALL" : G54.setIcon(Wall); break;}
		frame.getContentPane().add(G54);
		
		//(0,3) ~ (5,3)
		JLabel G03 = new JLabel(world.getGrid(0, 3));
		G03.setBounds(0, 220, 114, 110);
		G03.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(0, 3)) {case "WALL" : G03.setIcon(Wall); break;}
		frame.getContentPane().add(G03);
		JLabel G13 = new JLabel(world.getGrid(1, 3));
		G13.setBounds(114, 220, 114, 110);
		G13.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(1, 3)) {
		case "WALL" : G13.setIcon(Wall); break;
		case "PITCH" : G13.setIcon(Pitch); break;
		case "NONE" : G13.setIcon(None); break;
		case "WUMPUS" : G13.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G13.setIcon(WumpusDie); break;
		case "GOLD" : G13.setIcon(Gold); break;
		case "NOGOLD" : G13.setIcon(NoneGold); break;}
		frame.getContentPane().add(G13);
		JLabel G23 = new JLabel(world.getGrid(2, 3));
		G23.setBounds(228, 220, 114, 110);
		G23.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(2, 3)) {
		case "WALL" : G23.setIcon(Wall); break;
		case "PITCH" : G23.setIcon(Pitch); break;
		case "NONE" : G23.setIcon(None); break;
		case "WUMPUS" : G23.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G23.setIcon(WumpusDie); break;
		case "GOLD" : G23.setIcon(Gold); break;
		case "NOGOLD" : G23.setIcon(NoneGold); break;}
		frame.getContentPane().add(G23);
		JLabel G33 = new JLabel(world.getGrid(3, 3));
		G33.setBounds(342, 220, 114, 110);
		G33.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(3, 3)) {
		case "WALL" : G33.setIcon(Wall); break;
		case "PITCH" : G33.setIcon(Pitch); break;
		case "NONE" : G33.setIcon(None); break;
		case "WUMPUS" : G33.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G33.setIcon(WumpusDie); break;
		case "GOLD" : G33.setIcon(Gold); break;
		case "NOGOLD" : G33.setIcon(NoneGold); break;}
		frame.getContentPane().add(G33);
		JLabel G43 = new JLabel(world.getGrid(4, 3));
		G43.setBounds(456, 220, 114, 110);
		G43.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(4, 3)) {
		case "WALL" : G43.setIcon(Wall); break;
		case "PITCH" : G43.setIcon(Pitch); break;
		case "NONE" : G43.setIcon(None); break;
		case "WUMPUS" : G43.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G43.setIcon(WumpusDie); break;
		case "GOLD" : G43.setIcon(Gold); break;
		case "NOGOLD" : G43.setIcon(NoneGold); break;}
		frame.getContentPane().add(G43);
		JLabel G53 = new JLabel(world.getGrid(5, 3));
		G53.setBounds(570, 220, 114, 110);
		G53.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(5, 3)) {case "WALL" : G53.setIcon(Wall); break;}
		frame.getContentPane().add(G53);
		
		//(0,2) ~ (5,2)
		JLabel G02 = new JLabel(world.getGrid(0, 2));
		G02.setBounds(0, 330, 114, 110);
		G02.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(0, 2)) {case "WALL" : G02.setIcon(Wall); break;}
		frame.getContentPane().add(G02);
		JLabel G12 = new JLabel(world.getGrid(1, 2));
		G12.setBounds(114, 330, 114, 110);
		G12.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(1, 2)) {
		case "WALL" : G12.setIcon(Wall); break;
		case "PITCH" : G12.setIcon(Pitch); break;
		case "NONE" : G12.setIcon(None); break;
		case "WUMPUS" : G12.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G12.setIcon(WumpusDie); break;
		case "GOLD" : G12.setIcon(Gold); break;
		case "NOGOLD" : G12.setIcon(NoneGold); break;}
		frame.getContentPane().add(G12);
		JLabel G22 = new JLabel(world.getGrid(2, 2));
		G22.setBounds(228, 330, 114, 110);
		G22.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(2, 2)) {
		case "WALL" : G22.setIcon(Wall); break;
		case "PITCH" : G22.setIcon(Pitch); break;
		case "NONE" : G22.setIcon(None); break;
		case "WUMPUS" : G22.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G22.setIcon(WumpusDie); break;
		case "GOLD" : G22.setIcon(Gold); break;
		case "NOGOLD" : G22.setIcon(NoneGold); break;}
		frame.getContentPane().add(G22);
		JLabel G32 = new JLabel(world.getGrid(3, 2));
		G32.setBounds(342, 330, 114, 110);
		G32.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(3, 2)) {
		case "WALL" : G32.setIcon(Wall); break;
		case "PITCH" : G32.setIcon(Pitch); break;
		case "NONE" : G32.setIcon(None); break;
		case "WUMPUS" : G32.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G32.setIcon(WumpusDie); break;
		case "GOLD" : G32.setIcon(Gold); break;
		case "NOGOLD" : G32.setIcon(NoneGold); break;}
		frame.getContentPane().add(G32);
		JLabel G42 = new JLabel(world.getGrid(4, 2));
		G42.setBounds(456, 330, 114, 110);
		G42.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(4, 2)) {
		case "WALL" : G42.setIcon(Wall); break;
		case "PITCH" : G42.setIcon(Pitch); break;
		case "NONE" : G42.setIcon(None); break;
		case "WUMPUS" : G42.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G42.setIcon(WumpusDie); break;
		case "GOLD" : G42.setIcon(Gold); break;
		case "NOGOLD" : G42.setIcon(NoneGold); break;}
		frame.getContentPane().add(G42);
		JLabel G52 = new JLabel(world.getGrid(5, 2));
		G52.setBounds(570, 330, 114, 110);
		G52.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(5, 2)) {case "WALL" : G52.setIcon(Wall); break;}
		frame.getContentPane().add(G52);
		
		//(0,1) ~ (5,1)
		JLabel G01 = new JLabel(world.getGrid(0, 1));
		G01.setBounds(0, 440, 114, 110);
		G01.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(0, 1)) {case "WALL" : G01.setIcon(Wall); break;}
		frame.getContentPane().add(G01);
		JLabel G11 = new JLabel(world.getGrid(1, 1));
		G11.setBounds(114, 440, 114, 110);
		G11.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(1, 1)) {
		case "WALL" : G11.setIcon(Wall); break;
		case "PITCH" : G11.setIcon(Pitch); break;
		case "NONE" : G11.setIcon(None); break;
		case "WUMPUS" : G11.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G11.setIcon(WumpusDie); break;
		case "GOLD" : G11.setIcon(Gold); break;
		case "NOGOLD" : G11.setIcon(NoneGold); break;}
		frame.getContentPane().add(G11);
		JLabel G21 = new JLabel(world.getGrid(2, 1));
		G21.setBounds(228, 440, 114, 110);
		G21.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(2, 1)) {
		case "WALL" : G21.setIcon(Wall); break;
		case "PITCH" : G21.setIcon(Pitch); break;
		case "NONE" : G21.setIcon(None); break;
		case "WUMPUS" : G21.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G21.setIcon(WumpusDie); break;
		case "GOLD" : G21.setIcon(Gold); break;
		case "NOGOLD" : G21.setIcon(NoneGold); break;}
		frame.getContentPane().add(G21);
		JLabel G31 = new JLabel(world.getGrid(3, 1));
		G31.setBounds(342, 440, 114, 110);
		G31.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(3, 1)) {
		case "WALL" : G31.setIcon(Wall); break;
		case "PITCH" : G31.setIcon(Pitch); break;
		case "NONE" : G31.setIcon(None); break;
		case "WUMPUS" : G31.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G31.setIcon(WumpusDie); break;
		case "GOLD" : G31.setIcon(Gold); break;
		case "NOGOLD" : G31.setIcon(NoneGold); break;}
		frame.getContentPane().add(G31);
		JLabel G41 = new JLabel(world.getGrid(4, 1));
		G41.setBounds(456, 440, 114, 110);
		G41.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(4, 1)) {
		case "WALL" : G41.setIcon(Wall); break;
		case "PITCH" : G41.setIcon(Pitch); break;
		case "NONE" : G41.setIcon(None); break;
		case "WUMPUS" : G41.setIcon(Wumpus); break;
		case "WUMPUSDIE" : G41.setIcon(WumpusDie); break;
		case "GOLD" : G41.setIcon(Gold); break;
		case "NOGOLD" : G41.setIcon(NoneGold); break;}
		frame.getContentPane().add(G41);
		JLabel G51 = new JLabel(world.getGrid(5, 1));
		G51.setBounds(570, 440, 114, 110);
		G51.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(5, 1)) {case "WALL" : G51.setIcon(Wall); break;}
		frame.getContentPane().add(G51);
		
		//(1,0) ~ (4,0)
		JLabel G10 = new JLabel(world.getGrid(1, 0));
		G10.setBounds(114, 550, 114, 110);
		G10.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(1, 0)) {case "WALL" : G10.setIcon(Wall); break;}
		frame.getContentPane().add(G10);
		JLabel G20 = new JLabel(world.getGrid(2, 0));
		G20.setBounds(228, 550, 114, 110);
		G20.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(2, 0)) {case "WALL" : G20.setIcon(Wall); break;}
		frame.getContentPane().add(G20);
		JLabel G30 = new JLabel(world.getGrid(3, 0));
		G30.setBounds(342, 550, 114, 110);
		G30.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(3, 0)) {case "WALL" : G30.setIcon(Wall); break;}
		frame.getContentPane().add(G30);
		JLabel G40 = new JLabel(world.getGrid(4, 0));
		G40.setBounds(456, 550, 114, 110);
		G40.setHorizontalAlignment(SwingConstants.CENTER);
		switch (world.getGrid(4, 0)) {case "WALL" : G40.setIcon(Wall); break;}
		frame.getContentPane().add(G40);
		
		//Arrows
		JLabel Arrow = new JLabel("");
		Arrow.setBounds(0, 550, 114, 55);
		Arrow.setHorizontalAlignment(SwingConstants.CENTER);
		if (agent.getArrow() == 3) Arrow.setIcon(Arrow_3);
		else if (agent.getArrow() == 2) Arrow.setIcon(Arrow_2);
		else if (agent.getArrow() == 1) Arrow.setIcon(Arrow_1);
		else Arrow.setIcon(Arrow_0);
		frame.getContentPane().add(Arrow);
				
		//방문 격자인지 Check
		JLabel Check = new JLabel("");
		Check.setBounds(80, 605, 34, 55);
		Check.setHorizontalAlignment(SwingConstants.CENTER);
		if ((x < 5 && Agent.direction == "EAST" && Agent.IKnow[x + 1][y] != "NULL") || (x > 0 && Agent.direction == "WEST" && Agent.IKnow[x - 1][y] != "NULL") ||
				(y > 0 && Agent.direction == "SOUTH" && Agent.IKnow[x][y - 1] != "NULL") || (y < 5 && Agent.direction == "NORTH" && Agent.IKnow[x][y + 1] != "NULL")) Check.setIcon(Check_Yes);
		else Check.setIcon(Check_No);
		frame.getContentPane().add(Check);
		
		
		
		//Create Button
		JButton Next = new JButton(""); // 금 찾으러 가는 버튼
		Next.setIcon(new ImageIcon("C:\\Users\\윤혜숙\\Desktop\\4학년\\인공지능\\팀프로젝트 - wumpus world\\구현 이미지\\Image_NEXT.jpg"));
		Next.setFont(new Font("굴림", Font.PLAIN, 12));
		Next.setBounds(570, 550, 114, 55);
		frame.getContentPane().add(Next);
		
		JButton Home = new JButton(""); // 출발지로 돌아가는 버튼
		Home.setIcon(new ImageIcon("C:\\Users\\윤혜숙\\Desktop\\4학년\\인공지능\\팀프로젝트 - wumpus world\\구현 이미지\\Image_HOME.jpg"));
		Home.setFont(new Font("굴림", Font.PLAIN, 12));
		Home.setBounds(570, 605, 114, 55);
		frame.getContentPane().add(Home);
		
		JButton Exit = new JButton(""); // 스윙 창 종료 버튼
		Exit.setIcon(new ImageIcon("C:\\Users\\윤혜숙\\Desktop\\4학년\\인공지능\\팀프로젝트 - wumpus world\\구현 이미지\\Image_EXIT.jpg"));
		Exit.setBounds(0, 605, 80, 55);
		frame.getContentPane().add(Exit);
			
		
		//Button Action Function
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setVisible(false);
					start.StartWorld();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		Home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setVisible(false);
					climb.DoClimb();					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);	
			}
		});
		
		
		//Human State with percept
		//human die
		if (world.getGrid(x, y) == "WUMPUS" || world.getGrid(x, y) == "PITCH") {
			if (x == 1 && y == 1)
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Die);
			else if (x == 1 && y == 2) G12.setIcon(Die);
			else if (x == 1 && y == 3) G13.setIcon(Die);
			else if (x == 1 && y == 4) G14.setIcon(Die);

			else if (x == 2 && y == 1) G21.setIcon(Die);
			else if (x == 2 && y == 2) G22.setIcon(Die);
			else if (x == 2 && y == 3) G23.setIcon(Die);
			else if (x == 2 && y == 4) G24.setIcon(Die);

			else if (x == 3 && y == 1) G31.setIcon(Die);
			else if (x == 3 && y == 2) G32.setIcon(Die);
			else if (x == 3 && y == 3) G33.setIcon(Die);
			else if (x == 3 && y == 4) G34.setIcon(Die);

			else if (x == 4 && y == 1) G41.setIcon(Die);
			else if (x == 4 && y == 2) G42.setIcon(Die);
			else if (x == 4 && y == 3) G43.setIcon(Die);
			else if (x == 4 && y == 4) G44.setIcon(Die);
		}
		//human always grab the gold
		else if (climb.swingGrab == 1) {
			if (x == 1 && y == 1)
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Climbs);
			else if (x == 1 && y == 2) G12.setIcon(Climbs);
			else if (x == 1 && y == 3) G13.setIcon(Climbs);
			else if (x == 1 && y == 4) G14.setIcon(Climbs);

			else if (x == 2 && y == 1) G21.setIcon(Climbs);
			else if (x == 2 && y == 2) G22.setIcon(Climbs);
			else if (x == 2 && y == 3) G23.setIcon(Climbs);
			else if (x == 2 && y == 4) G24.setIcon(Climbs);

			else if (x == 3 && y == 1) G31.setIcon(Climbs);
			else if (x == 3 && y == 2) G32.setIcon(Climbs);
			else if (x == 3 && y == 3) G33.setIcon(Climbs);
			else if (x == 3 && y == 4) G34.setIcon(Climbs);

			else if (x == 4 && y == 1) G41.setIcon(Climbs);
			else if (x == 4 && y == 2) G42.setIcon(Climbs);
			else if (x == 4 && y == 3) G43.setIcon(Climbs);
			else if (x == 4 && y == 4) G44.setIcon(Climbs);
			}
		//glitter breeze stench 1
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(GrabBreezeStench);
			else if (x == 0 && y == 2) G02.setIcon(GrabBreezeStench);
			else if (x == 0 && y == 3) G03.setIcon(GrabBreezeStench);
			else if (x == 0 && y == 4) G04.setIcon(GrabBreezeStench);
		
			else if (x == 1 && y == 0) G10.setIcon(GrabBreezeStench);
			else if (x == 1 && y == 1)
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(GrabBreezeStench);
			else if (x == 1 && y == 2) G12.setIcon(GrabBreezeStench);
			else if (x == 1 && y == 3) G13.setIcon(GrabBreezeStench);
			else if (x == 1 && y == 4) G14.setIcon(GrabBreezeStench);
			else if (x == 1 && y == 5) G15.setIcon(GrabBreezeStench);

			else if (x == 2 && y == 0) G20.setIcon(GrabBreezeStench);
			else if (x == 2 && y == 1) G21.setIcon(GrabBreezeStench);
			else if (x == 2 && y == 2) G22.setIcon(GrabBreezeStench);
			else if (x == 2 && y == 3) G23.setIcon(GrabBreezeStench);
			else if (x == 2 && y == 4) G24.setIcon(GrabBreezeStench);
			else if (x == 2 && y == 5) G25.setIcon(GrabBreezeStench);

			else if (x == 3 && y == 0) G30.setIcon(GrabBreezeStench);
			else if (x == 3 && y == 1) G31.setIcon(GrabBreezeStench);
			else if (x == 3 && y == 2) G32.setIcon(GrabBreezeStench);
			else if (x == 3 && y == 3) G33.setIcon(GrabBreezeStench);
			else if (x == 3 && y == 4) G34.setIcon(GrabBreezeStench);
			else if (x == 3 && y == 5) G35.setIcon(GrabBreezeStench);

			else if (x == 4 && y == 0) G40.setIcon(GrabBreezeStench);		
			else if (x == 4 && y == 1) G41.setIcon(GrabBreezeStench);
			else if (x == 4 && y == 2) G42.setIcon(GrabBreezeStench);
			else if (x == 4 && y == 3) G43.setIcon(GrabBreezeStench);
			else if (x == 4 && y == 4) G44.setIcon(GrabBreezeStench);
			else if (x == 4 && y == 5) G45.setIcon(GrabBreezeStench);

			else if (x == 5 && y == 1) G51.setIcon(GrabBreezeStench);
			else if (x == 5 && y == 2) G52.setIcon(GrabBreezeStench);
			else if (x == 5 && y == 3) G53.setIcon(GrabBreezeStench);
			else if (x == 5 && y == 4) G54.setIcon(GrabBreezeStench);
		}
		//scream breeze stench 1
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(ScreamBreezeStench);
			else if (x == 0 && y == 2) G02.setIcon(ScreamBreezeStench);
			else if (x == 0 && y == 3) G03.setIcon(ScreamBreezeStench);
			else if (x == 0 && y == 4) G04.setIcon(ScreamBreezeStench);
		
			else if (x == 1 && y == 0) G10.setIcon(ScreamBreezeStench);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(ScreamBreezeStench);
			else if (x == 1 && y == 2) G12.setIcon(ScreamBreezeStench);
			else if (x == 1 && y == 3) G13.setIcon(ScreamBreezeStench);
			else if (x == 1 && y == 4) G14.setIcon(ScreamBreezeStench);
			else if (x == 1 && y == 5) G15.setIcon(ScreamBreezeStench);

			else if (x == 2 && y == 0) G20.setIcon(ScreamBreezeStench);
			else if (x == 2 && y == 1) G21.setIcon(ScreamBreezeStench);
			else if (x == 2 && y == 2) G22.setIcon(ScreamBreezeStench);
			else if (x == 2 && y == 3) G23.setIcon(ScreamBreezeStench);
			else if (x == 2 && y == 4) G24.setIcon(ScreamBreezeStench);
			else if (x == 2 && y == 5) G25.setIcon(ScreamBreezeStench);

			else if (x == 3 && y == 0) G30.setIcon(ScreamBreezeStench);
			else if (x == 3 && y == 1) G31.setIcon(ScreamBreezeStench);
			else if (x == 3 && y == 2) G32.setIcon(ScreamBreezeStench);
			else if (x == 3 && y == 3) G33.setIcon(ScreamBreezeStench);
			else if (x == 3 && y == 4) G34.setIcon(ScreamBreezeStench);
			else if (x == 3 && y == 5) G35.setIcon(ScreamBreezeStench);

			else if (x == 4 && y == 0) G40.setIcon(ScreamBreezeStench);		
			else if (x == 4 && y == 1) G41.setIcon(ScreamBreezeStench);
			else if (x == 4 && y == 2) G42.setIcon(ScreamBreezeStench);
			else if (x == 4 && y == 3) G43.setIcon(ScreamBreezeStench);
			else if (x == 4 && y == 4) G44.setIcon(ScreamBreezeStench);
			else if (x == 4 && y == 5) G45.setIcon(ScreamBreezeStench);

			else if (x == 5 && y == 1) G51.setIcon(ScreamBreezeStench);
			else if (x == 5 && y == 2) G52.setIcon(ScreamBreezeStench);
			else if (x == 5 && y == 3) G53.setIcon(ScreamBreezeStench);
			else if (x == 5 && y == 4) G54.setIcon(ScreamBreezeStench);
		}
		//scream breeze 1
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(ScreamBreeze);
			else if (x == 0 && y == 2) G02.setIcon(ScreamBreeze);
			else if (x == 0 && y == 3) G03.setIcon(ScreamBreeze);
			else if (x == 0 && y == 4) G04.setIcon(ScreamBreeze);
		
			else if (x == 1 && y == 0) G10.setIcon(ScreamBreeze);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(ScreamBreeze);
			else if (x == 1 && y == 2) G12.setIcon(ScreamBreeze);
			else if (x == 1 && y == 3) G13.setIcon(ScreamBreeze);
			else if (x == 1 && y == 4) G14.setIcon(ScreamBreeze);
			else if (x == 1 && y == 5) G15.setIcon(ScreamBreeze);

			else if (x == 2 && y == 0) G20.setIcon(ScreamBreeze);
			else if (x == 2 && y == 1) G21.setIcon(ScreamBreeze);
			else if (x == 2 && y == 2) G22.setIcon(ScreamBreeze);
			else if (x == 2 && y == 3) G23.setIcon(ScreamBreeze);
			else if (x == 2 && y == 4) G24.setIcon(ScreamBreeze);
			else if (x == 2 && y == 5) G25.setIcon(ScreamBreeze);

			else if (x == 3 && y == 0) G30.setIcon(ScreamBreeze);
			else if (x == 3 && y == 1) G31.setIcon(ScreamBreeze);
			else if (x == 3 && y == 2) G32.setIcon(ScreamBreeze);
			else if (x == 3 && y == 3) G33.setIcon(ScreamBreeze);
			else if (x == 3 && y == 4) G34.setIcon(ScreamBreeze);
			else if (x == 3 && y == 5) G35.setIcon(ScreamBreeze);

			else if (x == 4 && y == 0) G40.setIcon(ScreamBreeze);		
			else if (x == 4 && y == 1) G41.setIcon(ScreamBreeze);
			else if (x == 4 && y == 2) G42.setIcon(ScreamBreeze);
			else if (x == 4 && y == 3) G43.setIcon(ScreamBreeze);
			else if (x == 4 && y == 4) G44.setIcon(ScreamBreeze);
			else if (x == 4 && y == 5) G45.setIcon(ScreamBreeze);

			else if (x == 5 && y == 1) G51.setIcon(ScreamBreeze);
			else if (x == 5 && y == 2) G52.setIcon(ScreamBreeze);
			else if (x == 5 && y == 3) G53.setIcon(ScreamBreeze);
			else if (x == 5 && y == 4) G54.setIcon(ScreamBreeze);
		}
		//scream stench 1
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(ScreamStench);
			else if (x == 0 && y == 2) G02.setIcon(ScreamStench);
			else if (x == 0 && y == 3) G03.setIcon(ScreamStench);
			else if (x == 0 && y == 4) G04.setIcon(ScreamStench);
		
			else if (x == 1 && y == 0) G10.setIcon(ScreamStench);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(ScreamStench);
			else if (x == 1 && y == 2) G12.setIcon(ScreamStench);
			else if (x == 1 && y == 3) G13.setIcon(ScreamStench);
			else if (x == 1 && y == 4) G14.setIcon(ScreamStench);
			else if (x == 1 && y == 5) G15.setIcon(ScreamStench);

			else if (x == 2 && y == 0) G20.setIcon(ScreamStench);
			else if (x == 2 && y == 1) G21.setIcon(ScreamStench);
			else if (x == 2 && y == 2) G22.setIcon(ScreamStench);
			else if (x == 2 && y == 3) G23.setIcon(ScreamStench);
			else if (x == 2 && y == 4) G24.setIcon(ScreamStench);
			else if (x == 2 && y == 5) G25.setIcon(ScreamStench);

			else if (x == 3 && y == 0) G30.setIcon(ScreamStench);
			else if (x == 3 && y == 1) G31.setIcon(ScreamStench);
			else if (x == 3 && y == 2) G32.setIcon(ScreamStench);
			else if (x == 3 && y == 3) G33.setIcon(ScreamStench);
			else if (x == 3 && y == 4) G34.setIcon(ScreamStench);
			else if (x == 3 && y == 5) G35.setIcon(ScreamStench);

			else if (x == 4 && y == 0) G40.setIcon(ScreamStench);		
			else if (x == 4 && y == 1) G41.setIcon(ScreamStench);
			else if (x == 4 && y == 2) G42.setIcon(ScreamStench);
			else if (x == 4 && y == 3) G43.setIcon(ScreamStench);
			else if (x == 4 && y == 4) G44.setIcon(ScreamStench);
			else if (x == 4 && y == 5) G45.setIcon(ScreamStench);

			else if (x == 5 && y == 1) G51.setIcon(ScreamStench);
			else if (x == 5 && y == 2) G52.setIcon(ScreamStench);
			else if (x == 5 && y == 3) G53.setIcon(ScreamStench);
			else if (x == 5 && y == 4) G54.setIcon(ScreamStench);
		}
		//glitter breeze 1
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1 && check[PERCEPT_num.BREEZE.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(GrabBreeze);
			else if (x == 0 && y == 2) G02.setIcon(GrabBreeze);
			else if (x == 0 && y == 3) G03.setIcon(GrabBreeze);
			else if (x == 0 && y == 4) G04.setIcon(GrabBreeze);
		
			else if (x == 1 && y == 0) G10.setIcon(GrabBreeze);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(GrabBreeze);
			else if (x == 1 && y == 2) G12.setIcon(GrabBreeze);
			else if (x == 1 && y == 3) G13.setIcon(GrabBreeze);
			else if (x == 1 && y == 4) G14.setIcon(GrabBreeze);
			else if (x == 1 && y == 5) G15.setIcon(GrabBreeze);

			else if (x == 2 && y == 0) G20.setIcon(GrabBreeze);
			else if (x == 2 && y == 1) G21.setIcon(GrabBreeze);
			else if (x == 2 && y == 2) G22.setIcon(GrabBreeze);
			else if (x == 2 && y == 3) G23.setIcon(GrabBreeze);
			else if (x == 2 && y == 4) G24.setIcon(GrabBreeze);
			else if (x == 2 && y == 5) G25.setIcon(GrabBreeze);

			else if (x == 3 && y == 0) G30.setIcon(GrabBreeze);
			else if (x == 3 && y == 1) G31.setIcon(GrabBreeze);
			else if (x == 3 && y == 2) G32.setIcon(GrabBreeze);
			else if (x == 3 && y == 3) G33.setIcon(GrabBreeze);
			else if (x == 3 && y == 4) G34.setIcon(GrabBreeze);
			else if (x == 3 && y == 5) G35.setIcon(GrabBreeze);

			else if (x == 4 && y == 0) G40.setIcon(GrabBreeze);		
			else if (x == 4 && y == 1) G41.setIcon(GrabBreeze);
			else if (x == 4 && y == 2) G42.setIcon(GrabBreeze);
			else if (x == 4 && y == 3) G43.setIcon(GrabBreeze);
			else if (x == 4 && y == 4) G44.setIcon(GrabBreeze);
			else if (x == 4 && y == 5) G45.setIcon(GrabBreeze);

			else if (x == 5 && y == 1) G51.setIcon(GrabBreeze);
			else if (x == 5 && y == 2) G52.setIcon(GrabBreeze);
			else if (x == 5 && y == 3) G53.setIcon(GrabBreeze);
			else if (x == 5 && y == 4) G54.setIcon(GrabBreeze);
		}
		//glitter stench 1
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(GrabStench);
			else if (x == 0 && y == 2) G02.setIcon(GrabStench);
			else if (x == 0 && y == 3) G03.setIcon(GrabStench);
			else if (x == 0 && y == 4) G04.setIcon(GrabStench);
		
			else if (x == 1 && y == 0) G10.setIcon(GrabStench);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(GrabStench);
			else if (x == 1 && y == 2) G12.setIcon(GrabStench);
			else if (x == 1 && y == 3) G13.setIcon(GrabStench);
			else if (x == 1 && y == 4) G14.setIcon(GrabStench);
			else if (x == 1 && y == 5) G15.setIcon(GrabStench);

			else if (x == 2 && y == 0) G20.setIcon(GrabStench);
			else if (x == 2 && y == 1) G21.setIcon(GrabStench);
			else if (x == 2 && y == 2) G22.setIcon(GrabStench);
			else if (x == 2 && y == 3) G23.setIcon(GrabStench);
			else if (x == 2 && y == 4) G24.setIcon(GrabStench);
			else if (x == 2 && y == 5) G25.setIcon(GrabStench);

			else if (x == 3 && y == 0) G30.setIcon(GrabStench);
			else if (x == 3 && y == 1) G31.setIcon(GrabStench);
			else if (x == 3 && y == 2) G32.setIcon(GrabStench);
			else if (x == 3 && y == 3) G33.setIcon(GrabStench);
			else if (x == 3 && y == 4) G34.setIcon(GrabStench);
			else if (x == 3 && y == 5) G35.setIcon(GrabStench);

			else if (x == 4 && y == 0) G40.setIcon(GrabStench);		
			else if (x == 4 && y == 1) G41.setIcon(GrabStench);
			else if (x == 4 && y == 2) G42.setIcon(GrabStench);
			else if (x == 4 && y == 3) G43.setIcon(GrabStench);
			else if (x == 4 && y == 4) G44.setIcon(GrabStench);
			else if (x == 4 && y == 5) G45.setIcon(GrabStench);

			else if (x == 5 && y == 1) G51.setIcon(GrabStench);
			else if (x == 5 && y == 2) G52.setIcon(GrabStench);
			else if (x == 5 && y == 3) G53.setIcon(GrabStench);
			else if (x == 5 && y == 4) G54.setIcon(GrabStench);
		}
		//breeze stench 1
		else if (check[PERCEPT_num.BREEZE.ordinal()] == 1 && check[PERCEPT_num.STENCH.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(BreezeStench);
			else if (x == 0 && y == 2) G02.setIcon(BreezeStench);
			else if (x == 0 && y == 3) G03.setIcon(BreezeStench);
			else if (x == 0 && y == 4) G04.setIcon(BreezeStench);
		
			else if (x == 1 && y == 0) G10.setIcon(BreezeStench);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(BreezeStench);
			else if (x == 1 && y == 2) G12.setIcon(BreezeStench);
			else if (x == 1 && y == 3) G13.setIcon(BreezeStench);
			else if (x == 1 && y == 4) G14.setIcon(BreezeStench);
			else if (x == 1 && y == 5) G15.setIcon(BreezeStench);

			else if (x == 2 && y == 0) G20.setIcon(BreezeStench);
			else if (x == 2 && y == 1) G21.setIcon(BreezeStench);
			else if (x == 2 && y == 2) G22.setIcon(BreezeStench);
			else if (x == 2 && y == 3) G23.setIcon(BreezeStench);
			else if (x == 2 && y == 4) G24.setIcon(BreezeStench);
			else if (x == 2 && y == 5) G25.setIcon(BreezeStench);

			else if (x == 3 && y == 0) G30.setIcon(BreezeStench);
			else if (x == 3 && y == 1) G31.setIcon(BreezeStench);
			else if (x == 3 && y == 2) G32.setIcon(BreezeStench);
			else if (x == 3 && y == 3) G33.setIcon(BreezeStench);
			else if (x == 3 && y == 4) G34.setIcon(BreezeStench);
			else if (x == 3 && y == 5) G35.setIcon(BreezeStench);

			else if (x == 4 && y == 0) G40.setIcon(BreezeStench);		
			else if (x == 4 && y == 1) G41.setIcon(BreezeStench);
			else if (x == 4 && y == 2) G42.setIcon(BreezeStench);
			else if (x == 4 && y == 3) G43.setIcon(BreezeStench);
			else if (x == 4 && y == 4) G44.setIcon(BreezeStench);
			else if (x == 4 && y == 5) G45.setIcon(BreezeStench);

			else if (x == 5 && y == 1) G51.setIcon(BreezeStench);
			else if (x == 5 && y == 2) G52.setIcon(BreezeStench);
			else if (x == 5 && y == 3) G53.setIcon(BreezeStench);
			else if (x == 5 && y == 4) G54.setIcon(BreezeStench);
		}
		//stench 1
		else if (check[PERCEPT_num.STENCH.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(Stench);
			else if (x == 0 && y == 2) G02.setIcon(Stench);
			else if (x == 0 && y == 3) G03.setIcon(Stench);
			else if (x == 0 && y == 4) G04.setIcon(Stench);
		
			else if (x == 1 && y == 0) G10.setIcon(Stench);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Stench);
			else if (x == 1 && y == 2) G12.setIcon(Stench);
			else if (x == 1 && y == 3) G13.setIcon(Stench);
			else if (x == 1 && y == 4) G14.setIcon(Stench);
			else if (x == 1 && y == 5) G15.setIcon(Stench);

			else if (x == 2 && y == 0) G20.setIcon(Stench);
			else if (x == 2 && y == 1) G21.setIcon(Stench);
			else if (x == 2 && y == 2) G22.setIcon(Stench);
			else if (x == 2 && y == 3) G23.setIcon(Stench);
			else if (x == 2 && y == 4) G24.setIcon(Stench);
			else if (x == 2 && y == 5) G25.setIcon(Stench);

			else if (x == 3 && y == 0) G30.setIcon(Stench);
			else if (x == 3 && y == 1) G31.setIcon(Stench);
			else if (x == 3 && y == 2) G32.setIcon(Stench);
			else if (x == 3 && y == 3) G33.setIcon(Stench);
			else if (x == 3 && y == 4) G34.setIcon(Stench);
			else if (x == 3 && y == 5) G35.setIcon(Stench);

			else if (x == 4 && y == 0) G40.setIcon(Stench);		
			else if (x == 4 && y == 1) G41.setIcon(Stench);
			else if (x == 4 && y == 2) G42.setIcon(Stench);
			else if (x == 4 && y == 3) G43.setIcon(Stench);
			else if (x == 4 && y == 4) G44.setIcon(Stench);
			else if (x == 4 && y == 5) G45.setIcon(Stench);

			else if (x == 5 && y == 1) G51.setIcon(Stench);
			else if (x == 5 && y == 2) G52.setIcon(Stench);
			else if (x == 5 && y == 3) G53.setIcon(Stench);
			else if (x == 5 && y == 4) G54.setIcon(Stench);
		}
		// breeze 1
		else if (check[PERCEPT_num.BREEZE.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(Breeze);
			else if (x == 0 && y == 2) G02.setIcon(Breeze);
			else if (x == 0 && y == 3) G03.setIcon(Breeze);
			else if (x == 0 && y == 4) G04.setIcon(Breeze);
		
			else if (x == 1 && y == 0) G10.setIcon(Breeze);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Breeze);
			else if (x == 1 && y == 2) G12.setIcon(Breeze);
			else if (x == 1 && y == 3) G13.setIcon(Breeze);
			else if (x == 1 && y == 4) G14.setIcon(Breeze);
			else if (x == 1 && y == 5) G15.setIcon(Breeze);

			else if (x == 2 && y == 0) G20.setIcon(Breeze);
			else if (x == 2 && y == 1) G21.setIcon(Breeze);
			else if (x == 2 && y == 2) G22.setIcon(Breeze);
			else if (x == 2 && y == 3) G23.setIcon(Breeze);
			else if (x == 2 && y == 4) G24.setIcon(Breeze);
			else if (x == 2 && y == 5) G25.setIcon(Breeze);

			else if (x == 3 && y == 0) G30.setIcon(Breeze);
			else if (x == 3 && y == 1) G31.setIcon(Breeze);
			else if (x == 3 && y == 2) G32.setIcon(Breeze);
			else if (x == 3 && y == 3) G33.setIcon(Breeze);
			else if (x == 3 && y == 4) G34.setIcon(Breeze);
			else if (x == 3 && y == 5) G35.setIcon(Breeze);

			else if (x == 4 && y == 0) G40.setIcon(Breeze);		
			else if (x == 4 && y == 1) G41.setIcon(Breeze);
			else if (x == 4 && y == 2) G42.setIcon(Breeze);
			else if (x == 4 && y == 3) G43.setIcon(Breeze);
			else if (x == 4 && y == 4) G44.setIcon(Breeze);
			else if (x == 4 && y == 5) G45.setIcon(Breeze);

			else if (x == 5 && y == 1) G51.setIcon(Breeze);
			else if (x == 5 && y == 2) G52.setIcon(Breeze);
			else if (x == 5 && y == 3) G53.setIcon(Breeze);
			else if (x == 5 && y == 4) G54.setIcon(Breeze);
		} 
		// glitter 1
		else if (check[PERCEPT_num.GLITTER.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(Grab);
			else if (x == 0 && y == 2) G02.setIcon(Grab);
			else if (x == 0 && y == 3) G03.setIcon(Grab);
			else if (x == 0 && y == 4) G04.setIcon(Grab);
		
			else if (x == 1 && y == 0) G10.setIcon(Grab);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Grab);
			else if (x == 1 && y == 2) G12.setIcon(Grab);
			else if (x == 1 && y == 3) G13.setIcon(Grab);
			else if (x == 1 && y == 4) G14.setIcon(Grab);
			else if (x == 1 && y == 5) G15.setIcon(Grab);

			else if (x == 2 && y == 0) G20.setIcon(Grab);
			else if (x == 2 && y == 1) G21.setIcon(Grab);
			else if (x == 2 && y == 2) G22.setIcon(Grab);
			else if (x == 2 && y == 3) G23.setIcon(Grab);
			else if (x == 2 && y == 4) G24.setIcon(Grab);
			else if (x == 2 && y == 5) G25.setIcon(Grab);

			else if (x == 3 && y == 0) G30.setIcon(Grab);
			else if (x == 3 && y == 1) G31.setIcon(Grab);
			else if (x == 3 && y == 2) G32.setIcon(Grab);
			else if (x == 3 && y == 3) G33.setIcon(Grab);
			else if (x == 3 && y == 4) G34.setIcon(Grab);
			else if (x == 3 && y == 5) G35.setIcon(Grab);

			else if (x == 4 && y == 0) G40.setIcon(Grab);		
			else if (x == 4 && y == 1) G41.setIcon(Grab);
			else if (x == 4 && y == 2) G42.setIcon(Grab);
			else if (x == 4 && y == 3) G43.setIcon(Grab);
			else if (x == 4 && y == 4) G44.setIcon(Grab);
			else if (x == 4 && y == 5) G45.setIcon(Grab);

			else if (x == 5 && y == 1) G51.setIcon(Grab);
			else if (x == 5 && y == 2) G52.setIcon(Grab);
			else if (x == 5 && y == 3) G53.setIcon(Grab);
			else if (x == 5 && y == 4) G54.setIcon(Grab);
		}
		//bump 1
		else if (check[PERCEPT_num.BUMP.ordinal()] == 1) {     
			if (x == 0 && y == 1) G01.setIcon(Bump);
			else if (x == 0 && y == 2) G02.setIcon(Bump);
			else if (x == 0 && y == 3) G03.setIcon(Bump);
			else if (x == 0 && y == 4) G04.setIcon(Bump);
		
			else if (x == 1 && y == 0) G10.setIcon(Bump);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Bump);
			else if (x == 1 && y == 2) G12.setIcon(Bump);
			else if (x == 1 && y == 3) G13.setIcon(Bump);
			else if (x == 1 && y == 4) G14.setIcon(Bump);
			else if (x == 1 && y == 5) G15.setIcon(Bump);

			else if (x == 2 && y == 0) G20.setIcon(Bump);
			else if (x == 2 && y == 1) G21.setIcon(Bump);
			else if (x == 2 && y == 2) G22.setIcon(Bump);
			else if (x == 2 && y == 3) G23.setIcon(Bump);
			else if (x == 2 && y == 4) G24.setIcon(Bump);
			else if (x == 2 && y == 5) G25.setIcon(Bump);

			else if (x == 3 && y == 0) G30.setIcon(Bump);
			else if (x == 3 && y == 1) G31.setIcon(Bump);
			else if (x == 3 && y == 2) G32.setIcon(Bump);
			else if (x == 3 && y == 3) G33.setIcon(Bump);
			else if (x == 3 && y == 4) G34.setIcon(Bump);
			else if (x == 3 && y == 5) G35.setIcon(Bump);

			else if (x == 4 && y == 0) G40.setIcon(Bump);		
			else if (x == 4 && y == 1) G41.setIcon(Bump);
			else if (x == 4 && y == 2) G42.setIcon(Bump);
			else if (x == 4 && y == 3) G43.setIcon(Bump);
			else if (x == 4 && y == 4) G44.setIcon(Bump);
			else if (x == 4 && y == 5) G45.setIcon(Bump);

			else if (x == 5 && y == 1) G51.setIcon(Bump);
			else if (x == 5 && y == 2) G52.setIcon(Bump);
			else if (x == 5 && y == 3) G53.setIcon(Bump);
			else if (x == 5 && y == 4) G54.setIcon(Bump);
		}
		//scream 1
		else if (check[PERCEPT_num.SCREAM.ordinal()] == 1) {
			if (x == 0 && y == 1) G01.setIcon(Scream);
			else if (x == 0 && y == 2) G02.setIcon(Scream);
			else if (x == 0 && y == 3) G03.setIcon(Scream);
			else if (x == 0 && y == 4) G04.setIcon(Scream);
		
			else if (x == 1 && y == 0) G10.setIcon(Scream);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(Scream);
			else if (x == 1 && y == 2) G12.setIcon(Scream);
			else if (x == 1 && y == 3) G13.setIcon(Scream);
			else if (x == 1 && y == 4) G14.setIcon(Scream);
			else if (x == 1 && y == 5) G15.setIcon(Scream);

			else if (x == 2 && y == 0) G20.setIcon(Scream);
			else if (x == 2 && y == 1) G21.setIcon(Scream);
			else if (x == 2 && y == 2) G22.setIcon(Scream);
			else if (x == 2 && y == 3) G23.setIcon(Scream);
			else if (x == 2 && y == 4) G24.setIcon(Scream);
			else if (x == 2 && y == 5) G25.setIcon(Scream);

			else if (x == 3 && y == 0) G30.setIcon(Scream);
			else if (x == 3 && y == 1) G31.setIcon(Scream);
			else if (x == 3 && y == 2) G32.setIcon(Scream);
			else if (x == 3 && y == 3) G33.setIcon(Scream);
			else if (x == 3 && y == 4) G34.setIcon(Scream);
			else if (x == 3 && y == 5) G35.setIcon(Scream);

			else if (x == 4 && y == 0) G40.setIcon(Scream);		
			else if (x == 4 && y == 1) G41.setIcon(Scream);
			else if (x == 4 && y == 2) G42.setIcon(Scream);
			else if (x == 4 && y == 3) G43.setIcon(Scream);
			else if (x == 4 && y == 4) G44.setIcon(Scream);
			else if (x == 4 && y == 5) G45.setIcon(Scream);

			else if (x == 5 && y == 1) G51.setIcon(Scream);
			else if (x == 5 && y == 2) G52.setIcon(Scream);
			else if (x == 5 && y == 3) G53.setIcon(Scream);
			else if (x == 5 && y == 4) G54.setIcon(Scream);
		}
		
				
		//All of percept is 0, then show the direction
		//NORTH
		else if (Agent.direction == "NORTH") {
			if (x == 0 && y == 1) G01.setIcon(HumanN);
			else if (x == 0 && y == 2) G02.setIcon(HumanN);
			else if (x == 0 && y == 3) G03.setIcon(HumanN);
			else if (x == 0 && y == 4) G04.setIcon(HumanN);
		
			else if (x == 1 && y == 0) G10.setIcon(HumanN);
			else if (x == 1 && y == 1) 
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(HumanN);
			else if (x == 1 && y == 2) G12.setIcon(HumanN);
			else if (x == 1 && y == 3) G13.setIcon(HumanN);
			else if (x == 1 && y == 4) G14.setIcon(HumanN);
			else if (x == 1 && y == 5) G15.setIcon(HumanN);

			else if (x == 2 && y == 0) G20.setIcon(HumanN);
			else if (x == 2 && y == 1) G21.setIcon(HumanN);
			else if (x == 2 && y == 2) G22.setIcon(HumanN);
			else if (x == 2 && y == 3) G23.setIcon(HumanN);
			else if (x == 2 && y == 4) G24.setIcon(HumanN);
			else if (x == 2 && y == 5) G25.setIcon(HumanN);

			else if (x == 3 && y == 0) G30.setIcon(HumanN);
			else if (x == 3 && y == 1) G31.setIcon(HumanN);
			else if (x == 3 && y == 2) G32.setIcon(HumanN);
			else if (x == 3 && y == 3) G33.setIcon(HumanN);
			else if (x == 3 && y == 4) G34.setIcon(HumanN);
			else if (x == 3 && y == 5) G35.setIcon(HumanN);

			else if (x == 4 && y == 0) G40.setIcon(HumanN);		
			else if (x == 4 && y == 1) G41.setIcon(HumanN);
			else if (x == 4 && y == 2) G42.setIcon(HumanN);
			else if (x == 4 && y == 3) G43.setIcon(HumanN);
			else if (x == 4 && y == 4) G44.setIcon(HumanN);
			else if (x == 4 && y == 5) G45.setIcon(HumanN);

			else if (x == 5 && y == 1) G51.setIcon(HumanN);
			else if (x == 5 && y == 2) G52.setIcon(HumanN);
			else if (x == 5 && y == 3) G53.setIcon(HumanN);
			else if (x == 5 && y == 4) G54.setIcon(HumanN);
		}
		//SOUTH
		else if (Agent.direction == "SOUTH") {
			if (x == 0 && y == 1) G01.setIcon(HumanS);
			else if (x == 0 && y == 2) G02.setIcon(HumanS);
			else if (x == 0 && y == 3) G03.setIcon(HumanS);
			else if (x == 0 && y == 4) G04.setIcon(HumanS);
		
			else if (x == 1 && y == 0) G10.setIcon(HumanS);
			else if (x == 1 && y == 1)
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(HumanS);
			else if (x == 1 && y == 2) G12.setIcon(HumanS);
			else if (x == 1 && y == 3) G13.setIcon(HumanS);
			else if (x == 1 && y == 4) G14.setIcon(HumanS);
			else if (x == 1 && y == 5) G15.setIcon(HumanS);

			else if (x == 2 && y == 0) G20.setIcon(HumanS);
			else if (x == 2 && y == 1) G21.setIcon(HumanS);
			else if (x == 2 && y == 2) G22.setIcon(HumanS);
			else if (x == 2 && y == 3) G23.setIcon(HumanS);
			else if (x == 2 && y == 4) G24.setIcon(HumanS);
			else if (x == 2 && y == 5) G25.setIcon(HumanS);

			else if (x == 3 && y == 0) G30.setIcon(HumanS);
			else if (x == 3 && y == 1) G31.setIcon(HumanS);
			else if (x == 3 && y == 2) G32.setIcon(HumanS);
			else if (x == 3 && y == 3) G33.setIcon(HumanS);
			else if (x == 3 && y == 4) G34.setIcon(HumanS);
			else if (x == 3 && y == 5) G35.setIcon(HumanS);

			else if (x == 4 && y == 0) G40.setIcon(HumanS);		
			else if (x == 4 && y == 1) G41.setIcon(HumanS);
			else if (x == 4 && y == 2) G42.setIcon(HumanS);
			else if (x == 4 && y == 3) G43.setIcon(HumanS);
			else if (x == 4 && y == 4) G44.setIcon(HumanS);
			else if (x == 4 && y == 5) G45.setIcon(HumanS);

			else if (x == 5 && y == 1) G51.setIcon(HumanS);
			else if (x == 5 && y == 2) G52.setIcon(HumanS);
			else if (x == 5 && y == 3) G53.setIcon(HumanS);
			else if (x == 5 && y == 4) G54.setIcon(HumanS);
		}
		//EAST
		else if (Agent.direction == "EAST") {
			if (x == 0 && y == 1) G01.setIcon(HumanE);
			else if (x == 0 && y == 2) G02.setIcon(HumanE);
			else if (x == 0 && y == 3) G03.setIcon(HumanE);
			else if (x == 0 && y == 4) G04.setIcon(HumanE);
		
			else if (x == 1 && y == 0) G10.setIcon(HumanE);
			else if (x == 1 && y == 1)
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(HumanE);
			else if (x == 1 && y == 2) G12.setIcon(HumanE);
			else if (x == 1 && y == 3) G13.setIcon(HumanE);
			else if (x == 1 && y == 4) G14.setIcon(HumanE);
			else if (x == 1 && y == 5) G15.setIcon(HumanE);

			else if (x == 2 && y == 0) G20.setIcon(HumanE);
			else if (x == 2 && y == 1) G21.setIcon(HumanE);
			else if (x == 2 && y == 2) G22.setIcon(HumanE);
			else if (x == 2 && y == 3) G23.setIcon(HumanE);
			else if (x == 2 && y == 4) G24.setIcon(HumanE);
			else if (x == 2 && y == 5) G25.setIcon(HumanE);

			else if (x == 3 && y == 0) G30.setIcon(HumanE);
			else if (x == 3 && y == 1) G31.setIcon(HumanE);
			else if (x == 3 && y == 2) G32.setIcon(HumanE);
			else if (x == 3 && y == 3) G33.setIcon(HumanE);
			else if (x == 3 && y == 4) G34.setIcon(HumanE);
			else if (x == 3 && y == 5) G35.setIcon(HumanE);

			else if (x == 4 && y == 0) G40.setIcon(HumanE);		
			else if (x == 4 && y == 1) G41.setIcon(HumanE);
			else if (x == 4 && y == 2) G42.setIcon(HumanE);
			else if (x == 4 && y == 3) G43.setIcon(HumanE);
			else if (x == 4 && y == 4) G44.setIcon(HumanE);
			else if (x == 4 && y == 5) G45.setIcon(HumanE);

			else if (x == 5 && y == 1) G51.setIcon(HumanE);
			else if (x == 5 && y == 2) G52.setIcon(HumanE);
			else if (x == 5 && y == 3) G53.setIcon(HumanE);
			else if (x == 5 && y == 4) G54.setIcon(HumanE);
		}
		//WEST
		else if (Agent.direction == "WEST") {
			if (x == 0 && y == 1) G01.setIcon(HumanW);
			else if (x == 0 && y == 2) G02.setIcon(HumanW);
			else if (x == 0 && y == 3) G03.setIcon(HumanW);
			else if (x == 0 && y == 4) G04.setIcon(HumanW);
		
			else if (x == 1 && y == 0) G10.setIcon(HumanW);
			else if (x == 1 && y == 1)
				if (climb.nextXY[0] == 1 && climb.nextXY[1] == 1) G11.setIcon(Finish);
				else G11.setIcon(HumanW);
			else if (x == 1 && y == 2) G12.setIcon(HumanW);
			else if (x == 1 && y == 3) G13.setIcon(HumanW);
			else if (x == 1 && y == 4) G14.setIcon(HumanW);
			else if (x == 1 && y == 5) G15.setIcon(HumanW);

			else if (x == 2 && y == 0) G20.setIcon(HumanW);
			else if (x == 2 && y == 1) G21.setIcon(HumanW);
			else if (x == 2 && y == 2) G22.setIcon(HumanW);
			else if (x == 2 && y == 3) G23.setIcon(HumanW);
			else if (x == 2 && y == 4) G24.setIcon(HumanW);
			else if (x == 2 && y == 5) G25.setIcon(HumanW);
		
			else if (x == 3 && y == 0) G30.setIcon(HumanW);
			else if (x == 3 && y == 1) G31.setIcon(HumanW);
			else if (x == 3 && y == 2) G32.setIcon(HumanW);
			else if (x == 3 && y == 3) G33.setIcon(HumanW);
			else if (x == 3 && y == 4) G34.setIcon(HumanW);
			else if (x == 3 && y == 5) G35.setIcon(HumanW);
		
			else if (x == 4 && y == 0) G40.setIcon(HumanW);		
			else if (x == 4 && y == 1) G41.setIcon(HumanW);
			else if (x == 4 && y == 2) G42.setIcon(HumanW);
			else if (x == 4 && y == 3) G43.setIcon(HumanW);
			else if (x == 4 && y == 4) G44.setIcon(HumanW);
			else if (x == 4 && y == 5) G45.setIcon(HumanW);

			else if (x == 5 && y == 1) G51.setIcon(HumanW);
			else if (x == 5 && y == 2) G52.setIcon(HumanW);
			else if (x == 5 && y == 3) G53.setIcon(HumanW);
			else if (x == 5 && y == 4) G54.setIcon(HumanW);
		}
	}
}
