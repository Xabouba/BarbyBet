package com.barbyBet.components;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.barbyBet.object.Match;

public class SaxMatchResultHandler extends DefaultHandler 
{
	private String _content;
	private boolean _readContent;
	
	private Match _match;
	
	public SaxMatchResultHandler()
	{
		super();
		_readContent =  false;
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
    		if (atts.getValue("SNAME").equals("Bordeaux"))
    		{
    			_readContent = true;
    		}
    	}
    	else if (localName.equals("MATCHSTATUS") && _readContent)
    	{
    		_match.setStatut(atts.getValue("STATUS"));
    	}
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
    	if (_readContent)
    	{
    		if (localName.equals("HOMETEAMNAME"))
	    	{
    			_match.setHomeTeam(_content);
	    	}
	    	else if (localName.equals("HOMETEAMSCORE"))
	    	{
	    		try 
	    		{
					Integer.parseInt(_content);
					_match.setHomeScore(_content);
				} 
	    		catch (Exception e) 
	    		{
					_match.setHomeScore("-");
				}
	    	}
	    	else if (localName.equals("AWAYTEAMNAME"))
	    	{
    			_match.setAwayTeam(_content);
	    	}
	    	else if (localName.equals("AWAYTEAMSCORE"))
	    	{
	    		try 
	    		{
					Integer.parseInt(_content);
					_match.setAwayScore(_content);
				} 
	    		catch (Exception e) 
	    		{
					_match.setAwayScore("-");
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
