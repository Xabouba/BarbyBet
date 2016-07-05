<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
            <div id="content" class="information">
                <div class="wraper clearfix">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                    	<div id="sidebar-main-content" class="pull-left sidebar-minimized-rank">
                    		<%@include file='minimized-rank.jsp'%>
                    	</div>
                        <div class="results page-single pull-left" >
                     			<h2 class="widget-title">
                     				<span>Mon Profil : <c:out value="${currentUser.username}"></c:out> </span>
                     			</h2>
                                <div class="my-profile-content">
                                   	<form action="myProfile" method="post" id="kp-form-contact">
                                   		<div id="profile-pic">
                                   			<img class="rounded-image-my-profile" src="https://scontent-cdg2-1.xx.fbcdn.net/t31.0-8/458688_495448573812902_429598702_o.jpg" />
                                   			<br />
                                   			<br />
                                       	 	<input type="submit" name="sbumit" class="btn btn-primary" value="Modifier" id="input-submit">
                                   		</div>
                                   		<div id="profile-info" style="float:right; width: 78%; ">
	                                        <div class="form-group">
                                            	<label for="select-gender">Je suis : </label>
                                            	<br />
                                            	<select name="gender" id="select-gender">
                                            		<option value="other"></option>
													<option value="man">Un Homme</option>
													<option value="woman">Une Femme</option>
												</select>
	                                        </div>
	                                        <div class="form-group">
                                            	<label for="select-my-pronos">J'autorise mes pronostics à être visible : </label>
                                            	<br />
                                            	<select name="my-pronos" id="select-my-pronos">
													<option value="yes">Oui</option>
													<option value="no">Non</option>
												</select>
	                                        </div>
	                                        <div class="form-group">
	                                            <label for="input-last-name">Nom : </label>
	                                            <input type=text placeholder="Ajoutez votre nom" value="${currentUser.lastName}" class="form-control" id="input-last-name" name="first-name">
	                                        </div>
	                                        <!-- form group -->
	                                        <div class="form-group">
	                                            <label for="input-first-name">Prénom : </label>
	                                            <input type=text placeholder="Ajoutez votre prénom" value="${currentUser.firstName}" class="form-control" id="input-first-name" name="last-name">
	                                        </div>
	                                        <!-- form group -->
	                                        <div class="form-group">
	                                            <label for="input-email" >Email : </label>
	                                            <input type=text placeholder="Ajoutez votre email" value="${currentUser.email}" class="form-control" id="input-email" name="email">
	                                        </div>
	                                        <div class="form-group">
	                                            <label for="input-birthday">Date de naissance : </label>
	                                            <br />
												<select name="birth-day" id="select-birth-day">
													<option value="0"></option>
													<c:forEach begin="1" end="31" varStatus="day">
													    <option value="${day.index}">${day.index}</option>
													</c:forEach>
												</select>
												<select name="birth-month" id="select-birth-month">
													<option value="0"></option>
													<option value="1">Janvier</option>
													<option value="2">Février</option>
													<option value="3">Mars</option>
													<option value="4">Avril</option>
													<option value="5">Mai</option>
													<option value="6">Juin</option>
													<option value="7">Juillet</option>
													<option value="8">Août</option>
													<option value="9">Septembre</option>
													<option value="10">Octobre</option>
													<option value="11">Novembre</option>
													<option value="12">Décembre</option>
												</select>
												<select name="birth-year" id="select-birth-year">
													<option value="0"></option>
													<c:forEach begin="1900" end="2010" varStatus="year">
													    <option value="${year.index}">${year.index}</option>
													</c:forEach>
												</select>
	                                        </div>
	                                        <!-- form group -->
	                                        <div class="form-group">
	                                            <label for="input-location">Localisation : </label>
	                                            <input type=text placeholder="Ajoutez votre position" value="${currentUser.location}" class="form-control" id="input-location" name="location">
	                                        </div>
	                                        <!-- form group -->
	                                        <div class="form-group">
	                                            <label for="input-website">Site web : </label>
	                                            <input type=text placeholder="Ajoutez votre site Web" value="${currentUser.website}" class="form-control" id="input-website" name="website">
	                                        </div>
	                                        <div class="form-group">
	                                            <label for="input-name">Biographie : </label>
	                                            <textarea placeholder="Ajoutez une bio à votre profil" class="form-control" id="textarea-biography" name="biography" rows="6">${currentUser.biography}</textarea>
	                                        </div>
                                       		<!-- form group -->
                                       	 	<input type="submit" name="submit" class="btn btn-primary" value="Mettre à jour" id="input-submit">
                                        </div>
                                	</form>
	                    		</div>
		                <!-- main-col -->
	                    <div class="clearfix"></div>
                        <!-- page-single -->
                    </div>
                    <!-- main-content -->
                </div>
                <!-- wraper -->
                <div class="clearfix"></div>
            </div>
            <!-- content -->
        </div>
        
        <%@include file="footer.jsp" %>
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
        
        <script type="text/javascript">
	        var cw = $('#profile-pic').width();
	        $('#profile-pic').css({
	            'height': cw + 'px'
	        }); 
	        
	        $(function() {
	          $( "#input-birthday").datepicker();
	        });
		</script>
    </body>
</html>    