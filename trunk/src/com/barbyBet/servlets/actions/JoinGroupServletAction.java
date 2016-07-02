package com.barbyBet.servlets.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.GroupsUtils;

/**
 * Servlet implementation class JoinGroupServletAction
 */
@WebServlet("/JoinGroupServletAction")
public class JoinGroupServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String VIEW_GROUP = "/WEB-INF/jsp/group.jsp";
    private User currentUser;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinGroupServletAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		currentUser = usersComponent.getCurrentUser(request);
		
		if(!usersComponent.isCurrentUser(currentUser)) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			List<Group> userGroups = sqlGroupComponent.getUserGroups(currentUser.getId());
			Object justJoinedGroup = request.getAttribute("justJoinedGroup");
			String groupIdStr = null;
			
			if(justJoinedGroup != null) {
				groupIdStr = justJoinedGroup.toString(); // When the user clicked on "rejoindre ce groupe" then we show this group and not the others
			} else {
				groupIdStr = userGroups.get(0).getId().toString();
			}
			
			Long groupId = Long.parseLong(groupIdStr);
			Group group = sqlGroupComponent.getGroup(groupId);
			GroupsUtils groupsUtils = new GroupsUtils();
			
			groupsUtils.redirectToRightServlet(this.getServletContext(), request, response, group, userGroups, currentUser, VIEW_GROUP);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		currentUser = usersComponent.getCurrentUser(request);
		
		String groupIdStr = request.getParameter("groupIdJoinGroup");
		Long groupId = Long.parseLong(groupIdStr);
		
		SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
		Group g = sqlGroupComponent.getGroup(groupId);
		String result = sqlGroupComponent.addUserToGroup(g, currentUser);
		
		if(sqlGroupComponent.ADD_SUCCESSFUL.equals(result)) {
			// Update Group object
			g = sqlGroupComponent.getGroup(g.getId());
			
			// Add points to user in group 
			sqlGroupComponent.updateGroupUserPoint(currentUser.getId(), g.getId(), currentUser.getCoins());
			
			// Update Rank
			sqlGroupComponent.updateRankAfterModificationInGroup(g, currentUser);
			request.setAttribute("justJoinedGroup", g.getId());
			
			doGet(request, response);
		} else {
			// Redirect back to the same page with the error msg
			request.setAttribute("joinGroupMsg", result);
			doGet(request, response);
		}
	}

}
