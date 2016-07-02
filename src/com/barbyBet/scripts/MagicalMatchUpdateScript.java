package com.barbyBet.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.barbyBet.components.SQLGroupComponent;
import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SQLPronoComponent;
import com.barbyBet.components.SQLRankComponent;
import com.barbyBet.components.SQLScriptsComponent;
import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.object.Group;
import com.barbyBet.object.Match;
import com.barbyBet.tools.MatchStatus;
import com.barbyBet.tools.WebServiceConstants;
import com.barbyBet.tools.WebServiceUtil;
import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;

public class MagicalMatchUpdateScript {
	public static void main(String[] args) {
		String matchWebServiceIdStr = args[0];
		String homeGoalsStr = args[1];
		String awayGoalsStr = args[2];
		
		Long matchWebServiceId = 0L;
		int homeGoals = 0;
		int awayGoals = 0;

		if(matchWebServiceIdStr != null) {
			matchWebServiceId = Long.parseLong(matchWebServiceIdStr);		
		} else {
			System.out.println("Usage : java -jar magicalUpdateScript.jar webServiceMatchID homeGoals awayGoals");
		}

		if(homeGoalsStr != null) {
			homeGoals = Integer.parseInt(homeGoalsStr);
		} else {
			System.out.println("Usage : java -jar magicalUpdateScript.jar webServiceMatchID homeGoals awayGoals");
		}		

		if(awayGoalsStr != null) {
			awayGoals = Integer.parseInt(awayGoalsStr);
		} else {
			System.out.println("Usage : java -jar magicalUpdateScript.jar webServiceMatchID homeGoals awayGoals");
		}
		
		updateMatchScoresAndPronos(matchWebServiceId, homeGoals, awayGoals);
	}
	
	public static boolean updateMatchScoresAndPronos(Long matchWebServiceId, int homeGoals, int awayGoals) {
		SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();

		List<Match> currentMatches = new ArrayList<Match>();
		List<Match> justEndedMatch = new ArrayList<Match>();

		Match match = new Match();
		int statut = MatchStatus.ENDED;
		
		match.setIdWebService(matchWebServiceId);
		match.setStatut(statut);
		match.setHomeScore(homeGoals);
		match.setAwayScore(awayGoals);
		
		if(sqlMatchComponent.isMatchFromWebServiceInDatabase(matchWebServiceId)) {
			if(!sqlMatchComponent.hasMatchEnded(matchWebServiceId)) {
				currentMatches.add(match);	
				justEndedMatch.add(match);
			}
		}
		
		// Update the games in the database
		if(currentMatches.size() != 0) {
			sqlMatchComponent.updateMatchs(currentMatches);
		}
		
		for (Match m : justEndedMatch)
		{
			int homeScore = m.getHomeScore();
			int awayScore = m.getAwayScore();
			System.out.println("Looping through justEndedMatch & updating prono: " + m.getIdWebService());
			updateProno(homeScore, awayScore, m.getIdWebService());
		}
		
		// If games just ended we update the users points
		if(!justEndedMatch.isEmpty()) {
			SQLGroupComponent sqlGroupComponent = new SQLGroupComponent();
			SQLUsersComponent sqlUserComponent = new SQLUsersComponent();
			Map<Long, Integer> userWithPoint = sqlUserComponent.getUserWithPoint();
			Set<Long> groupToUpdate = new HashSet<Long>();
			
			// We update the users points in their respective groups & in the general ranking
			for(Long idUser : userWithPoint.keySet())
			{
				Map<Long, Map<String, String>> groups = sqlGroupComponent.getGroups(idUser);
				for (Long idGroup : groups.keySet())
				{
					System.out.println("Updating user : " + idUser + " in group : " + idGroup);
					sqlGroupComponent.updateGroupUserPoint(idUser, idGroup, userWithPoint.get(idUser));
	
					groupToUpdate.add(idGroup);
				}
				
				System.out.println("Updating user point : " + idUser);
				sqlUserComponent.updateUserPoint(idUser, userWithPoint.get(idUser));
			}
			
			// We update the groups rankings
			for (Long idGroup : groupToUpdate)
			{
				Group group = sqlGroupComponent.getGroup(idGroup);
				System.out.println("Updating group : " + group.getName() + " rank");
				sqlGroupComponent.updateRankAfterModificationInGroup(group, null);
			}
			
			// We update the general ranking
			System.out.println("Updating general ranking");
			SQLRankComponent sqlRankComponent = new SQLRankComponent();
			sqlRankComponent.updateRankAfterModification();
		}
		
		return false;
	}
	
	public static void updateProno(int homeScore, int awayScore, long idWebService)
	{
		/** Match Stat **/
		SQLPronoComponent sqlPronoComponent = new SQLPronoComponent();
		HashMap<String, String> matchStatPronostic = sqlPronoComponent.getMatchFromIdWebStatPronostic(idWebService);
		
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
			
			int nbProno = Integer.valueOf(matchStatPronostic.get("nbProno"));
			int nbWin = Integer.valueOf(matchStatPronostic.get("nbWin"));
			int nbDraw = Integer.valueOf(matchStatPronostic.get("nbExact"));
			int nbLose = Integer.valueOf(matchStatPronostic.get("nbLose"));
			
			if ((nbWin * 100 / nbProno) < 20)
			{
				if (pronoHomeScore > pronoAwayScore)
				{
					point = 2 * point;
				}
			}
			
			if ((nbLose * 100 / nbProno) < 20)
			{
				if (pronoHomeScore < pronoAwayScore)
				{
					point = 2 * point;
				}
			}
			
			if ((nbDraw * 100 / nbProno) < 20)
			{
				if (pronoHomeScore == pronoAwayScore)
				{
					point = 2 * point;
				}
			}
			
			long pronoId = Long.parseLong(pronoMap.get("id"));
			sqlPronoComponent.updatePronoFromId(pronoId, statut, point);
		}
	}
}
