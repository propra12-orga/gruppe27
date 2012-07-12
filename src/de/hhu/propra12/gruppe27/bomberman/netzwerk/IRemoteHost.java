package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.core.Level;

/**
 * Interface IRemoteHost, Definition der Remote-Methode
 * 
 * @author gruppe27
 * @version 1.0
 * @see IRemoteClient
 */

public interface IRemoteHost extends Remote {

	// public static final int registryPort = 1099;
	// public static final String hostname = "localhost";
	// public static final String servicename = "IRemoteHostService";

	public static final int UP = 1, LEFT = 2, DOWN = 3, RIGHT = 4, BOMB = 5;

	public void joingame(String strService) throws RemoteException;

	public Level getLevel() throws RemoteException;

	public void movep2c(int direction) throws RemoteException;
}
