package com.barbyBet.tools;

public class UsersUtils {
	public UsersUtils() {
		
	}
	
	public int convertGenderFromStringToInt(String gender) {
		if("man".equals(gender)) {
			return 0;
		} else if("woman".equals(gender)) {
			return 1;
		} else if("other".equals(gender)) {
			return 2;
		}
		
		return 2;
	}
	
	public String convertGenderFromIntToString(int gender) {
		if(gender == 0) {
			return "Homme";
		} else if(gender == 1) {
			return "Femme";
		} else {
			return "Non indiqué";
		}
	}

	public int convertPronosPublicsFromStringToInt(String myPronos) {
		if("yes".equals(myPronos)) {
			return 1;
		} else {
			return 0;
		}
	}
}
