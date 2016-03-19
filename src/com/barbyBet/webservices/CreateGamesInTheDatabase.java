package com.barbyBet.webservices;

import java.util.ArrayList;
import java.util.List;

import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.Odds;
import com.barbyBet.object.Team;
import com.barbyBet.tools.WebServiceConstants;
import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Leagues;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;

public class CreateGamesInTheDatabase {

	private static String API_KEY = "AGXWUSQXGXWHXLURQWBIETVFRPRBNYGLIFCUBGSVPRZSUDSCNG";
	
	public static void main(String[] args) {
		 XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
	        xmlSoccerService.setApiKey(API_KEY);

	        // demo access
	        xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");

	        // full access
	        //xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");

	      //  GetTeamResultDto getTeamResultDto = xmlSoccerService.getTeam("Celtic");
	      //  System.out.println(getTeamResultDto.toString());
	        
	        // List<GetLiveScoreResultDto> getLiveScoreResultDto = xmlSoccerService.getLiveScore();
	        //  System.out.println(getLiveScoreResultDto.toString());

	        // List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtoList = xmlSoccerService.getLeagueStandingsBySeason(Leagues.SCOTLAND_SCOTTISH_PREMIER_LEAGUE.getParam(), Seasons.SEASON_2015_2016.getParam());
	        
	        List<GetFixturesResultDto> allScotishPremiereLeagueGames = xmlSoccerService.getFixturesByLeagueAndSeason(Leagues.SCOTLAND_SCOTTISH_PREMIER_LEAGUE.getParam(), Seasons.SEASON_2015_2016.getParam());
	        List<Match> matchs = new ArrayList<Match>();
	        
	        for (GetFixturesResultDto games : allScotishPremiereLeagueGames) {
	        	Team homeTeam = new Team();
	        	homeTeam.setName(games.getHomeTeam());
	        	homeTeam.setId(games.getHomeTeamId());
	        	
	        	Team awayTeam = new Team();
	        	awayTeam.setName(games.getAwayTeam());
	        	awayTeam.setId(games.getAwayTeamId());
	        	
	        	Odds odds = new Odds(0, 0, 0);
	        	
	        	Match m = new Match();
	        	m.setIdSport(WebServiceConstants.SPORT_FOOTBALL_ID);
	        	m.setIdCompetition(WebServiceConstants.SPORT_FOOTBALL_ID);
	        	m.setJournee(Integer.parseInt(games.getRound()));
	        	m.setHomeTeam(homeTeam);
	        	m.setAwayTeam(awayTeam);
	        	
	        	if(games.getHomeGoals() != null) {
	        		m.setHomeScore(games.getHomeGoals());
	        	} else {
	        		m.setHomeScore(0);
	        	}
	        	
	        	if(games.getAwayGoals() != null) {
	        		m.setAwayScore(games.getAwayGoals());
	        	} else {
	        		m.setAwayScore(0);
	        	}
	        	
	        	m.setBeginDate(new java.sql.Timestamp(games.getDate().getTime()));
	        	m.setStatut(0);
	        	m.setOdds(odds);
	        	
	        	matchs.add(m);
	        }
	        
	        /* to pass in a parameter the name of the league or the season,
	        ** you can use specially prepared enumerated type,
	        ** which can be found in the package pl.com.pablo.xmlsoccer.model.enums
	        **
	        ** example:
	        ** List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtoList = xmlSoccerService.getLeagueStandingsBySeason(Leagues.SCOTLAND_SCOTTISH_PREMIER_LEAGUE.getParam(), Seasons.SEASON_2014_2015.getParam());
	        ** System.out.println(getLeagueStandingsResultDtoList.toString());
	        */
	        
	        // Insert all of the games in our own database
	        SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
	        sqlMatchComponent.insertMatchs(matchs);
	}
}
