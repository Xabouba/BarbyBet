package com.barbyBet.object;

import java.util.Date;


public class User {
	private String _username;
	private String _email;
	private Date _registrationDate;
	private int _coins;
	
	public User(String username, String email, Date date, int coins) {
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
}
