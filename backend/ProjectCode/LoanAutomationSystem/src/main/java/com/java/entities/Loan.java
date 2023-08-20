package com.java.entities;

public class Loan {
	int loanId;
	String loanType;
	int interest;
	public Loan() {
		super();
	}
	public Loan(int loanId, String loanType, int interest) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.interest = interest;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	};

}
