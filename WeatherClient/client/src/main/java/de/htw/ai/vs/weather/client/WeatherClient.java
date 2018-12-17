package de.htw.ai.vs.weather.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class WeatherClient {

	public static void main(String[] args) {
		
		try {
			startClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void startClient() throws UnknownHostException, IOException{
		
		  String sentence = "";
		  String modifiedSentence = "";
		  
		// Ein  BufferedReader wird erzeugt, der wartet dass in die CLI etwas eingegebeb wird. 
		// Diesem wird System.in übergben. Sobald von System.in etwas reinkommt wird es in das BufferedReader geschrieben.  
		  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
		  
		// client socket
		  Socket clientSocket = new Socket("localhost", 6789);  
		  
		//in dieses outputstream werden die daten verpackt für den server. Die daten sind im client Sockets outputSream
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
		  
		// Vom Server kommt ein stream (wird vom Server als outputstream an das InputStream vom client socket verpackt.). 
		// Dieser wird von einem InputStreamReader gelesen und an eine BufferedReader übergeben
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
		  
		// Dt	  
		  sentence = inFromUser.readLine();
		  
		// In das OutputStream wird nun der String vom Nuter geschrieben.    
		  outToServer.writeBytes(sentence + '\n');
		  
		  
		  modifiedSentence = inFromServer.readLine();
		  System.out.println("FROM SERVER: " + modifiedSentence);	  
		  clientSocket.close();
		 
	}

}
