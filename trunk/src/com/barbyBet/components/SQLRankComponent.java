package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLRankComponent extends SQLComponent
{
	public SQLRankComponent()
	{
		super();
	}
	
	public Map<String, Map<String, String>> getGroupRank(int groupId)
	{
		Map<String, Map<String, String>> rank = new LinkedHashMap<String, Map<String,String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.username, lu.userRankBeforeLastGame - lu.userRank, (SELECT count(creditsWon) FROM Pronostics p WHERE p.idUser = u.id) FROM Users u, LinkUserGroup lu WHERE lu.groupId = ? AND lu.userId = u.id ORDER BY lu.userRank");
		    stmt.setInt(1, groupId);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	
		    	rank.put(rs.getString(1), attribute);
		    }		    	
		    
		    return rank;
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
	
	public Map<String, Map<String, String>> getGroupRank(int groupId, int page, int nbUser)
	{
		Map<String, Map<String, String>> rank = new LinkedHashMap<String, Map<String,String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.username, lu.userRankBeforeLastGame - lu.userRank, (SELECT count(creditsWon) FROM Pronostics p WHERE p.idUser = u.id) FROM Users u, LinkUserGroup lu WHERE lu.groupId = ? AND lu.userId = u.id ORDER BY lu.userRank LIMIT ?, ?");
		    stmt.setInt(1, groupId);
		    stmt.setInt(2, (page-1)*nbUser);
		    stmt.setInt(3, nbUser);
		    
		    System.out.println(stmt.toString());
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	
		    	rank.put(rs.getString(1), attribute);
		    }		    	
		    
		    return rank;
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
	
	public int getGroupSize(int groupId)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT count(*) FROM Users u, LinkUserGroup lu WHERE lu.groupId = ? AND lu.userId = u.id");
		    stmt.setInt(1, groupId);
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	return rs.getInt(1);
		    }
		    
		    return 0;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return 0;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public Map<String, Map<String, String>> getGeneralRank()
	{
		Map<String, Map<String, String>> rank = new LinkedHashMap<String, Map<String,String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.username, u.rankBeforeLastGame - u.rank, (SELECT count(creditsWon) FROM Pronostics p WHERE p.idUser = u.id) FROM Users u ORDER BY u.rank");
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	
		    	rank.put(rs.getString(1), attribute);
		    }		    	
		    
		    return rank;
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
	
	public Map<String, Map<String, String>> getGeneralRank(int page, int nbUser)
	{
		Map<String, Map<String, String>> rank = new LinkedHashMap<String, Map<String,String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.username, u.rankBeforeLastGame - u.rank, (SELECT count(creditsWon) FROM Pronostics p WHERE p.idUser = u.id) FROM Users u ORDER BY u.rank LIMIT ?, ?");
		    stmt.setInt(1, (page-1)*nbUser);
		    stmt.setInt(2, nbUser);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	
		    	rank.put(rs.getString(1), attribute);
		    }		    	
		    
		    return rank;
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
	
	public int getGeneralSize()
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT count(*) FROM Users u");
		    
		    rs = stmt.executeQuery();
		    if (rs.next())
		    {
		    	return rs.getInt(1);
		    }		    	
		    
		    return 0;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return 0;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
}
