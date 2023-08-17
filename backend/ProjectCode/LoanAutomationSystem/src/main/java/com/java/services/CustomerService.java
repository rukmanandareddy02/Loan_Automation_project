package com.java.services;

import java.util.ArrayList;

import com.java.businesslogic.Service;
import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/customer")
public class CustomerService {
	Service service = new Service();

	@POST
	@Path("/addcustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addDetails(Customer customer) {
		try {
			return service.addDetails(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@POST
	@Path("/addloanapplication")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean applyForLoan(LoanApplication loanApplication) {
		try {
			return service.applyForLoan(loanApplication);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GET
	@Path("/viewcustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer viewCustomerDetailsById(@PathParam("customerId") int customerId) {
		try {
			return service.viewCustomerDetailsById(customerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/getapplication/{applicationNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public LoanApplication getLoanApplicationById(@PathParam("applicationNo") int applicationNo) {
		try {
			return service.getLoanApplicationById(applicationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/loans")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Loan> browseLoans(){
		try {
			return service.browseLoans();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/deleteapplication/{applicationNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteApplication(@PathParam("applicationNo") int applicationNo){
		try {
			return service.deleteApplication(applicationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean register(CustomerLogin login){
		try {
			return service.register(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int login(CustomerLogin login){
		try {
			return service.login(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateCustomer(Customer customer){
		try {
			return service.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
