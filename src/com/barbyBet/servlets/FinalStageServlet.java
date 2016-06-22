package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.Team;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.MatchStatus;
import com.barbyBet.tools.RequestUtils;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class FinalStageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public FinalStageServlet() {
        // TODO Auto-generated constructor stub
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
			/** Competition Stage */
			int idCompetition = 1; //TODO
			_setInfoGroup(request, "4", idCompetition, currentUser);
			
			/** Selected stage */
			request.setAttribute("selectedStage", "4");
			
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
			
			/** User group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			request.setAttribute("userGroups", sqlGroupComponent.getGroups(currentUser.getId()));
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/finalStage.jsp" ).forward(request, response);
		}
	}

	private void _setInfoGroup(HttpServletRequest request, String stage, int idCompetition, User currentUser)
	{
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
		SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
		
		List<Match> matchByGroup = sqlMatchComponent.getMatchByStage(idCompetition, stage);

		Map<Date, ArrayList<HashMap<String, String>>> matchEnded = new TreeMap<Date, ArrayList<HashMap<String, String>>>();
		Map<Date, ArrayList<HashMap<String, String>>> matchs = new TreeMap<Date, ArrayList<HashMap<String, String>>>();
		HashMap<Integer, HashMap<String, Object>> rank = new HashMap<Integer, HashMap<String, Object>>();
		
		Date day = new Date();
		
		for (Match match : matchByGroup)
		{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(match.getBeginDate().getTime());

			HashMap<String, String> pronoMap = sqlPronoComponent.getProno(match.getId(), currentUser.getId());
			ArrayList<HashMap<String, String>> list;
			if (day.equals(calendar.getTime()))
			{
				list = matchs.get(calendar.getTime());
				HashMap<String,String> matchMap = match.toHashMap();
				matchMap.putAll(pronoMap);
				matchMap.put("ended", String.valueOf(match.getStatut() == MatchStatus.ENDED));
				
				list.add(matchMap);
			}
			else
			{
				day = calendar.getTime();
				list = new ArrayList<HashMap<String,String>>();
				
				HashMap<String,String> matchMap = match.toHashMap();
				matchMap.putAll(pronoMap);
				matchMap.put("ended", String.valueOf(match.getStatut() == MatchStatus.ENDED));
				
				list.add(matchMap);
				matchs.put(day, list);
			}
		}

		HashMap<String, Map<Date, ArrayList<HashMap<String, String>>>> groupMatchs = new HashMap<String, Map<Date, ArrayList<HashMap<String, String>>>>();
		groupMatchs.put("matchsEnded", matchEnded);
		groupMatchs.put("matchs", matchs);
		
		request.setAttribute("group", groupMatchs);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		String stage = request.getParameter("stage");
		if (stage != null)
		{
			/** Competition Stage */
			int idCompetition = 1; //TODO
			_setInfoGroup(request, stage, idCompetition, currentUser);
			
			/** Selected group */
			request.setAttribute("selectedStage", stage);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/finalStage-part.jsp" ).forward(request, response);
		}
	}
	
}
