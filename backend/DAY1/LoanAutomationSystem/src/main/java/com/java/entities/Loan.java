package com.java.entities;

public class Loan {
	long applicationNo;
	String documents;

	public Loan() {
		super();
	}

	public Loan(long applicationNo, String documents) {
		super();
		this.applicationNo = applicationNo;
		this.documents = documents;
	}

	public long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

}
