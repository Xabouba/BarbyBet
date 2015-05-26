package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class SQLMatchComponent extends SQLComponent
{
	public SQLMatchComponent()
	{
		super();
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
		    stmt = connexion.prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.isEnded, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport ORDER BY m.beginDate");
		    
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
		    	match.put("dateTime", String.valueOf(date.getTime()));
		    	match.put("teamH", rs.getString(1));
		    	match.put("teamA", rs.getString(3));
		    	match.put("imgH", rs.getString(2));
		    	match.put("imgA", rs.getString(4));
		    	match.put("scoreH", rs.getString(7));
		    	match.put("scoreA", rs.getString(8));
		    	match.put("isEnded", String.valueOf(rs.getBoolean(9)));
		    	match.put("matchId", rs.getString(6));
		    	match.put("competition", rs.getString(10));
		    	match.put("sport", rs.getString(11));

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

}
