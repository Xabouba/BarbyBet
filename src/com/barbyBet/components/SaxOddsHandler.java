package com.barbyBet.components;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.barbyBet.object.Match;

public class SaxOddsHandler extends DefaultHandler {

    private Match _match;
    private boolean _isRightSport = false;
    private boolean _isRightCompetition = false;
    private boolean _isRightGame = false;
    private boolean _isInBets = false;

    public SaxOddsHandler(Match match) {
        super();
        _match = match;
    }

    public void startDocument() throws SAXException {

    }

    public void endDocument() throws SAXException {

    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (localName.equals("sport") && atts.getValue("name").toString().equals(_match.getSport())) {
            _isRightSport = true;
        } else if (_isRightSport && localName.equals("event")
                && atts.getValue("name").toString().equals(_match.getCompetition())) {
            _isRightCompetition = true;
        } else if (_isRightCompetition
                && localName.equals("match")
                && atts.getValue("name").toString()
                        .equals(_match.getHomeTeam().getTeam() + " - " + _match.getAwayTeam().getTeam())) {
            _isRightGame = true;
        } else if (_isRightGame && localName.equals("bet") && atts.getValue("name").toString().equals("Match Result")) {
            _isInBets = true;
        } else if (_isInBets && localName.equals("choice") && atts.getValue("name").toString().equals("%1%")) {
            _match.getOdds().setHomeOdd(Float.parseFloat(atts.getValue("odd")));
        } else if (_isInBets && localName.equals("choice") && atts.getValue("name").toString().equals("Draw")) {
            _match.getOdds().setDrawOdd(Float.parseFloat(atts.getValue("odd")));
        } else if (_isInBets && localName.equals("choice") && atts.getValue("name").toString().equals("%2%")) {
            _match.getOdds().setAwayOdd(Float.parseFloat(atts.getValue("odd")));
            _isInBets = false;
            _isRightGame = false;
            _isRightCompetition = false;
            _isRightSport = false;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

    }
}
