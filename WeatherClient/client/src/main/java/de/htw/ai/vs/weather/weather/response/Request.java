package de.htw.ai.vs.weather.weather.response;

import java.io.Serializable;

public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3313015635973457262L;
	String Data;
	
	public Request(String data) {
		Data = data;
	}
	
	public String getData(){
		return this.Data;
	}
	
}
