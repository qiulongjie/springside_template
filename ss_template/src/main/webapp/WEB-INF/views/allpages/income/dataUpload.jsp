<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
.myBox {
	border: 1px solid #DAE3E9;
	padding: 10px 10px 10px 10px;
	margin: 10px 0px 10px 0px;
	border-radius: 5px;
}
</style>
<div id="pad-wrapper" >
  <div class="row form-wrapper">
	
	<%
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if(error != null){
	%>
		<div class="alert alert-error input-medium controls">
			<button class="close" data-dismiss="alert">×</button>数据录入失败，请联系管理员.
		</div>
	<%
	}
	%>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div id="upload_data_show" style="display:none;">
		<div class="alert alert-success input-medium controls">
			<button class="close" data-dismiss="alert">×</button>数据正在上传，请稍后...
		</div>
		<div class="progress">
		  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
		    20%
		  </div>
		</div>
	</div>
	
	
	<form id="loginForm" 
	      onsubmit="return formSubmit();" 
	      action="${ctx}/income/upstreamDataInput" 
	      method="post" 
	      class="form-inline myBox" 
	      enctype="multipart/form-data"
	      >
	      <input type="hidden" name="act_index" value="${act_index}"/>
	      <div class="form-group">
		    <label class="sr-only">File</label>
		    <p class="form-control-static"><strong>录入上游数据:&nbsp;&nbsp;</strong></p>
		  </div>
		<div class="form-group">
			<label for="file" class="sr-only">文件:</label>
		    <input type="file" id="file" name="file"  class="form-control required"/>
		</div>
		<input id="submit_btn" class="btn btn-primary " type="submit" value="&nbsp;&nbsp;上&nbsp;&nbsp;传&nbsp;&nbsp;"/>		
		
	</form>
	
	<form id="loginForm2" 
	      onsubmit="return formSubmit2();" 
	      action="${ctx}/income/channelDataInput" 
	      method="post" 
	      class="form-inline myBox" 
	      enctype="multipart/form-data"
	      >
	      <input type="hidden" name="act_index" value="${act_index}"/>
	      <div class="form-group">
		    <label class="sr-only">File</label>
		    <p class="form-control-static"><strong>录入渠道数据:&nbsp;&nbsp;</strong></p>
		  </div>
		<div class="form-group">
			<label for="file" class="sr-only">文件:</label>
		    <input type="file" id="file2" name="file"  class="form-control required"/>
		</div>
		<input id="submit_btn" class="btn btn-primary " type="submit" value="&nbsp;&nbsp;上&nbsp;&nbsp;传&nbsp;&nbsp;"/>		
		
	</form>
	
	
	
 </div>
</div>
<script>
	$(document).ready(function() {
		$("#loginForm").validate();
		$("#loginForm2").validate();
	});
	
	function formSubmit(){
		if($("#file").val()){
			$('#upload_data_show').show();
			return true;
		}
		return false;
	}
	function formSubmit2(){
		if($("#file2").val()){
			$('#upload_data_show').show();
			return true;
		}
		return false;
	}
</script>
