package com.business.ExceptionHandler;

public class ResponseInfo {
	
	private String info;
	private String message;

	

	public ResponseInfo(String info, String message) {
		super();
		this.message = message;
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String Message) {
		this.message = Message;
	}

}
