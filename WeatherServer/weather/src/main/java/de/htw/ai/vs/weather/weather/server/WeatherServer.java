package de.htw.ai.vs.weather.weather.server;

import java.util.LinkedList;

public class WeatherServer {

	private final static int ACCEPTPORT = 6789;
	
	private static boolean isShutdown = false;

	public static void main(String[] args) {
	
		Server ServerInstance = Server.getInstance();

		boolean serverInitSucess = ServerInstance.initServer("");
		
		if (serverInitSucess) {
			System.out.println("Initialisierung erfolgreich!");
			ServerInstance.startServer(ACCEPTPORT);
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