package com.java.dataaccessimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.dataaccess.ClerkDAInterface;
import com.java.databasehandler.JDBCApp;
import com.java.entities.Customer;
import com.java.entities.LoanApplication;

public class ClerkDataAccess implements ClerkDAInterface {

	JDBCApp jdbc = new JDBCApp();
	Connection connection = jdbc.getConnection();
	PreparedStatement ps = jdbc.getPs();

	@Override
	public boolean applyForLoan() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Customer> getCustomerDetails() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ps = connection.prepareStatement("select CUSTOMER_ID,NAME,GENDER,MOBILE from CUSTOMERS");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				customers.add(new Customer(res.getLong(1), res.getString(2), res.getString(3), res.getLong(4)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return customers;
	}

	@Override
	public ArrayList<LoanApplication> getLoanDetails(long customerId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
		ps = connection.prepareStatement("select APPLICATION_NO,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION where CUSTOMER_ID=?");
		ps.setLong(1, customerId);
		ResultSet res = ps.executeQuery();
		if(res.getFetchSize()==0) {
			return null;
		}
		while(res.next()) {
			applications.add(new LoanApplication(res.getLong(1),res.getLong(2),res.getString(3),res.getLong(4),res.getString(5),res.getString(6),res.getLong(7))); 
		}
		}
		catch(SQLException e) {
			throw e;
		}
		return applications;
	}

}
