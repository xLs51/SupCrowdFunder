<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>List of all the user !</h1><br/>
	<div class="bs-docs-grid">
		<div class="row show-grid">
			<div class="span2">Mail</div>
	        <div class="span2">Last Name</div>
	        <div class="span2">First Name</div>
	        <div class="span3">Address</div>
	        <div class="span2">Password</div>
	        <div class="span1">Admin</div>
	        <div class="span1"></div>
	        <div class="span1"></div>
	    </div>
		<%-- List of all the users --%>
		<c:forEach items="${requestScope.listUser}" var="lu">
			<div class="row show-grid">
				<form class="form-inline" method="post" action="/SupCrowdFunder/auth/admin/updateUser?id=${lu.id}" onSubmit="return validateUserAdminForm(this);">
					<div class="span2"><input type="text" class="input-small" placeholder="Mail" value="<c:out value="${lu.mail}"/>" name="mail"></div>
					<div class="span2"><input type="text" class="input-small" placeholder="Last Name" value="<c:out value="${lu.lastName}"/>" name="lastName"></div>
					<div class="span2"><input type="text" class="input-small" placeholder="First Name" value="<c:out value="${lu.firstName}"/>" name="firstName"></div>
					<div class="span3"><input type="text" class="input" placeholder="Address" value="<c:out value="${lu.address}"/>" name="address"></div>
					<div class="span2"><input type="text" class="input-small" placeholder="Password" value="<c:out value="${lu.password}"/>" name="password"></div>
					<div class="span1"><input type="checkbox" name="admin" <c:if test="${lu.admin}">checked</c:if>></div>
					<c:if test="${idUser != lu.id}">
						<div class="span1"><a href="/SupCrowdFunder/auth/admin/removeUser?id=${lu.id}">Delete</a></div>
					</c:if>
					<c:if test="${idUser == lu.id}">
						<div class="span1"></div>
					</c:if>
					<div class="span1"><button type="submit" class="btn btn-primary">Update</button></div>
				</form>
			</div>
		</c:forEach>
	</div>
	<c:if test="${!empty(requestScope.error)}">
		<div class="alert alert-error">${requestScope.error}</div>
	</c:if>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>