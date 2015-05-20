package com.barbyBet.object;


public class Match {

	private String _homeTeam;
	private String _awayTeam;
	private String _homeScore;
	private String _awayScore;
	private String _statut;
	private String _competition;
	private String _sport;
	private Odds _odds = new Odds(0, 0, 0);
	
	public Match()
	{
		
	}
	
	public String getHomeTeam() 
	{
		return _homeTeam;
	}
	
	public void setHomeTeam(String homeTeam) 
	{
		this._homeTeam = homeTeam;
	}

	public String getAwayTeam() 
	{
		return _awayTeam;
	}

	public void setAwayTeam(String awayTeam) 
	{
		this._awayTeam = awayTeam;
	}

	public String getStatut() 
	{
		return _statut;
	}

	public void setStatut(String statut) 
	{
		this._statut = statut;
	}

	public String getHomeScore() 
	{
		return _homeScore;
	}

	public void setHomeScore(String homeScore) 
	{
		this._homeScore = homeScore;
	}

	public String getAwayScore() 
	{
		return _awayScore;
	}

	public void setAwayScore(String awayScore) 
	{
		this._awayScore = awayScore;
	}
	
	public String getCompetition() {
		return _competition;
	}
	
	public void setCompetition(String competition) {
		_competition = competition;
	}
	
	public String getSport() {
		return _sport;
	}
	
	public void setSport(String sport) {
		_sport = sport;
	}
	
	public Odds getOdds() {
		return _odds;
	}
	
	public void setOdds(Odds odds) {
		_odds = odds;
	}
}
