package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.barbyBet.object.Match;
import com.barbyBet.object.Odds;
import com.barbyBet.object.Team;

public class SQLMatchComponent extends SQLComponent
{
	public SQLMatchComponent()
	{
		super();
	}
	
	public ArrayList<Match> getMatchs(Date dateToday)
	{
		ArrayList<Match> listMatch = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND (m.beginDate BETWEEN '2016-03-13 14:15:55' AND '2016-04-03 14:15:55') ORDER BY m.beginDate");
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Match match = new Match();
		    	match.setId(rs.getLong(6));
		    	match.setBeginDate(rs.getTimestamp(5));
		    	
		    	Team homeTeam = new Team();
		    	homeTeam.setName(rs.getString(1));
		    	homeTeam.setImg(rs.getString(2));
		    	match.setHomeTeam(homeTeam);
		    	
		    	Team awayTeam = new Team();
		    	awayTeam.setName(rs.getString(3));
		    	awayTeam.setImg(rs.getString(4));
		    	match.setAwayTeam(awayTeam);
		    	
		    	match.setHomeScore(rs.getInt(7));
		    	match.setAwayScore(rs.getInt(8));
		    	match.setStatut(rs.getInt(9));
		    
		    	Odds odds = new Odds(rs.getFloat(10), rs.getFloat(11), rs.getFloat(12));
		    	match.setOdds(odds);
		    	
		    	match.setCompetition(rs.getString(13));
		    	match.setSport(rs.getString(14));

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
	
	public ArrayList<Match> getMatchsFromCompetition(int idCompetition)
	{
		ArrayList<Match> listMatch = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name, t1.id, t2.id  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.idCompetition = ? AND m.journee < 12 ORDER BY m.beginDate");
		    stmt.setInt(1, idCompetition);
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Match match = new Match();
		    	match.setId(rs.getLong(6));
		    	match.setBeginDate(rs.getTimestamp(5));
		    	
		    	Team homeTeam = new Team();
		    	homeTeam.setId(rs.getInt(15));
		    	homeTeam.setName(rs.getString(1));
		    	homeTeam.setImg(rs.getString(2));
		    	match.setHomeTeam(homeTeam);
		    	
		    	Team awayTeam = new Team();
		    	awayTeam.setId(rs.getInt(16));
		    	awayTeam.setName(rs.getString(3));
		    	awayTeam.setImg(rs.getString(4));
		    	match.setAwayTeam(awayTeam);
		    	
		    	match.setHomeScore(rs.getInt(7));
		    	match.setAwayScore(rs.getInt(8));
		    	match.setStatut(rs.getInt(9));
		    
		    	Odds odds = new Odds(rs.getFloat(10), rs.getFloat(11), rs.getFloat(12));
		    	match.setOdds(odds);
		    	
		    	match.setCompetition(rs.getString(13));
		    	match.setSport(rs.getString(14));

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
	
	public Match getMatch(Long matchId)
	{
		Match match = new Match();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.id = ? ");
		    stmt.setLong(1, matchId);
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	match.setId(rs.getLong(6));
		    	match.setBeginDate(rs.getTimestamp(5));
		    	
		    	Team homeTeam = new Team();
		    	homeTeam.setName(rs.getString(1));
		    	homeTeam.setImg(rs.getString(2));
		    	match.setHomeTeam(homeTeam);
		    	
		    	Team awayTeam = new Team();
		    	awayTeam.setName(rs.getString(3));
		    	awayTeam.setImg(rs.getString(4));
		    	match.setAwayTeam(awayTeam);
		    	
		    	match.setHomeScore(rs.getInt(7));
		    	match.setAwayScore(rs.getInt(8));
		    	match.setStatut(rs.getInt(9));
		    
		    	match.setCompetition(rs.getString(10));
		    	match.setSport(rs.getString(11));
		    }
		    
		    return match;
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

	public boolean insertMatchs(List<Match> matchs) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DriverManager.getConnection(_url, _user, _password);
		    for(Match m : matchs) {
		    	boolean isMatchInserted;
		    	
			    isMatchInserted = insertMatch(m, connection, stmt);
			    
			    if(!isMatchInserted) {
			    	System.out.println("The match " + m.getHomeTeam().getName() + " - " + m.getAwayTeam().getName() + " has not been inserted !");
			    }
		    }
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			
			return false;
		} finally {
			close(stmt);
			close(connection);
		}
		
		return true;
	}

	public boolean insertMatch(Match m, Connection connection, PreparedStatement stmt) {
		try {
			stmt = connection.prepareStatement("INSERT INTO Matchs (idWebService, idSport, idCompetition, journee, teamHId, teamAId, "
	    		+ "scoreH, scoreA, beginDate, statut, oddsHome, oddsDraw, oddsAway) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    
			stmt.setLong(1, m.getIdWebService());
			stmt.setLong(2, m.getIdSport());
	    	stmt.setLong(3, m.getIdCompetition());
		    stmt.setInt(4, m.getJournee());
		    stmt.setInt(5, m.getHomeTeam().getId());
		    stmt.setInt(6, m.getAwayTeam().getId());
		    stmt.setInt(7, m.getHomeScore());
		    stmt.setInt(8, m.getAwayScore());
		    stmt.setTimestamp(9, m.getBeginDate());
		    stmt.setInt(10, m.getStatut());
		    stmt.setFloat(11, m.getOdds().getHomeOdd());
		    stmt.setFloat(12, m.getOdds().getDrawOdd());
		    stmt.setFloat(13, m.getOdds().getAwayOdd());
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean updateMatchs(List<Match> matchs) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
		    connection = DriverManager.getConnection(_url, _user, _password);
		    for (Match match : matchs) {
				updateMatch(match, connection, stmt);
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(stmt);
			close(connection);
		}
		
		return true;
	}
	
	public boolean updateMatch(Match match, Connection connection, PreparedStatement stmt) {
	    try {
			stmt = connection.prepareStatement("UPDATE Matchs SET scoreH = ?, scoreA = ? WHERE idWebService = ?");

			stmt.setInt(1, match.getHomeScore());
		    stmt.setInt(2, match.getAwayScore());
		    stmt.setLong(3, match.getIdWebService());
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	    
		return true;
	}
}
