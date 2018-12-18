package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import de.htw.ai.vs.weather.weather.response.Request;
import de.htw.ai.vs.weather.weather.response.Response;

public class RequestHandler implements Runnable {

	Socket connectionSocket;
	boolean isDone;
	DataOutputStream outToClient;
	BufferedReader inFromClient;
	Response response;

	public RequestHandler(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	@Override
	public void run() {

		while (!this.isDone) {
			handleRequest();
		}
	}

	public void handleRequest() {

		String clientSentence = "";
		String capitalizedSentence = "";

		try {
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Request request = new Request(connectionSocket);
		clientSentence = request.getRequestData();

		System.out.println("Received: " + clientSentence);
		// String vom Client wird in Capitalletter umgewandelt
		capitalizedSentence = clientSentence.toUpperCase() + '\n';

		// über das outputStream das wir erstellt hatten und n das conection
		// socket gebunden hatten, wird nun die Antwort an den client gesendet.

		try {
			outToClient.writeBytes(capitalizedSentence);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.connectionSocket.close();
		} catch (IOException e) {
			System.out.println("Socket im im RequestHandler konnte nicht geschlossen werden!");
			e.printStackTrace();
		}
		this.isDone = true;
	}

}
