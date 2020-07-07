package com.newer.person.data.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	static ComboPooledDataSource dataSource = null; 
	
	static{
		try {
			  Properties prop = new Properties();
			  InputStream inStream  = DBUtil.class.getClassLoader().getResourceAsStream("c3p0.properties");
			  prop.load(inStream);
			  
			  dataSource = new ComboPooledDataSource();
			  dataSource.setDriverClass(prop.getProperty("driverClass"));
				dataSource.setJdbcUrl(prop.getProperty("jdbcUrl"));
				dataSource.setUser(prop.getProperty("user"));
				dataSource.setPassword(prop.getProperty("password"));
				dataSource.setInitialPoolSize(Integer.parseInt(prop
						.getProperty("initialPoolSize")));
				dataSource.setMaxPoolSize(Integer.parseInt(prop
						.getProperty("maxPoolSize")));
				dataSource.setMaxIdleTime(Integer.parseInt(prop
						.getProperty("maxIdleTime")));
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
	
		public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		

}











