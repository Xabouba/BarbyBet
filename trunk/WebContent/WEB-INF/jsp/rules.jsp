<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Conditions Générales d'Utilisation</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/icomoon.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/superfish.css">
		<link rel="stylesheet" type="text/css" href="css/result.css">
		<link rel="stylesheet" type="text/css" href="css/match.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
		<link rel="stylesheet" href="css/prettyPhoto.css">
		<link rel="stylesheet" type="text/css" href="css/default.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="stylesheet" type="text/css" href="css/responsive.css">
		<link rel="stylesheet" type="text/css" href="css/direct.css">
		<link rel="stylesheet" type="text/css" href="css/information.css">
		<link rel="stylesheet" type="text/css" href="css/createGroup.css">
		<link rel="stylesheet" type="text/css" href="css/group.css">
		<link rel="stylesheet" type="text/css" href="css/rank.css">
		<link rel="stylesheet" type="text/css" href="css/generalRank.css">
		<link rel="stylesheet" type="text/css" href="css/rules.css">
    </head>
    <body onLoad="goforit()">
		<div class="wraper">
			<header id="page-header">
				<div class="top-header clearfix">
				    <div class="title_BarbyBet" id="logo"><a href="index">Barbylone</a></div>
				    <nav>
				        <ul class="sf-menu" id="main-menu">
				            <li class="current-menu-item">
				                <a href="index">Accueil</a>
				            </li>
				            <%-- VÃ©rification de l'absence du nom d'utilisateur en cookie --%>
			                <c:choose>
								<c:when test="${empty cookie.currentUserId}">
							    	<li>
				                    	<a href="register">Inscription</a>
				                    </li>
							    </c:when>
								<c:otherwise>
									<li>
					                    <a href="match">Pronostiquer</a>
					                </li>
					                <li>
						            	<a href="resultats">Résultats</a>
						            </li>
					                <li>
					                    <a href="#">Groupes</a>
					                    <ul>
					                        <li><a href="createGroup">Créer Groupe</a></li>
					                        <li><a href="group">Mes Groupes</a></li>
					                    </ul>
					                </li>
					                <li>
					                    <a href="rank">Classements</a>
					                </li>
					                <li>
					                    <a href="rules">Règles du jeu</a>
					                </li>
							        <li>
				                        <a href="logout">Déconnexion</a>
				                    </li>
							    </c:otherwise>
			                </c:choose>
				            
			                
			            </ul>
			            <div id="mobile-menu">
			                <span>Menu</span>
			                <ul id="toggle-view-menu">
			                	<li class="clearfix">
			                    	<%-- VÃ©rification de l'absence du nom d'utilisateur en cookie --%>
					                <c:choose>
										<c:when test="${empty cookie.currentUserId}">
											<li>
									    		<h3><a href="register">Inscription</a></h3>
									    	</li>
									    </c:when>
										<c:otherwise>
											<li class="clearfix">
						                        <h3><a href="index">Accueil</a></h3>
						                    </li>
						                    <li class="clearfix">
						                        <h3><a href="match">Pronostiquer</a></h3>
						                    </li>
						                    <li class="clearfix">
						                        <h3><a href="resultats">Résultats</a></h3>
						                    </li>
						                    <li class="clearfix">
						                        <h3><a href="createGroup">Créer Groupe</a></h3>
						                    </li>
						                    <li class="clearfix">
						                        <h3><a href="group">Mes Groupes</a></h3>
						                    </li>
						                    <li class="clearfix">
						                    	<h3><a href="rank">Classements</a></h3>
						                    </li>
						                    <li class="clearfix">
						                    	<h3><a href="rules">Règles du jeu</a></h3>
						                    </li>
						                    <li class="clearfix">
									        	<h3><a href="logout">Déconnexion</a></h3>
									        </li>
									    </c:otherwise>
					                </c:choose>
			                    </li> 
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

            <div id="content" class="rules">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <!-- kp-breadcrumb -->
                    <div id="main-content" style="width:100% !important" class="pull-left">
                        <div class="page-content">
                            <div class="element element-kp-heading">
                                <h2 class="widget-title"><span>Règles du jeu & Lots à gagner</span></h2>
                                <div class="widget-content">
                                	Salut ! Ici tu trouveras tout ce que tu dois savoir sur Barbylone que ce soit avant ou après ton inscription :-)
                                	<br />
                                	<h5>Lots à gagner</h5>
									<p>
										A l’issue de l’Euro 2016 les premiers du classement général gagneront les lots suivants (merci donc de mettre un email correct afin que nous puissions communiquer avec vous) :
										<br />
										<ul>
											<li>
												<h6>1er : 2 places pour aller voir un match de l’équipe de son choix*</h6>
											</li>
											<li>
												<h6>2ème : 1 maillot de foot*</h6>
											</li>
											<li>
												<h6>3ème : 1 jeu Fifa 16 ou PES 2016 sur PS4 ou autre console*</h6>
											</li>
											<li>
												<h6>4ème : un ballon de foot (petite consolation pour celui ou celle qui finira au pied du podium)</h6>
											</li>
										</ul>
										<br />
									</p>
                                	<h5>Règles du jeu</h5>
                                    <p>
										Tu sais compter jusqu’à 5 ?  C’est bon tu es aptes à comprendre les règles et à jouer !
										<br />
										Le principe, tu pronostiques le score exact de la rencontre avant tirs aux buts et en fonction de tes pronostics tu gagnes des points :
										<br />
										<ul>
											<li>
												<h6>Tu as le score exact = 5 points.</h6>
												Exemple : Tu as un super marabout qui te prédit une victoire 3-2 du Pays de Galles contre la Slovaquie et voilà que le jour j … le Pays de Galles bat la Slovaquie 3-2 avec 3 buts dans les arrêts de jeu de Gareth Bale. Striiike ! 5 points
											</li>
											<li>
												<h6>Tu as le bon pronostic + le bon écart de buts entre les deux équipes = 3 points</h6>
												Exemple : Tu penses que l'Allemagne va facilement s'en sortir contre la Pologne avec un 2-0 classique mais le jour du match, les Polonais réduisent le score à 2-1 et Müller marque à la dernière seconde pour décrocher le 3-1 ! La victoire est présente et il y a 2 buts d'écart comme pour ton prono : 3 points :-) 
											</li>
											<li>
												<h6>Tu as le bon pronostic = 2 points.</h6>
												Exemple : Tu as pronostiqué que la France allait éclater la Roumanie 8 – 0 mais le soir du résultat Giroud et sa bande ont les pieds carrés et gagnent 1-0 sur un but d'Hugo Lloris depuis ses cages. Tu as 2 points !
											</li>
											<li>
												<h6>Tu prends des risques (moins de 20% des pronostiqueurs ont fait le même prono que toi) : points doublés !</h6>
												Exemple : Si tu as le cran de mettre la Roumanie vainqueur contre la France (bouh !), que moins de 20% de la communauté barbylone a pronostiqué comme toi et que ton prono est correct : tes points sont doublés :-)
											</li>
										</ul>
										<br />
										En cas de séance de tirs aux buts le résultat effectif est celui avant la séance de tirs aux buts.
										<h6 style="text-alignment:center">
											<a href="match">S’en est terminé des règles et place aux pronostics maintenant !</a>
										</h6>
										<br />
									</p>
									<p>
										<h5>Petit aperçu de Barbylone</h5>
										<ul>
											<li>
												<h6>Pronostique les matchs de l’Euro 2016 et tente de gagner des lots :</h6>
												<br />
												<a href="/images/screenshot-1.png">
													<img alt="" class="screenshot" src="/images/screenshot-1.png">
												</a>
											</li>
											<li>
												<h6>Crée des groupes avec tes amis (ou amies ;)) pour jouer entre potes :</h6>
												<br />
												<a href="/images/screenshot-2.png">
													<img alt="" class="screenshot" src="/images/screenshot-2.png">
												</a>
											</li>
											<li>
												<h6>Mesure toi à toute la communauté barbylone à travers le classement général :</h6>
												<br />
												<a href="/images/screenshot-3.png">
													<img alt="" class="screenshot" src="/images/screenshot-3.png">
												</a>
											</li>
											<li>
												<h6>Suis en direct les résultats de chaque match à travers un live et commentes le match avec tes potes et la communauté Barbylone :</h6>
												<br />
												<a href="/images/screenshot-4.png">
													<img alt="" class="screenshot" src="/images/screenshot-4.png">
												</a>
											</li>
										</ul>
									</p>
									<br />
									<p style="font-style:italic; font-size:10px">* Dans la limite des stocks disponibles. Maillots de la saison 2015-2016. 30€ maximum par place</p>
                                </div>
                                <!-- widget-content -->
                            </div>
                        </div>
                        <!-- page-element -->
                    </div>
                    <!-- main-content -->
                </div>
                <!-- wraper -->
                <div class="clearfix"></div>
            </div>
            <!-- content -->
        </div>
                
        <%@include file="footer.jsp" %>
        <!--[if !IE]><!-->
		<script src="js/ie10.js"></script>
		<script src="js/chart/Chart.js"></script>
		<script type="text/javascript" src="js/custom.js"></script>
    </body>
</html>