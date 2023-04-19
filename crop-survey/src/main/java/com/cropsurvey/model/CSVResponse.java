package com.cropsurvey.model;

public class CSVResponse {
	 private String message;

	  public CSVResponse(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
