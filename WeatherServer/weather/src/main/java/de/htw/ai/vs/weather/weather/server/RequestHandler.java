package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler implements Runnable {

	Socket connectionSocket;

	public RequestHandler(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		handleRequest();
	}
	
	public void handleRequest() {

		String clientSentence = "";
		String capitalizedSentence = "";

		// Das was der Thred dann machen sollte:

		// hier werden die Daten die vom connectionSocket gelesen und in eine
		// BufferedReader gespeichert.
		BufferedReader inFromClient = null;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Hier wird ein OutputStream erzeugt. Diesem werden die Daten
		// geschrieben welche über den connectin Socket zurück an den Client
		// gesenden werden soll.
		DataOutputStream outToClient = null;
		try {
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Vom BufferedReader, in das ja die Daten vom Client gespeichert
		// wurden, wird nun eine Zeile gelesen und an ein String geschrieben.
		try {
			clientSentence = inFromClient.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	}

	

}
