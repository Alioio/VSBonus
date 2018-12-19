package de.htw.ai.vs.weather.weather.server;

import java.io.IOException;
import java.net.ServerSocket;

import de.htw.ai.vs.weather.weather.storage.Measurements;

public class Server {

	public boolean isShutdown;
	private static Server instance;
	ConnectionHandler connectionHandler;

	public static Server getInstance() {

		if (Server.instance == null) {
			Server.instance = new Server();
		}
		return Server.instance;
	}

	public boolean initServer(String CSVFilePath) {

		boolean initSucess = false;
		this.connectionHandler = ConnectionHandler.getInstance();
		Measurements measurements = Measurements.getInstance();
		initSucess = measurements.init("");
		
		return initSucess;
	}

	public void startServer() {

		ConnectionHandler connectionHander;
		connectionHander = ConnectionHandler.getInstance();

		ServerSocket welcomeSocket = null;

		try {
			welcomeSocket = connectionHander.createServerAcceptSocket();
		} catch (IOException e) {
			System.out.print("Es konnten keine Daten aus dem Socket gelesen werden!");
			e.printStackTrace();
		}
		try {
			connectionHander.forwardAcceptedRequestsToSeperateHandler(welcomeSocket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void shutdownServer(){
		this.isShutdown = true;
		this.connectionHandler.receiveUpdatesAboutServerState(this.isShutdown);
		
	}
}
