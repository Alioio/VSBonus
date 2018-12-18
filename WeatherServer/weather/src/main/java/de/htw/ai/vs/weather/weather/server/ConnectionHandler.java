package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import de.htw.ai.vs.weather.weather.response.Response;

public class ConnectionHandler {

	private ServerSocket acceptSocket;
	private boolean isShutdown;
	private static ConnectionHandler instance;
	private Response response;

	public static ConnectionHandler getInstance() {

		if (ConnectionHandler.instance == null) {
			ConnectionHandler.instance = new ConnectionHandler();
		}

		return ConnectionHandler.instance;

	}

	public ServerSocket createServerAcceptSocket() throws IOException {
		this.acceptSocket = new ServerSocket();
		this.isShutdown = false;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		return welcomeSocket;
	}

	public void forwardAcceptedRequestsToSeperateHandler(ServerSocket welcomeSocket) throws IOException {

		while (!isShutdown) {
			Socket connectionSocket = welcomeSocket.accept();
			handleRequest(connectionSocket);
		}
	}

	public void handleRequest(Socket connectionSocket) throws IOException {

		RequestHandler requestHandler = new RequestHandler(connectionSocket);
		Thread newTread = new Thread(requestHandler);
		newTread.start();
		System.out.println("Wird bearbeiten von Thread: " + newTread.getId());

		if (requestHandler.isDone) {
			newTread.interrupt();
			System.out.print("Thread schon gestoppt!");
		}
		// NewRequest.handleRequest(connectionSocket);

	}

	protected ServerSocket getAcceptSocket() {
		return acceptSocket;
	}

	protected void setAcceptSocket(ServerSocket acceptSocket) {
		this.acceptSocket = acceptSocket;
	}

	protected boolean isShutdown() {
		return isShutdown;
	}

	protected void setShutdown(boolean isShutdown) {
		this.isShutdown = isShutdown;
	}

}
