package CSC211Final;

/**
 * 
 * @author Derrick DaCosta
 * CSC211 Final Project
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.io.*;

import javax.swing.JOptionPane;

public class CheckerBoard extends JPanel implements MouseListener, ActionListener {
	
	/**
	 * JFrame that is used for GUI
	 */
	private JFrame myFrame;
	/**
	 * JFrame that displays the configurations to perform
	 */
	private JFrame scoreFrame;
	/**
	 * JLabel for what configuration one is currently in
	 */
	private JLabel Player1score;
	/**
	 * JLabel for what configuration one is currently in
	 */
	private JLabel Player2score;
	/**
	 * String input value for the input file the user wants to read from
	 */
	private String inputFile = "";
	/**
	 * BufferReader type, what file we will read from
	 */
	BufferedReader	inFile = null;
	/**
	 * String input value for the output file the user wants to write to
	 */
	private String outputFile = "";
	/**
	 * PrintWriter type, what file we will write to
	 */
	PrintWriter		outFile = null; 
	/**
	 * 2D array of type BoardSpot where it holds the characteristics of each spot
	 */
	private BoardSpot[][] cBoard;
	/**
	 * temporary BoardSpot so it can fill up the BoardSpot array
	 */
	private BoardSpot temp;
	/**
	 * Temporary BoardSpot to hold information about the previously click spot
	 */
	private BoardSpot temp2;
	/**
	 * Temporary black checker to be added to a BoardSpot
	 */
	private Checker blacktemp;
	/**
	 * Temporary red checker to be added to a BoardSpot
	 */
	private Checker redtemp;
	/**
	 * Default grid color for when the game starts
	 */
	private Color gColor = Color.BLACK;
	/**
	 * Default player 1 color for when the game starts
	 */
	private Color bColor = Color.CYAN;
	/**
	 * Default player 2 color for when the game starts
	 */
	private Color rColor = Color.RED;
	/**
	 * Boolean flag to initialize drawing the board when the program first executes
	 */
	private boolean begin = true;
	/**
	 * Boolean flag used for when drawing the board it alternates between colors each row
	 * to give it the checkered look
	 */
	private boolean even = true;
	/**
	 * Boolean flag that allows for turns of each player, player 1 has to move first
	 */
	private boolean p1 = true;
	/**
	 * Boolean flag that allows for turns of each player, player 2 has to wait until player 1 moves
	 */
	private boolean p2 = false;
	/**
	 * Integer that holds player 2's score
	 */
	private int RedScore = 0;
	/**
	 * Integer that holds player 1's score
	 */
	private int BlackScore = 0;
	/**
	 * Integer that holds the dimensions of the grid
	 */
	private int gDimen = 0;
	/**
	 * Integer that holds the square size of each block
	 */
	private int squareSize;
	/**
	 * Integer that holds the start X coordinate value for drawing
	 */
	private int startX = 0;
	/**
	 * Integer that holds the start X coordinate value for drawing
	 */
	private int startY = 0;
	/**
	 * JComboBox used to choosing the board colors and piece colors
	 */
	private JComboBox jcb;
	/**
	 * String array that holds the names of all the Colors
	 */
	static final String[] GRID_LIST = {"BLACK", "BLUE", "CYAN", "DARK_GRAY", "GRAY", "GREEN", "LIGHT_GRAY", "MAGENTA",
										"ORANGE", "PINK", "RED", "YELLOW"};
	/**
	 * JMenu that is used at the top of the JFrame
	 */
	private JMenu menu;
	/**
	 * String array that holds the names of a JMenuItem - "File"
	 */
	static final String[] ONE_MENU_ITEM_STR = {"New Game", "Save Game", "Load Game", "Quit"};
	/**
	 * String array that holds the names of a JMenuItem - "Edit"
	 */
	static final String[] TWO_MENU_ITEM_STR = {"Change Grid Color", "Change Player 1 Color", "Change Player 2 Color"};
	/**
	 * JMenuItem for File
	 */
	private JMenuItem item1[];
	/**
	 * JMenuItem for Edit
	 */
	private JMenuItem item2[];
	/**
	 * String array that holds the names of all the JMenus
	 */
	final String[] MENU_STR = {"File", "Edit"};
	
	/**
	 * Constructor for the CheckerBoard class
	 * @param theTitle Title of the JFrame
	 * @param theWidth Width of the JFrame
	 * @param theHeight Height of the JFrame
	 */
	public CheckerBoard(String theTitle, int theWidth, int theHeight) {		
		super();
		
		
		layoutSetup(theTitle, theWidth, theHeight);
		scoreFrame = new JFrame("Game Score");        //Declaration of the score frame object
		scoreFrame.setSize(300, 100);            //sets the size of the battery frame
		scoreFrame.setLocation(700, 0);           //sets the initial location of the frame
		scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreFrame.setLayout(new GridLayout(1, 2));
		Player1score = new JLabel("Player 1 Score: " + BlackScore);
		scoreFrame.add(Player1score);
		Player2score = new JLabel("Player 2 Score: " + RedScore);
		scoreFrame.add(Player2score);
		
		//2D array of checker pieces
		cBoard = new BoardSpot[8][8];
		
		addMouseListener(this);
		
		scoreFrame.setVisible(true);
		myFrame.setVisible(true);
		
	}

	/**
	 * Launcher class for the CheckerBoard class
	 * @param args string of arguments
	 */
	public static void main(String[] args) {
		CheckerBoard game1 = new CheckerBoard("The Game of Checkers", 650, 700);	
	}
		
	/**
	 * Method that sets up the JFrame
	 * @param theTitle Title of the JFrame
	 * @param theWidth Width of the JFrame
	 * @param theHeight Height of the JFrame
	 */
	private void layoutSetup(String theTitle, int theWidth, int theHeight) {
		myFrame = new JFrame(theTitle) {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				paintComponents(g);
			}
		};
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(theWidth, theHeight);
		myFrame.setLayout(new BorderLayout());
		myFrame.add(this, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();                //Initialization for the JMenuBar
		item1 = new JMenuItem[ONE_MENU_ITEM_STR.length];
		item2 = new JMenuItem[TWO_MENU_ITEM_STR.length];
		for (int i=0; i<MENU_STR.length; i++) {
			menu = new JMenu(MENU_STR[i]);
			if (i==0) {
				for(int j=0; j<ONE_MENU_ITEM_STR.length; j++){
					item1[j] = new JMenuItem(ONE_MENU_ITEM_STR[j]);
					menu.add(item1[j]);
					item1[j].addActionListener(this);
				}
			}
			if (i==1) {
				for(int j=0; j<TWO_MENU_ITEM_STR.length; j++){
					item2[j] = new JMenuItem(TWO_MENU_ITEM_STR[j]);
					menu.add(item2[j]);
					item2[j].addActionListener(this);
				}
			}
			menu.addActionListener(this);
			menuBar.add(menu);
		}
		
		myFrame.add(menuBar, BorderLayout.NORTH);
		
	}

	
	
	
	
	/**
	 * Method that paints the JPanels and JFrame according the the proper grid blocks and user clicks
	 */
	public void paintComponent(Graphics g) {
		startX=0;
		startY=0;
		squareSize = getWidth()/8;
		
		if(begin) {
			for(int i=0; i<cBoard.length; i++) {                       //Row
				for(int j=0; j<cBoard.length; j++) {                  //Column
					if(even) {
						if(j%2==0) {
							temp = new BoardSpot(i,j,true,false,squareSize,startX,startY, null, gColor, bColor, rColor);
							cBoard[i][j] = temp;	
							if(i>=0 && i<=2) {
								blacktemp = new BlackChecker(i,j, false);
								cBoard[i][j].setChecker(blacktemp);
							}
							else if(i>=5 && i<=7) {   //the bottom columns are for the red pieces
								redtemp = new RedChecker(i,j, false);
								cBoard[i][j].setChecker(redtemp);
							}   
						}
						else {
							temp = new BoardSpot(i,j,false,false,squareSize,startX,startY, null, gColor, bColor, rColor);
							cBoard[i][j] = temp;
						}
					}
					if(!even){
						if(j%2==0) {
							temp = new BoardSpot(i,j,false,false,squareSize,startX,startY, null, gColor, bColor, rColor);
							cBoard[i][j] = temp;
						}
						else {
							temp = new BoardSpot(i,j,true,false,squareSize,startX,startY, null, gColor, bColor, rColor);
							cBoard[i][j] = temp;
							if(i>=0 && i<=2) {
								blacktemp = new BlackChecker(i,j, false);
								cBoard[i][j].setChecker(blacktemp);
							}
							else if(i>=5 && i<=7) {       //the bottom columns are for the red pieces
								redtemp = new RedChecker(i,j, false);
								cBoard[i][j].setChecker(redtemp);
							}   
						}
					}
					startX += squareSize;
				}
				even = !even;
				startX = 0;
				startY += squareSize;
			}
			begin = false;
		}
		else {
			for(int i=0; i<cBoard.length; i++) {                       //Row
				for(int j=0; j<cBoard.length; j++) {                  //Column
					cBoard[i][j].setSqrSize(squareSize);
					cBoard[i][j].setStartX(startX);
					cBoard[i][j].setStartY(startY);
					startX += squareSize;
				}
				startX = 0;
				startY += squareSize;
			}  
		} 
		
		for(int i=0; i<cBoard.length; i++) {                       //Row
			for(int j=0; j<cBoard.length; j++) {                  //Column
				cBoard[i][j].draw(g, getWidth(), getHeight());
			}
		}
		
	}

	/**
	 * Method that listens to the actions performed for the JMenuBar 
	 */
	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();
		
		if(obj==item1[0]) {            //File->New Game was pressed
			begin = true;
			BlackScore = 0;
			RedScore = 0;
			Player1score.setText("Player 1 Score: " + BlackScore);
			Player2score.setText("Player 2 Score: " + RedScore);
			repaint();
		}
		if(obj==item1[1]) {            //File->Save Game was pressed			
			outputFile = JOptionPane.showInputDialog(null, "Please enter the name of the file you would like to write to");
			
			try {
				if(outputFile != null) {
					outFile = new PrintWriter(new FileWriter(outputFile));
				}
	        }
	        catch (IOException e) {
				JOptionPane.showMessageDialog(null, "IO error", "Error opening output file" + outputFile,
								JOptionPane.ERROR_MESSAGE);
	        }
	        
	        if(outputFile != null) {
	        	outFile.println(cBoard.length);
		        outFile.println(BlackScore);
		        outFile.println(RedScore);
		        outFile.println(gColor);
		        outFile.println(bColor);
		        outFile.println(rColor);
		        for(int i=0; i<cBoard.length; i++) {           //Row
					for(int j=0; j<cBoard.length; j++) {      //Column
						outFile.println(cBoard[i][j].getRow());
						outFile.println(cBoard[i][j].getColumn());
						outFile.println(cBoard[i][j].getState());
						outFile.println(cBoard[i][j].getPressed());
						outFile.println(cBoard[i][j].getSqrSize());
						outFile.println(cBoard[i][j].getStartX());
						outFile.println(cBoard[i][j].getStartY());
						outFile.println(cBoard[i][j].getChecker());
						if(cBoard[i][j].getChecker()!=null) {
							outFile.println(cBoard[i][j].getChecker().getRow());
							outFile.println(cBoard[i][j].getChecker().getColumn());
							outFile.println(cBoard[i][j].getChecker().getType());
							outFile.println(cBoard[i][j].getChecker().getKing());
						}
						else {
							outFile.println("null");
							outFile.println("null");
							outFile.println("null");
							outFile.println("null");
						}
					}
				}
				
				// close the output file
				outFile.close();
	        }
		}
		
		else if(obj==item1[2]) {       //File->Load Game was pressed
			String dimen="", bScore="", rScore="", gridColor="", blkColor="", rdColor="";
			String gRow="", gColumn="",c_gRow="", c_gColumn="", c_gType="", gState="", gPressed="", gKing="", gSqrSize="", gStartX="", gStartY="", gChecker="";
			int bScore_=0, rScore_=0, gRow_=0, c_gRow_=0, c_gColumn_=0, c_gType_=0, gColumn_=0, gSqrSize_=0, gStartX_=0, gStartY_=0;
			Color gridColor_, blkColor_, rdColor_;
			Checker gChecker_= null;
			boolean gState_, gPressed_, gKing_;
			int i=0 ,j=0;
	
			inputFile = JOptionPane.showInputDialog(null, "Please enter the name of the file you would like to read from");
			
			try {
				if(inputFile != null) {
					inFile = new BufferedReader(new FileReader(inputFile));
				}
			}
			catch (FileNotFoundException theException) {
				JOptionPane.showMessageDialog(null, "IO error", "No such file!" + inputFile,
								JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			
			if(inputFile != null) {
				try {
				    dimen = inFile.readLine();
				    gDimen = Integer.parseInt(dimen);
				    bScore = inFile.readLine();
				    bScore_ = Integer.parseInt(bScore);
				    rScore = inFile.readLine();
				    rScore_ = Integer.parseInt(rScore);
				    //reads the grid color
				    gridColor = inFile.readLine();
				    String s = gridColor,r,g,b;
				    r = s.substring(s.indexOf("=")+1,s.indexOf(","));
					int r_ = Integer.parseInt(r);
					g = s.substring(s.indexOf(",")+1,s.length()-1);
					g = g.substring(g.indexOf("=")+1, g.indexOf(","));
					int g_ = Integer.parseInt(g);
					b = s.substring(s.lastIndexOf("=")+1,s.indexOf("]"));
					int b_ = Integer.parseInt(b);
					Color c = new Color(r_,g_,b_);
				    gridColor_ = c;				
				    //reads player 1's color
				    blkColor = inFile.readLine();
				    s = blkColor;
				    r = s.substring(s.indexOf("=")+1,s.indexOf(","));
					r_ = Integer.parseInt(r);
					g = s.substring(s.indexOf(",")+1,s.length()-1);
					g = g.substring(g.indexOf("=")+1, g.indexOf(","));
					g_ = Integer.parseInt(g);
					b = s.substring(s.lastIndexOf("=")+1,s.indexOf("]"));
					b_ = Integer.parseInt(b);
					c = new Color(r_,g_,b_);
				    blkColor_ = c;			
				    //reads player 2's color
				    rdColor = inFile.readLine();
				    s = rdColor;
				    r = s.substring(s.indexOf("=")+1,s.indexOf(","));
					r_ = Integer.parseInt(r);
					g = s.substring(s.indexOf(",")+1,s.length()-1);
					g = g.substring(g.indexOf("=")+1, g.indexOf(","));
					g_ = Integer.parseInt(g);
					b = s.substring(s.lastIndexOf("=")+1,s.indexOf("]"));
					b_ = Integer.parseInt(b);
					c = new Color(r_,g_,b_);
				    rdColor_ = c;
				    cBoard = new BoardSpot[gDimen][gDimen];
				    for(int k=0; k<gDimen*gDimen; k++) {
				    	gRow = inFile.readLine();
				    	gRow_ = Integer.parseInt(gRow);
				    	gColumn = inFile.readLine();
				    	gColumn_ = Integer.parseInt(gColumn);
				    	gState = inFile.readLine();
				    	gState_ = Boolean.parseBoolean(gState);
				    	gPressed = inFile.readLine();
				    	gPressed_ = Boolean.parseBoolean(gPressed);
				    	gSqrSize = inFile.readLine();
				    	gSqrSize_ = Integer.parseInt(gSqrSize);
				    	gStartX = inFile.readLine();
				    	gStartX_ = Integer.parseInt(gStartX);
				    	gStartY = inFile.readLine();
				    	gStartY_ = Integer.parseInt(gStartY);
				    	gChecker = inFile.readLine();
				    	if(!gChecker.equals("null")) {
				    		c_gRow = inFile.readLine();
				    		c_gRow_ = Integer.parseInt(c_gRow);
				    		c_gColumn = inFile.readLine();
				    		c_gColumn_ = Integer.parseInt(c_gColumn);
				    		c_gType = inFile.readLine();
				    		c_gType_ = Integer.parseInt(c_gType);
				    		gKing = inFile.readLine();
					    	gKing_ = Boolean.parseBoolean(gKing);
				    		if(c_gType_ == 0) {
				    			gChecker_ = new RedChecker(c_gRow_, c_gColumn_,gKing_);
				    		}
				    		else {
				    			gChecker_ = new BlackChecker(c_gRow_, c_gColumn_,gKing_);
				    		}
				    	}
				    	else {
				    		inFile.readLine();
				    		inFile.readLine();
				    		inFile.readLine();
				    		inFile.readLine();
				    	}
				    	temp = new BoardSpot(gRow_,gColumn_,gState_,gPressed_,gSqrSize_,gStartX_,gStartY_,gChecker_,gridColor_, blkColor_, rdColor_);
				    	gChecker_ = null;
				    	cBoard[i][j] = temp;
				    	j++;
				    	if(j>(gDimen)-1) {
				    		j = 0;
				    		i++;
				    	}
				    }
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, "IO error", "Error Reading the File",
									JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				BlackScore = bScore_;
				RedScore = rScore_;
				Player1score.setText("Player 1 Score: " + bScore_);
				Player2score.setText("Player 2 Score: " + rScore_);
				repaint();
			}
		}
		
		else if(obj==item1[3]) {                 //File->Quit was pressed
			System.exit(0);
		}
		
		else if(obj==item2[0]) {                 //Edit->Change Grid Color was pressed
			String a = "";
			a = (String)JOptionPane.showInputDialog(jcb, "Change Grid Color", "Grid Color", JOptionPane.QUESTION_MESSAGE, null, GRID_LIST, GRID_LIST[0]);
			if(a != null) {
				if(a.equals("BLACK")) {
					gColor = Color.BLACK;
				}
				else if(a.equals("BLUE")) {
					gColor = Color.BLUE;
				}
				else if(a.equals("CYAN")) {
					gColor = Color.CYAN;
				}
				else if(a.equals("DARK_GRAY")) {
					gColor = Color.DARK_GRAY;
				}
				else if(a.equals("GRAY")) {
					gColor = Color.GRAY;
				}
				else if(a.equals("GREEN")) {
					gColor = Color.GREEN;
				}
				else if(a.equals("LIGHT_GRAY")) {
					gColor = Color.LIGHT_GRAY;
				}
				else if(a.equals("MAGENTA")) {
					gColor = Color.MAGENTA;
				}
				else if(a.equals("ORANGE")) {
					gColor = Color.ORANGE;
				}
				else if(a.equals("PINK")) {
					gColor = Color.PINK;
				}
				else if(a.equals("RED")) {
					gColor = Color.RED;
				}
				else if(a.equals("YELLOW")) {
					gColor = Color.YELLOW;
				}
			}
			
			for(int i=0; i<cBoard.length; i++) {
				for(int j=0; j<cBoard.length; j++) {
					cBoard[i][j].setGColor(gColor);
				}
			}
			repaint();
		}
		
		else if(obj==item2[1]) {                 //Edit->Change Player 1 Color was pressed
			String a = "";
			a = (String)JOptionPane.showInputDialog(jcb, "Change Player 1 Color", "Player 1 Color", JOptionPane.QUESTION_MESSAGE, null, GRID_LIST, GRID_LIST[2]);
			if(a != null) {
				if(a.equals("BLACK")) {
					bColor = Color.BLACK;
				}
				else if(a.equals("BLUE")) {
					bColor = Color.BLUE;
				}
				else if(a.equals("CYAN")) {
					bColor = Color.CYAN;
				}
				else if(a.equals("DARK_GRAY")) {
					bColor = Color.DARK_GRAY;
				}
				else if(a.equals("GRAY")) {
					bColor = Color.GRAY;
				}
				else if(a.equals("GREEN")) {
					bColor = Color.GREEN;
				}
				else if(a.equals("LIGHT_GRAY")) {
					bColor = Color.LIGHT_GRAY;
				}
				else if(a.equals("MAGENTA")) {
					bColor = Color.MAGENTA;
				}
				else if(a.equals("ORANGE")) {
					bColor = Color.ORANGE;
				}
				else if(a.equals("PINK")) {
					bColor = Color.PINK;
				}
				else if(a.equals("RED")) {
					bColor = Color.RED;
				}
				else if(a.equals("YELLOW")) {
					bColor = Color.YELLOW;
				}
			}
			
			for(int i=0; i<cBoard.length; i++) {
				for(int j=0; j<cBoard.length; j++) {
					cBoard[i][j].setBColor(bColor);
				}
			}
			repaint();
		}
		
		else if(obj==item2[2]) {                 //Edit->Change Player 2 Color was pressed
			String a = "";
			a = (String)JOptionPane.showInputDialog(jcb, "Change Player 2 Color", "Player 2 Color", JOptionPane.QUESTION_MESSAGE, null, GRID_LIST, GRID_LIST[10]);
			if(a != null) {
				if(a.equals("BLACK")) {
					rColor = Color.BLACK;
				}
				else if(a.equals("BLUE")) {
					rColor = Color.BLUE;
				}
				else if(a.equals("CYAN")) {
					rColor = Color.CYAN;
				}
				else if(a.equals("DARK_GRAY")) {
					rColor = Color.DARK_GRAY;
				}
				else if(a.equals("GRAY")) {
					rColor = Color.GRAY;
				}
				else if(a.equals("GREEN")) {
					rColor = Color.GREEN;
				}
				else if(a.equals("LIGHT_GRAY")) {
					rColor = Color.LIGHT_GRAY;
				}
				else if(a.equals("MAGENTA")) {
					rColor = Color.MAGENTA;
				}
				else if(a.equals("ORANGE")) {
					rColor = Color.ORANGE;
				}
				else if(a.equals("PINK")) {
					rColor = Color.PINK;
				}
				else if(a.equals("RED")) {
					rColor = Color.RED;
				}
				else if(a.equals("YELLOW")) {
					rColor = Color.YELLOW;
				}
			}
			
			for(int i=0; i<cBoard.length; i++) {
				for(int j=0; j<cBoard.length; j++) {
					cBoard[i][j].setRColor(rColor);
				}
			}
			repaint();
		}
	}

	/**
	 * Method that listens to the actions performed by the mouse clicks and calls the proper functions
	 */
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(),
			y = e.getY();
	
		//Finds the exact grid block the user has clicked on
		for(int i=0; i<cBoard.length; i++) {
			for(int j=0; j<cBoard.length; j++) {
				if((x>cBoard[i][j].getStartX() && x<(cBoard[i][j].getStartX()+cBoard[i][j].getSqrSize())) && (y>cBoard[i][j].getStartY() && y<(cBoard[i][j].getStartY()+cBoard[i][j].getSqrSize()))) {
					// This is for the black checkers pressed
					
					//King piece movable spots
					if(temp2 != null && p1 && temp2.getPressed() && temp2.getChecker() != null && temp2.getChecker().getType() == 1 && temp2.getChecker().getKing()) {
						if(cBoard[i][j].getState() && cBoard[i][j].getChecker() == null && (temp2.getRow() == cBoard[i][j].getRow()-1  && temp2.getColumn() == cBoard[i][j].getColumn()+1 ||  // top right
								                                                            temp2.getRow() == cBoard[i][j].getRow()-1  && temp2.getColumn() == cBoard[i][j].getColumn()-1 ||  //top left
								                                                            temp2.getRow() == cBoard[i][j].getRow()+1  && temp2.getColumn() == cBoard[i][j].getColumn()+1 ||  //bottom right
																							temp2.getRow() == cBoard[i][j].getRow()+1  && temp2.getColumn() == cBoard[i][j].getColumn()-1)) { //bottom left
							cBoard[i][j].move(temp2.getChecker());  //moves checker
							temp2.setChecker(null);
							p1 = false;
							p2 = true;
							repaint();
						}
					}
					//Normal piece movable spots
					if(p1 && temp2 != null && temp2.getPressed() && temp2.getChecker() != null && temp2.getChecker().getType() == 1) {
						if(cBoard[i][j].getState() && cBoard[i][j].getChecker() == null && (temp2.getRow() == cBoard[i][j].getRow()-1  && temp2.getColumn() == cBoard[i][j].getColumn()+1 ||  // top right
								                                                            temp2.getRow() == cBoard[i][j].getRow()-1  && temp2.getColumn() == cBoard[i][j].getColumn()-1)) { //top left
							cBoard[i][j].move(temp2.getChecker());  //moves checker
							if(cBoard[i][j].getRow()==7) {
								cBoard[i][j].getChecker().setKing(true);
							}
							temp2.setChecker(null);
							p1 = false;
							p2 = true;
							repaint();
						}
					}
					//allows multiple jumps to happen
					if(temp2 != null && temp2.getPressed() && temp2.getChecker() != null && temp2.getChecker().getType() == 1) {
						if(cBoard[i][j].getState() && cBoard[i][j].getChecker() == null) {
							if((temp2.getRow()+1 < 8 && temp2.getColumn()-1 >= 0) && cBoard[temp2.getRow()+1][temp2.getColumn()-1].getChecker() != null && cBoard[temp2.getRow()+1][temp2.getColumn()-1].getChecker().getType() == 0 &&    //bottom left is opponent piece(red))
								temp2.getRow() == cBoard[i][j].getRow()-2  && temp2.getColumn() == cBoard[i][j].getColumn()+2){   // top right
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==7) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()+1][temp2.getColumn()-1].setChecker(null);   //removes checker from board
								BlackScore++;                                                     //increments score.
								Player1score.setText("Player 1 Score: " + BlackScore);
								temp2 = null;
								p1 = false;
								p2 = true;
								repaint();
							}
							else if((temp2.getRow()+1 < 8 && temp2.getColumn()+1 < 8) && cBoard[temp2.getRow()+1][temp2.getColumn()+1].getChecker() != null && cBoard[temp2.getRow()+1][temp2.getColumn()+1].getChecker().getType() == 0 &&    //bottom right is opponent piece(red))
									 temp2.getRow() == cBoard[i][j].getRow()-2  && temp2.getColumn() == cBoard[i][j].getColumn()-2) {   // top left
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==7) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()+1][temp2.getColumn()+1].setChecker(null); //removes checker from board
								BlackScore++;                                                  //increments score.
								Player1score.setText("Player 1 Score: " + BlackScore);
								temp2 = null;
								p1 = false;
								p2 = true;
								repaint();
							}
							else if((temp2.getRow()-1 >= 0 && temp2.getColumn()-1 >= 0) && cBoard[temp2.getRow()-1][temp2.getColumn()-1].getChecker() != null && cBoard[temp2.getRow()-1][temp2.getColumn()-1].getChecker().getType() == 0 &&    //top left is opponent piece(red))
									temp2.getRow() == cBoard[i][j].getRow()+2  && temp2.getColumn() == cBoard[i][j].getColumn()+2) {  // bottom right
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==7) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()-1][temp2.getColumn()-1].setChecker(null); //removes checker from board
								BlackScore++;                                                  //increments score.
								Player1score.setText("Player 1 Score: " + BlackScore);
								temp2 = null;
								p1 = false;
								p2 = true;
								repaint();
							}
							else if((temp2.getRow()-1 >= 0 && temp2.getColumn()+1 < 8) && cBoard[temp2.getRow()-1][temp2.getColumn()+1].getChecker() != null && cBoard[temp2.getRow()-1][temp2.getColumn()+1].getChecker().getType() == 0 &&    //top right is opponent piece(red))
									temp2.getRow() == cBoard[i][j].getRow()+2  && temp2.getColumn() == cBoard[i][j].getColumn()-2) { // bottom left
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==7) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()-1][temp2.getColumn()+1].setChecker(null); //removes checker from board
								BlackScore++;                                                  //increments score.
								Player1score.setText("Player 1 Score: " + BlackScore);
								temp2 = null;
								p1 = false;
								p2 = true;
								repaint();
							}
						}
					}
					// This is for the red checkers pressed
					
					//King piece movable spots
					else if(p2 && temp2 != null && temp2.getPressed() && temp2.getChecker() != null && temp2.getChecker().getType() == 0 && temp2.getChecker().getKing()) {
						if(cBoard[i][j].getState() && cBoard[i][j].getChecker() == null && (temp2.getRow() == cBoard[i][j].getRow()-1  && temp2.getColumn() == cBoard[i][j].getColumn()+1 ||  // top right
								                                                            temp2.getRow() == cBoard[i][j].getRow()-1  && temp2.getColumn() == cBoard[i][j].getColumn()-1 ||  //top left
								                                                            temp2.getRow() == cBoard[i][j].getRow()+1  && temp2.getColumn() == cBoard[i][j].getColumn()+1 ||  //bottom right
																							temp2.getRow() == cBoard[i][j].getRow()+1  && temp2.getColumn() == cBoard[i][j].getColumn()-1)) { //bottom left
							cBoard[i][j].move(temp2.getChecker());  //moves checker
							temp2.setChecker(null);
							p1 = true;
							p2 = false;
							repaint();
						}
					}
					
					//Normal piece movable spots
					else if(p2 && temp2 != null && temp2.getPressed() && temp2.getChecker() != null && temp2.getChecker().getType() == 0) {
						if(cBoard[i][j].getState() && cBoard[i][j].getChecker() == null && (temp2.getRow() == cBoard[i][j].getRow()+1  && temp2.getColumn() == cBoard[i][j].getColumn()+1 ||  // bottom right
								                                                            temp2.getRow() == cBoard[i][j].getRow()+1  && temp2.getColumn() == cBoard[i][j].getColumn()-1)) { //bottom left

							cBoard[i][j].move(temp2.getChecker());  //moves checker
							if(cBoard[i][j].getRow()==0) {
								cBoard[i][j].getChecker().setKing(true);
							}
							temp2.setChecker(null);
							p1 = true;
							p2 = false;
							repaint();
						}
					}
					//allows for multiple jumps to happen
					if(temp2 != null && temp2.getPressed() && temp2.getChecker() != null && temp2.getChecker().getType() == 0) {
						if(cBoard[i][j].getState() && cBoard[i][j].getChecker() == null) {
							if((temp2.getRow()+1 < 8 && temp2.getColumn()-1 >= 0) && cBoard[temp2.getRow()+1][temp2.getColumn()-1].getChecker() != null && cBoard[temp2.getRow()+1][temp2.getColumn()-1].getChecker().getType() == 1 &&    //bottom left is opponent piece(black))
									temp2.getRow() == cBoard[i][j].getRow()-2  && temp2.getColumn() == cBoard[i][j].getColumn()+2) {  // top right
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==0) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()+1][temp2.getColumn()-1].setChecker(null); //removes checker from board
								RedScore++;                                                  //increments score.
								Player2score.setText("Player 2 Score: " + RedScore);
								temp2 = null;
								p1 = true;
								p2 = false;
								repaint();
							}
							else if((temp2.getRow()+1 < 8 && temp2.getColumn()+1 < 8) && cBoard[temp2.getRow()+1][temp2.getColumn()+1].getChecker() != null && cBoard[temp2.getRow()+1][temp2.getColumn()+1].getChecker().getType() == 1 &&    //bottom right is opponent piece(black))
									 temp2.getRow() == cBoard[i][j].getRow()-2  && temp2.getColumn() == cBoard[i][j].getColumn()-2) {  // top left
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==0) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()+1][temp2.getColumn()+1].setChecker(null); //removes checker from board
								RedScore++;                                                  //increments score.
								Player2score.setText("Player 2 Score: " + RedScore);
								temp2 = null;
								p1 = true;
								p2 = false;
								repaint();
							}
							else if((temp2.getRow()-1 >= 0 && temp2.getColumn()-1 >= 0) && cBoard[temp2.getRow()-1][temp2.getColumn()-1].getChecker() != null && cBoard[temp2.getRow()-1][temp2.getColumn()-1].getChecker().getType() == 1 &&    //top left is opponent piece(black))
									 temp2.getRow() == cBoard[i][j].getRow()+2  && temp2.getColumn() == cBoard[i][j].getColumn()+2) {  // bottom right
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==0) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()-1][temp2.getColumn()-1].setChecker(null); //removes checker from board
								RedScore++;                                                  //increments score.
								Player2score.setText("Player 2 Score: " + RedScore);
								temp2 = null;
								p1 = true;
								p2 = false;
								repaint();
							}
							else if((temp2.getRow()-1 >= 0 && temp2.getColumn()+1 < 8) && cBoard[temp2.getRow()-1][temp2.getColumn()+1].getChecker() != null && cBoard[temp2.getRow()-1][temp2.getColumn()+1].getChecker().getType() == 1 &&    //top right is opponent piece(black))
									 temp2.getRow() == cBoard[i][j].getRow()+2  && temp2.getColumn() == cBoard[i][j].getColumn()-2) { // bottom left
								cBoard[i][j].move(temp2.getChecker());  //moves checker
								if(cBoard[i][j].getRow()==0) {
									cBoard[i][j].getChecker().setKing(true);
								}
								temp2.setPressed(false);
								temp2.setChecker(null);
								cBoard[temp2.getRow()-1][temp2.getColumn()+1].setChecker(null); //removes checker from board
								RedScore++;                                                  //increments score.
								Player2score.setText("Player 2 Score: " + RedScore);
								temp2 = null;
								p1 = true;
								p2 = false;
								repaint();
							}
						}
					}
					
					if(temp2!=null) {
						if(cBoard[i][j].getRow() != temp2.getRow() || cBoard[i][j].getColumn() != temp2.getColumn()) {
							temp2.setPressed(false);
							temp2 = cBoard[i][j];
							temp2.setPressed(true);
							repaint();
						}
					}
					else {
						temp2 = cBoard[i][j];
						temp2.setPressed(true);
						repaint();
					}  
				}
			}
		}
		
		/**
		 * Shows a message box of the winner when the game has ended
		 */
		if(BlackScore == 12) {
			JOptionPane.showMessageDialog(null, "Player 1 WINS!!!");
			begin = true;
			BlackScore = 0;
			RedScore = 0;
			Player1score.setText("Player 1 Score: " + BlackScore);
			Player2score.setText("Player 2 Score: " + RedScore);
			repaint();
		}
		else if(RedScore == 12) {
			JOptionPane.showMessageDialog(null, "Player 2 WINS!!!");
			begin = true;
			BlackScore = 0;
			RedScore = 0;
			Player1score.setText("Player 1 Score: " + BlackScore);
			Player2score.setText("Player 2 Score: " + RedScore);
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}