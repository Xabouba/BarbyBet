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
	public SQLGroupComponent() {
		super();
	}

    public boolean hasCreatedGroupToday(User u) {
    	Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT * FROM LinkUserGroup g Where user_id=? , creation_date < ?");
		    
		    stmt.setLong(1, u.getId());
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
    
    public String insertGroup(Group group, User user) {
    	Connection connexion = null;
		PreparedStatement stmt = null;
		
	    if(!doesGroupAlreadyExist(group.getName())) {
	    	try {
			    connexion = DriverManager.getConnection(_url, _user, _password);
			    connexion.setAutoCommit(false);
			    
			    stmt = connexion.prepareStatement("INSERT INTO Groups (name, description, status, img, groupCreator, creationDate) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			    stmt.setString(1, group.getName());
			    stmt.setString(2, group.getDescription());
			    stmt.setInt(3, group.getStatus());
			    stmt.setString(4, group.getImg());
			    stmt.setLong(5, group.getGroupCreator());
			    stmt.setTimestamp(6, new Timestamp(group.getCreationDate().getTime()));
			    
			    int affectedRows = stmt.executeUpdate();

		        if (affectedRows == 0) {
		            throw new SQLException("Creating group failed, no rows affected.");
		        }

		        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                group.setId(generatedKeys.getLong(1));
		            }
		            else {
		                throw new SQLException("Creating group failed, no ID obtained.");
		            }
		        }
				
			    close(stmt);
				
			    stmt = connexion.prepareStatement("INSERT INTO LinkUserGroup (groupId, userId, isAdmin, userRank, userRankBeforeLastGame, dateUserAdded) VALUES (?, ?, ?, ?, ?, ?)"); 
			    stmt.setLong(1, group.getId());
			    stmt.setLong(2, user.getId());
			    stmt.setInt(3, Constants.ADMIN);
			    stmt.setInt(4, 1);
			    stmt.setInt(5, 1);
			    stmt.setTimestamp(6, new Timestamp(group.getCreationDate().getTime()));
			    
			    stmt.executeUpdate();
			    
			    connexion.commit();
			} catch (SQLException e) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				return e.getMessage();
			} finally {
				close(stmt);
				close(connexion);
			}
	    } else {
	    	return "Ce nom de groupe existe déjà !";
	    }
		
		return null;
    }
    
	private boolean doesGroupAlreadyExist(String name) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT id FROM Groups WHERE name = ?");
		    stmt.setString(1, name);
		    
		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	return true;
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
		
		return false;
	}

	public List<Group> getGroupsBy(String ordering) {
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
	
	public List<User> getMembers(Group g) {
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
		    	User u = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
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
	
	public Group getGroup(Long groupId) {
		Group group = new Group();
		List<User> members = new ArrayList<User>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT g.id, g.name, g.description, g.status, g.img, g.groupCreator, g.creationDate, u.id, u.username, u.email, u.dateRegistration, u.coins, lug.isAdmin, lug.userRank, lug.userRankBeforeLastGame, lug.dateUserAdded  FROM Groups g, Users u, LinkUserGroup lug WHERE g.id = ? AND lug.groupId = ? AND lug.userId = u.id");
		    
		    stmt.setLong(1, groupId);
		    stmt.setLong(2, groupId);

		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	User member = new User(rs.getLong("u.id"), rs.getString("u.username"), rs.getString("u.email"), rs.getDate("u.dateRegistration"), rs.getInt("u.coins"));
		    	members.add(member);
		    	group.setId(rs.getLong("g.id"));
		    	group.setName(rs.getString("g.name"));
		    	group.setDescription(rs.getString("g.description"));
		    	group.setStatus(rs.getInt("g.status"));
		    	group.setImg(rs.getString("g.img"));
		    	group.setGroupCreator(rs.getLong("g.groupCreator"));
		    	group.setCreationDate(rs.getTimestamp("g.creationDate"));
		    }
		    
		    group.setMembers(members);
		    
		    return group;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
}
