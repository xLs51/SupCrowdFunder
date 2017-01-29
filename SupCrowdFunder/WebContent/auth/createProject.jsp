<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Create a project !</h1><br/><br/>
	<form class="form-horizontal" method="post" action="/SupCrowdFunder/auth/createProject" onSubmit="return validateCreateProjectForm(this);">
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
	   	<div id="start_date" class="control-group">
	    	<label class="control-label" for="inputStart_date">Start Date</label>
	    		<div class="controls" id="start_dateControl">
	    			<input type="text" id="inputStart_date" placeholder="Start Date (dd/mm/yyyy)" name="start_date">
	    		</div>
	   	</div>
	   	<div id="end_date" class="control-group">
	    	<label class="control-label" for="inputEnd_date">End Date</label>
	    		<div class="controls" id="end_dateControl">
	    			<input type="text" id="inputEnd_date" placeholder="End Date (dd/mm/yyyy)" name="end_date">
	    		</div>
	   	</div>
	   	<div id="goal" class="control-group">
	    	<label class="control-label" for="inputGoal">Money Goal</label>
	    		<div class="controls" id="goalControl">
	    			<input type="text" id="inputGoal" placeholder="Money Goal" name="goal">
	    		</div>
	   	</div>
	    <div id="category" class="control-group">
	    	<label class="control-label" for="inputCategory">Category</label>
	    	<div class="controls" id="categoryControl">
		   		<select name="category">
		   			<%-- List all the categories --%>
			   		<c:forEach items="${requestScope.listCategory}" var="lc">
						<option value="${lc.id}">${lc.name} 
					</c:forEach>
				</select>
			</div>
	   	</div>	
	   	<div id="rewards" class="control-group">
	    	<label class="control-label" for="inputRewards">Rewards</label>
	    		<div class="controls" id="goalControl">
	    			<input type="text" id="inputRewards" placeholder="Name" name="rewardsName">
	    			<input type="text" id="inputRewards" placeholder="Description" name="rewardsDescription">
	    			<input type="text" id="inputRewards" placeholder="Minimum Price" name="rewardsPriceMin">
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