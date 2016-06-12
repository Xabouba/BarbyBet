package com.barbyBet.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.MatchStatus;

/**
 * Servlet implementation class AdminMatchServlet
 */
@WebServlet("/AdminMatchServlet")
public class AdminMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		String league = request.getParameter("league");
		
		if(currentUser.getId() == null) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {
			if(currentUser.getId() != 1) {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/login.jsp" ).forward(request, response);
			} else {
				if(league != null) {
					SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
					Match match = sqlMatchComponent.getBarbyDevMatch();
					
					if(match != null) {
						String msgInfo = "Info";
						switch (match.getStatut()) {
						    case MatchStatus.NOT_STARTED:
						    	msgInfo = "A jouer";
						    	break;
						    case MatchStatus.FIRST_HALF:
						    	msgInfo = "1ère période";
						    	break;
						    case MatchStatus.HALFTIME:
						    	msgInfo = "Mi-temps";
						    	break;
						    case MatchStatus.SECOND_HALF:
						    	msgInfo = "2ème période";
						    	break;
						    case MatchStatus.OVERTIME:
						    	msgInfo = "Prolongation";
						    	break;
						    case MatchStatus.PENALTY:
						    	msgInfo = "Penalty";
						    	break;
						    case MatchStatus.ENDED:
						    	msgInfo = "Terminé";
						    	break;
						    default:
						    	break;
					    }
						
						request.setAttribute("homeTeam", match.getHomeTeam().getName());
						request.setAttribute("awayTeam", match.getAwayTeam().getName());
						request.setAttribute("homeScore", match.getHomeScore());
						request.setAttribute("awayScore", match.getAwayScore());
						request.setAttribute("matchStatus", msgInfo);
						
						this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/adminMatchDev.jsp" ).forward(request, response);
					}
				} else {
					SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
					SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
					Date dateToday = new Date(); //TODO
					ArrayList<Match> matchsSql = sqlMatchComponent.getMatchs(dateToday, MatchStatus.ALL);
					
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
						
						if (match.getStatut() == MatchStatus.ENDED)
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
					
					this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/admin-match.jsp" ).forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matchId = request.getParameter("matchId");
		String scoreHome = request.getParameter("score-home");
		String scoreAway = request.getParameter("score-away");
		
		Connection connection = null;
		PreparedStatement stmt = null;
		String _url = "jdbc:mysql://localhost:3306/barbybet";
		String _user = "root";
		String _password = "malikloic";
		
		try {
		    connection = DriverManager.getConnection(_url, _user, _password);

		    stmt = connection.prepareStatement("UPDATE Matchs SET scoreH = ?, scoreA = ?, statut = ? WHERE id = ?");

			stmt.setInt(1, Integer.parseInt(scoreHome));
		    stmt.setInt(2, Integer.parseInt(scoreAway));
		    stmt.setInt(3, MatchStatus.ENDED);
		    stmt.setLong(4, Long.parseLong(matchId));
		    
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) {
		        	log(e.getMessage(),e);
		        }
		    }
			
			if (connection != null) {
		        try {
		        	connection.close();
		        } catch (SQLException e) {
		        	log(e.getMessage(),e);
		        }
		    }
		}
		
		doGet(request, response);
	}
}
