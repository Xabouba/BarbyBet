package com.barbyBet.servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.RequestUtils;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class BetServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public BetServletAction() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);

		String matchIdAsString = request.getParameter("matchId");
		if (matchIdAsString != null)
		{
			int prono = Integer.parseInt(RequestUtils.getParameter(request, "prono", "0"));
			int scoreHome = Integer.parseInt(RequestUtils.getParameter(request, "scoreHome", "0"));
			int scoreAway = Integer.parseInt(RequestUtils.getParameter(request, "scoreAway", "0"));
			
			int credits = 0;
			Long matchId = Long.parseLong(matchIdAsString);
			
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			sqlPronoComponent.pronostic(matchId, currentUser.getId(), scoreHome, scoreAway, prono, credits);
		}
	}
}
