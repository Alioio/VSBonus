package de.htw.ai.vs.weather.weather.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Request {

	Socket connectionSocket;
	BufferedReader inFromClient;
	
	public Request(Socket connectionSocket){
		this.connectionSocket = connectionSocket;
	}
	
	//readRequestData return String
	public String getRequestData(){
		String requestData = "";
		BufferedReader inFromClient = readDataFromConnectionSocket();
		// Vom BufferedReader, in das ja die Daten vom Client gespeichert
		// wurden, wird nun eine Zeile gelesen und an ein String geschrieben.
				try {
					requestData = inFromClient.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		return requestData;
	}
	
	private BufferedReader readDataFromConnectionSocket(){
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			return inFromClient;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
}
