<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>

    <body>
    	<% response.setIntHeader("Refresh", 30); %>
    	
    	<div style="margin-left: auto; margin-right: auto; width: 1000px;">
    		<h1 style="text-align: center">Matchs du jour</h1>
	    	
	    	<table style="margin-left: auto; margin-right: auto;">
	    	<c:forEach items="${matchsInfo}" var="match">
		       <p style="text-align: center">${match.statut}</p>
			   <tr>
	    			<td><img src="images/team/${match.homeImg}_128.png"></img></td>
	    			<td style="width:200px; text-align: center; font-size:30px">${match.homeTeam}</td>
	    			<td style="width:100px; text-align: center; font-size:50px">${match.homeScore}</td>
	    			<td style="width:100px; text-align: center; font-size:50px">${match.awayScore}</td>
	    			<td style="width:200px; text-align: center; font-size:30px">${match.awayTeam}</td>
	    			<td><img src="images/team/${match.awayImg}_128.png"></img></td>
	    		</tr>
			</c:forEach>
	    	
	    		
	    	</table>
    	</div>
    </body>
</html>