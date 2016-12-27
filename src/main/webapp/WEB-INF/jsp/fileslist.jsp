<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link href="${greetingCss}" rel="stylesheet" type="text/css"/>
        <spring:url value="/res/fileupload/fileprev.png" var="prevPics" />
        <spring:url value="/res/fileupload/filenext.png" var="nextPics" />
        <link rel="stylesheet" href="/res/libs/bootstrap/bootstrap.css" />

        	<link rel="stylesheet" href="/res/libs/font-awesome-4.2.0/css/font-awesome.min.css" />

        	<link rel="stylesheet" href="/res/css/fonts.css" />
        	<link rel="stylesheet" href="/res/css/main.css" />
        	<link rel="stylesheet" href="/res/css/media.css" />

        <script src="/res/libs/jquery/jquery-3.1.1.min.js"></script>
        <script src="/res/libs/bootstrap/bootstrap.js"></script>
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
                        <form method="POST" class="form" action="/fileapi/uploadFile" enctype="multipart/form-data">
                        <div class="form-group">
                                Выберите файл для загрузки: <input type="file" class="form-control" name="file"><br />
                        Укажите публичность:<input type="checkbox"  name="publicity"  class="form-control" value="true" checked="checked" /><br />
                                        <input type="submit" class="btn btn-primary pull-right"value="Загрузить">
                                Нажмите для загрузки файла!
                        </div>
                            </form>

                        <c:if test="${!empty imageList}">
                            <table class="table table-hover data">
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
                                        <td><fmt:formatDate type="both" value="${imageData.posted}" /></td>
                                        <td>${imageData.publicity}</td>

                                        <td><img onclick="getSrc(this)" class="img-thumbnail" src="/dynamic/${imageData.imageName}" alt="${imageData.imageName}" width="200" height="150"/></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>

                        <c:if test="${ISPREVFILES}">
                            <a href="filelist&show=${PREVFILES}">
                            <!--<img src="${prevPics}" alt="Предыдущие" style="float:center;width:50px;height:50px;">!-->
                                                <i class="fa fa-3x fa-step-backward" aria-hidden="true"></i></a>
                            </a>
                        </c:if>

                        <c:if test="${ISNEXTFILES}">
                            <a href="filelist&show=${NEXTFILES}">
                                <!--<img src="${nextPics}" alt="Следующие" style="float:center;width:50px;height:50px;">!-->
                                                 <i class="fa fa-3x fa-step-forward" aria-hidden="true"></i></a>
                            </a>
                        </c:if>
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
<script>
    function getUrlParam(paramName){
        var reParam = new RegExp('(?:[\?&]|&amp;)' + paramName + '=([^&]+)','i');
        var match = window.location.search.match(reParam);

        return (match && match.length > 1) ? match[1] : '';
    }
    function getSrc(img){
        //alert(img.src);
        var funcNum = getUrlParam('CKEditorFuncNum');
                //var fileUrl = '/path/to/file.txt';
                 var fileUrl = img.src;
                window.opener.CKEDITOR.tools.callFunction(funcNum, fileUrl);
                window.close();
    }
   /* $('.img-icon').click(function(){
        var funcNum = getUrlParam('CKEditorFuncNum');
        //var fileUrl = '/path/to/file.txt';
         var fileUrl = $(this).find('img').src;
        window.opener.CKEDITOR.tools.callFunction(funcNum, fileUrl);
        window.close();
    });*/




</script>
    </body>
</html>

