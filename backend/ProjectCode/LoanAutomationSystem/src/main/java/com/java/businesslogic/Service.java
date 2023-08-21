package com.java.businesslogic;

import java.util.ArrayList;

import com.java.dataaccessimplementation.DataAccess;
import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.Documents;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;
import com.java.entities.ResetPassword;
import com.java.services.ServiceResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

//@Path("/myservices")
public class Service {
	
	DataAccess dataAccess = new DataAccess();
	
//	@POST
//	@Path("/addcustomer")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addDetails(Customer customer) {
		try {
			return dataAccess.addDetails(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	@POST
//	@Path("/addloanapplication")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public boolean applyForLoan(LoanApplication loanApplication) {
		try {
			return dataAccess.applyForLoan(loanApplication);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Service() {
		// TODO Auto-generated constructor stub
	}
	
	
//	@GET
//	@Path("/onecustomer/{customerId}")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Customer> viewCustomerDetailsById(int customerId) throws Exception {
		try {
			return dataAccess.viewCustomerDetailsById(customerId);
		} catch (Exception e) {
			throw e;
		}
	}
//	@GET
//	@Path("/allrecords")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Customer> getAllCustomers(){
		try {
			return dataAccess.getAllCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	@GET
//	@Path("/oneapplication/{applicationNo}")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoanApplication> getLoanApplicationById(int applicationNo) {
		try {
			return dataAccess.getLoanApplicationById(applicationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<LoanApplication> getApplicationsByDate(String date) {
		try {
			return dataAccess.getApplicationsByDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	@GET
//	@Path("/records")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Loan> browseLoans(){
		try {
			return dataAccess.browseLoans();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	@GET
//	@Path("/applications")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoanApplication> getAllApplications(){
		try {
			return dataAccess.getAllApplications();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public boolean sendMail() {
//		try {
//			return dataAccess.sendMail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
//	@GET
//	@Path("/onecapplication/{customerNo}")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoanApplication> getApplicationsByCId(int customerNo){
		try {
			return dataAccess.getApplicationsByCId(customerNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	@GET
//	@Path("/loanapplications/{loantype}")
//	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoanApplication> getLoanApplications(String loantype){
		try {
			return dataAccess.getLoanApplications(loantype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<LoanApplication> getApplicationsByStatus(String status){
		try {
			return dataAccess.getApplicationsByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteApplication(int applicationNo){
		try {
			return dataAccess.deleteApplication(applicationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(CustomerLogin login){
		try {
			return dataAccess.register(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int login(CustomerLogin login){
		try {
			return dataAccess.login(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean updateCustomer(Customer customer){
		try {
			return dataAccess.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setStatus(LoanApplication loanApplication){
		try {
			return dataAccess.setStatus(loanApplication);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean uploadImage(Documents documents) {
		try {
			return dataAccess.uploadDocuments(documents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String> fetchImages(int applicationNo) {
		try {
			return dataAccess.fetchImages(applicationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean clogin(CustomerLogin clerk){
		try {
			return dataAccess.clogin(clerk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean mlogin(CustomerLogin manager){
		try {
			return dataAccess.mlogin(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getCustomerId(@PathParam("userName") String userName){
		try {
			return dataAccess.getCustomerId(userName);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean resetPassword(ResetPassword reset){
		try {
			return dataAccess.resetPassword(reset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
