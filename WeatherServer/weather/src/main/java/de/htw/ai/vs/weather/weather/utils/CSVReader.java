package de.htw.ai.vs.weather.weather.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

	private String Filepath;
	private String Delimiter = ",";
	public ArrayList<String> ReadedValues;

	public CSVReader() {
		String UsrHomeDir = System.getProperty("user.home");
		String FileName = "weather.csv";
		String FileSeperator = System.getProperty("file.separator");
		this.Filepath = UsrHomeDir + FileSeperator + FileName;
	}

	public CSVReader(String Filepath) {
		this.Filepath = Filepath;
	}

	public void readFileToArrayList() throws FileNotFoundException {
		ReadedValues = new ArrayList<String>();
		Scanner scanner = new Scanner(new File(this.Filepath));
		scanner.useDelimiter(Delimiter);

		while (scanner.hasNext()) {
			this.ReadedValues.add(scanner.next());
		}	
		scanner.close();
	}
	
	public ArrayList getReadedValues(){
		
		/*
		for(String Entry : this.ReadedValues){
			System.out.print(Entry+" | ");
		}
		*/
		return this.ReadedValues;
	}

}
