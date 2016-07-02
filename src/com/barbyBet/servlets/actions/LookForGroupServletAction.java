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
 * Servlet implementation class LookForGroupServletAction
 */
@WebServlet("/LookForGroupServletAction")
public class LookForGroupServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private User currentUser;   
    private String VIEW_GROUP = "/WEB-INF/jsp/group.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookForGroupServletAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupToLookForIdStr = request.getParameter("groupName");
		
		SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
		Long groupToLookForId = sqlGroupComponent.getGroupId(groupToLookForIdStr);
		
		UsersComponent usersComponent = new UsersComponent();
		currentUser = usersComponent.getCurrentUser(request);
		
		GroupsUtils groupsUtils = new GroupsUtils();
		List<Group> userGroups = sqlGroupComponent.getUserGroups(currentUser.getId());
		
		String currentGroupIdStr = request.getParameter("groupId");
		Long currentGroupId = Long.parseLong(currentGroupIdStr);
		Group currentGroup = sqlGroupComponent.getGroup(currentGroupId);
		
		if(groupToLookForId != 0L) {
			Group group = sqlGroupComponent.getGroup(groupToLookForId);
			
			if(group.getStatus() == 1) {
				request.setAttribute("lookForGroupMsg", "Ce groupe est privé, vous n'avez pas le droit d'y accéder");
				
				group = currentGroup;
			}
			
			groupsUtils.redirectToRightServlet(this.getServletContext(), request, response, group, userGroups, currentUser, VIEW_GROUP);
		} else {
			request.setAttribute("lookForGroupMsg", "Ce nom de groupe n'existe pas");
			groupsUtils.redirectToRightServlet(this.getServletContext(), request, response, currentGroup, userGroups, currentUser, VIEW_GROUP);
		}
	}

}
