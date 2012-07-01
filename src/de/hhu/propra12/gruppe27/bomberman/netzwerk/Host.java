package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class Host extends UnicastRemoteObject implements IRemoteHost {

	private static final long serialVersionUID = 1L;

	IRemoteClient service;

	public Host() throws RemoteException {
		publishHost();
	}

	// suche nach remote-services des Clients
	public void retrieveClientService() {
		System.out.println("fetch:    rmi://" + IRemoteClient.clientname + ":"
				+ IRemoteClient.registryPort + "/" + IRemoteClient.servicename);
		try {
			service = (IRemoteClient) Naming.lookup("rmi://"
					+ IRemoteClient.clientname + ":"
					+ IRemoteClient.registryPort + "/"
					+ IRemoteClient.servicename);
			this.service = service;
		}

		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	@Override
	public void joingame() {

		/*
		 * Client hat sich beim Host angemeldet (Client joined Game)
		 * 
		 * suche nach remote-services des Clients
		 */
		retrieveClientService();

		GameWindow s = new GameWindow(0);

		// Übergabe des Spielfeldes an den Client
		try {
			Spielfeld spielfeld = s.getspielfeld();
			// System.out.println("Server:");
			// System.out.println("Players.PlayerList.size() ="
			// + spielfeld.Players.PlayerList.size());
			// for (AbstractPlayer ap : spielfeld.Players.PlayerList)
			// System.out.println("ap.isAlive()=" + ap.isAlive());
			// System.out.println(""
			// + spielfeld.Players.PlayerList.get(0).getClass());

			service.sendSpielfeld(s.getspielfeld());
			spielfeld.initPlayer();
			spielfeld.startgame();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void publishHost() throws RemoteException {

		try {
			System.out.println("RHI: " + "rmi://" + hostname + "/"
					+ servicename);
			LocateRegistry.createRegistry(registryPort);
			Naming.rebind("rmi://" + hostname + "/" + servicename, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveLanPlayer(int playerindex, int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void layBombLanPlayer(int playerindex) {
		// TODO Auto-generated method stub

	}

}
