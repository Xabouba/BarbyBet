package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLCommentComponent;
import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.User;
import com.barbyBet.tools.RequestUtils;

public class DirectResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DirectResultServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		if(currentUser == null) {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/login.jsp" ).forward(request, response);
		} else {
			SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
				
			String matchIdAsString = request.getParameter("matchId");
			if (matchIdAsString == null)
			{
				//TODO redirect to matchs
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/match.jsp" ).forward(request, response);
			}
			else
			{
				/** Match information */
				Long matchId = Long.parseLong(matchIdAsString);
				Match match = sqlMatchComponent.getMatch(matchId);
				request.setAttribute("match", match.toHashMap());
				
				/** Pronostic */
				SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
				HashMap<String, String> prono = sqlPronoComponent.getProno(matchId, currentUser.getId());
				
				request.setAttribute("prono", prono.get("prono"));
				request.setAttribute("scoreHome", prono.get("scoreHome"));
				request.setAttribute("scoreAway", prono.get("scoreAway"));
				request.setAttribute("credits", prono.get("credits"));
				request.setAttribute("creditsWon", prono.get("creditsWon"));
				
				/** Commentaires */
				SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
				ArrayList<HashMap<String, String>> comments = sqlCommentComponent.getComments(matchId);
				request.setAttribute("comments", comments);
				
				/** Classement */
				RankComponent rankComponent = new RankComponent();
				request.setAttribute("rank", rankComponent.getMinimizedRank(1, currentUser.getUsername()));
				
	//			User connectedUser = null;
	//			SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
	//			connectedUser = sqlUsersComponent.isUserRegistered(request);
	//			if (connectedUser != null)
	//			{
	//				request.setAttribute("user", connectedUser.getUsername());
	//			}
				
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/direct.jsp" ).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
//		
//		String comment = request.getParameter("comment");
//		if (comment != null  && comment != "")
//		{
//			String matchId = request.getParameter("matchId");
//			
//			SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
//			sqlCommentComponent.insertComment(comment.replace("\n", "<br/>"), matchId , currentUser.getId());
//
//			doGet(request, response);
//		}
		
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
		
//			doGet(request, response);
		}
	}
}
