package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public AccountServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser.getId() == null) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
			
			Long id = currentUser.getId();
			String idUser = request.getParameter("userId");
			if (idUser != null)
			{
				id = Long.parseLong(idUser);
			}
			
			/** Current User */
			request.setAttribute("isCurrentUser", id == currentUser.getId());
			
			/** Group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			request.setAttribute("userGroups", sqlGroupComponent.getGroups(currentUser.getId()));

			// Don't show private groups to others
			if(id == currentUser.getId()) {
				request.setAttribute("groups", sqlGroupComponent.getGroups(id));
			} else {
				request.setAttribute("groups", sqlGroupComponent.getGroupsByStatus(id, 0));
			}
			/** Next Pronostic */
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			ArrayList<HashMap<String, String>> nextMatchPronostic = sqlPronoComponent.getNextMatchPronostic(id);
			request.setAttribute("nextProno", nextMatchPronostic);

			/** Past Pronostic */
			ArrayList<HashMap<String, String>> pastMatchPronostic = sqlPronoComponent.getPastMatchPronostic(id);
			request.setAttribute("pastProno", pastMatchPronostic);
			
			/** User stat */
			SQLUsersComponent sqlUserComponent = new SQLUsersComponent();
			request.setAttribute("userStat", sqlPronoComponent.getUserStatPronostic(id));
			User user = sqlUserComponent.getUser(id);
			request.setAttribute("userLogin", user.getUsername());
			request.setAttribute("userPoint", user.getCoins());
			request.setAttribute("userRank", user.getRank());
			

			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}	
}