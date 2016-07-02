<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		                        	<li class="clearfix">
		                        		
		                                <div class="widget kp-review">
	                                    	<div class="group-rank" id="group-rank">
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
			                                            <c:forEach items="${ranks}" var="team" varStatus="j">
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
		                                </div>
		                            </li>
		                            <li class="clearfix">
		                                <div class="widget kp-review">
		                                    <h2 class="widget-title"><span>Match</span></h2>
	                                    	<div id="group-match" >
			                                    <div id="next-match">
				                                    <div class="widget-content">
			                                           	<c:forEach items="${group.matchs}" var="matchDay">
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
															    						<c:choose>
																    						<c:when test="${not empty match.matchStarted}">
																    							<span class="ended">X</span>
																    						</c:when>
																    						<c:otherwise>
																			    				<button class="btn btn-primary" onclick="bet(${match.matchId})">Parier</button>
																    						</c:otherwise>
															    						</c:choose>
																	    			</td>
																    				<td class="credits">
																    					<c:choose>
																    						<c:when test="${match.ended}">
																    							<c:choose>
																			    					<c:when test="${match.creditsWon > 0}">
																				    					<span class="positif">+ ${match.creditsWon}</span>
																			    					</c:when>
																			    					<c:when test="${match.creditsWon == 0}">
																				    					<span class="negatif">${match.creditsWon}</span>
																			    					</c:when>
																			    					<c:otherwise>
																			    						<span>-</span>
																			    					</c:otherwise>
																			    				</c:choose>
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
						                                        </ul>
				                                            </c:forEach>
			                                        	</c:forEach>
				                                    </div>
				                                </div>
			                                </div>
		                                </div>
		                            </li>