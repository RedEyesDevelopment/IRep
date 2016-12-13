<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
        <link href="${greetingCss}" rel="stylesheet" type="text/css"/>
        <spring:url value="/res/fileupload/dir.png" var="folderPic" />

</head>
<body>


<c:if test="${(searchable!=null)}">
<c:set var="ideaData" value="${searchable}" scope="page" />
<div class="ideaFullData"><spring:message code="label.ideaName"/> ${ideaData.getName()} </br>
    <div class="ideaAuthor"><spring:message code="label.ideaAuthor"/> ${ideaData.author.username}  </br>
        <div class="ideaPosted"><spring:message code="label.ideaCreated"/> ${ideaData.posted} </br>
            <div class="ideaRating"><spring:message code="label.ideaRatings"/></br>
            <spring:message code="label.ideaLiked"/> ${ideaData.liked}</br>
            <spring:message code="label.ideaDisliked"/> ${ideaData.disliked}</br>
            <spring:message code="label.ideaWatchCount"/> ${ideaData.viewedCount}</br>
            <spring:message code="label.ideaWatched"/> ${ideaData.viewed}</br>
                <div class="ideaContentData"><spring:message code="label.ideaContent"/></br>
                    ${ideaData.content.contentData}</br>
                </div>
            </div>
        </div>
    </div>
</div>
</c:if>
</body>
<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>

</html>