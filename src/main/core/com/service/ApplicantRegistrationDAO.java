package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.util.Formatter;
import com.util.JDBCConnection;

public class ApplicantRegistrationDAO {
	public static String addApplicant(String titleId,String initials,String initialsFull,String surname,String NIC,String businessName,String businessRegNo,String address1,String address2,String city,String state,String postalCode,String country,String mobileNo,String officeNo,String status){
		if(titleId=="" || initials==""|| surname==""||NIC==""||businessName=="" ||businessRegNo==""){
			return "Please fill out required fields";
		}else if(NIC !=null){
			Connection con=null;
			con=JDBCConnection.getConnection();
			try {		
				PreparedStatement query=con.prepareStatement("SELECT * FROM \"applicant\" WHERE \"nic\"=? ");
				query.setString(1,NIC );
				query.executeQuery();
				ResultSet result=query.getResultSet();     
				if(result.next()){
					return "Applicant already registered";
				}else { 
					java.sql.Date sqlToday=Formatter.convertToSqlDate(new Date());
					query = con.prepareStatement("INSERT INTO \"applicant\" (applicanttype,titleid,initials,initialsinfull,surname,nic,addressline1,addressline2,city,state,postalcodeid,countryid,mobileno,officeno,registereddate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					query.setString(1, "Individual");
					query.setInt(2, Integer.parseInt(titleId));
					query.setString(3,initials);
					query.setString(4,initialsFull);
					query.setString(5,surname);
					query.setString(6, NIC);
					query.setString(7,address1);
					query.setString(8,address2);
					query.setString(9,city);
					query.setString(10,state);
					query.setString(11,postalCode);
					query.setString(12,country);
					query.setString(13,mobileNo);
					query.setString(14,officeNo);
					query.setDate(15,sqlToday);
					System.out.println(query);
					query.executeUpdate();
					return "Applicant added successfuly";							

				}
			}catch(Exception e){
				e.printStackTrace();
				return e.getMessage();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(businessRegNo!=null){
			Connection con=null;
			con=JDBCConnection.getConnection();
			try {		
				PreparedStatement query=con.prepareStatement("SELECT * FROM \"applicant\" WHERE \"businessregno\"=? ");
				query.setString(1,businessRegNo );
				query.executeQuery();
				ResultSet result=query.getResultSet();     
				if(result.next()){
					return "Applicant already registered";
				}else{
					java.sql.Date sqlToday=Formatter.convertToSqlDate(new Date());
					query = con.prepareStatement("INSERT INTO \"applicant\" (applicanttype,businessname,businessregno,addressline1,addressline2,city,state,postalcodeid,countryid,mobileno,officeno,registereddate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
					query.setString(1, "Corporate");
					query.setString(2, businessName);
					query.setString(3, businessRegNo);
					query.setString(4,address1);
					query.setString(5,address2);
					query.setString(6,city);
					query.setString(7,state);
					query.setString(8,postalCode);
					query.setString(9,country);
					query.setString(10,mobileNo);
					query.setString(11,officeNo);
					query.setDate(12,sqlToday);
					System.out.println(query);
					query.executeUpdate();
					return "Applicant added successfuly";							
				}				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return e.getMessage();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
		return "";
	}
	public static int getApplicantIdByNICOrBusinessRegNo(String businessRegNIC) {
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {		
			PreparedStatement query=con.prepareStatement("SELECT * FROM \"applicant\" WHERE \"nic\"=? ");
			query.setString(1,businessRegNIC );
			System.out.println(query);
			query.executeQuery();
			ResultSet result=query.getResultSet();     
			if(result.next()){
				return result.getInt("applicantid");
			}else {
				query=con.prepareStatement("SELECT * FROM \"applicant\" WHERE \"businessregno\"=? ");
				query.setString(1,businessRegNIC );
				if(result.next()){
					return result.getInt("applicantid");
				}else{
					return 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
   }
	public static String getApplicantNameById(int id) {
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {		
			PreparedStatement query=con.prepareStatement("SELECT * FROM \"applicant\" WHERE \"applicantid\"=? ");
			query.setInt(1,id );
			query.executeQuery();
			ResultSet result=query.getResultSet();     
			if(result.next()){
				if(result.getString("applicanttype").equals("Corporate")){
					return result.getString("businessname");
				}else{
					return result.getString("initials")+" "+result.getString("surname");
				}
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
   }
}


