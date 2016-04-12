package com.barbyBet.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_GROUP = "/WEB-INF/jsp/group.jsp";
    public static final String VUE_INDEX = "/login";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser == null) {
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			String groupIdStr = request.getParameter("groupId");
			
			if(groupIdStr != null) {
				redirectToRightServlet(request, response, groupIdStr);
			} else {
				this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser == null) {
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			String groupIdStr = request.getAttribute("groupId").toString();
			
			if(groupIdStr != null) {
				redirectToRightServlet(request, response, groupIdStr);
			} else {
				this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
			}
		}
	}
	
	public void redirectToRightServlet(HttpServletRequest request, HttpServletResponse response, String groupIdStr) throws ServletException, IOException {
		Long groupId = Long.parseLong(groupIdStr);
		SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
		Group group = sqlGroupComponent.getGroup(groupId);
		if(group == null || group.getId() == null) {
			// Remove all request attributes
			Enumeration<?> e = request.getAttributeNames();
			while(e.hasMoreElements()){
				String attName = (String)e.nextElement();
				request.removeAttribute(attName);
			}
			  
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			request.setAttribute("group", group.toHashMap());
			List<HashMap<String, String>> members = new ArrayList<HashMap<String, String>>();
			for (User u : group.getMembers()) {
				members.add(u.toHashMap());
			}
			
			request.setAttribute("members", members);
			request.setAttribute("groupImagePath", Constants.GROUP_PICS_ROOT_FOLDER + File.separator + group.getImg());
			
			this.getServletContext().getRequestDispatcher(VUE_GROUP).forward(request, response);
		}
	}
}
