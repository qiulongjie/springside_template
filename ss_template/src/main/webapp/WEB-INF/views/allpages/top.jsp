<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx }/index/welcome">
               <img src="${ctx}/static/assets/img/logo1.png">
            </a>
        </div>
        <ul class="nav navbar-nav pull-right hidden-xs">
            
            <li class="dropdown">
                <a href="#" class="dropdown-toggle hidden-xs hidden-sm" data-toggle="dropdown">
                  <shiro:principal property="name"></shiro:principal>
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${ctx}/profile">修改密码</a></li>
                    <li><a href="${ctx}/logout">退&nbsp;&nbsp;出</a></li>
                </ul>
            </li>
           
           <%--  <li class="settings hidden-xs hidden-sm">
                <a href="${ctx}/logout" role="button">
                    <i class="icon-share-alt"></i>
                </a>
            </li> --%>
        </ul>
    </header>
    <!-- end navbar -->