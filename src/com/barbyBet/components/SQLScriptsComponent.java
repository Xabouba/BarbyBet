package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class SQLScriptsComponent extends SQLComponent{
	public SQLScriptsComponent() {
		super();
	}
	
	public void insertDummyLine() {
		System.out.println("Inserting dummy line");
		Connection connection = null;
		PreparedStatement stmt = null;
		Date now = new Date();
		
		try {
			connection = DriverManager.getConnection(_url, _user, _password);
			stmt = connection.prepareStatement("INSERT INTO Scripts (date) VALUES (?)");
	    
			stmt.setTimestamp(1, new Timestamp(now.getTime()));
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(stmt);
			close(connection);
		}
	}
}
