package com.java.services;

import java.util.ArrayList;

import com.java.businesslogic.Service;
import com.java.entities.LoanApplication;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/manager")
public class ManagerService {
	
	Service service = new Service();
	public boolean sendMail() {
		try {
			return service.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GET
	@Path("/applications")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoanApplication> getAllApplications(){
		try {
			return service.getAllApplications();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/loanapplicationsstatus/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoanApplication> getApplicationsByStatus(@PathParam("status") String status){
		try {
			return service.getApplicationsByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
