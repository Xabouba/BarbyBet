<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
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
	                                            	<div style="margin-left: auto; margin-right: auto;">
	                                            		<h1 class="result-title" style="text-align: center">Euro 2016</h1>
												    	
												        <p class="result-statut" style="text-align: center">${match.statut}</p>
  												    	<br/>
												    	<br/>
												    	<table style="margin-left: auto; margin-right: auto;">
														   <tr>
												    			<td><img class="result-img" src="images/team/${match.homeImg}_128.png"></img></td>
												    			<td class="result-team">${match.homeTeam}</td>
												    			<td class="result-score">${match.homeScore}</td>
												    			<td class="result-score">${match.awayScore}</td>
												    			<td class="result-team">${match.awayTeam}</td>
												    			<td><img class="result-img" src="images/team/${match.awayImg}_128.png"></img></td>
												    		</tr>
												    	</table>
												    	<br/>
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
                                    <h2 class="widget-title"><span>Commentaire</span></h2>
                                    <div id="chat" class="widget-content">
                                   		<%@include file='chat.jsp'%>
                                    </div>
								  	<label for="comments">Message: </label>
								 	<textarea style="width: 100%;" rows="5" name="comment" id="comments" maxlength="450" ></textarea>
									<input class="btn btn-primary" type="submit" value="Envoyer" onclick="post()"/>
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
			                                    				<strong>${cookie.cookieUsername.value}</strong>
															</td>
			                                    			<td class="bet">
		                                    					<input class="btn btn-primary" type="button" value="Pronostiquer" onclick="bet()" />
			                                    			</td>
			                                    		</tr>
			                                    		<tr>
			                                    			<td class="prono-title">Pronostique:</td>
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
    	
    	<script type="text/javascript">
		  window.onload = function () {
			var homeTeam = "${match.homeTeam}";
			var awayTeam = "${match.awayTeam}";
		    var homeOdd = "${match.homeOdd}";
			var drawOdd = "${match.drawOdd}";
			var awayOdd = "${match.awayOdd}";
		    var chart = new CanvasJS.Chart("info-match", {
		      
		    	axisX: {
		            title: "v:" + homeOdd + " -- n:" + drawOdd + " -- d:" + awayOdd + ""
		          },
	    		data: [             
		        {
		         type: "column",
		         toolTipContent: "<p>{label} : {y}%</p>",
		         dataPoints: [
		         { label: homeTeam, y: 70 },
		         { label: "nul", y: 20 },
		         { label: awayTeam, y: 10 }
		         ]
		       }
		       ]
		     });
		
		    chart.render();
		  }
		  
		  bet = function()
    	  {
			  var scoreHome = $("#score-home").html();
			  var scoreAway = $("#score-away").html();
	    	  
        	  if ($("#bet_form").length == 0)
           	  {
					var htmlBet = "<table class='table_bet' id='bet_form'>";
					htmlBet += "<tr>";
					htmlBet += "<td class='odd'><strong>E1</strong></td>";	
					htmlBet += "<td class='odd'><strong>N</strong></td>";	
					htmlBet += "<td class='odd'><strong>E2</strong></td>";	
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

// 		  setInterval(function(){
// 			  var matchId = ${match.matchId};
// 			  $("#chat").load("commentAction", {matchId: matchId, refresh: "true"}).fadeIn("slow");
// 			}, 2000);
		</script>
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
	</body>
</html>    