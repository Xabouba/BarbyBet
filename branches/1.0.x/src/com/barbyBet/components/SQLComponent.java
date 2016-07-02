package com.barbyBet.components;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLComponent {

	// Amazon
	// protected static String _url = "jdbc:mysql://mysql-instance1.chlueibc5lof.us-west-2.rds.amazonaws.com:3306/barbybet";
	
	// Digital Ocean & Localhost
	protected static String _url = "jdbc:mysql://localhost:3306/barbybet";
	protected static String _user = "root";
	protected static String _password = "malikloic";
	
	public SQLComponent()
	{
		try 
		{
            Class.forName("com.mysql.jdbc.Driver");
        } 
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
        }
	}
	
		
	public void close(Connection connexion)
	{
		if (connexion != null)
	    {
	        try 
	        {
	            connexion.close();
	        } 
	        catch (SQLException e) 
	        {
	        	
	        }
	    }
	}
	
	public void close(PreparedStatement stmt)
	{
		if (stmt != null)
	    {
	        try 
	        {
	        	stmt.close();
	        } 
	        catch (SQLException e) 
	        {
	        	
	        }
	    }
	}
	
	public void close(ResultSet rs)
	{
		if (rs != null)
	    {
	        try 
	        {
	        	rs.close();
	        } 
	        catch (SQLException e) 
	        {
	        	
	        }
	    }
	}
	
}
