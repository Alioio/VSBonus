package de.htw.ai.vs.weather.weather.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import de.htw.ai.vs.weather.weather.utils.CSVReader;

public class WeatherServer {

	private static boolean isShutdown = false;

	public static void main(String[] args) {
		System.out.println("Hello weather server");

		Server ServerInstance = Server.getInstance();

		ServerInstance.initServer("");

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