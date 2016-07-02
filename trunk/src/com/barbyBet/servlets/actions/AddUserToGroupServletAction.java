package com.barbyBet.servlets.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.User;

/**
 * Servlet implementation class AddUserToGroupServletAction
 */
@WebServlet("/AddUserToGroupServletAction")
public class AddUserToGroupServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserToGroupServletAction() {
        super();
        // TODO Auto-generated constructor stub
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
		String username = request.getParameter("username");
		SQLUsersComponent sqlUserComponent = new SQLUsersComponent();

		User u = null;
		
		if(username != null) {
			u = sqlUserComponent.getUser(username);
		}
		
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
				
				// Add points to user in group 
				sqlGroupComponent.updateGroupUserPoint(u.getId(), g.getId(), u.getCoins());
				
				// Update Rank
				sqlGroupComponent.updateRankAfterModificationInGroup(g, u);
				
				result = "L'utilisateur " + u.getUsername() + " a correctement été ajouté au groupe " + g.getName();
			}
			
			response.setContentType("text/plain");
	        response.getWriter().write(result);
		}
	}

}
