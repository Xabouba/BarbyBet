<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Contact</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/icomoon.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/superfish.css">
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
                            <ul id="ca-top-news">
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
                    </div>
                    <!-- top-search-form -->
                </div>
                <!-- bottom-header -->
            </header>
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
                                        <h2 class="widget-title"><span>Inscription</span></h2>
                                        <form action="register" method="post" id="kp-form-contact">
                                            <div class="form-group">
                                                <label for="input-username" class="sr-only">username</label>
                                                <input type=text placeholder="Pseudo" class="form-control" id="input-name" name="username">
                                            </div>
                                            <!-- form group -->
                                            <div class="form-group">
                                                <label for="input-email" class="sr-only">email</label>
                                                <input type=text placeholder="Email" class="form-control" id="input-email" name="email">
                                            </div>
                                            <!-- form group -->
                                            <div class="form-group">
                                                <label for="input-password" class="sr-only">password</label>
                                                <input type=password placeholder="Mot de passe" class="form-control" id="input-website" name="password">
                                            </div>
                                            <!-- form group -->
                                            <div class="form-group">
                                                <label for="input-repeat-password" class="sr-only">repeatPassword</label>
                                                <input type=password placeholder="Répeter mot de passe" class="form-control" id="input-website" name="repeatPassword">
                                            </div>
                                            <!-- form group -->
                                            <input type="submit" name="submit" class="btn btn-primary" value="Valider" id="input-submit">
                                        </form>
                                        <div id="renponse"></div>

                                    </div>
                                    <!-- col -->
                                    <div class="info-company">
                                        <h2 class="widget-title"><span>Locate us</span></h2>
                                        <p>Nunc at pellentesque libero. Quisque quis elit eget enim facilisis gravida. Donec placerat dui nec orci tincidunt vitae tristique augue dictum. Duis nec leo vitae lorem rutrum tempor sit amet id quam sed los dui eleifend ipsum commodo luctus.</p>
                                        <h3>Where to find Us</h3>
                                        <ul>
                                            <li><span class="icon-map-marker "></span>5512 Lorem Ipsum Vestibulum 666/13</li>
                                            <li><span class="icon-iphone "></span>+1 800 789 50 12, +1 800 450 6935, +1 800 450 6940</li>
                                            <li><span class="icon-email "></span>mail@compname.com</li>
                                        </ul>
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