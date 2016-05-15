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

import javax.servlet.http.HttpServletRequest;

import com.barbyBet.object.User;
import com.barbyBet.tools.CipherUtils;

public class SQLUsersComponent extends SQLComponent {

	private static final int DEFAULT_NUMBER_OF_COINS = 10000;
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "password";
	private static final String CHAMP_REPEAT_PASS = "repeatPassword";
	private static final String CHAMP_USERNAME = "username";
	private static final String CHAMP_INSCRIPTION = "inscription";
	private static final String CHAMP_CONNEXION = "connexion";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

	public SQLUsersComponent() {
		super();
	}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	// We check if the user is registered so we can connect them to the website
	public User isUserRegistered(HttpServletRequest request) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String email = getValueChamp(request, CHAMP_EMAIL);
		String password = getValueChamp(request, CHAMP_PASS);

		try {
			checkUsername(email);
		} catch (Exception e) {
			setError(CHAMP_EMAIL, e.getMessage());
			
			return null;
		}

		try {
			checkPassword(password);
		} catch (Exception e) {
			setError(CHAMP_PASS, e.getMessage());
			
			return null;
		}

		if (errors.isEmpty()) {
			password = encryptPassword(password);
			
			try {
			    connexion = DriverManager.getConnection(_url, _user, _password);
				stmt = connexion
						.prepareStatement("SELECT id, username, email, dateRegistration, coins FROM Users WHERE (username = ? AND password = ?) OR (email = ? AND password = ?)");
				stmt.setString(1, email);
				stmt.setString(2, password);
				stmt.setString(3, email);
				stmt.setString(4, password);

				rs = stmt.executeQuery();
				if (rs.next()) {
					return new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getDate("dateRegistration"), rs.getInt("coins"));
				} else {
					setError(CHAMP_EMAIL,
							"Vos identifiants sont incorrects.");

					return null;
				}
			} catch (SQLException e) {
				setError(CHAMP_CONNEXION, "Erreur lors de la connexion. Merci de réessayer");

				return null;
			} finally {
				close(rs);
				close(stmt);
				close(connexion);
			}
		} else {
			return null;
		}
	}

	public boolean isUsernameTaken(String username) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT COUNT(username) FROM Users WHERE username = ?");
			stmt.setString(1, username);

			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("COUNT(username)").equals("0")) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			setError(CHAMP_INSCRIPTION, "Il y a eu un problème lors de l'inscription. Merci de réessayer.");

			return false;
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public User getUser(Long id) {
		User user = null;
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT id, username, email, dateRegistration, coins, rank FROM Users WHERE id = ?");
			stmt.setLong(1, id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("username"),
						rs.getString("email"), rs.getDate("dateRegistration"),
						rs.getInt("coins"), rs.getInt("rank"));
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

			return null;
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
			stmt = connexion
					.prepareStatement("SELECT id, username, email, dateRegistration, coins FROM Users WHERE username = ?");
			stmt.setString(1, username);

			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("username"),
						rs.getString("email"), rs.getDate("dateRegistration"),
						rs.getInt("coins"));
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

			return null;
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
	}
	
	public Long getUserId(String username) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT id FROM Users WHERE username = ?");
			stmt.setString(1, username);

			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getLong("id");
			} else {
				return -1L;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

			return -1L;
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
	}

	// Insert user in the database
	public User insertUser(HttpServletRequest request) {
		Connection connexion = null;
		PreparedStatement stmt = null;

		String email = getValueChamp(request, CHAMP_EMAIL);
		String password = getValueChamp(request, CHAMP_PASS);
		String repeatPassword = getValueChamp(request, CHAMP_REPEAT_PASS);
		String username = getValueChamp(request, CHAMP_USERNAME);

		try {
			validationEmail(email);
		} catch (Exception e) {
			setError(CHAMP_EMAIL, e.getMessage());
			
			return null;
		}

		try {
			validationMotsDePasse(password, repeatPassword);
		} catch (Exception e) {
			setError(CHAMP_PASS, e.getMessage());
			setError(CHAMP_REPEAT_PASS, null);
			
			return null;
		}

		if (isEmailTaken(email)) {
			setError(CHAMP_USERNAME, "Un utilisateur avec cet email est déjà inscrit sur notre site");
			
			return null;
		}
		
		if (isUsernameTaken(username)) {
			setError(CHAMP_USERNAME, "Un utilisateur avec ce nom d'utilisateur est déjà inscrit sur notre site");
			
			return null;
		}

		if (!errors.isEmpty()) {
			return null;
		} else {
			password = encryptPassword(password);
			Date dateToday = new Date();
			Timestamp date = new Timestamp(dateToday.getTime());
			try {
			    connexion = DriverManager.getConnection(_url, _user, _password);
			    connexion.setAutoCommit(false);
			    
			    stmt = connexion.prepareStatement("SELECT count(*) as numberOfUsers FROM Users");
			    ResultSet rs = stmt.executeQuery();
			    Long numberOfUsers = 0L;
			    
			    while(rs.next()) {
			    	numberOfUsers = rs.getLong("numberOfUsers");
			    }
			    
			    close(stmt);
			    
				stmt = connexion
						.prepareStatement("INSERT INTO Users (username, email, password, dateRegistration, coins, rank, rankBeforeLastGame) VALUES (?, ?, ?, ?, ?, ?, ?)");

				stmt.setString(1, username);
				stmt.setString(2, email);
				stmt.setString(3, password);
				stmt.setTimestamp(4, date);
				stmt.setInt(5, DEFAULT_NUMBER_OF_COINS);
				stmt.setLong(6, numberOfUsers+1);
				stmt.setLong(7, numberOfUsers+1);

				stmt.executeUpdate();
				
				connexion.commit();
			} catch (SQLException e) {
				setError(CHAMP_INSCRIPTION, "Il y a eu un problème lors de l'inscription. Merci de réessayer.");

				return null;
			} finally {
				close(stmt);
				close(connexion);
			}

			return new User(getUserId(username),username,email,date,DEFAULT_NUMBER_OF_COINS);
		}
	}
	
	private boolean isEmailTaken(String username) {
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT COUNT(email) FROM Users WHERE email = ?");
			stmt.setString(1, username);

			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("COUNT(email)").equals("0")) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			setError(CHAMP_INSCRIPTION, "Il y a eu un problème lors de l'inscription. Merci de réessayer.");

			return false;
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
	}

	public ArrayList<String> getUserNames(String term, String username) {
		ArrayList<String> usernames = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT username FROM Users WHERE username like ? AND username <> ?");
			stmt.setString(1, "%" + term + "%");
			stmt.setString(2, username);

			rs = stmt.executeQuery();
						
			while(rs.next()) {
				usernames.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
		
		return usernames;
	}
	
	/**
	 * Get the list of usernames containing the "term" in the group of ID : groupId (while not displaying username) 
	 * @param term
	 * @param username
	 * @return
	 */
	public ArrayList<String> getUserNamesFromGroup(String term, String username, Long groupId) {
		ArrayList<String> usernames = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    connexion = DriverManager.getConnection(_url, _user, _password);
			stmt = connexion
					.prepareStatement("SELECT u.username FROM Users u, LinkUserGroup lug WHERE u.username like ? AND u.username <> ? AND u.id = lug.userId AND lug.groupId = ?");
			stmt.setString(1, "%" + term + "%");
			stmt.setString(2, username);
			stmt.setLong(3, groupId);

			rs = stmt.executeQuery();
						
			while(rs.next()) {
				usernames.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(stmt);
			close(connexion);
		}
		
		return usernames;
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	public static String getValueChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	private void validationMotsDePasse(String password, String repeatPassword)
			throws Exception {
		if (password != null && repeatPassword != null) {
			if (!password.equals(repeatPassword)) {
				throw new Exception(
						"Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			}
		} else {
			throw new Exception(
					"Merci de saisir et confirmer votre mot de passe.");
		}
	}

	private void checkUsername(String username) throws Exception {
		if (username == null) {
			throw new Exception("Veuillez entrer un nom d'utilisateur.");
		}
	}

	private void checkPassword(String password) throws Exception {
		if (password == null) {
			throw new Exception("Veuillez entrer un mot de passe.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String champ, String message) {
		errors.put(champ, message);
	}

	private String encryptPassword(String password) {
		return CipherUtils.encrypt(CipherUtils.KEY1, CipherUtils.KEY2, password);
	}
	
	@SuppressWarnings("unused")
	private String decryptPassword(String password) {
		return CipherUtils.decrypt(CipherUtils.KEY1, CipherUtils.KEY2, password);
	}
}
