<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<tilesx:useAttribute name="current" />
<!-- creating variable to describe current scope -->

<!DOCTYPE html>
<html>
<head>

<!-- JQuery libraries -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.11.1/jquery.validate.min.js"></script>

<!-- Boostrap CSS 3.x.x -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap CSS 4.x.x -->
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.4/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- Boostrap js 3.x.x -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Boostrap js 4.x.x -->
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"></script> -->
	

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<div class="container">
		<!-- Static navbar -->
		<nav class="navbar navbar-default navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a style="color: yellow;" class="navbar-brand"
						href='<spring:url value="/"/>'>Feeds App</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${current =='index' ? 'active' :'' }"><a
							href='<spring:url value="/"/>'>Home</a></li>
						<security:authorize access="hasRole('ROLE_ADMIN')"> <!-- authorizing by Spring security methods -->
							<li class="${current =='users' ? 'active' :'' }"><a
								href='<spring:url value="/users"/>'>Users</a></li>
							<li class="${current =='user-feeds' ? 'active' :'' }"><a
								href='<spring:url value="feeds"/>'>Feeds</a></li>
						</security:authorize>
						<li class="${current =='user-register' ? 'active' :'' }"><a
							href='<spring:url value="/register"/>'>Register</a></li>
						<security:authorize access="!isAuthenticated()">
							<li class="${current =='login' ? 'active' :'' }"><a
								href='<spring:url value="/login"/>'>Login</a></li>
						</security:authorize>

						<security:authorize access="isAuthenticated()">
						<li class="${current =='user-account' ? 'active' :'' }"><a href='<spring:url value="/account"/>'>My account</a></li>
							<li><a href='<spring:url value="/logout"/>'>Logout</a></li>
						</security:authorize>
					</ul>
				</div>
<!-- 				/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>
	</div>

	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>