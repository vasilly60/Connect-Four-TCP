import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.*;

/**
 * Class ConnectFour connects to the socket for the Connect Four
 * server and runs the game.
 * 
 * @author Vasilios Anton vea7038@rit.edu
 */
public class ConnectFour {

	/**
	 * Runs a Connect Four game.
	 * 
	 * @param args
	 *            Contains the host, port, and player name.
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			usage();
		}

		String host = args[0];
		int port = 0;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			usage();
		}
		String playername = args[2];

		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(host, port));

		C4ModelClone model = new C4ModelClone();
		C4UI view = new C4UI(model.getBoard(), args[2]);
		ModelProxy proxy = new ModelProxy(socket);

		model.setModelListener(view);
		view.setViewListener(proxy);
		proxy.setModelListener(model);
		proxy.join(null, playername);
	}

	/**
	 * Displays the usage message for improper command line prompts.
	 */
	private static void usage() {
		System.err
				.println("Usage: java ConnectFour <host> <port> <playername>");
		System.exit(0);
	}

}
