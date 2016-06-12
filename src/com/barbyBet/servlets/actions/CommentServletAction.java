package com.barbyBet.servlets.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.SQLCommentComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;

/**
 * Servlet implementation class SaxResultGenerator
 */
public class CommentServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public CommentServletAction() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		SQLCommentComponent sqlCommentComponent = new SQLCommentComponent();

		String comment = request.getParameter("comment");
		if (comment != null  && comment != "")
		{
			long matchId = Long.parseLong(request.getParameter("matchId"));
			
			sqlCommentComponent.insertComment(comment.replace("\n", "<br/>"), matchId , currentUser.getId());
		}
		
		String refresh = request.getParameter("refresh");
		if (refresh != null)
		{
			long matchId = Long.parseLong(request.getParameter("matchId"));
			
			/** Commentaires */
			ArrayList<HashMap<String, String>> comments = sqlCommentComponent.getComments(matchId);
			request.setAttribute("comments", comments);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/chat.jsp" ).forward(request, response);
		}
	}
}