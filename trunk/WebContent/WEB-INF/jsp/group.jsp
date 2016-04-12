<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import.jsp" %>
		<link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
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
                            <div class="widget kp-last-news">
                                <h2 class="widget-title"><span>${group.name}</span></h2>
                                <div class="widget-content">
                                    <ul class="list-unstyled">
                                    	<li class="format-standard">
	                                    	<div class="item">
		                                        <figure class="zoom-image">
		                                        	<c:choose>
													    <c:when test="${empty groupImagePath}">
													    	<!--  <img src="images/default-group-pic.jpg" class="img-responsive" alt=""> <span class="icon-image post-format"></span> -->
													    	<img src="placeholders/posts/img-6.jpg" class="img-responsive" alt=""> <span class="icon-image post-format"></span>
													    	
													    </c:when>
													    <c:otherwise>
													    													    	<img src="placeholders/posts/img-6.jpg" class="img-responsive" alt=""> <span class="icon-image post-format"></span>
													    
		                                            	<!-- 	<img src="${groupImagePath}" class="img-responsive" alt=""> <span class="icon-image post-format"></span> -->
													    </c:otherwise>
													</c:choose>
		                                        </figure>
		                                        <ul class="kp-metadata clearfix">
		                                            <li>${group.numberOfMembers}&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
		                                            <li>3 comment</li>
		                                            <li class="kp-time">Groupe ${group.statusStr}</li>
		                                        </ul>
		                                        <!-- kp-metadata -->
		                                        <br />
		                                        <p style="font-weight:bold">${group.description}</p>
		                                        <br />
		                                        <footer class="clearfix">
		                                            <ul class="meta-post pull-left">
		                                                <li><span>Groupe crée par : </span><a href="#">${group.groupCreator}</a></li>
		                                                <li><span>Groupe crée le  : </span>${group.creationDate}</li>
		                                            </ul>
		                                        </footer>
		                                    </div> 
                                  		</li>
                                  	</ul>
                                  			
                                </div>
                                <!-- widget-content -->
                            </div>
                    </div>
                    <!-- main-col -->
                    <div class="clearfix"></div>
                </div>
                <!-- main-content -->
            	<div id="sidebar" class="pull-right">
            		<ul class="clearfix list-unstyled">
                    	<c:if test="${cookie.currentUserName.value == group.groupCreator}">
	                    	<li>
	                        	<div class="widget widget-new-letter">
		                            <h2 class="widget-title"><span>Ajouter un utilisateur</span></h2>
		                            <p>Entrez ici le nom d'utilisateur de la personne que vous souhaitez ajouter à ce groupe</p>
		
		                            <form action="#" method="post" class="clearfix" style="text-align:center">
		                                <div class="form-group">
		                                    <input type=text placeholder="Nom d'utilisateur" class="form-control" id="user-search" name="user-search">
		                                </div>
		                                <input type="submit" value="Valider" id="s">
		                            </form>
	                        	</div>
	                        </li>
                        </c:if>
                        <li>
                            <div class="widget widget-kp-tab">
                                <h2 class="widget-title"><span>Les 5 derniers membres</span></h2>
                                    <div class="widget-content">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tab_popular">
                                                <ul class="list-unstyled">
                                                    <li>
                                                    	<c:set var="membersCount" value="0" scope="page" />
                                                    	
                                                    	<c:forEach items="${members}" var="member">
                                                    		<c:set var="membersCount" value="${membersCount + 1}" scope="page"/>
                                                    	
	                                                        <div class="kp-group clearfix">
	                                                            <span>${membersCount}</span>
	                                                            <a href="#">${member.username}</a>
	                                                            <ul class="kp-metadata clearfix">
	                                                                <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
	                                                                <li>3 comment</li>
	                                                            </ul>
	                                                        </div>
                                                        </c:forEach>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- widget-content -->
                                </div>
                        </li>
                    </ul>
                </div>
                <!-- sidebar -->
                <div class="clearfix"></div>
            </div>
    	</div>
    	<div class="page-footer">
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
        <script src="js/chart/jquery.canvasjs.js"></script>
        <script src="js/chart/canvasjs.js"></script>
        <script src="js/chart/excanvas.js"></script>
        <script type="text/javascript" src="js/custom.js"></script>
        <script src="js/autocomplete.js"></script>
	</body>
</html>    