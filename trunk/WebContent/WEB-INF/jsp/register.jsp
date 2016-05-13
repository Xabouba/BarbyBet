<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
	<head>
		<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<body>
		<div class="page-signup" id="page_index">
			<!-- Start of header -->
			<header class="index-header" id="index-header">
				<div class="index-container">
					<span class="index-header-logo icon icon-deezer"></span>
					<div class="index-header-actions index-header-discover pull-left hidden-phone hidden-tablet">
						<a class="btn btn-shadow" href="/features">
						<span class="label"></span>
						</a>
					</div>
					<div class="index-header-actions hidden-phone">
						<form action="login">
							<button class="btn btn-shadow" id="login_button" type="submit">
								<span class="label">Connexion</span>
							</button>
						</form>
					</div>
				</div>
			</header>
			<!-- End of header -->
			<!-- Start of body -->
			<section class="index-body">
				<div class="index-container">
					<div class="index-headings">
						<h1 class="heading-1">PRONOSTICS 100% GRATUITS ENTRE AMIS <br class="visible-phone"></h1>
						<h2 class="heading-2">Pronostiquez l'Euro de Football 2016.<br class="hidden-phone">Inscrivez-vous gratuitement.</h2>
					</div>
					<div class="index-form " id="index_form">
						<!-- 
							<div class="index-social clearfix" id="social_form">
								<div class="index-social-col">
									<button id="home_account_fb" class="btn btn-facebook btn-block evt-click" type="button" data-login-redirect="{&quot;type&quot;:&quot;refresh&quot;,&quot;link&quot;:&quot;\/&quot;}" data-tracking="1" data-tracking-tag="unlogged_home_click" data-tracking-params="{'type': 'facebook'}">
										<span class="label">Facebook</span>
									</button>
								</div>
								<div class="index-social-col">
									<button id="home_account_gp" class="btn btn-googleplus btn-block evt-click" type="button" data-redirect="{&quot;type&quot;:&quot;refresh&quot;,&quot;link&quot;:&quot;\/&quot;}" data-tracking="1" data-tracking-tag="unlogged_home_click" data-tracking-params="{'type': 'google'}" data-gapiattached="true">
										<span class="label">Google+</span>
									</button>
								</div>
							</div>
						 -->
						<div id="register_form">
							<form data-type="form-page" method="post">
								<div class="index-form-groups clearfix">
									<div class="form-group">
										<input id="register_form_mail_input" class="form-control" value="<c:out value="${param.email}" />" placeholder="Adresse email" type="text" name="email" data-check="emailuniq">
										<span id="register-email-error" class="form-control-error">Le format de votre adresse email n'est pas valide.</span>
									</div>
									<div class="form-group">
										<input id="register_form_password_input" class="form-control" placeholder="Mot de passe" name="password" type="password" data-check="password">
									</div>
									<div class="form-group">
										<input id="register_form_repeat_password_input" class="form-control" placeholder="Répéter mot de passe" name="repeatPassword" type="password" data-check="password">
										<span id="register-repeat-password-error" class="form-control-error">Vos mots de passes ne sont pas identiques</span>
									</div>
									<div class="form-group">
										<input id="register_form_username_input" class="form-control" value="<c:out value="${param.username}" />" placeholder="Pseudo" name="username" type="text" data-check="blogname" value="">
									</div>
								</div>
								<c:if test="${not empty errorsRegister}">
									<c:if test="${not empty errorsRegister['email']}">
										<div class="index-form-error" id="register_form_global_error">${errorsRegister['email']}</div>
									</c:if>
									<c:if test="${not empty errorsRegister['password']}">
										<div class="index-form-error" id="register_form_global_error">${errorsRegister['password']}</div>
									</c:if>
									<c:if test="${not empty errorsRegister['username']}">
										<div class="index-form-error" id="register_form_global_error">${errorsRegister['username']}</div>
									</c:if>
									<c:if test="${not empty errorsRegister['inscription']}">
										<div class="index-form-error" id="register_form_global_error">${errorsRegister['inscription']}</div>
									</c:if>
								</c:if>
								<div class="index-form-submit">
									<button class="btn btn-primary btn-large btn-block" id="register_form_submit" type="submit">
										<span class="label">Inscription</span>
									</button>
								</div>
								<div class="index-form-legal">
									En cliquant sur "Inscription", vous acceptez les <a class="evt-click" href="" target="_blank" data-tracking="1" data-tracking-tag="register_choice_click" data-tracking-params="{'registration_type': 'cgu', 'context': 'unlogged_home'}">Conditions générales d'utilisation</a>.		</div>
									<div class="visible-phone">
									<a class="btn btn-shadow btn-large btn-block evt-click" href="/login" id="register_form_login_button" data-login="1">
										<span class="label">Connexion</span>
									</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
			<!-- End of body -->
    	</div>
    	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="js/registration.js"></script>
	</body>
</html>