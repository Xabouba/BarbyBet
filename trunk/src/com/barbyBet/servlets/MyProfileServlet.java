package com.barbyBet.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbyBet.components.RankComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.User;
import com.barbyBet.tools.Constants;
import com.barbyBet.tools.DateUtil;
import com.barbyBet.tools.UsersUtils;

/**
 * Servlet implementation class MyProfileServlet
 */
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);

		if(!usersComponent.isCurrentUser(currentUser)) {
			response.sendRedirect(Constants.LOGIN_SERVLET);
		} else {	
			/** Classement */
			RankComponent rankComponent = new RankComponent();
			request.setAttribute("rank", rankComponent.getMinimizedRank(null, currentUser.getUsername()));
			
			request.setAttribute("currentUser", currentUser.toHashMap());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/my-profile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersComponent usersComponent = new UsersComponent();
		User currentUser = usersComponent.getCurrentUser(request);
		
		String genderStr = request.getParameter("gender");
		String myPronos = request.getParameter("my-pronos");
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		String birthDay = request.getParameter("birth-day");
		String birthMonth = request.getParameter("birth-month");
		String birthYear = request.getParameter("birth-year");
		String birthdayStr = "";
		// If the provided birthdate is not empty
		if(!"0".equals(birthDay) && !"0".equals(birthMonth) && !"0".equals(birthYear)) {
			birthdayStr = birthDay + "/" + birthMonth + "/" + birthYear;
		}
		Date birthday = null;
		if(!birthdayStr.isEmpty()) {
			try {
				birthday = DateUtil.SHORT_DATE_FORMAT_FRANCE.parse(birthdayStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String location = request.getParameter("location");
		String website = request.getParameter("website");
		String biography = request.getParameter("biography");
		
		UsersUtils usersUtils = new UsersUtils();
		int gender = usersUtils.convertGenderFromStringToInt(genderStr);
		int pronosPublics = usersUtils.convertPronosPublicsFromStringToInt(myPronos);
		
		currentUser.setGender(gender);
		currentUser.setPronosPublics(pronosPublics);
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setEmail(email);
		currentUser.setBirthday(birthday);
		currentUser.setLocation(location);
		currentUser.setWebsite(website);
		currentUser.setBiography(biography);
		
		SQLUsersComponent sqlUsersComponent = new SQLUsersComponent();
		sqlUsersComponent.updateUser(currentUser);
		
		response.sendRedirect("/myProfile");
	}
}
