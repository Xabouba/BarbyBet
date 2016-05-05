<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
            <div id="content" class="information">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                    	<div id="sidebar-main-content" class="pull-left">
                    		<%@include file='minimized-rank.jsp'%>
                    	</div>
                        <div class="page-single pull-left" >
                     			<h2 class="widget-title"><span>Classement</span></h2>
                                   <div class="menu-match">
                                   	<ul class="nav nav-tabs kp-tabs">
										<c:forEach items="${groupNames}" var="group" varStatus="i">
											<c:choose>
												<c:when test="${group == selectedGroup}">
	                    			            	<li id="nav-tab-${i.index}" class="active">
									                	<a onclick="selectGroup('${group}')" href="">Groupe ${group}</a>
									            	</li>
												</c:when>
												<c:otherwise>
													<li id="nav-tab-${i.index}">
									                	<a onclick="selectGroup('${group}')" href="">Groupe ${group}</a>
									            	</li>
												</c:otherwise>
											</c:choose>
						                </c:forEach>
						            </ul>
                                   </div>
					                <ul class="list-unstyled" id="group-information">
				                    	<%@include file='information-part.jsp'%>
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

		  selectGroup = function(group)
		  {
			  $("#group-information").load("information", {group: group}).fadeIn("slow");
		  }
				  
		</script>
		</script>
    </body>
</html>    