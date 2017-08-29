package com.salon.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.domain.Officer;
import com.util.Formatter;
import com.util.JDBCConnection;
import com.util.PasswordEncriptor;

public class OfficerDAO {
	public static String addUser(String titleId,String firstName,String surname,String mobileNo,String officerTypeId,String userName,String password,String status,int createdUserId){
		java.sql.Date createdDate=null;
		String encryptedPassword;
		if(titleId=="" || firstName==""|| surname==""||mobileNo==""||officerTypeId=="" ||userName==""||password==""){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();
			try {	 

					PreparedStatement query=con.prepareStatement("SELECT * FROM officerS WHERE username=? ");
					query.setString(1, userName.toLowerCase());
					query.executeQuery();
					ResultSet result=query.getResultSet();     
					if(result.next()){
						return "User Name allready exits";
					}else{			
						encryptedPassword=PasswordEncriptor.hashPassword(password);
						Date day=new Date();
						createdDate=Formatter.convertToSqlDate(day);
						query = con.prepareStatement("INSERT INTO officerS (titleid,firstname,surname,officertypeid,username,password,status,createduser,createddate,mobileno) VALUES (?,?,?,?,?,?,?,?,?,?)");
						query.setInt(1, Integer.parseInt(titleId));
					    query.setString(2, firstName);
					    query.setString(3, surname);
					    query.setInt(4, Integer.parseInt(officerTypeId));
					    query.setString(5, userName.toLowerCase());
					    query.setString(6, encryptedPassword);
					    query.setString(7, status);
					    query.setInt(8,createdUserId);
					    query.setDate(9,createdDate);
					    query.setString(10,mobileNo );
					    query.executeUpdate();
					    return "user added successfuly";
					}
				
				} catch (SQLException e) {
						e.printStackTrace();
						return e.getMessage();
				}finally{
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}		

		}
	}
	
	public static List<Officer> getAllOfficers(){
		List<Officer> officers = new ArrayList<Officer>();
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 

			PreparedStatement query=con.prepareStatement("SELECT * FROM officerS ");	
			query.executeQuery();
			ResultSet result1=query.getResultSet();     
			while (result1.next()) {
				Officer officer = new Officer();
				officer.setTitleId(result1.getInt("titleid"));
				officer.setFirstName(result1.getString("firstname"));
				officer.setSurname(result1.getString("surname"));
				officer.setMobileNo(result1.getString("mobileno"));
				officer.setOfficerTypeId(result1.getInt("officertypeid"));
				officer.setUserName(result1.getString("username"));
				officer.setPassword(result1.getString("password"));
				officer.setCreatedUser(result1.getInt("createduser"));
				officer.setCreatedDate(result1.getDate("createddate"));
				officer.setStatus(result1.getString("status"));
				officers.add(officer);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return officers;
	}
	public static String getFullNameByOfficerId(int id){
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 

			PreparedStatement query=con.prepareStatement("SELECT firstname FROM officerS where officerid=?");
			query.setInt(1, id);
			query.executeQuery();
			ResultSet result=query.getResultSet();     			
			while (result.next()) {
			  return result.getString("firstname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return "";
	}
	
	public static int getOfficerIdByName(String name){
		int officerId=0;
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("SELECT officerid FROM officerS  WHERE  firstname ilike ?");
			query.setString(1, name);
			query.executeQuery();
			ResultSet result=query.getResultSet();     
			while (result.next()) {	
				officerId=result.getInt("officerid");
			  }	
			return officerId;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return officerId;
	}	
}
