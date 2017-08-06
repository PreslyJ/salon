package com.salon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.domain.Officer;
import com.util.JDBCConnection;
import com.util.PasswordEncriptor;

public class LoginDAOS {
	public static Officer authentication(String userName,String password) throws SQLException{
		Connection con=null;
		String encryptedPassword= PasswordEncriptor.hashPassword(password);
		System.out.println(encryptedPassword);
		con=JDBCConnection.getConnection();
		try {
			PreparedStatement query=con.prepareStatement("SELECT * FROM officerS WHERE username=? and password=?");
			query.setString(1, userName);
			query.setString(2, encryptedPassword);
			query.executeQuery();
			ResultSet result=query.getResultSet(); 
			while(result.next()){	
				Officer officer=new Officer();
				officer.setFirstName(result.getString("firstname"));
				officer.setOfficerId(result.getInt("officerId"));
				officer.setOfficerTypeId(result.getInt("officerTypeId"));
				return officer;
			}	
		} finally{
			con.close();
		}
		
			return null;
	}
}
