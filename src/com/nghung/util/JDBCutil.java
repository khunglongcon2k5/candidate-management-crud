/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCutil {
	public static Connection getConnection() {
		Connection c = null;
		try {
			String url = "jdbc:sqlserver://ACER\\SQLEXPRESS:1433;databaseName=CandidateManagement;Encrypt=True;TrustServerCertificate=True";
			String username = "sa";
			String password = "khunglongcon2k5";
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.err.println("Lỗi kết nối CSDL: " + e.getMessage());
			e.printStackTrace();
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		if (c != null) {
			try {
				if (!c.isClosed()) {
					c.close();
					System.out.println("Connection closed successfully.");
				}
			} catch (SQLException e) {
				System.err.println("Error closing Connection: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
				System.out.println("PreparedStatement closed successfully.");
			} catch (SQLException e) {
				System.err.println("Error closing PreparedStatement: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				System.out.println("ResultSet closed successfully.");
			} catch (SQLException e) {
				System.err.println("Error closing ResultSet: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
