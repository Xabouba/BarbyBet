<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<title>Pronostiquez gratuitement sur les matchs de l'Euro 2016</title>
        <%@include file="import.jsp" %>
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
<%--     	<% response.setIntHeader("Refresh", 120); %> --%>
    	<div class="wraper">
        	<%@include file="header.jsp" %>
    		
            <!-- page-header -->
            <div id="content" class="listMatch">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                    	<div id="sidebar-main-content" class="pull-left sidebar-minimized-rank">
                    		<%@include file='minimized-rank.jsp'%>
                    	</div>
                        <div class="matchs page-single pull-left">
	                        <ul class="list-unstyled">
	                            <li class="clearfix">
	                                <div class="widget kp-review">
	                                    <h2 class="widget-title"><span>Match</span></h2>
	                                    <div class="menu-match">
	                                    	<ul class="nav nav-tabs kp-tabs">
									            <li id="allMatch" >
									                <a href="" onclick="allMatch()">Tous</a>
									            </li>
									            <li id="todayMatch" class="active">
								                    <a href="" onclick="todayMatch()">Aujourd'hui</a>
								                </li>
								                <li id="nextMatch">
								                    <a href="" onclick="nextMatch()">Prochainement</a>
								                </li>
								                <li id="endMatch">
								                    <a href="" onclick="endMatch()">Terminés</a>
								                </li>
								            </ul>
	                                    </div>
	                                    <div id="ended-match" style="display:none">
		                                    <div class="widget-content">
		                                    	<c:if test="${empty matchsEnded}">
			                                    	<ul class="list-unstyled" id="matchs-ended-empty" style="display:none"><li class="format-standard">Aucun match terminé</li></ul>
			                                    </c:if>
		                                    	<c:if test="${not empty matchsEnded}">
		                                    	<ul class="list-unstyled"><li class="format-standard match-title">Terminé</li></ul>
                                           		<ul class="list-unstyled">
		                                           	<c:forEach items="${matchsEnded}" var="match">
		                                           	<li class="format-standard match-info">
			                                           <div>
			                                            	<table class="match-info-table">
															   <tr>
													    			<td class="image">
													    				<img src="images/team/${match.homeImg}_128.png" />
													    			</td>
													    			<td class="team">
													    				<span>${match.homeTeam}</span>
													    			</td>
													    			<td class="score">
													    				<span class="score-home">${match.homeScore}</span>
												    					<br/>
												    					<span class="score-prono-home">
												    						<c:choose>
												    							<c:when test="${match.scoreHome != null}">
												    								(<span id="prono-home_${match.matchId}">${match.scoreHome}</span>
												    							</c:when>
												    							<c:otherwise>
												    								<span id="prono-home_${match.matchId}"></span>
												    							</c:otherwise>
												    						</c:choose>
									    								</span>
													    			</td>
													    			<td class="score">
													    				<span class="score-away">${match.awayScore}</span>
													    				<br/>
												    					<span class="score-prono-away">
												    						<c:choose>
												    							<c:when test="${match.scoreAway != null}">
												    								<span id="prono-away_${match.matchId}">${match.scoreAway}</span>)
												    							</c:when>
												    							<c:otherwise>
												    								<span id="prono-away_${match.matchId}"></span>
												    							</c:otherwise>
												    						</c:choose>
									    								</span>
													    			</td>
													    			<td class="team">
													    				<span>${match.awayTeam}</span>
													    			</td>
													    			<td class="image last">
													    				<img src="images/team/${match.awayImg}_128.png" />
													    			</td>
												    			</tr>
											    			</table>
											    			<table class="bet-info-table" id="match_${match.matchId}">
															   <tr>
													    			<td class="odd">
													    				<c:choose>
													    					<c:when test="${match.prono == '1'}">
															    				<span class="bet">1</span>
													    					</c:when>
													    					<c:otherwise>
															    				<span>1</span>
													    					</c:otherwise>
													    				</c:choose>
													    			</td>
													    			<td class="odd">
													    				<c:choose>
													    					<c:when test="${match.prono == '2'}">
															    				<span class="bet">N</span>
													    					</c:when>
													    					<c:otherwise>
															    				<span>N</span>
													    					</c:otherwise>
													    				</c:choose>
													    			</td>
													    			<td class="odd">
													    				<c:choose>
													    					<c:when test="${match.prono == '3'}">
															    				<span class="bet">2</span>
													    					</c:when>
													    					<c:otherwise>
															    				<span>2</span>
													    					</c:otherwise>
													    				</c:choose>
													    			</td>
													    			<td class="direct">
													    				<form method="get" action="direct">
													    					<input type="hidden" name="matchId" value="${match.matchId}" />
												    						<input class="btn btn-primary" type="submit" value="Direct" />
											    						</form>
											    					</td>
											    					<td class="bet-button">
											    						<span class="ended">X</span>
											    					</td>
													    			<td class="creditsWon">
													    				<c:choose>
													    					<c:when test="${match.creditsWon > 0}">
														    					<span class="positif">+ ${match.creditsWon}</span>
													    					</c:when>
													    					<c:when test="${match.scoreHome == null}">
													    						<span>-</span>
													    					</c:when>
													    					<c:otherwise>
													    						<span class="negatif">0</span>
													    					</c:otherwise>
													    				</c:choose>
													    			</td>
													    		</tr>
													    	</table>
												    	</div>
		                                            </li>
		                                            </c:forEach>
		                                        </ul>
		                                    	</c:if>
		                                    </div>
		                                </div>
		                                <div id="today-match">
		                                    <div class="widget-content">
		                                    	<c:if test="${empty matchsToday}">
			                                    	<ul class="list-unstyled" id="matchs-today-empty" style="display:none"><li class="format-standard">Aucun match aujourd'hui</li></ul>
			                                    </c:if>
	                                           	<c:forEach items="${matchsToday}" var="matchDay">
	                                           		<ul class="list-unstyled"><li class="format-standard match-title">Aujourd'hui - <fmt:formatDate pattern="HH:mm" value="${matchDay.key}" /></li></ul>
	                                           		<c:forEach items="${matchDay.value}" var="match">
				                                    	<ul class="list-unstyled">
				                                           	<li class="format-standard match-info">
					                                            <div>
					                                            	<table class="match-info-table">
																	   <tr>
															    			<td class="image">
															    				<img src="images/team/${match.homeImg}_128.png" />
															    			</td>
															    			<td class="team">
															    				<span>${match.homeTeam}</span>
															    			</td>
															    			<td class="score">
															    				<span class="score-home">${match.homeScore}</span>
														    					<br/>
														    					<span class="score-prono-home">
														    						<c:choose>
														    							<c:when test="${match.scoreHome != null}">
														    								(<span id="prono-home_${match.matchId}">${match.scoreHome}</span>
														    							</c:when>
														    							<c:otherwise>
														    								<span id="prono-home_${match.matchId}"></span>
														    							</c:otherwise>
														    						</c:choose>
											    								</span>
															    			</td>
															    			<td class="score">
															    				<span class="score-away">${match.awayScore}</span>
															    				<br/>
														    					<span class="score-prono-away">
														    						<c:choose>
														    							<c:when test="${match.scoreAway != null}">
														    								<span id="prono-away_${match.matchId}">${match.scoreAway}</span>)
														    							</c:when>
														    							<c:otherwise>
														    								<span id="prono-away_${match.matchId}"></span>
														    							</c:otherwise>
														    						</c:choose>
											    								</span>
															    			</td>
															    			<td class="team">
															    				<span>${match.awayTeam}</span>
															    			</td>
															    			<td class="image last">
															    				<img src="images/team/${match.awayImg}_128.png" />
															    			</td>
														   				</tr>
											    					</table>
											    					<table class="bet-info-table" id="match_${match.matchId}">
															   			<tr>
													    	 				<td class="odd" id="odd_1_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.prono == '1'}">
																	    				<span class="bet">1</span>
															    					</c:when>
															    					<c:otherwise>
																	    				<span>1</span>
															    					</c:otherwise>
															    				</c:choose>
															    			</td>
															    			<td class="odd" id="odd_2_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.prono == '2'}">
																	    				<span class="bet">N</span>
															    					</c:when>
															    					<c:otherwise>
																	    				<span>N</span>
															    					</c:otherwise>
															    				</c:choose>
															    			</td>
															    			<td class="odd" id="odd_3_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.prono == '3'}">
																	    				<span class="bet">2</span>
															    					</c:when>
															    					<c:otherwise>
																	    				<span>2</span>
															    					</c:otherwise>
															    				</c:choose>
															    			</td>
															    			<td class="direct">
															    				<form method="get" action="direct">
															    					<input type="hidden" name="matchId" value="${match.matchId}" />
														    						<input class="btn btn-primary" type="submit" value="Direct" />
													    						</form>
													    					</td>
													    					<c:choose>
														    					<c:when test="${empty match.matchStarted}">
																	    			<td class="bet-button">
																	    				<button class="btn btn-primary" onclick='bet(${match.matchId})'>Parier</button>
																	    			</td>
																    			</c:when>
																    			<c:otherwise>
																	    			<td class="bet-button">
															    						<span class="ended">X</span>
															    					</td>
																    			</c:otherwise>
															    			</c:choose>
														    				<td class="credits">
															    				<span id="bet_credits_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.credits > 0}">
																    					${match.credits}
															    					</c:when>
															    					<c:otherwise>
															    						-
															    					</c:otherwise>
															    				</c:choose>
															    				</span>
															    			</td>
															    		</tr>
															    	</table>
														    	</div>
				                                            </li>
				                                        </ul>
		                                            </c:forEach>
	                                        	</c:forEach>
		                                    </div>
		                                </div>
		                                <div id="next-match" style="display:none">
		                                    <div class="widget-content">
		                                    	<c:if test="${empty matchs}">
			                                    	<ul class="list-unstyled" id="next-matchs-empty" style="display:none"><li class="format-standard">Aucun match à venir</li></ul>
			                                    </c:if>
	                                           	<c:forEach items="${matchs}" var="matchDay">
	                                           		<ul class="list-unstyled"><li class="format-standard match-title"><fmt:formatDate pattern="dd MMMMMMM - HH:mm" value="${matchDay.key}" /></li></ul>
                                           			<c:forEach items="${matchDay.value}" var="match">
				                                    	<ul class="list-unstyled">
				                                           	<li class="format-standard match-info">
				                                            	<div>
					                                            	<table class="match-info-table">
																	   <tr>
															    			<td class="image">
															    				<img src="images/team/${match.homeImg}_128.png" />
															    			</td>
															    			<td class="team">
															    				<span>${match.homeTeam}</span>
															    			</td>
															    			<td class="score">
															    				<span class="score-home">${match.homeScore}</span>
														    					<br/>
														    					<span class="score-prono-home">
														    						<c:choose>
														    							<c:when test="${match.scoreHome != null}">
														    								(<span id="prono-home_${match.matchId}">${match.scoreHome}</span>
														    							</c:when>
														    							<c:otherwise>
														    								<span id="prono-home_${match.matchId}"></span>
														    							</c:otherwise>
														    						</c:choose>
											    								</span>
															    			</td>
															    			<td class="score">
															    				<span class="score-away">${match.awayScore}</span>
															    				<br/>
														    					<span class="score-prono-away">
														    						<c:choose>
														    							<c:when test="${match.scoreAway != null}">
														    								<span id="prono-away_${match.matchId}">${match.scoreAway}</span>)
														    							</c:when>
														    							<c:otherwise>
														    								<span id="prono-away_${match.matchId}"></span>
														    							</c:otherwise>
														    						</c:choose>
											    								</span>
															    			</td>
															    			<td class="team">
															    				<span>${match.awayTeam}</span>
															    			</td>
															    			<td class="image last">
															    				<img src="images/team/${match.awayImg}_128.png" />
															    			</td>
														   				</tr>
											    					</table>
											    					<table class="bet-info-table" id="match_${match.matchId}">
															   			<tr>
															    			<td class="odd" id="odd_1_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.scoreHome > match.scoreAway}">
																	    				<span class="bet">1</span>
															    					</c:when>
															    					<c:otherwise>
																	    				<span>1</span>
															    					</c:otherwise>
															    				</c:choose>
															    			</td>
															    			<td class="odd" id="odd_2_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.scoreHome != null and match.scoreHome == match.scoreAway}">
																	    				<span class="bet">N</span>
															    					</c:when>
															    					<c:otherwise>
																	    				<span>N</span>
															    					</c:otherwise>
															    				</c:choose>
															    			</td>
															    			<td class="odd" id="odd_3_${match.matchId}">
															    				<c:choose>
															    					<c:when test="${match.scoreHome < match.scoreAway}">
																	    				<span class="bet">2</span>
															    					</c:when>
															    					<c:otherwise>
																	    				<span>2</span>
															    					</c:otherwise>
															    				</c:choose>
															    			</td>
															    			<td class="direct">
															    				<form method="get" action="direct">
															    					<input type="hidden" name="matchId" value="${match.matchId}" />
														    						<input type="submit" class="btn btn-primary" value="Direct" />
													    						</form>
													    					</td>
													    					<td class="bet-button">
															    				<button class="btn btn-primary" onclick="bet(${match.matchId})">Parier</button>
															    			</td>
														    				<td class="credits">
													    					<span id="bet_credits_${match.matchId}">
															    				-
														    				</span>
															    			</td>
															    		</tr>
															    	</table>
															    </div>	
				                                            </li>
				                                        </ul>
		                                            </c:forEach>
	                                        	</c:forEach>
		                                    </div>
		                                </div>
	                                    <!-- widget-content -->
	                                </div>
	                                <!-- kp-review -->
	                            </li>
	                        </ul>
	                    </div>
		                <!-- main-col -->
	                    <div class="clearfix"></div>
                        <!-- page-single -->
                    </div>
                    <!-- main-content -->
                </div>
                <!-- wraper -->
                <div class="clearfix"></div>
            </div>
            <!-- content -->
        </div>
        
	    <%@include file="footer.jsp" %>
        <!-- page-footer -->
		
    	<script type="text/javascript">
    	  bet = function(id)
    	  {
    		  var scoreHome = $("#prono-home_" + id).html();
		      var scoreAway = $("#prono-away_" + id).html();
        	  
        	  if ($("#bet_" + id).length == 0)
           	  {
					var htmlBet = "<tr id='bet_" + id + "'>";

        		  	htmlBet += "<td class='odd'><select id='scoreHome_" + id + "'>";
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
            		htmlBet += "<td class='odd'><select id='scoreAway_" + id + "'>";
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
            		
					htmlBet += "<td class='bet-button' ><input type='button' class='btn btn-primary' value='Ok' onclick='pronostic(" + id + ")'/></td>";
					htmlBet += "<td class='direct'></td>";
					htmlBet += "<td class='credits'></td>";	
					htmlBet += "</tr>";

					$("#match_" + id).append(htmlBet);	
              }
        	  else
           	  {
        		  $("#bet_" + id).remove();
              }
          }
    	
		  pronostic = function(id)
		  {
			  var scoreHome = $("#scoreHome_" + id).val();
			  var scoreAway = $("#scoreAway_" + id).val();
			  
			  $.ajax(
			  {
		      	method: "POST",
				url: "betAction",
				data: {matchId: id, scoreHome: scoreHome, scoreAway: scoreAway}
			  }).done(function( msg ) 
			  {
				  if (scoreHome > scoreAway)
				  {
					  $("#odd_1_" + id).children().addClass("bet");
					  $("#odd_2_" + id).children().removeClass("bet");
					  $("#odd_3_" + id).children().removeClass("bet");
				  }
				  else if (scoreHome < scoreAway)
				  {
					  $("#odd_3_" + id).children().addClass("bet");
					  $("#odd_1_" + id).children().removeClass("bet");
					  $("#odd_2_" + id).children().removeClass("bet");
				  }
				  else
			      {
					  $("#odd_2_" + id).children().addClass("bet");
					  $("#odd_1_" + id).children().removeClass("bet");
					  $("#odd_3_" + id).children().removeClass("bet");
				  }

				  if ($("#prono-home_" + id).html() != '')
				  {
				      $("#prono-home_" + id).html(scoreHome);
				  }
				  else
				  {
				      var parent = $("#prono-home_" + id).parent();
				      parent.html("(<span id='prono-home_" + id + "'>" + scoreHome + "</span>");
  				  }

				  if ($("#prono-away_" + id).html() != '')
				  {
				      $("#prono-away_" + id).html(scoreAway);
				  }
				  else
				  {
				      var parent = $("#prono-away_" + id).parent();
				      parent.html("<span id='prono-away_" + id + "'>" + scoreAway + "</span>)");
  				  }
				
				  $("#bet_" + id).remove();
			  });
		  };
		  
		  allMatch = function()
		  {
			  var matchsEndedEmpty = $("#matchs-ended-empty");
			  if(matchsEndedEmpty != null) {
				  matchsEndedEmpty.hide();
			  }
			  
			  var matchsTodayEmpty = $("#matchs-today-empty");
			  if(matchsTodayEmpty != null) {
				  matchsTodayEmpty.hide();
			  }
			  
			  var nextMatchsEmpty = $("#next-matchs-empty");
			  if(nextMatchsEmpty != null) {
				  nextMatchsEmpty.hide();
			  }
			  
			  $("#ended-match").show();
			  $("#today-match").show();
			  $("#next-match").show();
		  }
		  
		  todayMatch = function()
		  {
			  var matchsEndedEmpty = $("#matchs-ended-empty");
			  if(matchsEndedEmpty != null) {
				  matchsEndedEmpty.hide();
			  }
			  
			  var matchsTodayEmpty = $("#matchs-today-empty");
			  if(matchsTodayEmpty != null) {
				  matchsTodayEmpty.show();
			  }
			  
			  var nextMatchsEmpty = $("#next-matchs-empty");
			  if(nextMatchsEmpty != null) {
				  nextMatchsEmpty.hide();
			  }
			  
			  $("#ended-match").hide();
			  $("#today-match").show();
			  $("#next-match").hide();
		  }
		  
		  endMatch = function()
		  {
			  var matchsEndedEmpty = $("#matchs-ended-empty");
			  if(matchsEndedEmpty != null) {
				  matchsEndedEmpty.show();
			  }
			  
			  var matchsTodayEmpty = $("#matchs-today-empty");
			  if(matchsTodayEmpty != null) {
				  matchsTodayEmpty.hide();
			  }
			  
			  var nextMatchsEmpty = $("#next-matchs-empty");
			  if(nextMatchsEmpty != null) {
				  nextMatchsEmpty.hide();
			  }
			  
			  $("#ended-match").show();
			  $("#today-match").hide();
			  $("#next-match").hide();
		  }
		  
		  nextMatch = function()
		  {
			  var matchsEndedEmpty = $("#matchs-ended-empty");
			  if(matchsEndedEmpty != null) {
				  matchsEndedEmpty.hide();
			  }
			  
			  var matchsTodayEmpty = $("#matchs-today-empty");
			  if(matchsTodayEmpty != null) {
				  matchsTodayEmpty.hide();
			  }
			  
			  var nextMatchsEmpty = $("#next-matchs-empty");
			  if(nextMatchsEmpty != null) {
				  nextMatchsEmpty.show();
			  }
			  
			  $("#ended-match").hide();
			  $("#today-match").hide();
			  $("#next-match").show();
		  }
		</script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
        </script>
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
        <script type="text/javascript" src="js/custom.js"></script>
        
        <script type="text/javascript">
    		var hasNoMatchToday = ${empty matchsToday};
        	if (hasNoMatchToday)
           	{
        		nextMatch();
				$("#allMatch").removeClass("active");
				$("#todayMatch").removeClass("active");
				$("#nextMatch").addClass("active");
				$("#endMatch").removeClass("active");
           	}
    	</script>
    </body>
</html>    