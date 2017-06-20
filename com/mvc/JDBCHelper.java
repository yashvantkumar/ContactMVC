package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHelper
{
	public static Connection getConnection()
	{
		//Connection con=null;
		try
		{
			Class.forName(CONSTANTS.DRIVER);
			Connection con=DriverManager.getConnection(CONSTANTS.URL,CONSTANTS.UID,"");
			return con;
		}
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
			return null;
		}
		//return con;
	}
	public static void close(Connection x)
	{
		if(x!=null)
		try 
		{
			x.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public static void close(ResultSet x)
	{
		if(x!=null)
		try 
		{
			x.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(Statement x)
	{
		if(x!=null)
		try 
		{
			x.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
