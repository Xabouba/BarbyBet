package com.barbyBet.object;


public class Match {

	private String _homeTeam;
	private String _awayTeam;
	private String _homeScore;
	private String _awayScore;
	private String _statut;
	
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
}
