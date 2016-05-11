package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLComponent {
	
	protected int _port = 3306;
    protected String _user = "root";
    protected String _password = "malikloic";
    protected String _url = "jdbc:mysql://localhost:" +_port+ "/barbybet";
    protected String _driverName = "com.mysql.jdbc.Driver";
	
	public SQLComponent()
	{
		try 
		{
            Class.forName(_driverName);
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
	        	System.out.println(e.getMessage());
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
	        	System.out.println(e.getMessage());
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
	        	System.out.println(e.getMessage());
	        }
	    }
	}
	
}
