<%@ page language="java"
         session="false"
         pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="app.of.k.social.SecurityContext" %>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="brand" href="./">Surprise!</a>
			<div class="nav-collapse">
				<ul class="nav">
					<li><a href="./gift">Find a gift</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">FAQ</a></li>
				</ul>
				<ul class="nav pull-right">
					<c:if test="<%= !SecurityContext.userSignedIn() %>">
						<form class="navbar-form pull-right" name="login" id="login" action="<c:url value="/signin/facebook" />" method="POST">
				 			<input type="hidden" name="scope" value="email,publish_stream,offline_access,friends_birthday" />
							<ul class="nav pull-right">
								<li><a href="javascript:void(0);" onclick="document.login.submit(); return false;" >Sign In</a></li>
							</ul>
						</form>
					</c:if>
					<c:if test="<%= SecurityContext.userSignedIn() %>">
						<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#"> User <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<a href="./mypage"><i class="icon-user"></i>My Page</a>
								<a href="<c:url value="/signout" />"><i class="icon-off"></i>Sign Out</a>
							</ul>
						</li>

					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>