package com.java.services;

import java.util.ArrayList;

import com.java.businesslogic.Service;
import com.java.entities.Customer;
import com.java.entities.LoanApplication;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clerk")
public class ClerkService {
	Service service = new Service();

	@POST
	@Path("/addcustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> addDetails(Customer customer) {
		try {
			boolean flag = service.addDetails(customer);
			return new ServiceResponse<Boolean>("data added",200,flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(),500,null);
		}
	}
	
	@POST
	@Path("/addloanapplication")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> applyForLoan(LoanApplication loanApplication) {
		try {
			boolean flag = service.applyForLoan(loanApplication);
			return new ServiceResponse<Boolean>("data added",200,flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(),500,null);
		}
	}
	
	@GET
	@Path("/applications")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<ArrayList<LoanApplication>> getAllApplications(){
		try {
			ArrayList<LoanApplication> applications = service.getAllApplications();
			return new ServiceResponse<ArrayList<LoanApplication>>("data found",200,applications);
		} catch (Exception e) {
			return new ServiceResponse<ArrayList<LoanApplication>>(e.getMessage(),500,null);
		}
	}
	
	@GET
	@Path("/customers")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<ArrayList<Customer>> getAllCustomers(){
		try {
			ArrayList<Customer> customers = service.getAllCustomers();
			return new ServiceResponse<ArrayList<Customer>>("data found",200,customers);
		} catch (Exception e) {
			return new ServiceResponse<ArrayList<Customer>>(e.getMessage(),500,null);
		}
	}
	
	@GET
	@Path("/loanapplications/{loantype}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<ArrayList<LoanApplication>> getApplicationsByCId(@PathParam("loantype") String loantype){
		try {
			ArrayList<LoanApplication> applications = service.getLoanApplications(loantype);
			return new ServiceResponse<ArrayList<LoanApplication>>("data found",200,applications);
		} catch (Exception e) {
			return new ServiceResponse<ArrayList<LoanApplication>>(e.getMessage(),500,null);
		}
	}

}
