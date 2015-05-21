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

import com.barbyBet.components.SQLCommentComponent;
import com.barbyBet.components.SQLComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.SaxComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.User;

public class DirectResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//TODO
	private static String idUser = "3";
	
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
			ArrayList<HashMap<String, String>> matchs = sqlComponent.getMatchs();
			
			HashMap<String, String> matchSql = matchs.get(3);
			
		    String team = matchSql.get("teamH");
		    String date = matchSql.get("date");
		    String sport = matchSql.get("sport");
			String competition = matchSql.get("competition");

		    Match match = saxComponent.parseMatch(date, team); 
		    match.setCompetition(competition);
		    match.setSport(sport);
		    
		    
		    // Récupérer les matchs de la journée et boucler sur ces matchs pour récupérer les cotes 
			// Il faut donc créer une table qui contient le nom de chaque competition 
			// (une colonne avec le nom d'affichage, une avec le nom de skysport et une avec le nom de betclic)
			// Il faut aussi modifier la table des equipes en rajoutant les noms d'équipes de betclic
			
			
			// Fonction qui va chercher les cotes par match et les update directement dans l'objet Match
			saxComponent.parseOdds(match);
			System.out.println(match.getOdds());

			// Enregistrer les odds dans la BDD pour le match en question
			
		    
		    /** Match Information */
		    HashMap<String, String> matchInfo = new HashMap<String, String>();
		    matchInfo.put("homeTeam", match.getHomeTeam());
		    matchInfo.put("awayTeam", match.getAwayTeam());
		    matchInfo.put("homeScore", match.getHomeScore());
		    matchInfo.put("awayScore", match.getAwayScore());
		    matchInfo.put("homeImg", matchSql.get("imgH"));
		    matchInfo.put("awayImg", matchSql.get("imgA"));
		    matchInfo.put("matchId", matchSql.get("matchId"));
		    matchInfo.put("homeOdd", match.getOdds().getHomeOdd() + "");
		    matchInfo.put("drawOdd", match.getOdds().getDrawOdd() + "");
		    matchInfo.put("awayOdd", match.getOdds().getAwayOdd() + "");
		    
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
			request.setAttribute("match", matchInfo);
			
			/** Pronostic */
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			HashMap<String, String> prono = sqlPronoComponent.getProno(matchSql.get("matchId"), idUser);
			
			request.setAttribute("prono", prono.get("prono"));
			request.setAttribute("credits", prono.get("credits"));
			request.setAttribute("creditsWon", prono.get("creditsWon"));
			
			/** Commentaires */
			SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
			ArrayList<HashMap<String, String>> comments = sqlCommentComponent.getComments(matchSql.get("matchId"));
			request.setAttribute("comments", comments);
			
//			User connectedUser = null;
//			SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
//			connectedUser = sqlUsersComponent.isUserRegistered(request);
//			if (connectedUser != null)
//			{
//				request.setAttribute("user", connectedUser.getUsername());
//			}
			
			
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
		if (comment != null  && comment != "")
		{
			String matchId = request.getParameter("matchId");
			
			SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
			sqlCommentComponent.insertComment(comment.replace("\n", "<br/>"), matchId , idUser);

			doGet(request, response);
		}
		
		String credits = request.getParameter("credits");
		if (credits != null && credits != "")
		{
			String prono = request.getParameter("prono");
			String matchId = request.getParameter("matchId");
			
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			sqlPronoComponent.pronostic(matchId, idUser, prono, credits);
		
//			doGet(request, response);
		}
	}
	
}
