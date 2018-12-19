package de.htw.ai.vs.weather.weather.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class CSVContentReadHelper {

	HashMap map;
	ArrayList<String> ReadedValues;
	private int MINTEMP = -45;
	private int MAXTEMT = 50;
	private String DATEFORMAT = "dd.MM.yyyy";

	public CSVContentReadHelper(ArrayList ReadValues) {
		this.ReadedValues = ReadValues;
		this.map = new HashMap<String,LinkedList<String>>();
	}

	private boolean isValidDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(this.DATEFORMAT);
		boolean validDate = false;
		try {
			formatter.parse(date);
			validDate = true;

		} catch (ParseException e) {

		}

		return validDate;
	}

	public boolean isValidTemperatur(String Temperature) {

		boolean validTemperature = false;
		int temperature;

		try {
			temperature = Integer.parseInt(Temperature);
		} catch (NumberFormatException e) {
			temperature = this.MAXTEMT + 2;
		}

		if (temperature >= this.MINTEMP && temperature <= this.MAXTEMT) {
			validTemperature = true;
		}

		return validTemperature;
	}

	public boolean isLineBreak(String LineBreak) {
		boolean lineBreak = false;

		return lineBreak;
	}

	public void mapListToHashMap() {

		LinkedList<String> temperatures = new LinkedList<String>();

		
		for (String Entry : this.ReadedValues) {

			if (this.isValidDate(Entry)) {
				
				LinkedList<String> local_temperatures = new LinkedList<String>();
				
				for(String s : temperatures){
					local_temperatures.add(s);
				}
				
				this.map.put(Entry, local_temperatures);
				temperatures.clear();
			}

			if (this.isValidTemperatur(Entry)) {
				temperatures.add(Entry);
			}
		}


	}

	
	public HashMap getMap() {
		//System.out.println("#"+this.map.keySet());
		//System.out.println("##"+this.map.values());
		return map;
	}

}
