package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.LanPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author
 * @version 1.0 Klasse Host
 */

public class Host extends UnicastRemoteObject implements IRemoteHost {

	private static final long serialVersionUID = 1L;
	GameWindow gw;

	IRemoteClient service = null;
	Spielfeld spielfeld;

	public Host() throws RemoteException {
		publishHost();

		// GameWindow s = new GameWindow(0);
	}

	/**
	 * Client hat sich beim Host angemeldet, �bergabe des Spielfeldes
	 */

	@Override
	public void joingame() {

		retrieveClientService();
		gw = new GameWindow(0);

		this.spielfeld = gw.getspielfeld();
		// Löschen service.sendSpielfeld(spielfeld);
		// spielfeld.initPlayer();
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
	 * 
	 * @throws RemoteException
	 */

	public void publishHost() throws RemoteException {

		try {
			LocateRegistry.createRegistry(registryPort);
			Naming.rebind("rmi://" + hostname + "/" + servicename, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suche nach remote-services des Clients
	 */

	public void retrieveClientService() {
		// System.out.println("fetch:    rmi://" + IRemoteClient.clientname +
		// ":"
		// + IRemoteClient.registryPort + "/" + IRemoteClient.servicename);
		try {
			service = (IRemoteClient) Naming.lookup("rmi://"
					+ IRemoteClient.clientname + ":"
					+ IRemoteClient.registryPort + "/"
					+ IRemoteClient.servicename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Client did not yet publish.");
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		try {
			service.tick();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void movep2c(int direction) throws RemoteException {
		// TODO Auto-generated method stub
		LanPlayer p2c = (LanPlayer) spielfeld.getPlayers().PlayerList.get(1);
		p2c.move(direction);
	}

}
