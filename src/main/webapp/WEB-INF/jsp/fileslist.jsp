<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link href="${greetingCss}" rel="stylesheet" type="text/css"/>
        <spring:url value="/res/fileupload/dir.png" var="folderPic" />
        <spring:url value="/res/fileupload/fileprev.png" var="prevPics" />
        <spring:url value="/res/fileupload/filenext.png" var="nextPics" />

    </head>
    <body>
<img src="${folderPic}" alt="MY HUGE DIIIICK!" style="float:center;width:20px;height:20px;"><spring:message code="label.images" />   <a href="/ideas/list"> Вернуться</a>
<c:if test="${!empty imageList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.imageName" /></th>
			<th><spring:message code="label.imageAuthor" /></th>
			<th><spring:message code="label.imagePosted" /></th>
			<th><spring:message code="label.imagePublicity" /></th>
			<th><spring:message code="label.imageContent" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${imageList}" var="imageData">
			<tr>
				<td>${imageData.imageName}</a></td>
				<td>${imageData.imageAuthorId}</td>
				<td>${imageData.posted}</td>
				<td>${imageData.publicity}</td>

				<td><img src="/dynamic/${imageData.imageName}" alt="${imageData.imageName}" height="100"/></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${!NoLessFiles}">
    <a href="previouslist"><img src="${prevPics}" alt="MY HUGE DIIIICK!" style="float:center;width:50px;height:50px;"></a>
</c:if>

<c:if test="${!NoMoreFiles}">
    <a href="nextlist"><img src="${nextPics}" alt="MY HUGE DIIIICK!" style="float:center;width:50px;height:50px;"></a>
</c:if>

    </body>
</html>

