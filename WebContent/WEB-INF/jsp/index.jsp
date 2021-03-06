<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Barbylone - Pronostiquez gratuitement les matchs de l'Euro 2016 et gagnez des lots</title>
		<%@include file="import.jsp" %>
    </head>
    <body onLoad="goforit()">
    	<div class="wraper">
    		<%@include file="header.jsp" %>
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
                                    <h2 class="widget-title"><span>top news</span></h2>
                                    <div class="widget-content">
                                        <div class="list_carousel responsive">
                                            <ul id="ca-main-news" class="clearfix">
                                                <li >
                                                    <div class="item clearfix">
                                                    	<% response.setIntHeader("Refresh", 30); %>
  	
												    	<div style="margin-left: auto; margin-right: auto; width: 1000px;">
												    		<h1 style="text-align: center">Match du jour</h1>
													    	<p style="text-align: center">${statut}</p>
													    	<table style="margin-left: auto; margin-right: auto;">
													    		<tr>
													    			<td><img src="images/team/${homeImg}_128.png"></img></td>
													    			<td style="width:200px; text-align: center; font-size:30px">${homeTeam}</td>
													    			<td style="width:100px; text-align: center; font-size:50px">${homeScore}</td>
													    			<td style="width:100px; text-align: center; font-size:50px">${awayScore}</td>
													    			<td style="width:200px; text-align: center; font-size:30px">${awayTeam}</td>
													    			<td><img src="images/team/${awayImg}_128.png"></img></td>
													    		</tr>
													    	</table>
												    	</div>
	                                                    </div>
                                                </li>
                                                <li >
                                                    <div class="item clearfix">
                                                        <figure>
                                                            <img src="placeholders/posts/img-2.jpg" class="img-responsive" alt="">
                                                        </figure>
                                                        <ul class="kp-metadata clearfix">
                                                            <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                            <li>3 comment</li>
                                                            <li class="kp-time">05 March, 2013</li>
                                                        </ul>
                                                        <!-- kp-metadata -->
                                                        <footer>
                                                            <h3><a href="#">Lorem ipsum Pariatur aliquip.</a></h3>
                                                            <p>Lorem ipsum Pariatur aliquip ut non pariatur pariatur deserunt dolore tempor laborum do sed enim velit aliquip ea.</p>

                                                            <a class="read-more" href="#"><span>Read more</span><i>+</i></a>
                                                        </footer>
                                                    </div>
                                                    <!-- thumbnail -->
                                                </li>
                                            </ul>


                                            <div class="clearfix"></div>
                                            <a id="prev3" class="prev icon-chevron-left" href="#"></a>
                                            <a id="next3" class="next icon-chevron-right" href="#"></a>
                                        </div>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-news -->
                            </li>
                            <li class="clearfix">
                                <div class="widget kp-review">
                                    <h2 class="widget-title"><span>Latets Review</span></h2>
                                    <div class="widget-content">
                                        <ul class="list-unstyled">
                                            <li class="format-standard">
                                                <div class="top-kp-review clearfix">
                                                    <figure class="pull-left zoom-image">
                                                        <a href="#"><img src="placeholders/posts/img-13.jpg" class="img-responsive" alt="">
                                                            <span class="icon-image post-format"></span>
                                                        </a>
                                                    </figure>
                                                    <div class="item-right">
                                                        <ul>
                                                            <li><span>Special Effects</span><strong>4.3</strong></li>
                                                            <li><span>Story line</span><strong>4.3</strong></li>
                                                            <li><span>Casting</span> <strong>4.3</strong></li>
                                                            <li class="rating-hidden" style="display:none;"><span>Lorem ipsum</span> <strong>4.3</strong></li>
                                                            <li class="rating-hidden" style="display:none;"><span>Lorem ipsum</span> <strong>4.3</strong></li><li class="rating-hidden" style="display:none;"><span>Lorem ipsum</span> <strong>4.3</strong></li>

                                                            <li class="kp-show"><i>...</i></li>
                                                            <li class="last-list-item"><span>Overall Score</span> <strong>4.3</strong></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <!-- top-kp-review -->
                                                <div class="bottom-kp-review clearfix">
                                                    <h3><a href="#">Brand New Cadillac CTS revealed for New York auto show </a></h3>
                                                    <ul class="kp-metadata clearfix"> 
                                                        <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                        <li>3 comment</li>
                                                    </ul>
                                                    <!-- kp-metadata -->

                                                    <p>Fix now decided to make a bold move; he resolved to tell Passepartout all. It seemed to be the only possible means of keeping Phileas Fogg several days </p>
                                                </div>
                                                <!-- bottom-kp-review -->
                                            </li>
                                            <li class="format-video">
                                                <div class="top-kp-review clearfix">
                                                    <figure class="pull-left zoom-image">
                                                        <a href="#"><img src="placeholders/posts/img-5.jpg" class="img-responsive" alt=""><span class="icon-videocamera post-format"></span></a>
                                                    </figure>
                                                    <div class="item-right">
                                                        <ul>
                                                            <li><span>Special Effects</span><strong>4.3</strong></li>
                                                            <li><span>Story line</span><strong>4.3</strong></li>
                                                            <li><span>Casting</span> <strong>4.3</strong></li>
                                                            <li class="rating-hidden" style="display:none;"><span>Lorem ipsum</span> <strong>4.3</strong></li>
                                                            <li class="rating-hidden" style="display:none;"><span>Lorem ipsum</span> <strong>4.3</strong></li><li class="rating-hidden" style="display:none;"><span>Lorem ipsum</span> <strong>4.3</strong></li>

                                                            <li class="kp-show"><i>...</i></li>
                                                            <li class="last-list-item"><span>Overall Score</span> <strong>4.3</strong></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <!-- top-kp-review -->
                                                <div class="bottom-kp-review clearfix">
                                                    <h3><a href="#">Lorem ipsum Eiusmod ea minim cupidatat dolore </a></h3>
                                                    <ul class="kp-metadata clearfix">
                                                        <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                        <li>3 comment</li>
                                                    </ul>
                                                    <!-- kp-metadata -->

                                                    <p>Fix now decided to make a bold move; he resolved to tell Passepartout all. It seemed to be the only possible means of keeping Phileas Fogg several days </p>
                                                </div>
                                                <!-- bottom-kp-review -->
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
    	<div class="bottom-sidebar">
    		bottom sidebar
    	</div>
    	<div class="page-footer">
    		page footer
    	</div>
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
        <script type="text/javascript" src="js/custom.js"></script>
	</body>
</html>    