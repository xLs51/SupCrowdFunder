<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>List of all the project !</h1><br/>
	<%-- Div to display the success message --%>
	<div id="alert"></div>
	<div class="bs-docs-grid">
		<div class="row show-grid">
			<div class="span2">Name</div>
	        <div class="span3">Description</div>
	        <div class="span1">Goal</div>
	        <div class="span1">Current Fund</div>
	        <div class="span1">Nb contribution</div>
	        <div class="span2">Start Date</div>
	        <div class="span2">End Date</div>
	        <div class="span3">Creator</div>
	        <div class="span1"></div>
	        <div class="span1"></div>
	    </div>
		<%-- List of all the projects --%>
		<c:forEach items="${requestScope.listProject}" var="lp">
			<div class="row show-grid">
				<form class="form-inline" method="post" action="/SupCrowdFunder/auth/admin/updateProject?id=${lp.id}" onSubmit="return validateProjectAdminForm(this);">
					<div class="span2"><input type="text" class="input-small" placeholder="Name" value="<c:out value="${lp.name}"/>" name="name"></div>
					<div class="span3"><input type="text" class="input" placeholder="Description" value="<c:out value="${lp.description}"/>" name="description"></div>
					<div class="span1"><input type="text" class="input-mini" placeholder="Goal" value="<c:out value="${lp.goal}"/>" name="goal"></div>
					<div class="span1"><input type="text" class="input-mini" placeholder="Current Fund" value="<c:out value="${lp.currentFund}"/>" name="currentFund"></div>
					<div class="span1"><input type="text" class="input-mini" placeholder="Contribution" value="<c:out value="${lp.nb_contribution}"/>" name="contribution"></div>
					<div class="span2"><input type="text" class="input-small" placeholder="Start Date" value="<fmt:formatDate value="${lp.start_date}" pattern="dd/MM/yyyy"/>" name="start_date"></div>
					<div class="span2"><input type="text" class="input-small" placeholder="End Date" value="<fmt:formatDate value="${lp.end_date}" pattern="dd/MM/yyyy"/>" name="end_date"></div>
					<div class="span3"><input type="text" class="input" placeholder="Id Creator" value="<c:out value="${lp.user_fk.id} (${lp.user_fk.lastName} ${lp.user_fk.firstName})"/>" name="creator"></div>
					<div class="span1"><a href="/SupCrowdFunder/auth/admin/removeProject?id=${lp.id}">Delete</a></div>
					<div class="span1"><button type="submit" class="btn btn-primary">Update</button></div>
				</form>
			</div>
		</c:forEach>
	</div>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>