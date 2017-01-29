<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Page of the project : ${showProject.name}</h1>
	Name : <c:out value="${showProject.name}"/><br/>
	Description : <c:out value="${showProject.description}"/><br/>
	Category : <c:out value="${showProject.category_fk.name}"/><br/>
	Goal : <c:out value="${showProject.goal}"/><br/>
	Current Fund : <c:out value="${showProject.currentFund}"/><br/>
	<%-- Set the format of % : #,## --%>
	Percentage : <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(showProject.currentFund/showProject.goal)*100}" />%<br/>
	Creator : <c:out value="${showProject.user_fk.lastName}"/> <c:out value="${showProject.user_fk.firstName}"/><br/>
	<%-- Set the format of dates : dd/MM/yyyy --%>
	Start date : <fmt:formatDate value="${showProject.start_date}" pattern="dd/MM/yyyy"/><br/>
	End date : <fmt:formatDate value="${showProject.end_date}" pattern="dd/MM/yyyy"/><br/>
	Nb Contribution : <c:out value="${showProject.nb_contribution}"/><br/><br/>
	<%-- If user is logged --%>
	<c:if test="${!empty sessionScope.username}">	
		<a href="/SupCrowdFunder/auth/addContribution?id=${showProject.id}"><input class="btn btn-primary" type="button" value="Contribute"></a>
		<%-- If the user contributed --%>
		<c:if test="${!empty listContribution}">
			<br/><br/>Your contribution : <br/>
			<ul>
				<c:forEach items="${listContribution}" var="lc">
					<li><c:out value="${lc.price}"/> euros<br/>
				</c:forEach>
			</ul>
		</c:if>
	</c:if>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>