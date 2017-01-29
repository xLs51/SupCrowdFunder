<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Add a category !</h1><br/>
	<form class="form-horizontal" method="post" action="/SupCrowdFunder/auth/admin/addCategory" onSubmit="return validateCategoryForm(this);">
		<div id="name" class="control-group">
	    	<label class="control-label" for="inputName">Name</label>
	    		<div class="controls" id="nameControl">
	    			<input type="text" id="inputName" placeholder="Name" name="name">
	    		</div>
	   	</div>
	   	<div id="description" class="control-group">
	    	<label class="control-label" for="inputDescription">Description</label>
	    		<div class="controls" id="descriptionControl">
	    			<input type="text" id="inputDescription" placeholder="Description" name="description">
	    		</div>
	   	</div>
	    <div class="control-group">
	    	<div class="controls">
	    		<button type="submit" class="btn btn-primary">Add</button>
	    	</div>
		</div>
   	 </form>
   	 <%-- Div to display the success message --%>
	<div id="alert"></div>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>