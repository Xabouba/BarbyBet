package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SQLComponent {

	protected static String _url = "jdbc:mysql://45.55.225.41:3306/barbybet";
	protected static String _user = "root";
	protected static String _password = "sparco";
	
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
	
	public String getImgEquipe(String sName)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT img FROM Team WHERE sname = ?");
		    stmt.setString(1, sName);
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	return rs.getString("img");
		    }
		    else
		    {
		    	return null;
		    }
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return null;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public ArrayList<HashMap<String, String>> getMatchs()
	{
		ArrayList<HashMap<String, String>> listMatch = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, beginDate FROM Matchs m, Team t1, Team t2 WHERE m.teamHId = t1.id AND m.teamAId = t2.id");
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	HashMap<String, String> match = new HashMap<String, String>();
		    	Timestamp date = rs.getTimestamp(5);
		    	GregorianCalendar calendar = new GregorianCalendar();
		    	calendar.setTime(date);
		    	
		    	String yearAsString = String.valueOf(calendar.get(Calendar.YEAR));
		    	int month = calendar.get(Calendar.MONTH) + 1;
		    	String monthAsSring = String.valueOf(month);
		    	if (month < 9)
		    	{
		    		monthAsSring = "0" + monthAsSring;
		    	}
		    	String dayAsString = String.valueOf(calendar.get(Calendar.DATE));
		    	
		    	String dateAsString = yearAsString + monthAsSring + dayAsString;
		    	match.put("date", dateAsString);
		    	match.put("teamH", rs.getString(1));
		    	match.put("teamA", rs.getString(3));
		    	match.put("imgH", rs.getString(2));
		    	match.put("imgA", rs.getString(4));
		    	
		    	listMatch.add(match);
		    }		    	
		    
		    return listMatch;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return null;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public ArrayList<HashMap<String, String>> getComments()
	{
		ArrayList<HashMap<String, String>> listComment = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT dateComment, idUser, comment FROM Comments ORDER BY dateComment DESC LIMIT 0, 20");
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	HashMap<String, String> comment = new HashMap<String, String>();
		    	Timestamp date = rs.getTimestamp(1);
		    	
		    	String hour = new SimpleDateFormat("HH:mm").format(date);
		    	String day = new SimpleDateFormat("E d").format(date);
		    	comment.put("user", "User 1");
		    	comment.put("hour", hour);
		    	comment.put("day", day);
		    	comment.put("comment", rs.getString(3));
		    	
		    	listComment.add(comment);
		    }		    	
		    
		    return listComment;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return null;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public void insertComment(String comment)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Comments (idUser, comment, dateComment) VALUES (?, ?, ?)");
		    
		    stmt.setInt(1, 1);
		    stmt.setString(2, comment);
		    
		    Date dateToday = new Date();
		    Timestamp date = new Timestamp(dateToday.getTime());
		    stmt.setTimestamp(3, date);
		    
		    stmt.executeUpdate();
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
		} 
		finally 
		{
			close(stmt);
			close(connexion);
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
