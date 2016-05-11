package com.barbyBet.webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLTeamComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.Odds;
import com.barbyBet.object.Team;
import com.barbyBet.tools.WebServiceConstants;
import com.barbyBet.tools.WebServiceUtil;
import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Leagues;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;

public class CreateGamesInTheDatabase {
	
	public static void main(String[] args) {
		XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
		xmlSoccerService.setApiKey(WebServiceConstants.API_KEY);
		
		// demo access
		// xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");
		
		// full access
		xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");
		
		SQLTeamComponent sqlTeamComponent = new SQLTeamComponent();
		HashMap<Integer, Integer> teamsIdMap = sqlTeamComponent.getTeamsIdMap();
		
		List<GetFixturesResultDto> allEuro2016Games = xmlSoccerService.getFixturesByLeagueAndSeason("EURO 2016", "");
		List<Match> matchs = new ArrayList<Match>();
		
		for (GetFixturesResultDto game : allEuro2016Games) {
			if(game.getRound() != null) {
				Team homeTeam = new Team();
				homeTeam.setName(game.getHomeTeam());
				homeTeam.setId(teamsIdMap.get(game.getHomeTeamId()));
				homeTeam.setIdWebService(game.getHomeTeamId());
				
				Team awayTeam = new Team();
				awayTeam.setName(game.getAwayTeam());
				awayTeam.setId(teamsIdMap.get(game.getAwayTeamId()));
				awayTeam.setIdWebService(game.getAwayTeamId());
	
				Odds odds = new Odds(0, 0, 0);
				
				Match m = new Match();
				m.setIdWebService(new Long(game.getId()));
				m.setIdSport(WebServiceConstants.SPORT_FOOTBALL_ID);
				m.setIdCompetition(WebServiceConstants.SPORT_FOOTBALL_ID);
				m.setJournee(Integer.parseInt(game.getRound()));
				m.setHomeTeam(homeTeam);
				m.setAwayTeam(awayTeam);
				m.setStatut(WebServiceUtil.createStatus(game.getTime()));
				
				if(game.getHomeGoals() != null) {
					m.setHomeScore(game.getHomeGoals());
				} else {
					m.setHomeScore(0);
				}
				
				if(game.getAwayGoals() != null) {
					m.setAwayScore(game.getAwayGoals());
				} else {
					m.setAwayScore(0);
				}
				
				m.setBeginDate(new java.sql.Timestamp(game.getDate().getTime()));
				m.setOdds(odds);
							
				matchs.add(m);
			}
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
