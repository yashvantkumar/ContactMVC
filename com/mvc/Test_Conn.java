package com.mvc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test_Conn {

	public static void main(String[] args)
	{
		Connection con=null;
		try
		{
			Class.forName("org.hsqldb.jdbcDriver");
			con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/","SA","");
			if(con==null)
				System.out.println("Failed");
			else
				System.out.println(con);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
