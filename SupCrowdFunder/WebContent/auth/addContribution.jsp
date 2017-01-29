<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Include Header --%>
<%@ include file="/header.jsp" %>
<div class="page-header">
	<h1>Add a contribution !</h1><br/><br/>
	<form class="form-horizontal" method="post" action="/SupCrowdFunder/auth/addContribution" onSubmit="return validateContributionForm(this);">
		<div id="amount" class="control-group">
	    	<label class="control-label" for="inputAmount">Amount</label>
	    		<div class="controls" id="amountControl">
	    			<input type="text" id="inputAmount" placeholder="Amount" name="amount" onkeyup="allowRewards();">
	    		</div>
	   	</div>
	   	<div id="rewards" class="control-group">
	    	<label class="control-label" for="inputRewards">Rewards</label>
	    		<div class="controls" id="rewardsControl">
	    				<%-- List all the rewards --%>
				   		<c:forEach items="${listRewards}" var="lr">
							<input type="radio" name="rewards" value="${lr.id}"> ${lr.price_min}+ euros ${lr.name} ${lr.description} <br/>
							<input type="hidden" name="priceMin" value="${lr.price_min}">
						</c:forEach>
	    		</div>
	   	</div>
	   	<input name="idProject" type="hidden" value="${Project.id}">
	    <div class="control-group">
	    	<div class="controls">
	    		<button type="submit" class="btn btn-primary">Add</button>
	    	</div>
		</div>
   	 </form>
</div>
<%-- Include Footer --%>
<%@ include file="/footer.jsp" %>