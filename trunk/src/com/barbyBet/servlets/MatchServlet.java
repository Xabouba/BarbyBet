package com.barbyBet.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
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
import com.barbyBet.object.User;
import com.barbyBet.tools.RequestUtils;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class MatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public MatchServlet() {
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
			Date dateToday = new Date(); //TODO
			ArrayList<Match> matchsSql = sqlMatchComponent.getMatchs(dateToday);
			
			ArrayList<HashMap<String, String>> matchEnded = new ArrayList<HashMap<String,String>>();
			Map<Date,ArrayList<HashMap<String, String>>> matchs = new TreeMap<Date, ArrayList<HashMap<String,String>>>();
			Map<Date,ArrayList<HashMap<String, String>>> matchsToday = new TreeMap<Date, ArrayList<HashMap<String,String>>>();
			
			Date day = new Date();
			String hour = "";
			
			Locale locale = new Locale("fr");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm", locale);
			SimpleDateFormat dayFormat = new SimpleDateFormat("d MMMMMMMMM", locale);
			String today = dayFormat.format(dateToday);
			for (Match match : matchsSql)
			{
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(match.getBeginDate().getTime());
				
				HashMap<String, String> pronoMap = sqlPronoComponent.getProno(match.getId(), currentUser.getId());
				
				if (match.getStatut() > 2)
				{
					HashMap<String,String> matchMap = match.toHashMap();
					matchMap.putAll(pronoMap);
					
					matchEnded.add(matchMap);
				}
				else
				{
					if (today.equals(dayFormat.format(calendar.getTime())))
					{
						ArrayList<HashMap<String, String>> list;
						if (hour.equals(hourFormat.format(calendar.getTime())))
						{
							list = matchsToday.get(calendar.getTime());
							if (list == null)
							{
								list = new ArrayList<HashMap<String,String>>();
							}
							
							HashMap<String,String> matchMap = match.toHashMap();
							matchMap.putAll(pronoMap);
									
							list.add(matchMap);
						}
						else
						{
							hour = hourFormat.format(calendar.getTime());
							list = new ArrayList<HashMap<String,String>>();
							
							HashMap<String,String> matchMap = match.toHashMap();
							matchMap.putAll(pronoMap);
							
							list.add(matchMap);
		
							matchsToday.put(calendar.getTime(), list);
						}
					}
					else
					{
						ArrayList<HashMap<String, String>> list;
						if (day.equals(calendar.getTime()))
						{
							list = matchs.get(calendar.getTime());
							if (list == null)
							{
								list = new ArrayList<HashMap<String,String>>();
							}
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
					}
				}
			}
	
			request.setAttribute("matchsEnded", matchEnded);
			request.setAttribute("matchsToday", matchsToday);
			request.setAttribute("matchs", matchs);
			
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
			
			/** Group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			request.setAttribute("userGroups", sqlGroupComponent.getGroups(currentUser.getId()));
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/match.jsp" ).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
}
