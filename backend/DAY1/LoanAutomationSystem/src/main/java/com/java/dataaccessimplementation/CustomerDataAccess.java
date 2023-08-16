package com.java.dataaccessimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.dataaccess.CustomerDAInterface;
import com.java.databasehandler.JDBCApp;
import com.java.entities.Customer;
import com.java.entities.Loan;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/myservice")
public class CustomerDataAccess implements CustomerDAInterface{
	
	@GET
	@Path("/greetings/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage(@PathParam("name") String name) {
		return "Greetings "+name;
	}
	
	@Override
	public boolean applyForLoan() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	public Customer viewCustomerDetails(@PathParam("value") long value) throws Exception {
		// TODO Auto-generated method stub
		JDBCApp jdbc = new JDBCApp();
		Connection connection = jdbc.getConnection();
		PreparedStatement ps = jdbc.getPs();
		Customer customer = null;
		try {
		ps = connection.prepareStatement("select CUSTOMER_ID,NAME,GENDER,MOBILE from CUSTOMERS where CUSTOMER_ID = ?");
		ps.setLong(1, value);
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
	@GET
	@Path("/records")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Loan> browseLoans() throws Exception {
		// TODO Auto-generated method stub
//		JDBCApp jdbc = new JDBCApp();
//		Connection connection = jdbc.getConnection();
//		PreparedStatement ps = jdbc.getPs();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott","tiger");
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
	public Customer viewCustomerDetailsById(long CustomerId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan getLoanApplicationById(long ApplicationNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
