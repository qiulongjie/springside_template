<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>xxxx运营分析系统</title>
<link type="image/x-icon" href="${ctx}/static/images/favicon.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet" />

   <!-- bootstrap -->
<link href="${ctx}/static/assets/css/bootstrap/bootstrap.css" rel="stylesheet" />
<%-- <link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" /> --%>
<link href="${ctx}/static/assets/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

<!-- libraries -->
<link href="${ctx}/static/assets/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/assets/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/compiled/layout.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/compiled/elements.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/compiled/icons.css">

<link href="${ctx}/static/assets/css/lib/jquery.dataTables.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/assets/css/lib/jquery.dataTables_themeroller.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/assets/css/lib/bootstrap.datepicker.css" type="text/css" rel="stylesheet">
    
<!-- this page specific styles -->
<link rel="stylesheet" href="${ctx}/static/assets/css/compiled/datatables.css" type="text/css" media="screen" />

<!-- this page specific styles -->
<link rel="stylesheet" href="${ctx}/static/assets/css/compiled/index.css" type="text/css" media="screen" />
<!-- open sans font -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<!-- lato font -->
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<sitemesh:head/>
<!-- scripts -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/static/assets/js/jquery-ui-1.10.2.custom.min.js"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/js/bootstrap.datepicker2.js"></script>
<script src="${ctx}/static/assets/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/assets/js/theme.js"></script>
<script src="${ctx}/static/assets/js/zzba.left.js"></script>

</head>

<body>

<shiro:user>
<%@ include file="/WEB-INF/views/allpages/top.jsp"%>
   
<%@ include file="/WEB-INF/views/allpages/left.jsp"%>
</shiro:user>

<shiro:user>
	<!-- main container -->
    <div class="content">

        <!-- settings changer -->
       <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">默认皮肤</span>
            </a>
            <a href="#" class="skin second_nav" data-file="${ctx}/static/assets/css/compiled/skins/dark.css">
                <span class="icon"></span><span class="text">深色皮肤</span>
            </a>
        </div>

</shiro:user>
   <sitemesh:body/>
   
<shiro:user>
    </div>
</shiro:user>

<script type="text/javascript">
   $(function () {
      
       
   });
</script>
</body>
</html>