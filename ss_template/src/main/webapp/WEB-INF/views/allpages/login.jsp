<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="">
<meta name="author" content="">

<title>登录-掌众传媒运营分析系统</title>
 <!-- bootstrap -->
    <link href="${ctx}/static/assets/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/static/assets/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/compiled/layout.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/compiled/elements.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/compiled/icons.css">

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/lib/font-awesome.css">
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="${ctx}/static/assets/css/compiled/signin.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>

<body>
    <!-- background switcher  *********************  -->
    <div class="bg-switch visible-desktop">
        <div class="bgs">
            <a href="#" data-img="7.jpg" class="bg">
                <img src="${ctx}/static/assets/img/bgs/7.jpg" />
            </a>
            <a href="#" data-img="8.jpg" class="bg">
                <img src="${ctx}/static/assets/img/bgs/8.jpg" />
            </a>
            <a href="#" data-img="9.jpg" class="bg">
                <img src="${ctx}/static/assets/img/bgs/9.jpg" />
            </a>
            <a href="#" data-img="10.jpg" class="bg">
                <img src="${ctx}/static/assets/img/bgs/10.jpg" />
            </a>
            <a href="#" data-img="11.jpg" class="bg">
                <img src="${ctx}/static/assets/img/bgs/11.jpg" />
            </a>
        </div>
    </div>

    <div class="login-wrapper">
        <h2>xxxx运营分析系统</h2>
        <br>
<form role="form" id="loginForm" action="${ctx}/login" method="post">

        <div class="box">
            <div class="content-wrap">
                <h6>登录</h6>
                <input name="username" value="${username}" class="form-control" type="text" placeholder="名称" >
                <input name="password" class="form-control" type="password" placeholder="密码">
                <c:if test="${isVerify}">
	                <div class="input-group">
		                <input name="verifyCode" class="form-control" type="text" placeholder="验证码"  aria-describedby="basic-addon2">
		                <span class="input-group-addon" id="basic-addon2">
		                  <img src="${ctx}/static/img.jsp" id="code_img" />
		                </span>
		                
		            </div>  
                </c:if>
                
                <!-- <div class="remember">
                    <input id="rememberMe" name="rememberMe" type="checkbox">
                    <label for="rememberMe">记住我</label>
                </div> -->
                <a class="btn-glow primary login" href="javascript:loginForm.submit();">登录</a>
            </div>
        </div>
</form>
        
        
        <%
				String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
				if (error != null) {
					if(error.equals("verifyCode_error")){
						%>
						<div class="box" style="background:none;border:none;height:30px;margin-top:5px;padding-top:5px;">
							<div class="alert alert-danger">
								 验证码错误！请重新输入 <br />
							</div>
						 </div>
						<%	
					}
					else{
						
					
			%>
			<div class="box" style="background:none;border:none;height:30px;margin-top:5px;padding-top:5px;">
				<div class="alert alert-danger">
<!-- 					<button type="button" aria-hidden="true" class="close" -->
<!-- 						data-dismiss="alert">×</button> -->
					登录失败!用户名或密码错误. <br />
				</div>
			 </div>
			<%
			          }
				}
			%>
    </div>
    
    <!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
<%--     <script src="${ctx}/static/assets/js/theme.js"></script> --%>

    <!-- pre load bg imgs -->
    <script type="text/javascript">
        $(function () {
            // bg switcher
            $("html").css("background-image", "url('${ctx}/static/assets/img/bgs/9.jpg')");
            var $btns = $(".bg-switch .bg");
            
            $btns.click(function (e) {
                e.preventDefault();
                $btns.removeClass("active");
                $(this).addClass("active");
                var bg = $(this).data("img");
                
                $("html").css("background-image", "url('${ctx}/static/assets/img/bgs/" + bg + "')");
            });

            
                   
           $('#code_img').click(function () {//生成验证码  
           		$(this).hide().attr('src', '${ctx}/static/img.jsp?' + Math.floor(Math.random()*100) ).fadeIn(); 
           	})      
             
            
        });
        
        
    </script>



</body>
</html>
