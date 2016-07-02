package com.barbyBet.webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLTeamComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.Team;
import com.barbyBet.tools.WebServiceConstants;
import com.barbyBet.tools.WebServiceUtil;
import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;

public class UpdateLiveGamesInTheDatabase {

	public static void main(String[] args) {
		XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
		xmlSoccerService.setApiKey(WebServiceConstants.API_KEY);
		
		// demo access
		xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");
		
		// full access
		//xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");
		
		SQLTeamComponent sqlTeamComponent = new SQLTeamComponent();
		HashMap<Integer, Integer> teamsIdMap = sqlTeamComponent.getTeamsIdMap();
		
		List<GetLiveScoreResultDto> allLiveScores = xmlSoccerService.getLiveScore();
		List<Match> matchs = new ArrayList<Match>();
		
		for (GetLiveScoreResultDto liveScore : allLiveScores) {
			Team homeTeam = new Team();
			homeTeam.setId(teamsIdMap.get(liveScore.getHomeTeamId()));
			homeTeam.setIdWebService(liveScore.getHomeTeamId());
			homeTeam.setName(liveScore.getHometeam());
			
			Team awayTeam = new Team();
			awayTeam.setId(teamsIdMap.get(liveScore.getAwayTeamId()));
			awayTeam.setIdWebService(liveScore.getAwayTeamId());
			awayTeam.setName(liveScore.getAwayteam());
			
			Match m = new Match();
			m.setIdWebService(new Long(liveScore.getId()));
			m.setHomeScore(liveScore.getHomeGoals());
			m.setAwayScore(liveScore.getAwayGoals());
			m.setHomeTeam(homeTeam);
			m.setAwayTeam(awayTeam);
			m.setStatut(WebServiceUtil.createStatus(liveScore.getTime()));
			
			matchs.add(m);
		}
		
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
		sqlMatchComponent.updateMatchs(matchs);
	}
}
