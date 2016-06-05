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
import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLRankComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class RankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int nbUserByPage = 15;
	
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
		
		if(currentUser.getId() == null) {
			response.sendRedirect(Constants.INDEX_SERVLET);
		} else {
			/** User group */
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			Map<Long, Map<String, String>> groups = sqlGroupComponent.getGroups(currentUser.getId());
			request.setAttribute("groups", groups);
			
			Long idGroup = null;
			if (request.getParameter("group") != null && !request.getParameter("group").equals("all"))
			{
				idGroup = Long.parseLong((request.getParameter("group")));
				request.setAttribute("currentGroupId", idGroup);
			}
			
			int index = 1;
			int indexFound = 0;
			for (Long id : groups.keySet())
			{
				if (id == idGroup)
				{
					indexFound = index;
				}
				index++;
			}
			
			if (indexFound == 0)
			{
				request.setAttribute("currentGroupIndex", "general");
			}
			else
			{
				request.setAttribute("currentGroupIndex", String.valueOf(indexFound - 1));
			}

			/** User info */
			SQLUsersComponent sqlUserComponent = new SQLUsersComponent();
			User user = sqlUserComponent.getUser(currentUser.getId());
			request.setAttribute("userPoint", user.getCoins());
			request.setAttribute("userRank", user.getRank());

			int rankUser = user.getRank();
			int pageNumber = 1;
			if (rankUser % nbUserByPage == 0)
			{
				pageNumber = rankUser / nbUserByPage;
			}
			else
			{
				pageNumber = (int) (Math.floor(rankUser/nbUserByPage) + 1);
			}
			
			/** Classement */
			setRankRequest(request, currentUser, idGroup, pageNumber, nbUserByPage);
			
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
		
		Long idGroup = null;
		if (request.getParameter("group") != null && !request.getParameter("group").equals("all"))
		{
			idGroup = Long.parseLong((request.getParameter("group")));
			request.setAttribute("currentGroupId", idGroup);
		}
		
		int page = 1;
		if (request.getParameter("page") != null)
		{
			page = Integer.valueOf(request.getParameter("page"));
		}
		else
		{
			SQLRankComponent rankComponent = new SQLRankComponent();
			Map<String, Map<String, String>> rank = rankComponent.getGroupRank(idGroup);
			int rankUser = Integer.parseInt(rank.get(currentUser.getUsername()).get("rank"));
			if (rankUser % nbUserByPage == 0)
			{
				page = rankUser / nbUserByPage;
			}
			else
			{
				page = (int) (Math.floor(rankUser/nbUserByPage) + 1);
			}
		}
		
		/** Classement */
		setRankRequest(request, currentUser, idGroup, page, nbUserByPage);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/rank-part.jsp" ).forward(request, response);
	}
	
	protected void setRankRequest(HttpServletRequest request, User currentUser, Long groupId, int page, int nbUser)
	{
		if (groupId == null)
		{
			request.setAttribute("groupName", "General");
		}
		else
		{
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			request.setAttribute("groupName", sqlGroupComponent.getGroup(groupId).getName());
		}
		
		RankComponent rankComponent = new RankComponent();
		Map<String, Map<String, String>> rank = rankComponent.getRank(groupId, currentUser.getUsername(), page, nbUser);
		request.setAttribute("rank", rank);
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
