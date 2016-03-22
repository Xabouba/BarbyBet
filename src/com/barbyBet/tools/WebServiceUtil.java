package com.barbyBet.tools;

public class WebServiceUtil {

	private static int STATUS_GAME_NOT_STARTED = 0;
	private static int STATUS_GAME_CURRENTLY_PLAYING = 1;
	private static int STATUS_GAME_FINISHED = 2;
	private static int STATUS_GAME_POSTPONED = 3;
	
	
	public WebServiceUtil() {
		
	}
	
	public static int createStatus(String time) {
		int status = STATUS_GAME_NOT_STARTED;
		
		if(time != null) {
			if("Finished".equals(time)) {
				status = STATUS_GAME_FINISHED;
			} else if("Postponed".equals(time)) {
				status = STATUS_GAME_POSTPONED;
			} else {
				status = STATUS_GAME_CURRENTLY_PLAYING;
			}
		}
		
		return status;
	}
}
