package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.barbyBet.object.User;

public class SQLUsersComponent extends SQLComponent {
	
	public SQLUsersComponent() {
		super();
	}
	
	// We check if the user is registered so we can connect them to the website
	public boolean isUserRegistered(String username, String password) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT COUNT(username) FROM Users WHERE username = ? AND password = ?");
		    stmt.setString(1, username);
		    stmt.setString(2, password);
		    
		    rs = stmt.executeQuery();
		    if (rs.next()) {
		    	return true;
		    } else {
		    	return false;
		    }
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			
			return false;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public boolean isUsernameTaken(String username) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT COUNT(username) FROM Users WHERE username = ?");
		    stmt.setString(1, username);
		    
		    rs = stmt.executeQuery();
		    if (rs.next()) {
		    	return true;
		    } else {
		    	return false;
		    }
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			
			return false;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public User getUser(String username) {
		User user = null;
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("SELECT username,email,dateRegistration,coins FROM Users WHERE username = ?");
		    stmt.setString(1, username);
		    
		    rs = stmt.executeQuery();
		    if (rs.next()) {
		    	user = new User(rs.getString("username"), rs.getString("email"), rs.getDate("dateRegistration"), rs.getInt("coins"));
		    	return user;
		    } else {
		    	return null;
		    }
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			
			return null;
		} finally {
		    close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	// Insert user in the database
	public boolean insertUser(String email, String username, String password) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
		    stmt = connexion.prepareStatement("INSERT INTO Users (username, email, password, dateRegistration) VALUES (?, ?, ?, ?)");
		    
		    stmt.setString(1, username);
		    stmt.setString(2, email);
		    stmt.setString(3, password);
		    
		    Date dateToday = new Date();
		    Timestamp date = new Timestamp(dateToday.getTime());
		    stmt.setTimestamp(4, date);
		    
		    stmt.executeUpdate();
		} catch (SQLException e ) {
			System.out.println(e.getMessage());
			
			return false;
		} finally {
			close(stmt);
			close(connexion);
		}
		
		return true;
	}
}
