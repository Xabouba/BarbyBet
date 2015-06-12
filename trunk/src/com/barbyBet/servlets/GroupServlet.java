package com.barbyBet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.ServletUtil;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = ServletUtil.isConnected(this,request,response);
	    if(user==null){
	    	return;
	    }
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/newgroup.jsp" ).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User user = ServletUtil.isConnected(this,request,response);
	    if(user==null){
	    	return;
	    }
		//TODO add userToGroupLink as admin for current user and check if hasCreatedGroupToday(currentUser)
		String name = request.getParameter("groupname");
		String desc = request.getParameter("groupdesc");
		String status = request.getParameter("status");
		request.setAttribute("groupname", name);
		if(name.equals("") || name.startsWith(" ")){
			request.setAttribute("error", "Le nom du groupe est invalide.");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/newgroup.jsp" ).forward(request, response);
			return;
		}
		Group g = new Group(name,desc,status);
		SQLGroupComponent gc = new SQLGroupComponent();
		String msg = gc.insertGroup(g,user);
		if(msg!=null){
			request.setAttribute("error", msg);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/newgroup.jsp" ).forward(request, response);
			return;
		}
		//TODO change redirection to group view
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/newgroup.jsp" ).forward(request, response);
	}
	
}
