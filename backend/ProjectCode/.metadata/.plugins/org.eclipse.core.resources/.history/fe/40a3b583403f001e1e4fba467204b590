package com.java.services;

import java.util.ArrayList;

import com.java.businesslogic.Service;
import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.Documents;
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
			if(flag==true)
				return new ServiceResponse<Boolean>("data added",200,flag);
			else
				return new ServiceResponse<Boolean>("data not added",404,flag);
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
			if(flag==true)
				return new ServiceResponse<Boolean>("data added",200,flag);
			else
				return new ServiceResponse<Boolean>("data not added",404,flag);
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
			if(applications.size()!=0)
				return new ServiceResponse<ArrayList<LoanApplication>>("data found",200,applications);
			else
				return new ServiceResponse<ArrayList<LoanApplication>>("data not found",404,applications);
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
			if(customers.size()!=0)
				return new ServiceResponse<ArrayList<Customer>>("data found",200,customers);
			else
				return new ServiceResponse<ArrayList<Customer>>("data not found",404,customers);
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
			if(applications.size()!=0)
				return new ServiceResponse<ArrayList<LoanApplication>>("data found",200,applications);
			else
				return new ServiceResponse<ArrayList<LoanApplication>>("data not found",404,applications);
		} catch (Exception e) {
			return new ServiceResponse<ArrayList<LoanApplication>>(e.getMessage(),500,null);
		}
	}
	
	@GET
	@Path("/cdocuments/{applicationNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<ArrayList<Documents>> fetchImages(@PathParam("applicationNo") int applicationNo){
		try {
			ArrayList<Documents> docs = service.fetchImages(applicationNo);
			if(docs.size()!=0)
				return new ServiceResponse<ArrayList<Documents>>("data found",200,docs);
			else
				return new ServiceResponse<ArrayList<Documents>>("data not found",404,docs);
		} catch (Exception e) {
			return new ServiceResponse<ArrayList<Documents>>(e.getMessage(),500,null);
		}
	}
	
	@POST
	@Path("/clogin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> clogin(CustomerLogin clerk){
		try {
			boolean flag=service.clogin(clerk);
			if(flag==true)
				return new ServiceResponse<Boolean>("data found",200,flag);
			else
				return new ServiceResponse<Boolean>("data not found",404,flag);
				
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(),500,null);
		}
	}

}
