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

	ObjectOutputStream outToClient;
	ObjectInputStream ois;
	Request request;
	
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

		 initReader();

		 readRequestFromClient();

		 sendResponseToClient();

		 closeRequest();
	}
	
	private boolean initReader(){
		boolean sucess = false;
		try {
			outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
			ois = new ObjectInputStream(connectionSocket.getInputStream());
			sucess = true;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return sucess;
	}
	
	private boolean readRequestFromClient(){
		
		boolean sucecess = false;
		try {
			request = (Request) ois.readObject();
			sucecess = true;
		} catch (ClassNotFoundException e2) {
			System.out.println(" Klasse nicht gefunden!");
			e2.printStackTrace();
		} catch (IOException e2) {
			System.out.println("");
			e2.printStackTrace();
		}
		return sucecess;
	}
	
	private boolean sendResponseToClient(){
		
		boolean sucess = false;
		Measurements MeasurementData = Measurements.getInstance();
		
		// vorhanden?
		boolean measurementExists = MeasurementData.messurementsForDayExists(this.request.getData());
		
		Response response;
		
		if(!measurementExists){
			response = new Response("Keine Daten für diesen Tag vorhanden! ", 1);
		}else{
			
			boolean measurementComplete = MeasurementData.messurementsCompleteForDay(this.request.getData());
			
			int isComplete = 1;
			if(measurementComplete){
				isComplete = 0;
			}
		
			// Response
			String Messwerte = MeasurementData.getDayMessurements(this.request.getData());		
			response = new Response(Messwerte, isComplete);
			
		}

		try {
			outToClient.writeObject(response);
			sucess = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sucess;
	}
	
	private void closeRequest(){	
		
		try {
			outToClient.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			this.connectionSocket.close();
		} catch (IOException e) {
			System.out.println("Socket im RequestHandler konnte nicht geschlossen werden!");
			e.printStackTrace();
		}
		this.isDone = true;
		
	}
	
	

}
