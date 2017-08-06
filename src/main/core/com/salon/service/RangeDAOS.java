package com.salon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.domain.Range;
import com.util.JDBCConnection;

public class RangeDAOS {

	public static String insertTitle(String code,String description,String status){
		
		if(code==null||description==null||code==""||description==""){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();

			try {	 
				PreparedStatement query=con.prepareStatement("SELECT * FROM ranges WHERE code=? ");
				query.setString(1, code);
				query.executeQuery();
				ResultSet reslt=query.getResultSet();     
				boolean y=reslt.next();
				if(y==true){
					return "code already exists";
				}else{			
					query = con.prepareStatement("INSERT INTO ranges (code,description,status)VALUES (?,?,?)");
					query.setString(1, code);
					query.setString(2, description);
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

public static String modifyTitle(String code,String description,String status,int titleId){
		
		if(code==null||description==null||code==""||description==""){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();

			try {	 
				PreparedStatement query=con.prepareStatement("UPDATE ranges SET code= ?,description= ? , status = ? WHERE rangeid=?;");
					query.setString(1, code);
					query.setString(2, description);
					query.setString(3, status);
					query.setInt(4, titleId);
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

	public static String deleteTitle(int titleId){
		Connection con=null;
		con=JDBCConnection.getConnection();

		try {	 
			PreparedStatement query=con.prepareStatement("DELETE FROM ranges where rangeid=?");
			query.setInt(1, titleId);
			query.executeUpdate();
			return "record deleted successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "cannot delete title";
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Range> getAllTitles(){
		List<Range> titles = new ArrayList<Range>();
		Connection con=null;
		con=JDBCConnection.getConnection();

		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM ranges ");			
			query.executeQuery();
			ResultSet rs=query.getResultSet();     			
			while (rs.next()) {
				Range title = new Range();
				title.setRangeId(rs.getInt("rangeid"));
				title.setCode(rs.getString("code"));
				title.setDescription(rs.getString("description"));
				title.setStatus(rs.getString("status"));
				titles.add(title);
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
		
		return titles;
	}
	public static String getTitleCodeByTitleId(int id){
		Connection con=null;
		con=JDBCConnection.getConnection();

		try {	 
			PreparedStatement query=con.prepareStatement("SELECT code FROM ranges where rangeid=?");
			query.setInt(1, id);
			query.executeQuery();
			ResultSet result=query.getResultSet();     			
			while (result.next()) {
			  return result.getString("code");
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
}
