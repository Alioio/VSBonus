package de.htw.ai.vs.weather.weather.server;

import java.util.LinkedList;

public class WeatherServer {

	private static boolean isShutdown = false;


	public static void main(String[] args) {
		System.out.println("Hello weather server");

		Server ServerInstance = Server.getInstance();

		boolean serverInitSucess = ServerInstance.initServer("");
		
		if (serverInitSucess) {
			ServerInstance.startServer();
		}else{
			System.out.println("Server konnte nicht gestartet werden. Fehler bei der Initialisierung.");
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

	// send response

	// shutdownserver <close socket>
}