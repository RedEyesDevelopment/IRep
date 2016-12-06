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
<h3><spring:message code="label.places" /></h3>
<c:if test="${!empty ideaList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.placeNameId" /></th>
			<th><spring:message code="label.description" /></th>
			<th><spring:message code="label.address" /></th>
			<th><spring:message code="label.telephone" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${ideaList}" var="ideadata">
			<tr>
				<td>${ideadata.toString()}</td>

			</tr>
		</c:forEach>
	</table>
</c:if>
    </body>

<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>
</html>