package com.java.dataaccessimplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.java.dataaccess.DataAccessInterface;
import com.java.databasehandler.JDBCApp;
import com.java.entities.Customer;
import com.java.entities.CustomerLogin;
import com.java.entities.Documents;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;
import com.java.entities.ResetPassword;

public class DataAccess implements DataAccessInterface {
	JDBCApp jdbc = new JDBCApp();
	Connection connection = jdbc.getConnection();
	PreparedStatement ps = jdbc.getPs();

	@Override
	public boolean addDetails(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		try {
			ps = connection.prepareStatement(
					"INSERT INTO CUSTOMERS(CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ADDRESS,MOBILE) VALUES(?,?,?,?,?,?)");
			ps.setInt(1, customer.getCustomerId());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getGender());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getMobileNumber());
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
			throw e;
		}

		return true;
	}

	public boolean applyForLoan(LoanApplication loanApplication) throws Exception {
		try {
			Blob blob = connection.createBlob();
			ps = connection.prepareStatement(
					"INSERT INTO LOAN_APPLICATION(CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,APPLY_DATE,DOCUMENT) values(?,?,?,?,?,?,SYSDATE,?)");
			ps.setInt(1, loanApplication.getCustomerId());
			ps.setString(2, loanApplication.getLoanType());
			ps.setString(3, loanApplication.getLoanAmount());
			ps.setInt(4, loanApplication.getInterest());
			ps.setInt(5, loanApplication.getTenure());
			ps.setString(6, loanApplication.getEmi());
			blob.setBytes(1, loanApplication.getDocument().getBytes());
			ps.setBlob(7, blob);
			// ps.setString(7, loanApplication.getDate());
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
			throw e;
		}

		return true;
	}

	@Override
	public ArrayList<Customer> viewCustomerDetailsById(int customerId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"SELECT CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ADDRESS,MOBILE FROM CUSTOMERS WHERE CUSTOMER_ID = ?");
			ps.setInt(1, customerId);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				customers.add(new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6)));
			}
		} catch (SQLException e) {
			throw e;
		}
		return customers;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"SELECT CUSTOMER_ID,FIRST_NAME,LAST_NAME,GENDER,ADDRESS,MOBILE FROM CUSTOMERS ORDER BY CUSTOMER_ID");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				customers.add(new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6)));
			}
		} catch (SQLException e) {
			throw e;
		}
		// System.out.println(customers);
		return customers;
	}

	@Override
	public ArrayList<LoanApplication> getLoanApplicationById(int applicationNo) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"SELECT APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,STATUS,APPLY_DATE,DOCUMENT FROM LOAN_APPLICATION WHERE APPLICATION_ID=?");
			ps.setInt(1, applicationNo);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				String s =buf.toString();
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9),s));
			}
		} catch (SQLException e) {
			throw e;
		}
		return applications;
	}

	@Override
	public ArrayList<Loan> browseLoans() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Loan> loans = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT LOAN_ID,LOAN_TYPE,INTEREST FROM LOANS ORDER BY LOAN_ID");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				loans.add(new Loan(res.getInt(1), res.getString(2), res.getInt(3)));
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
					"SELECT APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,STATUS,APPLY_DATE,DOCUMENT FROM LOAN_APPLICATION ORDER BY APPLICATION_ID");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				String s =buf.toString();
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9),s));
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
					"SELECT APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,STATUS,APPLY_DATE,DOCUMENT FROM LOAN_APPLICATION WHERE LOAN_TYPE=? ORDER BY APPLICATION_ID");
			ps.setString(1, loanType);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				String s =buf.toString();
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9),s));
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
					"SELECT APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,STATUS,APPLY_DATE,DOCUMENT FROM LOAN_APPLICATION WHERE STATUS=? ORDER BY APPLICATION_ID");
			ps.setString(1, status);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				String s =buf.toString();
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9),s));
			}
		} catch (SQLException e) {
			throw e;
		}
		return applications;
	}

	@Override
	public boolean sendMail(int applicationNo) throws Exception {
		// TODO Auto-generated method stub
//		Properties properties = new Properties();
//		properties.put("mail.smtp.host", "smtp.office365.com");
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
//		//properties.put("mail.smtp.ssl.enable", "true");
//		//properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//
//		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("rukmanandareddyabbidi@outlook.com", "Rukku@9802!");
//			}
//		});
//		try {
//			ps = connection.prepareStatement(
//					"SELECT EMAIL FROM CUSTOMER_LOGIN WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM LOAN_APPLICATION WHERE APPLICATION_ID=?)");
//			ps.setInt(1, applicationNo);
//			String mail=null;
//			try {
//			ResultSet res = ps.executeQuery();
//			if(res.next())
//				mail=res.getString(1);
//			else
//				return false;
//			}catch(SQLException e) {
//				throw e;
//			}
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("rukmanandareddyabbidi@outlook.com"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
//			message.setSubject("Loan Approved");
//			message.setText("Your Loan Application with Application No " + applicationNo + " is approved");
//			// Send the message
//			Transport.send(message);
//			// System.out.println("Email sent successfully.");
//		} catch (MessagingException e) {
//			throw e;
//		}
		String host = "smtp.gmail.com";
        final String username = "manishssssskumaraaaaa@gmail.com"; // Replace with your Gmail email
        final String password = "osbzpdwcdbexonng"; // Replace with your Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
			ps = connection.prepareStatement(
					"SELECT EMAIL FROM CUSTOMER_LOGIN WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM LOAN_APPLICATION WHERE APPLICATION_ID=?)");
			ps.setInt(1, applicationNo);
			String mail=null;
			try {
			ResultSet res = ps.executeQuery();
			if(res.next())
				mail=res.getString(1);
			else
				return false;
			}catch(SQLException e) {
				throw e;
			}
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        message.setSubject("LOAN APPROVED");
        message.setText("Your Loan Application with Application No " + applicationNo + " is approved");
        Transport.send(message);
        }catch(MessagingException e) {
        	throw e;
        }
	return true;
	}

	@Override
	public ArrayList<LoanApplication> getApplicationsByCId(int customerId) throws Exception {
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"SELECT APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,STATUS,APPLY_DATE,DOCUMENT FROM LOAN_APPLICATION WHERE CUSTOMER_ID =? ORDER BY APPLICATION_ID");
			ps.setInt(1, customerId);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				String s =buf.toString();
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9),s));
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

			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e;
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
			ps = connection.prepareStatement(
					"update customers set first_name=?,last_name=?,gender=?,address=?,mobile=? where customer_id=?");
			ps.setInt(6, customer.getCustomerId());
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getGender());
			ps.setString(4, customer.getAddress());
			ps.setString(5, customer.getMobileNumber());
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
			ps.setInt(2, loanApplication.getApplicationId());
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

	public boolean uploadDocuments(Documents document) throws Exception {
		for (String s : document.getDocuments()) {
			try {
				ps = connection.prepareStatement("insert into loan_documents values(?,?)");
				Blob blob = connection.createBlob();
				blob.setBytes(1, s.getBytes());
				ps.setInt(1, document.getApplicationId());
				ps.setBlob(2, blob);
				int res = ps.executeUpdate();
				if (res <= 0)
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		return true;
	}

	public ArrayList<String> fetchImages(int applicationNo) throws Exception {
		ArrayList<String> docs = new ArrayList<>();
		try {
			ps = connection.prepareStatement("select document from loan_documents where application_id=?");
			ps.setInt(1, applicationNo);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				docs.add(buf.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return docs;
	}

	public boolean clogin(CustomerLogin clerk) throws Exception {
		try {
			ps = connection.prepareStatement("SELECT * FROM CLERK_LOGIN WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, clerk.getUserName());
			ps.setString(2, clerk.getPassword());
			ResultSet res = ps.executeQuery();
			if (!res.next())
				return false;

		} catch (SQLException e) {
			throw e;
		}
		return true;
	}

	public boolean mlogin(CustomerLogin manager) throws Exception {
		try {
			ps = connection.prepareStatement("SELECT * FROM MANAGER_LOGIN WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, manager.getUserName());
			ps.setString(2, manager.getPassword());
			ResultSet res = ps.executeQuery();
			if (!res.next())
				return false;

		} catch (SQLException e) {
			throw e;
		}
		return true;
	}

	public int getCustomerId(String userName) throws Exception {
		int p = -1;
		try {
			ps = connection.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER_LOGIN WHERE EMAIL=?");
			ps.setString(1, userName);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				p = res.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public ArrayList<LoanApplication> getApplicationsByDate(String date) throws Exception {
		ArrayList<LoanApplication> applications = new ArrayList<>();
		try {
			ps = connection.prepareStatement(
					"SELECT APPLICATION_ID,CUSTOMER_ID,LOAN_TYPE,LOAN_AMOUNT,INTEREST,TENURE,EMI,STATUS,APPLY_DATE,DOCUMENT FROM LOAN_APPLICATION WHERE APPLY_DATE =? ORDER BY APPLICATION_ID");
			ps.setString(1, date);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				StringBuffer buf = new StringBuffer();
				String temp = null;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getBlob("document").getBinaryStream()));
				while ((temp = reader.readLine()) != null) {
					buf.append(temp);
				}
				String s =buf.toString();
				applications.add(new LoanApplication(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9),s));
			}
		} catch (SQLException e) {
			throw e;
		}
		return applications;
	}

	public boolean resetPassword(ResetPassword reset) throws Exception {
		try {
			ps = connection.prepareStatement("update customer_login set password=? where email =? and password=?");
			ps.setString(1, reset.getNewPassword());
			ps.setString(2, reset.getUserName());
			ps.setString(3, reset.getOldPassword());
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

}
