package com.mindtree.codingChallenge.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.codingChallenge.exception.daoException.DBUtillException;


public class DBUtill {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/studentClassRoom";
	private static final String USERNAME="root";
	private static final String PASSWORD="root";
	public static Connection getMyConnection() throws Exception
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch(SQLException e)
		{
			throw new DBUtillException("connection fault");
		}
		return con;
	}
	public static void closeResource(Connection con) throws Exception {
		if(con!=null)
		{
			con.close();
		}
	}
	public static void closeResource(Statement st) throws Exception {
		if(st!=null)
		{
			st.close();
		}
	}
	
}