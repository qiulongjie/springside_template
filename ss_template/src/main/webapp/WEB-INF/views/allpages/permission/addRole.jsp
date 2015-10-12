<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- <link href="${ctx}/static/assets/css/lib/uniform.default.css" type="text/css" rel="stylesheet">
<link href="${ctx}/static/assets/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/assets/js/jquery.uniform.min.js"></script> --%>
<link rel="stylesheet" href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<div id="pad-wrapper" >
  <div class="row form-wrapper">
		<form id="inputForm" action="${ctx}/role/addRole" method="post" class="form-horizontal">
		    <input type="hidden" id="pmsids" name="pmsids" value=""/>
		    <input type="hidden" name="act_index" value="${act_index}"/>
			<fieldset>
				<legend><small>添加角色</small></legend>
				<div class="form-group">
					<label for="roleCode" class="col-sm-2 control-label">角色编码:</label>
					<div class="col-sm-6">
						<input type="text" id="roleCode" name="roleCode" class="form-control required" minlength="1" maxlength="50" placeholder="如：user或admin之类"/>
					</div>
				</div>
				<div class="form-group">
					<label for="roleName" class="col-sm-2 control-label">角色名称:</label>
					<div class="col-sm-6">
						<input type="text" id="roleName" name="roleName" maxlength="50" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label for="roleDesc" class="col-sm-2 control-label">角色描述:</label>
					<div class="col-sm-6">
					    <textarea id="roleDesc" name="roleDesc" class="form-control" rows="3" maxlength="250"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pmsNames" class="col-sm-2 control-label">配置权限:</label>
					<div class="col-sm-6">
		                <input type="text" id="pmsSel" name="pmsNames" value="${names }" class="form-control"/>
		                <ul id="treeDemo" class="submenu ztree" style="border:1px solid grey;">
		                </ul>
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
	//$("#roleCode").focus();
	
	$.fn.zTree.init($("#treeDemo"), setting);
	
	//为inputForm注册validate函数
    $("#inputForm").validate({
		rules: {
			roleCode: {
				remote: "${ctx}/role/checkRoleCode"
			}
		},
		messages: {
			roleCode: {
				remote: "角色编码已存在"
			}
		}
	});
	
});
function onCheck(e,treeId,treeNode){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getCheckedNodes(true),
	v = "";
	id = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		id += nodes[i].id + ",";
	}
	if (v.length > 0 ) {
		v = v.substring(0, v.length-1);
	}
	if (id.length > 0 ) {
		id = id.substring(0, id.length-1);
	}
    $("#pmsSel").val(v);
    $("#pmsids").val(id);
}	

var setting = {
      view: {
          selectedMulti: false
      },
      check: {
          enable: true
      },
      data: {
          simpleData: {
            enable: true,
            idKey: "id",
  			pIdKey: "pId",
  			rootPId: ''
          }
      },
      edit: {
          enable: false
      },
      async: {
		enable: true,
		url:"${ctx}/role/getPmsZtreeAll"
	  },
      callback:{
          onCheck:onCheck
      }
};
</script>