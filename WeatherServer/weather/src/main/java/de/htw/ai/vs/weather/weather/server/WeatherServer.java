package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class WeatherServer {

    public static void main(String[] args) {
        System.out.println("Hello weather server");
        
        ////// TEST 

        try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ////////
        
    }
    
    public static void startServer() throws IOException{
    	
    	String clientSentence = "";
    	  String capitalizedSentence = "";
    	  ServerSocket welcomeSocket = new ServerSocket(6789);

    	  while (true) {
    	   Socket connectionSocket = welcomeSocket.accept();
    	   BufferedReader inFromClient =
    	    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    	   DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
    	   clientSentence = inFromClient.readLine();
    	   System.out.println("Received: " + clientSentence);
    	   capitalizedSentence = clientSentence.toUpperCase() + 'n';
    	   outToClient.writeBytes(capitalizedSentence);
    	  }

    	
    }
    
    // start server 
    	// init
    	// create tcp server soket 
    	// wait for request unit shutdown request
    			// handle request with new tread 
    			// call shutdownserver if shutdown request comes in
    
    // init 
    	// read data from csv
    
    // create tcp server socket
    
    // accept requests
    
    // handle request
    
    // send response
    
    // shutdownserver <close socket>
}