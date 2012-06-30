package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public interface IRemoteClient extends Remote {

	public static final int registryPort = 1099;
	public static final String clientname = "localhost";
	public static final String servicename = "IRemoteClientService";

	public void sendSpielfeld(Spielfeld spielfeld) throws RemoteException;

}
