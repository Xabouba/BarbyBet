package com.barbyBet.tools;

public class Constants {

	public static final String ATT_ERRORS 		= "errorsConnect";
	public static final String ATT_USERNAME 	= "usernameConnect";
	public static final String VUE_ERROR 		= "/WEB-INF/jsp/login.jsp";
	public static final String VUE_SUCCESS 		= "/WEB-INF/jsp/index.jsp";
	public static final String CHAMP_MEMOIRE   	= "memoire";
	public static final String CHAMP_USERNAME	= "username";
	public static final int    COOKIE_MAX_AGE  	= 60 * 60 * 24 * 365;  // 1 an
	public static final String ATT_INTERVALLE_CONNEXIONS = "intervalleConnexions";
	public static final String ATT_SESSION_USER          = "sessionUser";
	public static final String FORMAT_DATE               = "dd/MM/yyyy HH:mm:ss";
	public static final String NOT_CONNECTED_ERROR = "Vous n'êtes pas connecté!";
	public static final int ADMIN = 1;
	public static final String GROUP_PICS_ROOT_FOLDER = "/media/group-pics";

	
	// Current user cookies
	public static final String COOKIE_CURRENT_USER_ID                 = "currentUserId";
	public static final String COOKIE_CURRENT_USER_NAME               = "currentUserName";
	public static final String COOKIE_CURRENT_USER_EMAIL              = "currentUserEmail";
	public static final String COOKIE_CURRENT_USER_REGISTRATION_DATE  = "currentUserRegistrationDate";
	public static final String COOKIE_CURRENT_USER_NUMBER_OF_COINS    = "currentUserNumberOfCoins";
}
