package com.bit.util;

import java.sql.*;

//싱글톤 패턴으로 mysql 연결하는 connection파일 만들기
public class BitMy {
	private static Connection conn;
	private final static String driver="com.mysql.cj.jdbc.Driver";
	private final static String url="jdbc:mysql://localhost:3306/scott";
	private final static String user="user01";
	private final static String password="1234";
	
	private BitMy() {}
	
	public static Connection getConnection() throws SQLException {
		if(conn==null || conn.isClosed()) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn=DriverManager.getConnection(url, user, password);
			
			
		}
		return conn;
	}
}
