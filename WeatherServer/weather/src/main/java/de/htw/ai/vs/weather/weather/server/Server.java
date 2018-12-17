package de.htw.ai.vs.weather.weather.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public boolean isShutdown;
	private Server instance;
	ConnectionHandler connectionHandler;

	public Server getInstance() {

		if (this.instance == null) {
			this.instance = new Server();
			this.isShutdown = false;

			try {
				this.connectionHandler = ConnectionHandler.class.newInstance();
			} catch (InstantiationException e) {
				System.out.println("Es konnte kein Instanz von ConnectionHandler erzeugt werden");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return this;
		} else {
			return this;
		}
	}

	public static void startServer() {

		ConnectionHandler connectionHander = null;
		try {
			connectionHander = ConnectionHandler.class.newInstance();
		} catch (InstantiationException e) {
			System.out.println("Es konnte kein Instanz von ConnectionHandler erzeugt werden!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ServerSocket welcomeSocket = null;
			try {
				welcomeSocket = connectionHander.createServerAcceptSocket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connectionHander.forwardAcceptedRequestsToSeperateHandler(welcomeSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
