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
            <!-- page-header -->
            <div id="content">
                <div class="wraper clearfix general-rank">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                    	<%@include file='rank-part.jsp'%>
                    </div>
                    <!-- main-content -->

                    <div id="sidebar" class="pull-right">
                        <ul class="clearfix list-unstyled">
                            <li class="clearfix">
                                <div class="widget kp-review">
                                    <h2 class="widget-title"><span>Informations</span></h2>
	                                <div class="widget-content groups">
	                                    <ul class="list-unstyled">
	                                    	<li class="format-standard">
			                                    <div id="match">
			                                    	<select onchange="changeGroup(this.value)" style="width: 100%; margin-bottom: 2px;">
													  <option value="all">General</option>
													  <option value="1">Groupe 1</option>
													  <option value="2">Groupe 2</option>
													  <option value="3">Groupe 3</option>
													  <option value="4">Groupe 4</option>
													</select>
			                                    </div>
	                                    	</li>
	                                        <li class="format-standard">
	                                        	<span class="group-name">Groupe A</span>
	                                        	<table>
	                                        		<tr>
	                                        			<td class="information">
	                                        				<span class="attribute-name">Rang :</span><span class="attribute-value">1</span>
	                                        				<br/>
	                                        				<span class="attribute-name">Points :</span><span class="attribute-value">5</span>
	                                        				<br/>
	                                        				<span class="attribute-name">Membres :</span><span class="attribute-value">${totalUser}</span>
	                                        			</td>
	                                        			<td class="stats"><canvas class="chart-account" id="chart-area" width="120" height="120"/></td>
	                                        		</tr>
	                                       		</table>
	                                    	</li>
	                                   	</ul>
	                                </div>
                                </div>
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

			changeGroup = function(value)
			{
				$("#main-content").load("rank", {page: 1, group: value}).fadeIn("slow");
			}
        
			var pieData = [
				{
					value: 67,
					color:"#F7464A",
					highlight: "#FF5A5E",
					label: "Réussi"
				},
				{
					value: 33,
					color: "#46BFBD",
					highlight: "#5AD3D1",
					label: "Raté"
				}
			];

			window.onload = function(){
				var ctx = document.getElementById("chart-area").getContext("2d");
				window.myPie = new Chart(ctx).Pie(pieData);
			};

		</script>
    </body>
</html>    