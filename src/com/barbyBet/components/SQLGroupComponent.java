package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barbyBet.object.Group;
import com.barbyBet.object.User;

public class SQLGroupComponent extends SQLComponent
{
	public SQLGroupComponent()
	{
		super();
	}
	
	public List<Group> getGroupsBy(String ordering)
	{
		List<Group> groups = new ArrayList<Group>();
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT g.name, g.img FROM Group g ORDER BY g."+ordering);
		    
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	Group g = new Group();
		    	g.setImg(rs.getString(2));
		    	g.setName(rs.getString(1));
		    	groups.add(g);
		    }
		    
		    return groups;
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
	
	
	
	public List<User> getMembers(Group g)
	{
		List<User> users = new ArrayList<User>();
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT u.id, u.username, u.email, u.dateRegistration, u.coins FROM GroupUserLink gul, User u WHERE gul.groupname = ?");
		    stmt.setString(1,g.getName());

		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
		    	users.add(u);
		    }
		    
		    return users;
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
