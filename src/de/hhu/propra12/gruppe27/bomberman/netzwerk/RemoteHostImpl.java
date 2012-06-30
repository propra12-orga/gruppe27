package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteHostImpl extends UnicastRemoteObject implements IRemoteHost {
	private static final long serialVersionUID = 1L;

	Host host;

	protected RemoteHostImpl(Host host) throws RemoteException {
		this.host = host;
		try {
			System.out.println("RHI: " + "rmi://" + hostname + "/"
					+ servicename);
			LocateRegistry.createRegistry(registryPort);
			Naming.rebind("rmi://" + hostname + "/" + servicename, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String echo(String s) throws RemoteException {
		String tmp = "server " + s;
		return tmp;
	}

	@Override
	public void joingame() {

		host.startgame();

	}

}
