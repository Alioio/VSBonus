package de.htw.ai.vs.weather.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import de.htw.ai.vs.weather.weather.response.Request;
import de.htw.ai.vs.weather.weather.response.Response;

public class Client {

	String userInput;
	Socket clientSocket;
	ObjectInputStream inFromServer;
	ObjectOutputStream outToServer;
	Response response;

	public Client(String Date) {
		this.userInput = Date;
	}

	public void InitRequest() {

		System.out.println("Eingabe! " + this.userInput);
		if (validateUserInput()) {

			boolean serverConnectionEstablished = false;
			serverConnectionEstablished = establischServerConnection();

			if (serverConnectionEstablished) {
				
				boolean obInStream = initObjectInputSream();
				boolean obOutStream = initObjectOutputStream();
				
				if (obInStream && obOutStream) {

						boolean requestSent = false;
						requestSent = sendRequestToServer();

						if (requestSent) {
							getResponseFromServer();
							closeConnection();
						}
				}
			}
		} else {
			System.out.println("Eingabe nicht valid! ");
		}
	}

	public boolean validateUserInput() {

		boolean isValid = false;
		isValid = InputValidator.isValidDateFormat(this.userInput);
		return isValid;
	}

	public boolean establischServerConnection() {
		boolean connectionSucessed = false;

		try {
			this.clientSocket = new Socket("localhost", 6789);
			connectionSucessed = true;
		} catch (UnknownHostException e) {
			System.out.println("Der angegenen Server konnte nicht gefunden werden! ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Es konnten keine Daten vom Socket gelesen werden! ");
			e.printStackTrace();
		}

		System.out.println("Server connection: "+connectionSucessed);
		return connectionSucessed;
	}

	public boolean initObjectInputSream() {
		boolean sucessed = false;
		
		try {
			this.inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			sucessed = true;
		} catch (IOException e) {
			System.out.println("Es konnten keine Daten gelesen werden.");
			e.printStackTrace();
		}
		
		return sucessed;
	}

	public boolean initObjectOutputStream() {
		boolean sucessed = false;

		try {
			this.outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			sucessed = true;
		} catch (IOException e) {
			System.out.println("Es konnten keine Daten gelesen werden.");
			e.printStackTrace();
		}

		return sucessed;
	}

	public boolean sendRequestToServer() {

		boolean sucessed = false;

		Request request = new Request(this.userInput);

		System.out.println("USER INPUT: " + this.userInput);
		try {
			this.outToServer.writeObject(request);
			sucessed = true;
		} catch (IOException e) {
			System.out.println("Es konnten keine Daten gesendet werden!");
			e.printStackTrace();
		}

		return sucessed;
	}

	public boolean getResponseFromServer() {
		boolean sucessed = false;

		try {
			response = (Response) inFromServer.readObject();
			System.out.println(response.toString());
		} catch (ClassNotFoundException e) {
			System.out.println("Vom Server Empfangenge Format konnte nicht gelesenen werden!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Fehler beim lesen der Daten vom Server!");
			e.printStackTrace();
		}

		return sucessed;
	}

	public void closeConnection() {

		try {
			inFromServer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schlißen des Input Streams!");
			e.printStackTrace();
		}

		try {
			outToServer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schlißen des Output Streams!");
			e.printStackTrace();
		}

		try {
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schlißen des Sockets!");
			e.printStackTrace();
		}

	}

}
