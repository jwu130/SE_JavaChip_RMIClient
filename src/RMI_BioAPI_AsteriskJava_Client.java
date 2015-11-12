import java.rmi.Naming;

public class RMI_BioAPI_AsteriskJava_Client {

	public RMI_BioAPI_AsteriskJava_Client(String host_ip, String serviceName, String srcFileName, String socket_ip,
			int socket_port, String remote_fileName) {

		try {
			RMI_BioAPI_AsteriskJava_Interface service = (RMI_BioAPI_AsteriskJava_Interface) Naming
					.lookup("rmi://" + host_ip + "/RMI_BioAPI_AsteriskJava");
			System.out.println("Naming lookup succeeded");
			if (serviceName == "RPC_FileRead")
				service.RPC_FileRead(serviceName, srcFileName, socket_ip, socket_port, remote_fileName);
			else if (serviceName == "retrieve_available_files")
				service.retrieve_available_files(socket_ip, socket_port);
		} catch (Exception e) {

			System.out.println(e);
			e.printStackTrace();
			System.out.println("RMI_BioAPI_AsteriskJava Naming lookup fails!");

		} // try-catch
	} // Constructor

} // RMI_BioAPI_AsteriskJava_Client
