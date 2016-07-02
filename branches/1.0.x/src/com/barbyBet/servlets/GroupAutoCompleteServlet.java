package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.google.gson.Gson;

/**
 * Servlet implementation class GroupAutoCompleteServlet
 */
@WebServlet("/GroupAutoCompleteServlet")
public class GroupAutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupAutoCompleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(!usersComponent.isCurrentUser(currentUser)) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {
			String actionType = request.getParameter("actionType").toString();
			// The request is coming from an AJAX request in group.jsp
			if(actionType != null) {
				if("add-user-to-group".equals(actionType)) {
					response.setContentType("application/json");
			        try {
			            String term = request.getParameter("term");
			
			            SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
			            ArrayList<String> users = sqlUsersComponent.getUserNames(term, currentUser.getUsername());
			
			            String searchList = new Gson().toJson(users);
			            response.getWriter().write(searchList);
			        } catch (Exception e) {
			            System.err.println(e.getMessage());
			        }
				} else if("delete-user-from-group".equals(actionType)) {
					response.setContentType("application/json");
			        try {
			            String term = request.getParameter("term");
			            String groupIdStr = request.getParameter("groupId").toString();
						Long groupId = Long.parseLong(groupIdStr);
			
			            SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
			            ArrayList<String> users = sqlUsersComponent.getUserNamesFromGroup(term, currentUser.getUsername(), groupId);
			
			            String searchList = new Gson().toJson(users);
			            response.getWriter().write(searchList);
			        } catch (Exception e) {
			            System.err.println(e.getMessage());
			        }
				} else if("look-for-group".equals(actionType)) {
					response.setContentType("application/json");
					try {
						String term = request.getParameter("term");
			
			            SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			            ArrayList<String> groups = sqlGroupComponent.getAllPublicGroupNames(term);
			
			            String searchList = new Gson().toJson(groups);
			            response.getWriter().write(searchList);
			        } catch (Exception e) {
			            System.err.println(e.getMessage());
			        }
				} else if("look-for-user".equals(actionType)) {
					response.setContentType("application/json");
					try {
						String term = request.getParameter("term");
			
			            SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
			            ArrayList<String> users = sqlUsersComponent.getUserNames(term, currentUser.getUsername());
			
			            String searchList = new Gson().toJson(users);
			            response.getWriter().write(searchList);
			        } catch (Exception e) {
			            System.err.println(e.getMessage());
			        }
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
