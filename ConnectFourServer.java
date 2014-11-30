import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class GoServer is the server main program for the Network Go Game. The
 * command line arguments specify the host and port to which the server should
 * listen for connections.
 * 
 * Usage: java GoServer host port
 *
 * @author  Alan Kaminsky
 * @author  Vasilios Anton
 */

public class ConnectFourServer {

	/**
	 * Main program.
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 2) usage();

		String host = args[0];
		int port = Integer.parseInt(args[1]);

		ServerSocket serversocket = new ServerSocket();
		serversocket.bind(new InetSocketAddress(host, port));

		SessionManager manager = new SessionManager();

		for (;;) {
			Socket socket = serversocket.accept();
			ViewProxy proxy = new ViewProxy(socket);
			proxy.setViewListener(manager);
		}
	}

	/**
	 * Print a usage message and exit.
	 */
	private static void usage() {
		System.err.println("Usage: java ConnectFourServer <serverhost> <serverport>");
		System.exit(1);
	}

}