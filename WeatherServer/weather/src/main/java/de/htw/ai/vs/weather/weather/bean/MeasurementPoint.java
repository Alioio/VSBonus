package de.htw.ai.vs.weather.weather.bean;

import java.util.Date;

public class MeasurementPoint {

	String Date;
	int hour;
	int temperature;
	boolean complete;
	
	public MeasurementPoint(String day, int hour, int temperature){
		this.Date = day;
		this.hour = hour;
		this.temperature = temperature;
 	}
	
	@Override
	public String toString(){
		return "\n[ Date: "+this.Date+" Hour: "+hour+" Temperature: "+this.temperature+" c° ]";
	}
	
}
