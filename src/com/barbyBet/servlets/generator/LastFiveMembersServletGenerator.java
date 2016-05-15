package com.barbyBet.servlets.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.object.Group;

/**
 * Servlet implementation class LastFiveMembersServletGenerator
 */
@WebServlet("/LastFiveMembersServletGenerator")
public class LastFiveMembersServletGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VIEW_LAST_FIVE_MEMBERS = "/WEB-INF/jsp/last-five-members.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LastFiveMembersServletGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupIdStr = request.getParameter("groupId");
		
		if(groupIdStr != null) {
			Long groupId = Long.parseLong(groupIdStr);
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			
			Group group = sqlGroupComponent.getGroup(groupId);
			
			// Get the last five added users (the sorting should have been done in the "getGroup" function in SQLGroupComponent)
			List<HashMap<String, String>> lastFiveMembers = new ArrayList<HashMap<String, String>>();
			int max = group.getMembers().size();
			if(max > 5) {
				max = 5;
			}
			
			for(int i = 0; i < max; i++) {
				lastFiveMembers.add(group.getMembers().get(i).toHashMap());
			}
			
			request.setAttribute("lastFiveMembers", lastFiveMembers);
			
			this.getServletContext().getRequestDispatcher(VIEW_LAST_FIVE_MEMBERS).forward(request, response);
		}					
	}
}
