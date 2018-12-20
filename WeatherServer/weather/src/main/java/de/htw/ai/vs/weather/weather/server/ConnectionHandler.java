package de.htw.ai.vs.weather.weather.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {

	private ServerSocket acceptSocket;
	private static ConnectionHandler instance;
	private static int acceptport;
	private boolean shutdownRequest; 
	
	public static ConnectionHandler getInstance() {

		if (ConnectionHandler.instance == null) {
			ConnectionHandler.instance = new ConnectionHandler();
		}
		return ConnectionHandler.instance;

	}

	public void forwardAcceptedRequestsToSeperateHandler() throws IOException {
		
		while(!this.shutdownRequest){
			System.out.println("Weather Server lauscht auf Port "+this.getAcceptSocket().getLocalPort()+"...\n");
			Socket connectionSocket = this.getAcceptSocket().accept();
			handleRequest(connectionSocket);
		}
	}

	public void handleRequest(Socket connectionSocket) throws IOException {

		RequestHandler requestHandler = new RequestHandler(connectionSocket);
		Thread newTread = new Thread(requestHandler);
		newTread.start();
		System.out.println("Neuer Request wird mit Port: "+connectionSocket.getPort()+" Von Thread "+newTread.getId()+" bearbeitet!");

	}

	protected ServerSocket getAcceptSocket() {
		return acceptSocket;
	}

	protected void setAcceptSocket(int acceptPort) throws IOException {
		this.acceptSocket = new  ServerSocket(acceptPort);
	}

	protected static int getAcceptport() {
		return acceptport;
	}

	protected static void setAcceptport(int acceptport) {
		ConnectionHandler.acceptport = acceptport;
	}
	
	protected void receiveUpdatesAboutServerState(boolean serverState){
		System.out.println("Server shutdown request: "+serverState);
	}
	
}
