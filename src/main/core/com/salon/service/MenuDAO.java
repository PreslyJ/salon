package com.salon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.domain.AccessibleMenu;
import com.domain.Menu;
import com.util.JDBCConnection;

public class MenuDAO {

	public static List<Menu> getAllMenuByNode(int node) {
		List<Menu> menus = new ArrayList<Menu>();
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM menuS WHERE node=?");
			query.setInt(1, node);
			query.executeQuery();
			ResultSet rs=query.getResultSet();     
			
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getInt("menuid"));
				menu.setUrl(rs.getString("url"));
				menu.setLabel(rs.getString("label"));
				menu.setNode(rs.getString("node"));
				menu.setAnchorId(rs.getString("anchorId"));
				menus.add(menu);
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
		
		return menus;
	}
	
	
	public static List<AccessibleMenu> getAllMenuByNodeNOfficer(int node,int officerTypeId) {
		List<AccessibleMenu> menus = new ArrayList<AccessibleMenu>();
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 
			PreparedStatement query=con.prepareStatement("SELECT * FROM accessiblemenuS WHERE nodeid=? AND officertypeid=?");
			query.setInt(1, node);
			query.setInt(2, officerTypeId);
			query.executeQuery();
			ResultSet rs=query.getResultSet();     
			
			while (rs.next()) {
				AccessibleMenu menu = new AccessibleMenu();
				menu.setMenuId(rs.getInt("menuid"));
				menu.setNodeid(rs.getInt("nodeid"));
				menus.add(menu);
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
		
		
		return menus;
	}
	
	public static List<Menu> getAllMenuByOfficer(int officerId) {
		List<Menu> menus = new ArrayList<Menu>();
		Connection con=null;
		con=JDBCConnection.getConnection();
		try {	 

			PreparedStatement query=con.prepareStatement("SELECT * FROM accessiblemenuS WHERE officertypeid=?");
			query.setInt(1, officerId);
			query.executeQuery();
			ResultSet rs=query.getResultSet();     
			
			while (rs.next()) {
				int menuId = rs.getInt("menuid");
				PreparedStatement queryMenu=con.prepareStatement("SELECT * FROM menuS WHERE menuid=?");
				queryMenu.setInt(1, menuId);
				queryMenu.executeQuery();
				ResultSet resultSet=queryMenu.getResultSet(); 
				while (resultSet.next()) {
				Menu menu = new Menu();
				menu.setMenuId(resultSet.getInt("menuid"));
				menu.setUrl(resultSet.getString("url"));
				menu.setLabel(resultSet.getString("label"));
				menu.setNode(resultSet.getString("node"));
				menu.setAnchorId(resultSet.getString("anchorId"));
				menus.add(menu);
				}
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
		
		return menus;
	}
}
