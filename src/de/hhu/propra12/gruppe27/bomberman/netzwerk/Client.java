package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractPlayer;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class Client extends UnicastRemoteObject implements IRemoteClient {

	private static final long serialVersionUID = 1L;
	IRemoteHost service;

	public Client() throws RemoteException {

		publishClient();

		service = retrieveHostService();
		service.joingame();

	}

	public void publishClient() throws RemoteException {

		try {

			System.out.println("publish:    rmi://" + clientname + ":"
					+ registryPort + "/" + servicename);
			Naming.rebind("rmi://" + clientname + ":" + registryPort + "/"
					+ servicename, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSpielfeld(Spielfeld spielfeld) {
		System.out.println("Client:");
		System.out.println("Players.PlayerList.size() ="
				+ spielfeld.Players.PlayerList.size());
		for (AbstractPlayer ap : spielfeld.Players.PlayerList)
			System.out.println("ap.isAlive()=" + ap.isAlive());

		spielfeld.initImages();
		GameWindow s = new GameWindow(0, true, spielfeld.getsystem(), spielfeld);
		spielfeld.startgame();
		System.out.println("send game received");

	}

	public IRemoteHost retrieveHostService() {
		try {
			System.out.println("rmi://" + IRemoteHost.hostname + ":"
					+ IRemoteHost.registryPort + "/" + IRemoteHost.servicename);
			IRemoteHost service = (IRemoteHost) Naming.lookup("rmi://"
					+ IRemoteHost.hostname + ":" + IRemoteHost.registryPort
					+ "/" + IRemoteHost.servicename);

			this.service = service;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return service;

	}
}
