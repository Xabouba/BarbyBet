package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.barbyBet.components.SQLComponent;
import com.barbyBet.components.SaxComponent;
import com.barbyBet.object.Match;

/**
 * Servlet implementation class SaxResultGenerator
 */
@WebServlet("/SaxResultGenerator")
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
		SaxComponent saxComponent = new SaxComponent();
		SQLComponent sqlComponent = new SQLComponent();
		try 
		{
			String sport = "Football";
			String competition = "Spanish Liga Primera";
			
			ArrayList<HashMap<String, String>> matchs = sqlComponent.getMatchs();
			ArrayList<HashMap<String, String>> matchsInfo = new ArrayList<HashMap<String,String>>();
			
			HashMap<String, String> matchSql = matchs.get(2);
//			for(HashMap<String, String> matchSql : matchs) {
			
			    String team = matchSql.get("teamH");
			    String date = matchSql.get("date");
			    Match match = saxComponent.parseMatch(date, team);
			    
			    // Récupérer les matchs de la journée et boucler sur ces matchs pour récupérer les cotes 
				// Il faut donc créer une table qui contient le nom de chaque competition 
				// (une colonne avec le nom d'affichage, une avec le nom de skysport et une avec le nom de betclic)
				// Il faut aussi modifier la table des equipes en rajoutant les noms d'équipes de betclic
				
				
				// Fonction qui va chercher les cotes par match et les update directement dans l'objet Match
				saxComponent.parseOdds(sport, competition, match);

				// Enregistrer les odds dans la BDD pour le match en question
				
				
				
			    HashMap<String, String> matchInfo = new HashMap<String, String>();
			    matchInfo.put("homeTeam", match.getHomeTeam());
			    matchInfo.put("awayTeam", match.getAwayTeam());
			    matchInfo.put("homeScore", match.getHomeScore());
			    matchInfo.put("awayScore", match.getAwayScore());
			    matchInfo.put("homeImg", matchSql.get("imgH"));
			    matchInfo.put("awayImg", matchSql.get("imgA"));
			    
			    int statut = Integer.parseInt(match.getStatut());
			    String msgInfo = "";
			    switch (statut) {
			    case 0:
			    case 1:
			    	msgInfo = "A jouer";
			    	break;
			    case 2:
			    	msgInfo = "1ère période";
			    	break;
			    case 3:
			    	msgInfo = "Mi-temps";
			    	break;
			    case 4:
			    	msgInfo = "2ème période";
			    	break;
			    case 5:
			    	msgInfo = "Terminé";
			    	break;
			    default:
			    	break;
			    }
			    matchInfo.put("statut", msgInfo);
			    
//			    matchsInfo.add(matchInfo);
//			}
			request.setAttribute("match", matchInfo);
			
			ArrayList<HashMap<String, String>> comments = sqlComponent.getComments();
			request.setAttribute("comments", comments);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/index.jsp" ).forward(request, response);
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String comment = request.getParameter("comment");
		if (comment != "")
		{
			SQLComponent sqlComponent = new SQLComponent();
			sqlComponent.insertComment(comment.replace("\n", "<br/>"));

			doGet(request, response);
		}
		
	}
	
}
