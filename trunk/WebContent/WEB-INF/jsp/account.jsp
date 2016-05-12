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
            <div id="content" class="account">
                <div class="top-effect clearfix">
                    <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                    <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                </div>
                <!-- top-effect -->
                <div id="main-content" class="pull-left">
                    <div id="sidebar-main-content" class="pull-left">
                    	<%@include file='minimized-rank.jsp'%>
                    </div>
                    <!-- sidebar-main-content -->
                    <div id="main-col" class="page-single pull-left kp-review">
                        <ul class="list-unstyled">
                            <li class="clearfix">
                                <div class="widget kp-main-news next-prono">
                                   <h2 class="widget-title"><span>Prochains pronostics</span></h2>
                                    <div class="widget-content">
                                    <c:if test="${empty nextProno}">
                                    	<ul class="list-unstyled"><li class="format-standard">Aucun prochain pronostic</li></ul>
                                    </c:if>
                                    <c:forEach items="${nextProno}" var="match">
                                        <ul class="list-unstyled">
                                          	<li class="format-standard match-info">
                                            <div class="${isCurrentUser == 'true' ? 'current-user' : ''}">
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
										    			<td class="creditsWon" />
									   				</tr>
						    					</table>
						    					<c:if test="${isCurrentUser == 'true'}">
						    					<table class="bet-info-table" id="match_${match.matchId}">
										   			<tr>
								    	 				<td class="odd" id="odd_1_${match.matchId}">
										    				<c:choose>
										    					<c:when test="${match.prono == '1'}">
												    				<span class="bet">${match.homeOdd}</span>
										    					</c:when>
										    					<c:otherwise>
												    				<span>${match.homeOdd}</span>
										    					</c:otherwise>
										    				</c:choose>
										    			</td>
										    			<td class="odd" id="odd_2_${match.matchId}">
										    				<c:choose>
										    					<c:when test="${match.prono == '2'}">
												    				<span class="bet">${match.drawOdd}</span>
										    					</c:when>
										    					<c:otherwise>
												    				<span>${match.drawOdd}</span>
										    					</c:otherwise>
										    				</c:choose>
										    			</td>
										    			<td class="odd" id="odd_3_${match.matchId}">
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
										    				<button class="btn btn-primary" onclick='bet(${match.matchId})'>Parier</button>
										    			</td>
										    		</tr>
										    	</table>
										    	</c:if>
									    	</div>
                                           </li>
                                       </ul>
                                     </c:forEach>
                                     </div> 
                                  </div>
                                  <!-- widget-content -->
                            </li>
                            <li class="clearfix">
                                <div class="widget kp-review last-prono">
                                    <h2 class="widget-title"><span>Derniers pronostics</span></h2>
                                    <div class="widget-content">
                                    <c:if test="${empty pastProno}">
                                    	<ul class="list-unstyled"><li class="format-standard">Aucun dernier pronostic</li></ul>
                                    </c:if>
                                    <c:forEach items="${pastProno}" var="match">
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
												    			<td class="creditsWon">
												    				<c:choose>
												    					<c:when test="${match.creditsWon > 0}">
													    					<span class="positif">+ ${match.creditsWon}</span>
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
	                                        </ul>
                                       </c:forEach>
                                    </div> 
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
                                    		<p class="user-title">${userLogin}</p>
                                    		<p class="user-info">
                                    			<span class="user-rank">
                                    				<c:choose>
                                    					<c:when test="${userRank == 1}">${userRank}er</c:when>
                                    					<c:otherwise>${userRank}ème</c:otherwise>
                                    				</c:choose>
                                    			</span>
                                    			-
                                    			<span class="user-point">${userPoint}
                                    				<c:choose>
                                    					<c:when test="${userPoint > 1}">points</c:when>
                                    					<c:otherwise>point</c:otherwise>
                                    				</c:choose>
                                    			</span>
                                    		</p>
                                    		<div class="user-stat">
	                                    		<canvas id="chart-area" width="150" height="150"></canvas>
                                    		</div>
                                    	</li>
                                   	</ul>
                                </div>
                                <!-- widget-content -->
                            </div>
                            <!-- kp-last-news -->
                        </li>
                    </ul>
                    <ul class="clearfix list-unstyled">
                        <li>
                            <div class="widget widget-kp-tab">
                                <h2 class="widget-title"><span>Groupes</span></h2>
                                <div class="widget-content">
                                   <div class="tab-content">
                                       <div class="tab-pane active" id="tab_popular">
                                           <ul class="list-unstyled">
                                           	 <c:forEach items="${groups}" var="userGroup" varStatus="i">
                                               <li>
                                                   <div class="kp-group clearfix">
                                                       <span>${i.index}</span>
                                                       <a href="#">${userGroup.value.name}</a>
                                                       <ul class="kp-metadata clearfix">
                                                           <li>
                                                           	<c:choose>
                                                           		<c:when test="${userGroup.value.rank > 1}">${userGroup.value.rank}ème</c:when>
                                                           		<c:otherwise>${userGroup.value.rank}er</c:otherwise>
                                                           	</c:choose>
                                                           	&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp
                                                           </li>
                                                           <li>
                                                           	10 points
                                                           </li>
                                                       </ul>
                                                   </div>
                                               </li>
                                             </c:forEach>
                                         	</ul>
                                        </div>
                                     </div>
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

		  var nbProno = ${userStat.nbProno};
		  var nbWin = ${userStat.nbWin};
		  var nbLose = ${userStat.nbLose};
		  var nbExact = ${userStat.nbExact};

		  if (nbProno != 0)
		  {
			  var win = Math.floor(nbWin / nbProno * 100);
			  var lose = Math.floor(nbLose / nbProno * 100);
			  var exact = Math.floor(nbExact / nbProno * 100);
			  
			  var pieData = [
				{
					value: 20,//win,
					color: "#0000FF",
					highlight: "#55E451",
					label: "Prono réussi"
				},
				{
					value: 10,//exact,
					color: "#F7F2B4",
					highlight: "#5AD3D1",
					label: "Prono exact"
				},
				{
					value: 50,//lose,
					color: "#FF0000",
					highlight: "#889D88",
					label: "Prono raté"
				}
			];
				
			window.onload = function(){
				var ctx = document.getElementById("chart-area").getContext("2d");
				window.myPie = new Chart(ctx).Pie(pieData);
			};
		  }
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