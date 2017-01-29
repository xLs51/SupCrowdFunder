<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- Include Header --%>
<%@ include file="header.jsp" %>
<div class="page-header">
 	<h1>Welcome to SupCrowdFunder</h1>
 	<%-- If listProject is empty --%>
 	<c:if test="${empty listProject}">There is no project available</c:if>
 	<%-- List all the project --%>
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
					<c:when test="${percent >= 100}">
						<div class="ribbon">
							<div class="ribbon-stitches-top"></div>
							<strong class="ribbon-content">
								<i class="icon-heart"></i>${percentage} %
							</strong>
							<div class="ribbon-stitches-bottom"></div>
						</div>
					</c:when>
				  	<c:when test="${percent >= 50 && percent < 100}">
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