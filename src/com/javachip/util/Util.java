package com.javachip.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import com.javachip.MainServlet;
import com.javachip.rmi.RMI_Main;

public class Util {

	// Client side server socket port to be started on
	static int client_socket_port = MainServlet.client_socket_port;
	// IP address of the rmi server
	static String AsteriskJava_IP = MainServlet.AsteriskJava_IP;

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
			
			String serviceName = "retrieve_available_files";
			// Name of file on server side to be read from
			String remote_AsteriskSrcFilename = "dummyName";
			// Name of file on client side to be written to
			String local_fileName = "dummyFileName"; // Not needed
			
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
	public static void deleteFile(String fileName){
		
		File file = new File(fileName);
		if( file.exists()) {
			file.delete();
		}

	}
	
	// Set the string contents of the file as a attribute in the request
	public static void setRequestAttr(HttpServletRequest request, String local_fileName, String attributeName, String fileNameAttribute) {
		// This will read the file one line at a time
		String line = null;
		String fileContent = "";

		try {
			local_fileName = local_fileName.replace("\"", "");

			File file = new File(local_fileName);

			System.out.println("File is found here: " + file.getAbsolutePath());

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				fileContent += line + "<br>";
			}

			System.out.println(fileContent);

			bufferedReader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		request.setAttribute(attributeName, fileContent);
		request.setAttribute(fileNameAttribute, fileContent);
	}
	
	// For testing
	public static void main(String args []) {
		client_socket_port = 7000;
		AsteriskJava_IP = "192.168.1.2";
		LinkedList linked = getAvailableFiles();
		System.out.println("This is the list " + linked.toString());
	}
	// Testing
}
