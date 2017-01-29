<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
      	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	    <title>SupCrowdFunder</title>
	    <!-- Include CSS -->
	    <link href="/SupCrowdFunder/css/bootstrap.css" rel="stylesheet">
	    <link href="/SupCrowdFunder/css/supcrowdfunder.css" rel="stylesheet">
	     <link href="/SupCrowdFunder/css/alertify.core.css" rel="stylesheet">
	    <link href="/SupCrowdFunder/css/alertify.bootstrap.css" rel="stylesheet">
  	</head>
	<body>
	     <div class="navbar navbar-inverse">
    		<div class="navbar-inner">
   				<a class="brand" href="/SupCrowdFunder/">SupCrowdFunder</a>
    			<ul class="nav">
   					<li class="active"><a href="/SupCrowdFunder/">Home</a></li>
   					<%-- If user is logged --%>
   					<c:if test="${!empty sessionScope.username}">
   						<li><a href="/SupCrowdFunder/auth/updateProfil">Welcome ${sessionScope.username}</a></li>
   					</c:if>
   					<%-- If user is logged and admin --%>
   					<c:if test="${!empty sessionScope.usernameAdmin}">
   						<li class="dropdown">
	                		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Category<b class="caret"></b></a>
	                		<ul class="dropdown-menu">
	                  			<li><a href="/SupCrowdFunder/auth/admin/listCategory">Update/Delete</a></li>
	                  			<li><a href="/SupCrowdFunder/auth/admin/addCategory.jsp">Add</a></li>
	                		</ul>
              			</li>
   					</c:if>
   					<%-- If user is logged and admin --%>
   					<c:if test="${!empty sessionScope.usernameAdmin}">
   						<li class="dropdown">
	                		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Project<b class="caret"></b></a>
	                		<ul class="dropdown-menu">
	                  			<li><a href="/SupCrowdFunder/auth/admin/listProject">Update/Delete</a></li>
	                  			<li><a href="/SupCrowdFunder/auth/admin/addProject">Add</a></li>
	                		</ul>
              			</li>
   					</c:if>
   					<%-- If user is logged and admin --%>
   					<c:if test="${!empty sessionScope.usernameAdmin}">
   						<li class="dropdown">
	                		<a href="#" class="dropdown-toggle" data-toggle="dropdown">User<b class="caret"></b></a>
	                		<ul class="dropdown-menu">
	                  			<li><a href="/SupCrowdFunder/auth/admin/listUser">Update/Delete</a></li>
	                  			<li><a href="/SupCrowdFunder/auth/admin/addUser.jsp">Add</a></li>
	                		</ul>
              			</li>
   					</c:if>
   					<%-- If user is logged and admin --%>
   					<c:if test="${!empty sessionScope.usernameAdmin}">
	                  	<li><a href="/SupCrowdFunder/auth/admin/viewData">View important data</a></li>
   					</c:if>
   					<%-- For anonymous --%>
   					<li><a href="/SupCrowdFunder/projectByCategory">All Project by Category</a></li>
   					<%-- If user is logged and not admin --%>
   					<c:if test="${!empty sessionScope.username && empty sessionScope.usernameAdmin}">
   						<li><a href="/SupCrowdFunder/auth/createProject">Create a Project</a></li>
   					</c:if>
   					<%-- Sign in if user is not logged else Logout --%>
    				<c:choose>
    					<c:when test="${empty sessionScope.username}">
    						<li><a href="/SupCrowdFunder/sign_up.jsp">Sign Up</a></li>
    					</c:when>
    					<c:otherwise>
    						<li><a href="/SupCrowdFunder/logout">Logout</a></li>
    					</c:otherwise>
    				</c:choose>
   				</ul>
   				<%-- If user is not logged --%>
   				<c:if test="${empty sessionScope.username}">
					<form class="navbar-form pull-right" method="post" action="/SupCrowdFunder/login">
						<input class="span2" id="inputWarning" type="text" placeholder="Mail" name="mail">
	             		<input class="span2" type="password" placeholder="Password" name="password">
	              		<button type="submit" class="btn">Sign in</button>
	            	</form>
	            </c:if>
    		</div>
    	</div>