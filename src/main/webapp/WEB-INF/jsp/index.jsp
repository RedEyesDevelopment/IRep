<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
          			<div class="navbar navbar-default navbar-static-top ">
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
          					<ul class="nav navbar-default">
          						<li><b>IRep</b><br/>Idea Repository</li>
          					</ul>

          				</div>
          			</div>
          		</div>
          		</div>
          		<div >
          	</div>


          <div class="container">
              <div class="row">
                  <div class="col-sm-6 col-md-4 col-md-offset-4">
                      <h1 class="text-center login-title">Войдите в Банк Идей:</h1>
                      <div class="account-wall">
                          <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                              alt="">
                          <form class="form-signin" id="form-login">
          	                <input type="text" id="login" class="form-control" placeholder="Email" required autofocus>
          	                <input type="password" id="pass" class="form-control" placeholder="Password" required>
          	                <button class="btn btn-lg btn-primary btn-block" type="submit">
          	                    Вход</button>
          	                <label class="checkbox pull-left">
          	                    <input type="checkbox" value="remember-me">
          	                    Запомнить меня
          	                </label>
          	                <a href="#" class="pull-right need-help">Нужна помощь? </a><span class="clearfix"></span>
                          </form>
                      </div>
                      <!-- <a href="#" class="text-center new-account">Create an account </a> -->
                  </div>
              </div>
          </div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#form-login").submit(function() { //устанавливаем событие отправки для формы
            $.ajax({
                    type: "POST", //Метод отправки
                    url: "/login", //путь к контроллеру
		            dataType: "html",
                    data: "login="+$("#login").val()+"&password="+$("#login").val(),
                    success: function(data){// успешная отправка сообщения.
                         alert("success!"+data);
                         document.write(data);
                    },
		            error: function(xhr,ajaxOptions,thrownError){// мне нужна причина ошибки.
                     	alert("error входа!");
                     	alert(xhr.status);
                     	alert(thrownError);
                    }
            });
	    });
    });
</script>

    </body>
<!--<%@include file="/WEB-INF/jsp/includes/footer.jsp" %>!-->
</html>