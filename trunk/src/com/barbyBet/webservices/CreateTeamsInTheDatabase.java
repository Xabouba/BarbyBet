package com.barbyBet.webservices;

import java.util.ArrayList;
import java.util.List;

import com.barbyBet.components.SQLTeamComponent;
import com.barbyBet.object.Team;
import com.barbyBet.tools.WebServiceConstants;
import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Leagues;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;

public class CreateTeamsInTheDatabase {

	public static void main(String[] args) {
		XmlSoccerService xmlSoccerService = new XmlSoccerServiceImpl();
		xmlSoccerService.setApiKey(WebServiceConstants.API_KEY);
		
		// demo access
		xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");
		
		// full access
		//xmlSoccerService.setServiceUrl("http://www.xmlsoccer.com/FootballData.asmx");
		
		List<GetTeamResultDto> euro2016Teams = xmlSoccerService.getAllTeamsByLeagueAndSeason(Leagues.SCOTLAND_SCOTTISH_PREMIER_LEAGUE.getParam(), Seasons.SEASON_2015_2016.getParam());
		List<Team> teams = new ArrayList<Team>();
		
		for (GetTeamResultDto team : euro2016Teams) {
			Team t = new Team();
			t.setName(team.getName());
			t.setImg(team.getName().replace(" ", "_").toLowerCase());
			t.setIdWebService(team.getTeamId());
			
			teams.add(t);
		}
		
		// Insert all of the teams in our own database
		SQLTeamComponent sqlTeamComponent = new SQLTeamComponent();
		sqlTeamComponent.insertTeams(teams);
	}

}
