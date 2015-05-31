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
import com.barbyBet.object.Match;

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
		
		ArrayList<Match> matchsSql = sqlMatchComponent.getMatchs();
		
		ArrayList<HashMap<String, String>> matchEnded = new ArrayList<HashMap<String,String>>();
		HashMap<String,ArrayList<HashMap<String, String>>> matchs = new HashMap<String, ArrayList<HashMap<String,String>>>();
		String day = "";
		for (Match match : matchsSql)
		{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(match.getBeginDate().getTime());
			
			if (match.getStatut() > 4)
			{
				matchEnded.add(match.toHashMap());
			}
			else
			{
				ArrayList<HashMap<String, String>> list;
				if (day.equals(calendar.getTime().toString()))
				{
					list = matchs.get(calendar.getTime().toString());
					list.add(match.toHashMap());
				}
				else
				{
					day = calendar.getTime().toString();
					list = new ArrayList<HashMap<String,String>>();
					list.add(match.toHashMap());

					matchs.put(calendar.getTime().toString(), list);
				}
			}
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
