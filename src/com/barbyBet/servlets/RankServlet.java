package com.barbyBet.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class RankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public RankServlet() {
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
			/** Classement */
			setRankRequest(request, currentUser, null, 1, 25);
				
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/rank.jsp" ).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		int page = 1;
		if (request.getParameter("page") != null)
		{
			page = Integer.valueOf(request.getParameter("page"));
		}
		
		Long idGroup = null;
		if (request.getParameter("group") != null && !request.getParameter("group").equals("all"))
		{
			idGroup = Long.parseLong((request.getParameter("group")));
		}
		/** Classement */
		setRankRequest(request, currentUser, idGroup, page, 25);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/rank-part.jsp" ).forward(request, response);
	}
	
	protected void setRankRequest(HttpServletRequest request, User currentUser, Long groupId, int page, int nbUser)
	{
		RankComponent rankComponent = new RankComponent();
		request.setAttribute("rank", rankComponent.getRank(groupId, currentUser.getUsername(), page, nbUser));
		request.setAttribute("page", page);
		
		int totalUser = rankComponent.getSize(groupId);
		int nbPage = (int) Math.round(Math.ceil((float) totalUser / nbUser));
		request.setAttribute("totalUser", totalUser);
		request.setAttribute("totalPage", nbPage);
		request.setAttribute("pagination", getPagination(page, nbPage));
	}
	
	protected Map<Integer, Map<String, String>> getPagination(int page, int totalPage)
	{
		Map<Integer, Map<String, String>> pagination = new LinkedHashMap<Integer, Map<String,String>>();
		
		Map<String, String> attribut = new HashMap<String, String>();
		if (page > 4)
		{
			attribut.put("hasNext", "false");
		}
		if (1 == page)
		{
			attribut.put("current", "true");
		}
		pagination.put(1, attribut);
		
		for (int i = 0; i < 5; i++)
		{
			attribut = new HashMap<String, String>();
			int index = page - 2 + i;
			if (index > 1 && index < totalPage)
			{
				if (i == 4 && index != totalPage - 1)
				{
					attribut.put("hasNext", "false");
				}
				else
				{
					attribut.put("hasNext", "true");
				}
				
				if (index == page)
				{
					attribut.put("current", "true");
				}
				
				pagination.put(index, attribut);
			}
		}
		
		attribut = new HashMap<String, String>();
		attribut.put("hasNext", "false");
		attribut.put("end", "true");
		if (totalPage == page)
		{
			attribut.put("current", "true");
		}
		pagination.put(totalPage, attribut);
		
		return pagination;
	}
	
}
