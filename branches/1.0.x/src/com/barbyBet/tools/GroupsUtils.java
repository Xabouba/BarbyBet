package com.barbyBet.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLCommentComponent;
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;

public class GroupsUtils {
	
	public GroupsUtils() {
		
	}

	public void redirectToRightServlet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Group group, List<Group> userGroups, User currentUser, String viewToForwardTo) throws ServletException, IOException {
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
			
			servletContext.getRequestDispatcher(viewToForwardTo).forward(request, response);
		}
	}
}
