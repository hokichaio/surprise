<%@ page language="java"
         session="false"
         pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/spring-social/facebook/tags" prefix="facebook" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../com/meta.jsp"%>	
</head>
<body>
	<%@ include file="../com/header.jsp"%>	
	<div class="container">
		<h1>Welcome to Surprise!</h1>
		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#1" data-toggle="tab">Surprise Log</a>
			</li>
			<c:if test="<%= SecurityContext.userSignedIn() %>">
				<li>
					<a href="#2" data-toggle="tab">My Log</a>
				</li>
			</c:if>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="1">
				<p>
					<i class="icon-glass"></i>Kevin surprised Queen by <a href="#">Starbucks Coffee</a> 
				</p>
				<p>
					<i class="icon-glass"></i>Issa surprised Sakai by <a href="#">Strawberry Cake</a> 
				</p>
				<p>
					<i class="icon-glass"></i>Osaki, Matsumoto surprised Sano by <a href="#">Apple Pie</a> 
				</p>
			</div>
			<c:if test="<%= SecurityContext.userSignedIn() %>">
				<div class="tab-pane" id="2">
					<div class="span12">
						<i class="icon-glass"></i>Kevin surprised Queen by <a href="#">Starbucks Coffee</a> 
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>