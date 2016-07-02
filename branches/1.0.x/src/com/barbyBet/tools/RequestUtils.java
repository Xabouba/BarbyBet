package com.barbyBet.tools;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String getParameter(HttpServletRequest request, String param, String defaultValue)
	{
		String result = request.getParameter(param);
		if (result == null)
		{
			result = defaultValue;
		}
		
		return result;
	}
}
