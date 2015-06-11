package com.barbyBet.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.barbyBet.object.Match;
import com.barbyBet.object.Team;

public class SQLMatchComponent extends SQLComponent {
    public SQLMatchComponent() {
        super();
    }

    public ArrayList<Match> getMatchs() {
        ArrayList<Match> listMatch = new ArrayList<Match>();

        Connection connexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connexion = DriverManager.getConnection(_url, _user, _password);
            stmt =
                    connexion
                            .prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport ORDER BY m.beginDate");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt(6));
                match.setBeginDate(rs.getTimestamp(5));

                Team homeTeam = new Team();
                homeTeam.setTeam(rs.getString(1));
                homeTeam.setImg(rs.getString(2));
                match.setHomeTeam(homeTeam);

                Team awayTeam = new Team();
                awayTeam.setTeam(rs.getString(3));
                awayTeam.setImg(rs.getString(4));
                match.setAwayTeam(awayTeam);

                match.setHomeScore(rs.getInt(7));
                match.setAwayScore(rs.getInt(8));
                match.setStatut(rs.getInt(9));

                match.setCompetition(rs.getString(10));
                match.setSport(rs.getString(11));

                listMatch.add(match);
            }

            return listMatch;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close(rs);
            close(stmt);
            close(connexion);
        }
    }

    public Match getMatch(String matchID) {
        //		HashMap<String, String> match = new HashMap<String, String>();
        Match match = new Match();

        Connection connexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connexion = DriverManager.getConnection(_url, _user, _password);
            stmt =
                    connexion
                            .prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, c.name, s.name  FROM Matchs m, Team t1, Team t2, Sport s, Competition c  WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND m.id = ? ");
            stmt.setInt(1, Integer.valueOf(matchID));

            rs = stmt.executeQuery();
            if (rs.next()) {
                //		    	Timestamp date = rs.getTimestamp(5);
                //		    	GregorianCalendar calendar = new GregorianCalendar();
                //		    	calendar.setTime(date);
                //		    	
                //		    	String yearAsString = String.valueOf(calendar.get(Calendar.YEAR));
                //		    	int month = calendar.get(Calendar.MONTH) + 1;
                //		    	String monthAsSring = String.valueOf(month);
                //		    	if (month < 9)
                //		    	{
                //		    		monthAsSring = "0" + monthAsSring;
                //		    	}
                //		    	String dayAsString = String.valueOf(calendar.get(Calendar.DATE));
                //		    	
                //		    	String dateAsString = yearAsString + monthAsSring + dayAsString;

                //		    	match.put("date", dateAsString);
                //		    	match.put("dateTime", String.valueOf(date.getTime()));

                match.setId(rs.getInt(6));
                match.setBeginDate(rs.getTimestamp(5));

                Team homeTeam = new Team();
                homeTeam.setTeam(rs.getString(1));
                homeTeam.setImg(rs.getString(2));
                match.setHomeTeam(homeTeam);

                Team awayTeam = new Team();
                awayTeam.setTeam(rs.getString(3));
                awayTeam.setImg(rs.getString(4));
                match.setAwayTeam(awayTeam);

                match.setHomeScore(rs.getInt(7));
                match.setAwayScore(rs.getInt(8));
                match.setStatut(rs.getInt(9));

                match.setCompetition(rs.getString(10));
                match.setSport(rs.getString(11));
            }

            return match;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close(rs);
            close(stmt);
            close(connexion);
        }
    }

    public ArrayList<Match> getMatchsWithNoOdds() {
        ArrayList<Match> listMatch = new ArrayList<Match>();

        Connection connexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connexion = DriverManager.getConnection(_url, _user, _password);
            stmt =
                    connexion
                            .prepareStatement("SELECT t1.sname, t1.img, t2.sname, t2.img, m.beginDate, m.id, m.scoreH, m.scoreA, m.statut, c.name, s.name FROM Matchs m, Team t1, Team t2, Sport s, Competition c WHERE m.teamHId = t1.id AND m.teamAId = t2.id AND c.id = m.idCompetition AND s.id = m.idSport AND (m.oddsHome = '' OR m.oddsDraw = '' OR m.oddsAway = '') ORDER BY m.beginDate");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt(6));
                match.setBeginDate(rs.getTimestamp(5));

                Team homeTeam = new Team();
                homeTeam.setTeam(rs.getString(1));
                homeTeam.setImg(rs.getString(2));
                match.setHomeTeam(homeTeam);

                Team awayTeam = new Team();
                awayTeam.setTeam(rs.getString(3));
                awayTeam.setImg(rs.getString(4));
                match.setAwayTeam(awayTeam);

                match.setHomeScore(rs.getInt(7));
                match.setAwayScore(rs.getInt(8));
                match.setStatut(rs.getInt(9));

                match.setCompetition(rs.getString(10));
                match.setSport(rs.getString(11));

                listMatch.add(match);
            }

            return listMatch;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close(rs);
            close(stmt);
            close(connexion);
        }
    }

    public void updateOdds(Match m) {
        Connection connexion = null;
        PreparedStatement stmt = null;
        try {
            connexion = DriverManager.getConnection(_url, _user, _password);
            stmt = connexion.prepareStatement("UPDATE Match SET oddsHome = ?, oddsDraw = ?, oddsAway = ? WHERE id = ?");

            stmt.setString(1, m.getOdds().getHomeOdd() + "");
            stmt.setString(2, m.getOdds().getDrawOdd() + "");
            stmt.setString(3, m.getOdds().getAwayOdd() + "");
            stmt.setInt(4, m.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(stmt);
            close(connexion);
        }
    }
}
