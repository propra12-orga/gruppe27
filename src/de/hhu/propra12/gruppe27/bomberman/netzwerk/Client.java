package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.awt.Robot;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.LanPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author 
 * @version 1.0
 * Klasse Client
 */

public class Client extends UnicastRemoteObject implements IRemoteClient {

	private static final long serialVersionUID = 1L;
	IRemoteHost service;
	SysEinst system = SysEinst.getSystem();
	Spielfeld spielfeld = null;
	
	public Client() throws RemoteException {

		publishClient();

		service = retrieveHostService();
		service.joingame();

	}
	
	/**
	 * Clientname, registryPort, servicename werden erfragt
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
	 * Spielfeld wird uebergeben
	 */
	
	@Override
	public void sendSpielfeld(Spielfeld spielfeld) {
//		System.out.println("Client:");
//		System.out.println("Players.PlayerList.size() ="
//				+ spielfeld.Players.PlayerList.size());
//		for (AbstractPlayer ap : spielfeld.Players.PlayerList)
//			System.out.println("ap.isAlive()=" + ap.isAlive());
		this.spielfeld = spielfeld;
		spielfeld.initImages();
		// spielfeld.switchKeyset();

		GameWindow s = new GameWindow(0, true, spielfeld);

		spielfeld.initPlayer();
		spielfeld.setsystem(system);
		//TODO startgame entfernen, wenn remoteaufruf tick funktioniert.
		spielfeld.startgame();
		System.out.println("send game received");

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
	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed) throws RemoteException{
//		Robot r = new Robot();
//		int [][] keycodeMapping = new int [2][5]; //2 Spieler, je 5 keycodes.
//		for (int i=0;i<5;++i){
//		//	Keyset k1 = ((LanPlayer)(;
//			
//		// ((LanPlayer)(spielfeld.Players.getPlayerList().get(0));
//		}
//		
//		if (playerindex==1){
//			AbstractPlayer ap = spielfeld.Players.getPlayerList().get(playerindex);
//			
//		r.keyPress(direction);
//			
//		}else if(playerindex==0){
//			
//		}
	}

	@Override
	public void tick() throws RemoteException {
		spielfeld.Players.movePlayers();
		spielfeld.repaint();
		
	}

}
