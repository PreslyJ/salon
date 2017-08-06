package com.salon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.domain.Range;
import com.dto.ProductTypeDTO;
import com.util.JDBCConnection;

public class ProTypesDAO {

	public static String insertTitle(String code,String description,String status,int rangeid,double amt){
		
		if(code==null||description==null||code==""||description==""){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();

			try {	 
				PreparedStatement query=con.prepareStatement("SELECT * FROM protypes WHERE code=? ");
				query.setString(1, code);
				query.executeQuery();
				ResultSet reslt=query.getResultSet();     
				boolean y=reslt.next();
				if(y==true){
					return "code already exists";
				}else{			
					query = con.prepareStatement("INSERT INTO protypes (code,description,status,rangeid,proamount)VALUES (?,?,?,?,?)");
					query.setString(1, code);
					query.setString(2, description);
					query.setString(3, status);
					query.setInt(4, rangeid);
					query.setDouble(5, amt);
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
				PreparedStatement query=con.prepareStatement("UPDATE protypes SET code= ?,description= ? , status = ? WHERE protypesid=?;");
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
			PreparedStatement query=con.prepareStatement("DELETE FROM protypes where protypesid=?");
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
			PreparedStatement query=con.prepareStatement("SELECT * FROM protypes ");			
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
			PreparedStatement query=con.prepareStatement("SELECT code FROM protypes where protypesid=?");
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
	
	
	public static List<ProductTypeDTO> getAllRecords(){
		List<ProductTypeDTO> titles = new ArrayList<ProductTypeDTO>();
		Connection con=null;
		con=JDBCConnection.getConnection();

		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM protypes p inner join ranges r on p.rangeid=r.rangeid ");			
			query.executeQuery();
			ResultSet rs=query.getResultSet();     			
			while (rs.next()) {
				ProductTypeDTO title = new ProductTypeDTO();
				title.setDescription(rs.getString("p.protypesid"));
				title.setRangeCode(rs.getString("r.code"));
				title.setRangeDescription(rs.getString("r.description"));
				title.setCode(rs.getString("p.code"));
				title.setDescription(rs.getString("p.description"));
				title.setProductAmount(rs.getDouble("p.proamount"));
				title.setStatus(rs.getString("p.status"));
				title.setProductTypeId(rs.getInt("p.protypesid"));
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
}
