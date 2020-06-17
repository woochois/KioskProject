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
				pstmt = conn.prepareStatement("SELECT NAME FROM PRODUCT WHERE PSEQ = ?");
				pstmt.setString(1, DB);
				rs = pstmt.executeQuery();
		
				if (rs.next()) { 
					temp = rs.getString("NAME");
				}

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
				pstmt = conn.prepareStatement("SELECT PRICE FROM PRODUCT WHERE PSEQ = ?");
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
				pstmt = conn.prepareStatement("SELECT ID FROM EMPS WHERE ID = ?");
				String Id = "admin";
				pstmt.setString(1, Id);
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					temp = rs.getString("ID");
				}
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
				pstmt = conn.prepareStatement("SELECT PWD FROM EMPS WHERE PWD = ?");
				String Pwd = "admin";
				pstmt.setString(1, Pwd);
				rs = pstmt.executeQuery();

				if (rs.next()) { 
					temp = rs.getString("PWD");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return temp;
		}

		static void PaymentDB(int db1, String db2, int db3, int db4, int db5) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				pstmt = conn.prepareStatement("INSERT INTO ORDERED VALUES (ORDERED_PLUS.NEXTVAL , ORDERED_PLUS.NEXTVAL , ? , ? , ? , ? , ?, SYSDATE)");
				pstmt.setInt(1, db1);
				pstmt.setString(2, db2);
				pstmt.setInt(3, db3);
				pstmt.setInt(4, db4);
				pstmt.setInt(5, db5);
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
//-----------------------------------<어드민 테이블 뷰>--------------------------------------------------//
		
		static String NameDB(int i) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				pstmt = conn.prepareStatement("SELECT NAME FROM ORDERED WHERE PSEQ = ?");
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				
				if (rs.next()) { 
					temp = rs.getString("NAME");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return temp;
		}
		
		static int PriceDB(int i) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				pstmt = conn.prepareStatement("SELECT PRICE FROM ORDERED WHERE PSEQ = ?");
				pstmt.setInt(1, i);
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
		static int CountDB(int i) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				pstmt = conn.prepareStatement("SELECT COUNT FROM ORDERED WHERE PSEQ = ?");
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				if (rs.next()) { 
					tempi = rs.getInt("COUNT");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return tempi;
		} 
		static int TotalDB(int i) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				pstmt = conn.prepareStatement("SELECT TOTAL FROM ORDERED WHERE PSEQ = ?");
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				if (rs.next()) { 
					tempi = rs.getInt("TOTAL");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return tempi;
		} 

		
}
