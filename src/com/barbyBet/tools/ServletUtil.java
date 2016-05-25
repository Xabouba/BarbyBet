package com.barbyBet.tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.barbyBet.object.User;

public class ServletUtil {

	public ServletUtil() {
		
	}
	
	public static User isConnected(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    HttpSession session = request.getSession();
		User connectedUser = (User) session.getAttribute(Constants.ATT_SESSION_USER);
		if(connectedUser==null){
			request.setAttribute("error", Constants.NOT_CONNECTED_ERROR);
			servlet.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/login.jsp" ).forward(request, response);
			return null;
		}
		return connectedUser;
	}
	
	/**
	 * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	 * réponse HTTP.
	 */
	public static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
	    Cookie cookie = new Cookie(nom, valeur);
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
	/**
	  * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
	  * depuis la requête HTTP.
	  */
	 public static String getCookieValue(HttpServletRequest request, String nom) {
	     Cookie[] cookies = request.getCookies();
	     if (cookies != null) {
	         for (Cookie cookie : cookies) {
	             if (cookie != null && nom.equals(cookie.getName())) {
	                 return cookie.getValue();
	             }
	         }
	     }
	     return null;
	 }
	 
	 public static void removeCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
			
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	 }
	 
	 public static int getGroupStatusFromString(String groupStatus) {
		 if("public".equals(groupStatus)) {
			 return 0;
		 } else if("private".equals(groupStatus)) {
			 return 1;
		 }
		 
		 return 0;
	 }
	 
	 public static String getGroupStatusStringFromInt(int groupStatus) {
		 if(groupStatus == 0) {
			 return "Public";
		 } else if(groupStatus == 1) {
			 return "Privé";
		 }
		 
		 return "Public";
	 }
}