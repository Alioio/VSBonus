package de.htw.ai.vs.weather.weather.storage;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import de.htw.ai.vs.weather.weather.bean.MeasurementPoint;
import de.htw.ai.vs.weather.weather.server.ConnectionHandler;
import de.htw.ai.vs.weather.weather.server.Server;
import de.htw.ai.vs.weather.weather.utils.CSVContentReadHelper;
import de.htw.ai.vs.weather.weather.utils.CSVReader;

public class Measurements {

	private static Measurements instance;
	private ConcurrentHashMap<String,LinkedList<String>> measurementMap = new ConcurrentHashMap<String,LinkedList<String>>(); 
	
	public static synchronized Measurements getInstance() {

		if (Measurements.instance == null) {
			Measurements.instance = new Measurements(new ConcurrentHashMap());
		}
		return Measurements.instance;
	}

	private Measurements(ConcurrentHashMap<String, LinkedList<String>> measurementMap) {
		this.measurementMap = measurementMap;
	}

	public boolean init(String CSVFilePath) {
		boolean initStorgeSucess = false;

		boolean sucessfullRead = readDataFromCSV(CSVFilePath);

		if(sucessfullRead){
			
			
		}
		
		return sucessfullRead;
	}

	public boolean readDataFromCSV(String CSVFilePath) {

		boolean readSucess = false;

		if (CSVFilePath.length() > 3) {
			CSVReader CSVReader = new CSVReader(CSVFilePath);
			try {
				CSVReader.readFileToArrayList();
				readSucess = true;
			} catch (FileNotFoundException e1) {
				System.out.println("Die CSV Datei konnte nicht gefunden werden!");
				e1.printStackTrace();
			}
		} else {
			CSVReader CSVReader = new CSVReader();
			try {
				CSVReader.readFileToArrayList();		
				CSVContentReadHelper HashMap = new CSVContentReadHelper( CSVReader.getReadedValues());
				HashMap.mapListToHashMap();
				
				this.measurementMap.putAll(HashMap.getMap());
			
				
				readSucess = true;
			} catch (FileNotFoundException e1) {
				System.out.println("Die CSV Datei konnte nicht gefunden werden!");
				e1.printStackTrace();
			}
		}
		return readSucess;
	}

	protected ConcurrentHashMap getMeasurementMap() {
		return measurementMap;
	}

	public boolean messurementsForDayExists(String Date){
		
		boolean messurmentsExists = false;
		
		if(this.measurementMap.containsKey(Date)){
			messurmentsExists = true;
		}
		
		return messurmentsExists;
	}
	
	public boolean messurementsCompleteForDay(String Date){
		boolean isComplete = false;
		
		if(this.measurementMap.get(Date).size() > 23){
			isComplete = true;
		}
		
		return isComplete;
	}
	
	public LinkedList<MeasurementPoint> getMesurementsForDay(String Date){
		LinkedList<MeasurementPoint> messurements = new LinkedList<MeasurementPoint>(); 

		for(int i = 0; i < this.measurementMap.get(Date).size(); i++){
			String Temperature = this.measurementMap.get(Date).get(i);
			int temperature = Integer.parseInt(Temperature);
			
			MeasurementPoint meassurement = new MeasurementPoint(Date,i,temperature);
			messurements.add(meassurement);
		}
		
		return messurements;
	}

	public float getAvarateTemperatureForDay(String Date){
		
		int sumTemp = 0;
		
		for(int i = 0; i < this.measurementMap.get(Date).size(); i++){
			String Temperature = this.measurementMap.get(Date).get(i);
			int temperature = Integer.parseInt(Temperature);
			sumTemp += temperature;	
		}
		
		float avgTemp = (float)(sumTemp/this.measurementMap.get(Date).size());	
		return avgTemp;
		
	}
	
	public int getMinTempOfDay(String Date){
		
		String MinTemp = this.measurementMap.get(Date).get(0);
		int minTemp = Integer.parseInt(MinTemp);
		
		for(int i = 0; i < this.measurementMap.get(Date).size(); i++){
			
			String Temperature = this.measurementMap.get(Date).get(i);
			int temperature = Integer.parseInt(Temperature);

			if(temperature < minTemp ){
				minTemp = temperature;
			}
		}
		System.out.println("Min temp: "+minTemp);
		return minTemp;
		
	}

	public int getMaxTempOfDay(String Date){
		
		String MaxTemp = this.measurementMap.get(Date).get(0);
		int maxTemp = Integer.parseInt(MaxTemp);
		
		for(int i = 0; i < this.measurementMap.get(Date).size(); i++){
			
			String Temperature = this.measurementMap.get(Date).get(i);
			int temperature = Integer.parseInt(Temperature);

			if(temperature > maxTemp ){
				maxTemp = temperature;
			}
		}
		return maxTemp;
		
	}
	
	protected void setMeasurementMap(ConcurrentHashMap measurementMap) {

		this.measurementMap = measurementMap;
	}

	public boolean measurementsForDayComplete(String Date){
		
		boolean isComplete = false;
		
		LinkedList <MeasurementPoint> MeasurementPointList =  getMesurementsForDay(Date);
		
		if(MeasurementPointList.size() > 24){
			isComplete = true;
		}
		
		return isComplete;
	}
	
	public String getDayMessurements(String Date){
		
		String summary = ""; 
		String messurements = ""; 
		LinkedList <MeasurementPoint> MeasurementPointList =  getMesurementsForDay(Date);
	
		for(MeasurementPoint m : MeasurementPointList ){
			messurements += m.toString();
		}
		
		int maxTemp = getMaxTempOfDay(Date);
		int minTemp = getMinTempOfDay(Date);
		float avgTemp = getAvarateTemperatureForDay(Date);
		
		summary = "\nMaximaltemperature: "+maxTemp+"\n"+"Minimaltemperatur: "+minTemp+"\n"+"Durschnittliche Temperatur:"+avgTemp+"\n"+"\nMessurements:\n "+messurements;
		
		return summary;
	}
	
}
