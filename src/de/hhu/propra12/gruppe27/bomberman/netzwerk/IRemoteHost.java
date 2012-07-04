package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.core.Level;

/**
 * 
 * @author
 * @version 1.0 Interface IRemoteHost, Definition der Remote-Methode
 */

public interface IRemoteHost extends Remote {

	public static final int registryPort = 1099;
	public static final String hostname = "localhost";
	public static final String servicename = "IRemoteHostService";

	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	public void joingame() throws RemoteException;

	public Level getLevel() throws RemoteException;

	public void movep2c(int direction) throws RemoteException;
}
