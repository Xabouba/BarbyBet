package com.barbyBet.scripts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.object.Match;
import com.barbyBet.tools.WebServiceConstants;
import com.barbyBet.tools.WebServiceUtil;
import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;

public class MagicalUpdateScript {
	public static void main(String[] args) {
		/*
			Updater les scores (toutes les 30 secondes)
			Si match fini:
				-> Update des points de chaque pronostic
				-> Update des classements des utilisateurs :
					- Classement général (table User)
					- Classement dans chaque groupe (table LinkUserGroup)
					
		 */
		//System.out.println("Hello World");
		
		// updateCurrentGamesRealScores();
	}
	
	public MagicalUpdateScript() {
		updateCurrentGamesRealScores();
	}
	
	public static boolean updateCurrentGamesRealScores() {
		XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
		xmlSoccerService.setApiKey(WebServiceConstants.API_KEY);
		
		// demo access
		// xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");
		
		// full access
		xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");
		

		List<Match> currentMatches = new ArrayList<Match>();
		// List<GetLiveScoreResultDto> allCurrentLiveScores = xmlSoccerService.getLiveScoreByLeague("EURO 2016");
		List<GetLiveScoreResultDto> allCurrentLiveScores = xmlSoccerService.getLiveScore();
		for(GetLiveScoreResultDto liveScore : allCurrentLiveScores) {
			Match match = new Match();
			match.setIdWebService(Long.valueOf(liveScore.getId()));
			match.setStatut(WebServiceUtil.createStatus(liveScore.getTime()));
			match.setHomeScore(liveScore.getHomeGoals());
			match.setAwayScore(liveScore.getAwayGoals());
			
			currentMatches.add(match);
		}
		
		// Update the games in the database
		if(currentMatches.size() != 0) {
			SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
			sqlMatchComponent.updateMatchs(currentMatches);
		}
		
		Date today = new Date();
		
		/*
		List<Match> unfinishedMatchs = sqlMatchComponent.getUnfinishedMatch(); // Get all matches that are not ended (even the not started ones)
		
		for (Match match : unfinishedMatchs) {
			if(match.getBeginDate().before(today)) {
				// Le match vient de commencer
				// On update le statut du match en java
				// On update le score du match en java
				// On update la BDD
			}
			
			if(match)
		}
		*/
		return false;
	}
}
