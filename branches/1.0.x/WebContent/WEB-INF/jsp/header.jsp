<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="page-header">
	<div class="top-header clearfix">
	    <div class="title_BarbyBet" id="logo"><a href="index">Barbylone</a></div>
	    <nav>
	        <ul class="sf-menu" id="main-menu">
	            <li class="current-menu-item">
	                <a href="index">Accueil</a>
	            </li>
	            <li>
                    <a href="match">Pronostiquer</a>
                </li>
	            <li>
                    <a href="#">R�sultats</a>
                    <ul>
                        <li><a href="resultats">Groupe</a></li>
                        <li><a href="finalStage">Phase finale</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">Groupes</a>
                    <ul>
                        <li><a href="createGroup">Cr�er Groupe</a></li>
                        <li><a href="group">Mes Groupes</a></li>
                    </ul>
                </li>
                <li>
                    <a href="rank">Classements</a>
                </li>
                <li>
                    <a href="rules">R�gles du jeu</a>
                </li>
                <%-- Vérification de l'absence du nom d'utilisateur en cookie --%>
                <c:choose>
					<c:when test="${empty cookie.currentUserId}">
				    	<li>
	                    	<a href="register">Inscription</a>
	                    </li>
				    </c:when>
					<c:otherwise>
				        <li>
	                        <a href="logout">D�connexion</a>
	                    </li>
				    </c:otherwise>
                </c:choose>
            </ul>
            <div id="mobile-menu">
                <span>Menu</span>
                <ul id="toggle-view-menu">
                    <li class="clearfix">
                        <h3><a href="index">Accueil</a></h3>
                    </li>
                    <li class="clearfix">
                        <h3><a href="match">Pronostiquer</a></h3>
                    </li>
                    <li class="clearfix">
                        <h3><a href="resultats">Phase groupe</a></h3>
                    </li>
                    <li class="clearfix">
                        <h3><a href="finalStage">Phase finale</a></h3>
                    </li>
                    <li class="clearfix">
                        <h3><a href="createGroup">Cr�er Groupe</a></h3>
                    </li>
                    <li class="clearfix">
                        <h3><a href="group">Mes Groupes</a></h3>
                    </li>
                    <li class="clearfix">
                    	<h3><a href="rank">Classements</a></h3>
                    </li>
                    <li class="clearfix">
                    	<h3><a href="rules">R�gles du jeu</a></h3>
                    </li>   
                    <li class="clearfix">
                    	<%-- Vérification de l'absence du nom d'utilisateur en cookie --%>
		                <c:choose>
							<c:when test="${empty cookie.currentUserId}">
						    	<h3><a href="register">Inscription</a></h3>
						    </c:when>
							<c:otherwise>
						        <h3><a href="logout">D�connexion</a></h3>
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
            