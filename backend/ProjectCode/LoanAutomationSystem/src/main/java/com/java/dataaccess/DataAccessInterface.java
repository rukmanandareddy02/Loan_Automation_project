package com.java.dataaccess;

import java.util.ArrayList;

import com.java.entities.Customer;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public interface DataAccessInterface {
	boolean applyForLoan() throws Exception;
	Customer viewCustomerDetailsById(long customerId) throws Exception;
	ArrayList<Customer> getAllCustomers() throws Exception;
	LoanApplication getLoanApplicationById(long applicationNo) throws Exception;
	ArrayList<Loan> browseLoans() throws Exception;
	ArrayList<LoanApplication> getAllApplications() throws Exception;
	boolean sendMail() throws Exception;
	ArrayList<LoanApplication> getApplicationsByCId(long customerId) throws Exception;

}
