<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>List of all the category !</h1>
	<c:if test="${empty category}">
		<%-- Div to display the success message --%>
		<div id="alert"></div>
		<div class="bs-docs-grid">
			<div class="row show-grid">
				<div class="span2">Name</div>
		        <div class="span3">Description</div>
		        <div class="span1"></div>
		        <div class="span1"></div>
		    </div>
			<%-- List of all the categories --%>
			<c:forEach items="${requestScope.listCategory}" var="lc">
				<div class="row show-grid">
					<form class="form-inline" method="post" action="/SupCrowdFunder/auth/admin/updateCategory?id=${lc.id}" onSubmit="return validateCategoryAdminForm(this);">
						<div class="span2"><input type="text" class="input-small" placeholder="Name" value="<c:out value="${lc.name}"/>" name="name"></div>
						<div class="span3"><input type="text" class="input" placeholder="Description" value="<c:out value="${lc.description}"/>" name="description"></div>
						<div class="span1"><a href="/SupCrowdFunder/auth/admin/removeCategory?id=${lc.id}">Delete</a></div>
						<div class="span1"><button type="submit" class="btn btn-primary">Update</button></div>
					</form>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty category}">
		<div class="alert alert-error">This category is associated to a project and cannot be deleted</div>
	</c:if>
	
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>