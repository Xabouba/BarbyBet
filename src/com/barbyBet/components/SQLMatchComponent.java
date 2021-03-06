package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.barbyBet.object.Match;
import com.barbyBet.object.Odds;
import com.barbyBet.object.Team;
import com.barbyBet.tools.MatchStatus;

public class SQLMatchComponent extends SQLComponent
{
	public SQLMatchComponent()
	{
		super();
	}
	
	public ArrayList<Match> getMatchs(Date dateToday, int status)
	{
		ArrayList<Match> listMatch = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{	
			List<String> statusList = new ArrayList<String>();
			if(MatchStatus.ALL == status) {
				statusList.add(String.valueOf(MatchStatus.ENDED));
			} 
			
			if(MatchStatus.ALL == status || MatchStatus.NOT_ENDED == status) {
				statusList.add(String.valueOf(MatchStatus.NOT_STARTED));
			}
			
			if(MatchStatus.ALL == status || MatchStatus.NOT_ENDED == status || MatchStatus.CURRENT == status) {
				statusList.add(String.valueOf(MatchStatus.FIRST_HALF));	
				statusList.add(String.valueOf(MatchStatus.HALFTIME)); 	
				statusList.add(String.valueOf(MatchStatus.SECOND_HALF)); 
				statusList.add(String.valueOf(MatchStatus.OVERTIME)); 	
				statusList.add(String.valueOf(MatchStatus.PENALTY)); 	
			} else {
				statusList.add(String.valueOf(status));
			}
			
			
			
			String sqlQuery = "SELECT t1.name, t1.img, t2.name, t2.img, "
					+ "m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, "
					+ "c.name, s.name  "
					+ "FROM Matchs m, Team t1, Team t2, Sport s, Competition c  "
					+ "WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.statut IN (" + String.join(",", statusList) + ") " 
					+ "ORDER BY m.beginDate";
			//  LIMIT 0, 24
			
			//AND (m.beginDate BETWEEN '2016-03-13 14:15:55' AND '2016-04-03 14:15:55')
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement(sqlQuery);
		    
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
	
	public List<Match> getMatchByGroup(int idCompetition, String groupName)
	{
		ArrayList<Match> listMatch = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name, t1.id, t2.id  FROM Matchs m, Team t1, Team t2, Sport s, Competition c, InfoTeam i  WHERE i.idCompetition = c.id AND i.idTeam = m.teamHId AND i.groupName = ? AND m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.idCompetition = ? ORDER BY m.beginDate");
		    stmt.setString(1, groupName);
		    stmt.setInt(2, idCompetition);
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
	
	public List<Match> getMatchByStage(int idCompetition, String stage)
	{
		ArrayList<Match> listMatch = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name, t1.id, t2.id  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE  m.journee = ? AND m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.idCompetition = ? ORDER BY m.beginDate");
		    stmt.setString(1, stage);
		    stmt.setInt(2, idCompetition);
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

	public boolean hasMatchBegin(Long matchId)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT * FROM Matchs m WHERE m.statut <> ? AND m.id = ? ");
		    
		    stmt.setInt(1, MatchStatus.NOT_STARTED);
		    stmt.setLong(2, matchId);
		    
		    rs = stmt.executeQuery();
		    return rs.next();
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return false;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public boolean hasMatchEnded(Long matchIdWebService)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT * FROM Matchs m WHERE m.statut = ? AND m.idWebService = ? ");
		    
		    stmt.setInt(1, MatchStatus.ENDED);
		    stmt.setLong(2, matchIdWebService);
		    
		    rs = stmt.executeQuery();
		    return rs.next();
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return false;
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
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			close(stmt);
			close(connection);
		}
		
		return true;
	}
	
	public boolean updateMatch(Match match, Connection connection, PreparedStatement stmt) {
	    try {
			stmt = connection.prepareStatement("UPDATE Matchs SET scoreH = ?, scoreA = ?, statut = ? WHERE idWebService = ?");

			stmt.setInt(1, match.getHomeScore());
		    stmt.setInt(2, match.getAwayScore());
		    stmt.setInt(3, match.getStatut());
		    stmt.setLong(4, match.getIdWebService());
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	    
		return true;
	}

	public List<Match> getUnfinishedMatch() {
		return getMatchs(new Date(), MatchStatus.NOT_ENDED);
	}

	public Match getBarbyDevMatch() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(String.valueOf(MatchStatus.ENDED));
		statusList.add(String.valueOf(MatchStatus.NOT_STARTED));
		statusList.add(String.valueOf(MatchStatus.FIRST_HALF));	
		statusList.add(String.valueOf(MatchStatus.HALFTIME)); 	
		statusList.add(String.valueOf(MatchStatus.SECOND_HALF)); 
		statusList.add(String.valueOf(MatchStatus.OVERTIME)); 	
		statusList.add(String.valueOf(MatchStatus.PENALTY)); 	
		
		Match match = null;

		try {
			String sqlQuery = "SELECT t1.name as homeTeam, m.scoreH as homeTeamScore, t2.name as awayTeam, m.scoreA as awayTeamScore, m.statut as matchStatus "
					+ "FROM Matchs m, Team t1, Team t2 "
					+ "WHERE m.id = 37 AND t1.id = m.teamHid AND t2.id = m.teamAId";
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbybet_dev", _user, _password);
		    stmt = connection.prepareStatement(sqlQuery);
		    
		    rs = stmt.executeQuery();
			while (rs.next()) {
		    	match = new Match();
		    	
		    	Team homeTeam = new Team();
		    	homeTeam.setName(rs.getString(1));
		    	match.setHomeTeam(homeTeam);
		    	
		    	Team awayTeam = new Team();
		    	awayTeam.setName(rs.getString(3));
		    	match.setAwayTeam(awayTeam);
		    	
		    	match.setHomeScore(rs.getInt(2));
		    	match.setAwayScore(rs.getInt(4));
		    	match.setStatut(rs.getInt(5));
		    }		    	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
		    close(rs);
			close(stmt);
			close(connection);
		}
		
	    return match;
	}
	
	public boolean isMatchFromWebServiceInDatabase(Long idWebService) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT COUNT(*) FROM Matchs m WHERE m.idWebService = ?");
		    stmt.setLong(1, idWebService);
		    
		    rs = stmt.executeQuery();
		    
		    return rs.next();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
		
		return false;
	}
}
