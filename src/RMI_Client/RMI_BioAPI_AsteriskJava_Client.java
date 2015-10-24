package RMI_Client;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 *
 */
public class RMI_BioAPI_AsteriskJava_Client {

	public RMI_BioAPI_AsteriskJava_Client(String host_ip, String Service_UID, String srcFileName, String socket_ip,
			int socket_port, String remote_fileName) {
		try {
			RMI_BioAPI_AsteriskJava_Interface service = (RMI_BioAPI_AsteriskJava_Interface) Naming
					.lookup("rmi://" + host_ip + "/RMI_BioAPI_AsteriskJava");

			System.out.println("Naming lookup succeeded");
			service.RPC_FileRead(Service_UID, srcFileName, socket_ip, socket_port, remote_fileName);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("RMI_BioAPI_AsteriskJava Naming lookup fails!");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// Check for hostname argument
		if (args.length != 6) {
			System.out.println(
					"Syntax - java RMI_BioAPP_AsteriskJava_Client <host_IP> <Service_UID> <srcFileName_to_be_sent_over> <socket_ip> <socket_port> <remote_fileName>");
			System.exit(1);
		}

		// Assign security manager
		// if (System.getSecurityManager() == null)
		// {
		// System.setSecurityManager
		// (new RMISecurityManager());
		// }

		// Call registry for Service
		new RMI_BioAPI_AsteriskJava_Client(args[0], args[1], args[2], args[3], Integer.parseInt(args[4]), args[5]);
	}

}
