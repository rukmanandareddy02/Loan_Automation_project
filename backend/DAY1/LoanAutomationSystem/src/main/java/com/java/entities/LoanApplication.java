package com.java.entities;

public class LoanApplication {
	long applicationNo;
	long CustomerId;
	String loanType;
	long loanAmount;
	String status;
	String remarks;
	long balance;

	public LoanApplication() {
		super();
	}

	public LoanApplication(long applicationNo, long customerId, String loanType, long loanAmount, String status,
			String remarks, long balance) {
		super();
		this.applicationNo = applicationNo;
		CustomerId = customerId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.status = status;
		this.remarks = remarks;
		this.balance = balance;
	}

	public long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(long customerId) {
		CustomerId = customerId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}
