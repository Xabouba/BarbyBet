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
import com.barbyBet.tools.MatchStatus;
import com.barbyBet.tools.RequestUtils;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class InformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public InformationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);

		if(currentUser.getId() == null) {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/login.jsp" ).forward(request, response);
		} else {
			/** Competition Group Name */
			String[] groupNames = {"A", "B", "C", "D", "E", "F"};
			request.setAttribute("groupNames", groupNames);

			/** Competition Group */
			int idCompetition = 1; //TODO
			_setInfoGroup(request, "A", idCompetition, currentUser);
			
			/** Selected group */
			request.setAttribute("selectedGroup", "A");
			
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
			
			/** User group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			request.setAttribute("userGroups", sqlGroupComponent.getGroups(currentUser.getId()));
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/information.jsp" ).forward(request, response);
		}
	}

	private void _setInfoGroup(HttpServletRequest request, String groupName, int idCompetition, User currentUser)
	{
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
		SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
		
		List<Match> matchByGroup = sqlMatchComponent.getMatchByGroup(idCompetition, groupName);

		Map<Date, ArrayList<HashMap<String, String>>> matchEnded = new TreeMap<Date, ArrayList<HashMap<String, String>>>();
		Map<Date, ArrayList<HashMap<String, String>>> matchs = new TreeMap<Date, ArrayList<HashMap<String, String>>>();
		HashMap<Integer, HashMap<String, Object>> rank = new HashMap<Integer, HashMap<String, Object>>();
		
		Date day = new Date();
		
		for (Match match : matchByGroup)
		{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(match.getBeginDate().getTime());

			HashMap<String, String> pronoMap = sqlPronoComponent.getProno(match.getId(), currentUser.getId());
			_initializeStatTeam(match.getHomeTeam(), rank);
			_initializeStatTeam(match.getAwayTeam(), rank);

			ArrayList<HashMap<String, String>> list;
			if (day.equals(calendar.getTime()))
			{
				list = matchs.get(calendar.getTime());
				HashMap<String,String> matchMap = match.toHashMap();
				matchMap.putAll(pronoMap);
				
				list.add(matchMap);
			}
			else
			{
				day = calendar.getTime();
				list = new ArrayList<HashMap<String,String>>();
				
				HashMap<String,String> matchMap = match.toHashMap();
				matchMap.putAll(pronoMap);
				list.add(matchMap);
				matchs.put(day, list);
			}
			
			int homeTeam = match.getHomeTeam().getId();
			int awayTeam = match.getAwayTeam().getId();
			
			_setHomeTeamStats(homeTeam, match, rank);
			_setAwayTeamStats(awayTeam, match, rank);
		}

		HashMap<String, Map<Date, ArrayList<HashMap<String, String>>>> groupMatchs = new HashMap<String, Map<Date, ArrayList<HashMap<String, String>>>>();
		groupMatchs.put("matchsEnded", matchEnded);
		groupMatchs.put("matchs", matchs);
		
		request.setAttribute("group", groupMatchs);
		request.setAttribute("ranks", _orderRankMap(rank));
	}
	
	
	private Map<String, HashMap<String, Object>> _orderRankMap(HashMap<Integer, HashMap<String, Object>> rank) 
	{
		TreeMap<String, HashMap<String, Object>> treeMap = new TreeMap<String, HashMap<String,Object>>();
		for (Integer key : rank.keySet())
		{ 
			HashMap<String, Object> map = rank.get(key);
			int win = (Integer) map.get("win");
			int draw = (Integer) map.get("draw");
			int goal = (Integer) map.get("goal");
			int taken = (Integer) map.get("taken");
			
			double point = (3 * win + draw) * 10000 + (goal - taken) * 100 + goal;
			treeMap.put(point + "_" + key, map);
		}
		
		return treeMap.descendingMap();
	}

	private void _setHomeTeamStats(int idHomeTeam, Match match, HashMap<Integer, HashMap<String, Object>> rank) 
	{
		if (!rank.containsKey(idHomeTeam))
		{
			rank.put(idHomeTeam, new HashMap<String, Object>());
		}
		HashMap<String, Object> homeTeamStats = rank.get(idHomeTeam);
		
		if (match.getStatut() == MatchStatus.ENDED)
		{
			if (match.getHomeScore() > match.getAwayScore())
			{
				homeTeamStats.put("win", _incrementStat("win", 1, homeTeamStats));
			}
			else if (match.getHomeScore() < match.getAwayScore())
			{
				homeTeamStats.put("lost", _incrementStat("lost", 1, homeTeamStats));
			}
			else
			{
				homeTeamStats.put("draw", _incrementStat("draw", 1, homeTeamStats));
			}
			homeTeamStats.put("goal", _incrementStat("goal", match.getHomeScore(), homeTeamStats));
			homeTeamStats.put("taken", _incrementStat("taken", match.getAwayScore(), homeTeamStats));
		}
	}
	
	private void _setAwayTeamStats(int idAwayTeam, Match match, HashMap<Integer, HashMap<String, Object>> rank) 
	{
		if (!rank.containsKey(idAwayTeam))
		{
			rank.put(idAwayTeam, new HashMap<String, Object>());
		}
		HashMap<String, Object> awayTeamStats = rank.get(idAwayTeam);
		
		if (match.getHomeScore() < match.getAwayScore())
		{
			awayTeamStats.put("win", _incrementStat("win", 1, awayTeamStats));
		}
		else if (match.getHomeScore() > match.getAwayScore())
		{
			awayTeamStats.put("lost", _incrementStat("lost", 1, awayTeamStats));
		}
		else
		{
			awayTeamStats.put("draw", _incrementStat("draw", 1, awayTeamStats));
		}
		awayTeamStats.put("goal", _incrementStat("goal", match.getAwayScore(), awayTeamStats));
		awayTeamStats.put("taken", _incrementStat("taken", match.getHomeScore(), awayTeamStats));
	}

	private int _incrementStat(String stat, int value, HashMap<String, Object> homeTeamStats) 
	{
		int statValue = 0;
		if (homeTeamStats.containsKey(stat))
		{
			statValue = (Integer) homeTeamStats.get(stat);
		}
		
		return statValue + value;
	}
	
	private void _initializeStatTeam(Team team, HashMap<Integer, HashMap<String, Object>> rank)
	{
		if (!rank.containsKey(team.getId()))
		{
			HashMap<String, Object> statMap = new HashMap<String, Object>();
			statMap.put("name", team.getName());
			statMap.put("img", team.getImg());
			statMap.put("win", 0);
			statMap.put("lost", 0);
			statMap.put("draw", 0);
			statMap.put("goal", 0);
			statMap.put("taken", 0);
			
			rank.put(team.getId(), statMap);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		String groupName = request.getParameter("group");
		if (groupName != null)
		{
			/** Competition Group Name */
			String[] groupNames = {"A", "B", "C", "D", "E", "F"};
			request.setAttribute("groupNames", groupNames);

			/** Competition Group */
			int idCompetition = 1; //TODO
			_setInfoGroup(request, groupName, idCompetition, currentUser);

			/** Selected group */
			request.setAttribute("selectedGroup", groupName);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/information-part.jsp" ).forward(request, response);
		}
	}
	
}
