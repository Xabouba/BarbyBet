package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SQLCommentComponent extends SQLComponent {

	public SQLCommentComponent()
	{
		super();
	}
	
	public ArrayList<HashMap<String, String>> getComments(String idMatch)
	{
		ArrayList<HashMap<String, String>> listComment = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT c.dateComment, u.username, c.comment, u.id FROM Comments c, Users u WHERE c.idMatch = ? AND u.id = c.idUser ORDER BY dateComment DESC LIMIT 0, 20");
		    stmt.setInt(1, Integer.valueOf(idMatch));
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	HashMap<String, String> comment = new HashMap<String, String>();
		    	Timestamp date = rs.getTimestamp(1);
		    	
		    	String hour = new SimpleDateFormat("HH:mm").format(date);
		    	String day = new SimpleDateFormat("E d").format(date);
		    	comment.put("user", rs.getString(2));
		    	comment.put("hour", hour);
		    	comment.put("day", day);
		    	comment.put("comment", rs.getString(3));
		    	
		    	SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
		    	HashMap<String, String> prono = sqlPronoComponent.getProno(idMatch, rs.getString(4));
	    		comment.put("prono", prono.get("prono"));
		    	
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
	
	public void insertComment(String comment, String idMatch, String idUser)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Comments (idUser, idMatch, comment, dateComment) VALUES (?, ?, ?, ?)");
		    
		    stmt.setInt(1, Integer.valueOf(idUser));
		    stmt.setInt(2, Integer.valueOf(idMatch));
		    stmt.setString(3, comment);
		    
		    Date dateToday = new Date();
		    Timestamp date = new Timestamp(dateToday.getTime());
		    stmt.setTimestamp(4, date);
		    
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
}
