package com.java.entities;

public class Documents {
	int applicationNo;
	String document1;
	String document2;
	public Documents() {
		super();
	}
	public Documents(int applicationNo, String document1, String document2) {
		super();
		this.applicationNo = applicationNo;
		this.document1 = document1;
		this.document2 = document2;
	}
	public int getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getDocument1() {
		return document1;
	}
	public void setDocument1(String document1) {
		this.document1 = document1;
	}
	public String getDocument2() {
		return document2;
	}
	public void setDocument2(String document2) {
		this.document2 = document2;
	}
	

}
