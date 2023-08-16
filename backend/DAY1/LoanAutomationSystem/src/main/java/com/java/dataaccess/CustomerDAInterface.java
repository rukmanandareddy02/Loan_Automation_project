package com.java.dataaccess;

import java.util.ArrayList;

import com.java.entities.Customer;
import com.java.entities.Loan;

public interface CustomerDAInterface {
	boolean applyForLoan() throws Exception;
	Customer viewCustomerDetails(long CustomerId) throws Exception;
	ArrayList<Loan> browseLoans() throws Exception;

}
