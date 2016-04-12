package com.barbyBet.object;

import java.util.Date;
import java.util.HashMap;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.tools.DateUtil;
import com.barbyBet.tools.ServletUtil;


public class User {
	private String _username;
	private String _email;
	private Date _registrationDate;
	private int _coins;
	private Long _id;
	
	public User() {
		
	}
	
	public User(Long id, String username, String email, Date date, int coins) {
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
	
	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		this._id = id;
	}
	
	@Override
	public String toString() {
		return "Id = " + this._id + " / username = " + this._username + " / email = " + this._username + " / registration date = " + this._registrationDate + " / coins = " + this._coins;
	}
	
	public HashMap<String, String> toHashMap() {
		// User Information
	    HashMap<String, String> groupInfo = new HashMap<String, String>();
	    groupInfo.put("id", String.valueOf(this.getId()));
	    groupInfo.put("username", this.getUsername());
	    groupInfo.put("email", this.getEmail());
	    groupInfo.put("registrationDate", DateUtil.SHORT_DATE_FORMAT_FRANCE.format(this.getRegistrationDate()));
	    groupInfo.put("coins", String.valueOf(this.getCoins()));
	    
	    return groupInfo;
	}
}
