package com.java.businesslogic;

import java.util.ArrayList;

import com.java.dataaccessimplementation.DataAccess;
import com.java.entities.Customer;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/myservice")
public class Service {
	
	@GET
	@Path("/greetings/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage(@PathParam("name") String name) {
		return "Greetings "+name;
	}
	DataAccess dataAccess = new DataAccess();
	
	public boolean applyForLoan() {
		try {
			return dataAccess.applyForLoan();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GET
	@Path("/records/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer viewCustomerDetailsById(@PathParam("value") long customerId) {
		try {
			return dataAccess.viewCustomerDetailsById(customerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Customer> getAllCustomers(){
		try {
			return dataAccess.getAllCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public LoanApplication getLoanApplicationById(long applicationNo) {
		try {
			return dataAccess.getLoanApplicationById(applicationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@GET
	@Path("/records")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Loan> browseLoans(){
		try {
			return dataAccess.browseLoans();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<LoanApplication> getAllApplications(){
		try {
			return dataAccess.getAllApplications();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean sendMail() {
		try {
			return dataAccess.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<LoanApplication> getApplicationsByCId(long customerId){
		try {
			return dataAccess.getApplicationsByCId(customerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
