package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	IRemote service;

	public Client() {
		try {
			IRemote service = (IRemote) Naming.lookup("rmi://"
					+ IRemote.hostname + ":" + IRemote.registryPort + "/"
					+ IRemote.servicename);

			this.service = service;
			service.echo("service");
			service.joingame();

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
}
