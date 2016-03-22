package com.barbyBet.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			HashMap<String,ArrayList<HashMap<String, String>>> matchs = new HashMap<String, ArrayList<HashMap<String,String>>>();
			HashMap<String,ArrayList<HashMap<String, String>>> matchsToday = new HashMap<String, ArrayList<HashMap<String,String>>>();
			
			String day = "";
			String hour = "";
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMMMMMMM - HH:mm");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
			SimpleDateFormat dayFormat = new SimpleDateFormat("d MMMMMMMMM");
	//		GregorianCalendar calendarsss = new GregorianCalendar(2015, 11, 05);
	//		System.out.println(calendarsss.getTime());
			String today = dayFormat.format(dateToday);
			for (Match match : matchsSql)
			{
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(match.getBeginDate().getTime());
				
				HashMap<String, String> pronoMap = sqlPronoComponent.getProno(match.getId(), currentUser.getId());
				
				if (match.getStatut() > 4)
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
							list = matchsToday.get(hourFormat.format(calendar.getTime()));
							
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
		
							matchsToday.put(hour, list);
						}
					}
					else
					{
						ArrayList<HashMap<String, String>> list;
						if (day.equals(dateFormat.format(calendar.getTime())))
						{
							list = matchs.get(dateFormat.format(calendar.getTime()));
							HashMap<String,String> matchMap = match.toHashMap();
							matchMap.putAll(pronoMap);
							
							list.add(matchMap);
						}
						else
						{
							day = dateFormat.format(calendar.getTime());
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
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/match.jsp" ).forward(request, response);
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
			int matchId = Integer.parseInt(matchIdAsString);
			
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			sqlPronoComponent.pronostic(matchId, currentUser.getId(), scoreHome, scoreAway, prono, credits);
		
//			doGet(request, response);
		}
	}
	
}
