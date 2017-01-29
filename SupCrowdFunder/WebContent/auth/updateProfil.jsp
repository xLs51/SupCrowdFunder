<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Update your profil !</h1>
	<form class="form-inline" method="post" action="/SupCrowdFunder/auth/updateProfil" onSubmit="return validateUpdateProfilForm(this);">
		<div class="bs-docs-grid">
			<div class="row show-grid">
				<div class="span3">Mail</div>
	            <div class="span2">Last Name</div>
	            <div class="span2">First Name</div>
	            <div class="span3">Address</div>
	            <div class="span2">Password</div>
	            <div class="span2">Confirm Password</div>
	            <div class="span2"></div>
            </div>
            <div class="row show-grid">
				<input type="hidden" value="<c:out value="${showUser.id}"/>" name="id">
	             <div class="span3"><input type="text" class="input-medium" placeholder="Mail" value="<c:out value="${showUser.mail}"/>" name="mail"></div>
	             <div class="span2"><input type="text" class="input-small" placeholder="Last Name" value="<c:out value="${showUser.lastName}"/>" name="lastName"></div>
	             <div class="span2"><input type="text" class="input-small" placeholder="First Name" value="<c:out value="${showUser.firstName}"/>" name="firstName"></div>
	             <div class="span3"><input type="text" class="input-medium" placeholder="Address" value="<c:out value="${showUser.address}"/>" name="address"></div>
	             <div class="span2"><input type="password" class="input-small" placeholder="Password" value="<c:out value="${showUser.password}"/>" name="password"></div>
	             <div class="span2"><input type="password" class="input-small" placeholder="Confirm Password" value="" name="confirmPassword"></div>
	             <div class="span2"><input type="submit" class="btn btn-primary" onclick="return alertSuccess()" value="Update"></div>
            </div>
		</div>
	</form>
	<%-- Div to display the success message --%>
	<div id="alert"></div>
	You have <b>${requestScope.nbProject}</b>
	<%-- Use the plural or not according to the number of project --%>
	<c:choose>
		<c:when test="${requestScope.nbProject>1}">projects :</c:when>
		<c:when test="${requestScope.nbProject==1}">project :</c:when>
		<c:otherwise>project.</c:otherwise>
	</c:choose>
	<ul>
		<%-- List all the projects created by the user --%>
		<c:forEach items="${requestScope.listProject}" var="lp">
			<li><a href="/SupCrowdFunder/showProject?id=${lp.id}">${lp.name}</a>
		</c:forEach>
	</ul>
	You did <b>${requestScope.nbContribution}</b>
	<%-- Use the plural or not according to the number of contribution --%>
	<c:choose>
		<c:when test="${requestScope.nbContribution>1}">contributions,</c:when>
		<c:when test="${requestScope.nbContribution==1}">contribution,</c:when>
		<c:otherwise>contribution.</c:otherwise>
	</c:choose>
	<%-- If the user has atleast 1 contribution --%>
	<c:if test="${requestScope.nbContribution>0}">
		you spend :	
		<ul>
			<%-- List all the contributions done by the user --%>
			<c:forEach items="${requestScope.listContribution}" var="lc">
				<%-- Set and increment a variable to get the total amount of the contribution --%>
				<c:set var="total" value="${total + lc.price}" />
				<li>${lc.price} euros
			</c:forEach>
		</ul>
		a total of <b>${total}</b> euros
	</c:if>
</div>
<%@ include file="/footer.jsp" %>