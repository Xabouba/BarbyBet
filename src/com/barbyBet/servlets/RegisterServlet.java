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
	//	SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();	
		System.out.println("get");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/contact.jsp" ).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		
		if(username != null && email != null && password != null && repeatPassword != null) {
			if(password.equals(repeatPassword)) {
				SQLUsersComponent sqlComponent = new SQLUsersComponent();
				sqlComponent.insertUser(email, username, password);
			} else {
				// Passwords do not match
			}
			
			doGet(request,response);
		}
	}
	
}
