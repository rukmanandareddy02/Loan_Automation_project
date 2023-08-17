package com.java.dataaccessimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.dataaccess.DataAccessInterface;
import com.java.databasehandler.JDBCApp;
import com.java.entities.Customer;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public class DataAccess implements DataAccessInterface{
	JDBCApp jdbc = new JDBCApp();
	Connection connection = jdbc.getConnection();
	PreparedStatement ps = jdbc.getPs();

	@Override
	public boolean addDetails(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		try {
			ps = connection.prepareStatement("INSERT INTO CUSTOMERS(CUSTOMER_ID,NAME,GENDER,MOBILE) values(?,?,?,?)");
			ps.setLong(1, customer.getCustomerId());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getGender());
			ps.setLong(4, customer.getMobileNumber());
			int res = ps.executeUpdate();
			if (res <= 0) {
				System.out.println("did not inserted");
				try {
					jdbc.closeConnection(connection);
				} catch (SQLException e) {
					throw e;
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean applyForLoan(LoanApplication loanApplication) throws Exception{
		try {
			ps = connection.prepareStatement("INSERT INTO LOAN_APPLICATION(APPLICATION_NO,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE) values(?,?,?,?,?,?,?)");
			ps.setLong(1, loanApplication.getApplicationNo());
			ps.setLong(2, loanApplication.getCustomerId());
			ps.setString(3, loanApplication.getLoanType());
			ps.setLong(4, loanApplication.getLoanAmount());
			ps.setString(5, loanApplication.getStatus());
			ps.setString(6, loanApplication.getRemarks());
			ps.setLong(7, loanApplication.getBalance());
			int res = ps.executeUpdate();
			if (res <= 0) {
				System.out.println("did not inserted");
				try {
					jdbc.closeConnection(connection);
				} catch (SQLException e) {
					throw e;
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	@Override
	public Customer viewCustomerDetailsById(long customerId) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
		ps = connection.prepareStatement("select CUSTOMER_ID,NAME,GENDER,MOBILE from CUSTOMERS where CUSTOMER_ID = ?");
		ps.setLong(1, customerId);
		ResultSet res = ps.executeQuery();
		if(res.getFetchSize()==0) {
			return null;
		}
		while(res.next()) {
			customer = new Customer(res.getLong(1),res.getString(2),res.getString(3),res.getLong(4)); 
		}
		}
		catch(SQLException e) {
			throw e;
		}
		return customer;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Customer> customers = new ArrayList<>();
		try {
		ps = connection.prepareStatement("select CUSTOMER_ID,NAME,GENDER,MOBILE from CUSTOMERS");
		ResultSet res = ps.executeQuery();
		if(res.getFetchSize()==0) {
			return null;
		}
		while(res.next()) {
			customers.add(new Customer(res.getLong(1),res.getString(2),res.getString(3),res.getLong(4)));
			
		}
		}
		catch(SQLException e) {
			throw e;
		}
		//System.out.println(customers);
		return customers;
	}

	@Override
	public LoanApplication getLoanApplicationById(long applicationNo) throws Exception {
		// TODO Auto-generated method stub
		LoanApplication loanApplication = null;
		try {
		ps = connection.prepareStatement("select APPLICATION_NO,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION where APPLICATION_NO=?");
		ps.setLong(1, applicationNo);
		ResultSet res = ps.executeQuery();
		if(res.getFetchSize()==0) {
			return null;
		}
		while(res.next()) {
			loanApplication=new LoanApplication(res.getLong(1),res.getLong(2),res.getString(3),res.getLong(4),res.getString(5),res.getString(6),res.getLong(7)); 
		}
		}
		catch(SQLException e) {
			throw e;
		}
		return loanApplication;
	}

	@Override
	public ArrayList<Loan> browseLoans() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Loan> loans = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT LOAN_ID,LOAN_TYPE FROM LOANS");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				loans.add(new Loan(res.getLong(1), res.getString(2)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return loans;
	}

	@Override
	public ArrayList<LoanApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
		ps = connection.prepareStatement("select APPLICATION_NO,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION");
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

	@Override
	public boolean sendMail() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ArrayList<LoanApplication> getApplicationsByCId(long customerId) throws Exception{
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
		ps = connection.prepareStatement("select APPLICATION_NO,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION WHERE CUSTOMER_ID =?");
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