import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class RMI_BioAPI_Demo {

	// The client ip address that the socket listener listens at
	private String socket_listener_ip;

	// *** This section is for multi-threading initialization
	private final class RequestThread extends Thread {
		// To decide which thread to create
		private String option;
		// The ip address of the rmi server
		private String AsteriskJava_IP;
		// Useless, need to get rid of
		private String Service_UID;
		// Name of remote file on server side
		private String remote_AsteriskSrcFilename;
		// The client ip address that the socket listener listens at (variable
		// for thread)
		private String socket_listener_ip;
		// Port of client socket
		private int socket_port;
		// Name of local file (file will create one if not available)
		private String local_fileName;

		RequestThread(final String option, final String AsteriskJava_IP, final String Service_UID,
				final String remote_AsteriskSrcFilename, final String socket_listener_ip, final int socket_port,
				final String local_fileName) {
			this.option = option;
			this.AsteriskJava_IP = AsteriskJava_IP;
			this.Service_UID = Service_UID;
			this.remote_AsteriskSrcFilename = remote_AsteriskSrcFilename;
			this.socket_listener_ip = socket_listener_ip;
			this.socket_port = socket_port;
			this.local_fileName = local_fileName;
			this.start();
		}

		public void run() {
			// If socket, create a socket thread
			if (option.equals("socket")) {
				try {
					initialize_socket(socket_port, local_fileName);
				} catch (Exception e) {
					System.out.println("Error on initializing socket server");
				}
			}
			// If AsteriskJava, create new client instance
			if (option.equals("AsteriskJava")) {
				new RMI_BioAPI_AsteriskJava_Client(AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename,
						socket_listener_ip, socket_port, local_fileName);
			}
			// Runs the above operations simultaneously as multi-threads
		}

	}// Request Thread end
		// --------------------------------------------------------------------------------------

	private PrintWriter pw;
	private BufferedReader br;
	private Socket socket;
	private ServerSocket serverSocket = null;

	public void initialize_socket(int port, String local_fileName) {
		try {
			// Create client side server socket bound to specified port
			serverSocket = new ServerSocket(port);

			// Display ip and port - for testing purposes
			String addr = serverSocket.getInetAddress().toString();
			String addrr = serverSocket.getLocalSocketAddress().toString();
			System.out.println("IP address of client socket: " + addr);
			System.out.println("Client socket address(IP address: port): " + addrr);

			initialize_socket_stream_buffer();
			socket_listener(local_fileName);
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + port + e.getMessage());
			System.exit(-1);
		}
	}

	public void initialize_socket_stream_buffer() {
		try {
			// Create socket and enable listening using serverSocket
			socket = serverSocket.accept();
			System.out.println("Server socket (Client) is ready to recieve response from server");
			
			// Init reader and writer
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void socket_listener(String local_fileName) {
		System.out.println("Recieving data from server: ");
		String inputln;
		PrintWriter outStream = null;

		// Find/create file and create printwriter
		try {
			outStream = new PrintWriter(new FileOutputStream(local_fileName));
			System.out.println("Local file found in this location: " + outStream.toString());
			
			// For displaying where the file ended up
			// Need to designate a location to find the client files ?
			File f = new File("txtClient");
			System.out.println("txtClient found " + f.getAbsolutePath());
			
		} catch (FileNotFoundException e1) {
			System.out.println("Failed to open file to write to. File name was: " + local_fileName);
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			while ((inputln = br.readLine()) != "Done" && inputln != null) {
				System.out.println(inputln);
				
				// Write lines received to local file
				outStream.println(inputln);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		outStream.close();
		socket_stream_buffer_close();
	}

	public void socket_stream_buffer_close() {
		pw.close();
		try {
			br.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** RMI_BioAPI_Demo constructor **/
	public RMI_BioAPI_Demo(String local_fileName, int client_socket_port, String AsteriskJava_IP, String Service_UID,
			String remote_AsteriskSrcFilename) throws RemoteException {
		
		// Get system ip address
		try {
			socket_listener_ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(socket_listener_ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// Create new threads, socket, and RMI_BioAPI_Asterisk_Client ( looks up remote object and invokes method on rm)
		new RequestThread("socket", "N/A", "N/A", "N/A", socket_listener_ip, client_socket_port, local_fileName);
		new RequestThread("AsteriskJava", AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename, socket_listener_ip,
				client_socket_port, local_fileName);

		System.out.println("RMI_BioAPI_Demo instance is created. Local file name: " + local_fileName
				+ ". Remote file name: " + remote_AsteriskSrcFilename);
	}

}
