package com.java.entities;

public class Customer {

	long customerId;
	String name;
	String gender;
	long mobileNumber;

	public Customer() {
		super();
	}

	public Customer(long customerId, String name, String gender, long mobileNumber) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
