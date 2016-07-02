package com.barbyBet.object;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.barbyBet.tools.MatchStatus;


public class Match {

	private Long _id;
	private Long _idWebService;
	private Long _idSport;
	private Long _idCompetition;
	private Team _homeTeam;
	private Team _awayTeam;
	private int _homeScore;
	private int _awayScore;
	private int _statut;
	private int _journee;
	private String _competition;
	private String _sport;
	private Odds _odds = new Odds(0, 0, 0);
	private Timestamp _beginDate;
	
	public Match()
	{
		
	}
	
	public Long getId()
	{
		return _id;
	}
	
	public void setId(Long id)
	{
		this._id = id;
	}
	
	public Long getIdWebService() {
		return _idWebService;
	}
	
	public void setIdWebService(Long idWebService) {
		this._idWebService = idWebService;
	}
	
	public Team getHomeTeam() 
	{
		return _homeTeam;
	}
	
	public void setHomeTeam(Team homeTeam) 
	{
		this._homeTeam = homeTeam;
	}

	public Team getAwayTeam() 
	{
		return _awayTeam;
	}

	public void setAwayTeam(Team awayTeam) 
	{
		this._awayTeam = awayTeam;
	}

	public int getStatut() 
	{
		return _statut;
	}

	public void setStatut(int statut) 
	{
		this._statut = statut;
	}

	public int getHomeScore() 
	{
		return _homeScore;
	}

	public void setHomeScore(int homeScore) 
	{
		this._homeScore = homeScore;
	}

	public int getAwayScore() 
	{
		return _awayScore;
	}

	public void setAwayScore(int awayScore) 
	{
		this._awayScore = awayScore;
	}
	
	public String getCompetition() 
	{
		return _competition;
	}
	
	public void setCompetition(String competition) 
	{
		_competition = competition;
	}
	
	public String getSport() 
	{
		return _sport;
	}
	
	public void setSport(String sport) 
	{
		_sport = sport;
	}
	
	public Odds getOdds() 
	{
		return _odds;
	}
	
	public void setOdds(Odds odds) 
	{
		_odds = odds;
	}

	public Timestamp getBeginDate()
	{
		return _beginDate;
	}
	
	public void setBeginDate(Timestamp beginDate)
	{
		this._beginDate = beginDate;
	}
	
	public int getJournee() {
		return _journee;
	}

	public void setJournee(int _journee) {
		this._journee = _journee;
	}
	
	public HashMap<String, String> toHashMap()
	{
		/** Match Information */
	    HashMap<String, String> matchInfo = new HashMap<String, String>();
	    matchInfo.put("homeTeam", _homeTeam.getName());
	    matchInfo.put("awayTeam", _awayTeam.getName());
	    matchInfo.put("homeScore", String.valueOf(_homeScore));
	    matchInfo.put("awayScore", String.valueOf(_awayScore));
	    matchInfo.put("homeImg", _homeTeam.getImg());
	    matchInfo.put("awayImg", _awayTeam.getImg());
	    matchInfo.put("matchId", String.valueOf(_id));
	    matchInfo.put("homeOdd", String.valueOf(_odds.getHomeOdd()));
	    matchInfo.put("drawOdd", String.valueOf(_odds.getDrawOdd()));
	    matchInfo.put("awayOdd", String.valueOf(_odds.getAwayOdd()));
	    
	    String msgInfo = "";
	    switch (_statut) {
	    case MatchStatus.NOT_STARTED:
	    	Date matchDate = this.getBeginDate();
	    	Date today = new Date();
	    	Calendar calendarToday = Calendar.getInstance();
	    	calendarToday.setTime(today);
	    	
	    	Calendar calendarMatch = Calendar.getInstance();
	    	calendarMatch.setTime(matchDate);
	    	
	    	boolean sameDay = calendarToday.get(Calendar.YEAR) == calendarMatch.get(Calendar.YEAR) &&
	    			calendarToday.get(Calendar.DAY_OF_YEAR) == calendarMatch.get(Calendar.DAY_OF_YEAR);
	    	
	    	int dayOfTheWeek = calendarMatch.get(Calendar.DAY_OF_WEEK);
	    	String dayOfTheWeekStr = "";
	    	switch(dayOfTheWeek) {
		    	case Calendar.MONDAY:
		    		dayOfTheWeekStr = "Lun.";
		    		break;
		    	case Calendar.TUESDAY:
		    		dayOfTheWeekStr = "Mar.";
		    		break;
		    	case Calendar.WEDNESDAY:
		    		dayOfTheWeekStr = "Mer.";
		    		break;
		    	case Calendar.THURSDAY:
		    		dayOfTheWeekStr = "Jeu.";
		    		break;
		    	case Calendar.FRIDAY:
		    		dayOfTheWeekStr = "Ven.";
		    		break;
		    	case Calendar.SATURDAY:
		    		dayOfTheWeekStr = "Sam.";
		    		break;
		    	case Calendar.SUNDAY:
		    		dayOfTheWeekStr = "Dim.";
		    		break;
		    	default:
		    		break;
	    	}
	    	
	    	int dayOfTheMonthIndex = calendarMatch.get(Calendar.DAY_OF_MONTH);
	    	String dayOfTheMonthStr = String.valueOf(dayOfTheMonthIndex);
	    	if(dayOfTheMonthIndex < 10) {
	    		dayOfTheMonthStr = "0" + dayOfTheMonthIndex;
	    	}
	    	
	    	int month = calendarMatch.get(Calendar.MONTH) + 1;
	    	String monthStr = String.valueOf(month);
	    	if(month < 10) {
	    		monthStr = "0" + month;
	    	}
	    	
	    	int hour = calendarMatch.get(Calendar.HOUR_OF_DAY);
	    	String hourStr = String.valueOf(hour);
	    	if(hour < 10) {
	    		hourStr = "0" + hour;
	    	}
	    	
	    	int minute = calendarMatch.get(Calendar.MINUTE);
	    	String minuteStr = String.valueOf(minute);
	    	if(minute < 10) {
	    		minuteStr = "0" + minute;
	    	}
	    	
	    	String dateStr = "";
	    	if(!sameDay) {
	    		dateStr = dayOfTheWeekStr + " " + dayOfTheMonthStr + "/" + monthStr + " à " + hourStr + "h" + minuteStr;
	    	} else {
	    		dateStr = "Aujourd'hui à " + hourStr + "h" + minuteStr;
	    	}
	    	
	    	msgInfo = dateStr;
	    	break;
	    case MatchStatus.FIRST_HALF:
	    	msgInfo = "1ère période";
	    	break;
	    case MatchStatus.HALFTIME:
	    	msgInfo = "Mi-temps";
	    	break;
	    case MatchStatus.SECOND_HALF:
	    	msgInfo = "2ème période";
	    	break;
	    case MatchStatus.OVERTIME:
	    	msgInfo = "Prolongations";
	    	break;
	    case MatchStatus.PENALTY:
	    	msgInfo = "Tirs aux buts";
	    	break;
	    case MatchStatus.ENDED:
	    	msgInfo = "Terminé";
	    	break;
	    default:
	    	break;
	    }
	    matchInfo.put("statut", msgInfo);
	    
	    if(_statut != MatchStatus.NOT_STARTED) {
	    	matchInfo.put("matchStarted", "yes");
	    }
		
	    return matchInfo;
	}

	public Long getIdCompetition() {
		return _idCompetition;
	}

	public void setIdCompetition(Long _idCompetition) {
		this._idCompetition = _idCompetition;
	}

	public Long getIdSport() {
		return _idSport;
	}

	public void setIdSport(Long _idSport) {
		this._idSport = _idSport;
	}
	
	@Override
	public String toString() {
		return this.getHomeTeam().getName() + " - " + this.getAwayTeam().getName() + " : " + this.getHomeScore() + " - " + this.getAwayScore();
	}
}
