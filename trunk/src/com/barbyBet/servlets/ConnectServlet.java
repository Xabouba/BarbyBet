package com.barbyBet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   

	/**
	 * Default constructor.
	 */
	public ConnectServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(Constants.VUE_ERROR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SQLUsersComponent sqlComponent = new SQLUsersComponent();

		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

		User connectedUser = null;

		connectedUser = sqlComponent.isUserRegistered(request);
        
		if (connectedUser != null) {
			// Ajout de l'utilisateur à la session
	        session.setAttribute(Constants.ATT_SESSION_USER, connectedUser);
	        // la session expire dans 30 min
	        session.setMaxInactiveInterval(30*60);
	        
			// Set a cookie
			/* Si et seulement si la case du formulaire est cochée */
		    if (SQLUsersComponent.getValueChamp(request, Constants.CHAMP_MEMOIRE) != null) {
		        /* Création du cookie, et ajout à la réponse HTTP */
		        setCookie(response, Constants.COOKIE_USERNAME, connectedUser.getUsername(), Constants.COOKIE_MAX_AGE);
		    } else {
		        /* Demande de suppression du cookie du navigateur */
		        setCookie(response, Constants.COOKIE_USERNAME, null, 0);
		    }
			
			this.getServletContext().getRequestDispatcher(Constants.VUE_SUCCESS)
					.forward(request, response);
		} else {
			// Ajout des éventuelles erreurs à la requête
			request.setAttribute(Constants.ATT_ERRORS, sqlComponent.getErrors());

			// Ajout du username tapé pour qu'il apparaisse dans l'input
			request.setAttribute(Constants.ATT_USERNAME,
					SQLUsersComponent.getValueChamp(request, Constants.ATT_USERNAME));
			
			// La connexion a échoué on met pas d'attribut de session
			session.setAttribute(Constants.ATT_SESSION_USER, null);

			this.getServletContext().getRequestDispatcher(Constants.VUE_ERROR)
					.forward(request, response);
		}
	}
	
	/*
	 * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	 * réponse HTTP.
	 */
	private static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
	    Cookie cookie = new Cookie(nom, valeur);
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
//	/**
//     * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
//     * depuis la requête HTTP.
//     */
//    private static String getCookieValue(HttpServletRequest request, String nom) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie != null && nom.equals(cookie.getName())) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
//    }
}
