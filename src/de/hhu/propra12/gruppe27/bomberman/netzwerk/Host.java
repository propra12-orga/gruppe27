package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;

public class Host {

	public Host() {

		try {
			IRemote service = new RemoteImpl(this);

			// System.out.println(service.echo("hallo test"));
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	public void startgame() {
		GameWindow s = new GameWindow(0);

	}
}
