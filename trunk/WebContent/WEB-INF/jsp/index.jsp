<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/icomoon.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/superfish.css">
        <link rel="stylesheet" type="text/css" href="css/result.css">
        <link rel="stylesheet" href="css/prettyPhoto.css">
        <link rel="stylesheet" type="text/css" href="css/default.css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <!--[if !IE]><!-->
        <script src="js/ie10.js"></script>
        <!--<![endif]-->

        <!--[if IE 9]>
                <link rel="stylesheet" type="text/css" href="css/ie9.css">
        <![endif]-->


        <!--[if lt IE 9]>
        <link rel="stylesheet" type="text/css" href="css/ie.css">
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
        <![endif]-->
    </head>
    <body onLoad="goforit()">
    	<% response.setIntHeader("Refresh", 120); %>
    	
    	<div class="wraper">
    		<header id="page-header">
                <div class="top-header clearfix">
                    <div id="logo"><figure><a href="index.html"><img src="images/logo.png" class="img-responsive" alt=""></a> </figure></div>
                    <nav>
                        <ul class="sf-menu" id="main-menu">
                            <li class="current-menu-item">
                                <a href="index.html">home</a>
                            </li>
                            <li>
                                <a href="single.html">page</a>
                                <ul>
                                    <li><a href="elements.html">Element</a></li>
                                    <li><a href="single.html">Single</a>
                                        <ul>
                                            <li><a href="single.html">Single</a></li>
                                            <li><a href="single-video.html">Single video</a></li>
                                            <li><a href="single-audio.html">Single audio</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="categories.html">Category</a>
                                        <ul>
                                            <li><a href="categories.html">Category 1</a></li>
                                            <li><a href="categories-2.html">Category 2</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="video.html">Video</a></li>
                                    <li><a href="gallery6.html">Gallery</a>
                                        <ul>
                                            <li><a href="gallery6.html">Gallery 6 col</a></li>
                                            <li><a href="gallery7.html">Gallery 7 col</a></li>
                                            <li><a href="gallery8.html">Gallery 8 col</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="contact.html">contact</a>
                            </li>
                            <li>
                                <a href="#">entertaiment</a>
                                <ul>
                                    <li><a href="#">Menu item</a></li>
                                    <li><a href="#">Menu item</a></li>
                                    <li><a href="#">Menu item</a></li>
                                    <li><a href="#">Menu item</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">world</a>
                            </li>
                            <li>
                                <a href="#">photography</a>
                            </li>
                        </ul>
                        <div id="mobile-menu">
                            <span>Menu</span>
                            <ul id="toggle-view-menu">
                                <li class="clearfix">
                                    <h3><a href="#">Home</a></h3>
                                    <span>+</span>
                                    <div class="clearfix"></div>
                                    <div class="menu-panel clearfix">
                                        <ul>
                                            <li><a href="index.html">Index style 1</a></li>
                                            <li><a href="index-2.html">Index style 2</a></li>
                                            <li><a href="index-3.html">Index style 3</a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <h3><a href="#">Pages</a></h3>
                                    <span>+</span>
                                    <div class="clearfix"></div>                    
                                    <div class="menu-panel clearfix">
                                        <ul>
                                            <li><a href="about.html">About page</a></li>
                                            <li><a href="elements.html">Elements page</a></li>
                                            <li><a href="404.html">404 page</a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <h3><a href="#">Portfolio</a></h3>
                                    <span>+</span>
                                    <div class="clearfix"></div>                    
                                    <div class="menu-panel clearfix">
                                        <ul>
                                            <li><a href="portfolio-3col.html">Portfolio 3 column</a></li>
                                            <li><a href="portfolio-2col.html">Portfolio 2 column</a></li>
                                            <li><a href="portfolio-1col.html">Portfolio 1 column</a></li>
                                            <li>
                                                <a href="#">Portfolio detail</a>
                                                <ul>
                                                    <li><a href="portfolio-detail.html">Portfolio single</a></li>
                                                    <li><a href="portfolio-audio.html">Portfolio audio</a></li>
                                                    <li><a href="portfolio-gallery.html">Portfolio gallery</a></li>
                                                    <li><a href="portfolio-video.html">Portfolio video</a></li>
                                                    <li><a href="portfolio-soundcloud.html">Portfolio soundcloud</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <h3><a href="#">Blog</a></h3>
                                    <span>+</span>
                                    <div class="clearfix"></div>                    
                                    <div class="menu-panel clearfix">
                                        <ul>
                                            <li>
                                                <a href="#">Blog style 1</a>
                                                <ul>
                                                    <li><a href="blog-1-left-sidebar.html">Width left sidebar</a></li>
                                                    <li><a href="blog-1-right-sidebar.html">Width right sidebar</a></li>
                                                    <li><a href="blog-1-two-sidebar.html">Width two sidebar</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="blog-2.html">Blog style 2</a></li>
                                            <li>
                                                <a href="#">Blog style 3</a>
                                                <ul>
                                                    <li><a href="blog-3-one-sidebar.html">Width one sidebar</a></li>
                                                    <li><a href="blog-3-two-sidebar.html">Width two sidebar</a></li>
                                                </ul>
                                            </li>
                                            <li>
                                                <a href="#">Blog single</a>
                                                <ul>
                                                    <li><a href="single-1.html">Single style 1</a></li>
                                                    <li><a href="single-2.html">Single style 2</a></li>
                                                    <li><a href="single-3.html">Single style 3</a></li>
                                                    <li><a href="single-4.html">Single style 4</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="clearfix"><h3><a href="contact.html">Contact</a></h3></li>                   
                            </ul><!--toggle-view-menu-->
                        </div><!--mobile-menu-->
                    </nav>
                </div>
                <!-- top-header -->
                <div class="bottom-header clearfix">
                    <div class="kp-time">									
                        <span id="clock"></span>
                    </div>
                    <!-- kp-time -->
                    <div class="top-news">
                        <h2>Breaking news:</h2>
                        <div class="list_carousel responsive">
                            <ul id="ca-top-news" class="list-unstyled">
                                <li>
                                    <a href="#">Lorem ipsum Amet sed veniam occaecat laborum laboris Duis .</a>
                                </li>
                                <li>
                                    <a href="#">Lorem ipsum Amet sed veniam occaecat laborum laboris Duis mollit proident.</a>
                                </li>
                                <li>
                                    <a href="#">Lorem ipsum Amet sed veniam occaecat laborum laboris Duis mollit proident.</a>
                                </li>
                                <li>
                                    <a href="#">Lorem ipsum Amet sed veniam occaecat laborum laboris Duis mollit proident.</a>
                                </li>
                                <li>
                                    <a href="#">Lorem ipsum Amet sed veniam occaecat laborum laboris Duis mollit proident.</a>
                                </li>
                            </ul>						
                        </div>
                    </div>
                    <!-- top-news -->
                    <div class="top-search-form">					
                        <form action="#" class="search-form clearfix" method="get">
                               <input type="text" onBlur="if (this.value == '')
            this.value = this.defaultValue;" onFocus="if (this.value == this.defaultValue)
            this.value = '';" value="Search..." name="s" class="search-text">
                            <input type="submit" value="" name="submit" class="search-submit">
                        </form><!-- search-form -->
                    </div><!-- top-search-form -->
                </div>
                <!-- bottom-header -->
            </header>
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
                                <div class="widget kp-story">
                                    <h2 class="widget-title"><span>Top stories</span></h2>
                                    <div class="widget-content">
                                        <div class="list_carousel responsive">
                                            <ul id="ca-kp-story">
                                                <li>
                                                    <div class="thumbnail clearfix">
                                                        <h3><a href="#">Indian on TV's longest-running soap opera "Corrie"</a></h3>
                                                        <figure class="zoom-image">
                                                            <a href="#">
                                                                <img src="placeholders/posts/img.jpg" class="img-responsive" alt="...">
                                                            </a>
                                                        </figure>
                                                        <div class="caption">
                                                            <ul class="kp-metadata">
                                                                <li class="kp-time">05 March, 2013</li>
                                                            </ul>
                                                            <p>Lorem ipsum Irure aliquip esse elit adipisicing exercitation in.</p>
                                                        </div>
                                                        <!-- caption -->
                                                    </div>
                                                    <!-- thumbnail -->
                                                </li>
                                                <li>
                                                    <div class="thumbnail clearfix">
                                                        <h3><a href="#">Indian on TV's longest-running soap opera "Corrie"</a></h3>
                                                        <figure class="zoom-image">
                                                            <a href="#">
                                                                <img src="placeholders/posts/img.jpg" class="img-responsive" alt="...">
                                                            </a>
                                                        </figure>
                                                        <div class="caption">
                                                            <ul class="kp-metadata">
                                                                <li class="kp-time">05 March, 2013</li>
                                                            </ul>
                                                            <p>Lorem ipsum Irure aliquip esse elit adipisicing exercitation in.</p>
                                                        </div>
                                                        <!-- caption -->
                                                    </div>
                                                    <!-- thumbnail -->
                                                </li>
                                                <li>
                                                    <div class="thumbnail clearfix">
                                                        <h3><a href="#">Indian on TV's longest-running soap opera "Corrie"</a></h3>
                                                        <figure class="zoom-image">
                                                            <a href="#">
                                                                <img src="placeholders/posts/img.jpg" class="img-responsive" alt="...">
                                                            </a>
                                                        </figure>
                                                        <div class="caption">
                                                            <ul class="kp-metadata">
                                                                <li class="kp-time">05 March, 2013</li>
                                                            </ul>
                                                            <p>Lorem ipsum Irure aliquip esse elit adipisicing exercitation in.</p>
                                                        </div>
                                                        <!-- caption -->
                                                    </div>
                                                    <!-- thumbnail -->
                                                </li>
                                            </ul>
                                            <div class="clearfix"></div>
                                            <a id="prev1" class="prev icon-chevron-left" href="#"></a>
                                            <a id="next1" class="next icon-chevron-right" href="#"></a>
                                            <div id="pager1" class="pager"></div>
                                        </div>
                                        <ul class="list-news list-unstyled">
                                            <li>
                                                <a href="#">Indian on longest-running soap opera hits 50</a>
                                            </li>
                                            <li>
                                                <a href="#">Sneak Peek: Christmas Comes Early to The Office</a>
                                            </li>
                                            <li>
                                                <a href="#">Portable Cord Attaches to Furniture</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-story -->
                            </li>
                            <li class="clearfix">
                                <div class="widget kp-social">
                                    <h2 class="widget-title"><span>Net work</span></h2>
                                    <div class="widget-content">
                                        <ul class="list-unstyled">
                                            <li class="clearfix item-fb">
                                                <span>
                                                    <a href="http://facebook.com/kopatheme" class="icon-facebook-2"></a>1234
                                                </span>
                                                Likes
                                            </li>
                                            <li class="clearfix item-tw">
                                                <span>
                                                    <a href="http://twitter.com/kopasoft" class="icon-twitter"></a>1234
                                                </span>
                                                Followers
                                            </li>
                                            <li class="clearfix item-fe">
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
		                                    	<table style="margin-left: auto; margin-right: auto; width: 100%">
		                                    		<caption style="font-size: 20px; margin-top: 10px; margin-bottom: 10px;">
		                                    			<strong>User 1</strong>
	                                    			</caption>
	                                    			<tbody>
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
                        <li>
                            <div class="widget kp-ads">
                                <div class="widget-content">
                                    <figure>
                                        <a href="#"><img src="placeholders/posts/ads.jpg" class="img-responsive" alt="" /></a>
                                    </figure>
                                </div>
                                <!-- widget-content -->
                            </div>
                            <!-- kp-ads -->
                        </li>
                    </ul>
                </div>
                <!-- sidebar -->
                <div class="clearfix"></div>
            </div>
    	</div>
    	<div id="test" class="bottom-sidebar">
    		bottom sidebar
    	</div>
    	<div class="page-footer">
    		page footer
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
<!--         <script src="js/masonry.pkgd.min.js"></script> -->
        
<!-- 	    <script src="js/chart/fusioncharts.js" ></script> -->
<!-- 	    <script src="js/chart/fusioncharts-jquery-plugin.js" ></script> -->
        <script type="text/javascript" src="js/custom.js"></script>
	</body>
</html>    