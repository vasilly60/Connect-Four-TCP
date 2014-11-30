import java.io.IOException;

/**
 * Class C4ModelClone provides a client-side clone of the server-side
 * model object in the Network Connect Four Game.
 * 
 * @author Vasilios Anton
 */
public class C4ModelClone implements ModelListener {

	/**
	 * The board the model uses.
	 */
	private C4Board board = new C4Board();

	/**
	 * The listener attached to this model.
	 */
	private ModelListener modelListener;

	/**
	 * Construct a new Connect Four model clone.
	 */
	public C4ModelClone() {
	}

	/**
	 * Returns a reference to the Connect Four board object in this
	 * Connect Four model clone.
	 * 
	 * @return Connect Four board.
	 */
	public C4Board getBoard() {
		return board;
	}

	/**
	 * Set the model listener for this Connect Four model clone.
	 * 
	 * @param modelListener
	 *            Model listener.
	 */
	public void setModelListener(ModelListener modelListener) {
		this.modelListener = modelListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#markerAdded(int, int, int)
	 */
	public void markerAdded(int r, int c, int player) throws IOException {
		board.setMarker(r, c, player);
		modelListener.markerAdded(r, c, player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#boardCleared()
	 */
	public void boardCleared() throws IOException {
		board.clearBoard();
		modelListener.boardCleared();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#playerNumber(int)
	 */
	public void playerNumber(int player) throws IOException {
		modelListener.playerNumber(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#playerName(int, java.lang.String)
	 */
	public void playerName(int player, String name) throws IOException {
		modelListener.playerName(player, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#playerTurn(int)
	 */
	public void playerTurn(int player) throws IOException {
		modelListener.playerTurn(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#close()
	 */
	public void close() {
		modelListener.close();
	}

}