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
						<span class="label">Découvrir Deezer</span>
						</a>
					</div>
					<div class="index-header-actions hidden-phone">
						<form action="register" method="get">
							<button class="btn btn-shadow" id="login_button" type="submit">
								<span class="label">Inscription</span>
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
						<h2 class="heading-2">Pronostiquez l'Euro de Football 2016.
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
										<input id="register_form_mail_input" class="form-control" value="<c:out value="${param.email}" />" placeholder="Adresse email ou pseudo" type="text" name="email" data-check="emailuniq">
									</div>
									<div class="form-group">
										<input id="register_form_password_input" class="form-control" placeholder="Mot de passe" name="password" type="password" data-check="password">
									</div>
								</div>
								<div class="index-form-recover">
									<a class="evt-click" id="login_forgot_password" href="javascript:void(0)">
										<span class="icon icon-info icon-before"></span> Mot de passe oublié ?
									</a>
								</div>
								<c:if test="${not empty errorsLogin}">
									<c:if test="${not empty errorsLogin['email']}">
										<div class="index-form-error" id="register_form_global_error">${errorsLogin['email']}</div>
									</c:if>
									<c:if test="${not empty errorsLogin['password']}">
										<div class="index-form-error" id="register_form_global_error">${errorsLogin['password']}</div>
									</c:if>
									<c:if test="${not empty errorsLogin['connexion']}">
										<div class="index-form-error" id="register_form_global_error">${errorsLogin['connexion']}</div>
									</c:if>
								</c:if>
								<div class="index-form-submit">
									<button class="btn btn-primary btn-large btn-block" id="login_form_submit" type="submit">
										<span class="label">Connexion</span>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
			<!-- End of body -->
    	</div>
	</body>
</html>