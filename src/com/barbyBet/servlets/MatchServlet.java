package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLMatchComponent;

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
		
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
		
		ArrayList<HashMap<String, String>> matchsSql = sqlMatchComponent.getMatchs();
		
		ArrayList<HashMap<String, String>> matchEnded = new ArrayList<HashMap<String,String>>();
		HashMap<String,ArrayList<HashMap<String, String>>> matchs = new HashMap<String, ArrayList<HashMap<String,String>>>();
		String day = "";
		for (HashMap<String, String> match : matchsSql)
		{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(Long.valueOf(match.get("dateTime")));
			
			if (Boolean.parseBoolean(match.get("isEnded")))
			{
				matchEnded.add(match);
			}
			else
			{
				ArrayList<HashMap<String, String>> list;
				if (day.equals(calendar.getTime().toString()))
				{
					list = matchs.get(calendar.getTime().toString());
					list.add(match);
				}
				else
				{
					day = calendar.getTime().toString();
					list = new ArrayList<HashMap<String,String>>();
					list.add(match);

					matchs.put(calendar.getTime().toString(), list);
				}
			}
//			System.out.println(calendar.getTime());
//			calendar.set
//			calendar.setTime(arg0);
//			/** Match Information */
//		    HashMap<String, String> matchInfo = new HashMap<String, String>();
//		    matchInfo.put("homeTeam", match.get("teamH"));
//		    matchInfo.put("awayTeam", match.get("teamA"));
//		    matchInfo.put("homeImg", match.get("imgH"));
//		    matchInfo.put("awayImg", match.get("imgA"));
//		    matchInfo.put("homeScore", match.get("scoreH"));
//		    matchInfo.put("awayScore", match.get("scoreA"));
//		    matchInfo.put("matchId", match.get("matchId"));
		}

		request.setAttribute("matchsEnded", matchEnded);
		request.setAttribute("matchs", matchs);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/match.jsp" ).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
}
