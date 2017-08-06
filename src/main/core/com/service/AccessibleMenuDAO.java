package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.domain.AccessibleMenu;
import com.util.JDBCConnection;

public class AccessibleMenuDAO {
	public static List<AccessibleMenu> getAccessibleMenus(int officerTypeId){
		List<AccessibleMenu> accessibleMenus = new ArrayList<AccessibleMenu>();

		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM accessibleMenu WHERE  officertypeid= ?");
			query.setInt(1, officerTypeId);
			query.executeQuery();
			ResultSet rs=query.getResultSet();     
			
			while (rs.next()) {
				AccessibleMenu accessibleMenu = new AccessibleMenu();
				accessibleMenu.setAccessibleMenuId(rs.getInt("accessibleMenuid"));
				accessibleMenu.setMenuId(rs.getInt("menuid"));
				accessibleMenu.setOfficerTypeId(rs.getInt("officertypeid"));
				accessibleMenus.add(accessibleMenu);
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
		
		
		return accessibleMenus;
	}
	
	
}
