package com.java.entities;

public class LoanApplication {
	int applicationId;
	int customerId;
	String loanType;
	String loanAmount;
	int interest;
	int tenure;
	String emi;
	String status;
	String date;
	public LoanApplication() {
		super();
	}
	public LoanApplication(int applicationId, int customerId, String loanType, String loanAmount, int interest,
			int tenure, String emi, String status, String date) {
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.interest = interest;
		this.tenure = tenure;
		this.emi = emi;
		this.status = status;
		this.date = date;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getEmi() {
		return emi;
	}
	public void setEmi(String emi) {
		this.emi = emi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
