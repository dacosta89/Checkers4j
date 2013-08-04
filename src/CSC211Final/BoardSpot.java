package CSC211Final;

/**
 * 
 * @author Derrick DaCosta
 * CSC211 Final Project
 *
 */

import java.awt.Color;
import java.awt.Graphics;


public class BoardSpot {

	/**
	 * Integer that holds the row number
	 */
	private int rowNum;
	/**
	 * Integer that holds the column number
	 */
	private int columnNum;
	/**
	 * Boolean that holds the state, true = black, false = white
	 */
	private boolean state;
	/**
	 * Boolean that holds the state, true = pressed, false = not pressed
	 */
	private boolean pressed;
	/**
	 * Integer that holds the square size
	 */
	private int sqrSize;
	/**
	 * Integer that holds the value where to start the x coordinate for drawing
	 */
	private int startX;
	/**
	 * Integer that holds the value where to start the y coordinate for drawing
	 */
	private int startY;
	/**
	 * Color that holds the playable block color
	 */
	private Color gColor;
	/**
	 * Color that holds player 1's piece color
	 */
	private Color bColor;
	/**
	 * Color that holds player 2's piece color
	 */
	private Color rColor;
	/**
	 * Checker that holds the current checker in that BoardSpot, null if there is no checker
	 */
	private Checker check;
	
	/**
	 * Constructor for the BoardSpot class
	 * @param r variable for the row value
	 * @param c variable for the column value
	 * @param s variable for the boolean state value
	 * @param p variable for the boolean pressed value
	 * @param sqS variable for the square size value
	 * @param sX variable for the start X value
	 * @param sY variable for the start Y value
	 * @param ch variable of type Checker for the Checker value
	 * @param col variable for the grid color value
	 * @param bC variable for player 1 color value
	 * @param rC variable for player 2 color value
	 */
	public BoardSpot(int r, int c, boolean s, boolean p, int sqS, int sX, int sY, Checker ch, Color col, Color bC, Color rC) {
		
		rowNum = r;
		columnNum = c;
		state = s;
		pressed = p;
		sqrSize = sqS;
		startX = sX;
		startY = sY;
		check = ch;
		gColor = col;
		bColor = bC;
		rColor = rC;

	}
	
	/**
	 * Method that returns the checker, null if there is no checker on the BoardSpot
	 * @return returns the checker, null if there is no checker on the BoardSpot
	 */
	public Checker getChecker() {
		return check;
	}
	/**
	 * Method that returns the row number of that grid block
	 * @return returns the row number of that grid block
	 */
	public int getRow() {
		return rowNum;
	}
	
	/**
	 * Method that returns the column number of that grid block
	 * @return returns the column number of that grid block
	 */
	public int getColumn() {
		return columnNum;
	}
	
	/**
	 * Method that returns true if state is black and false if state is white
	 * @return returns true if state is black and false if state is white
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Method that returns true if the BoardSpot is pressed and false otherwise
	 * @return returns true if the BoardSpot is pressed and false otherwise
	 */
	public boolean getPressed() {
		return pressed;
	}
	
	/**
	 * Method that returns the square size of that grid block
	 * @return returns the square size of that grid block
	 */
	public int getSqrSize() {
		return sqrSize;
	}
	
	/**
	 * Method that returns the start x coordinate for drawing
	 * @return returns the start x coordinate for drawing
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * Method that returns the start y coordinate for drawing
	 * @return returns the start y coordinate for drawing
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * Method that sets the checker to the BoardSpot
	 * @param ch Checker sent through as the parameter
	 */
	public void setChecker(Checker ch) {
		check = ch;
	}
	
	/**
	 * Method that sets the row number of that grid block
	 * @param r variable the row number is set to
	 */
	public void setRow(int r) {
		rowNum = r;
	}
	
	/**
	 * Method that sets the column number of that grid block
	 * @param c variable the column number is set to
	 */
	public void setColumn(int c) {
		columnNum = c;
	}
	
	/**
	 * Method that sets the state of that grid block
	 * @param s variable the state is set to
	 */
	public void setState(boolean s) {
		state = s;
	}
	
	/**
	 * Method that sets whether or not the BoardSpot has been pressed or not
	 * @param p true if pressed, false if not pressed
	 */
	public void setPressed(boolean p) {
		pressed = p;
	}
	
	/**
	 * Method that sets the square size of that grid block
	 * @param s variable the square size is set to
	 */
	public void setSqrSize(int s) {
		sqrSize = s;
	}
	
	/**
	 * Method that sets the start x coordinate for drawing of that grid block
	 * @param a variable the start x coordinate is set to
	 */
	public void setStartX(int a) {
		startX = a;
	}
	
	/**
	 * Method that sets the start y coordinate for drawing of that grid block
	 * @param a variable the start y coordinate is set to
	 */
	public void setStartY(int a) {
		startY = a;
	}
	
	/**
	 * Method that sets the Color of the playable BoardSpots
	 * @param c Color to be changed to
	 */
	public void setGColor(Color c) {
		gColor = c;
	}
	/**
	 * Method that sets the Color of player 1's pieces
	 * @param c Color to be changed to
	 */
	public void setBColor(Color c) {
		bColor = c;
	}
	/**
	 * Method that sets the Color of player 2's pieces
	 * @param c Color to be changed to
	 */
	public void setRColor(Color c) {
		rColor = c;
	}
	
	/**
	 * Method that draws all the BoardSpots on the 2D grid, it also draws the checkers if one resides on
	 * a BoardSpot
	 * @param g Graphics object used for calling Graphics methods
	 * @param gW variable that holds the total window width
	 * @param gH variable that holds the total window height
	 */
	public void draw(Graphics g, int gW, int gH) {
		if(!state) {
			g.setColor(Color.WHITE);
			g.fillRect(startX, startY, gW/8, gH/8);
			g.setColor(Color.GRAY);
			g.drawRect(startX, startY, gW/8, gH/8);
		}
		else if(state) {
			if(pressed) {
				g.setColor(Color.RED);
				g.fillRect(startX, startY, (gW/8), (gH/8));
			}
			g.setColor(gColor);
			g.fillRect(startX+(gW/192), startY+(gW/192), gW/8-(gW/110), gH/8-(gH/110));
			g.setColor(Color.GRAY);
			g.drawRect(startX, startY, gW/8, gH/8);
		}
		if(check != null && check.getType() == 0) {
			if(!check.getKing()) {
				g.setColor(rColor);
				g.fillOval(startX+(gW/50), startY+(gH/50), gW/12, gH/12);
			}
			else {
				g.setColor(rColor);
				g.fillOval(startX+(gW/50), startY+(gH/50), gW/12, gH/12);
				g.setColor(Color.BLACK);
				g.drawLine(startX+(gW/50)+18, startY+(gH/50)+12, startX+(gW/50)+18, startY+(gH/50)+37);
				g.drawLine(startX+(gW/50)+37, startY+(gH/50)+12, startX+(gW/50)+18, startY+(gH/50)+25);
				g.drawLine(startX+(gW/50)+18, startY+(gH/50)+25, startX+(gW/50)+37, startY+(gH/50)+37);
			}
		}
		else if(check != null && check.getType() == 1) {
			if(!check.getKing()) {
				g.setColor(bColor);
				g.fillOval(startX+(gW/50), startY+(gH/50), gW/12, gH/12);
			}
			else {
				g.setColor(bColor);
				g.fillOval(startX+(gW/50), startY+(gH/50), gW/12, gH/12);
				g.setColor(Color.BLACK);
				g.drawLine(startX+(gW/50)+18, startY+(gH/50)+12, startX+(gW/50)+18, startY+(gH/50)+37);
				g.drawLine(startX+(gW/50)+37, startY+(gH/50)+12, startX+(gW/50)+18, startY+(gH/50)+25);
				g.drawLine(startX+(gW/50)+18, startY+(gH/50)+25, startX+(gW/50)+37, startY+(gH/50)+37);
			}
		}
			
	}

	/**
	 * Method that moves the checker from one BoardSpot to another
	 * @param ch Checker that is moved to this BoardSpot
	 */
	public void move(Checker ch) {
		check = ch;
	}
	
	/**
	 * Method that returns a full descriptions of the BoardSpot
	 * @return returns a full descriptions of the BoardSpot
	 */
	public String getDescription() {
		return ("Row: " + rowNum + " Column: " + columnNum + " State: " + state);
	}
}
