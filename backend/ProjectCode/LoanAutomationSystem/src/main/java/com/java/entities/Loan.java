package com.java.entities;

public class Loan {
	long loanId;
	String loanType;
	public Loan() {
		super();
	}
	public Loan(long loanId, String loanType) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
	}
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	

}
