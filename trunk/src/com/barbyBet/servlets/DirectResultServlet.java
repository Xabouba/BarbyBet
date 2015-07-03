package com.barbyBet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLCommentComponent;
import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.object.Match;

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
//		SaxComponent saxComponent = new SaxComponent();
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
//		try 
//		{
			String matchId = request.getParameter("matchId");
			if (matchId == null)
			{
				matchId = "1";
			}
			Match match = sqlMatchComponent.getMatch(matchId);
			
//		    Match match = saxComponent.parseMatch(date, team); 
//		    match.setCompetition(competition);
//		    match.setSport(sport);
		    
			// Fonction qui va chercher les cotes par match et les update directement dans l'objet Match
//			saxComponent.parseOdds(match);

			// Enregistrer les odds dans la BDD pour le match en question
			
		    request.setAttribute("match", match.toHashMap());
			
			/** Pronostic */
			SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
			HashMap<String, String> prono = sqlPronoComponent.getProno(matchId, idUser);
			
			request.setAttribute("prono", prono.get("prono"));
			request.setAttribute("credits", prono.get("credits"));
			request.setAttribute("creditsWon", prono.get("creditsWon"));
			
			/** Commentaires */
			SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();
			ArrayList<HashMap<String, String>> comments = sqlCommentComponent.getComments(matchId);
			request.setAttribute("comments", comments);
			
//			User connectedUser = null;
//			SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
//			connectedUser = sqlUsersComponent.isUserRegistered(request);
//			if (connectedUser != null)
//			{
//				request.setAttribute("user", connectedUser.getUsername());
//			}
			
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/index.jsp" ).forward(request, response);
//		} 
//		catch (ParserConfigurationException e) 
//		{
//			e.printStackTrace();
//		} 
//		catch (SAXException e) 
//		{
//			e.printStackTrace();
//		}
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
