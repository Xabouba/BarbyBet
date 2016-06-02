package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

public class SQLGroupComponent extends SQLComponent
{
	public String DELETE_SUCCESSFUL = "Suppression réussie";
	public String ADD_SUCCESSFUL = "Ajout réussi";
	
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
		    stmt = connexion.prepareStatement("SELECT g.id, g.name, g.description, g.status, g.img, g.groupCreator, g.creationDate, u.id, u.username, u.email, u.dateRegistration, u.coins, lug.isAdmin, lug.userRank, lug.userRankBeforeLastGame, lug.dateUserAdded FROM Groups g, Users u, LinkUserGroup lug WHERE g.id = ? AND lug.groupId = ? AND lug.userId = u.id ORDER BY lug.dateUserAdded DESC");
		    
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
		    
		    if(group.getId() == null) {
		    	return null;
		    }
		    
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

	public List<Group> getUserGroups(Long id) {
		List<Group> groups = new ArrayList<Group>();		
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT g.id FROM Groups g, LinkUserGroup lug WHERE g.id = lug.groupId AND lug.userId = ? ORDER BY g.name ASC");
		    
		    stmt.setLong(1, id);

		    rs = stmt.executeQuery();
		    
		    while (rs.next()) {
		    	Long groupId = rs.getLong("g.id");
		    	
		    	PreparedStatement innertStmt = null;
		    	ResultSet innerResultSet = null;
		    	try {
		    	
		    		innertStmt = connexion.prepareStatement("SELECT g.id, g.name, g.description, g.status, g.img, g.groupCreator, g.creationDate, u.id, u.username, u.email, u.dateRegistration, u.coins, lug.isAdmin, lug.userRank, lug.userRankBeforeLastGame, lug.dateUserAdded FROM Groups g, Users u, LinkUserGroup lug WHERE g.id = ? AND lug.groupId = ? AND lug.userId = u.id ORDER BY lug.dateUserAdded DESC");
			    	innertStmt.setLong(1, groupId);
			    	innertStmt.setLong(2, groupId);
	
			    	innerResultSet = innertStmt.executeQuery();
			    	Group group = new Group();
					List<User> members = new ArrayList<User>();
					
				    while (innerResultSet.next()) {
				    	User member = new User(innerResultSet.getLong("u.id"), innerResultSet.getString("u.username"), innerResultSet.getString("u.email"), innerResultSet.getDate("u.dateRegistration"), innerResultSet.getInt("u.coins"));
				    	members.add(member);
				    	group.setId(innerResultSet.getLong("g.id"));
				    	group.setName(innerResultSet.getString("g.name"));
				    	group.setDescription(innerResultSet.getString("g.description"));
				    	group.setStatus(innerResultSet.getInt("g.status"));
				    	group.setImg(innerResultSet.getString("g.img"));
				    	group.setGroupCreator(innerResultSet.getLong("g.groupCreator"));
				    	group.setCreationDate(innerResultSet.getTimestamp("g.creationDate"));
				    }
				    
				    group.setMembers(members);
			    
			    	groups.add(group);
		    	} catch (SQLException e) {
					System.out.println(e.getMessage());
					return null;
				} finally {
				    close(innerResultSet);
				    close(innertStmt);
				}
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
		    close(rs);
		    close(stmt);
			close(connexion);
		}
		
		return groups;
	}

	public String addUserToGroup(Group g, User u) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		
	    if(!userInGroup(g.getId(), u.getId())) {
	    	try {
	    		connexion = DriverManager.getConnection(_url, _user, _password);
			    connexion.setAutoCommit(false);
			    
			    Date now = new Date();
			    
			    stmt = connexion.prepareStatement("INSERT INTO LinkUserGroup (groupId, userId, isAdmin, userRank, userRankBeforeLastGame, dateUserAdded) VALUES (?, ?, ?, ?, ?, ?)"); 
			    stmt.setLong(1, g.getId());
			    stmt.setLong(2, u.getId());
			    stmt.setInt(3, Constants.NOT_ADMIN);
			    stmt.setInt(4, g.getMembers().size()+1);
			    stmt.setInt(5, g.getMembers().size()+1);
			    stmt.setTimestamp(6, new Timestamp(now.getTime()));
			    
			    stmt.executeUpdate();
			    
			    connexion.commit();
			} catch (SQLException e) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				return "Il y a eu une erreur lors de l'ajout. Veuillez réessayer";
			} finally {
				close(stmt);
				close(connexion);
			}
	    } else {
	    	return "Cet utilisateur est déjà dans le groupe : " + g.getName() + " !";
	    }
		
		return ADD_SUCCESSFUL;
	}
	
	public String deleteUserFromGroup(Group g, User userToDelete, User currentUser) {
		if(userToDelete.getId() == currentUser.getId()) {
			return "Vous ne pouvez pas vous supprimer d'un groupe. Veuillez cliquer sur \"Quitter ce groupe\"";
		}
		
		if(isGroupAdmin(g.getId(), userToDelete.getId())) {
			return "Vous ne pouvez pas supprimer le créateur du groupe";
		}
		
		return leaveGroup(g, userToDelete);
	}
	
	public String leaveGroup(Group g, User userToDelete) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		
	    if(userInGroup(g.getId(), userToDelete.getId())) {
	    	try {
	    		connexion = DriverManager.getConnection(_url, _user, _password);
			    connexion.setAutoCommit(false);
			    
			    stmt = connexion.prepareStatement("DELETE FROM LinkUserGroup WHERE groupId = ? AND userId = ?"); 
			    stmt.setLong(1, g.getId());
			    stmt.setLong(2, userToDelete.getId());
			    
			    stmt.executeUpdate();
			    
			    connexion.commit();
			} catch (SQLException e) {
				try {
					connexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					
					return "Il y a eu un problème lors de la suppression. Veuillez réessayer";
				}
				
				return e.getMessage();
			} finally {
				close(stmt);
				close(connexion);
			}
	    } else {
	    	return "Cet utilisateur n'est pas dans ce groupe !";
	    }
		
		return DELETE_SUCCESSFUL;
	}

	private boolean isGroupAdmin(Long groupId, Long userId) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT groupCreator FROM Groups WHERE groupId = ? AND groupCreator = ?");
		    stmt.setLong(1, groupId);
		    stmt.setLong(2, userId);
		    
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

	private boolean userInGroup(Long groupId, Long userId) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT userId FROM LinkUserGroup WHERE groupId = ? AND userId = ?");
		    stmt.setLong(1, groupId);
		    stmt.setLong(2, userId);
		    
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

	public String deleteGroup(Group g) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		
    	try {
    		connexion = DriverManager.getConnection(_url, _user, _password);
		    connexion.setAutoCommit(false);
		    
		    stmt = connexion.prepareStatement("DELETE FROM LinkUserGroup WHERE groupId = ?"); 
		    stmt.setLong(1, g.getId());
		    
		    stmt.executeUpdate();
		    close(stmt);
		    
		    stmt = connexion.prepareStatement("DELETE FROM Groups WHERE id = ?");
		    stmt.setLong(1, g.getId());
		    
		    stmt.executeUpdate();
		    close(stmt);
		    
		    connexion.commit();
		} catch (SQLException e) {
			try {
				connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
				return "Il y a eu un problème lors de la suppression. Veuillez réessayer";
			}
			
			return e.getMessage();
		} finally {
			close(stmt);
			close(connexion);
		}
		
		return DELETE_SUCCESSFUL;
	}
	
	public Map<Long, Map<String, String>> getGroups(Long userId) {
		Map<Long, Map<String, String> > groups = new HashMap<Long, Map<String, String>>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT g.id, g.name, lug.userRank, lug.points FROM Groups g, Users u, LinkUserGroup lug WHERE u.id = ? AND lug.groupId = g.id AND lug.userId = u.id");
		    
		    stmt.setLong(1, userId);

		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	Map<String, String> attribut = new HashMap<String, String>();
		    	attribut.put("name", rs.getString(2));
		    	attribut.put("rank", rs.getString(3));
		    	attribut.put("point", rs.getString(4));
		    	groups.put(rs.getLong(1), attribut);
		    }
		    
		    return groups;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public ArrayList<String> getAllPublicGroupNames(String term) {
		ArrayList<String> groups = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT name FROM Groups WHERE name LIKE ? AND status = ?");
			stmt.setString(1, "%" + term + "%");
			stmt.setInt(2, 0);


			rs = stmt.executeQuery();
						
			while(rs.next()) {
				groups.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
		
		return groups;
	}

	public Long getGroupId(String groupName) {
		Long groupId = 0L;

		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT id FROM Groups WHERE name = ?");
		    stmt.setString(1, groupName);

		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	groupId = rs.getLong("id");
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
		
	    return groupId;
	}

	public void updateRankAfterModificationInGroup(Group g, User u) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    connexion.setAutoCommit(false);
		    
		    // Select users ranks to loop through them and update rank before last game
		    stmt = connexion.prepareStatement("SELECT userId, userRank FROM LinkUserGroup WHERE groupId = ?");
		    stmt.setLong(1, g.getId());
		    rs = stmt.executeQuery();
		    System.out.println("1");
		    
		    // Update rank before last game
		    while(rs.next()) {
		    	PreparedStatement stmt2 = null;
				try 
		    	{
					stmt2 = connexion.prepareStatement("UPDATE LinkUserGroup SET userRankBeforeLastGame = ? WHERE groupId = ? AND userId = ?");
					stmt2.setInt(1, rs.getInt("userRank"));
					stmt2.setLong(2, g.getId());
		    		stmt2.setLong(3, rs.getLong("userId"));
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
		    stmt = connexion.prepareStatement("SELECT userId FROM LinkUserGroup WHERE groupId = ? ORDER BY points DESC");
		    stmt.setLong(1, g.getId());
		    rs = stmt.executeQuery();
		    System.out.println("3");
		    
		    // Update rank
		    int rank = 1;
		    while(rs.next()) {
		    	PreparedStatement stmt2 = null;
				try 
		    	{
					stmt2 = connexion.prepareStatement("UPDATE LinkUserGroup SET userRank = ? WHERE groupId = ? AND userId = ?");
					stmt2.setInt(1, rank);
					stmt2.setLong(2, g.getId());
					stmt2.setLong(3, rs.getLong("userId"));
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
	
	public boolean updateGroupUserPoint(Long idUser, Long idGroup, int point) 
	{
		Connection connexion = null;
		PreparedStatement stmt = null;
		try 
		{
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("UPDATE LinkUserGroup SET points = ? WHERE groupId = ? AND userId = ?");
		    
		    stmt.setInt(1, point);
		    stmt.setLong(2, idGroup);
		    stmt.setLong(3, idUser);
		    
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
}
