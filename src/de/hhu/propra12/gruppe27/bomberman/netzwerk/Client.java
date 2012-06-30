package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class Client {

	IRemoteHost service;

	public Client() {
		try {
			System.out.println("rmi://" + IRemoteHost.hostname + ":"
					+ IRemoteHost.registryPort + "/" + IRemoteHost.servicename);
			RemoteHostImpl rhi = new RemoteHostImpl(new Host());
			IRemoteHost service = (IRemoteHost) Naming.lookup("rmi://"
					+ IRemoteHost.hostname + ":" + IRemoteHost.registryPort
					+ "/" + IRemoteHost.servicename);

			this.service = service;
			// service.echo("service");
			// service.joingame();

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

	}

	public void sendgame(Spielfeld spielfeld) {

		GameWindow s = new GameWindow(0, true, spielfeld.getsystem(), spielfeld);
	}

}
