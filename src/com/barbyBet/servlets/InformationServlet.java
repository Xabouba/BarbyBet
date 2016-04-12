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

import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.Team;
import com.barbyBet.object.User;
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

		if(currentUser == null) {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/login.jsp" ).forward(request, response);
		} else {
			SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			
			int idCompetition = 1; //TODO
			ArrayList<Match> matchsSql = sqlMatchComponent.getMatchsFromCompetition(idCompetition);
			List<Match> groupe1 = new ArrayList<Match>();
			List<Match> groupe2 = new ArrayList<Match>();
			List<Match> groupe3 = new ArrayList<Match>();
			
			Map<String, List<Match>> groups = new HashMap<String, List<Match>>();
			for (Match match : matchsSql)
			{
				if (match.getJournee() < 12)
				{
					if (match.getAwayTeam().getId() < 5 && match.getHomeTeam().getId() < 5)
					{
						groupe1.add(match);
					}
					else if (match.getAwayTeam().getId() > 4 && match.getHomeTeam().getId() > 4 && match.getAwayTeam().getId() < 9 && match.getHomeTeam().getId() < 9)
					{
						groupe2.add(match);
					}
					else if (match.getAwayTeam().getId() > 8 && match.getHomeTeam().getId() > 8)
					{
						groupe3.add(match);
					}
				}
			}
			
			groups.put("Groupe A", groupe1);
			groups.put("Groupe B", groupe2);
			groups.put("Groupe C", groupe3);

			Map<String, Map<String, Map<Date, ArrayList<HashMap<String, String>>>>> matchsGroup = new TreeMap<String, Map<String, Map<Date, ArrayList<HashMap<String, String>>>>>();
			Map<String, Map<Integer, HashMap<String, Object>>> ranksGroup = new TreeMap<String, Map<Integer, HashMap<String, Object>>>();
			for (String group : groups.keySet())
			{
				Map<Date, ArrayList<HashMap<String, String>>> matchEnded = new TreeMap<Date, ArrayList<HashMap<String, String>>>();
				Map<Date, ArrayList<HashMap<String, String>>> matchs = new TreeMap<Date, ArrayList<HashMap<String, String>>>();
				HashMap<Integer, HashMap<String, Object>> rank = new HashMap<Integer, HashMap<String, Object>>();
				
				Date day = new Date();
				
				for (Match match : groups.get(group))
				{
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(match.getBeginDate().getTime());

					HashMap<String, String> pronoMap = sqlPronoComponent.getProno(match.getId(), currentUser.getId());
					_initializeStatTeam(match.getHomeTeam(), rank);
					_initializeStatTeam(match.getAwayTeam(), rank);
					
					if (match.getStatut() > 2)
					{
						ArrayList<HashMap<String, String>> list;
						if (day.equals(calendar.getTime()))
						{
							list = matchEnded.get(calendar.getTime());
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
		
							matchEnded.put(day, list);
						}
					}
					else
					{
						ArrayList<HashMap<String, String>> list;
						if (day.equals(calendar.getTime()))
						{
							list = matchs.get(calendar.getTime().getTime());
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
				}
		
				HashMap<String, Map<Date, ArrayList<HashMap<String, String>>>> groupMatchs = new HashMap<String, Map<Date, ArrayList<HashMap<String, String>>>>();
				groupMatchs.put("matchsEnded", matchEnded);
				groupMatchs.put("matchs", matchs);
				
				ranksGroup.put(group, _orderRankMap(rank));
				matchsGroup.put(group, groupMatchs);
			}
			
			request.setAttribute("groups", matchsGroup);
			request.setAttribute("ranks", ranksGroup);
			
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/information.jsp" ).forward(request, response);
		}
	}

	private Map<Integer, HashMap<String, Object>> _orderRankMap(HashMap<Integer, HashMap<String, Object>> rank) 
	{
		TreeMap<Integer, HashMap<String, Object>> treeMap = new TreeMap<Integer, HashMap<String,Object>>();
		for (HashMap<String, Object> map : rank.values())
		{
			int win = (Integer) map.get("win");
			int draw = (Integer) map.get("draw");
			int goal = (Integer) map.get("goal");
			int taken = (Integer) map.get("taken");
			
			int point = (3 * win + draw) * 10000 + (goal - taken) * 100 + goal;
			treeMap.put(point, map);
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
