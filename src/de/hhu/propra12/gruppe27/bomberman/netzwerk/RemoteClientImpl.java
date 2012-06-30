package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class RemoteClientImpl extends UnicastRemoteObject implements
		IRemoteClient {
	private static final long serialVersionUID = 1L;

	Client client;

	protected RemoteClientImpl(Client client) throws RemoteException {
		this.client = client;
		try {
			LocateRegistry.createRegistry(registryPort);
			Naming.rebind("rmi://" + clientname + "/" + servicename, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSpielfeld(Spielfeld spielfeld) {

		// client.sendgame(spielfeld);

	}

}
