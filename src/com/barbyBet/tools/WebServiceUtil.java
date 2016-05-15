package com.barbyBet.tools;

public class WebServiceUtil {

	public WebServiceUtil() {
		
	}
	
	public static int createStatus(String time) {
		int status = MatchStatus.NOT_STARTED;
		
		if(time != null) {
			if("Finished".equals(time)) {
				status = MatchStatus.ENDED;
			} else if("Postponed".equals(time)) {
				status = MatchStatus.NOT_STARTED;
			} else {
				// TODO 
				if("Halftime".equals(time)) {
					status = MatchStatus.HALFTIME;
				} else {
					status = MatchStatus.FIRST_HALF;
				}
			}
		}
		
		return status;
	}
}
