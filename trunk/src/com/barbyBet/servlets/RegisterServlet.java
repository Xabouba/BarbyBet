package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.barbyBet.components.SQLComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.SaxComponent;
import com.barbyBet.object.Match;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_ERRORS   = "errorsRegister";
	public static final String ATT_USERNAME = "usernameRegister";
	public static final String ATT_EMAIL    = "emailRegister";
	public static final String CHAMP_USERNAME = "username";
	public static final String CHAMP_EMAIL    = "email";
    public static final String VUE_ERROR    = "/WEB-INF/jsp/contact.jsp";
    public static final String VUE_SUCCESS  = "/WEB-INF/jsp/index.jsp";

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
		boolean userInsert = false;
		
		// Insertion de l'utilisateur dans la BDD : vérification des conditions dans cette fonction
		userInsert = sqlComponent.insertUser(request);

		if(userInsert) {
			this.getServletContext().getRequestDispatcher(VUE_SUCCESS).forward(request, response);
		} else {
			// Ajout des éventuelles erreurs à la requête
			request.setAttribute(ATT_ERRORS, sqlComponent.getErrors());
			
			// Ajout de l'username et du mail pour qu'ils apparaissent dans l'input
			request.setAttribute(ATT_USERNAME, sqlComponent.getValueChamp(request, CHAMP_USERNAME));
			request.setAttribute(ATT_EMAIL, sqlComponent.getValueChamp(request, CHAMP_EMAIL));
			
			this.getServletContext().getRequestDispatcher(VUE_ERROR).forward(request, response);
		}
	}
	
}
