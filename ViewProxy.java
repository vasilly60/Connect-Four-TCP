import java.io.*;
import java.net.Socket;

/**
 * Class ViewProxy provides the network proxy for the view object in the Network
 * Go Game. The view proxy resides in the server program and communicates with
 * the client program.
 *
 * @author  Alan Kaminsky
 * @version 21-Jan-2010
 */
public class ViewProxy implements ModelListener {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private ViewListener viewListener;

	/**
	 * Construct a new view proxy.
	 *
	 * @param  socket  Socket.
	 *
	 * @exception  IOException
	 *     Thrown if an I/O error occurred.
	 */
	public ViewProxy(Socket socket) throws IOException {
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * Set the view listener object for this view proxy.
	 *
	 * @param  viewListener  View listener.
	 */
	public void setViewListener(ViewListener viewListener) {
		if (this.viewListener == null) {
			this.viewListener = viewListener;
			new ReaderThread().start();
		} else {
			this.viewListener = viewListener;
		}
	}

	public void markerAdded(int r, int c, int player) throws IOException {
		out.println("add " + player + " " + r + " " + c);
	}

	public void boardCleared() throws IOException {
		out.println("clear");
	}

	public void playerNumber(int player) throws IOException {
		out.println("number " + player);
	}

	public void playerTurn(int player) throws IOException {
		out.println("turn " + player);
	}

	public void playerName(int player, String name) throws IOException {
		out.println("name " + player + " " + name);
	}

	public void close() { }

	/**
	 * Class ReaderThread receives messages from the network, decodes them, and
	 * invokes the proper methods to process them.
	 *
	 * @author  Alan Kaminsky
	 * @version 19-Jan-2010
	 */
	private class ReaderThread extends Thread {
		public void run() {
			try {
				for (;;) {
					String b = in.readLine();

					if (b != null) {
						b = b.trim();
					} else {
						close();
						break;
					}

					System.out.println("ViewProxy " + b);

					if (b.length() == 0) {
						System.err.println("Bad message");
					} else {
						String[] parts = b.split(" ");

						switch (parts[0]) {
							case "join":
								String name = parts[1];
								viewListener.join(ViewProxy.this, name);
								break;
							case "add":
								viewListener.addMarker(-1,
										Integer.valueOf(parts[2]),
										Integer.valueOf(parts[1]));
								break;
							case "clear":
								viewListener.clearBoard();
								break;
							default:
								System.err.println("Bad message");
								break;
						}
					}
				}
			} catch (IOException exc) {
			} finally {
				try {
					socket.close();
				} catch (IOException exc) {
				}
			}
		}
	}
}