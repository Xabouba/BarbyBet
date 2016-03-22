<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="page-header">
	<div class="top-header clearfix">
	    <div class="title_BarbyBet" id="logo"><a href="index.html">BarbyBet</a></div>
	    <nav>
	        <ul class="sf-menu" id="main-menu">
	            <li class="current-menu-item">
	                <a href="accueil">Accueil</a>
	            </li>
                <li>
                    <a href="match">Directs</a>
                </li>
                <li>
                    <a href="contact">contact</a>
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
                    <li class="current-menu-item">
	                <a href="accueil">Accueil</a>
	            </li>
                <li>
                    <a href="match">Directs</a>
                </li>
                <li>
                    <a href="contact">Contact</a>
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
            