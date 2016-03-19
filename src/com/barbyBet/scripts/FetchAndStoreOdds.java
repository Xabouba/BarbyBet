package com.barbyBet.scripts;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.barbyBet.components.SQLMatchComponent;
import com.barbyBet.components.SaxComponent;
import com.barbyBet.object.Match;
import com.barbyBet.object.Team;

public class FetchAndStoreOdds {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SQLMatchComponent sqlMatchComponent = new SQLMatchComponent();
        // ArrayList<Match> matchs = sqlMatchComponent.getMatchsWithNoOdds();

        ArrayList<Match> matchs = new ArrayList<Match>();

        Match m1 = new Match();
        Team homeTeam1 = new Team();
        homeTeam1.setName("Marseille");

        Team awayTeam1 = new Team();
        awayTeam1.setName("Bastia");

        m1.setHomeTeam(homeTeam1);
        m1.setAwayTeam(awayTeam1);
        m1.setSport("Football");
        m1.setCompetition("French Ligue 1");

        Match m2 = new Match();
        Team homeTeam2 = new Team();
        homeTeam2.setName("Lorient");

        Team awayTeam2 = new Team();
        awayTeam2.setName("Monaco");

        m2.setHomeTeam(homeTeam2);
        m2.setAwayTeam(awayTeam2);
        m2.setSport("Football");
        m2.setCompetition("French Ligue 1");

        matchs.add(m1);
        matchs.add(m2);

        for (Match match : matchs) {
            match = getMatchOdd(match);
            System.out.println(match.getOdds().toString());
            // sqlMatchComponent.updateOdds(m);
        }
    }

    private static Match getMatchOdd(Match m) {
        Match matchToReturn = m;
        SaxComponent saxComponent = new SaxComponent();
        try {
            saxComponent.parseOdds(matchToReturn);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return matchToReturn;
    }
}
