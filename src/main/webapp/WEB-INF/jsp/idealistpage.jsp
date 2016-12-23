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
        <spring:url value="/res/js/tagsListAjaxQuery.js" var="tagsListAjaxQueryJS"/>
        <script src="${tagsListAjaxQueryJS}"></script>

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
    <!--<div>MY DIV </div>
    <div class="contentblock">
        <h1>Example - Spring Application</h1>
        <h2><%@include file="/WEB-INF/jsp/includes/greeting.jsp" %><h2>
        <a href="hello.html">Hillel IT School</a>
        <br>
        <img src="${mainGif}" alt="MY HUGE DIIIICK!" style="float:center;width:350px;height:350px;">
        </div>
          !-->

         <div class="container">
         		<div class="row">
         			<div class="navbar navbar-static-top">
         				<div class="navbar-header">
         					<i class="fa fa-home fa-5x" aria-hidden="true"></i>
         					<a class="navbar-brand navbar-brand-center" href="#">
         							<!-- <img class="logo-pic" src="images/logo_min.png" alt="RedEyesDevelopment" />  -->
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
         						<!-- <li class="dropdown">
         							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Пункт2 <b class="caret"></b></a>
         							<ul class="dropdown-menu">
         								<li><a href="#">Пункт2-1</a></li>
         								<li><a href="#">Пункт2-2</a></li>
         								<li><a href="#">Пункт2-3</a></li>
         								<li class="divider"></li>
         								<li><a href="#">Пункт2-4</a></li>
         							</ul>
         						</li>
         						<li><a href="#">Пункт3</a></li>
         						<li><a href="#">Пункт4</a></li>	 -->
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
         					<li>
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
           							<button type="button" class="btn btn-default">Дате создания</button>
         						    <button type="button" class="btn btn-default">Дате изменения</button>
         						    <button type="button" class="btn btn-default">Автор</button>
         						    <button type="button" class="btn btn-default">Название идеи</button>
         						    <button type="button" class="btn btn-default">Likes</button>
         						    <button type="button" class="btn btn-default">Dislikes</button>
           						</div>
         					</div>
         				  </div>
         				  <div id="panel2" class="tab-pane fade">
         				    <div class="btn-toolbar" role="toolbar" aria-label="...">
           						<div class="btn-group" role="group" aria-label="...">
           							<button type="button" class="btn btn-default">Мои идеи</button>
         						    <button type="button" class="btn btn-default">С комментариями</button>
         						    <button type="button" class="btn btn-default">С картинками</button>
         						    <button type="button" class="btn btn-default">С видео</button>
         						    <button type="button" class="btn btn-default">With Likes</button>
         						    <button type="button" class="btn btn-default">With Dislikes</button>
           						</div>
         					</div>
         				  </div>
         				    <div id="panel3" class="tab-pane fade">
         				    <form action="" class="form">
         						<div class="form-group">
         							<label class="text-primary">Нзвание идеи:</label>
         							<input type="text" class="form-control" placeholder="Name" value="">
         							<label class="text-primary">Тэги идеи:</label>
                 					<input type="text" name="tags" placeholder="Введите тег:" class="tm-input tm-input-info tm-input-lager form-control"/>
         	        				<div>
         	        				<label class="text-primary">Содержание идеи:</label>
         							<textarea cols="80" name="editor1" id="editor1" cols="48" rows="10"></textarea>
         							</div>
         							<script>
         							CKEDITOR.replace('editor1', {
         								filebrowserBrowseUrl: '/ckeditorN/libs/kcfinder/browse.php',
         								filebrowserUploadUrl: '/ckeditorN/libs/kcfinder/upload.php'
         								});
         							</script>
         						</div>
         						<button type="submit" class="btn btn-primary">
         							<i class="fa fa-sign-in"></i> ок
         						</button>
         					</form>
         					<!-- <form method="POST" action="index.php" onsubmit="return f_submit();">
         						<p class="text-primary">Тэги для идеи:</p>
                 				<input type="text" name="tags" placeholder="Введите тег:" class="tm-input tm-input-info tm-input-lager"/>

                 				<div id="tagsIdea" class="tag-list"></div>
                 				<div>
         						<textarea cols="80" name="editor1" id="editor1" cols="48" rows="10"></textarea>
         						</div>
         						<script>
         						CKEDITOR.replace('editor1', {
         							filebrowserBrowseUrl: '/ckeditorN/libs/kcfinder/browse.php',
         							filebrowserUploadUrl: '/ckeditorN/libs/kcfinder/upload.php'
         							});
         						</script>
         						<br><input type="submit" value="Постить" name="B1"  >
         						<textarea cols="80" name="editor" id="editor" cols="48" rows="10"></textarea>
         					</form>	 -->
         				    </div>
         				</div>
         			</div>
         			<div class="col-md-3">
         				<div class="input-group">
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
         			    </div>
         			</div>

         		</div>
         		<div class="row">
         			<div class="grid col-md-9">

                    <div id="result">

         			<c:forEach items="${ideaList}" var="ideadata">
                                        <div class="thumbnail item">
                                                 					<h1 class="item-name"><a href="showidea/${ideadata.id}">${ideadata.getName()}</a></h1>
                                                 					<hr>
                                                 					<p class="item-body">${ideadata.getDescription()}</p>
                                                 					<hr>
                                                 					<div class="item-footer">
                                                 						<span class="item-autor"><b>${ideadata.getAuthor().getUsername()}</b> |</span>
                                                 						<span class="item-like">Like:${ideadata.getLiked()} |</span>
                                                 						<span class="item-dislike">Dislike:${ideadata.getDisliked()} |</span>
                                                 						<span class="item-view">Просмотров:${ideadata.getViewedCount()} |</span>
                                                 						<span class="item-comment">Комментариев:25 </span>
                                                 						<a href="#"><span class="item-up"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                                                 </span></a>/
                                                 						<a href="#"><span class="item-down"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
                                                 </span></a>
                                                 					</div>
                                                 				</div>
                    </c:forEach>



         			</div>
         			<div class="sidebar col-md-3">
         				<div class="tags_pic">
         					<div id='tag-cloud'></div>
         					<!-- <div id="tagcloud">
         				        <ul>
         				            <li><a href="#">dazzlingly</a></li>
         				            <li><a href="#">natatory</a></li>
         				            <li><a href="#">automa</a></li>
         				            <li><a href="#">vinologist</a></li>
         				            <li><a href="#">castrate</a></li>
         				            <li><a href="#">airfoil</a></li>
         				            <li><a href="#">unpaintedly</a></li>
         				            <li><a href="#">caffetannic</a></li>
         				            <li><a href="#">fissirostrate</a></li>
         				            <li><a href="#">gingivectomy</a></li>
         				            <li><a href="#">pinprick</a></li>
         				            <li><a href="#">forecount</a></li>
         				            <li><a href="#">anorthophyre</a></li>
         				            <li><a href="#">tram</a></li>
         				            <li><a href="#">democratian</a></li>
         				            <li><a href="#">ungovernedness</a></li>
         				            <li><a href="#">anaesthesia</a></li>
         				            <li><a href="#">Eutychian</a></li>
         				            <li><a href="#">insalutary</a></li>
         				            <li><a href="#">amphilogism</a></li>
         				            <li><a href="#">reshunt</a></li>
         				            <li><a href="#">wende</a></li>
         				            <li><a href="#">ope</a></li>
         				            <li><a href="#">Bomarea</a></li>
         				            <li><a href="#">inadequative</a></li>
         				            <li><a href="#">ovalish</a></li>
         				            <li><a href="#">rhizopod</a></li>
         				            <li><a href="#">stethoscopist</a></li>
         				            <li><a href="#">riverman</a></li>
         				            <li><a href="#">underbuoy</a></li>
         				            <li><a href="#">Pindarist</a></li>
         				            <li><a href="#">bacteriodiagnosis</a></li>
         				            <li><a href="#">stonecutter</a></li>
         				            <li><a href="#">unlanterned</a></li>
         				            <li><a href="#">leawill</a></li>
         				            <li><a href="#">hooky</a></li>
         				            <li><a href="#">wisewoman</a></li>
         				            <li><a href="#">gerundively</a></li>
         				            <li><a href="#">minny</a></li>
         				            <li><a href="#">aminosuccinamic</a></li>
         				            <li><a href="#">theaterless</a></li>
         				            <li><a href="#">heal</a></li>
         				            <li><a href="#">synchronically</a></li>
         				            <li><a href="#">devilman</a></li>
         				            <li><a href="#">unbilleted</a></li>
         				            <li><a href="#">galactotherapy</a></li>
         				            <li><a href="#">ninepenny</a></li>
         				            <li><a href="#">Andesic</a></li>
         				            <li><a href="#">trainbolt</a></li>
         				            <li><a href="#">Daza</a></li>
         				            <li><a href="#">porterage</a></li>
         				            <li><a href="#">innoxiousness</a></li>
         				            <li><a href="#">sextuplex</a></li>
         				            <li><a href="#">pharmacologic</a></li>
         				            <li><a href="#">overlip</a></li>
         				            <li><a href="#">Paiconeca</a></li>
         				            <li><a href="#">grooving</a></li>
         				            <li><a href="#">sublinear</a></li>
         				        </ul>
         				    </div> -->
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

         	<!--<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script><script type="text/javascript" src="https://rawgithub.com/dynamicguy/tagcloud/master/src/tagcloud.jquery.js"></script>-->

         	 <script src="/res/libs/jquery-mousewheel/jquery.mousewheel.min.js"></script>
         	<script src="/res/libs/jquery-svg3dtagcloud/jquery.svg3dtagcloud.min.js"></script>
         	<script>
         	    	$( document ).ready( function() {
         	            var entries = [
         	                { label: 'Back to top', url: '#', target: '_top' },
         	                { label: 'Bootstrap', url: '#', target: '_top' },
         	                { label: 'Carousel', url: '#', target: '_top' },
         	                { label: 'Countdown', url: '#', target: '_top' },
         	                { label: 'Dropdown Menu', url: '#', target: '_top' },
         	                { label: 'CodePen', url: '#', target: '_top' },
         	                { label: 'three.js', url: '#', target: '_top' },
         	                { label: 'Form Validation', url: '#', target: '_top' },
         	                { label: 'JS Compress', url: '#', target: '_top' },
         	                { label: 'TinyPNG', url: '#', target: '_top' },
         	                { label: 'Can I Use', url: '#', target: '_top' },
         	                { label: 'URL shortener', url: '#', target: '_top' },
         	                { label: 'Grid Layout', url: '#', target: '_top' },
         	                { label: 'Twitter', url: '#', target: '_top' },
         	                { label: 'deviantART', url: '#', target: '_top' },
         	                { label: 'Gulp', url: '#', target: '_top' },
         	                { label: 'Browsersync', url: '#', target: '_top' },
         	                { label: 'GitHub', url: 'https://github.com/', target: '_top' },
         	                { label: 'Shadertoy', url: '#', target: '_top' },
         	                { label: 'Tree View', url: 'http://www.jqueryscript.net/tags.php?/tree%20view/', target: '_top' },
         	                { label: 'jsPerf', url: '#', target: '_top' },
         	                { label: 'Foundation', url: '#', target: '_top' },
         	                { label: 'CreateJS', url: '#', target: '_top' },
         	                { label: 'Velocity.js', url: 'http://julian.com/research/velocity/', target: '_top' },
         	                { label: 'TweenLite', url: '#', target: '_top' },
         	                { label: 'jQuery', url: 'https://jquery.com/', target: '_top' },
         	                { label: 'Notification', url: 'http://www.jqueryscript.net/tags.php?/Notification/', target: '_top' },
         	                { label: 'Parallax', url: 'http://www.jqueryscript.net/tags.php?/parallax/', target: '_top' }
         	            ];

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
         	                speed: 2,
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
         			} );
         	</script>


         	 <!-- <script src="libs/tagcloud/tagcloud.jquery.js"></script>
         		<script type="text/javascript">
         		    var settings = {
         		    //height of sphere container
         		    height: 400,
         		    //width of sphere container
         		    width: 400,
         		    //radius of sphere
         		    radius: 150,
         		    //rotation speed
         		    speed: 3,
         		    //sphere rotations slower
         		    slower: 0.9,
         		    //delay between update position
         		    timer: 5,
         		    //dependence of a font size on axis Z
         		    fontMultiplier: 15,
         		    //tag css stylies on mouse over
         		    hoverStyle: {
         		        border: 'none',
         		        color: '#0b2e6f'
         		    },
         		    //tag css stylies on mouse out
         		    mouseOutStyle: {
         		        border: '',
         		        color: ''
         		    }
         		    };

         		    $(document).ready(function(){
         		        $('#tagcloud').tagoSphere(settings);
         		    });
         		</script> -->
         	<script>
         		function f_submit(){
         			var value1 = CKEDITOR.instances['editor1'].getData();
         			document.getElementById("editor").value=value1;
         			return false;
         		}
         	</script>
         	<script>jQuery(".tm-input").tagsManager();</script>


         	 <script src="/res/libs/fancybox/jquery.fancybox.pack.js"></script>
         	<script src="/res/libs/waypoints/waypoints-1.6.2.min.js"></script>
         	<script src="/res/libs/scrollto/jquery.scrollTo.min.js"></script>
         	<script src="/res/libs/owl-carousel/owl.carousel.min.js"></script>
         	<script src="/res/libs/countdown/jquery.plugin.js"></script>
         	<script src="/res/libs/countdown/jquery.countdown.min.js"></script>
         	<script src="/res/libs/countdown/jquery.countdown-ru.js"></script>
         	<script src="/res/libs/landing-nav/navigation.js"></script>
         	<script src="/res/js/common.js"></script>
         	<!-- Yandex.Metrika counter --><!-- /Yandex.Metrika counter -->
         	<!-- Google Analytics counter --><!-- /Google Analytics counter -->

    </body>
<!--<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>!-->
</html>