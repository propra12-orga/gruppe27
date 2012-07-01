package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteHost extends Remote {

	public static final int registryPort = 1099;
	public static final String hostname = "localhost";
	public static final String servicename = "IRemoteHostService";

	public void joingame() throws RemoteException;

}
