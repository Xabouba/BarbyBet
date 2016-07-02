
package com.barbyBet.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLCommentComponent;
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
    public static final String CREATE_GROUP_SERVLET = "/createGroup";
    
    private User currentUser;
    private List<Group> userGroups;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		currentUser = usersComponent.getCurrentUser(request);
		
		if(!usersComponent.isCurrentUser(currentUser)) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {
			// If we are in the case of a user deleting a group, the groupId is not set anymore and Group group will be null 
			String groupIdStr = request.getParameter("groupId");
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			Group group = null;
			
			if(groupIdStr != null && !"".equals(groupIdStr)) {
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
					response.sendRedirect(CREATE_GROUP_SERVLET);
				} else {
					Long groupId = Long.parseLong(userGroups.get(0).getId().toString());
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
		
		if(!usersComponent.isCurrentUser(currentUser)) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {
			String groupIdStr = request.getParameter("groupId");
			Long groupId = Long.parseLong(groupIdStr);
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			Group group = sqlGroupComponent.getGroup(groupId);
			
			if(groupIdStr != null) {
				redirectToRightServlet(request, response, group);
			} else {
				response.sendRedirect(Constants.INDEX_SERVLET);
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
						Constants.COOKIE_CURRENT_USER_NAME.equals(attName) || 
						Constants.COOKIE_CURRENT_USER_NUMBER_OF_COINS.equals(attName)) {
					continue;
				} else {
					request.removeAttribute(attName);
				}
			}
			  
			response.sendRedirect(Constants.INDEX_SERVLET);
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
			
			if(group.getImg() != null) {
				request.setAttribute("groupImagePath", Constants.GROUP_PICS_FORMATED_ROOT_FOLDER + File.separator + group.getImg());
			} else {
				request.setAttribute("groupImagePath", "");
			}
			request.setAttribute("currentGroupId", group.getId());

			/** Commentaires */
			SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
			ArrayList<HashMap<String, String>> comments = sqlCommentComponent.getGroupChatMessages(group.getId());
			request.setAttribute("comments", comments);
			
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(group.getId(), currentUser.getUsername()));
			
			/** Group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			Map<Long, Map<String, String>> userGroupsToSend = sqlGroupComponent.getGroups(currentUser.getId());
			// TODO : Check if the group being look at is in the user's group list, otherwise add it to the ranking
			request.setAttribute("userGroups", userGroupsToSend);
			
			this.getServletContext().getRequestDispatcher(VUE_GROUP).forward(request, response);
		}
	}
}
                         