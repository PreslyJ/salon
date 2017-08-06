package com.util;

public class Formatter {
	@SuppressWarnings("deprecation")
	public static java.util.Date convertToUtilDate(String date){
		return new java.util.Date(date);
	}
	public static java.sql.Date convertToSqlDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
	public static int stringToInt(String str){
		try {
		      return Integer.parseInt(str);
		} catch (NumberFormatException e) {
		      return 0;
		}
	}
}
