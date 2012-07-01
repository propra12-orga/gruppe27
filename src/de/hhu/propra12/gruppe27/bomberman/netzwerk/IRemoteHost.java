package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteHost extends Remote {

	public static final int registryPort = 1099;
	public static final String hostname = "localhost";
	public static final String servicename = "IRemoteHostService";

	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// Definition der Remote-Methoden

	public void joingame() throws RemoteException;

	public void moveLanPlayer(int playerindex, int direction)
			throws RemoteException;

	public void layBombLanPlayer(int playerindex) throws RemoteException;

}
