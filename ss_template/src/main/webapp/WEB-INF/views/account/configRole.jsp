<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/static/assets/css/lib/uniform.default.css" type="text/css" rel="stylesheet">
<link href="${ctx}/static/assets/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/assets/js/jquery.uniform.min.js"></script>
<script src="${ctx}/static/assets/js/select2.min.js"></script>
<link href="${ctx}/static/assets/css/lib/select2.css" type="text/css" rel="stylesheet">
<div id="pad-wrapper" >
  <div class="row form-wrapper">
		<form id="inputForm" action="${ctx}/admin/user/updateRole" method="post" class="form-horizontal">
		  <input type="hidden" name="id" value="${user.id}"/>
		  <input type="hidden" name="act_index" value="${act_index}"/>
			<fieldset>
				<legend><small>角色分配</small></legend>
				<div class="form-group">
					<label for="loginName" class="col-sm-2 control-label">登录名:</label>
					<div class="col-sm-6">
						<input type="text" id="loginName" value="${user.loginName}" class="form-control required" disabled="" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="roles" class="col-sm-2 control-label">角 色:</label>
					<div class="col-sm-6">
						<select name="roles" style="width:250px" multiple class="select2">
                                <option></option>
                             <c:forEach items="${roles}" var="role">
                                <option value="${role.roleCode }" ${role.selected }>${role.roleName }</option>
                             </c:forEach>
                        </select>
					</div>
				</div>
				
				
				
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-6">
				      <button type="submit" class="btn btn-primary">提交</button>
				      <button type="button" class="btn btn-default" onclick="history.back()">返回</button>
				    </div>
				</div>
			</fieldset>
		</form>
    
  </div>
</div>

<script>
$(document).ready(function() {
	//聚焦第一个输入框
	$("#loginName").focus();
	
    $(".select2").select2({
    });
});
</script>