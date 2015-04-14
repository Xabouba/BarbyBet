package com.barbyBet.servlets;

import java.awt.Component;
import java.io.IOException;

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
		try 
		{
			Match match = saxComponent.parseMatch("20150419", "Lyon");
//			Match match = saxComponent.parseMatch("20150413", "Angers");
			
			request.setAttribute("homeTeam", match.getHomeTeam());
			request.setAttribute("awayTeam", match.getAwayTeam());
			request.setAttribute("homeScore", match.getHomeScore());
			request.setAttribute("awayScore", match.getAwayScore());
			
			SQLComponent sqlComponent = new SQLComponent();
			request.setAttribute("homeImg", sqlComponent.getImgEquipe("Lyon"));
			request.setAttribute("awayImg", sqlComponent.getImgEquipe("Saint-Etienne"));
			
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
			request.setAttribute("statut", msgInfo);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/test.jsp" ).forward( request, response );
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
