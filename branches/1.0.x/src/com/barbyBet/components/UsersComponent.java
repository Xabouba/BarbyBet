package com.barbyBet.components;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.DateUtil;
import com.barbyBet.tools.ServletUtil;

public class UsersComponent {
	
	public UsersComponent() {
		
	}
	
	public boolean isCurrentUser(User currentUser) {
		SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
		
		if(currentUser.getId() == null || currentUser.getUsername() == null || currentUser.getEmail() == null) {
			return false;
		}
		
		return sqlUsersComponent.isUserInDatabase(currentUser);
	}
	
	public User getCurrentUser(HttpServletRequest request) {
		User currentUser = new User();
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_ID) && cookie.getMaxAge() != 0) {
					currentUser.setId(Long.parseLong(cookie.getValue()));
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_NAME) && cookie.getMaxAge() != 0) {
					try {
						currentUser.setUsername(URLDecoder.decode(cookie.getValue(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_EMAIL) && cookie.getMaxAge() != 0) {
					try {
						currentUser.setEmail(URLDecoder.decode(cookie.getValue(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_REGISTRATION_DATE) && cookie.getMaxAge() != 0) {
					try {
						currentUser.setRegistrationDate(DateUtil.FULL_DATE_FORMAT_FRANCE.parse(cookie.getValue()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS) && cookie.getMaxAge() != 0) {
					currentUser.setCoins(Integer.parseInt(cookie.getValue()));
				}
			}
		}
		
		return currentUser;
	}
}
