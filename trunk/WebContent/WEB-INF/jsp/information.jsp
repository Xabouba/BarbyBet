<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import.jsp" %>
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
<%--     	<% response.setIntHeader("Refresh", 120); %> --%>
    	<div class="wraper">
        	<%@include file="header.jsp" %>
    		
            <!-- page-header -->
            <div id="content">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                        <div class="page-single information">
		                        <ul class="list-unstyled">
		                        	<li class="clearfix">
		                                <div class="widget kp-review">
		                                    <h2 class="widget-title"><span>Classement</span></h2>
		                                    <div class="menu-match">
		                                    	<ul class="nav nav-tabs kp-tabs">
													<c:forEach items="${groups}" var="group" varStatus="i">
														<c:choose>
															<c:when test="${i.index == 0}">
				                    			            	<li id="nav-tab-${i.index}" class="active">
												                	<a onclick="changeGroup(${i.index})" href="">${group.key}</a>
												            	</li>
															</c:when>
															<c:otherwise>
																<li id="nav-tab-${i.index}">
												                	<a onclick="changeGroup(${i.index})" href="">${group.key}</a>
												            	</li>
															</c:otherwise>
														</c:choose>
									                </c:forEach>
									            </ul>
		                                    </div>
			                        		<c:forEach items="${groups}" var="group" varStatus="i">
		                                    	<div class="group-rank" id="group-rank-${i.index}">
                                    				<div class="widget-content">
		                                           		<ul class="list-unstyled">
		                                           			<li class="format-standard rank-info">
					                                           <div>
					                                            	<table class="rank-info-table rank-info-table-title">
																	   <tr>
																	   		<td class="rank">
															    				<span>Rang</span>
															    			</td>
															    			<td class="image">
															    			</td>
															    			<td class="team">
															    				<span>Equipe</span>
															    			</td>
															    			<td class="point">
															    				<span>P.</span>
															    			</td>
															    			<td class="play">
															    				<span>J.</span>
															    			</td>
															    			<td class="win">
															    				<span>V.</span>
															    			</td>
															    			<td class="draw">
															    				<span>N.</span>
															    			</td>
															    			<td class="lost">
															    				<span>D.</span>
															    			</td>
															    			<td class="goal">
															    				<span>B. M.</span>
															    			</td>
															    			<td class="goal-taken">
															    				<span>B. E.</span>
															    			</td>
															    			<td class="diff">
															    				<span>Diff</span>
															    			</td>
														    			</tr>
													    			</table>
														    	</div>
				                                            </li>
				                                            <c:forEach items="${ranks.get(group.key)}" var="team" varStatus="j">
					                                           	<li class="format-standard rank-info">
						                                           <div>
						                                            	<table class="rank-info-table">
																		   <tr>
																		   		<td class="rank rank-number">
																    				<span>${j.index + 1}</span>
																    			</td>
																    			<td class="image">
																    				<img src="images/team/${team.value.img}_128.png" />
																    			</td>
																    			<td class="team">
																    				<span>${team.value.name}</span>
																    			</td>
																    			<td class="point">
																    				<span>${team.value.win * 3 + team.value.draw}</span>
																    			</td>
																    			<td class="play">
																    				<span>${team.value.win + team.value.draw + team.value.lost}</span>
																    			</td>
																    			<td class="win">
																    				<span>${team.value.win}</span>
																    			</td>
																    			<td class="draw">
																    				<span>${team.value.draw}</span>
																    			</td>
																    			<td class="lost">
																    				<span>${team.value.lost}</span>
																    			</td>
																    			<td class="goal">
																    				<span>${team.value.goal}</span>
																    			</td>
																    			<td class="goal-taken">
																    				<span>${team.value.taken}</span>
																    			</td>
																    			<td class="diff">
																    				<span>${team.value.goal - team.value.taken }</span>
																    			</td>
															    			</tr>
														    			</table>
															    	</div>
					                                            </li>
				                                            </c:forEach>
				                                        </ul>
				                                    </div>
				                                </div>
		                                	</c:forEach>
		                                </div>
		                            </li>
		                            <li class="clearfix">
		                                <div class="widget kp-review">
		                                    <h2 class="widget-title"><span>Match</span></h2>
		                                    <c:forEach items="${groups}" var="group" varStatus="i">
		                                    	<div id="group-match-${i.index }" >
				                                    <div id="ended-match">
					                                    <div class="widget-content">
					                                    	<c:if test="${not empty group.value.matchsEnded}">
					                                    	<ul class="list-unstyled"><li class="format-standard match-title">Terminé</li></ul>
			                                           		<ul class="list-unstyled">
					                                           	<c:forEach items="${group.value.matchsEnded.value}" var="match">
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
																		    				<span class="bet">${match.homeOdd}</span>
																    					</c:when>
																    					<c:otherwise>
																		    				<span>${match.homeOdd}</span>
																    					</c:otherwise>
																    				</c:choose>
																    			</td>
																    			<td class="odd">
																    				<c:choose>
																    					<c:when test="${match.prono == '2'}">
																		    				<span class="bet">${match.drawOdd}</span>
																    					</c:when>
																    					<c:otherwise>
																		    				<span>${match.drawOdd}</span>
																    					</c:otherwise>
																    				</c:choose>
																    			</td>
																    			<td class="odd">
																    				<c:choose>
																    					<c:when test="${match.prono == '3'}">
																		    				<span class="bet">${match.awayOdd}</span>
																    					</c:when>
																    					<c:otherwise>
																		    				<span>${match.awayOdd}</span>
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
																    					<c:when test="${match.creditsWon < 0}">
																	    					<span class="negatif">- ${-match.creditsWon}</span>
																    					</c:when>
																    					<c:otherwise>
																    						<span>-</span>
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
					                                <div id="next-match">
					                                    <div class="widget-content">
				                                           	<c:forEach items="${group.value.matchs}" var="matchDay">
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
																				    				<span class="bet">${match.homeOdd}</span>
																		    					</c:when>
																		    					<c:otherwise>
																				    				<span>${match.homeOdd}</span>
																		    					</c:otherwise>
																		    				</c:choose>
																		    			</td>
																		    			<td class="odd" id="odd_2_${match.matchId}">
																		    				<c:choose>
																		    					<c:when test="${match.scoreHome != null and match.scoreHome == match.scoreAway}">
																				    				<span class="bet">${match.drawOdd}</span>
																		    					</c:when>
																		    					<c:otherwise>
																				    				<span>${match.drawOdd}</span>
																		    					</c:otherwise>
																		    				</c:choose>
																		    			</td>
																		    			<td class="odd" id="odd_3_${match.matchId}">
																		    				<c:choose>
																		    					<c:when test="${match.scoreHome < match.scoreAway}">
																				    				<span class="bet">${match.awayOdd}</span>
																		    					</c:when>
																		    					<c:otherwise>
																				    				<span>${match.awayOdd}</span>
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
				                                </div>
			                                </c:forEach>
		                                    
<!-- 		                                    widget-content -->
		                                </div>
<!-- 		                                kp-review -->
		                            </li>
		                        </ul>
	                    </div>
		                <!-- main-col -->
	                    <div class="clearfix"></div>
                        <!-- page-single -->
                    </div>
                    <!-- main-content -->

                    <div id="sidebar" class="pull-right">
                        <ul class="clearfix list-unstyled">
                            <li class="clearfix">
                                <div class="widget kp-review">
                                    <h2 class="widget-title"><span>Classement</span></h2>
                                    <div class="widget-content">
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

                    <!-- sidebar -->
                </div>
                <!-- wraper -->
                <div class="clearfix"></div>
            </div>
            <!-- content -->
        </div>
        
        <footer id="page-footer">
        </footer>
        <!-- page-footer -->
		
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
				url: "match",
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

		  changeGroup = function(id)
		  {
			var count = "${groups.length}";
		  	for (var i = 0; i < 3; i++)
			{
		    	if (i == id)
			    {
		    		$("#group-rank-" + i).show();
		    		$("#group-match-" + i).show();
			    }
		    	else
			    {
			 		$("#group-rank-" + i).hide();
			 		$("#group-match-" + i).hide();
			    }
			}
		  }
		  changeGroup(0);
		  
		</script>
    </body>
</html>    