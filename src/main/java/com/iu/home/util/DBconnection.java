package com.iu.home.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnection {

	
	//getConnection
	public static Connection getConnection() throws Exception {
		//1.  연결 정보
		String user = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		//2. Driver를 메모리에 로딩
		Class.forName(driver);
		
		//3. DB 연결	
		return DriverManager.getConnection(url, user, password);
				
	
	}
	
	public static void disConnect(Connection con,PreparedStatement st) throws Exception {
		st.close();
		con.close();
	}
	public static void disConnect(ResultSet rs, Connection con,PreparedStatement st) throws Exception {
		st.close();
		con.close();
		rs.close();
	}
	

	
	
	//배포전 삭제하세요
	public static void main(String[] args) {
		 try {
			Connection con = DBconnection.getConnection();
			System.out.println(con != null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
