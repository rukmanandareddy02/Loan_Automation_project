package com.java.tests;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.java.dataaccessimplementation.DataAccess;
import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.LoanApplication;

public class DataAccessTests {
	private static DataAccess dataAccess;

	@BeforeClass
	public static void initialize() {
		// System.out.println("fds");
		dataAccess = new DataAccess();
	}

	@AfterClass
	public static void cleanUp() {
		dataAccess = null;
	}

	@Test
	public void viewCustomerDetailsById() {
		// System.out.println("jhey");
		ArrayList<Customer> customers = null;
		try {
			customers = dataAccess.viewCustomerDetailsById(33);
			Customer expectedCustomer = new Customer(33, "rukku", "abbidi", "m", "hyd", "8688346309");
			Assert.assertEquals(expectedCustomer.getCustomerId(), customers.get(0).getCustomerId());
			Assert.assertEquals(expectedCustomer.getFirstName(), customers.get(0).getFirstName());
			Assert.assertEquals(expectedCustomer.getLastName(), customers.get(0).getLastName());
			Assert.assertEquals(expectedCustomer.getGender(), customers.get(0).getGender());
			Assert.assertEquals(expectedCustomer.getAddress(), customers.get(0).getAddress());
			Assert.assertEquals(expectedCustomer.getMobileNumber(), customers.get(0).getMobileNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void viewCustomerDetailsByInvalidId() {
		// System.out.println("jhey");
		ArrayList<Customer> customers = null;
		try {
			customers = dataAccess.viewCustomerDetailsById(88);
			ArrayList<Customer> expectedCustomer = new ArrayList<Customer>();
			Assert.assertEquals(expectedCustomer, customers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getLoanApplicationById() {
		// System.out.println("jhey");
		ArrayList<LoanApplication> applications = null;
		try {
			applications = dataAccess.getLoanApplicationById(7);
			LoanApplication expectedApplication = new LoanApplication(7, 33, "EducationLoan", "1233456", 8, 10,
			Assert.assertEquals(expectedApplication.getApplicationId(), applications.get(0).getApplicationId());
			Assert.assertEquals(expectedApplication.getCustomerId(), applications.get(0).getCustomerId());
			Assert.assertEquals(expectedApplication.getLoanType(), applications.get(0).getLoanType());
			Assert.assertEquals(expectedApplication.getLoanAmount(), applications.get(0).getLoanAmount());
			Assert.assertEquals(expectedApplication.getInterest(), applications.get(0).getInterest());
			Assert.assertEquals(expectedApplication.getTenure(), applications.get(0).getTenure());
			Assert.assertEquals(expectedApplication.getEmi(), applications.get(0).getEmi());
			Assert.assertEquals(expectedApplication.getStatus(), applications.get(0).getStatus());
			Assert.assertEquals(expectedApplication.getDate(), applications.get(0).getDate());
			Assert.assertEquals(expectedApplication.getDocument(), applications.get(0).getDocument());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getCustomerId() {
		try {
			int actual=dataAccess.getCustomerId("rukmanandareddyabbidi@gmail.com");
			int expected = 33;
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void login() {
		try {
			CustomerLogin login = new CustomerLogin("rukmanandareddyabbidi@gmail.com","98765");
			int actual=dataAccess.login(login);
			int expected = 33;
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void register() {
		try {
			CustomerLogin customer = new CustomerLogin("rukkureddy@gmail.com","0987654");
			boolean actual=dataAccess.register(customer);
			boolean expected = true;
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void addCustomer() {
		try {
			Customer customer = new Customer(38,"kishan","reddy","m","Hyd","9989193476");
			boolean actual=dataAccess.addDetails(customer);
			boolean expected = true;
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}