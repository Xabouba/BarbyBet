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
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.MatchStatus;
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
		
		if(currentUser.getId() == null) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
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
				
				if(match.getStatut() != MatchStatus.NOT_STARTED) {
					request.setAttribute("matchStarted", "yes");
				}
				
				/** Pronostic */
				SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
				HashMap<String, String> prono = sqlPronoComponent.getProno(matchId, currentUser.getId());
				
				request.setAttribute("prono", prono.get("prono"));
				request.setAttribute("scoreHome", prono.get("scoreHome"));
				request.setAttribute("scoreAway", prono.get("scoreAway"));
				request.setAttribute("credits", prono.get("credits"));
				request.setAttribute("creditsWon", prono.get("creditsWon"));
				
				/** Match Stat */
				HashMap<String, String> matchStatPronostic = sqlPronoComponent.getMatchStatPronostic(matchId);
				request.setAttribute("matchStat", matchStatPronostic);
				
				/** Current User */
				request.setAttribute("currentUser", currentUser.getUsername());
				
				/** Commentaires */
				SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
				ArrayList<HashMap<String, String>> comments = sqlCommentComponent.getComments(matchId);
				request.setAttribute("comments", comments);
				
				/** Classement */
				RankComponent rankComponent = new RankComponent();
				request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
				
				/** Group */
				SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
				request.setAttribute("userGroups", sqlGroupComponent.getGroups(currentUser.getId()));
				
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/direct.jsp" ).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String matchIdAsString = request.getParameter("matchId");
		if (matchIdAsString != null)
		{
			SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
			
			/** Match information */
			Long matchId = Long.parseLong(matchIdAsString);
			Match match = sqlMatchComponent.getMatch(matchId);
			request.setAttribute("match", match.toHashMap());
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/direct-part.jsp" ).forward(request, response);
			
		}
	}
}
