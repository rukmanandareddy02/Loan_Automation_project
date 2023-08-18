package com.java.services;

import java.util.ArrayList;

import com.java.businesslogic.Service;
import com.java.entities.LoanApplication;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/manager")
public class ManagerService {
	
	Service service = new Service();
	public ServiceResponse<Boolean> sendMail() {
		try {
			boolean flag = service.sendMail();
			if(flag==true)
				return new ServiceResponse<Boolean>("data sent",200,flag);
			else
				return new ServiceResponse<Boolean>("data not sent",404,flag);
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
	@Path("/loanapplicationsstatus/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<ArrayList<LoanApplication>> getApplicationsByStatus(@PathParam("status") String status){
		try {
			ArrayList<LoanApplication> applications= service.getApplicationsByStatus(status);
			if(applications.size()!=0)
				return new ServiceResponse<ArrayList<LoanApplication>>("data found",200,applications);
			else
				return new ServiceResponse<ArrayList<LoanApplication>>("data not found",404,applications);
		} catch (Exception e) {
			return new ServiceResponse<ArrayList<LoanApplication>>(e.getMessage(),500,null);
		}
	}
	
	@PUT
	@Path("/setStatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> setStatus(LoanApplication loanApplication){
		try {
			boolean flag=service.setStatus(loanApplication);
			if(flag==true)
				return new ServiceResponse<Boolean>("data updated",200,flag);
			else
				return new ServiceResponse<Boolean>("data not found",404,flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(),500,null);
		}
	}
}
