package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.domain.OfficerType;
import com.util.JDBCConnection;

public class OfficerTypeDAO {
	public static String addOfficerType(String code,String description,String status){
		
		if(code==""||description==""){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();

			try {	 
				PreparedStatement query=con.prepareStatement("SELECT * FROM officertype WHERE code=? ");
				query.setString(1, code);
				query.executeQuery();
				ResultSet result=query.getResultSet();     
				if(result.next()){
					return "code already exists";
				}else{			
					query = con.prepareStatement("INSERT INTO officertype (code,description,status)VALUES (?,?,?)");
					query.setString(1, code);
					query.setString(2,description);
					query.setString(3, status);
					query.executeUpdate();
					return "record added successfully";
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
	
public static String modifyOfficerType(String code,String description,String status,int officerTypeId){
		
		if(code==null||description==null||code==""||description==""){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();

			try {	 
				PreparedStatement query=con.prepareStatement("UPDATE officertype SET code= ?,description= ? , status = ? WHERE officertypeid=?;");
					query.setString(1, code);
					query.setString(2, description);
					query.setString(3, status);
					query.setInt(4, officerTypeId);
					query.executeUpdate();
					return "record modified successfully";
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

	public static String deleteOfficerType(int officerTypeId){
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("DELETE FROM officertype where officertypeid=?");
			query.setInt(1, officerTypeId);
			query.executeUpdate();
			return "Record deleted successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Cannot delete Officer Type";
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	public static List<OfficerType> getAllOfficerTypes(){
		List<OfficerType> officerTypes = new ArrayList<OfficerType>();
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM officertype ");			
			query.executeQuery();
			ResultSet result=query.getResultSet();     			
			while (result.next()) {
				OfficerType officerType = new OfficerType();
				officerType.setCode(result.getString("code"));
				officerType.setDescription(result.getString("description"));
				officerType.setStatus(result.getString("status"));
				officerType.setOfficerTypeId(result.getInt("officertypeid"));
				officerTypes.add(officerType);
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
		
		return officerTypes;
	}
	
	
	public static String getOfficerTypeById(int officerTypeId){
		String officerType="";
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM officertype WHERE officertypeid =?");			
			query.setInt(1, officerTypeId);
			query.executeQuery();
			ResultSet result=query.getResultSet();     
			while (result.next()) {
				officerType = result.getString("code");
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

		return officerType;
	}


}
