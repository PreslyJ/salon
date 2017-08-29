package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCConnection {
	private static final String DRIVER="org.postgresql.Driver";
	private static final String URL="jdbc:postgresql://localhost:5432/postgres";
	private static final String USERNAME="postgres";
	private static final String PASSWORD="123456";
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Connection con=null;
		try {
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return con;
	}
}
