package com.java.dataaccess;

import java.util.ArrayList;

import com.java.entities.Customer;
import com.java.entities.Loan;

public interface CustomerDAInterface {
	boolean applyForLoan() throws Exception;
	Customer viewCustomerDetailsById(long CustomerId) throws Exception;
	ArrayList<Customer> getAllCustomers() throws Exception
	Loan getLoanApplicationById(long ApplicationNo) throws Exception;
	ArrayList<Loan> browseLoans() throws Exception;

}
