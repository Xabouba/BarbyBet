<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Mot de passe oublié</title>
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
		<link rel="stylesheet" type="text/css" href="css/reset-password.css">
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
									    	<h3><a href="register">Inscription</a></h3>
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

            <div id="content" class="reset-password">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <!-- kp-breadcrumb -->
                    <div id="main-content" style="width:100% !important" class="pull-left">
                        <div class="page-content">
                            <div class="element element-kp-heading">
                                <h2 class="widget-title"><span>Mot de passe oublié</span></h2>
                                <br />
                                <div class="widget-content content-reset-password">
                                	<c:choose>
                                		<c:when test="${not empty changePasswordMode}">
                                			<h5>Vous pouvez modifier votre mot de passe :</h5>
											<c:if test="${not empty changePasswordMsg}">
								           	 	<div id="change-password-msg" style="font-weight:bold; color:red;">
			                                		${changePasswordMsg}
			                                	</div>
												<br />
		                                	</c:if>
											<form name="change-password" action="reset" method="post">
												<input type="hidden" name="userId" value="${userId}" />
												<input type="hidden" name="actionType" value="changePassword" />
												<input type="password" class="form-control" name="password" placeholder="*****" /> <br /><br />
												<input type="password" class="form-control" name="repeatPassword" placeholder="*****" /> <br /><br />
												<input type="submit" class="btn btn-primary" name="submit-button" value="Mettre à jour votre mot de passe" />
											</form>
                                		</c:when>
	                                	<c:when test="${not empty emailSent}">
	                                		<h5>Vous avez un message</h5>
	                                		<p>
	                                			Un email de réinitialisation de votre mot de passe vient de vous être envoyé à l'adresse : ${emailSent}. Merci de consulter votre boite de récéption.
	                                			Si dans quelques minutes vous n'avez toujours pas reçu le message, merci de vérifier que celui-çi n'est pas dans vos messages indésirables.
	                                		</p>
	                                	</c:when>
	                                	<c:otherwise>
											<h5>Veuillez fournir votre adresse email pour réinitialisez votre mot de passe :</h5>
											<c:if test="${not empty resetPasswordMsg}">
								           	 	<div id="reset-password-msg" style="font-weight:bold; color:red;">
			                                		${resetPasswordMsg}
			                                	</div>
												<br />
		                                	</c:if>
											<form name="reset-password" action="reset" method="post">
												<input type="hidden" name="actionType" value="resetPassword" />
												<input type="text" class="form-control" name="email" placeholder="Adresse email" /> <br /><br />
												<input type="submit" class="btn btn-primary" name="submit-button" value="Réinitialiser votre mot de passe" />
											</form>
										</c:otherwise>
									</c:choose>
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