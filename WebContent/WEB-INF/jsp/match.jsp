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
	                                    <h3 class="widget-title"><span>Terminé</span></h3>
	                                    <div class="widget-content">
	                                    	<ul class="list-unstyled">
	                                           	<c:forEach items="${matchsEnded}" var="match">
	                                           	<li class="format-standard">
	                                            	<table style="margin-left: auto; margin-right: auto; width: 100%">
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
											    			<td class="odd">
											    				<span>3</span>
											    			</td>
											    			<td class="odd">
											    				<span>2</span>
											    			</td>
											    			<td class="odd">
											    				<span>4</span>
											    			</td>
											    			<td class="bet">
											    				<button>Parier</button>
											    			</td>
											    			<td class="direct">
											    				<form method="get" action="sax">
											    					<input type="hidden" name="matchId" value="${match.matchId}" />
										    						<input type="submit" value="Direct" />
									    						</form>
									    					</td>
											    		</tr>
											    	</table>
	                                            </li>
	                                            </c:forEach>
	                                        </ul>
	                                    </div>
	                                    <div class="widget-content">
                                           	<c:forEach items="${matchs}" var="matchDay">
			                                    <h3 class="widget-title"><span>${matchDay.key}</span></h3>
                                           		<c:forEach items="${matchDay.value}" var="match">
			                                    	<ul class="list-unstyled">
			                                           	<li class="format-standard">
			                                            	<table style="margin-left: auto; margin-right: auto; width: 100%">
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
													    			<td class="odd">
													    				<span>3</span>
													    			</td>
													    			<td class="odd">
													    				<span>2</span>
													    			</td>
													    			<td class="odd">
													    				<span>4</span>
													    			</td>
													    			<td class="bet">
													    				<button>Parier</button>
													    			</td>
													    			<td class="direct">
													    				<form method="get" action="sax">
													    					<input type="hidden" name="matchId" value="${match.matchId}" />
												    						<input type="submit" value="Direct" />
											    						</form>
											    					</td>
													    		</tr>
													    	</table>
			                                            </li>
			                                        </ul>
	                                            </c:forEach>
                                        	</c:forEach>
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
                            <li>
                                <div class="widget kp-last-news">
                                    <h2 class="widget-title"><span>Latest news</span></h2>
                                    <div class="widget-content">
                                        <ul class="list-unstyled">
                                            <li class="format-standard">
                                                <div class="kp-item">
                                                    <figure class="zoom-image">
                                                        <a href="#"><img src="placeholders/posts/img-16.jpg" class="img-responsive" alt="">
                                                            <span class="icon-image post-format"></span>
                                                        </a>
                                                    </figure>
                                                    <ul class="kp-metadata clearfix">
                                                        <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                        <li>3 comment</li>
                                                        <li class="kp-time">05 March, 2013</li>
                                                    </ul>
                                                    <!-- kp-metadata -->
                                                    <h3><a href="#">Oprah Winfrey Headed Back to the Big</a></h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula  </p>
                                                </div>
                                                <!-- kp-item -->
                                            </li>
                                            <li class="format-gallery">
                                                <div class="kp-item">
                                                    <figure class="zoom-image">
                                                        <a href="#"><img src="placeholders/posts/img-17.jpg" class="img-responsive" alt="">
                                                            <span class="icon-images post-format"></span>
                                                        </a>
                                                    </figure>
                                                    <ul class="kp-metadata clearfix">
                                                        <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                        <li>3 comment</li>
                                                        <li class="kp-time">05 March, 2013</li>
                                                    </ul>
                                                    <!-- kp-metadata -->
                                                    <h3><a href="#">Best tennis competitions of the year</a></h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula </p>
                                                </div>
                                                <!-- kp-item -->
                                            </li>
                                            <li class="format-video">
                                                <div class="kp-item">
                                                    <figure class="zoom-image">
                                                        <a href="#"><img src="placeholders/posts/img-18.jpg" class="img-responsive" alt="">
                                                            <span class="icon-videocamera post-format"></span>
                                                        </a>
                                                    </figure>
                                                    <ul class="kp-metadata clearfix">
                                                        <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                        <li>3 comment</li>
                                                        <li class="kp-time">05 March, 2013</li>
                                                    </ul>
                                                    <!-- kp-metadata -->
                                                    <h3><a href="#">Oprah Winfrey Headed Back to the Big Screen </a></h3>
                                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula </p>
                                                </div>
                                                <!-- kp-item -->
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
                </div>
                <!-- wraper -->
                <div class="clearfix"></div>
            </div>
            <!-- content -->
        </div>
        
        <footer id="page-footer">
        </footer>
        <!-- page-footer -->


		<div class="prono-form" id="prono-form" title="Pronostic">
		  <form method="post" action="sax">
		    <input type="hidden" id="matchId" value="${match.matchId}" />
		    <table class="prono-table">
			   <tr>
			   		<td/>
			   		<td/>
	    			<td class="prono-header" style="font-weight: bold;">E1</td>
	    			<td style="font-weight: bold;">N</td>
	    			<td style="font-weight: bold;">E2</td>
	    			<td/>
	    			<td/>
    		   </tr>
			   <tr>
	    			<td><img class="prono-img" src="images/team/${match.homeImg}_128.png"></img></td>
	    			<td class="prono-team" style="">${match.homeTeam}</td>
	    			<td class="prono-score" style="">
	    				<c:choose>
		    				<c:when test="${prono == '1'}">
		    					<input type="radio" name="prono" value="1" checked/>
		    				</c:when>
		    				<c:otherwise>
		    					<input type="radio" name="prono" value="1" />
		    				</c:otherwise>
	    				</c:choose>
					</td>
	    			<td class="prono-score">
	    				<c:choose>
		    				<c:when test="${prono == '2'}">
		    					<input type="radio" name="prono" value="2" checked/>
		    				</c:when>
		    				<c:otherwise>
		    					<input type="radio" name="prono" value="2" />
		    				</c:otherwise>
	    				</c:choose>
    				</td>
	    			<td class="prono-score">
	    				<c:choose>
		    				<c:when test="${prono == '3'}">
		    					<input type="radio" name="prono" value="3" checked/>
		    				</c:when>
		    				<c:otherwise>
		    					<input type="radio" name="prono" value="3" />
		    				</c:otherwise>
	    				</c:choose>
					</td>
	    			<td class="prono-team">${match.awayTeam}</td>
	    			<td><img class="prono-img" src="images/team/${match.awayImg}_128.png"></img></td>
	    		</tr>
	    		<tr>
	    			<td/>
	    			<td class="prono-credits" colspan="5">
	    				<input id="credits" type="text" value="${credits}"/> / 10000
	    			</td>
	    			<td/>
	    		</tr>
	    	</table>
		  </form>
		</div>
		
    	<script type="text/javascript">
		  openProno = function()
		  {
			  dialog = $( "#prono-form" ).dialog({
			      autoOpen: false,
			      height: 260,
			      width: 700,
			      modal: true,
			      buttons: {
			        "Valider": function() {
		        	  if ($( "#credits" ).val() <= 10000/2)
					  {
		        		  if ($( "#credits" ).val() == "" || $( "#credits" ).val() == 0)
			        	  {
			        	  	  alert('Vous devez miser des crédits.');	  
			        	  }
		        		  else
	        			  {
		        			  dialog.dialog( "close" );
				        	  pronostic();
	        			  }
					  }
		        	  else
	        		  {
		        	  	  alert('Vous ne pouvez pas miser autant de crédits.');	  
	        		  }
			        },
			        "Annuler" : function() {
			          dialog.dialog( "close" );
			        }
			      }
			    });
			  
			  dialog.dialog( "open" );
		  }
		  
		  pronostic = function()
		  {
			  var matchId = $("#matchId").val();
			  var prono = $("input[name='prono']:checked").val();
			  var credits = $("#credits").val();
			  $.ajax(
			  {
		      	method: "POST",
				url: "sax",
				data: {matchId: matchId, prono: prono, credits: credits}
			  }).done(function( msg ) 
			  {
			  	location.reload()
			  });
		  };
		  
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