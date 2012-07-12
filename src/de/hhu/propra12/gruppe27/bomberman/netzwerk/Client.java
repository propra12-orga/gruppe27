package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.Keyset;
import de.hhu.propra12.gruppe27.bomberman.core.LanPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse Client, Vererbung von IRemoteClient URL,Spielfeld etc.
 *          wird uebergeben fuer den Client
 */

public class Client extends UnicastRemoteObject implements IRemoteClient {

	private static final long serialVersionUID = 1L;
	IRemoteHost service;

	SysEinst system = SysEinst.getSystem();

	Spielfeld spielfeld = null;

	public Client(int regPort, String servicename, String hostservice)
			throws RemoteException {

		String strService = publishClient(regPort, servicename);

		service = retrieveService(hostservice);
		system.setRemoteHost(service);
		System.out.println("serverURL " + strService);
		service.joingame(strService);

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
	 * Clientname, registryPort, servicename werden erfragt Ausnahme fuer
	 * RemoteException
	 * 
	 * @throws RemoteException
	 */

	public String publishClient(int registryPort, String servicename)
			throws RemoteException {
		String str = "client not yet published";
		try {

			if (system.getRegistry() != null) {
				UnicastRemoteObject.unexportObject(system.getRegistry(), true);
			}

			String ip = InetAddress.getLocalHost().getHostAddress();
			// LocateRegistry.createRegistry(registryPort);

			Registry registry = LocateRegistry.createRegistry(registryPort);
			system.setRegistry(registry);

			str = "rmi://" + ip + ":" + registryPort + "/" + servicename;
			Naming.rebind(str, this);
			// System.out.println("ip host:" + ip);
			System.out.println(str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * catch faengt die Ausnahemen des try-Blocks ab
	 * 
	 * @return service service Werte werden zurueckgeliefert
	 */

	public IRemoteHost retrieveService(String hostservice) {
		IRemoteHost service_ = null;
		try {
			service_ = (IRemoteHost) Naming.lookup(hostservice);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return service_;
	}

	/**
	 * Werte von Spielfeld werden uebergeben
	 */

	@Override
	public void tick() throws RemoteException {
		spielfeld.getPlayers().movePlayers();
		spielfeld.repaint();

	}

	/**
	 * Initialisierung von Playerindex, Keycode und Boolean pressed
	 */

	@Override
	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * Bewegung des Spielers
	 */

	@Override
	public void movep2h(int direction) throws RemoteException {
		direction = translate(direction);

		// spielfeld.getPlayers().moveremotePlayers(direction);
		((LanPlayer) (spielfeld.getPlayers().getPlayerList().get(1)))
				.moveremote(direction);

	}

	/**
	 * Parameter fuer die Bewegung
	 * 
	 * @param direction
	 * @return O! 0 wird aufgerufen, sollte allerdings nie vorkommen
	 */

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
		return 0;
	}
}
