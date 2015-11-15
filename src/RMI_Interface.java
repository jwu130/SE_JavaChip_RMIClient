

import java.rmi.RemoteException;

public interface RMI_Interface extends java.rmi.Remote {

	public void RPC_FileRead(String Service_UID, String srcFileName, String socket_ip, int socket_port, String remote_fileName) throws RemoteException;
	
	void retrieve_available_files(String socket_ip, int socket_port);
}