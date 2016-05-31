package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barbyBet.object.Match;
import com.barbyBet.object.Odds;
import com.barbyBet.object.Team;

public class SQLPronoComponent extends SQLComponent
{
	public SQLPronoComponent()
	{
		super();
	}
	
	public HashMap<String, String> getProno(Long matchId, Long idUser)
	{
		HashMap<String, String> prono = new HashMap<String, String>();

		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT scoreHome, scoreAway, prono, credits, creditsWon FROM Pronostics WHERE idUser = ? AND idMatch = ?");
		    
		    stmt.setLong(1, idUser);
		    stmt.setLong(2, matchId);
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	prono.put("scoreHome", String.valueOf(rs.getInt(1)));
		    	prono.put("scoreAway", String.valueOf(rs.getInt(2)));
		    	prono.put("prono", String.valueOf(rs.getInt(3)));
		    	prono.put("credits", String.valueOf(rs.getInt(4)));
		    	prono.put("creditsWon", String.valueOf(rs.getInt(5)));
		    }
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
		} 
		finally 
		{
			close(rs);
			close(stmt);
			close(connexion);
		}
		
		return prono;
	}
	
	public List<Map<String, String>> getPronoFromMatch(Long matchIdWebService)
	{
		List<Map<String, String>> prono = new ArrayList<Map<String,String>>();

		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT p.scoreHome, p.scoreAway, p.id FROM Pronostics p, Matchs m WHERE p.idMatch = m.id AND m.idWebService = ? ");
		    
		    stmt.setLong(1, matchIdWebService);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> mapProno = new HashMap<String, String>();
		    	mapProno.put("scoreHome", String.valueOf(rs.getInt(1)));
		    	mapProno.put("scoreAway", String.valueOf(rs.getInt(2)));
		    	mapProno.put("id", String.valueOf(rs.getLong(3)));
		    	
		    	prono.add(mapProno);
		    }
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
		} 
		finally 
		{
			close(rs);
			close(stmt);
			close(connexion);
		}
		
		return prono;
	}

	
	public boolean insertProno(Long matchId, Long idUser, int scoreHome, int scoreAway, int prono, int credits)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Pronostics (idUser, idMatch, scoreHome, scoreAway, prono, credits) VALUES (?, ?, ?, ?, ?, ?)");
		    
		    stmt.setLong(1, idUser);
		    stmt.setLong(2, matchId);
		    stmt.setInt(3, scoreHome);
		    stmt.setInt(4, scoreAway);
		    stmt.setInt(5, prono);
		    stmt.setInt(6, credits);
		    
		    stmt.executeUpdate();
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return false;
		} 
		finally 
		{
			close(stmt);
			close(connexion);
		}
		return true;
	}

	public boolean updateProno(Long matchId, Long idUser, int scoreHome, int scoreAway, int prono, int credits) 
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("UPDATE Pronostics SET scoreHome = ?, scoreAway = ?, prono = ?, credits = ? WHERE idMatch = ? And idUser = ?");
		    
		    stmt.setInt(1, scoreHome);
		    stmt.setInt(2, scoreAway);
		    stmt.setInt(3, prono);
		    stmt.setInt(4, credits);
		    stmt.setLong(5, matchId);
		    stmt.setLong(6, idUser);
		    
		    stmt.executeUpdate();
		    return true;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return false;
		} 
		finally 
		{
			close(stmt);
			close(connexion);
		}
	}
	
	public boolean updatePronoFromId(Long pronoId, int statut, int creditWon) 
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("UPDATE Pronostics SET statut = ?, creditsWon = ? WHERE id = ?");
		    
		    stmt.setInt(1, statut);
		    stmt.setInt(2, creditWon);
		    stmt.setLong(3, pronoId);
		    
		    stmt.executeUpdate();
		    return true;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return false;
		} 
		finally 
		{
			close(stmt);
			close(connexion);
		}
	}
	
	public boolean pronostic(Long matchId, Long idUser, int scoreHome, int scoreAway, int prono, int credits)
	{
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
		if (!sqlMatchComponent.hasMatchBegin(matchId))
		{
			if (getProno(matchId, idUser).isEmpty())
			{
				return insertProno(matchId, idUser, scoreHome, scoreAway, prono, credits);
			}
			else
			{
				return updateProno(matchId, idUser, scoreHome, scoreAway, prono, credits);
			}
		}
		
		return false;
	}
	
	public ArrayList<HashMap<String, String>> getNextMatchPronostic(long idUser)
	{
		ArrayList<HashMap<String, String>> listMatch = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name, p.scoreHome, p.scoreAway, p.prono FROM Matchs m, Team t1, Team t2, Sport s, Competition c, Pronostics p WHERE p.idMatch = m.id AND p.idUser = ? AND m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.statut = 0 ORDER BY m.beginDate LIMIT 0, 5");
		    
		    stmt.setLong(1, idUser);
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

		    	HashMap<String, String> matchMap = match.toHashMap();
		    	matchMap.put("scoreHome", String.valueOf(rs.getInt(15)));
		    	matchMap.put("scoreAway", String.valueOf(rs.getInt(16)));
		    	matchMap.put("prono", String.valueOf(rs.getInt(17)));
		    	
		    	listMatch.add(matchMap);
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
	
	public ArrayList<HashMap<String, String>> getPastMatchPronostic(long idUser)
	{
		ArrayList<HashMap<String, String>> listMatch = new ArrayList<HashMap<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT t1.name, t1.img, t2.name, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, m.oddsHome, m.oddsDraw, m.oddsAway, c.name, s.name, p.scoreHome, p.scoreAway, p.creditsWon FROM Matchs m, Team t1, Team t2, Sport s, Competition c, Pronostics p WHERE p.idMatch = m.id AND p.idUser = ? AND m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.statut = 3 ORDER BY m.beginDate LIMIT 0, 5");
		    
		    stmt.setLong(1, idUser);
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

		    	HashMap<String, String> matchMap = match.toHashMap();
		    	matchMap.put("scoreHome", String.valueOf(rs.getInt(15)));
		    	matchMap.put("scoreAway", String.valueOf(rs.getInt(16)));
		    	matchMap.put("creditsWon", String.valueOf(rs.getInt(17)));
			    
		    	listMatch.add(matchMap);
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
	
	public HashMap<String, String> getMatchStatPronostic(long idMatch)
	{
		HashMap<String, String> userStat = new HashMap<>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT p.scoreHome, p.scoreAway FROM Pronostics p WHERE p.idMatch = ?");
		    
		    stmt.setLong(1, idMatch);

		    int nbProno = 0;
		    int nbExact = 0;
		    int nbWin = 0;
		    int nbLose = 0;
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	int scoreHome = rs.getInt(1);
		    	int scoreAway = rs.getInt(2);
		    	
		    	if (scoreHome < scoreAway)
		    	{
		    		nbLose++;
		    	}
		    	else if (scoreHome > scoreAway)
		    	{
		    		nbWin++;
		    	}
		    	else 
		    	{
		    		nbExact++;
		    	}
		    	nbProno++;
		    }		    	
		    
		    userStat.put("nbProno", String.valueOf(nbProno));
		    userStat.put("nbWin", String.valueOf(nbWin));
		    userStat.put("nbExact", String.valueOf(nbExact));
		    userStat.put("nbLose", String.valueOf(nbLose));
		    
		    return userStat;
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
	
	public HashMap<String, String> getUserStatPronostic(long idUser)
	{
		HashMap<String, String> userStat = new HashMap<>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT p.statut FROM Matchs m, Pronostics p, Users u WHERE p.idMatch = m.id AND p.idUser = ? AND u.id = p.idUser AND m.statut = 3");
		    
		    stmt.setLong(1, idUser);

		    int nbProno = 0;
		    int nbExact = 0;
		    int nbWin = 0;
		    int nbLose = 0;
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	int statut = rs.getInt(1);
		    	
		    	if (statut == 0)
		    	{
		    		nbLose++;
		    	}
		    	else if (statut == 1)
		    	{
		    		nbWin++;
		    	}
		    	else 
		    	{
		    		nbExact++;
		    	}
		    	nbProno++;
		    }		    	
		    
		    userStat.put("nbProno", String.valueOf(nbProno));
		    userStat.put("nbWin", String.valueOf(nbWin));
		    userStat.put("nbExact", String.valueOf(nbExact));
		    userStat.put("nbLose", String.valueOf(nbLose));
		    
		    return userStat;
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
