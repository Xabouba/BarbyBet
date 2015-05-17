package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SQLPronoComponent extends SQLComponent
{
	public SQLPronoComponent()
	{
		super();
	}
	
	public HashMap<String, String> getProno(String matchId, String idUser)
	{
		HashMap<String, String> prono = new HashMap<String, String>();

		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT prono, credits, creditsWon FROM Pronostics WHERE idUser = ? AND idMatch = ?");
		    
		    stmt.setInt(1, Integer.valueOf(idUser));
		    stmt.setInt(2, Integer.valueOf(matchId));
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	prono.put("prono", String.valueOf(rs.getInt(1)));
		    	prono.put("credits", String.valueOf(rs.getInt(2)));
		    	prono.put("creditsWon", String.valueOf(rs.getInt(3)));
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
	
	public void insertProno(String matchId, String idUser, String prono, String credits)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Pronostics (idUser, idMatch, prono, credits) VALUES (?, ?, ?, ?)");
		    
		    stmt.setInt(1, Integer.valueOf(idUser));
		    stmt.setInt(2, Integer.valueOf(matchId));
		    stmt.setInt(3, Integer.valueOf(prono));
		    stmt.setString(4, credits);
		    
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

	public void updateProno(String matchId, String idUser, String prono, String credits) 
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("UPDATE Pronostics SET prono = ?, credits = ? WHERE idMatch = ? And idUser = ?");
		    
		    stmt.setInt(1, Integer.valueOf(prono));
		    stmt.setInt(2, Integer.valueOf(credits));
		    stmt.setInt(3, Integer.valueOf(matchId));
		    stmt.setInt(4, Integer.valueOf(idUser));
		    
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
	
	public void pronostic(String matchId, String idUser, String prono, String credits)
	{
		if (getProno(matchId, idUser).isEmpty())
		{
			insertProno(matchId, idUser, prono, credits);
		}
		else
		{
			updateProno(matchId, idUser, prono, credits);
		}
	}

}
