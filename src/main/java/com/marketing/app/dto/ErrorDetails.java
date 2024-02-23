package com.marketing.app.dto;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String messege;
	private String details;
    
	//Constructor
	public ErrorDetails(Date timestamp, String messege, String details) {
		this.timestamp = timestamp;
		this.messege = messege;
		this.details = details;
	}

	//Getters
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessege() {
		return messege;
	}

	public String getDetails() {
		return details;
	}




}
