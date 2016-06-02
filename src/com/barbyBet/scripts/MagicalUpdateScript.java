package com.barbyBet.scripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.SQLRankComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.components.UsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.Match;
import com.barbyBet.tools.MatchStatus;
import com.barbyBet.tools.WebServiceConstants;
import com.barbyBet.tools.WebServiceUtil;
import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;

public class MagicalUpdateScript {
	public static void main(String[] args) {
		long webId = 360422;
		updateProno(2, 4, webId);
		
		SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
		SQLUsersComponent sqlUserComponent = new SQLUsersComponent();
		Map<Long, Integer> userWithPoint = sqlUserComponent.getUserWithPoint();
		Set<Long> groupToUpdate = new HashSet<Long>();
		for(Long idUser : userWithPoint.keySet())
		{
			Map<Long, Map<String, String>> groups = sqlGroupComponent.getGroups(idUser);
			for (Long idGroup : groups.keySet())
			{
				System.out.println("Groupe " + idGroup);
				sqlGroupComponent.updateGroupUserPoint(idUser, idGroup, userWithPoint.get(idUser));

				
				groupToUpdate.add(idGroup);
			}
			
			System.out.println("User " + idUser);
			sqlUserComponent.updateUserPoint(idUser, userWithPoint.get(idUser));
		}
		
		for (Long idGroup : groupToUpdate)
		{
			System.out.println("Update groupe " + idGroup);
			Group group = sqlGroupComponent.getGroup(idGroup);
			sqlGroupComponent.updateRankAfterModificationInGroup(group, null);
		}
		
		System.out.println("Update general");
		SQLRankComponent sqlRankComponent = new SQLRankComponent();
		sqlRankComponent.updateRankAfterModification();
		
		System.out.println("coucou");
		
//		long groupId = 31;
//		System.out.println("cool");
	}
	
	public boolean updateCurrentGamesRealScores() {
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
		
		XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
		xmlSoccerService.setApiKey(WebServiceConstants.API_KEY);
		
		// demo access
		// xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");
		
		// full access
		xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");
		

		List<Match> currentMatches = new ArrayList<Match>();
		List<Match> justEndedMatch = new ArrayList<Match>();
		// List<GetLiveScoreResultDto> allCurrentLiveScores = xmlSoccerService.getLiveScoreByLeague("EURO 2016");
		List<GetLiveScoreResultDto> allCurrentLiveScores = xmlSoccerService.getLiveScore();
		for(GetLiveScoreResultDto liveScore : allCurrentLiveScores) {
			Match match = new Match();
			Long idWebService = Long.valueOf(liveScore.getId());
			int statut = WebServiceUtil.createStatus(liveScore.getTime());
			
			match.setIdWebService(idWebService);
			match.setStatut(statut);
			match.setHomeScore(liveScore.getHomeGoals());
			match.setAwayScore(liveScore.getAwayGoals());
			
			currentMatches.add(match);
			if (statut == MatchStatus.ENDED && !sqlMatchComponent.hasMatchEnded(idWebService))
			{
				justEndedMatch.add(match);
			}
		}
		
		for (Match match : justEndedMatch)
		{
			int homeScore = match.getHomeScore();
			int awayScore = match.getAwayScore();
			
			updateProno(homeScore, awayScore, match.getIdWebService());
		}
		
		// Update the games in the database
		if(currentMatches.size() != 0) {
			sqlMatchComponent.updateMatchs(currentMatches);
		}
		
		return false;
	}
	
	public static void updateProno(int homeScore, int awayScore, long idWebService)
	{
		SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
		
		List<Map<String, String>> pronoFromMatch = sqlPronoComponent.getPronoFromMatch(idWebService);
		for (Map<String, String> pronoMap : pronoFromMatch)
		{
			int point = 0;
			int statut = 0;
			int pronoHomeScore = Integer.parseInt(pronoMap.get("scoreHome"));
			int pronoAwayScore = Integer.parseInt(pronoMap.get("scoreAway"));
			if (pronoHomeScore == homeScore && pronoAwayScore == awayScore)
			{
				point = 5;
				statut = 2;
			}
			else if (pronoHomeScore - pronoAwayScore == homeScore - awayScore)
			{
				point = 3;
				statut = 1;
			}
			else if (((pronoHomeScore - pronoAwayScore) * (homeScore - awayScore)) > 0)
			{
				point = 2;
				statut = 1;
			}
			
			long pronoId = Long.parseLong(pronoMap.get("id"));
			sqlPronoComponent.updatePronoFromId(pronoId, statut, point);
		}
	}
}
