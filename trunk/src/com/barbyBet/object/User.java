package com.barbyBet.object;

import java.util.Date;
import java.util.HashMap;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.tools.DateUtil;
import com.barbyBet.tools.ServletUtil;


public class User {
	private String _firstName;
	private String _lastName;
	private String _username;
	private String _email;
	private Date _registrationDate;
	private int _coins;
	private Long _id;
	private int _rank;
	private int _gender;
	private int _pronosPublics;
	private Date _birthday;
	private String _location;
	private String _website;
	private String _biography;
	
	public User() {
		
	}
	
	public User(Long id, String username, String email, Date date, int coins) {
		_id = id;
		_username = username;
		_email = email;
		_registrationDate = date;
		_coins = coins;
	}
	
	public User(Long id, String username, String email, Date date, int coins, int rank) {
		_id = id;
		_username = username;
		_email = email;
		_registrationDate = date;
		_coins = coins;
		_rank = rank;
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
	
	public int getRank() {
		return _rank;
	}

	public void setRank(int rank) {
		this._rank = rank;
	}
	
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String _lastName) {
		this._lastName = _lastName;
	}

	public int getGender() {
		return _gender;
	}

	public void setGender(int _gender) {
		this._gender = _gender;
	}

	public int getPronosPublics() {
		return _pronosPublics;
	}

	public void setPronosPublics(int _pronosPublics) {
		this._pronosPublics = _pronosPublics;
	}

	public Date getBirthday() {
		return _birthday;
	}

	public void setBirthday(Date _birthday) {
		this._birthday = _birthday;
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String _location) {
		this._location = _location;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String _website) {
		this._website = _website;
	}

	public String getBiography() {
		return _biography;
	}

	public void setBiography(String _biography) {
		this._biography = _biography;
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
	    groupInfo.put("firstName", this.getFirstName());
	    groupInfo.put("lastName", this.getLastName());
	    
	    if(this.getGender() == 0) {
	    	groupInfo.put("sex", "Homme");
	    } else if(this.getGender() == 1) {
	    	groupInfo.put("sex", "Femme");
	    } else if(this.getGender() == 2) {
	    	groupInfo.put("sex", "Non indiqué");
	    }
	    
	    groupInfo.put("pronosPublics", String.valueOf(this.getPronosPublics()));
	    if(this.getBirthday() != null) {
	    	groupInfo.put("birthday", DateUtil.SHORT_DATE_FORMAT_FRANCE.format(this.getBirthday()));
	    }
	    groupInfo.put("location", this.getLocation());
	    groupInfo.put("website", this.getWebsite());
	    groupInfo.put("biography", this.getBiography());
	    
	    return groupInfo;
	}
}
