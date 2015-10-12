<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="pad-wrapper">
  <div class="row form-wrapper">
  
	<form id="inputForm" action="${ctx}/admin/user/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
		<input type="hidden" name="act_index" value="${act_index}"/>
		<fieldset>
			<legend><small>修改用户</small></legend>
			<div class="form-group">
				<label class="col-sm-2 control-label">登录名:</label>
				<div class="col-sm-6">
					<input type="text" value="${user.loginName}" class="form-control input-large" disabled="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">用户名:</label>
				<div class="col-sm-6">
					<input type="text" id="name" name="name" value="${user.name}" class="form-control input-large required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="plainPassword" class="col-sm-2 control-label">密码:</label>
				<div class="col-sm-6">
					<input type="password" id="plainPassword" name="plainPassword" class="form-control input-large" placeholder="...Leave it blank if no change"/>
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-sm-2 control-label">确认密码:</label>
				<div class="col-sm-6">
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control input-large" equalTo="#plainPassword" />
				</div>
			</div>
<%-- 			<div class="form-group">
				<label class="col-sm-2 control-label">注册日期:</label>
				<div class="col-sm-6">
					<span class="help-inline" style="padding:5px 0px"><fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /></span>
				</div>
			</div>
 --%>			<div class="form-group">
			  <div class="col-sm-offset-2 col-sm-6">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			  </div>
			</div>
		</fieldset>
	</form>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</div>
</div>
