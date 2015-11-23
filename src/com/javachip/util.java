package com.javachip;

import java.io.File;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.ListIterator;

import com.javachip.rmi.RMI_Main;

public class util {

	// Name of file on client side to be written to
	static String local_fileName = "FileList"; // Not needed
	// Client side server socket port to be started on
	static int client_socket_port = MainServlet.client_socket_port;
	// IP address of the rmi server
	static String AsteriskJava_IP = MainServlet.AsteriskJava_IP;
	// Useless, need to get rid of
	static String serviceName = "retrieve_available_files";
	// Name of file on server side to be read from
	static String remote_AsteriskSrcFilename = "txtServer";

	public static LinkedList<String> getAvailableFiles() {

		// Client side server socket port to be started on
		client_socket_port = MainServlet.client_socket_port;
		// IP address of the rmi server
		AsteriskJava_IP = MainServlet.AsteriskJava_IP;
		
		LinkedList<String> filesAvailable = null;

		if (client_socket_port != 0 || AsteriskJava_IP != null) {
			RMI_Main demo_instance = null;
			try {
				demo_instance = new RMI_Main();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			demo_instance.initialize(local_fileName, client_socket_port, AsteriskJava_IP, serviceName,
					remote_AsteriskSrcFilename);
			
			filesAvailable = demo_instance.availableFiles;

			System.out.println("This is the linked list");
			ListIterator<String> it = filesAvailable.listIterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			
		} else {
			System.out.println("Client Socket Port and Server IP is not available");
		}
		
		return filesAvailable;
	}
	
	// public void deleteFile(File dir, String fileName)
	public void deleteFile(String fileName){
		
		File file = new File(fileName);
		if( file.exists()) {
			file.delete();
		}

	}
	
	public static void main(String args []) {
		client_socket_port = 7000;
		AsteriskJava_IP = "192.168.1.2";
		LinkedList linked = getAvailableFiles();
		System.out.println("This is the list " + linked.toString());
	}
}
