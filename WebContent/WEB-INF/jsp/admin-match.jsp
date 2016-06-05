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
		                                    	<c:if test="${empty matchsEnded}">
			                                    	<ul class="list-unstyled" id="matchs-ended-empty" style="display:none"><li class="format-standard">Aucun match terminé</li></ul>
			                                    </c:if>
		                                    	<c:if test="${not empty matchsEnded}">
		                                    	<ul class="list-unstyled"><li class="format-standard match-title">Terminé</li></ul>
                                           		<ul class="list-unstyled">
		                                           	<c:forEach items="${matchsEnded}" var="match">
		                                           	<li class="format-standard match-info">
			                                           	<div>
			                                        		<form method="post" action="adminmatch">
				                                            	<table class="match-info-table">
																   <tr>
														    			<td class="image">
														    				<img src="images/team/${match.homeImg}_128.png" />
														    			</td>
														    			<td class="team">
														    				<span>${match.homeTeam}</span>
														    			</td>
														    			<td class="score">
																    		<input type="text" style="width:20px !important" name="score-home" value="${match.homeScore}" />
													    					<br/>
														    			</td>
														    			<td class="score">
																    		<input type="text" style="width:20px !important" name="score-away" value="${match.awayScore}" />
														    				<br/>
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
														    			<td class="direct">
													    					<input type="hidden" name="matchId" value="${match.matchId}" />
												    						<input class="btn btn-primary" type="submit" value="Update" />
												    					</td>
														    		</tr>
														    	</table>
														    </form>
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
					                                            	<form method="post" action="adminmatch">
																    	<table class="match-info-table">
																		   <tr>
																    			<td class="image">
																    				<img src="images/team/${match.homeImg}_128.png" />
																    			</td>
																    			<td class="team">
																    				<span>${match.homeTeam}</span>
																    			</td>
																    			<td class="score">
																    				<input type="text" style="width:20px !important" name="score-home" value="${match.homeScore}" />
															    					<br/>
																    			</td>
																    			<td class="score">
																    				<input type="text" style="width:20px !important" name="score-away" value="${match.awayScore}" />
																    				<br/>
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
																    			<td class="direct">
																    				<input type="hidden" name="matchId" value="${match.matchId}" />
															    					<input class="btn btn-primary" type="submit" value="Update" />
														    					</td>
																    		</tr>
																    	</table>
															   		</form>
															    </div>
				                                            </li>
				                                        </ul>
		                                            </c:forEach>
	                                        	</c:forEach>
		                                    </div>
		                                </div>
		                                <div id="next-match">
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
				                                            	<form method="post" action="adminmatch">
															    				
					                                            	<table class="match-info-table">
																	   <tr>
															    			<td class="image">
															    				<img src="images/team/${match.homeImg}_128.png" />
															    			</td>
															    			<td class="team">
															    				<span>${match.homeTeam}</span>
															    			</td>
															    			<td class="score">
															    				<input type="text" style="width:20px !important" name="score-home" value="${match.homeScore}" />
														    					<br/>
															    			</td>
															    			<td class="score">
															    				<input type="text" style="width:20px !important" name="score-away" value="${match.awayScore}" />
															    				<br/>
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
															    			<td class="direct">
															    				<input type="hidden" name="matchId" value="${match.matchId}" />
														    					<input type="submit" class="btn btn-primary" value="Update" />
													    					</td>
															    		</tr>
															    	</table>
															   	</form>
															    	
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
    </body>
</html>    