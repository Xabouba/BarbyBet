package com.barbyBet.object;

import java.sql.Timestamp;
import java.util.HashMap;


public class Match {

	private int _id;
	private Team _homeTeam;
	private Team _awayTeam;
	private int _homeScore;
	private int _awayScore;
	private int _statut;
	private String _competition;
	private String _sport;
	private Odds _odds = new Odds(0, 0, 0);
	private Timestamp _beginDate;
	
	public Match()
	{
		
	}
	
	public int getId()
	{
		return _id;
	}
	
	public void setId(int id)
	{
		this._id = id;
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
	
	public HashMap<String, String> toHashMap()
	{
		/** Match Information */
	    HashMap<String, String> matchInfo = new HashMap<String, String>();
	    matchInfo.put("homeTeam", _homeTeam.getTeam());
	    matchInfo.put("awayTeam", _awayTeam.getTeam());
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
	    case 0:
	    case 1:
	    	msgInfo = "A jouer";
	    	break;
	    case 2:
	    	msgInfo = "1ère période";
	    	break;
	    case 3:
	    	msgInfo = "Mi-temps";
	    	break;
	    case 4:
	    	msgInfo = "2ème période";
	    	break;
	    case 5:
	    	msgInfo = "Terminé";
	    	break;
	    default:
	    	break;
	    }
	    matchInfo.put("statut", msgInfo);
		
	    return matchInfo;
	}
}