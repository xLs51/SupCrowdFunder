<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Important Data !</h1><br/>
	Number of users : <b>${requestScope.numberUser}</b> <br/>
	Number of contributions : <b>${requestScope.numberContribution}</b> <br/>
	Total of contributions prices : <b>${requestScope.numberContributionPrice}</b> euros<br/>
	Number of projects : <b>${requestScope.numberProject}</b> <br/>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>