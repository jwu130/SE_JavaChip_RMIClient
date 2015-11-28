package com.javachip.rmi;

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
import java.util.LinkedList;

public class RMI_Main {

	// To know when the transfer is completed
	public boolean finished;

	public Object fin = new Object();

	public LinkedList<String> availableFiles = new LinkedList<String>();

	// The client ip address that the socket listener listens at
	private String socket_listener_ip;

	// *** This section is for multi-threading initialization
	private final class RequestThread extends Thread {
		// To decide which thread to create
		private String option;
		// The ip address of the rmi server
		private String AsteriskJava_IP;
		// Useless, need to get rid of
		private String serviceName;
		// Name of remote file on server side
		private String remote_AsteriskSrcFilename;
		// The client ip address that the socket listener listens at (variable
		// for thread)
		private String socket_listener_ip;
		// Port of client socket
		private int socket_port;
		// Name of local file (file will create one if not available)
		private String local_fileName;

		RequestThread(final String option, final String AsteriskJava_IP, final String serviceName,
				final String remote_AsteriskSrcFilename, final String socket_listener_ip, final int socket_port,
				final String local_fileName) {
			this.option = option;
			this.AsteriskJava_IP = AsteriskJava_IP;
			this.serviceName = serviceName;
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
					e.printStackTrace();
				} finally {
					socket_stream_buffer_close();
				}
			}
			// If AsteriskJava, create new client instance
			if (option.equals("AsteriskJava")) {
				new RMI_Client(AsteriskJava_IP, serviceName, remote_AsteriskSrcFilename, socket_listener_ip,
						socket_port, local_fileName);
			}
			// Runs the above operations simultaneously as multi-threads
		}

	}// Request Thread end
		// --------------------------------------------------------------------------------------

	private PrintWriter pw;
	private BufferedReader br;
	private Socket socket;
	private ServerSocket serverSocket = null;

	private String serviceName;

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
			if (this.serviceName == "RPC_FileRead")
				socket_listener_save(local_fileName);
			else if (serviceName == "retrieve_available_files")
				socket_listener_names();
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

	public void socket_listener_save(String local_fileName) {
		String inputln;
		PrintWriter outStream = null;

		try {
			File clientFile = new File(local_fileName);
			System.out.println("File can be found in: " + clientFile.getAbsolutePath());
			System.out.println("Recieving data from server: ");

			outStream = new PrintWriter(new FileOutputStream(clientFile));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		long start = System.currentTimeMillis();
		long end = start + 60 * 1000; // 60 seconds * 1000 ms/sec

		finished = false;
		try {
			while (System.currentTimeMillis() < end && !finished) {
				if ((inputln = br.readLine()).equals("Xfer Start")) {
					while (!((inputln = br.readLine()).equals("Done"))) {
						inputln = inputln.trim();
						outStream.println(inputln);
						System.out.println(inputln);
					}

					finished = true;
					break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		outStream.close();
		socket_stream_buffer_close();
	}

	public void socket_listener_names() {
		String inputln;

		long start = System.currentTimeMillis();
		long end = start + 60 * 1000; // 60 seconds * 1000 ms/sec

		finished = false;
		try {
			while (System.currentTimeMillis() < end && !finished) {
				if ((inputln = br.readLine()).equals("Xfer Start")) {
					while (!((inputln = br.readLine()).equals("Done"))) {
						inputln = inputln.trim();
						System.out.println(inputln);
						availableFiles.add(inputln);
					}

					finished = true;
					break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		socket_stream_buffer_close();
	}

	public void socket_stream_buffer_close() {
		pw.close();
		try {
			socket.close();
			synchronized (fin) {
				fin.notify();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getFinished() {
		return finished;
	}

	public void initialize(String local_fileName, int client_socket_port, String rmiServer_ip, String serviceName,
			String remote_AsteriskSrcFilename) {
		// Get system ip address
		try {
			socket_listener_ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("Socket listener ip is: " + socket_listener_ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		this.serviceName = serviceName;
		
		// Create new threads, socket, and RMI_BioAPI_Asterisk_Client ( looks up
		// remote object and invokes method on rm)
		RequestThread socketThread = new RequestThread("socket", "N/A", "N/A", "N/A", socket_listener_ip,
				client_socket_port, local_fileName);
		RequestThread rmiThread = new RequestThread("AsteriskJava", rmiServer_ip, serviceName,
				remote_AsteriskSrcFilename, socket_listener_ip, client_socket_port, local_fileName);

		try {
			synchronized (fin) {
				fin.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** RMI_BioAPI_Demo constructor **/
	public RMI_Main() throws RemoteException {
	}

}
