<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Add a user !</h1><br/><br/>
	<form class="form-horizontal" method="post" action="/SupCrowdFunder/auth/admin/addUser" onSubmit="return validateUserForm(this);">
		<div id="mail" class="control-group">
	    	<label class="control-label" for="inputMail">Mail</label>
	    		<div class="controls" id="mailControl">
	    			<input type="text" id="inputMail" placeholder="Mail" name="mail">
	    		</div>
	   	</div>
	   	<div id="lastName" class="control-group">
	    	<label class="control-label" for="inputLastName">Last Name</label>
	    		<div class="controls" id="lastNameControl">
	    			<input type="text" id="inputLastName" placeholder="Last Name" name="lastName">
	    		</div>
	   	</div>
	   	<div id="firstName" class="control-group">
	    	<label class="control-label" for="inputFirstName">First Name</label>
	    		<div class="controls" id="firstNameControl">
	    			<input type="text" id="inputFirstName" placeholder="First Name" name="firstName">
	    		</div>
	   	</div>
	   	<div id="address" class="control-group">
	    	<label class="control-label" for="inputAddress">Address</label>
	    		<div class="controls" id="addressControl">
	    			<input type="text" id="inputAddress" placeholder="Address" name="address">
	    		</div>
	   	</div>
	   	<div id="password" class="control-group">
	    	<label class="control-label" for="inputPassword">Password</label>
	    		<div class="controls" id="passwordControl">
	    			<input type="password" id="inputPassword" placeholder="Password" name="password">
	    		</div>
	   	</div>
	    <div class="control-group">
	    	<div class="controls">
	    		<button type="submit" class="btn btn-primary">Add</button>
	    	</div>
		</div>
   	 </form>
   	<%-- If mail is already used --%>
	<c:if test="${!empty requestScope.error}">
		<div class="alert alert-error">${requestScope.error}</div>
	</c:if>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>