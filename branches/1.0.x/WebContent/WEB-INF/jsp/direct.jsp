<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<title>Match en direct : ${match.homeTeam} - ${match.awayTeam}</title>
        <%@include file="import.jsp" %>
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
    	<div class="wraper">
        	<%@include file="header.jsp" %>
    		
    		<!-- page-header -->
            <div id="content" class="match-direct">
                <div class="top-effect clearfix">
                    <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                    <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                </div>
                <!-- top-effect -->
                <div id="main-content" class="pull-left">
                    <div id="sidebar-main-content" class="pull-left sidebar-minimized-rank">
                    	<%@include file='minimized-rank.jsp'%>
                    </div>
                    <!-- sidebar-main-content -->
                    <div id="main-col" class="pull-left">
                        <ul class="list-unstyled">
                            <li class="clearfix">
                                <div class="widget kp-main-news">
                                    <div class="widget-content">
	                                    <h2 class="widget-title"><span>Direct</span></h2>
                                        <div class="list_carousel responsive result-match">
                                            <ul id="ca-main-news" class="clearfix">
                                            	<li>
	                                            	<div id="match-direct" style="margin-left: auto; margin-right: auto;">
	                                            		<%@include file='direct-part.jsp'%>
											    	</div>
										    	</li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-news -->
                            </li>
                            <li class="clearfix">
                                <div class="widget kp-review">
                                    <h2 class="widget-title"><span>Commentaires</span></h2>
                                    <div id="chat" class="widget-content">
                                   		<%@include file='chat.jsp'%>
                                    </div>
                                    <br />
								  	<label for="comments">Message: </label>
								 	<textarea style="width: 100%;" rows="5" name="comment" id="comments" maxlength="450" ></textarea>
									<br />
									<br />
									<div style="width:100%; text-align: center">
										<input class="btn btn-primary" type="submit" value="Envoyer" onclick="post()"/>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-review -->
                            </li>
                        </ul>
                    </div>
                    <!-- main-col -->
                    <div class="clearfix"></div>
                </div>
                <!-- main-content -->
            	<div id="sidebar" class="pull-right user-information">
                    <ul class="clearfix list-unstyled">
                        <li>
                            <div class="widget kp-last-news">
                                <h2 class="widget-title"><span>Informations</span></h2>
                                <div class="widget-content">
                                    <ul class="list-unstyled">
                                    	<li class="format-standard">
		                                    <div id="match">
		                                    	
		                                    	<table class="table-info">
		                                    		<caption>
	                                    			</caption>
	                                    			<tbody>
			                                    		<tr>
			                                    			<td class="login">
			                                    				<strong>${currentUser}</strong>
															</td>
															<c:if test="${empty matchStarted}">
				                                    			<td class="bet">
			                                    					<input class="btn btn-primary" type="button" value="Parier" onclick="bet()" />
				                                    			</td>
			                                    			</c:if>
			                                    		</tr>
			                                    		<tr>
			                                    			<td class="prono-title">Pronostic:</td>
			                                    			<td id="prono-score" class="prono-info">
														    	<span id="score-home">${scoreHome}</span> - <span id="score-away">${scoreAway}</span>
			                                    			</td>
			                                    		</tr>
	                                    				<tr>
			                                    			<td class="prono-title">Point:</td>
			                                    			<td class="prono-info">
			                                    				<c:if test="${creditsWon != null}">
				                                    				${creditsWon}
			                                    				</c:if>
			                                    			</td>
			                                    		</tr>
	                                    			</tbody>
		                                    	</table>
		                                    </div>
                                    	</li>
                                        <li class="format-standard">
                                        	<div id="info-match" class="info-match">
                                    		</div>
                                    	</li>
                                   	</ul>
                                </div>
                                <!-- widget-content -->
                            </div>
                            <!-- kp-last-news -->
                        </li>
                    </ul>
                </div>
                <!-- sidebar -->
                <div class="clearfix"></div>
            </div>
    	</div>
    	<%@include file="footer.jsp" %>
    	<!-- Page footer -->
    	
    	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/jqueryUi.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/superfish.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/jquery.carouFredSel-6.2.1-packed.js"></script>
        <script src="js/jflickrfeed.min.js"></script>
        <script src="js/tweetable.jquery.js"></script>
        <script src="js/jquery.timeago.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/grid.js"></script>
        <script src="js/masonry.pkgd.min.js"></script>
        <script src="js/chart/jquery.canvasjs.js"></script>
        <script src="js/chart/canvasjs.js"></script>
        <script src="js/chart/excanvas.js"></script>
        <script type="text/javascript" src="js/custom.js"></script>
    	<script type="text/javascript">

   		statMatch = function (nbProno, nbWin, nbLose, nbDraw) {
			var homeTeam = "${match.homeTeam}";
			var awayTeam = "${match.awayTeam}";
		    var homeOdd = "${match.homeOdd}";
			var drawOdd = "${match.drawOdd}";
			var awayOdd = "${match.awayOdd}";
		    
			if (nbProno != 0)
			{
				var win = Math.round(nbWin / nbProno * 1000) / 10;
				var lose = Math.round(nbLose / nbProno * 1000) / 10;
				var draw = Math.round(nbDraw / nbProno * 1000) / 10;

				if ((win + lose + draw) > 100)
				{
					if (19.7 < win < 20.3)
					{
						if (19.7 < lose < 20.3)
						{
							draw = draw - 0.1;
						}
						else
						{
							lose = lose - 0.1;
						}
					}
					else
					{
						win = win - 0.1;
					}
				}

				if ((win + lose + draw) < 100)
				{
					if (19.7 < win < 20.3)
					{
						if (19.7 < lose < 20.3)
						{
							draw = draw + 0.1;
						}
						else
						{
							lose = lose + 0.1;
						}
					}
					else
					{
						win = win + 0.1;
					}
				}
				
		    	var chart = new CanvasJS.Chart("info-match", {
			    	axisX: {
			            title: homeTeam + "   -   " + awayTeam
			          },
		            axisY:{ 
		        	    maximum: 100,
		        	  },
		    		data: [             
			        {
				         type: "column",
				         toolTipContent: "<p>{label} : {y}%</p>",
				         dataPoints: [
					         { label: "1", y: win },
					         { label: "N", y: draw },
					         { label: "2", y: lose }
				         ]
			        }
			       ]
			     });
			
			    chart.render();
		       }
		  }

   		  var nbProno = ${matchStat.nbProno};
		  var nbWin = ${matchStat.nbWin};
		  var nbLose = ${matchStat.nbLose};
		  var nbDraw = ${matchStat.nbExact};
    	  statMatch(nbProno, nbWin, nbLose, nbDraw);

		  bet = function()
    	  {
			  var scoreHome = $("#score-home").html();
			  var scoreAway = $("#score-away").html();
	    	  
        	  if ($("#bet_form").length == 0)
           	  {
					var htmlBet = "<table class='table_bet' id='bet_form'>";
					htmlBet += "<tr>";
					htmlBet += "<td class='odd'><strong>1</strong></td>";	
					htmlBet += "<td class='odd'><strong>N</strong></td>";	
					htmlBet += "<td class='odd'><strong>2</strong></td>";	
					htmlBet += "<td class='direct' />";	
					htmlBet += "</tr>";

        		  	htmlBet += "<tr>";
        		  	htmlBet += "<td class='odd'><select id='scoreHome'>";
        		  	for (i = 0; i < 10; i++)
        		  	{
						htmlBet += "<option value='" + i + "'";
						if (scoreHome == i)
						{
							htmlBet += " selected='selected'";
						}
						htmlBet += ">" + i + "</option>";
            		}
            		htmlBet += "</select></td>";

					htmlBet += "<td class='odd' >-</td>";
            		
            		htmlBet += "<td class='odd'><select id='scoreAway'>";
        		  	for (i = 0; i < 10; i++)
        		  	{
        		  		htmlBet += "<option value='" + i + "'";
						if (scoreAway == i)
						{
							htmlBet += " selected='selected'";
						}
						htmlBet += ">" + i + "</option>";
            		}
            		htmlBet += "</select></td>";
            		
					htmlBet += "<td class='prono'><input type='button' class='btn btn-primary' value='Ok' onclick='pronostic()'/></td>";
					htmlBet += "</tr>";

					htmlBet += "</table>";
					$("#match").append(htmlBet);	
              }
        	  else
           	  {
        		  $("#bet_form").remove();
              }
          }
    	
		  pronostic = function()
		  {
			  var scoreHome = $("#scoreHome").val();
			  var scoreAway = $("#scoreAway").val();
			  var id = '${match.matchId}';

			  $.ajax(
			  {
		      	method: "POST",
				url: "betAction",
				data: {matchId: id, scoreHome: scoreHome, scoreAway: scoreAway}
			  }).done(function( msg ) 
			  {
				  $("#score-home").html(scoreHome);
				  $("#score-away").html(scoreAway);

				  $("#bet_form").remove();

				  var nbProno = ${matchStat.nbProno};
				  var nbWin = ${matchStat.nbWin};
				  var nbLose = ${matchStat.nbLose};
				  var nbDraw = ${matchStat.nbExact};

				  if (scoreHome > scoreAway)
				  {
			    	  statMatch(nbProno + 1, nbWin + 1, nbLose, nbDraw);
				  }
				  else if (scoreAway > scoreHome)
				  {
			    	  statMatch(nbProno + 1, nbWin, nbLose + 1, nbDraw);
				  }
				  else
				  {
		    	  	  statMatch(nbProno + 1, nbWin, nbLose, nbDraw + 1);
				  }
			  });
		  };

		  post = function()
		  {
			  var matchId = ${match.matchId};
			  var comment = $("#comments").val();
			  
			  $.ajax(
			  {
		      	method: "POST",
				url: "commentAction",
				data: {matchId: matchId, comment: comment}
			  }).done(function( msg ) {
					$("#comments").val("");
					$("#chat").load("commentAction", {matchId: matchId, refresh: "true"}).fadeIn("slow");
			  });
		  }

		  setInterval(function(){
			  var matchId = ${match.matchId};
			  $("#match-direct").load("direct", {matchId: matchId, refresh: "true"}).fadeIn("slow");
			}, 30000);
		  
		  setInterval(function(){
			  var matchId = ${match.matchId};
			  $("#chat").load("commentAction", {matchId: matchId, refresh: "true"}).fadeIn("slow");
			}, 30000);
		</script>
	</body>
</html>    