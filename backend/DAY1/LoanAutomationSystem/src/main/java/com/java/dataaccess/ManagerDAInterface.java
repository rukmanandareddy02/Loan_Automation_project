package com.java.dataaccess;

import java.util.ArrayList;

import com.java.entities.LoanApplication;

public interface ManagerDAInterface {
	LoanApplication getApplication(long CustomerId) throws Exception;
	ArrayList<LoanApplication> getAllApplications() throws Exception;
	boolean sendMail() throws Exception;

}
