package controller;

import java.sql.*;

public class KioskDBController {
	
	//=================================================<데이터베이스 뷰>======================================================//	
		
		static String url = "jdbc:oracle:thin:@localhost:1521:xe", user = "team", password = "1234";
		static Connection conn = null;
		static Statement stmt = null;
		static ResultSet rs = null;
		static PreparedStatement pstmt = null;
		static String temp = "";
		static int tempi = 0;
		
		static String MenuNameDB(String DB) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "SELECT NAME FROM PRODUCT WHERE PSEQ = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, DB);
				rs = pstmt.executeQuery();
		
				if (rs.next()) { 
					temp = rs.getString("NAME");
		
				}
				System.out.println(temp);
		
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return temp;
		}
		
		static int MenuPriceDB(int DB) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "SELECT PRICE FROM PRODUCT WHERE PSEQ = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, DB);
				rs = pstmt.executeQuery();
		
				if (rs.next()) { 
					tempi = rs.getInt("PRICE");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return tempi;
		}
		
		static String AdminIdDB() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "SELECT ID FROM EMPS WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				String Id = "admin";
				pstmt.setString(1, Id);
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					temp = rs.getString("ID");
				}
				System.out.println(temp);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return temp;
		}
		
		static String AdminPwDB() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "SELECT PWD FROM EMPS WHERE PWD = ?";
				pstmt = conn.prepareStatement(sql);
				String Pwd = "admin";
				pstmt.setString(1, Pwd);
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					temp = rs.getString("PWD");
				}
				System.out.println(temp);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return temp;
		}
		
		static void PaymentCashDB(int DB1, int DB2, String DB3, int DB4, int DB5) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "INSERT INTO ORDERED VALUES (? , ? , ? , ? , ? , SYSDATE)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, DB1);
				pstmt.setInt(2, DB2);
				pstmt.setString(3, DB3);
				pstmt.setInt(4, DB4);
				pstmt.setInt(5, DB5);
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					rs.getString("NO");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		static void PaymentCardDB(Integer db1, Integer db2, String db3, Integer db4, Integer db5) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "INSERT INTO ORDERED VALUES (? , ? , ? , ? , ? , SYSDATE)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, db1);
				pstmt.setInt(2, db2);
				pstmt.setString(3, db3);
				pstmt.setInt(4, db4);
				pstmt.setInt(5, db5);
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					rs.getString("NO");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
}
