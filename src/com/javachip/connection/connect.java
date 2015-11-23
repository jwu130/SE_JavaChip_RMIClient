package com.javachip.connection;
/**************************
 * 

 * Anything Involving Connecting to the DB
 * 
 */
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connect {
	
	static Connection conn = null;	

	//testConnection creates the connection to ORACLE database
/***************ORACLE***********************/
	public static String testConnection(){
		String dbURL = "jdbc:oracle:thin:@bonnet19.cs.qc.edu:1521:dminor";
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String user = "lackner";
		String password = "guest";
		
		PreparedStatement pstmt = null;	
		
		ResultSet rs = null;
		String newTime = "";
		String result = "";
		
		//Find OracleDriver
		try{
			Class.forName(driverName);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try{
			//Create connection with database
			conn = DriverManager.getConnection(dbURL, user, password);
			
			//Query database for time
			String stmt = "select sysdate from dual";
			pstmt = conn.prepareStatement(stmt);
			rs = pstmt.executeQuery();
			
			//Display results
			if(rs.next()){
				newTime = rs.getString(1);
				result = ( "System clock of Oracle at bonnet19 is:" + newTime );
			}
			
			rs.close();
			pstmt.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
/***************ORACLE*************************/
	
	
//getSQLConnection establishes connection to mySQL database
/************SQL
 * @throws ClassNotFoundException ******************************/
	public static void getSQLConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://www.db4free.net:3306/javachip";
		String username = "javachip";
		String password = "professorSy";

		System.out.println("Connecting database...");
		
		PreparedStatement pstmt = null;	
		java.sql.Statement statement;
		String stmt;
		
		//SQL Driver
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		    
			try{	
				
				conn = DriverManager.getConnection(url, username, password);
				System.out.println("connSQL established");
				
			}catch(SQLException se){
				 System.out.println("Cannot connect to the database!");
			     se.printStackTrace();
			}
			
		}
		finally{
			System.out.println("Finally");
		}	
	}

 /**************************************************************************/

	//Close connection with database
	public static void closeConn(){
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
		}
	}
	
	public static Connection getConn(){
		return conn;
	}
	
	public static void setConn(Connection connection){
		conn = connection;
	}

}//class
