package com.barbyBet.tools;

public class WebServiceUtil {

	public WebServiceUtil() {
		
	}
	
	public static int createStatus(String webServiceStatus) {
		int status = MatchStatus.NOT_STARTED;
		
		if(webServiceStatus != null) {
			if("Finished".equals(webServiceStatus)) {
				status = MatchStatus.ENDED;
			} else if("Not started".equals(webServiceStatus) || "Postponed".equals(webServiceStatus)) {
				status = MatchStatus.NOT_STARTED;
			} else {
				// TODO 
				if("Halftime".equals(webServiceStatus)) {
					status = MatchStatus.HALFTIME;
				} else {
					webServiceStatus = webServiceStatus.replace("'", "");
					Long minuteOfMatch = Long.parseLong(webServiceStatus);
					Long firstHalfMinuteLimit = 45L;
					Long secondHalfMinuteLimit = 90L;
					
					if(minuteOfMatch > 0 && minuteOfMatch <= firstHalfMinuteLimit) {
						status = MatchStatus.FIRST_HALF;
					} else if(minuteOfMatch > firstHalfMinuteLimit && minuteOfMatch <= secondHalfMinuteLimit) {
						status = MatchStatus.SECOND_HALF;
					} else {
						status = MatchStatus.OVERTIME;
					}
				}
			}
		}
		
		return status;
	}
}
