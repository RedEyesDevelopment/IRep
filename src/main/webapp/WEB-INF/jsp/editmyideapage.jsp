<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
                <spring:url value="/ideas/editideahandler" var="userActionUrl" />

                    <form:form class="form" method="post" modelAttribute="ideaAttrib" action="${userActionUrl}">
                        <form:label path="name" class="text-primary">
                            <spring:message code="label.ideaName" />
                        </form:label>
                        <spring:bind path="name">
                                <form:input path="name" type="text" class="form-control" id="name" placeholder="" />
                        </spring:bind>
                        <form:label path="description" class="text-primary">
                            <spring:message code="label.ideaDescription" />
                        </form:label>
                        <spring:bind path="description">
                                <form:input path="description" type="text" class="form-control" id="description" placeholder="" />
                        </spring:bind>
                        <form:label path="image" class="text-primary">
                            <spring:message code="label.ideaImage" />
                        </form:label>
                        <spring:bind path="image">
                                <form:input path="image" type="text" class="form-control" id="image" placeholder="" />
                        </spring:bind>
                        <form:label path="tags" class="text-primary">
                            <spring:message code="label.ideaTags" />
                        </form:label>
                        <spring:bind path="tags">
                                        <form:input path="tags" type="text" class="form-control" id="tags" placeholder="" />
                                </spring:bind>
                        <form:label path="content" class="text-primary">
                            <spring:message code="label.ideaContent" />
                        </form:label>
                        <spring:bind path="content">
                        <form:textarea path="content" rows="10" class="form-control" id="editor1" placeholder="" />
                        </spring:bind>
                        <script>
                            CKEDITOR.replace('editor1', {
                                filebrowserBrowseUrl: '/fileapi/filelist&show=0',
                                filebrowserUploadUrl: '/fileapi/filelist&show=0'
                                });
                        </script>
                        <form:label path="enabled" class="text-primary">
                            <spring:message code="label.ideaEnabled" />
                        </form:label>
                        <spring:bind path="enabled">
                                <form:select path="enabled" class="form-control">
                                    <form:options items="${enablind}" />
                                </form:select>
                        </spring:bind>

                        <button type="submit" class="btn btn-primary pull-right">Сохранить</button>

                    </form:form>

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
</html>