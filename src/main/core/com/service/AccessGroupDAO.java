package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import com.domain.AccessibleMenu;
import com.util.JDBCConnection;

public class AccessGroupDAO {

	public static String addAccessibleMenu(List<AccessibleMenu> accessibleMenus){

		if(accessibleMenus.size()==0){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();
			try {
				for (AccessibleMenu accessibleMenu : accessibleMenus) {
					PreparedStatement query = con.prepareStatement("INSERT INTO accessiblemenu (menuid,officertypeid,nodeid) VALUES (?,?,?)");
					query.setInt(1, accessibleMenu.getMenuId());
					query.setInt(2, accessibleMenu.getOfficerTypeId());
					query.setInt(3, accessibleMenu.getNodeid());
					query.executeUpdate();
				}
				return "record added successfully";
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
	
	public static String modifyAccessibleMenu(List<AccessibleMenu> accessibleMenus){

		if(accessibleMenus.size()==0){
			return "Please fill out required fields";
		}else{
			Connection con=null;
			con=JDBCConnection.getConnection();
			try {
				int nodeId= accessibleMenus.get(0).getNodeid();
				int officerTypeId= accessibleMenus.get(0).getOfficerTypeId();
				PreparedStatement queryDel = con.prepareStatement("DELETE  FROM accessiblemenu WHERE nodeid=? AND officertypeid=?");
				queryDel.setInt(1,nodeId);
				queryDel.setInt(2, officerTypeId);
				queryDel.executeUpdate();
				if(officerTypeId==1 & nodeId==4){
					PreparedStatement query = con.prepareStatement("INSERT INTO accessiblemenu (menuid,officertypeid,nodeid) VALUES (?,?,?)");
					query.setInt(1,11);
					query.setInt(2, 1);
					query.setInt(3, 4);
					query.executeUpdate();
				}			
				for (AccessibleMenu accessibleMenu : accessibleMenus) {
					PreparedStatement query = con.prepareStatement("INSERT INTO accessiblemenu (menuid,officertypeid,nodeid) VALUES (?,?,?)");
					query.setInt(1, accessibleMenu.getMenuId());
					query.setInt(2, accessibleMenu.getOfficerTypeId());
					query.setInt(3, accessibleMenu.getNodeid());
					query.executeUpdate();
				}
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
}
