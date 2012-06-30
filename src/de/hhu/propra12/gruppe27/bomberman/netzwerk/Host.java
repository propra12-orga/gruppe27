package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;

public class Host {

	IRemoteClient service;

	public Host() {
	}

	// suche nach remote-services des Clients
	public void fetchclientservice() {

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

	public void startgame() {

		/*
		 * Client hat sich beim Host angemeldet (Client joined Game)
		 * 
		 * suche nach remote-services des Clients
		 */
		fetchclientservice();

		GameWindow s = new GameWindow(0);

		// Übergabe des Spielfeldes an den Client
		try {
			service.sendSpielfeld(s.getspielfeld());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
