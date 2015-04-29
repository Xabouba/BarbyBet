<%@ include file="header.jsp" %>
            <!-- page-header -->
            <div id="content">
                <div class="top-effect clearfix">
                    <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                    <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                </div>
                <!-- top-effect -->
                <div id="main-content" class="pull-left">
                    <div id="sidebar-main-content" class="pull-left">
                        <ul class="list-unstyled">
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
                            <li class="clearfix">
                                <div class="widget kp-social">
                                    <h2 class="widget-title"><span>Réseaux</span></h2>
                                    <div class="widget-content">
                                        <ul class="list-unstyled">
                                            <li class="format-standard">
                                                <span>
                                                    <a href="http://facebook.com/kopatheme" class="icon-facebook-2"></a>1234
                                                </span>
                                                Likes
                                            </li>
                                            <li class="format-standard">
                                                <span>
                                                    <a href="http://twitter.com/kopasoft" class="icon-twitter"></a>1234
                                                </span>
                                                Followers
                                            </li>
                                            <li class="format-standard">
                                                <span>
                                                    <a href="http://kopatheme.com/feed/" class="icon-rss-2"></a>1234
                                                </span>
                                                Subcribers
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-social -->
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
												    			<td><img style="width:80px; height:80px" src="images/team/${match.homeImg}_128.png"></img></td>
												    			<td class="result-team">${match.homeTeam}</td>
												    			<td class="result-score">${match.homeScore}</td>
												    			<td class="result-score">${match.awayScore}</td>
												    			<td class="result-team">${match.awayTeam}</td>
												    			<td><img style="width:80px; height:80px" src="images/team/${match.awayImg}_128.png"></img></td>
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
                                    	<ul class="list-unstyled" style="height:600px; overflow:auto;">
                                    		<c:forEach items="${comments}" var="comment">
                                            	<li class="format-standard">
                                            	<table style="margin-left: auto; margin-right: auto; width: 100%">
												   <tr style="width: 100%">
										    			<td style="width: 20%;">
										    				<p><strong>${comment.user}</strong></p>
										    				<p style="color: green"><strong>(1 - 0)</strong></p>
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
										  <input type="submit" value="Envoyer" />
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
            	<div id="sidebar" class="pull-right">
                    <ul class="clearfix list-unstyled">
                        <li>
                            <div class="widget kp-last-news">
                                <h2 class="widget-title"><span>Informations</span></h2>
                                <div class="widget-content">
                                    <ul class="list-unstyled">
                                    	<li class="format-standard">
		                                    <div>
		                                    	
		                                    	<table style="margin-left: auto; margin-right: auto; width: 100%;">
		                                    		<caption style="margin-top: 5px;">
	                                    			</caption>
	                                    			<tbody>
			                                    		<tr>
			                                    			<td style="float: right; width: 90%; font-size: 20px; margin-top:5px">
			                                    				<strong>User 1</strong>
															</td>
			                                    			<td style="width: 30%">
		                                    					<input class="button-prono" type="button" onclick="openProno()" />
			                                    			</td>
			                                    		</tr>
			                                    		<tr>
			                                    			<td style="float: right; width: 90%">Pronostique:</td>
			                                    			<td style="width: 30%">1 - 1</td>
			                                    		</tr>
			                                    		<tr>
			                                    			<td style="float: right; width: 90%">Mise:</td>
			                                    			<td style="width: 30%">500 crédits</td>
			                                    		</tr>
	                                    				<tr>
			                                    			<td style="float: right; width: 90%">Gain:</td>
			                                    			<td style="width: 30%">1650 crédits</td>
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
    	<div id="test" class="bottom-sidebar">
    	</div>
    	<div class="page-footer">
    	</div>
    	
    	<div id="prono-form" title="Pronostic" style="display:none">
		  <form>
		    <table style="margin-left: auto; margin-right: auto;">
			   <tr>
	    			<td><img style="width:80px; height:80px" src="images/team/${match.homeImg}_128.png"></img></td>
	    			<td style="border-bottom: solid 1px" class="result-team">${match.homeTeam}</td>
	    			<td style="border-bottom: solid 1px">
	    			<select style="width:40px; height:40px; font-size:20px;">
					  <option value="0">0</option>
					  <option value="1">1</option>
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					  <option value="6">6</option>
					  <option value="7">7</option>
					  <option value="8">8</option>
					  <option value="9">9</option>
					</select>
					</td>
	    			<td style="border-bottom: solid 1px">
	    			<select style="width:40px; height:40px; font-size:20px">
					  <option value="0">0</option>
					  <option value="1">1</option>
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					  <option value="6">6</option>
					  <option value="7">7</option>
					  <option value="8">8</option>
					  <option value="9">9</option>
					</select>
	    			</td>
	    			<td style="border-bottom: solid 1px" class="result-team">${match.awayTeam}</td>
	    			<td><img style="width:80px; height:80px" src="images/team/${match.awayImg}_128.png"></img></td>
	    		</tr>
	    	</table>
	    	<div style="margin-top: 20px">
	    	<center style="text-align: center; font-size:14px; color: orange; font-weight: bold;">
<!-- 	    		<p >Crédits :</p> -->
    			<input style="width: 100px" id="credits" type="text" /> / 10000
	    	</center>
	    	</div>
		  </form>
		</div>
		
    	<script type="text/javascript">
		  window.onload = function () {
			var homeTeam = "${match.homeTeam}";
			var awayTeam = "${match.awayTeam}";
		    var chart = new CanvasJS.Chart("info-match", {
		      
		    	axisX: {
		            title: "v:1.95 -- n:3.30 -- d:3.90"
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
		  
		  openProno = function()
		  {
			  dialog = $( "#prono-form" ).dialog({
			      autoOpen: false,
			      height: 250,
			      width: 600,
			      modal: true,
			      buttons: {
			        "Valider": function() {
		        	  if ($( "#credits" ).val() <= 10000/2)
					  {
			        	  dialog.dialog( "close" );
			        	  pronostic();
					  }
		        	  else
	        		  {
		        	  	  alert('Vous ne pouvez pas miser autant de crédits.');	  
	        		  }
			        },
			        "Annuler" : function() {
			          dialog.dialog( "close" );
			        }
			      },
			      close: function() {
			       // form[ 0 ].reset();
			       // allFields.removeClass( "ui-state-error" );
			      }
			    });
			  
			  dialog.dialog( "open" );
		  }
		  
		  pronostic = function()
		  {
			  
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