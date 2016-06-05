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

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_ERRORS   	= "errorsRegister";
	public static final String ATT_USERNAME 	= "usernameRegister";
	public static final String ATT_EMAIL    	= "emailRegister";
	public static final String ATT_SESSION_USER = "sessionUser";
	public static final String CHAMP_USERNAME 	= "username";
	public static final String CHAMP_EMAIL   	= "email";
    public static final String VIEW_REGISTER    = "/WEB-INF/jsp/register.jsp";
    public static final String VIEW_INDEX  		= "/WEB-INF/jsp/index.jsp";

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser.getId() == null) {
			this.getServletContext().getRequestDispatcher(VIEW_REGISTER).forward(request, response);
		} else {
			response.sendRedirect(Constants.INDEX_SERVLET);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SQLUsersComponent sqlComponent = new SQLUsersComponent();
	
		User insertedUser = null;
		
		// Insertion de l'utilisateur dans la BDD : vérification des conditions dans cette fonction
		insertedUser = sqlComponent.insertUser(request);

		if(insertedUser != null) {
			// Ajout de l'utilisateur aux cookies
			int cookiesExpiry = 60 * 60 * 24 * 365;
			
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_ID, String.valueOf(insertedUser.getId()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_NAME, String.valueOf(insertedUser.getUsername()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_EMAIL, String.valueOf(insertedUser.getEmail()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_REGISTRATION_DATE, DateUtil.FULL_DATE_FORMAT_FRANCE.format(insertedUser.getRegistrationDate()), cookiesExpiry);
			ServletUtil.setCookie(response, Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS, String.valueOf(insertedUser.getCoins()), cookiesExpiry);

			UsersComponent uc = new UsersComponent();
			User currentUser = uc.getCurrentUser(request);
	        
			request.setAttribute("currentUser", currentUser);
			
			response.sendRedirect(Constants.INDEX_SERVLET);
		} else {
			// Ajout des éventuelles erreurs à la requête
			request.setAttribute(ATT_ERRORS, sqlComponent.getErrors());
			
			this.getServletContext().getRequestDispatcher(VIEW_REGISTER).forward(request, response);
		}
	}
	
}
