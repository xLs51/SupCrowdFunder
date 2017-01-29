<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- Include Header --%>
<%@ include file="header.jsp" %>
<div class="page-header">
 	<h1>Project
 		<%-- Display a different sentence if the category is selected or not --%>
		<c:choose>
			<c:when test="${empty Category.name}">by Category</c:when>
			<c:otherwise>with the ${Category.name} category</c:otherwise> 
		</c:choose>
	</h1>
	<%-- Choose the category --%>
	<form method="post" style="text-align: center;">
		<label class="control-label" for="inputCategory">Category :</label>
		<div class="controls" id="categoryControl">
		   	<select name="category">
			   	<c:forEach items="${listCategory}" var="lc">
					<option value="${lc.id}">${lc.name} 
				</c:forEach>
			</select>
		</div>
	 	<input class="btn btn-primary" type="submit" value="Submit">
	</form>	
 	<%-- List all the projects --%>
    <c:forEach items="${listProject}" var="lp">
    	<div class="project">
    		<div id="nameProject">${lp.name}</div>
    		<div id="descriptionProject">${lp.description}</div>
    		<div id="detailsProject"><a href="showProject?id=${lp.id}">Details</a></div>
    		<div>
    			<%-- Set the format of % : #,## --%>
    			<c:set var="percentage">
    				<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(lp.currentFund/lp.goal)*100}" />
    			</c:set>
    			<%-- Use different icon according to the % --%>
    			<% 
	    			String percentage = (String)pageContext.getAttribute("percentage");
					String p = percentage.replaceAll(",", "");
					double pdouble;
					int percent = 0;
					try {
						pdouble = Double.valueOf(p);
						percent = (int)pdouble;
						request.setAttribute("percent", percent);
					} catch (Exception e) {
				
					}
    			%>
    			<c:choose>
					<c:when test="${percent > '100'}">
						<div class="ribbon">
							<div class="ribbon-stitches-top"></div>
							<strong class="ribbon-content">
								<i class="icon-heart"></i>${percentage} %
							</strong>
							<div class="ribbon-stitches-bottom"></div>
						</div>
					</c:when>
				  	<c:when test="${percent > '50'}">
				  		<div class="ribbon">
							<div class="ribbon-stitches-top"></div>
							<strong class="ribbon-content">
								<i class="icon-star"></i>${percentage} %
							</strong>
							<div class="ribbon-stitches-bottom"></div>
						</div>
				  	</c:when>
				  	<c:otherwise>
				  		<div class="ribbon">
							<div class="ribbon-stitches-top"></div>
							<strong class="ribbon-content">
								<i class="icon-star-empty"></i> ${percentage} %
							</strong>
							<div class="ribbon-stitches-bottom"></div>
						</div>
				  	</c:otherwise>
				</c:choose>
  			</div>	    		
    	</div>
	</c:forEach>
</div>
<%-- Include Footer --%>
<%@ include file="footer.jsp" %>