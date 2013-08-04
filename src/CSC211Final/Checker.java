package CSC211Final;

/**
 * 
 * @author Derrick DaCosta
 * CSC211 Final Project
 *
 */


import java.awt.Graphics;

import javax.swing.JFrame;



public abstract class Checker extends JFrame {
	
	/**
	 * Constructor for the Checker super class
	 */
	public Checker() {
		
	}
	
	/**
	 * Method that returns the Checker's id
	 * @return returns the Checker's id
	 */
	public abstract int getId();
	
	/**
	 * Method that returns the Checker's type 0 = red, 1 = black
	 * @return returns the Checker's type 0 = red, 1 = black
	 */
	public abstract int getType();
	
	/**
	 * Method that returns the row number of the checker
	 * @return returns the row number of the checker
	 */
	public abstract int getRow();
	
	/**
	 * Method that returns the column number of the checker
	 * @return returns the column number of the checker
	 */
	public abstract int getColumn();
	
	/**
	 * Method that returns true if piece is a king and false otherwise
	 * @return returns true if piece is a king and false otherwise
	 */
	public abstract boolean getKing();
	
	/**
	 * Method that sets the king value to true if piece has just become king
	 * @param k Parameter sent to determine the king
	 */
	public abstract void setKing(boolean k);
	
	/**
	 * Method that returns a full descriptions of the checker
	 * @return returns a full descriptions of the checker
	 */
	public abstract String getDescription();

}