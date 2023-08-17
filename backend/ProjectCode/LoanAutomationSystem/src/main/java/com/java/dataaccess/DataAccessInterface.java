package com.java.dataaccess;

import java.util.ArrayList;

import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public interface DataAccessInterface {
	boolean addDetails(Customer customer) throws Exception;
	boolean applyForLoan(LoanApplication loanApplication) throws Exception;
	Customer viewCustomerDetailsById(int customerId) throws Exception;
	ArrayList<Customer> getAllCustomers() throws Exception;
	LoanApplication getLoanApplicationById(int applicationNo) throws Exception;
	ArrayList<Loan> browseLoans() throws Exception;
	ArrayList<LoanApplication> getAllApplications() throws Exception;
	boolean sendMail() throws Exception;
	ArrayList<LoanApplication> getLoanApplications(String loanType) throws Exception;
	ArrayList<LoanApplication> getApplicationsByCId(int customerId) throws Exception;
	ArrayList<LoanApplication> getApplicationsByStatus(String status) throws Exception;
	boolean deleteApplication(int applicationNo) throws Exception;
	boolean register(CustomerLogin login) throws Exception;
	int login(CustomerLogin login) throws Exception;
	boolean updateCustomer(Customer customer) throws Exception;

}
