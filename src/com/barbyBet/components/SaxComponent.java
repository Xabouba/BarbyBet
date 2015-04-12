package com.barbyBet.components;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.barbyBet.object.Match;

public class SaxComponent
{
	public SaxComponent()
	{
		
	}
	
	public Match parseMatch() throws ParserConfigurationException, SAXException, IOException
	{
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
	    
	    XMLReader xmlReader = saxParser.getXMLReader();
	    
	    SaxMatchResultHandler saxMatchHandler = new SaxMatchResultHandler();
	    xmlReader.setContentHandler(saxMatchHandler);
	    
	    URL adresse = new URL("http://live.skysports.com/football/fixtures/20150412.xml");
	    InputStream stream = adresse.openStream();
	    InputSource source = new InputSource(stream);
	    xmlReader.parse(source);
	    
	    return saxMatchHandler.getMatch();
	 }
    
}
