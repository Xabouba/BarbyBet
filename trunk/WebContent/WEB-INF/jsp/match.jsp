<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <!-- page-header -->
            <div id="content">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                        <div class="page-single listMatch">
	                        <ul class="list-unstyled">
	                            <li class="clearfix">
	                                <div class="widget kp-review">
	                                    <h2 class="widget-title"><span>Match</span></h2>
	                                    <div class="menu-match">
	                                    	<ul class="nav nav-tabs kp-tabs">
									            <li class="active">
									                <a href="" onclick="allMatch()">Tous</a>
									            </li>
									            <li>
								                    <a href="" onclick="todayMatch()">Aujourd'hui</a>
								                </li>
								                <li>
								                    <a href="" onclick="nextMatch()">Prochainement</a>
								                </li>
								                <li>
								                    <a href="" onclick="endMatch()">Terminés</a>
								                </li>
								            </ul>
	                                    </div>
	                                    <div id="ended-match">
		                                    <div class="widget-content">
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
													    				<span>${match.homeScore}</span>
													    			</td>
													    			<td class="score">
													    				<span>${match.awayScore}</span>
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
													    				<form method="get" action="sax">
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
		                                <div id="today-match">
		                                    <div class="widget-content">
	                                           	<c:forEach items="${matchsToday}" var="matchDay">
	                                           		<ul class="list-unstyled"><li class="format-standard match-title">Aujourd'hui - ${matchDay.key}</li></ul>
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
															    				<span>${match.homeScore}</span>
															    			</td>
															    			<td class="score">
															    				<span>${match.awayScore}</span>
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
															    				<form method="get" action="sax">
															    					<input type="hidden" name="matchId" value="${match.matchId}" />
														    						<input class="btn btn-primary" type="submit" value="Direct" />
													    						</form>
													    					</td>
															    			<td class="bet-button">
															    				<button class="btn btn-primary" onclick='bet(${match.matchId})'>Parier</button>
															    			</td>
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
		                                <div id="next-match">
		                                    <div class="widget-content">
	                                           	<c:forEach items="${matchs}" var="matchDay">
	                                           		<ul class="list-unstyled"><li class="format-standard match-title">${matchDay.key}</li></ul>
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
															    				<span>${match.homeScore}</span>
															    			</td>
															    			<td class="score">
															    				<span>${match.awayScore}</span>
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
															    				<form method="get" action="sax">
															    					<input type="hidden" name="matchId" value="${match.matchId}" />
														    						<input type="submit" class="btn btn-primary" value="Direct" />
													    						</form>
													    					</td>
													    					<td class="bet-button">
															    				<button class="btn btn-primary" onclick="bet(${match.matchId})">Parier</button>
															    			</td>
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
		
    	<script type="text/javascript">
    	  bet = function(id)
    	  {
        	  if ($("#bet_" + id).length == 0)
           	  {
					var htmlBet = "<tr id='bet_" + id + "'>";
					htmlBet += "<td class='odd'><input type='radio' name='bet_" + id + "' value='1'/></td>";	
					htmlBet += "<td class='odd'><input type='radio' name='bet_" + id + "' value='2'/></td>";	
					htmlBet += "<td class='odd'><input type='radio' name='bet_" + id + "' value='3'/></td>";	
					htmlBet += "<td class='bet-button'><input type='text' id='credits_" + id + "' style='width:50px' /></td>";
					htmlBet += "<td class='direct'><input type='button' class='btn btn-primary' value='Ok' onclick='openProno(" + id + ")'/></td>";
					htmlBet += "<td class='credits'></td>";	
					htmlBet += "</tr>";

					$("#match_" + id).append(htmlBet);	
              }
        	  else
           	  {
        		  $("#bet_" + id).remove();
              }
          }
    	
		  openProno = function(id)
		  {
			  var prono = $("input[name='bet_" + id + "']:checked").val();
			  var credits = $( "#credits_" + id ).val();

			  if (prono == null)
			  {
				 alert("Vous devez faire un choix");
				 return;
			  }
			  
       		  if (credits == "" || credits == 0)
        	  {
        	  	  alert('Vous devez miser des crédits.');	  
        	  }
        	  else if (credits > 1000) //TODO
       		  {
        	  	  alert('Vous ne pouvez pas miser autant de crédits.');	  
    		  }
       		  else
      		  {
	         	  pronostic(id);
      		  }
		  }
		  
		  pronostic = function(id)
		  {
			  var prono = $("input[name='bet_" + id + "']:checked").val();
			  var credits = $("#credits_" + id).val();
			  $.ajax(
			  {
		      	method: "POST",
				url: "sax",
				data: {matchId: id, prono: prono, credits: credits}
			  }).done(function( msg ) 
			  {
				  for (var i = 1; i <= 3; i++)
				  {
					  if (i == prono)
					  {
						  $("#odd_" + i + "_" + id).addClass("bet");
					  }
					  else
					  {
						  $("#odd_" + i + "_" + id).removeClass("bet");
					  }
				  }

				  $("#bet_credits_" + id).html(credits);
				  $("#bet_" + id).remove();
			  });
		  };
		  
		  allMatch = function()
		  {
			  $("#ended-match").show();
			  $("#today-match").show();
			  $("#next-match").show();
		  }
		  
		  todayMatch = function()
		  {
			  $("#ended-match").hide();
			  $("#today-match").show();
			  $("#next-match").hide();
		  }
		  
		  endMatch = function()
		  {
			  $("#ended-match").show();
			  $("#today-match").hide();
			  $("#next-match").hide();
		  }
		  
		  nextMatch = function()
		  {
			  $("#ended-match").hide();
			  $("#today-match").hide();
			  $("#next-match").show();
		  }

			var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

			var barChartData = {
				labels : ["Pronostic du match"],
				datasets : [
					{
						fillColor : "rgba(0,0,220,0.6)",
						strokeColor : "rgba(0,0,220,0.8)",
						highlightFill: "rgba(0,0,220,0.5)",
						highlightStroke: "rgba(0,0,220,0.8)",
						data : [45]
					},
					{
						fillColor : "rgba(160,160,160,0.6)",
						strokeColor : "rgba(160,160,160,0.8)",
						highlightFill: "rgba(160,160,160,0.5)",
						highlightStroke: "rgba(160,160,160,0.8)",
						data : [25]
					},
					{
						fillColor : "rgba(90,90,220,0.6)",
						strokeColor : "rgba(90,90,220,0.8)",
						highlightFill: "rgba(90,90,220,0.5)",
						highlightStroke: "rgba(90,90,220,0.8)",
						data : [30]
					}
				]

			}
			window.onload = function(){
				var ctx = document.getElementById("canvas").getContext("2d");
				window.myBar = new Chart(ctx).Bar(barChartData, {
					responsive : true,
				    scaleShowGridLines : false,
				    scaleShowVerticalLines: false,
				    scaleShowHorizontalLines: false
				});
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
    </body>
</html>    