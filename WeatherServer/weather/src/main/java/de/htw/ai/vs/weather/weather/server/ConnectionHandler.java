package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {

	private ServerSocket acceptSocket;
	private boolean isShutdown;
	private ConnectionHandler instance;

	public ConnectionHandler getInstance() throws IOException {

		if (this.instance == null) {
			this.instance = new ConnectionHandler();
			this.acceptSocket = new ServerSocket();
			this.isShutdown = false;
			return this;
		} else {
			return this;
		}

	}

	public ServerSocket createServerAcceptSocket() throws IOException {
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

		Thread newTread =  new Thread(new RequestHandler(connectionSocket));
		newTread.start();
	//	NewRequest.handleRequest(connectionSocket);

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
