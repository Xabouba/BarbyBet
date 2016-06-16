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
import java.util.HashMap;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class SQLCommentComponent extends SQLComponent {

	public SQLCommentComponent()
	{
		super();
	}
	
	public ArrayList<HashMap<String, String>> getComments(Long matchId)
	{
		ArrayList<HashMap<String, String>> listComment = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT c.dateComment, u.username, c.comment, u.id FROM Comments c, Users u WHERE c.idMatch = ? AND u.id = c.idUser ORDER BY dateComment DESC LIMIT 0, 20");
		    stmt.setLong(1, matchId);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	HashMap<String, String> comment = new HashMap<String, String>();
		    	Timestamp date = rs.getTimestamp(1);
		    	
		    	Calendar calendar = Calendar.getInstance();
		    	calendar.setTime(date);
		    	
		    	int day = calendar.get(Calendar.DAY_OF_MONTH);
		    	String dayStr = String.valueOf(day);
		    	if(day < 10) {
		    		dayStr = "0" + day;
		    	}
		    	
		    	int month = calendar.get(Calendar.MONTH) + 1;
		    	String monthStr = String.valueOf(month);
		    	if(month < 10) {
		    		monthStr = "0" + month;
		    	}
		    	
		    	int year = calendar.get(Calendar.YEAR);
		    	
		    	String hour = new SimpleDateFormat("HH:mm").format(date);
		    	String dateStr =  dayStr + "/" + monthStr + "/" + year;
		    	comment.put("user", rs.getString(2));
		    	comment.put("hour", hour);
		    	comment.put("date", dateStr);
		    	comment.put("comment", rs.getString(3));
		    	
		    	SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
		    	HashMap<String, String> prono = sqlPronoComponent.getProno(matchId, rs.getLong(4));
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
	
	public void insertComment(String comment, long idMatch, long idUser)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Comments (idUser, idMatch, comment, dateComment) VALUES (?, ?, ?, ?)");
		    
		    // Date in Joda Time with the Europe/Paris timezone instead of the server time
		    LocalDateTime localDateTime = new LocalDateTime();  
		    Timestamp date = new Timestamp(localDateTime.toDateTime(DateTimeZone.forID("Europe/Paris")).getMillis());
		    
		    stmt.setLong(1, idUser);
		    stmt.setLong(2, idMatch);
		    stmt.setString(3, comment);
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
	
	// Insert Message in the group chat
	public void insertGroupChatMessage(String comment, long groupId, long idUser) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO GroupsChat (userId, groupId, comment, dateComment) VALUES (?, ?, ?, ?)");
		    
		    // Date in Joda Time with the Europe/Paris timezone instead of the server time
		    LocalDateTime localDateTime = new LocalDateTime();  
		    Timestamp date = new Timestamp(localDateTime.toDateTime(DateTimeZone.forID("Europe/Paris")).getMillis());
		    
		    stmt.setLong(1, idUser);
		    stmt.setLong(2, groupId);
		    stmt.setString(3, comment);
		    stmt.setTimestamp(4, date);
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(stmt);
			close(connexion);
		}
	}

	public ArrayList<HashMap<String, String>> getGroupChatMessages(Long groupId) {
		ArrayList<HashMap<String, String>> listComment = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT gc.dateComment, u.username, gc.comment, u.id FROM GroupsChat gc, Users u WHERE gc.groupId = ? AND u.id = gc.userId ORDER BY dateComment DESC LIMIT 0, 100");
		    stmt.setLong(1, groupId);
		    
		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	HashMap<String, String> comment = new HashMap<String, String>();
		    	Timestamp date = rs.getTimestamp(1);
		    	
		    	Calendar calendar = Calendar.getInstance();
		    	calendar.setTime(date);
		    	
		    	int day = calendar.get(Calendar.DAY_OF_MONTH);
		    	String dayStr = String.valueOf(day);
		    	if(day < 10) {
		    		dayStr = "0" + day;
		    	}
		    	
		    	int month = calendar.get(Calendar.MONTH) + 1;
		    	String monthStr = String.valueOf(month);
		    	if(month < 10) {
		    		monthStr = "0" + month;
		    	}
		    	
		    	int year = calendar.get(Calendar.YEAR);
		    	
		    	String hour = new SimpleDateFormat("HH:mm").format(date);
		    	String dateStr =  dayStr + "/" + monthStr + "/" + year;
		    	comment.put("user", rs.getString(2));
		    	comment.put("hour", hour);
		    	comment.put("date", dateStr);
		    	comment.put("comment", rs.getString(3));
		    	
		    	listComment.add(comment);
		    }		    	
		    
		    return listComment;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
}
