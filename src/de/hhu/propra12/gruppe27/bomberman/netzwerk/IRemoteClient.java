package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public interface IRemoteClient extends Remote {

	public static final int registryPort = 1099;
	public static final String clientname = "localhost";
	public static final String servicename = "IRemoteClientService";

	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// Definition der Remote Methoden

	public void sendSpielfeld(Spielfeld spielfeld) throws RemoteException;
	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed) throws RemoteException;
	//public void hostKeyReleased(int playerindex, int keycode) throws RemoteException;
	public void tick() throws RemoteException;
	

}
