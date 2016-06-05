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
					                    <a href="rules">Règles du jeu</a>
					                </li>
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
						                    <li class="clearfix">
						                    	<h3><a href="rules">Règles du jeu</a></h3>
						                    </li>
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

            <div id="content">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <!-- kp-breadcrumb -->
                    <div id="main-content" style="width:100% !important" class="pull-left">
                        <div class="page-content">
                            <div class="element element-kp-heading">
                                <h2 class="widget-title"><span>Conditions Générales d'Utilisation</span></h2>
                                <br />
                                <div class="widget-content">
                                    <h4>Article 1 : Objet</h4>
                                    <p>
                                    	Les présentes « conditions générales d'utilisation » ont pour objet l'encadrement juridique des modalités de mise à disposition des services du site barbylone.com et leur utilisation par « l'Utilisateur ».
										Les conditions générales d'utilisation doivent être acceptées par tout Utilisateur souhaitant accéder au site. Elles constituent le contrat entre le site et l'Utilisateur. L’accès au site par l’Utilisateur signifie son acceptation des présentes conditions générales d’utilisation.
										En cas de non-acceptation des conditions générales d'utilisation stipulées dans le présent contrat, l'Utilisateur se doit de renoncer à l'accès des services proposés par le site.
										barbylone.com se réserve le droit de modifier unilatéralement et à tout moment le contenu des présentes conditions générales d'utilisation.
									</p>
									<br />
									<h4>Article 2 : Mentions légales</h4>
                                    <p>
                                    	L'hébergeur du site barbylone.com est la Société OVH (SAS) au capital de 10 069 020,00 € dont le siège social est situé à Roubaix, France.
                                    	
                                    </p>
									<br />
									<h4>Article 3 : Définitions</h4>
                                    <p>
                                    	La présente clause a pour objet de définir les différents termes essentiels du contrat :
											<ul>
												<li>Utilisateur : ce terme désigne toute personne qui utilise le site ou l'un des services proposés par le site.</li>
												<li>Contenu utilisateur : ce sont les données transmises par l'Utilisateur au sein du site.</li>
												<li>Membre : l'Utilisateur devient membre lorsqu'il est identifié sur le site.</li>
												<li>Identifiant et mot de passe : c'est l'ensemble des informations nécessaires à l'identification d'un Utilisateur sur le site. L'identifiant et le mot de passe permettent à l'Utilisateur d'accéder à des services réservés aux membres du site. Le mot de passe est confidentiel.</li>
                                    		</ul>
                                    </p>
                                    <br />
									<h4>Article 4 : Accès aux services</h4>
                                    <p>
                                    	Le site permet à l'Utilisateur un accès gratuit aux services suivants :
                                    	<ul>
                                    		<li>Publication de commentaires</li>
                                    		<li>Edition de pronostics pour des rencontres sportives.</li>
                                    	</ul>
                                    	<br />
                                    	Le site est accessible gratuitement en tout lieu à tout Utilisateur ayant un accès à Internet. Tous les frais supportés par l'Utilisateur pour accéder au service (matériel informatique, logiciels, connexion Internet, etc.) sont à sa charge.
										L’Utilisateur non membre n'a pas accès aux services réservés aux membres. Pour cela, il doit s'identifier à l'aide de son identifiant et de son mot de passe.
										Le site met en œuvre tous les moyens mis à sa disposition pour assurer un accès de qualité à ses services. L'obligation étant de moyens, le site ne s'engage pas à atteindre ce résultat.
										Tout événement dû à un cas de force majeure ayant pour conséquence un dysfonctionnement du réseau ou du serveur n'engage pas la responsabilité de barbylone.com.
										L'accès aux services du site peut à tout moment faire l'objet d'une interruption, d'une suspension, d'une modification sans préavis pour une maintenance ou pour tout autre cas. L'Utilisateur s'oblige à ne réclamer aucune indemnisation suite à l'interruption, à la suspension ou à la modification du présent contrat.
                                    </p>
                                    <br />
									<h4>Article 5 : Propriété intellectuelle</h4>
                                    <p>
                                    	Les marques, logos, signes et tout autre contenu du site font l'objet d'une protection par le Code de la propriété intellectuelle et plus particulièrement par le droit d'auteur.
										L'Utilisateur sollicite l'autorisation préalable du site pour toute reproduction, publication, copie des différents contenus.
										L'Utilisateur s'engage à une utilisation des contenus du site dans un cadre strictement privé. Une utilisation des contenus à des fins commerciales est strictement interdite.
										Tout contenu mis en ligne par l'Utilisateur est de sa seule responsabilité. L'Utilisateur s'engage à ne pas mettre en ligne de contenus pouvant porter atteinte aux intérêts de tierces personnes. Tout recours en justice engagé par un tiers lésé contre le site sera pris en charge par l'Utilisateur. 
										Le contenu de l'Utilisateur peut être à tout moment et pour n'importe quelle raison supprimé ou modifié par le site. L'Utilisateur ne reçoit aucune justification et notification préalablement à la suppression ou à la modification du contenu Utilisateur.
                                    </p>
                                    <br />
									<h4>Article 6 : Données personnelles</h4>
                                    <p>
                                    	Les informations demandées à l’inscription au site sont nécessaires et obligatoires pour la création du compte de l'Utilisateur. En particulier, l'adresse électronique pourra être utilisée par le site pour l'administration, la gestion et l'animation du service.
										Le site assure à l'Utilisateur une collecte et un traitement d'informations personnelles dans le respect de la vie privée conformément à la loi n°78-17 du 6 janvier 1978 relative à l'informatique, aux fichiers et aux libertés. 
										En vertu des articles 39 et 40 de la loi en date du 6 janvier 1978, l'Utilisateur dispose d'un droit d'accès, de rectification, de suppression et d'opposition de ses données personnelles. L'Utilisateur exerce ce droit via :
                                    	<ul>
                                    		<li>Son espace personnel</li>
                                    		<li>Par email à <a href="mailto:contact@barbylone.com">contact@barbylone.com</a></li>
                                    	</ul>
                                    </p>
                                    <br />
									<h4>Article 7 : Responsabilité et force majeure</h4>
                                    <p>
                                    	Les sources des informations diffusées sur le site sont réputées fiables. Toutefois, le site se réserve la faculté d'une non-garantie de la fiabilité des sources. Les informations données sur le site le sont à titre purement informatif. Ainsi, l'Utilisateur assume seul l'entière responsabilité de l'utilisation des informations et contenus du présent site.
										L'Utilisateur s'assure de garder son mot de passe secret. Toute divulgation du mot de passe, quelle que soit sa forme, est interdite.
										L'Utilisateur assume les risques liés à l'utilisation de son identifiant et mot de passe. Le site décline toute responsabilité.
										Tout usage du service par l'Utilisateur ayant directement ou indirectement pour conséquence des dommages doit faire l'objet d'une indemnisation au profit du site.
										Une garantie optimale de la sécurité et de la confidentialité des données transmises n'est pas assurée par le site. Toutefois, le site s'engage à mettre en œuvre tous les moyens nécessaires afin de garantir au mieux la sécurité et la confidentialité des données.
										La responsabilité du site ne peut être engagée en cas de force majeure ou du fait imprévisible et insurmontable d'un tiers.
										<br />
										<br />
										Le site barbylone.com n'est pas responsable :
										<ul>
											<li>en cas de problématiques ou défaillances techniques, informatiques ou de compatibilité du site avec un matériel ou logiciel quel qu'il soit.</li>
											<li>des dommages directs ou indirects, matériels ou immatériels, prévisibles ou imprévisibles résultant de l'utilisation ou des difficultés d'utilisation du site ou de ses services.</li>
											<li>des caractéristiques intrinsèques de l'Internet, notamment celles relatives au manque de fiabilité et au défaut de sécurisation des informations y circulant.</li>
                                    	</ul>
                                    </p>
                                    <br />
									<h4>Article 8 : Liens hypertextes</h4>
                                    <p>
                                    	De nombreux liens hypertextes sortants sont présents sur le site, cependant les pages web où mènent ces liens n'engagent en rien la responsabilité de barbylone.com qui n'a pas le contrôle de ces liens.
										L'Utilisateur s'interdit donc à engager la responsabilité du site concernant le contenu et les ressources relatives à ces liens hypertextes sortants.
										En outre, l'information préalable de l'éditeur du site est nécessaire avant toute mise en place de lien hypertexte.
										Sont exclus de cette autorisation les sites diffusant des informations à caractère illicite, violent, polémique, pornographique, xénophobe ou pouvant porter atteinte à la sensibilité du plus grand nombre.
										Enfin, barbylone.com se réserve le droit de faire supprimer à tout moment un lien hypertexte pointant vers son site, si le site l'estime non conforme à sa politique éditoriale.
                                    </p>
                                    <br />
									<h4>Article 9 : Évolution du contrat</h4>
                                    <p>
                                    	Le site se réserve à tout moment le droit de modifier les clauses stipulées dans le présent contrat.
                                    </p>
                                    <br />
									<h4>Article 10 : Durée</h4>
                                    <p>
                                    	La durée du présent contrat est indéterminée. Le contrat produit ses effets à l'égard de l'Utilisateur à compter de l'utilisation du service.
                                    </p>
                                    <br />
									<h4>Article 11 : Droit applicable et juridiction compétente</h4>
                                    <p>
                                    	La législation française s'applique au présent contrat. En cas d'absence de résolution amiable d'un litige né entre les parties, seuls les tribunaux du ressort de la Cour d'appel de Paris sont compétents.
                                    </p>
                                    <br />
									<h4>Article 12 : Publication par l’Utilisateur</h4>
                                    <p>
                                    	Le site permet aux membres de publier des commentaires.
										Dans ses publications, le membre s’engage à respecter les règles de la Netiquette et les règles de droit en vigueur.
										Le site exerce une modération a priori et a posteriori sur les publications et se réserve le droit de refuser leur mise en ligne, sans avoir à s’en justifier auprès du membre.
										Le membre reste titulaire de l’intégralité de ses droits de propriété intellectuelle. Mais en publiant une publication sur le site, il cède à la société éditrice le droit non exclusif et gratuit de représenter, reproduire, adapter, modifier, diffuser et distribuer sa publication, directement ou par un tiers autorisé, dans le monde entier, sur tout support (numérique ou physique), pour la durée de la propriété intellectuelle. Le Membre cède notamment le droit d'utiliser sa publication sur internet et sur les réseaux de téléphonie mobile.
										La société éditrice s'engage à faire figurer le nom du membre à proximité de chaque utilisation de sa publication.
                                    </p>
                                    <br />
									<h4>Article 13 : Cookies</h4>
                                    <p>
                                    	Le site a éventuellement recours aux techniques de "cookies" lui permettant de traiter des statistiques et des informations sur le trafic, de faciliter la navigation et d'améliorer le service pour le confort de l'utilisateur, lequel peut s'opposer à l'enregistrement de ces "cookies" en configurant son logiciel de navigation.
                                    </p>
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