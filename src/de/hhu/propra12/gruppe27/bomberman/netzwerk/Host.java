package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.LanPlayer;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class Host extends UnicastRemoteObject implements IRemoteHost {

	private static final long serialVersionUID = 1L;

	IRemoteClient service=null;

	public Host() throws RemoteException {
		publishHost();
		
//		GameWindow s = new GameWindow(0);
	}



	@Override
	public void joingame() {
		/*
		 * Client hat sich beim Host angemeldet (Client joined Game)
		 * suche nach remote-services des Clients
		 */
		retrieveClientService();
		GameWindow gw = new GameWindow(0);
		// Uebergabe des Spielfeldes an den Client
		try {
					Spielfeld spielfeld = gw.getspielfeld();
					service.sendSpielfeld(spielfeld);
					spielfeld.initPlayer();
					
					for (AbstractPlayer ap : spielfeld.Players.getPlayerList()){
						((LanPlayer)ap).h = this;
					}					
			System.out.println("Host aufruf: startgame");
//					spielfeld.startgame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void publishHost() throws RemoteException {

		try {
			LocateRegistry.createRegistry(registryPort);
			Naming.rebind("rmi://" + hostname + "/" + servicename, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	// suche nach remote-services des Clients
	public void retrieveClientService() {
//		System.out.println("fetch:    rmi://" + IRemoteClient.clientname + ":"
//				+ IRemoteClient.registryPort + "/" + IRemoteClient.servicename);
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


	@Override
	public void clientKeyPressed(int keycode) throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void clientKeyReleased(int keycode) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
		
	public void hostKeyUpdate(int playerindex, int keycode, boolean pressed){
		try {
			service.hostKeyUpdate(playerindex, keycode, pressed);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void tick(){
		try {
			service.tick();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
