package de.htw.ai.vs.weather.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InputValidator {

	private static String DATEFORMAT = "dd.MM.yyyy";
	
	public static boolean isValidDateFormat(String Date){
		
		boolean isValid = false;
	
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
	
		try {
			formatter.parse(Date);
			isValid = true;
		} catch (ParseException e) {

		}
		
		return isValid;
		
	}	
	
}
