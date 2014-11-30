/**
 * Class C4Board implements C4BoardIntf and represents the board in a
 * 2D integer array data structure to play Connect Four.
 * 
 * @author Vasilios Anton vea7038@rit.edu
 */
public class C4Board implements C4BoardIntf {

	/**
	 * Constructs a new board and fills the cells with a player ID of
	 * -1 (empty)
	 */
	public C4Board() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				board[r][c] = -1;
			}
		}
	}

	/**
	 * Sets the cell's value to a player ID.
	 * 
	 * @param r
	 *            Row of the cell.
	 * @param c
	 *            Column of the cell.
	 * @param player
	 *            Player ID.
	 */
	public void setMarker(int r, int c, int player) {
		board[r][c] = player;
	}

	public int findOpenRow(int c) {
		int current = -1;
		for(int r = 0; r<ROWS; r++) {
			if (board[r][c] == -1) {
				current = r;
			} else {
				if (r == 0)
					return -1;

				break;
			}
		}
		
		return current;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see C4BoardIntf#getPlayer(int, int)
	 */
	public int getPlayer(int r, int c) {
		return board[r][c];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see C4BoardIntf#hasPlayer1Marker(int, int)
	 */
	public boolean hasPlayer1Marker(int r, int c) {
		// TODO Auto-generated method stub
		return board[r][c] == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see C4BoardIntf#hasPlayer2Marker(int, int)
	 */
	public boolean hasPlayer2Marker(int r, int c) {
		// TODO Auto-generated method stub
		return board[r][c] == 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see C4BoardIntf#hasWon()
	 */
	@Override
	public int[] hasWon() {
		int count, r1, c1, r2, c2;
		boolean win = false;

		count = r1 = c1 = r2 = c2 = 0;

		winCheck: for (int player = 1; player < 3; player++) {
			// Check for horizontal
			for (int r = 0; r < ROWS; r++) {
				count = 0;
				for (int c = 0; c < COLS; c++) {
					if (getPlayer(r, c) == player) {
						count++;

						if (count == 1) {
							r1 = r;
							c1 = c;
						} else if (count == 4) {
							r2 = r;
							c2 = c;
							win = true;
							break winCheck;
						}
					} else {
						count = 0;
						c1 = r1 = 0;
					}
				}
			}

			// Check for vertical
			for (int c = 0; c < COLS; c++) {
				count = 0;
				for (int r = 0; r < ROWS; r++) {
					if (getPlayer(r, c) == player) {
						count++;

						if (count == 1) {
							r1 = r;
							c1 = c;
						} else if (count == 4) {
							r2 = r;
							c2 = c;
							win = true;
							break winCheck;
						}
					} else {
						count = 0;
						c1 = r1 = 0;
					}
				}
			}

			// Check for diagonal top left to bottom right
			for (int c = 0; c < COLS; c++) {
				for (int r = 0; r < ROWS; r++) {
					count = 0;

					for (int delta = 0; delta < 5; delta++) {
						if (r + delta >= ROWS || c + delta >= COLS)
							continue;

						if (getPlayer(r + delta, c + delta) == player) {
							count++;

							if (count == 1) {
								r1 = r + delta;
								c1 = c + delta;
							} else if (count == 4) {
								r2 = r + delta;
								c2 = c + delta;
								win = true;
								break winCheck;
							}
						} else {
							count = 0;
							c1 = r1 = 0;
						}
					}
				}
			}

			// Check for diagonal bottom left to top right
			for (int c = 0; c < COLS; c++) {
				for (int r = 0; r < ROWS; r++) {
					count = 0;

					for (int delta = 0; delta < 5; delta++) {
						if (r - delta < 0 || c + delta >= COLS)
							continue;

						if (getPlayer(r - delta, c + delta) == player) {
							count++;

							if (count == 1) {
								r1 = r - delta;
								c1 = c + delta;
							} else if (count == 4) {
								r2 = r - delta;
								c2 = c + delta;
								win = true;
								break winCheck;
							}
						} else {
							count = 0;
							c1 = r1 = 0;
						}
					}
				}
			}
		}

		if (win) {
			int[] result = { r1, c1, r2, c2 };
			return result;
		} else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see C4BoardIntf#clearBoard()
	 */
	public void clearBoard() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				board[r][c] = -1;
			}
		}
	}

}
