package de.htw.ai.vs.weather.weather.response;

public class Response {

	private String message;
	private int status;
	
	public Response(String message, int status) {
		this.message = message;
		this.status = status;
	}

	protected String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	protected int getStatus() {
		return status;
	}

	protected void setStatus(int status) {
		this.status = status;
	}

	
}
