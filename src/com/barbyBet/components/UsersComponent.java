package com.barbyBet.components;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

public class UsersComponent {
	
	public UsersComponent() {
		
	}
	
	public User getCurrentUser(HttpServletRequest request) {
		User currentUser = new User();
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_ID)) {
				currentUser.setId(Integer.parseInt(cookie.getValue()));
			}
			
			if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_NAME)) {
				currentUser.setUsername(cookie.getValue());
			}
			
			if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_EMAIL)) {
				currentUser.setEmail(cookie.getValue());
			}
			
			if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_REGISTRATION_DATE)) {
				// currentUser.setRegistrationDate(new Date(cookie.getValue()));
			}
			
			if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS)) {
				currentUser.setCoins(Integer.parseInt(cookie.getValue()));
			}
		}
		
		return currentUser;
	}
}
