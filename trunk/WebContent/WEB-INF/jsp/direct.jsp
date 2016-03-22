<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import.jsp" %>
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
    	<% response.setIntHeader("Refresh", 120); %>
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
                    <div id="sidebar-main-content" class="pull-left">
                        <ul class="list-unstyled classement">
                            <li class="clearfix">
                                <div class="widget kp-review">
                                    <h2 class="widget-title"><span>Classement</span></h2>
                                    <div class="widget-content">
	                                    <select style="width: 100%; margin-bottom: 2px;">
										  <option value="group1">Groupe 1</option>
										  <option value="group2">Groupe 2</option>
										  <option value="group3">Groupe 3</option>
										  <option value="group4">Groupe 4</option>
										</select>
										<ul class="list-unstyled">
                                            <li class="format-standard">
                                            	<table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">1</td>
                                            			<td class="td_rank_progress">=</td>
                                            			<td class="td_rank_name">User 2</td>
                                            			<td class="td_rank_credit">15250</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                            <li class="format-standard">
                                                <table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">2</td>
                                            			<td class="td_rank_progress">+2</td>
                                            			<td class="td_rank_name">User 3</td>
                                            			<td class="td_rank_credit">13250</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                            <li class="format-standard">
                                                <p style="text-align: center">..........................................................</p>
                                            </li>
                                            <li class="format-standard">
                                                <table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">5</td>
                                            			<td class="td_rank_progress">-1</td>
                                            			<td class="td_rank_name">User 4</td>
                                            			<td class="td_rank_credit">8250</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                            <li class="format-standard">
                                                <table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">6</td>
                                            			<td class="td_rank_progress">=</td>
                                            			<td class="td_rank_name">User 5</td>
                                            			<td class="td_rank_credit">7280</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                            <li class="format-standard">
                                                <table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">7</td>
                                            			<td class="td_rank_progress">+3</td>
                                            			<td class="td_rank_name" style="color: red">User 1</td>
                                            			<td class="td_rank_credit">7250</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                            <li class="format-standard">
                                                <table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">8</td>
                                            			<td class="td_rank_progress">-2</td>
                                            			<td class="td_rank_name">User 6</td>
                                            			<td class="td_rank_credit">5420</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                            <li class="format-standard">
                                                <table style="width: 100%">
                                            		<tr>
                                            			<td class="td_rank_nb">9</td>
                                            			<td class="td_rank_progress">+1</td>
                                            			<td class="td_rank_name">User 7</td>
                                            			<td class="td_rank_credit">3250</td>
                                            		</tr>
                                            	</table>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-story -->
                            </li>
                        </ul>
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
	                                            		<h1 class="result-title" style="text-align: center">Ligue 1</h1>
												    	
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
                                    <h2 class="widget-title"><span>Chat</span></h2>
                                    <div class="widget-content">
                                    	<ul class="list-unstyled" style="max-height:600px; overflow:auto;">
                                    		<c:forEach items="${comments}" var="comment">
                                            	<li class="format-standard">
                                            	<table style="margin-left: auto; margin-right: auto; width: 100%">
												   <tr style="width: 100%">
										    			<td style="width: 20%;">
										    				<p><strong>${comment.user}</strong></p>
										    				<p style="color: green">
										    					<strong>
											    					<c:choose>
																		<c:when test="${comment.prono=='1'}">
																	    	${match.homeTeam}
																	    </c:when>
																		<c:when test="${comment.prono=='2'}">
																	    	nul
																	    </c:when>
																		<c:when test="${comment.prono=='3'}">
																	    	${match.awayTeam}
																	    </c:when>
																	</c:choose>
																</strong>
															</p>
										    			</td>
										    			<td style="width: 65%;">
										    				<div style="width: 300px; word-wrap: break-word;">${comment.comment}</div>
										    			</td>
										    			<td style="width: 15%; text-align: center;">
										    				<p>${comment.hour}</p>
										    				<p>${comment.day}</p>
										    			</td>
										    		</tr>
										    	</table>
                                            </li>
											</c:forEach>
                                        </ul>
                                        <br/>
                                        <form action="sax" method="post">
										  <label for="comments">Message: </label>
										  <textarea style="width: 100%;" rows="5" name="comment" id="comments" maxlength="450" ></textarea>
										  <input type="hidden" name="matchId" value="${match.matchId}" />
										  <input class="btn btn-primary" type="submit" value="Envoyer" />
										</form>
                                       
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
            	<div id="sidebar" class="pull-right information">
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
    	<div class="page-footer">
    	</div>
    	
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
				url: "direct",
				data: {matchId: id, scoreHome: scoreHome, scoreAway: scoreAway}
			  }).done(function( msg ) 
			  {
				  $("#score-home").html(scoreHome);
				  $("#score-away").html(scoreAway);

				  $("#bet_form").remove();
			  });
		  };
		  
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