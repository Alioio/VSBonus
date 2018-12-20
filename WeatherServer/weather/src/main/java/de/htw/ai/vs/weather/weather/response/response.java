package de.htw.ai.vs.weather.weather.response;

import java.io.Serializable;

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5767484264760108275L;
	private String message;
	private int status;

	public Response(String message, int status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String DataComplete = "";
		if (this.status == 0) {
			DataComplete = "Daten für diesen Tag sind vollstaendlig!";
		} else {
			DataComplete = "Daten für diesen Tag sind NICHT vollstaendlig!";
		}

		return message + "\n" + DataComplete;
	}

}
