package com.barbyBet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.DateUtil;
import com.barbyBet.tools.ServletUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
	public static final String ATT_ERRORS   	= "errorsLogin";
	public static final String ATT_SESSION_USER = "sessionUser";
	public static final String CHAMP_EMAIL   	= "email";
	public static final String CHAMP_PASSWORD   = "password";
    public static final String VUE_ERROR    	= "/WEB-INF/jsp/login.jsp";
    public static final String VUE_SUCCESS  	= "/WEB-INF/jsp/index.jsp";

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!ServletUtil.getCookieValue(request, Constants.COOKIE_CURRENT_USER_ID).isEmpty()) {
			this.getServletContext().getRequestDispatcher(Constants.VUE_ERROR).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher(Constants.VUE_SUCCESS).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SQLUsersComponent sqlComponent = new SQLUsersComponent();

		User connectedUser = sqlComponent.isUserRegistered(request);
        
		if (connectedUser != null) {
			// Ajout de l'utilisateur aux cookies
			int cookiesExpiry = 60 * 60 * 24 * 365;
			
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_ID, String.valueOf(connectedUser.getId()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_NAME, String.valueOf(connectedUser.getUsername()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_EMAIL, String.valueOf(connectedUser.getEmail()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_REGISTRATION_DATE, DateUtil.FULL_DATE_FORMAT_FRANCE.format(connectedUser.getRegistrationDate()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS, String.valueOf(connectedUser.getCoins()), cookiesExpiry);

			UsersComponent uc = new UsersComponent();
			User currentUser = uc.getCurrentUser(request);
	        
			request.setAttribute("currentUser", currentUser);
			
			this.getServletContext().getRequestDispatcher(VUE_SUCCESS).forward(request, response);
		} else {
			// Ajout des éventuelles erreurs à la requête
			request.setAttribute(ATT_ERRORS, sqlComponent.getErrors());

			this.getServletContext().getRequestDispatcher(Constants.VUE_ERROR)
					.forward(request, response);
		}
	}
}
