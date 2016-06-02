package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.barbyBet.object.Group;
import com.barbyBet.object.User;

public class SQLRankComponent extends SQLComponent
{
	public SQLRankComponent()
	{
		super();
	}
	
	public Map<String, Map<String, String>> getGroupRank(Long groupId)
	{
		Map<String, Map<String, String>> rank = new LinkedHashMap<String, Map<String,String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.username, lu.userRankBeforeLastGame - lu.userRank, lu.points, lu.userRank, u.id FROM Users u, LinkUserGroup lu WHERE lu.groupId = ? AND lu.userId = u.id ORDER BY lu.userRank");
		    stmt.setLong(1, groupId);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	attribute.put("id", rs.getString(5));
		    	attribute.put("rank", rs.getString(4));
		    	
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
	
	public Map<String, Map<String, String>> getGroupRank(Long groupId, int page, int nbUser)
	{
		Map<String, Map<String, String>> rank = new LinkedHashMap<String, Map<String,String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.username, lu.userRankBeforeLastGame - lu.userRank, lu.points, lu.userRank, u.id FROM Users u, LinkUserGroup lu WHERE lu.groupId = ? AND lu.userId = u.id ORDER BY lu.userRank LIMIT ?, ?");
		    stmt.setLong(1, groupId);
		    stmt.setInt(2, (page-1)*nbUser);
		    stmt.setInt(3, nbUser);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	attribute.put("id", rs.getString(5));
		    	attribute.put("rank", rs.getString(4));
		    	
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
	
	public int getGroupSize(Long groupId)
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT count(*) FROM Users u, LinkUserGroup lu WHERE lu.groupId = ? AND lu.userId = u.id");
		    stmt.setLong(1, groupId);
		    
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
		    stmt = connexion.prepareStatement("SELECT u.username, u.rankBeforeLastGame - u.rank, u.coins, u.rank, u.id FROM Users u ORDER BY u.rank");
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	attribute.put("id", rs.getString(5));
		    	attribute.put("rank", rs.getString(4));
		    	
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
		    stmt = connexion.prepareStatement("SELECT u.username, u.rankBeforeLastGame - u.rank, u.coins, u.rank, u.id FROM Users u ORDER BY u.rank LIMIT ?, ?");
		    stmt.setInt(1, (page-1)*nbUser);
		    stmt.setInt(2, nbUser);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Map<String, String> attribute = new HashMap<String, String>();
		    	attribute.put("point", rs.getString(3));
		    	attribute.put("diff", rs.getString(2));
		    	attribute.put("id", rs.getString(5));
		    	attribute.put("rank", rs.getString(4));
		    	
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
	
	public void updateRankAfterModification() {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    connexion.setAutoCommit(false);
		    
		    // Select users ranks to loop through them and update rank before last game
		    stmt = connexion.prepareStatement("SELECT id, rank FROM Users");
		    rs = stmt.executeQuery();
		    System.out.println("1");
		    
		    // Update rank before last game
		    while(rs.next()) {
		    	PreparedStatement stmt2 = null;
				try 
		    	{
					stmt2 = connexion.prepareStatement("UPDATE Users SET rankBeforeLastGame = ? WHERE id = ?");
					stmt2.setInt(1, rs.getInt("rank"));
		    		stmt2.setLong(2, rs.getLong("id"));
		    		stmt2.executeUpdate();
		    	}
		    	finally
		    	{
		    		close(stmt2);
		    	}
		    }
		    
		    close(stmt);
		    System.out.println("2");
		    
		    // Select users sorted by points to loop through them and update their rank
		    stmt = connexion.prepareStatement("SELECT id FROM Users ORDER BY coins DESC");
		    rs = stmt.executeQuery();
		    System.out.println("3");
		    
		    // Update rank
		    int rank = 1;
		    while(rs.next()) {
		    	PreparedStatement stmt2 = null;
				try 
		    	{
					stmt2 = connexion.prepareStatement("UPDATE Users SET rank = ? WHERE id = ?");
					stmt2.setInt(1, rank);
					stmt2.setLong(2, rs.getLong("id"));
					stmt2.executeUpdate();
				    rank++;
		    	}
				finally
				{
					close(stmt2);
				}
		    }
		    System.out.println("4");
		    connexion.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(!stmt.isClosed()) {
					close(stmt);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			close(connexion);
		}
	}
}
