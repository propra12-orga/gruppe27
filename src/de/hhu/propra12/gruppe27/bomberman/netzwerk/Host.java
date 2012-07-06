package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Keyset;
import de.hhu.propra12.gruppe27.bomberman.core.LanPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse Host
 * Vererbung von IRemoteHost
 * Spielfeld, RegistryPort und servicename werden uebergeben
 */

public class Host extends UnicastRemoteObject implements IRemoteHost {

	private static final long serialVersionUID = 1L;
	GameWindow gw;
	String hostservice = "host service not yet published";
	IRemoteClient service = null;
	Spielfeld spielfeld;

	public Host(int registryPort, String servicename) throws RemoteException {
		publishHost(registryPort, servicename);

		// GameWindow s = new GameWindow(0);
	}

	/**
	 * Client hat sich beim Host angemeldet, uebergabe des Spielfeldes
	 */

	@Override
	public void joingame(String strService) {

		retrieveClientService(strService);
		gw = new GameWindow(0);

		this.spielfeld = gw.getspielfeld();

		System.out.println(service);
		System.out.println("sysref h:" + SysEinst.getSystem());
		SysEinst.getSystem().setRemoteClient(service);

		for (AbstractPlayer ap : spielfeld.getPlayers().getPlayerList()) {
			((LanPlayer) ap).h = this;
		}
		System.out.println("Host aufruf: startgame");
		// spielfeld.startgame();

	}
	
	

	@Override
	public Level getLevel() {
		return gw.getspielfeld().getLevel();
	}

	/**
	 * Initialisierung des registryPort und des servicenamen
	 * @throws RemoteException
	 * Regestrierung des Hosts
	 */

	public void publishHost(int registryPort, String servicename)
			throws RemoteException {

		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			// LocateRegistry.getRegistry(registryPort);
			LocateRegistry.createRegistry(registryPort);
			String str = "rmi://" + ip + ":" + registryPort + "/" + servicename;
			Naming.rebind(str, this);
			// System.out.println("ip host:" + ip);
			hostservice = str;
			System.out.println(str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public String getServiceURL() {
		return hostservice;
	}

	/**
	 * Suche nach remote-services des Clients
	 */

	public void retrieveClientService(String strService) {
		// System.out.println("fetch:    rmi://" + IRemoteClient.clientname +
		// ":"
		// + IRemoteClient.registryPort + "/" + IRemoteClient.servicename);
		try {

			// besser ist folgendes:
			// service = (IRemoteClient) LocateRegistry
			// .getRegistry("hostip", 1099).lookup(strService);
			service = (IRemoteClient) Naming.lookup(strService);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Client did not yet publish.");
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * try fuer die Ausnahmebehandlung
	 */

	public void tick() {
		try {
			service.tick();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Bewegung wird bestimmt
	 */

	@Override
	public void movep2c(int direction) throws RemoteException {
		direction = translate(direction);

		// spielfeld.getPlayers().moveremotePlayers(direction);

		((LanPlayer) (spielfeld.getPlayers().getPlayerList().get(1)))
				.moveremote(direction);

	}
	
	/**
	 * @see client
	 * @param direction
	 * @return
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
		System.out.println("host: translate: " + direction);

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
