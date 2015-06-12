package com.barbyBet.tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.barbyBet.object.User;

public class ServletUtil {

	public static User isConnected(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    HttpSession session = request.getSession();
		User connectedUser = (User) session.getAttribute(Constants.ATT_SESSION_USER);
		if(connectedUser==null){
			request.setAttribute("error", Constants.NOT_CONNECTED_ERROR);
			servlet.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/connect.jsp" ).forward(request, response);
			return null;
		}
		return connectedUser;
	}
}
