package com.icnman.tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static String id="tester1";
	private static String pass="cjsekd13";
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,id,pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
}
