<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/static/assets/css/lib/uniform.default.css" type="text/css" rel="stylesheet">
<link href="${ctx}/static/assets/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/assets/js/jquery.uniform.min.js"></script>
<%-- <script src="${ctx}/static/assets/js/select2.min.js"></script> --%>
<%-- <link href="${ctx}/static/assets/css/lib/select2.css" type="text/css" rel="stylesheet"> --%>
<div id="pad-wrapper" >
  <div class="row form-wrapper">
		<form id="inputForm" action="${ctx}/register/addUser" method="post" class="form-horizontal">
		<input type="hidden" name="act_index" value="${act_index}"/>
			<fieldset>
				<legend><small>添加用户</small></legend>
				<div class="form-group">
					<label for="loginName" class="col-sm-2 control-label">登录名:</label>
					<div class="col-sm-6">
						<input type="text" id="loginName" name="loginName" class="form-control required" minlength="3"/>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">用户名:</label>
					<div class="col-sm-6">
						<input type="text" id="name" name="name" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label for="plainPassword" class="col-sm-2 control-label">密码:</label>
					<div class="col-sm-6">
						<input type="password" id="plainPassword" name="plainPassword" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="col-sm-2 control-label">确认密码:</label>
					<div class="col-sm-6">
						<input type="password" id="confirmPassword" name="confirmPassword" class="form-control required" equalTo="#plainPassword"/>
					</div>
				</div>
				<!-- 
				<div class="form-group">
					<label for="birthdate" class="col-sm-2 control-label">datetime:</label>
					<div class="col-sm-6">
					    <input type="text" value="03/29/2013" class="form-control input-datepicker" />
					</div>
				</div>
				<div class="form-group">
                    <label class="col-sm-2 control-label">Checkboxes:</label>
                    <div class="col-md-8">
	                    <label class="checkbox-inline">
	                      <input type="checkbox" id="inlineCheckbox1" value="option1"> Option 1
	                    </label>
	                    <label class="checkbox-inline">
	                      <input type="checkbox" id="inlineCheckbox2" value="option1"> Option 2
	                    </label>
	                    <label class="checkbox-inline">
	                      <input type="checkbox" id="inlineCheckbox3" value="option1"> Option 3
	                    </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Radiobuttons:</label>
                    <div class="col-md-8">
                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                            Option one
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                            Option two
                        </label>
                    </div>                                
                </div> -->
				
				
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-6">
				      <button type="submit" class="btn btn-primary">提交</button>
				      <button type="reset" class="btn btn-default">重置</button>
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
			//为inputForm注册validate函数
		    $("#inputForm").validate({
				rules: {
					loginName: {
						remote: "${ctx}/register/checkLoginName"
					}
				},
				messages: {
					loginName: {
						remote: "登录名已存在"
					}
				}
			});
			
		 // datepicker plugin
            $('.input-datepicker').datepicker().on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
		 
		});
</script>