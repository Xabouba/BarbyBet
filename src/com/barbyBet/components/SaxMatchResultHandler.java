package com.barbyBet.components;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.barbyBet.object.Match;
import com.barbyBet.object.Team;

public class SaxMatchResultHandler extends DefaultHandler 
{
	private String _sName;
	
	private String _content;
	private boolean _readContent;
	
	private Match _match;
	
	public SaxMatchResultHandler(String sName)
	{
		super();
		_sName = sName;
		_readContent = false;
		_match = new Match();
	}
	
	public void startDocument() throws SAXException 
    {
		
    }
    
    public void endDocument() throws SAXException 
    {
    	
    }
    
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
    {
    	if (localName.equals("HOMETEAMNAME"))
    	{
    		if (atts.getValue("SNAME").equals(_sName))
    		{
    			_readContent = true;
    		}
    	}
    	else if (localName.equals("MATCHSTATUS") && _readContent)
    	{
    		_match.setStatut(Integer.valueOf(atts.getValue("STATUS")));
    	}
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
    	if (_readContent)
    	{
    		if (localName.equals("HOMETEAMNAME"))
	    	{
    			Team homeTeam = new Team();
    			homeTeam.setTeam(_content);
    			_match.setHomeTeam(homeTeam);
	    	}
	    	else if (localName.equals("HOMETEAMSCORE"))
	    	{
	    		try 
	    		{
					Integer.parseInt(_content);
					_match.setHomeScore(Integer.valueOf(_content));
				} 
	    		catch (Exception e) 
	    		{
					_match.setHomeScore(0);
				}
	    	}
	    	else if (localName.equals("AWAYTEAMNAME"))
	    	{
	    		Team awayTeam = new Team();
	    		awayTeam.setTeam(_content);
    			_match.setAwayTeam(awayTeam);
	    	}
	    	else if (localName.equals("AWAYTEAMSCORE"))
	    	{
	    		try 
	    		{
					Integer.parseInt(_content);
					_match.setAwayScore(Integer.valueOf(_content));
				} 
	    		catch (Exception e) 
	    		{
					_match.setAwayScore(0);
				}
	    	}
	    	else if (localName.equals("MATCHSTATUS"))
	    	{
	    		_readContent = false;
	    	}
    	}
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException 
    {
    	_content = String.copyValueOf(ch, start, length).trim();
    }

	public Match getMatch() {
		return _match;
	}

	public void setMatch(Match match) {
		this._match = match;
	}

}
