package com.stacksimplify.restservices.exceptions;

import java.util.Date;

public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errorDetails;
	
	public CustomErrorDetails(Date date, String message, String errorDetails) {
		super();
		this.timestamp = date;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
}
