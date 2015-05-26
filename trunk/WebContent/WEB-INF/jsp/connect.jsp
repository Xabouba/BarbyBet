<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <div id="content">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <!-- top-effect -->
                    <div id="kp-breadcrumb" class="clearfix">
                        <p class="pull-left">You are here: </p>
                        <ol class="breadcrumb pull-left">
                            <li><a href="#">Home</a></li>
                            <li class="active">Contact</li>
                        </ol>
                    </div>
                    <!-- kp-breadcrumb -->
                    <div id="main-content" class="pull-left">
                        <div class="page-content">
                            <div class="bottom-contact">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6 col-xs-6">
                                        <h2 class="widget-title"><span>Connexion</span></h2>
                                        <form action="connect" method="post" id="kp-form-contact">
                                        	<label>
												<c:if test="${not empty errorsConnect['username']}">
												    <c:out value="${errorsConnect['username']}" />
												</c:if>
												<c:if test="${not empty errorsConnect['password']}">
												    <c:out value="${errorsConnect['password']}" />
												</c:if>
                                        	</label>
                                            <div class="form-group">
                                                <label for="input-username" class="sr-only">username</label>
                                                <input type=text placeholder="Pseudo" class="form-control" id="input-name" name="username" value="<c:out value="${usernameConnect}" />">
                                            </div>
                                            <!-- form group -->
                                            <div class="form-group">
                                                <label for="input-password" class="sr-only">password</label>
                                                <input type=password placeholder="Mot de passe" class="form-control" id="input-website" name="password">
                                            </div>
                                            <input type="checkbox" id="memoire" name="memoire" />
                                            <label for="memoire">Se souvenir de moi</label>
							                <br />
                                            <!-- form group -->
                                            <input type="submit" name="submit" class="btn btn-primary" value="Valider" id="input-submit">
                                        </form>
                                        <div id="renponse"></div>

                                    </div>
                                     <div class="col-md-6 col-sm-6 col-xs-6">
                                        <h2 class="widget-title"><span>Connexion reseaux sociaux</span></h2>
                                        	<br />
                                        	<div 
                                        		class="fb-login-button" 
                                        		data-max-rows="1" 
                                        		data-size="xlarge" 
                                        		data-show-faces="false" 
                                        		data-auto-logout-link="false"
                                        		style="margin-left:100px">
                                        	</div>
                                        <div id="renponse"></div>

                                    </div>
                                    <!-- info company -->
                                </div>
                                <!-- row -->
                            </div>
                            <!-- bottom contact -->
                        </div>
                        <!-- page-contact -->
                    </div>
                    <!-- main-content -->
                    <div id="sidebar" class="pull-right">
                        <ul class="clearfix list-unstyled">
                            <li>
                                <div class="widget kp-trend">
                                    <h2 class="widget-title"><span>Trending Post</span></h2>
                                    <div class="widget-content">
                                        <ul class="toggle-view toggle-view-1">
                                            <li class="clearfix active">
                                                <h3>Powered By KOPASOFT</h3>
                                                <span>+</span>
                                                <div class="clearfix"></div>
                                                <div class="panel clearfix">
                                                    <figure class="pull-left">
                                                        <a href="#">
                                                            <img src="placeholders/posts/img-4.jpg" class="img-responsive" alt="">
                                                        </a>
                                                    </figure>
                                                    <div class="item-right">
                                                        <ul class="kp-metadata clearfix">
                                                            <li>05 March 2013</li>
                                                        </ul>
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                                        <a href="#" class="read-more">Read more ...</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="clearfix">
                                                <h3>Powered By KOPASOFT</h3>
                                                <span>+</span>
                                                <div class="clearfix"></div>
                                                <div class="panel clearfix">
                                                    <figure class="pull-left">
                                                        <a href="#">
                                                            <img src="placeholders/posts/img-4.jpg" class="img-responsive" alt="">
                                                        </a>
                                                    </figure>
                                                    <div class="item-right">
                                                        <ul>
                                                            <li class="kp-time">05 March 2013</li>
                                                        </ul>
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pretium dignissim metus at tempus.</p>
                                                        <a href="#" class="read-more">Read more ...</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="clearfix">
                                                <h3>Powered By KOPASOFT</h3>
                                                <span>+</span>
                                                <div class="clearfix"></div>
                                                <div class="panel clearfix">
                                                    <figure class="pull-left">
                                                        <a href="#">
                                                            <img src="placeholders/posts/img-4.jpg" class="img-responsive" alt="">
                                                        </a>
                                                    </figure>
                                                    <div class="item-right">
                                                        <ul>
                                                            <li class="kp-time">05 March 2013</li>
                                                        </ul>
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pretium dignissim metus at tempus.</p>
                                                        <a href="#" class="read-more">Read more ...</a>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul><!--end:toggle-view-->
                                    </div>
                                    <!-- widget-trend -->
                                </div>
                                <!-- kp-trend -->
                            </li>
                            <li>
                                <div class="widget widget_categories">
                                    <h2 class="widget-title"><span>Categories</span></h2>
                                    <ul class="list-unstyled">
                                        <li class="icon-double-angle-right" ><a href="#">Business (13)</a></li>
                                        <li ><a href="#">Education (13)</a></li>
                                        <li ><a href="#">Fashion (13)</a></li>
                                        <li ><a href="#">Technology (13)</a></li>
                                    </ul>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-story -->
                            </li>

                            <li>
                                <div class="widget kp-flickr">
                                    <h2 class="widget-title"><span>photo on flickr</span></h2>
                                    <div class="widget-content">
                                        <div class="flickr-wrap clearfix" id="flickr-feed-1">
                                            <ul class="clearfix list-unstyled"></ul>
                                        </div><!--flickr-wrap-->
                                    </div>
                                    <!-- widget-content -->
                                </div>
                                <!-- kp-flickr -->
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
        <!-- wraper -->
        <div id="bottom-sidebar">
            <div class="wraper clearfix">
                <div class="kp-social clearfix">
                    <ul class="clearfix">
                        <li><a href="http://facebook.com/kopatheme" class="facebook-2"><span class="icon-facebook-2 "></span></a><span><a href="http://facebook.com/kopatheme">Facebook</a></span> </li>
                        <li><a href="http://twitter.com/kopasoft" class="twitter"><span class="icon-twitter"></span></a><span><a href="http://twitter.com/kopasoft">Twitter</a> </span> </li>

                        <li><a href="https://plus.google.com/u/0/107446115860737829611/posts" class="google-plus"><span class="icon-google-plus "></span></a><span><a href="https://plus.google.com/u/0/107446115860737829611/posts">Google +</a> </span> </li>

                        <li><a href="#" class="linkedin-2"><span class="icon-linkedin-2 "></span></a><span><a href="#">Linkedin</a> </span> </li>
                        <li><a href="#" class="dribbble-2"><span class="icon-dribbble-2"></span></a><span><a href="#">Dribbble</a> </span> </li>
                        <li><a href="#" class="tumblr-2"><span class="icon-rss"></span></a><span><a href="#">Rss</a> </span> </li>
                        <li><a href="#" class="pinterest"><span class="icon-pinterest"></span></a><span><a href="#">Pinterest</a> </span></li>
                    </ul>
                </div>
                <!-- kp-social -->
                <div class="row">
                    <div class="col-md-4 col-sm-4 col-xs-4  ">
                        <div class="widget widget-text">
                            <h2 class="widget-title"><span>Get it touth</span></h2>                           
                            <p>Lorem ipsum Amet sit occaecat elit ex incididunt sunt ullamco mollit dolore voluptate aute officia.</p>
                            <ul class="list-unstyled">
                                <li><i class="icon-map-marker"></i><span>Jl.</span> Aspal Hitam 13a, Banana City, Jebe Temor </li>
                                <li><i class="icon-iphone"></i><span>T</span> (212) 555 55 00 </li>
                                <li><i class="icon-email"></i><span>Email:</span><a href="mailto:kopasof@gmail.com">kopatheme@gmail.com</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- widget-text -->
                    <div class=" col-md-4 col-sm-4 col-xs-4  ">

                        <div class="widget widget-last-post">
                            <h2 class="widget-title"><span>Last post</span></h2>
                            <ul class="list-unstyled">
                                <li>
                                    <div class="item clearfix">
                                        <figure class="pull-left">
                                            <a href="#">
                                                <img src="placeholders/posts/img-11.jpg" class="img-responsive" alt="">
                                            </a>
                                        </figure>
                                        <div class="item-right">

                                            <p><a href="#">Asia The Underground City Centres of Japan</a></p>
                                            <ul class=" kp-metadata clearfix">
                                                <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                <li>3 comment</li>
                                            </ul>
                                            <!-- kp-metadata -->
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="item clearfix">
                                        <figure class="pull-left">
                                            <a href="#">
                                                <img src="placeholders/posts/img-11.jpg" class="img-responsive" alt="">
                                            </a>
                                        </figure>
                                        <div class="item-right">

                                            <p><a href="#">The Browser Wars, when will it ever stop?</a></p>
                                            <ul class=" kp-metadata clearfix">
                                                <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                                                <li>3 comment</li>
                                            </ul>
                                            <!-- kp-metadata -->
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- widget-last-post -->
                    <div class="col-md-4 col-sm-4 col-xs-4  ">

                        <div class="widget widget-new-letter">
                            <h2 class="widget-title"><span>Newletters signup</span></h2>
                            <p>Lorem ipsum Amet sit occaecat elit ex incididunt sunt ullamco mollit dolore voluptate aute officia incididunt amet commodo in elit nisi.</p>

                            <form action="http://kopatheme.com/demo/style-magazine/html/processNewsletterForm.php" method="post" class="clearfix">
                                <div class="form-group">
                                    <label for="a" class="sr-only">name</label>
                                    <input type=text placeholder="Your.address@email.com" class="form-control" id="a" name="name">
                                </div>
                                <input type="submit" value="Join Us" id="s">
                            </form>

                            <span>* It really works! Mailchimp intergration.</span>
                            <small>We never spam!</small>
                        </div>
                    </div>
                    <!-- widget-new-letter -->
                </div>
                <!-- row -->
            </div>
            <!-- wraper -->
        </div>
        <!-- bottom-sidebar -->
        <footer id="page-footer">
            <div class="wraper clearfix">
                <p class="pull-left">Copyrights. &:copy 2013 by KOPASOFT</p>
                <ul id="bottom-menu" class="pull-right clearfix">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Faq</a></li>
                    <li><a href="#">Term of use</a></li>
                    <li><a href="#">Contact us</a></li>
                </ul>
            </div>
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
    </body>
</html>