<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
        <title>Login Idea Rip</title>
        <spring:url value="/res/simple.css" var="mainCss" />
        <spring:url value="/res/indexPage/index.gif" var="mainGif" />
        <link href="${mainCss}" rel="stylesheet" type="text/css"/>


           <link rel="stylesheet" href="/res/libs/bootstrap/bootstrap.css" />
        	    <link rel="stylesheet" href="/res/css/fonts.css" />
        	    <link rel="stylesheet" href="/res/css/main.css" />
        	    <link rel="stylesheet" href="/res/css/media.css" />
        	    <link rel="stylesheet" href="/res/css/login.css" />

        	<script src="/res/libs/jquery/jquery-3.1.1.min.js"></script>
        	<script src="/res/libs/bootstrap/bootstrap.js"></script>

        	<link rel="stylesheet" href="/res/libs/bootstrap/bootstrap.css" />

            	<link rel="stylesheet" href="/res/libs/font-awesome-4.2.0/css/font-awesome.min.css" />

            	<link rel="stylesheet" href="/res/libs/fancybox/jquery.fancybox.css" />
            	<link rel="stylesheet" href="/res/libs/owl-carousel/owl.carousel.css" />
            	<link rel="stylesheet" href="/res/libs/countdown/jquery.countdown.css" />
            	<link rel="stylesheet" href="/res/css/fonts.css" />
            	<link rel="stylesheet" href="/res/css/main.css" />
            	<link rel="stylesheet" href="/res/css/media.css" />

            	<script src="/res/libs/jquery/jquery-3.1.1.min.js"></script>
            	<script src="/res/libs/jquery/jquery.session.js"></script>
            	<script src="/res/libs/bootstrap/bootstrap.js"></script>



            	<link href="/res/libs/tagmanager/bootstrap-tagmanager.css" rel="stylesheet">
            	<script src='/res/libs/tagmanager/bootstrap-tagmanager.js'></script>


            	<script type="text/javascript" src="/res/libs/ckeditor/ckeditor.js"></script>


                <style>
                        /* Mother effin brute force CSS reset... */
                        /* Always include after any other CSS */
                    * {
                        resize: none;
                        border: none;
                        outline: none;
                        text-decoration: none;
                        padding: 0px;
                        margin: 0px;
                        list-style-type: none;
                    }
                </style>
    </head>
    <body>
         <div class="container">
         		<div class="row">
         			<div class="navbar navbar-default navbar-static-top">
         				<div class="navbar-header">
         					<i class="fa fa-home fa-5x" aria-hidden="true"></i>
         					<a class="navbar-brand navbar-brand-center" href="#">
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
         						<li><a href="/ideas/list"><b>IRep</b><br/>Банк Идей</a></li>
         					</ul>
         				</div>
         			</div>
         			<div class="col-md-9">
         				<ul class="nav nav-tabs">
         					<li class="active">
         						<a data-toggle="tab" href="#panel1">
         							Сортировка по:
         						</a>
         					</li>
         					<li >
         						<a data-toggle="tab" href="#panel2">
         							Фильтр по:
         						</a>
         					</li>
         					<li>
         						<a data-toggle="tab" href="#panel3">
         							Создать идею:
         						</a>
         					</li>
         				</ul>
         				<div class="tab-content">
         				  <div id="panel1" class="tab-pane fade in active">
         				   	<div class="btn-toolbar" role="toolbar" aria-label="...">
           						<div class="btn-group" role="group" aria-label="...">
           							<button type="button" id="btn-sort1" class="btn btn-default btn-num"><i  aria-hidden="true"></i> Дате создания</button>
         						    <button type="button" id="btn-sort2" class="btn btn-default btn-num"><i  aria-hidden="true"></i> Дате изменения</button>
         						    <button type="button" id="btn-sort3" class="btn btn-default btn-alpha"><i  aria-hidden="true"></i> Автор</button>
         						    <button type="button" id="btn-sort4" class="btn btn-default btn-alpha"><i  aria-hidden="true"></i>Название идеи</button>
         						    <button type="button" id="btn-sort5" class="btn btn-default btn-num"><i  aria-hidden="true"></i> Лайкам</button>
         						    <button type="button" id="btn-sort6" class="btn btn-default btn-num"><i  aria-hidden="true"></i> Дизлайкам</button>
           						</div>
         					</div>
         				  </div>
         				  <div id="panel2" class="tab-pane fade">
         				    <div class="btn-toolbar" role="toolbar" aria-label="...">
           						<div class="btn-group" role="group" aria-label="...">
           							<button type="button" class="btn btn-default" id="btn-filter1"><i aria-hidden="true"></i> Мои идеи</button>
         						    <!--<button type="button" class="btn btn-default btn-filter" id="btn-filter2"><i aria-hidden="true"></i> С комментариями</button>
         						    <button type="button" class="btn btn-default">С картинками</button>
         						    <button type="button" class="btn btn-default">С видео</button>
         						    <button type="button" class="btn btn-default">With Likes</button>
         						    <button type="button" class="btn btn-default">With Dislikes</button>!-->
           						</div>
         					</div>
         				  </div>
         				    <div id="panel3" class="tab-pane fade">

         				    <form:form method="post" action="/ideas/createideahandler" class="form" commandName="ideaData">
                                <div class="form-group">
                            			<form:label path="name" class="text-primary">
                            				<spring:message code="label.ideaName" />
                            			</form:label>
                            			<form:input path="name" type="text" class="form-control" placeholder="Нaзвание идеи" />
                            			<form:label path="description" class="text-primary">
                            				<spring:message code="label.ideaDescription" />
                            			</form:label>
                            			<form:input path="description" type="text" class="form-control" placeholder="Описание идеи"/>
                            			<!--<form:label path="image" class="text-primary">
                            				<spring:message code="label.ideaImage" />
                            			</form:label>
                            			<form:input path="image" type="text" class="form-control" placeholder="Иконка идеи"/>!-->
                            			<form:hidden path="image" />
                            			<form:label path="tags" class="text-primary">
                            				<spring:message code="label.ideaTags" />
                            			</form:label>
                            			<form:input path="tags" type="text" name="tags" placeholder="Введите тег:" class="tm-input tm-input-info tm-input-lager form-control"/>
                            		    <form:label path="content" class="text-primary">
                            				<spring:message code="label.ideaContent" />
                            			</form:label>
                            			<form:textarea path="content" cols="80" id="editor1" rows="10"/>
                            			<script>
                                            CKEDITOR.replace('editor1', {
                                                filebrowserBrowseUrl: '/fileapi/filelist&show=0',
                                                filebrowserUploadUrl: '/fileapi/fileupload'
                                                });
                                        </script>
                                        <!--<form:label path="enabled" class="text-primary">
                            				<spring:message code="label.ideaEnabled" />
                            			</form:label>

                            			<form:input path="enabled" type="text" class="form-control" placeholder="Состояние"/>!-->
                            			<form:hidden path="enabled" />
                            	</div>

                            			<input type="submit" id="btn-create" class="btn btn-primary pull-right"
                            				value="<spring:message code="label.ideaCreate"/>" />

                            </form:form>


         				    </div>
         				</div>
         			</div>
         			<div class="col-md-3">
         				<!--<div class="input-group">
         			      <input type="text" class="form-control" placeholder="Search for idea...">
         			      <span class="input-group-btn">
         			        <button class="btn btn-default" type="button">Go!</button>
         			      </span>
         			    </div>
         			    <div class="input-group">
         			      <input type="text" class="form-control" placeholder="Search for tag...">
         			      <span class="input-group-btn">
         			        <button class="btn btn-default" type="button">Go!</button>
         			      </span>
         			    </div>!-->
         			</div>

         		</div>
         		<div class="row">
         			<div class="grid col-md-9">

         			<c:forEach items="${ideaList}" var="ideadata">
                        <div class="thumbnail item">
                        <h1 class="item-name"><a href="showidea/${ideadata.id}">${ideadata.getName()}</a></h1>
                        <hr>
                        <p class="item-body">${ideadata.getDescription()}</p>
                        <hr>
                        <div class="item-footer">
                            <span class="item-autor"><b>${ideadata.getAuthor().getUsername()}</b> |</span>
                            <span class="item-like"><spring:message code="label.ideaLiked"/>:${ideadata.getLiked()} |</span>
                            <span class="item-dislike"><spring:message code="label.ideaDisliked"/>:${ideadata.getDisliked()} |</span>
                            <span class="item-view"><spring:message code="label.ideaWatchCount"/>:${ideadata.getViewedCount()} |</span>
                            <!--<span class="item-comment"><spring:message code="label.ideaCommentsCount"/>:25 </span>
                            <a href="#"><span class="item-up"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
     </span></a>/
                            <a href="#"><span class="item-down"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
     </span></a>!-->
                        </div>
                    </div>
                    </c:forEach>
         			</div>
         			<div class="sidebar col-md-3">
         				<div class="tags_pic">
         					<div id='tag-cloud'></div>

         				</div>
         				<div class="tags">

         				</div>
         			</div>
         		</div>
         	</div>


         	<!--[if lt IE 9]>
         	<script src="/res/libs/html5shiv/es5-shim.min.js"></script>
         	<script src="/res/libs/html5shiv/html5shiv.min.js"></script>
         	<script src="/res/libs/html5shiv/html5shiv-printshiv.min.js"></script>
         	<script src="/res/libs/respond/respond.min.js"></script>
         	<![endif]-->

         	<!--<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script><script type="text/javascript" src="https://rawgithub.com/dynamicguy/tagcloud/master/src/tagcloud.jquery.js"></script>
         	<script src="/res/libs/jquery-mousewheel/jquery.mousewheel.min.js"></script>
         	-->


         	<script src="/res/libs/jquery-svg3dtagcloud/jquery.svg3dtagcloud.min.js"></script>
         	<script>
         	        if ($.session.get("myFilter") === "1"){
                        $('#btn-filter1').find('i').addClass('fa fa-filter');
                    }

                    //alert($.session.get("myField")+$.session.get("myAsc"));
                    if ( ($.session.get("myField") === "1") ||
                         ($.session.get("myField") === "2") ||
                         ($.session.get("myField") === "5") ||
                         ($.session.get("myField") === "6") ) {
                            if ($.session.get("myAsc") === "asc"){
                                $('#btn-sort'+$.session.get("myField")).find('i').addClass('fa fa-sort-numeric-asc');
                            }else if ($.session.get("myAsc") === "desc"){
                                $('#btn-sort'+$.session.get("myField")).find('i').addClass('fa fa-sort-numeric-desc');
                            }

                    }
                    if ( ($.session.get("myField") === "3") ||
                         ($.session.get("myField") === "4") ) {
                            if ($.session.get("myAsc") === "asc"){
                                $('#btn-sort'+$.session.get("myField")).find('i').addClass('fa fa-sort-alpha-asc');
                            }else if ($.session.get("myAsc") === "desc"){
                                $('#btn-sort'+$.session.get("myField")).find('i').addClass('fa fa-sort-alpha-desc');
                            }

                    }

         	        $('#btn-sort1').click(function(){
         	             if ($.session.get("myField")==="1") {
                            if ($.session.get("myAsc") === "asc"){
                                 $.session.set("myAsc", "desc");
                                 location.href="/ideas/list&sort_field=posted&sort_asc=false&filter=&offset=0";
                             } else {
                                 $.session.set("myAsc", "asc");
                                 alert($.session.get("myField")+$.session.get("myAsc"));
                                 location.href="/ideas/list&sort_field=posted&sort_asc=true&filter=&offset=0";
                             }
                         } else {
                             $.session.set("myAsc", "asc");
                             location.href="/ideas/list&sort_field=posted&sort_asc=true&filter=&offset=0";
                         }
                         $.session.set("myField","1");
         	        });
         	         $('#btn-sort2').click(function(){
         	            if ($.session.get("myField")==="2") {
                            if ($.session.get("myAsc") === "asc"){
                                 $.session.set("myAsc", "desc");
                                 location.href="/ideas/list&sort_field=viewed&sort_asc=false&filter=&offset=0";
                             } else {
                                 $.session.set("myAsc", "asc");
                                 location.href="/ideas/list&sort_field=viewed&sort_asc=true&filter=&offset=0";
                             }
                        } else {
                            $.session.set("myAsc", "asc");
                            location.href="/ideas/list&sort_field=viewed&sort_asc=true&filter=&offset=0";
                        }
                         $.session.set("myField","2");
                    });
                    $('#btn-sort3').click(function(){
         	            if ($.session.get("myField")==="3") {
                            if ($.session.get("myAsc") === "asc"){
                                 $.session.set("myAsc", "desc");
                                 location.href="/ideas/list&sort_field=author&sort_asc=false&filter=&offset=0";
                             } else {
                                 $.session.set("myAsc", "asc");
                                 location.href="/ideas/list&sort_field=author&sort_asc=true&filter=&offset=0";
                             }
                        } else {
                            $.session.set("myAsc", "asc");
                            location.href="/ideas/list&sort_field=author&sort_asc=true&filter=&offset=0";
                        }
                         $.session.set("myField","3");
                         alert($.session.get("myField")+$.session.get("myAsc"));
                    });
                    $('#btn-sort4').click(function(){
                        if ($.session.get("myField")==="3") {
                            if ($.session.get("myAsc") === "asc"){
                                 $.session.set("myAsc", "desc");
                                 location.href="/ideas/list&sort_field=name&sort_asc=false&filter=&offset=0";
                             } else {
                                 $.session.set("myAsc", "asc");
                                 location.href="/ideas/list&sort_field=name&sort_asc=true&filter=&offset=0";
                             }
                        } else {
                            $.session.set("myAsc", "asc");
                            location.href="/ideas/list&sort_field=name&sort_asc=true&filter=&offset=0";
                        }
                         $.session.set("myField","3");
                    });


         	        $('.btn-alpha').click(function(){
                        $(this).find('i').toggleClass('fa-sort-alpha-asc fa-sort-alpha-desc');
                        location.href="/ideas/list&sort_field=posted&sort_asc=true&filter=&offset=0";
                    });
                    $('#btn-filter1').click(function(){
                        if ($.session.get("myFilter") === "1"){
                            $.session.set("myFilter","0");
                            location.href="/ideas/list&sort_field=posted&sort_asc=true&filter=&offset=0";
                        } else {
                            $.session.set("myFilter","1");
                            location.href="/ideas/list&sort_field=posted&sort_asc=true&filter=own&offset=0";
                        }
                    });
         	    	$( document ).ready( function() {
                    var entries = [];
         	    	$.ajax({
                    			type : "POST",
                    			contentType : "application/json",
                    			url : "/ajaxapi/tagscloud",
                    			data :{},
                    			dataType : 'json',
                    			timeout : 100000,
                    			success : function(data) {
                    				console.log("SUCCESS TAGS: ", data);
                                    for (var i = 0; i < data.length; i++) {
                                      entries[i] = { label: data[i].content, url: '/ideas/list&sort_field=posted&sort_asc=true&filter=tag'+data[i].id+'&offset=0', target: '_top' };
                                    }
                                     var settings = {

                                             	                entries: entries,
                                             	                width: 320,
                                             	                height: 240,
                                             	                radius: '85%',
                                             	                radiusMin: 75,
                                             	                bgDraw: true,
                                             	                bgColor: '#FFF',
                                             	                opacityOver: 1.00,
                                             	                opacityOut: 0.05,
                                             	                opacitySpeed: 6,
                                             	                fov: 800,
                                             	                speed: 1.3,
                                             	                fontFamily: 'Oswald, Arial, sans-serif',
                                             	                fontSize: '15',
                                             	                fontColor: '#337ab7',
                                             	                fontWeight: 'normal',//bold
                                             	                fontStyle: 'normal',//italic
                                             	                fontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
                                             	                fontToUpperCase: true

                                             	            };
                                             	            //var svg3DTagCloud = new SVG3DTagCloud( document.getElementById( 'holder'  ), settings );
                                             	            $( '#tag-cloud' ).svg3DTagCloud( settings );
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

         			} );

                    /*function searchViaAjax() {

                    		var search = {}
                    		search["orderingParameter"] = "posted";
                    		search["ascend"] = "true";
                            console.log("SUCCESS order: ");
                    		$.ajax({
                    			type : "POST",
                    			contentType : "application/json",
                    			url : "/ajaxapi/sortenabledideas",
                    			data : JSON.stringify(search),
                    			dataType : 'json',
                    			timeout : 100000,
                    			success : function(data) {
                    				console.log("SUCCESS order: ", data);

                    				//удалить все идеи и заново заполнить
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
                    	    console.log("enableSearchButton- ", flag);
                    		$("#btn-search").prop("disabled", flag);
                    	}

                    	function display(data) {
                    		var json = "<h4>Ajax Response</h4><pre>"
                    				+ JSON.stringify(data, null, 4) + "</pre>";
                    				 console.log("display- ", json);
                    		//$('#feedback').html(json);
                    	}
                    document.getElementById('btn-search').addEventListener('click', function(event) {
                        console.log("(#btn-search).onclick ");
                           // Disable the search button
                           enableSearchButton(false);

                           // Prevent the form from submitting via the browser.
                           event.preventDefault();

                           searchViaAjax();
                    })
                    $("#btn-search").onclick(function(event) {
                        console.log("(#btn-search).onclick ");
                        // Disble the search button
                        enableSearchButton(false);

                        // Prevent the form from submitting via the browser.
                        event.preventDefault();

                        searchViaAjax();
                    }
                    );*/

                    document.getElementById('btn-create').addEventListener('click', function(event) {
                                            console.log("(#btn-create).onclick ");
                                               // Disable the search button
                                               //enableSearchButton(false);

                                               // Prevent the form from submitting via the browser.
                                              // event.preventDefault();

                                               //searchViaAjax();
                                        })
         	</script>



         	<script>
         		function f_submit(){
         			var value1 = CKEDITOR.instances['editor1'].getData();
         			document.getElementById("editor").value=value1;
         			return false;
         		}
         	</script>
         	<script>jQuery(".tm-input").tagsManager();</script>



         	<!--
         	<script src="/res/libs/fancybox/jquery.fancybox.pack.js"></script>
                     	<script src="/res/libs/waypoints/waypoints-1.6.2.min.js"></script>
                     	<script src="/res/libs/scrollto/jquery.scrollTo.min.js"></script>
                     	<script src="/res/libs/owl-carousel/owl.carousel.min.js"></script>
                     	<script src="/res/libs/countdown/jquery.plugin.js"></script>
                     	<script src="/res/libs/countdown/jquery.countdown.min.js"></script>
                     	<script src="/res/libs/countdown/jquery.countdown-ru.js"></script>
                     	<script src="/res/libs/landing-nav/navigation.js"></script>
         	<script src="/res/js/common.js"></script>-->
         	<!-- Yandex.Metrika counter --><!-- /Yandex.Metrika counter -->
         	<!-- Google Analytics counter --><!-- /Google Analytics counter -->

    </body>
<!--<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>!-->
</html>