package de.htw.ai.vs.weather.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
*/

import java.net.Socket;
import java.net.UnknownHostException;
import de.htw.ai.vs.weather.weather.response.Response;;

public class WeatherClient {

	//http://www.java2s.com/Code/Java/Network-Protocol/ServerSocketandSocketforSerializableobject.htm
	static int ch = 0; 
	public static void main(String[] args) {
		
			try {
				startClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void startClient() throws IOException{

		  Socket clientSocket = new Socket("localhost", 6789);  
		  
		  ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		  ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());

		   Response response = new Response("Hi Server", 2);
		   outToServer.writeObject(response);

		   try {
			   response = (Response) inFromServer.readObject();
			   System.out.println("Vom Server: "+response.getMessage()+"   "+response.getStatus());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   inFromServer.close();
		   outToServer.close();
		   clientSocket.close();

	}

}
