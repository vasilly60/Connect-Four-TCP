import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.IOException;

/**
 * Class C4UI provides the user interface for the network game of
 * Connect Four.
 * 
 * @author Alan Kaminsky
 * @author Vasilios Anton
 */
public class C4UI implements ModelListener {

	// Hidden data members.

	private C4BoardIntf c4board;

	private JFrame frame;
	private C4Panel boardPanel;
	private JTextField message;
	private JButton newGameButton;

	private ViewListener viewListener;

	private boolean waiting = true;

	private int player;
	private String opponent;

	// Exported constructors.

	/**
	 * Construct a new Connect Four UI.
	 * 
	 * @param board
	 *            Connect Four board.
	 * @param name
	 *            Player's name.
	 */
	public C4UI(C4BoardIntf board, String name) {
		c4board = board;

		// Set up window.
		frame = new JFrame("Connect Four -- " + name);
		Container pane = frame.getContentPane();
		JPanel p1 = new JPanel();
		pane.add(p1);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Create and add widgets.
		boardPanel = new C4Panel(c4board);
		boardPanel.setAlignmentX(0.5f);
		p1.add(boardPanel);
		p1.add(Box.createVerticalStrut(10));
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p2.setAlignmentX(0.5f);
		p1.add(p2);
		message = new JTextField(20);
		message.setAlignmentY(0.5f);
		message.setEditable(false);
		message.setText("Waiting for partner");
		p2.add(message);
		p2.add(Box.createHorizontalStrut(10));
		newGameButton = new JButton("New Game");
		newGameButton.setAlignmentY(0.5f);
		newGameButton.setEnabled(false);
		p2.add(newGameButton);

		// Clicking the Connect Four panel informs the view listener.
		boardPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (waiting == false) {
						int c = boardPanel.clickToColumn(e);
						int r = 0; // CHANGE THIS
						viewListener.addMarker(r, c, player);
					}
				} catch (IOException exc) {
				}
			}
		});

		// Clicking the New Game button informs the view listener.
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewListener.clearBoard();
				} catch (IOException exc) {
				}
			}
		});

		// Closing the window exits the client.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Set the view listener for this Connect Four UI.
	 * 
	 * @param viewListener
	 *            View listener.
	 */
	public void setViewListener(ViewListener viewListener) {
		this.viewListener = viewListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#markerAdded(int, int, int)
	 */
	public void markerAdded(int r, int c, int player) {
		c4board.setMarker(r, c, player);
		boardPanel.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#boardCleared()
	 */
	public void boardCleared() {
		boardPanel.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#playerNumber(int)
	 */
	public void playerNumber(int player) {
		this.player = player;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#playerName(int, java.lang.String)
	 */
	public void playerName(int player, String name) {
		if (player != this.player) {
			this.opponent = name;
			newGameButton.setEnabled(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#playerTurn(int)
	 */
	public void playerTurn(int player) {
		if (player == this.player) {
			this.waiting = false;
			message.setText("Your turn");
		} else if (player == 0) {
			this.waiting = true;
			message.setText("Game over");
		} else {
			this.waiting = true;
			message.setText(this.opponent + "'s turn");
		}

		frame.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ModelListener#close()
	 */
	public void close() {
		frame.dispose();
		System.exit(1);
	}
}