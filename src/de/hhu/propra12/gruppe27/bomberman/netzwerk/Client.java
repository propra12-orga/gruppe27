package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.Keyset;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinstClient;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author
 * @version 1.0 Klasse Client
 */

public class Client extends UnicastRemoteObject implements IRemoteClient {

	private static final long serialVersionUID = 1L;
	IRemoteHost service;
	/*
	 * für 2 systeme wieder reinnehmen SysEinst system = SysEinst.getSystem();
	 */
	SysEinstClient system = SysEinstClient.getSystemClient();

	Spielfeld spielfeld = null;

	public Client() throws RemoteException {

		publishClient();

		service = retrieveHostService();
		system.setRemoteHost(service);
		service.joingame();

		// System.out.println("Client:");
		// System.out.println("Players.PlayerList.size() ="
		// + spielfeld.Players.PlayerList.size());
		// for (AbstractPlayer ap : spielfeld.Players.PlayerList)
		// System.out.println("ap.isAlive()=" + ap.isAlive());
		Level level = service.getLevel();

		// spielfeld.switchKeyset();

		GameWindow s = new GameWindow(level);
		spielfeld = s.getspielfeld();

		// TODO startgame entfernen, wenn remoteaufruf tick funktioniert.
		// spielfeld.startgame();
		System.out.println("send game received");
	}

	/**
	 * Clientname, registryPort, servicename werden erfragt
	 * 
	 * @throws RemoteException
	 */

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

	/**
	 * 
	 * @return service
	 */

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

	@Override
	public void tick() throws RemoteException {
		spielfeld.getPlayers().movePlayers();
		spielfeld.repaint();

	}

	@Override
	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void movep2h(int direction) throws RemoteException {
		direction = translate(direction);

		spielfeld.getPlayers().moveremotePlayers(direction);

	}

	// public int translate(int direction) {
	// if (direction == KeyEvent.VK_UP) {
	// return Keyset.REMUP;
	// } else if (direction == KeyEvent.VK_LEFT) {
	// return Keyset.REMLEFT;
	// } else if (direction == KeyEvent.VK_DOWN) {
	// return Keyset.REMDOWN;
	// } else if (direction == KeyEvent.VK_RIGHT) {
	// return Keyset.REMRIGHT;
	// } else if (direction == KeyEvent.VK_ENTER) {
	// return Keyset.REMBOMB;
	// }
	// return 0; // sollte nie vorkommen!
	// }

	public int translate(int direction) {
		System.out.println("client: translate: " + direction);
		if (direction == IRemoteClient.UP) {
			return Keyset.REMUP;
		} else if (direction == IRemoteClient.LEFT) {
			return Keyset.REMLEFT;
		} else if (direction == IRemoteClient.DOWN) {
			return Keyset.REMDOWN;

		} else if (direction == IRemoteClient.RIGHT) {
			return Keyset.REMRIGHT;
		} else if (direction == IRemoteClient.BOMB) {
			return Keyset.REMBOMB;
		}
		System.out.println("keine korrekte Uebersetzung moeglich");
		return 0; // sollte nie vorkommen!
	}
}
