package com.barbyBet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_ERRORS   	= "errorsRegister";
	public static final String ATT_USERNAME 	= "usernameRegister";
	public static final String ATT_EMAIL    	= "emailRegister";
	public static final String ATT_SESSION_USER = "sessionUser";
	public static final String CHAMP_USERNAME 	= "username";
	public static final String CHAMP_EMAIL   	= "email";
    public static final String VUE_ERROR    	= "/WEB-INF/jsp/register-connect.jsp";
    public static final String VUE_SUCCESS  	= "/WEB-INF/jsp/index.jsp";

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
		this.getServletContext().getRequestDispatcher(VUE_ERROR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SQLUsersComponent sqlComponent = new SQLUsersComponent();
		
		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        
		User insertedUser = null;
		
		// Insertion de l'utilisateur dans la BDD : vérification des conditions dans cette fonction
		insertedUser = sqlComponent.insertUser(request);

		if(insertedUser != null) {
			// Ajout de l'utilisateur aux cookies
			int cookiesExpiry = 60 * 60 * 24 * 365;
			
			Cookie currentUserIdCookie = new Cookie(Constants.COOKIE_CURRENT_USER_ID, String.valueOf(insertedUser.getId()));
			currentUserIdCookie.setMaxAge(cookiesExpiry);
			
			Cookie currentUserNameCookie = new Cookie(Constants.COOKIE_CURRENT_USER_NAME, insertedUser.getUsername());
			currentUserNameCookie.setMaxAge(cookiesExpiry);
			
			Cookie currentUserEmailCookie = new Cookie(Constants.COOKIE_CURRENT_USER_EMAIL, insertedUser.getEmail());
			currentUserEmailCookie.setMaxAge(cookiesExpiry);
			
			Cookie currentUserRegistrationDateCookie = new Cookie(Constants.COOKIE_CURRENT_USER_REGISTRATION_DATE, insertedUser.getRegistrationDate().toString());
			currentUserRegistrationDateCookie.setMaxAge(cookiesExpiry);
			
			Cookie currentUserNumberOfCoinsCookie = new Cookie(Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS, String.valueOf(insertedUser.getCoins()));
			currentUserNumberOfCoinsCookie.setMaxAge(cookiesExpiry);
			
			response.addCookie(currentUserIdCookie);
			response.addCookie(currentUserNameCookie);
			response.addCookie(currentUserEmailCookie);
			response.addCookie(currentUserRegistrationDateCookie);
			response.addCookie(currentUserNumberOfCoinsCookie);
			
			UsersComponent uc = new UsersComponent();
			User currentUser = uc.getCurrentUser(request);
	        
			request.setAttribute("currentUser", currentUser);
			
			this.getServletContext().getRequestDispatcher(VUE_SUCCESS).forward(request, response);
		} else {
			// Ajout des éventuelles erreurs à la requête
			request.setAttribute(ATT_ERRORS, sqlComponent.getErrors());
			
			// L'inscription a échoué on met pas d'attribut de session
			session.setAttribute(ATT_SESSION_USER, null);
						
			// Ajout de l'username et du mail pour qu'ils apparaissent dans l'input
			request.setAttribute(ATT_USERNAME, SQLUsersComponent.getValueChamp(request, CHAMP_USERNAME));
			request.setAttribute(ATT_EMAIL, SQLUsersComponent.getValueChamp(request, CHAMP_EMAIL));
			
			this.getServletContext().getRequestDispatcher(VUE_ERROR).forward(request, response);
		}
	}
	
}
