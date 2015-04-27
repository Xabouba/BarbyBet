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

public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_ERRORS = "errorsConnect";
	public static final String ATT_USERNAME = "usernameConnect";
	public static final String VUE_ERROR = "/WEB-INF/jsp/contact.jsp";
	public static final String VUE_SUCCESS = "/WEB-INF/jsp/index.jsp";

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
		this.getServletContext()
				.getRequestDispatcher(VUE_ERROR)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SQLUsersComponent sqlComponent = new SQLUsersComponent();

		boolean isConnected = false;

		isConnected = sqlComponent.isUserRegistered(request);

		if (isConnected) {
			// Set a cookie
			// TODO
			
			this.getServletContext().getRequestDispatcher(VUE_SUCCESS)
					.forward(request, response);
		} else {
			// Ajout des éventuelles erreurs à la requête
			request.setAttribute(ATT_ERRORS, sqlComponent.getErrors());

			// Ajout du username tapé pour qu'il apparaisse dans l'input
			request.setAttribute(ATT_USERNAME,
					sqlComponent.getValueChamp(request, ATT_USERNAME));

			this.getServletContext().getRequestDispatcher(VUE_ERROR)
					.forward(request, response);
		}
	}
}
