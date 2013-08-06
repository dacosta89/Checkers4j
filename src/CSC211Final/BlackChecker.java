package CSC211Final;

/**
 * 
 * @author Derrick DaCosta
 * CSC211 Final Project
 *
 */

public class BlackChecker extends Checker {
	
	/**
	 * Integer that holds the row number
	 */
	private int rowNum;
	/**
	 * Integer that holds the column number
	 */
	private int columnNum;
	/**
	 * Boolean flag to tell whether or not a piece is a king
	 */
	private boolean king;
	/**
	 * Integer that holds the type of checker object, 0 = red, 1 = black
	 */
	private final int type = 1;
	/**
	 * Integer that holds the Checker's id
	 */
	private int id;
	/**
	 * Integer that counts up which gets assigned to the checker's id
	 */
	private static int counter = 1;
	
	/**
	 * Constructor for the BlackChecker subclass 
	 * @param r Row number where the black checker is located
	 * @param c Column number where the black checker is located
	 */
	public BlackChecker(int r, int c, boolean k) {
		super();
		
		id = counter;
		rowNum = r;
		columnNum = c;
		king = k;
		counter++;
	}
	
	/**
	 * Method that returns the Black Checker's type 0 = red, 1 = black
	 * @return returns the Black Checker's type 0 = red, 1 = black
	 */
	public int getType() {
		return type;
	}
	/**
	 * Method that returns the row number of the black checker
	 * @return returns the row number of the black checker
	 */
	public int getRow() {
		return rowNum;
	}
	
	/**
	 * Method that returns the column number of the black checker
	 * @return returns the column number of the black checker
	 */
	public int getColumn() {
		return columnNum;
	}
	
	/**
	 * Method that returns true if piece is a king and false otherwise
	 * @return returns true if piece is a king and false otherwise
	 */
	public boolean getKing() {
		return king;
	}
	
	/**
	 * Method that sets the row number of the black checker
	 * @param r variable the row number is set to
	 */
	public void setRow(int r) {
		rowNum = r;
	}
	
	/**
	 * Method that sets the column number of the black checker
	 * @param c variable the column number is set to
	 */
	public void setColumn(int c) {
		columnNum = c;
	}
	
	/**
	 * Method that sets the king value to true if piece has just become king
	 * @param k Parameter sent to determine the king
	 */
	public void setKing(boolean k) {
		king = k;
	}
	
	/**
	 * Method that returns the Black Checker's id
	 * @return returns the Black Checker's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Method that returns a full descriptions of the Black Checker
	 * @return returns a full descriptions of the Black Checker
	 */
	public String getDescription() {
		return ("Black Checker id#: " + id + " Row: " + rowNum + " Column: " + columnNum);
	}

}