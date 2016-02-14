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
                        <div class="page-single account">
	                        <ul class="list-unstyled">
	                            <li class="clearfix">
	                                <div class="widget kp-review">
	                                    <h2 class="widget-title"><span>Mon Compte</span></h2>
	                                    <div class="widget-content">
	                                    	<ul class="list-unstyled">
                                            	<li class="format-standard">
                                            		<div>
														<div class="image-account">
															<img src="images/profil.jpg" />
														</div>
														<div class="info-account">
															<h1>Bugy</h1>
															<span>
																Points : 10500
																</br>
																Points gagnés : 15106
															</span>
														</div>
														<canvas class="chart-account" id="chart-area" width="160" height="160"/>                                         		
                                            		</div>
                                            	</li>
                                            </ul>	
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