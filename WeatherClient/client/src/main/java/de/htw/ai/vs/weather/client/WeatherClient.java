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

	public static void main(String[] args) {

		
		Client Client = new Client(args[0]);
		Client.InitRequest();
	}

}
