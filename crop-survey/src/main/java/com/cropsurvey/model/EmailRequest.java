package com.cropsurvey.model;

import javax.mail.MessagingException;

public class EmailRequest {

	private String to;
	private String subject;
	private String message;
	
	public EmailRequest(String to, String subject, String message) throws MessagingException{
		super();
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	public EmailRequest() {
		
	}

	public String getTo() {
		return to;
	}

	public void setTo(String from) {
		this.to = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EmailRequest [to=" + to + ", subject=" + subject + ", message=" + message + "]";
	}
	
	
}
