

//${catalina.base}/wtpwebapps/Assignment3Servlet/WEB-INF/classes/RMI_Client
//
//grant codeBase "file:/C:/Users/TeresaUser/Downloads/apache-tomcat-7.0.64/wtpwebapps/Assignment3Servlet/WEB-INF/classes/RMI_Client/-" {
//    permission java.security.AllPermission;
//};
//grant {
//    permission java.security.AllPermission;
//};
//grant codeBase "file:${catalina.home}/wtpwebapps/Assignment3Servlet/WEB-INF/classes/-" { 
//    permission java.security.AllPermission "", ""; 
//}; 
// 
//grant codeBase "file:${catalina.home}/wtpwebapps/Assignment3Servlet/WEB-INF/lib/-" { 
//    permission java.security.AllPermission "", ""; 
//}; 
//grant codeBase "file:${catalina.home}/wtpwebapps/Assignment3Servlet/WEB-INF/lib/some-common-3.0.jar" { 
//    permission java.io.FilePermission "*", "read, write"; 
//};
//-Dcatalina.base="C:\Users\TeresaUser\Downloads\apache-tomcat-7.0.64" -Dcatalina.home="C:\Users\TeresaUser\Downloads\apache-tomcat-7.0.64" -Dwtp.deploy="C:\Users\TeresaUser\Downloads\apache-tomcat-7.0.64\wtpwebapps" -Djava.endorsed.dirs="C:\Users\TeresaUser\Downloads\apache-tomcat-7.0.64\endorsed" -Djava.security.manager -Djava.security.policy="C:\Users\TeresaUser\JavaEEWorkspace\Servers\Tomcat v7.0 Server at localhost-config\catalina.policy"

import java.rmi.Naming;

public class RMI_BioAPI_AsteriskJava_Client {

	public RMI_BioAPI_AsteriskJava_Client(String host_ip, String Service_UID, String srcFileName, String socket_ip,
			int socket_port, String remote_fileName) {
		// InetAddress intAddr;
		// String host_ip = "";
		try {
			// intAddr = InetAddress.getLocalHost();
			// host_ip = intAddr.getHostAddress();
			//
			// URL url =
			// RMI_BioAPI_AsteriskJava_Server.class.getResource("rmi.policy");
			//
			// System.out.println(url.toString());
			// System.setProperty("java.security.policy", url.toString());
			//
			// if (System.getSecurityManager() == null) {
			//
			// System.setSecurityManager(new SecurityManager());
			//
			// }

//			System.setProperty("java.security.policy", "C:\\Users\\TeresaUser\\JavaEEWorkspace\\Assignment3Servlet\\client.policy");
//			System.out.println("Set the security policy: C:\\Users\\TeresaUser\\JavaEEWorkspace\\Assignment3Servlet\\client.policy");
//			
//			System.setSecurityManager(new SecurityManager());

			RMI_BioAPI_AsteriskJava_Interface service = (RMI_BioAPI_AsteriskJava_Interface) Naming
					.lookup("rmi://" + host_ip + "/RMI_BioAPI_AsteriskJava");

			System.out.println("Naming lookup succeeded");
			service.RPC_FileRead(Service_UID, srcFileName, socket_ip, socket_port, remote_fileName);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("RMI_BioAPI_AsteriskJava Naming lookup fails!");
		}
	}

}
