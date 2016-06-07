package com.barbyBet.components;

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
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_ID) && cookie.getMaxAge() != 0) {
					currentUser.setId(Long.parseLong(cookie.getValue()));
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_NAME) && cookie.getMaxAge() != 0) {
					currentUser.setUsername(cookie.getValue());
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_EMAIL) && cookie.getMaxAge() != 0) {
					currentUser.setEmail(cookie.getValue());
				}
				
				if(cookie.getName().equals(Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS) && cookie.getMaxAge() != 0) {
					currentUser.setCoins(Integer.parseInt(cookie.getValue()));
				}
			}
		}
		
		return currentUser;
	}
}
