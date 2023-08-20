package com.java.entities;

import java.util.ArrayList;

public class Documents {
	int applicationId;
	ArrayList<String> documents = new ArrayList<>();
	public Documents() {
		super();
	}
	public Documents(int applicationId, ArrayList<String> documents) {
		super();
		this.applicationId = applicationId;
		this.documents = documents;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public ArrayList<String> getDocuments() {
		return documents;
	}
	public void setDocuments(ArrayList<String> documents) {
		this.documents = documents;
	}
	

}
