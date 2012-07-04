package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author
 * @version Interface IRemoteClient, Definition der Remote Mehtode
 */

public interface IRemoteClient extends Remote {

	public static final int registryPort = 1099;
	public static final String clientname = "localhost";
	public static final String servicename = "IRemoteClientService";

	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, BOMB = 4;

	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed)
			throws RemoteException;

	public void tick() throws RemoteException;

	public void movep2h(int direction) throws RemoteException;

}
