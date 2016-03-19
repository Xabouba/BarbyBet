package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SQLPronoComponent extends SQLComponent
{
	public SQLPronoComponent()
	{
		super();
	}
	
	public HashMap<String, String> getProno(int matchId, int idUser)
	{
		HashMap<String, String> prono = new HashMap<String, String>();

		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT scoreHome, scoreAway, prono, credits, creditsWon FROM Pronostics WHERE idUser = ? AND idMatch = ?");
		    
		    stmt.setInt(1, idUser);
		    stmt.setInt(2, matchId);
		    
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
	
	public boolean insertProno(int matchId, int idUser, int scoreHome, int scoreAway, int prono, int credits)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Pronostics (idUser, idMatch, scoreHome, scoreAway, prono, credits) VALUES (?, ?, ?, ?, ?, ?)");
		    
		    stmt.setInt(1, idUser);
		    stmt.setInt(2, matchId);
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

	public void updateProno(int matchId, int idUser, int scoreHome, int scoreAway, int prono, int credits) 
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
		    stmt.setInt(5, matchId);
		    stmt.setInt(6, idUser);
		    
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
	
	public void pronostic(int matchId, int idUser, int scoreHome, int scoreAway, int prono, int credits)
	{
		if (getProno(matchId, idUser).isEmpty())
		{
			insertProno(matchId, idUser, scoreHome, scoreAway, prono, credits);
		}
		else
		{
			updateProno(matchId, idUser, scoreHome, scoreAway, prono, credits);
		}
	}

}
