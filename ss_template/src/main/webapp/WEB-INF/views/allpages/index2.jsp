<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="/WEB-INF/views/allpages/top2.jsp"%>
<%@ include file="/WEB-INF/views/allpages/left2.jsp"%>

	<!-- main container -->
    <div class="content">

        <!-- settings changer -->
       <!--  <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">默认皮肤</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/compiled/skins/dark.css">
                <span class="icon"></span><span class="text">深色皮肤</span>
            </a>
        </div> -->

      <!-- 4:3 aspect ratio -->
	<div class="container-fluid ">
	  <iframe id="mainIframe" name="mainIframe" src="${ctx }/index/welcome"
	          width="100%" frameborder="0" scrolling="no"></iframe>
	</div>

       
    </div>
