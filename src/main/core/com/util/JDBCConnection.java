package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCConnection {
	private static final String DRIVER="com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
	private static final String URL="jdbc:mysql://localhost:3306/inventry?useSSL=false";
	private static final String USERNAME="root";
	private static final String PASSWORD="root";
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
