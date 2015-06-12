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

import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

public class SQLGroupComponent extends SQLComponent
{
	public SQLGroupComponent()
	{
		super();
	}

    public boolean hasCreatedGroupToday(User u){
    	Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT * FROM LinkUserGroup g Where user_id=? , creation_date < ?");
		    
		    stmt.setInt(1,u.getId());
		    Date dateToday = new Date();
		    Timestamp date = new Timestamp(dateToday.getTime()-86400000);
		    stmt.setTimestamp(2, date);
		    rs = stmt.executeQuery();
		    while (rs.next())
		    {
		    	return true;
		    }
		    
		    return false;
		} 
		catch (SQLException e ) 
		{
			System.out.println(e.getMessage());
			return true;
		} 
		finally 
		{
		    close(rs);
			close(stmt);
			close(connexion);
		}
    }
    
    public String insertGroup(Group group, User user){
    	Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    connexion.setAutoCommit(false);
		    stmt = connexion.prepareStatement("INSERT INTO Groups (name, description, status, creation_date) VALUES (?, ?, ?, ?)");
		    stmt.setString(1, group.getName());
		    stmt.setString(2, group.getDescription());
		    stmt.setString(3, group.getStatus());
		    
		    Date dateToday = new Date();
		    Timestamp date = new Timestamp(dateToday.getTime());
		    stmt.setTimestamp(4, date);
		    
		    stmt.executeUpdate();
			close(stmt);
		    stmt = connexion.prepareStatement("INSERT INTO LinkUserGroup (user_id, group_name, status) VALUES (?, ?, ?)"); 
		    stmt.setInt(1, user.getId());
		    stmt.setString(2, group.getName());
		    stmt.setString(3, Constants.ADMIN);
		    stmt.executeUpdate();
		    
		    connexion.commit();
		} 
		catch (SQLException e ) 
		{
			try {
				connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(e.getErrorCode()==1062){
				return "Ce nom de groupe est déjà utilisé";
			}
			return e.getMessage();
		} 
		finally 
		{
			close(stmt);
			close(connexion);
		}
		return null;
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
