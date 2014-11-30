
/**
 * Interface C4BoardIntf specifies the interface for an object that
 * implements the Connect Four game board. The methods in this
 * interface are queried to display the game board.
 * 
 * @author Alan Kaminsky
 * @author Vasilios Anton vea7038@rit.edu
 * @version 13-Oct-2014
 */
public interface C4BoardIntf {

	/**
	 * Number of rows.
	 */
	public static final int ROWS = 6;

	/**
	 * Number of columns.
	 */
	public static final int COLS = 7;

	/**
	 * 2D integer array of integers to represent the board.
	 */
	public static Integer[][] board = new Integer[ROWS][COLS];

	/**
	 * Removes all player IDs (set to -1) from the board.
	 */
	public void clearBoard();

	/**
	 * Get the player of the cell. -1 means empty.
	 * 
	 * @param r
	 *            Row of the cell
	 * @param c
	 *            Column of the cell.
	 * @return Returns integer of player ID.
	 */
	public int getPlayer(int r, int c);

	/**
	 * Determine if the given row and column contains player 1's
	 * marker.
	 * 
	 * @param r
	 *            Row.
	 * @param c
	 *            Column.
	 * 
	 * @return True if (r, c) contains player 1's marker, false
	 *         otherwise.
	 */
	public boolean hasPlayer1Marker(int r, int c);

	/**
	 * Determine if the given row and column contains player 2's
	 * marker.
	 * 
	 * @param r
	 *            Row.
	 * @param c
	 *            Column.
	 * 
	 * @return True if (r, c) contains player 2's marker, false
	 *         otherwise.
	 */
	public boolean hasPlayer2Marker(int r, int c);

	public void setMarker(int r, int c, int player);

	/**
	 * Determine if one player or the other has won; that is, has four
	 * markers in a row horizontally, vertically, or diagonally. If
	 * so, an array of four integers (r1, c1, r2, c2) is returned,
	 * where (r1, c1) is the row/column of the first of the four
	 * markers and (r2, c2) is the row/column of the last of the four
	 * markers. If neither player has won, null is returned.
	 * 
	 * @return Array of (r1, c1, r2, c2), or null.
	 */
	public int[] hasWon();

}
