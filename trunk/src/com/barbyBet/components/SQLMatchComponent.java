package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public ArrayList<Match> getMatchs()
	{
		ArrayList<Match> listMatch = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport ORDER BY m.beginDate");
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Match match = new Match();
		    	match.setId(rs.getInt(6));
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
	
	public Match getMatch(int matchId)
	{
		Match match = new Match();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.id = ? ");
		    stmt.setInt(1, matchId);
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	match.setId(rs.getInt(6));
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
		    	
			    isMatchInserted = insertMatch(m, stmt, connection);
			    
			    if(!isMatchInserted) {
			    	System.out.println("The match " + m.getHomeTeam() + " - " + m.getAwayTeam() + " has not been inserted !");
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

	public boolean insertMatch(Match m, PreparedStatement stmt, Connection connection) {
		try {
			stmt = connection.prepareStatement("INSERT INTO Matchs (idSport, idCompetition, journee, teamHId, teamAId, "
	    		+ "scoreH, scoreA, beginDate, statut, oddsHome, oddsDraw, oddsAway) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    
			stmt.setInt(1, m.getIdSport());
	    	stmt.setInt(2, m.getIdCompetition());
		    stmt.setInt(3, m.getJournee());
		    stmt.setInt(4, m.getHomeTeam().getId());
		    stmt.setInt(5, m.getAwayTeam().getId());
		    stmt.setInt(6, m.getHomeScore());
		    stmt.setInt(7, m.getAwayScore());
		    stmt.setTimestamp(8, m.getBeginDate());
		    stmt.setInt(9, m.getStatut());
		    stmt.setFloat(10, m.getOdds().getHomeOdd());
		    stmt.setFloat(11, m.getOdds().getDrawOdd());
		    stmt.setFloat(12, m.getOdds().getAwayOdd());
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
}