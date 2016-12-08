<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>Example :: Spring Application</title>
    <spring:url value="/res/simple.css" var="mainCss"/>
    <spring:url value="/res/indexPage/index.gif" var="mainGif"/>
    <link href="${mainCss}" rel="stylesheet" type="text/css"/>
    <spring:url value="https://code.jquery.com/jquery-1.10.2.min.js" var="jqueryJs"/>
    <script src="${jqueryJs}"></script>
</head>

<body>
<div id="feedback">
    <h3>
        <spring:message code="label.ideas"/>
    </h3>
    <c:if test="${!empty ideaList}">
        <table class="data">
            <tr>
                <th>
                    <spring:message code="label.ideaId"/>
                </th>
                <th>
                    <spring:message code="label.ideaName"/>
                </th>
                <th>
                    <spring:message code="label.ideaDescription"/>
                </th>
                <th>
                    <spring:message code="label.ideaImage"/>
                </th>
                <th>
                    <spring:message code="label.ideaTags"/>
                </th>
                <th>
                    <spring:message code="label.ideaAuthor"/>
                </th>
                <th>
                    <spring:message code="label.ideaCreated"/>
                </th>
                <th>
                    <spring:message code="label.ideaLiked"/>
                </th>
                <th>
                    <spring:message code="label.ideaDisliked"/>
                </th>
                <th>
                    <spring:message code="label.ideaWatched"/>
                </th>
                <th>
                    <spring:message code="label.ideaWatchCount"/>
                </th>
                <th>
                    <spring:message code="label.ideaEnabled"/>
                </th>
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

</div>

<div class="container" style="min-height: 500px">

    <div class="starter-template">
        <h1>Search Form</h1>
        <br>
        <form class="form-horizontal" id="search-form">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">orderingParameter</label>
                <div class="col-sm-10">
                    <input type=text class="form-control" id="orderingParameter">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">ascend</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="ascend">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="bth-search"
                            class="btn btn-primary btn-lg">Search
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>

<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>

<script>	jQuery(document).ready(function($) {

        		$("#search-form").submit(function(event) {

        			// Disble the search button
        			enableSearchButton(false);

        			// Prevent the form from submitting via the browser.
        			event.preventDefault();

        			searchViaAjax();

        		});

        	});

        	function searchViaAjax() {

        		var search = {}
        		search["orderingParameter"] = $("#orderingParameter").val();
        		search["ascend"] = $("#ascend").val();

        		$.ajax({
        			type : "POST",
        			contentType : "application/json",
        			url : "${home}ajaxapi/sortenabledideas",
        			data : JSON.stringify(search),
        			dataType : 'json',
        			timeout : 100000,
        			success : function(data) {
        				console.log("SUCCESS: ", data);
        				display(data);
        			},
        			error : function(e) {
        				console.log("ERROR: ", e);
        				display(e);
        			},
        			done : function(e) {
        				console.log("DONE");
        				enableSearchButton(true);
        			}
        		});

        	}

        	function enableSearchButton(flag) {
        		$("#btn-search").prop("disabled", flag);
        	}

        	function display(data) {
        		var json = "<h4>Ajax Response</h4><pre>"
        				+ JSON.stringify(data, null, 4) + "</pre>";
        		$('#feedback').html(json);
        	}
</script>


</html>