<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
										    				<div style="word-wrap: break-word;">${comment.comment}</div>
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