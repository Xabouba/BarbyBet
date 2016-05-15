package com.barbyBet.tools;

public class MatchStatus {
	public static final int ALL = 0;
	public static final int ENDED 		= 1;
	
	// Matchs that are currently playing & matches that did not start yet
	public static final int NOT_ENDED 	= 2;

	public static final int NOT_STARTED = 3;
	// Matches that are currently playing
	public static final int CURRENT = 4;

	public static final int FIRST_HALF 	= 5;
	public static final int HALFTIME 	= 6;
	public static final int SECOND_HALF = 7;
	public static final int OVERTIME 	= 8;
	public static final int PENALTY 	= 9;
}
