package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import de.htw.ai.vs.weather.weather.response.Request;
import de.htw.ai.vs.weather.weather.response.Response;
import de.htw.ai.vs.weather.weather.storage.Measurements;

public class RequestHandler implements Runnable {

	Socket connectionSocket;
	boolean isDone;
	/*
	DataOutputStream outToClient;
	BufferedReader inFromClient;
	*/
	
	ObjectOutputStream outToClient;
	ObjectInputStream ois;
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
		
		try {
			 outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
			 ois = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			response = (Response) ois.readObject();
			  System.out.println("Vom Server: "+response.getMessage()+"   "+response.getStatus());
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println(" Klasse nicht gefunden!");
			e2.printStackTrace();
		} catch (IOException e2) {
			System.out.println(" IO E!");
			e2.printStackTrace();
		}
		
		
		Response anDenClient = new Response("bye bye Client", 1);
			try {
				outToClient.writeObject(anDenClient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		try {
			outToClient.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		//////

		try {
			this.connectionSocket.close();
			// System.out.println("Socket geschlossen?
			// "+this.connectionSocket.isClosed()+"
			// "+this.connectionSocket.getPort());
		} catch (IOException e) {
			System.out.println("Socket im RequestHandler konnte nicht geschlossen werden!");
			e.printStackTrace();
		}
		this.isDone = true;
	}

}
