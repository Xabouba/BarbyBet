package com.barbyBet.object;

import java.util.Date;


public class User {
	private String _username;
	private String _email;
	private Date _registrationDate;
	private int _coins;
	private int _id;
	
	public User() {
		
	}
	
	public User(int id, String username, String email, Date date, int coins) {
		_id = id;
		_username = username;
		_email = email;
		_registrationDate = date;
		_coins = coins;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		this._username = username;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		this._email = email;
	}

	public Date getRegistrationDate() {
		return _registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this._registrationDate = registrationDate;
	}

	public int getCoins() {
		return _coins;
	}

	public void setCoins(int coins) {
		this._coins = coins;
	}
	
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}
	
	@Override
	public String toString() {
		return "Id = " + this._id + " / username = " + this._username + " / email = " + this._username + " / registration date = " + this._registrationDate + " / coins = " + this._coins;
	}
}
