package com.barbyBet.servlets.actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.GroupsUtils;

/**
 * Servlet implementation class LeaveOrDeleteGroupServletAction
 */
@WebServlet("/LeaveOrDeleteGroupServletAction")
public class LeaveOrDeleteGroupServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CREATE_GROUP_SERVLET = "/createGroup";
	public static final String VIEW_GROUP = "/WEB-INF/jsp/group.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveOrDeleteGroupServletAction() {
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
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			List<Group> userGroups = sqlGroupComponent.getUserGroups(currentUser.getId());
			
			// If the user is affiliated to no group we redirect him to CreateGroupServlet
			if(userGroups == null || userGroups.isEmpty()) {
				request.setAttribute("comingFromGroupServletDelete", "yes");
				response.sendRedirect(CREATE_GROUP_SERVLET);
			} else {
				String groupIdStr = userGroups.get(0).getId().toString();
				Long groupId = Long.parseLong(groupIdStr);
				Group group = sqlGroupComponent.getGroup(groupId);
				GroupsUtils groupsUtils = new GroupsUtils();
				groupsUtils.redirectToRightServlet(this.getServletContext(), request, response, group, userGroups, currentUser, VIEW_GROUP);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		SQLUsersComponent sqlUserComponent = new SQLUsersComponent();

		User u = null;
		
		if(username != null) {
			u = sqlUserComponent.getUser(username);
		}
		
		String actionType = request.getParameter("actionType");
		
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
			// Delete the group pic
			String groupPicName = g.getImg();
			
			if(groupPicName != null) {
				// Delete group image
				File deleteFile = new File(Constants.GROUP_PICS_FORMATED_ROOT_FOLDER + File.separator + groupPicName) ;
				// check if the file is present or not
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
			}
			
			// Redirect to the group page
			if("leave-group".equals(actionType)) {
				// Update Group object
				g = sqlGroupComponent.getGroup(g.getId());
				
				// Update Rank
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
	}
}
