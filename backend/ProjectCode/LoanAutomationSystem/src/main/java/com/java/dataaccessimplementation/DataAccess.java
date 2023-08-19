package com.java.dataaccessimplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.dataaccess.DataAccessInterface;
import com.java.databasehandler.JDBCApp;
import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.Documents;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;

public class DataAccess implements DataAccessInterface {
	JDBCApp jdbc = new JDBCApp();
	Connection connection = jdbc.getConnection();
	PreparedStatement ps = jdbc.getPs();

	@Override
	public boolean addDetails(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		try {
			ps = connection.prepareStatement("INSERT INTO CUSTOMERS(CUSTOMER_ID,NAME,GENDER,MOBILE) values(?,?,?,?)");
			ps.setInt(1, customer.getCustomerId());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getGender());
			ps.setInt(4, customer.getMobileNumber());
			int res = ps.executeUpdate();
			if (res <= 0) {
				// System.out.println("did not inserted");
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

	public boolean applyForLoan(LoanApplication loanApplication) throws Exception {
		try {
			ps = connection.prepareStatement(
					"INSERT INTO LOAN_APPLICATION(CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE) values(?,?,?,?,?,?)");
			ps.setInt(1, loanApplication.getCustomerId());
			ps.setString(2, loanApplication.getLoanType());
			ps.setInt(3, loanApplication.getLoanAmount());
			ps.setString(4, loanApplication.getStatus());
			ps.setString(5, loanApplication.getRemarks());
			ps.setInt(6, loanApplication.getBalance());
			int res = ps.executeUpdate();
			if (res <= 0) {
				// System.out.println("did not inserted");
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
	public Customer viewCustomerDetailsById(int customerId) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			ps = connection
					.prepareStatement("select CUSTOMER_ID,NAME,GENDER,MOBILE from CUSTOMERS where CUSTOMER_ID = ?");
			ps.setInt(1, customerId);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				customer = new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
			}
		} catch (SQLException e) {
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
			while (res.next()) {
				customers.add(new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
			}
		} catch (SQLException e) {
			throw e;
		}
		// System.out.println(customers);
		return customers;
	}

	@Override
	public LoanApplication getLoanApplicationById(int applicationNo) throws Exception {
		// TODO Auto-generated method stub
		LoanApplication loanApplication = null;
		try {
			ps = connection.prepareStatement(
					"select APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION where APPLICATION_ID=?");
			ps.setInt(1, applicationNo);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				loanApplication = new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4),
						res.getString(5), res.getString(6), res.getInt(7));
			}
		} catch (SQLException e) {
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
				loans.add(new Loan(res.getInt(1), res.getString(2)));
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
			ps = connection.prepareStatement(
					"select APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4),
						res.getString(5), res.getString(6), res.getInt(7)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return applications;
	}

	@Override
	public ArrayList<LoanApplication> getLoanApplications(String loanType) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"select APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION WHERE LOAN_TYPE=?");
			ps.setString(1, loanType);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4),
						res.getString(5), res.getString(6), res.getInt(7)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return applications;
	}

	@Override
	public ArrayList<LoanApplication> getApplicationsByStatus(String status) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"select APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION WHERE STATUS=?");
			ps.setString(1, status);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4),
						res.getString(5), res.getString(6), res.getInt(7)));
			}
		} catch (SQLException e) {
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
	public ArrayList<LoanApplication> getApplicationsByCId(int customerId) throws Exception {
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"select APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,STATUS,REMARKS,BALANCE FROM LOAN_APPLICATION WHERE CUSTOMER_ID =?");
			ps.setInt(1, customerId);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4),
						res.getString(5), res.getString(6), res.getInt(7)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return applications;
	}

	public boolean deleteApplication(int applicationNo) throws Exception {
		try {
			ps = connection.prepareStatement("DELETE FROM LOAN_APPLICATION WHERE APPLICATION_ID =?");
			ps.setInt(1, applicationNo);
			int res = ps.executeUpdate();
			if (res <= 0)
				return false;
		} catch (SQLException e) {
			throw e;
		}
		return true;
	}

	public boolean register(CustomerLogin login) throws Exception {
		try {
			ps = connection.prepareStatement("INSERT INTO CUSTOMER_LOGIN(EMAIL,PASSWORD) VALUES(?,?)");
			ps.setString(1, login.getUserName());
			ps.setString(2, login.getPassword());
			int res = ps.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			throw e;
		}
		return true;
	}

	public int login(CustomerLogin login) throws Exception {
		int p = -1;
		try {
			ps = connection.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER_LOGIN WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, login.getUserName());
			ps.setString(2, login.getPassword());
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				p = res.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public boolean updateCustomer(Customer customer) throws Exception {
		try {
			ps = connection.prepareStatement("update customers set name=?,gender=?,mobile=? where customer_id=?");
			ps.setInt(4, customer.getCustomerId());
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getGender());
			ps.setInt(3, customer.getMobileNumber());
			int res = ps.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}

		return true;
	}

	public boolean setStatus(LoanApplication loanApplication) throws Exception {
		try {
			ps = connection.prepareStatement("update loan_application set status=? where application_id=?");
			ps.setString(1, loanApplication.getStatus());
			ps.setInt(2, loanApplication.getApplicationNo());
			int res = ps.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return true;
	}

	public boolean uploadImage(Documents document) throws Exception {
		try {
			ps = connection.prepareStatement("insert into loan_documents values(?,?,?)");
			Blob blob1 = connection.createBlob();
			Blob blob2 = connection.createBlob();
			blob1.setBytes(1, document.getDocument1().getBytes());
			blob2.setBytes(2, document.getDocument2().getBytes());
			ps.setInt(1, document.getApplicationNo());
			ps.setBlob(2, blob1);
			ps.setBlob(3, blob2);
			int res = ps.executeUpdate();
			if(res<=0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return true;
	}
	
	public ArrayList<Documents> fetchImages(int applicationNo) throws Exception{
		ArrayList<Documents> docs = new ArrayList<>();
		try {
			ps = connection.prepareStatement("select * from loan_documents where application_id=?");
			ps.setInt(1, applicationNo);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				Documents doc = new Documents();
				doc.setApplicationNo(res.getInt("application_id"));				;
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(res.getBlob("document1").getBinaryStream()));
				while((temp = reader.readLine())!=null) {
					buf.append(temp);
				}
				doc.setDocument1(buf.toString());
				docs.add(doc);
				StringBuffer buf2 = new StringBuffer();
				String temp2 = null;
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(res.getBlob("document2").getBinaryStream()));
				while((temp2 = reader2.readLine())!=null) {
					buf2.append(temp2);
				}
				doc.setDocument2(buf.toString());
				docs.add(doc);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return docs;
	}
	
	public boolean clogin(CustomerLogin clerk) throws Exception{
		try {
			ps = connection.prepareStatement("SELECT * FROM CLERK_LOGIN WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, clerk.getUserName());
			ps.setString(2, clerk.getPassword());
			ResultSet res = ps.executeQuery();
			if(!res.next())
				return false;
				
		} catch (SQLException e) {
			throw e;
		}
		return true;
	}
	
	public boolean mlogin(CustomerLogin manager) throws Exception{
		try {
			ps = connection.prepareStatement("SELECT * FROM MANAGER_LOGIN WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, manager.getUserName());
			ps.setString(2, manager.getPassword());
			ResultSet res = ps.executeQuery();
			if(!res.next())
				return false;
				
		} catch (SQLException e) {
			throw e;
		}
		return true;
	}
	
}
