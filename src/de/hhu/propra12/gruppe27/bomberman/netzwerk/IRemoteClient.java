package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface IRemoteClient, Definition der Remote Methode
 * Initlialisierung von up,left,down,right und bomb
 * Bewegung des Client
 * @author gruppe 27
 * @version 1.0
 */ 

public interface IRemoteClient extends Remote {

	// public static final int registryPort = 1099;
	// public static final String clientname = "localhost";
	// public static final String servicename = "IRemoteClientService";

	public static final int UP = 1, LEFT = 2, DOWN = 3, RIGHT = 4, BOMB = 5;

	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed)
			throws RemoteException;

	public void tick() throws RemoteException;

	public void movep2h(int direction) throws RemoteException;

}
