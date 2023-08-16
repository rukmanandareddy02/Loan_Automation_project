package com.java.dataaccess;

import java.util.ArrayList;

import com.java.entities.Customer;
import com.java.entities.LoanApplication;

public interface ClerkDAInterface {
	boolean applyForLoan() throws Exception;
	ArrayList<Customer> getCustomerDetails() throws Exception;
	ArrayList<LoanApplication> getLoanDetails(long customerId) throws Exception;

}
