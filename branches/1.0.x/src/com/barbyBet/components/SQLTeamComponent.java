package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.barbyBet.object.Team;

public class SQLTeamComponent extends SQLComponent {

	public SQLTeamComponent() {
		super();
	}
	
	public HashMap<Integer, Integer> getTeamsIdMap() {
		HashMap<Integer, Integer> teamsIdMap = new HashMap<Integer, Integer>();
		
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT id, idWebService FROM Team");
		    
		    rs = stmt.executeQuery();
		    while (rs.next()) {
		    	teamsIdMap.put(rs.getInt(2), rs.getInt(1));
		    }
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			return null;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
		
		return teamsIdMap;
	}
	
	public boolean insertTeams(List<Team> teams) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
		    connection = DriverManager.getConnection(_url, _user, _password);
		    for(Team t : teams) {
		    	boolean isTeamInserted;
		    	
		    	isTeamInserted = insertTeam(t, connection, stmt);
			    
			    if(!isTeamInserted) {
			    	System.out.println("The team " + t.getName() + " (web service id : " + t.getIdWebService() + ") has not been inserted !");
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

	public boolean insertTeam(Team team, Connection connection, PreparedStatement stmt) {
		try {
			stmt = connection.prepareStatement("INSERT INTO Team (name, img, idWebService) VALUES (?, ?, ?)");
	    
			stmt.setString(1, team.getName());
	    	stmt.setString(2, team.getImg());
		    stmt.setInt(3, team.getIdWebService());
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
}
