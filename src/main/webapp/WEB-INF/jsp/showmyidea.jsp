<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
        <!--<link href="${greetingCss}" rel="stylesheet" type="text/css"/>!-->
        <spring:url value="/res/fileupload/dir.png" var="folderPic" />



	<link rel="stylesheet" href="/res/libs/bootstrap/bootstrap.css" />

	<link rel="stylesheet" href="/res/libs/font-awesome-4.2.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/res/css/fonts.css" />
	<link rel="stylesheet" href="/res/css/main.css" />
	<link rel="stylesheet" href="/res/css/media.css" />

	<script src="/res/libs/jquery/jquery-3.1.1.min.js"></script>
	<script src="/res/libs/bootstrap/bootstrap.js"></script>
	<script src='/res/libs/bootstrap-tags/dist/js/bootstrap-tags.js'></script>
	<link rel="stylesheet" href="/res/libs/bootstrap-tags/dist/css/bootstrap-tags.css" />
	<link href="/res/libs/tagmanager/bootstrap-tagmanager.css" rel="stylesheet">
	<script src='/res/libs/tagmanager/bootstrap-tagmanager.js'></script>


	<script type="text/javascript" src="/res/libs/ckeditor/ckeditor.js"></script>



</head>
<body>
<div class="container">
		<div class="row">
			<div class="navbar navbar-default navbar-static-top">
				<div class="navbar-header">
					<i class="fa fa-home fa-5x" aria-hidden="true"></i>
					<a class="navbar-brand navbar-brand-center " href="#">
							 <img class="logo-pic" src="/res/images/logo_min.png" alt="RedEyesDevelopment" />
					</a>
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
						<span class="sr-only">Открыть навигацию</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse collapse-center" id="responsive-menu">
					<ul class="nav navbar-nav">
						<li><a href="#"><b>IRep</b><br/>Idea Repository</a></li>

					</ul>
				</div>
			</div>
			<div class="col-md-9">
			<c:if test="${(searchable!=null)}">
            <c:set var="ideaData" value="${searchable}" scope="page" />
                <div class="text-right">
                <a href="${RURI}"><spring:message code="label.back"/></a>|
                <c:if test="${ISMINE}">
                     <a href="/ideas/editmyidea/${ideaData.id}"><spring:message code="label.edit"/></a>
                </c:if>

                </div>
<div class="text-right">
                <spring:message code="label.ideaCreated"/> <fmt:formatDate type="both" value="${ideaData.posted}" />
                </div>
				    <div >
						<div class="form-group">
							<label class="text-primary">Нaзвание идеи:</label>
							<input type="text" class="form-control" placeholder="Нaзвание идеи" value="${ideaData.getName()}"  readonly="readonly">
							<!--<label class="text-primary">Описание идеи:</label>
							<input type="text" class="form-control" placeholder="Описание идеи" value="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod." readonly="readonly">
							<label class="text-primary">Иконка идеи:</label>
							<input type="text" class="form-control" placeholder="Иконка идеи" value="" readonly="readonly">!-->
							<label class="text-primary">Содержание идеи:</label>
							 <div class="ideaContentData"><spring:message code="label.ideaContent"/></br>
							<textarea class="form-control" cols="80" name="editor1" id="editor1" cols="48" rows="10" readonly="readonly"> ${ideaData.content.contentData}</textarea>
							<script>
                                CKEDITOR.replace('editor1', {
                                    filebrowserBrowseUrl: '/ckeditorN/libs/kcfinder/browse.php',
                                    filebrowserUploadUrl: '/ckeditorN/libs/kcfinder/upload.php'
                                    });
                            </script>
							<!--<label class="text-primary">Тэги идеи:</label>
        					<input type="text" name="tags" placeholder="Tеги идеи:" class="tm-input tm-input-info tm-input-lager form-control" value="tag1 tag2 tag3" readonly="readonly"/>

	        				<label class="text-primary">Состояние идеи:</label>
							<input type="text" class="form-control" placeholder="true/false" value="true" readonly="readonly">!-->
							</div>
						</div>
						<div class="item-footer">
                            <span class="item-autor"><b>${ideaData.author.username}</b> |</span>
                            <span class="item-like"><spring:message code="label.ideaLiked"/>: ${ideaData.liked}|</span>
                            <span class="item-dislike"><spring:message code="label.ideaDisliked"/>: ${ideaData.disliked} |</span>
                            <span class="item-view"><spring:message code="label.ideaWatchCount"/>: ${ideaData.viewedCount}|</span>
                            <span class="item-comment">Комментариев:25 </span>
                            <a href="#"><span class="item-up"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i><c:if test="${(!notshowlikes)}"><form:form method="POST" action="/ideas/likeidea/${ideaData.id}&like=true"><input type="submit" value="<spring:message code="label.ideaLiked"/>"></form:form></c:if>
    </span></a>/
                            <a href="#"><span class="item-down"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
    </span></a>
                       </div>
                       <div class="text-right top-buffer" >
                                           						    <spring:message code="label.ideaWatched"/> <fmt:formatDate type="both" value="${ideaData.viewed}" />
                                           						   </div>
                       					 <div class="text-right">
                                                           <a href="${RURI}"><spring:message code="label.back"/></a>|
                                                           <a href="/ideas/editmyidea/${ideaData.id}"><spring:message code="label.edit"/></a>
                                                           </div>

                       <spring:message code="label.ideaComments"/>
                       <%@include file="/WEB-INF/jsp/includes/messagebox.jsp" %>

                       <c:forEach items="${ideaData.comments}" var="com">
                           <span class="item-autor"><b>${com.author.username}</b> </span> posted at <fmt:formatDate type="both" value="${com.posted}" /> :
                           <div class="item-comment">${com.content}</div>
                       </c:forEach>

					</div>

						<!--<button type="submit" class="btn btn-primary"s>
							<i class="fa fa-sign-in"></i> Сохранить
						</button>!-->

				</div></c:if>
			</div>
			<div class="col-md-3">
			</div>

		</div>
			<div class="sidebar col-md-3">

			</div>
		</div>
	</div>


	<!--[if lt IE 9]>
	<script src="libs/html5shiv/es5-shim.min.js"></script>
	<script src="libs/html5shiv/html5shiv.min.js"></script>
	<script src="libs/html5shiv/html5shiv-printshiv.min.js"></script>
	<script src="libs/respond/respond.min.js"></script>
	<![endif]-->

</body>
<!--<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>!-->

</html>
