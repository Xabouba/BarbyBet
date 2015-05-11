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
    <div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v2.3&appId=632099886859535";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
    	<% response.setIntHeader("Refresh", 120); %>
    	
    	<div class="wraper">
    		<header id="page-header">
                <div class="top-header clearfix">
                    <div class="title_BarbyBet" id="logo"><a href="index.html">Barby Bet</a></div>
                    <nav>
                        <ul class="sf-menu" id="main-menu">
                            <li class="current-menu-item">
                                <a href="index.html">home</a>
                            </li>
                            <%-- Vérification de l'absence du nom d'utilisateur en cookie --%>
                            <c:choose>
				                <c:when test="${empty cookie.cookieUsername}">
				                	<li>
	                                	<a href="register">Inscription</a>
	                            	</li>
				                </c:when>
				                <c:otherwise>
				                	<li>
	                                	<a href="register">Deconnexion</a>
	                            	</li>
				                </c:otherwise>
                            </c:choose>
                            <li>
                                <a href="contact.html">contact</a>
                            </li>
                            <li>
                                <a href="#">entertaiment</a>
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
                </div>
                <!-- bottom-header -->
            </header>