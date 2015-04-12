<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>

    <body>
    	<% response.setIntHeader("Refresh", 30); %>
    	
    	<div style="margin-left: auto; margin-right: auto; width: 1000px;">
    		<h1 style="text-align: center">Match du jour</h1>
	    	<p style="text-align: center">${statut}</p>
	    	<table style="margin-left: auto; margin-right: auto;">
	    		<tr>
	    			<td><img src="images/team/bordeaux.png"></img></td>
	    			<td style="width:200px; text-align: center; font-size:30px">${homeTeam}</td>
	    			<td style="width:100px; text-align: center; font-size:50px">${homeScore}</td>
	    			<td style="width:100px; text-align: center; font-size:50px">${awayScore}</td>
	    			<td style="width:200px; text-align: center; font-size:30px">${awayTeam}</td>
	    			<td><img src="images/team/marseille.png"></img></td>
	    		</tr>
	    	</table>
    	</div>
    </body>
</html>