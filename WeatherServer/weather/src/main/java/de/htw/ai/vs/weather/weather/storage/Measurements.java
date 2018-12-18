package de.htw.ai.vs.weather.weather.storage;

import java.io.FileNotFoundException;

import de.htw.ai.vs.weather.weather.server.ConnectionHandler;
import de.htw.ai.vs.weather.weather.server.Server;
import de.htw.ai.vs.weather.weather.utils.CSVReader;

public class Measurements {

	private static Measurements instance;

	public static synchronized Measurements getInstance() {

		if (Measurements.instance == null) {
			Measurements.instance = new Measurements();
		}
		return Measurements.instance;
	}

	// init
	public boolean init(String CSVFilePath) {
		boolean initStorgeSucess = false;

		boolean sucessfullRead = readDataFromCSV(CSVFilePath);

		return initStorgeSucess;
	}

	public boolean readDataFromCSV(String CSVFilePath) {

		boolean readSucess = false;

		if (CSVFilePath.length() > 3) {
			CSVReader CSVReader = new CSVReader(CSVFilePath);
			try {
				CSVReader.readFile();
				readSucess = true;
			} catch (FileNotFoundException e1) {
				System.out.println("Die CSV Datei konnte nicht gefunden werden!");
				e1.printStackTrace();
			}
		} else {
			CSVReader CSVReader = new CSVReader();
			try {
				CSVReader.readFile();
				readSucess = true;
			} catch (FileNotFoundException e1) {
				System.out.println("Die CSV Datei konnte nicht gefunden werden!");
				e1.printStackTrace();
			}
		}
		return readSucess;
	}

	// getMeasurementsForDate(date) <error if no 24 measurementpoints available
	// for that day>

	// getAvarateTemp

	// getMinTemp

	// getMaxTemp

	// toString

}
