import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")

public class servletEntryPoint extends HttpServlet{

	String message = "Hello World";
	//	String local_fileName, int socket_port, String AsteriskJava_IP, String Service_UID,
	//		String remote_AsteriskSrcFilename
	String local_fileName = "txtClient";
	int client_socket_port = 7050;
	String AsteriskJava_IP = "192.168.1.5";
	String Service_UID = "Anything";
	String remote_AsteriskSrcFilename = "txtServer";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//do get request, response servlet exception, ioexception
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     // Set response content type
	      response.setContentType("text/html");
	      
	        ClassLoader cl = ClassLoader.getSystemClassLoader();

	        URL[] urls = ((URLClassLoader)cl).getURLs();

	        for(URL url: urls){
	        	System.out.println(url.getFile());
	        }
	        
	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + message + "</h1>");
	      
	      RMI_BioAPI_Demo demo_instance = new RMI_BioAPI_Demo(local_fileName, client_socket_port, AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename);
	}
	
	//do post request, response servlet exception, ioexception
	protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		//where to store file: good practice
		//get request servletcontext real path
		//regular expression \\\\ to escape two \
		//should not allow users to specify the file name
	}	
}
