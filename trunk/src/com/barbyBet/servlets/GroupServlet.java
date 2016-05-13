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

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLUsersComponent;
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
	public static final String GROUP_SERVLET = "/group";
    public static final String VUE_INDEX = "/login";
    public static final String CREATE_GROUP_SERVLET = "/createGroup";
    
    private User currentUser;
    private List<Group> userGroups;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser.getId() == null) {
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			// If we are in the case of a user deleting a group, the groupId is not set anymore and Group group will be null 
			String groupIdStr = request.getParameter("groupId");
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			Group group = null;
			
			if(groupIdStr != null) {
				Long groupId = Long.parseLong(groupIdStr);
				group = sqlGroupComponent.getGroup(groupId);
			}
			
			if(group != null) {
				redirectToRightServlet(request, response, group);
			} else {
				userGroups = sqlGroupComponent.getUserGroups(currentUser.getId());
				
				// If the user is affiliated to no group we redirect him to CreateGroupServlet
				if(userGroups == null || userGroups.isEmpty()) {
					request.setAttribute("comingFromGroupServletDelete", "yes");
					this.getServletContext().getRequestDispatcher(CREATE_GROUP_SERVLET).forward(request, response);
				} else {
					groupIdStr = userGroups.get(0).getId().toString();
					Long groupId = Long.parseLong(groupIdStr);
					group = sqlGroupComponent.getGroup(groupId);
					redirectToRightServlet(request, response, group);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser.getId() == null) {
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			String actionType = request.getParameter("actionType");
			
			// The request is coming from an AJAX request in group.jsp
			if(actionType != null) {
				String username = request.getParameter("username");
				SQLUsersComponent sqlUserComponent = new SQLUsersComponent();

				User u = null;
				
				if(username != null) {
					u = sqlUserComponent.getUser(username);
				}

				if("add-user-to-group".equals(actionType)) {
					if(u == null) {
						response.setContentType("text/plain");
				        response.getWriter().write("L'utilisateur \"" + username + "\" n'existe pas");
					} else {
						String groupIdStr = request.getParameter("groupId");
						Long groupId = Long.parseLong(groupIdStr);
						
						SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
						Group g = sqlGroupComponent.getGroup(groupId);
						String result = sqlGroupComponent.addUserToGroup(g, u);
						
						if(sqlGroupComponent.ADD_SUCCESSFUL.equals(result)) {
							// Update Group object
							g = sqlGroupComponent.getGroup(g.getId());
							
							result = "L'utilisateur " + u.getUsername() + " a correctement été ajouté au groupe " + g.getName();
						}
						response.setContentType("text/plain");
				        response.getWriter().write(result);
					}
				} else if("delete-user-from-group".equals(actionType)) {
					if(u == null) {
						response.setContentType("text/plain");
				        response.getWriter().write("L'utilisateur \"" + username + "\" n'existe pas");
					} else {
						String groupIdStr = request.getParameter("groupId").toString();
						Long groupId = Long.parseLong(groupIdStr);
						
						SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
						Group g = sqlGroupComponent.getGroup(groupId);
						String result = sqlGroupComponent.deleteUserFromGroup(g, u, currentUser);
						
						if(sqlGroupComponent.DELETE_SUCCESSFUL.equals(result)) {
							// Update Group object
							g = sqlGroupComponent.getGroup(g.getId());
							
							result = "L'utilisateur " + u.getUsername() + " a correctement été supprimé du groupe " + g.getName();
						}
						
						response.setContentType("text/plain");
				        response.getWriter().write(result);
					}
				} else if("leave-group".equals(actionType) || "delete-group".equals(actionType)) {
					String groupIdStr = request.getParameter("groupIdLeaveDeleteGroup");
					Long groupId = Long.parseLong(groupIdStr);

					SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
					Group g = sqlGroupComponent.getGroup(groupId);
					
					String result = null;
					if("leave-group".equals(actionType)) {
						result = sqlGroupComponent.leaveGroup(g, u);
					} else if("delete-group".equals(actionType)) {
						result = sqlGroupComponent.deleteGroup(g);
					}
					
					if(sqlGroupComponent.DELETE_SUCCESSFUL.equals(result)) {
						// Redirect to the group page
						if("leave-group".equals(actionType)) {
							// Update Group object
							g = sqlGroupComponent.getGroup(g.getId());
							
							// TODO : Update Rank
							sqlGroupComponent.updateRankAfterModificationInGroup(g, u);
						}
						doGet(request, response);
					} else {
						// Redirect back to the same page with the error msg
						if("leave-group".equals(actionType)) {
							request.setAttribute("leaveGroupMsg", result);
						} else if("delete-group".equals(actionType)) {
							request.setAttribute("deleteGroupMsg", result);
						}
						doGet(request, response);
					}
				} else if("join-group".equals(actionType)) {
					String groupIdStr = request.getParameter("groupIdJoinGroup");
					Long groupId = Long.parseLong(groupIdStr);
					
					SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
					Group g = sqlGroupComponent.getGroup(groupId);
					String result = sqlGroupComponent.addUserToGroup(g, currentUser);
					
					if(sqlGroupComponent.ADD_SUCCESSFUL.equals(result)) {
						// Update Group object
						g = sqlGroupComponent.getGroup(g.getId());
						// TODO : Update Rank
						sqlGroupComponent.updateRankAfterModificationInGroup(g, currentUser);
						doGet(request, response);
					} else {
						// Redirect back to the same page with the error msg
						request.setAttribute("joinGroupMsg", result);
						doGet(request, response);
					}
				} else if("look-for-group".equals(actionType)) {
					String groupName = request.getParameter("groupName");
					SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
					Long groupId = sqlGroupComponent.getGroupId(groupName);
					
					if(groupId != 0L) {
						Group g = sqlGroupComponent.getGroup(groupId);
						redirectToRightServlet(request, response, g);
					} else {
						request.setAttribute("lookForGroupMsg", "Ce nom de groupe n'existe pas");
						doGet(request, response);
					}
				}
			} else {
				String groupIdStr = request.getParameter("groupId");
				Long groupId = Long.parseLong(groupIdStr);
				SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
				Group group = sqlGroupComponent.getGroup(groupId);
				
				if(groupIdStr != null) {
					redirectToRightServlet(request, response, group);
				} else {
					this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
				}
			}
		}
	}
	
	public void redirectToRightServlet(HttpServletRequest request, HttpServletResponse response, Group group) throws ServletException, IOException {
		if(group == null || group.getId() == null) {
			// Remove all request attributes
			Enumeration<?> e = request.getAttributeNames();
			while(e.hasMoreElements()){
				String attName = (String)e.nextElement();
				
				if(Constants.COOKIE_CURRENT_USER_ID.equals(attName) || Constants.COOKIE_CURRENT_USER_EMAIL.equals(attName) || 
						Constants.COOKIE_CURRENT_USER_NAME.equals(attName) || Constants.COOKIE_CURRENT_USER_REGISTRATION_DATE.equals(attName) || 
						Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS.equals(attName)) {
					continue;
				} else {
					request.removeAttribute(attName);
				}
			}
			  
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
		} else {
			request.setAttribute("group", group.toHashMap());
			request.setAttribute("isUserInGroup", false);
			List<HashMap<String, String>> members = new ArrayList<HashMap<String, String>>();
			for (User u : group.getMembers()) {
				members.add(u.toHashMap());
				if(currentUser.getId() == u.getId()) {
					request.setAttribute("isUserInGroup", true);
				}
			}
			
			request.setAttribute("members", members);
			
			// Get the last five added users (the sorting should have been done in the "getGroup" function in SQLGroupComponent)
			List<HashMap<String, String>> lastFiveMembers = new ArrayList<HashMap<String, String>>();
			int max = group.getMembers().size();
			if(max > 5) {
				max = 5;
			}
			
			for(int i = 0; i < max; i++) {
				lastFiveMembers.add(group.getMembers().get(i).toHashMap());
			}
			
			request.setAttribute("lastFiveMembers", lastFiveMembers);
			
			if(userGroups != null) {
				// Gets the groups where the user is in
				List<HashMap<String, String>> userGroupList = new ArrayList<HashMap<String, String>>();
				for(Group userGroup : userGroups) {
					userGroupList.add(userGroup.toHashMap());
				}
				
				request.setAttribute("userGroupList", userGroupList);
			}
			request.setAttribute("groupImagePath", Constants.GROUP_PICS_ROOT_FOLDER + File.separator + group.getImg());
			
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(group.getId(), currentUser.getUsername()));
			
			/** Group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			request.setAttribute("userGroups", sqlGroupComponent.getGroups(currentUser.getId()));
			
			this.getServletContext().getRequestDispatcher(VUE_GROUP).forward(request, response);
		}
	}
}
