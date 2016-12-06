<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta charset="utf-8">
        <title>Example :: Spring Application</title>
        <spring:url value="/res/simple.css" var="mainCss" />
        <spring:url value="/res/indexPage/index.gif" var="mainGif" />
        <link href="${mainCss}" rel="stylesheet" type="text/css"/>
    </head>

    <body>
<h3><spring:message code="label.ideas" /></h3>
<c:if test="${!empty ideaList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.ideaId" /></th>
			<th><spring:message code="label.ideaName" /></th>
			<th><spring:message code="label.ideaDescription" /></th>
            <th><spring:message code="label.ideaImage" /></th>
            <th><spring:message code="label.ideaTags" /></th>
			<th><spring:message code="label.ideaAuthor" /></th>
            <th><spring:message code="label.ideaCreated" /></th>
            <th><spring:message code="label.ideaLiked" /></th>
            <th><spring:message code="label.ideaDisliked" /></th>
            <th><spring:message code="label.ideaWatched" /></th>
            <th><spring:message code="label.ideaWatchCount" /></th>
			<th><spring:message code="label.ideaEnabled" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${ideaList}" var="ideadata">
			<tr>
				<td>${ideadata.getId()}</td>
				<td>${ideadata.getName()}</td>
				<td>${ideadata.getDescription()}</td>
				<td>${ideadata.getImage()}</td>
				<td>${ideadata.getTags()}</td>
				<td>${ideadata.getAuthor()}</td>
				<td>${ideadata.getPosted()}</td>
				<td>${ideadata.getLiked()}</td>
				<td>${ideadata.getDisliked()}</td>
				<td>${ideadata.getViewed()}</td>
				<td>${ideadata.getViewedCount()}</td>
				<td>${ideadata.isEnabled()}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
    </body>

<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>
</html>