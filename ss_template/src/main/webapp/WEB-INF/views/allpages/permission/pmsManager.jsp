<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/assets/js/select2.min.js"></script>
<link href="${ctx}/static/assets/css/lib/select2.css" type="text/css" rel="stylesheet">

<link rel="stylesheet" href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<div id="pad-wrapper" >

  <div class="row">
       <div class="col-sm-12" style='height:50px;'>
          
      </div>
      <div id='success_message' class="col-sm-12">
	      <c:if test="${not empty message}">
			<div class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
		  </c:if>
	  </div>
	   <div id='show_message' class="col-sm-12" style="display:none;">
			<div id="message" class="alert alert-warning"><button data-dismiss="alert" class="close">×</button><span id='span_message'></span></div>
	  </div>
  
      <div class="col-sm-6">
         <a id="add_node_btn" class="btn btn-default btn-sm" href="#" role="button">
		   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新建
		  </a>
          <a id="del_node_btn" class="btn btn-default btn-sm" href="#" role="button">
		   <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> 删除
		  </a>
          <a id="modify_node_btn" class="btn btn-default btn-sm" href="#" role="button">
		   <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改
		  </a>
          <a id="config_role_btn" class="btn btn-default btn-sm" href="#" role="button">
		   <span class="glyphicon glyphicon-user" aria-hidden="true"></span> 分配角色
		  </a>
         <ul id="treeDemo" class="ztree" ></ul>
      </div>
      <div class="col-sm-6">
             <ul class="submenu unstyled" style="display:none">
                 
         <div class="row form-wrapper">
				<form id="inputForm" action="${ctx}/pms/mergePms" method="post" class="form-horizontal">
				    <input type="hidden" id='pid' name="pid" />
				    <input type="hidden" id='actionTyle' name="actionTyle" />
				    <input type="hidden" name="act_index" value="${act_index}"/>
					<fieldset>
                        <legend><small id='legend_title'></small></legend>
						<div class="form-group">
							<label for="pmsName" class="col-sm-2 control-label">权限名称:</label>
							<div class="col-sm-6">
								<input type="text" id="pmsName" name="pmsName"  maxlength="50" class="form-control required"/>
							</div>
						</div>
						<div class="form-group">
							<label for="accessUri" class="col-sm-2 control-label">访问路径:</label>
							<div class="col-sm-6">
								<input type="text" value='#' id="accessUri" name="accessUri"  maxlength="60" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
						    <div class="col-sm-offset-2 col-sm-6">
						      <button type="submit" class="btn btn-primary">提交</button>
						      <button type="reset" class="btn btn-default">重置</button>
						    </div>
						</div>
					</fieldset>
				</form>
		   </div>
		
             </ul>
             
             <ul class="submenu2 unstyled" style="display:none">
                 
		         <div class="row form-wrapper">
						<form id="inputForm2" action="${ctx}/pms/configPmsRoles" method="post" class="form-horizontal">
						    <input type="hidden" id='pms_ids' name="pms_ids" />
						    <input type="hidden" name="act_index" value="${act_index}"/>
							<fieldset>
		                        <legend><small id='legend_title2'></small></legend>
								<div class="form-group">
									<label for="roles" class="col-sm-2 control-label">选择角色:</label>
									<div class="col-sm-6">
										<select name="roles" style="width:250px" multiple class="select2">
				                                <option></option>
				                             <c:forEach items="${roles}" var="role">
				                                <option value="${role.roleCode }" ${role.selected }>${role.roleName }</option>
				                             </c:forEach>
				                        </select>
									</div>
								</div>
								<div class="row">
								    <div class="col-sm-2"></div>
									<div class="col-sm-8 page-header"><h6>提醒:<small>此处为权限添加角色，之前拥有的角色不会被删除</small></h6></div>
								</div>
								<div class="form-group">
								    <div class="col-sm-offset-2 col-sm-6">
								      <button type="submit" class="btn btn-primary">提交</button>
								    </div>
								</div>
								
							</fieldset>
						</form>
						
				   </div>
		
             </ul>
      </div>
  </div>
</div>
<script>
$(document).ready(function() {
	
	$.fn.zTree.init($("#treeDemo"), setting);
	
	//为inputForm注册validate函数
    $("#inputForm").validate({
	});
	
    setTimeout(hideMsg,5000);
    
    function hideMsg(){
    	$('#success_message').hide('slow');
    }
    
    $(".select2").select2({
    });
	
});

//******* 删除 ===  start  ===
$('#del_node_btn').on('click',function(e){
	hideSubmune2();
	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
    nodes=treeObj.getCheckedNodes(true);
    ids="";
    pids="";
    names="";
    for(var i=0;i<nodes.length;i++){
    	if(!nodes[i].isParent){
    	   ids+=nodes[i].id + ",";
    	   names = names + "\n【" + nodes[i].name + "】";
    	}else{
    		if(nodes[i].id != '0'){
    			pids+=nodes[i].id + ",";
    		}
    	}
    }
    ids = ids.substr(0,ids.length-1);
    pids = pids.substr(0,pids.length-1);
    if(ids != ''){
    	if(confirm("确定要删除如下权限吗？"+names)){
    		// ajax 删除
    		deletePms(ids,pids);
    	}
    	hideMessage();
    }else{
    	showMessage('请选择要删除的权限');
    }
});

// 向服务器请求删除权限
function deletePms(ids,pids){
	$.ajax({
        type: "POST",
        url: "${ctx }/pms/deletePms",
        data: {ids:ids,pids:pids},
        dataType: "text",
        success: function(data,textStatus){
        	//window.location.href='${ctx }/pms';
        	$.fn.zTree.init($("#treeDemo"), setting);
        },
        error : function() {    
            alert("操作异常！");    
        } 
    });
}
//******* 删除 ==== END　  ==
	
	
// ******* 分配角色   === start === %%%%%%%
var isConfiging = false;
$('#config_role_btn').on('click',function(e){
	configRoleClick();
	isConfiging = true;
});

function configRoleClick(){
	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
    nodes=treeObj.getCheckedNodes(true);
	ids="";
    names="";
    for(var i=0;i<nodes.length;i++){
    	if(!nodes[i].isParent){
    	   names = names + "【" + nodes[i].name + "】";
    	}
   		if(nodes[i].id != '0'){
   		   ids+=nodes[i].id + ",";
   		}
    }
    if(ids == ''){
    	showMessage('请选择要分配角色的权限!');
    	$(".submenu2").slideUp('fast');
    	return ;
    }
    ids = ids.substr(0,ids.length-1);
    $('#legend_title2').text('分配角色：为勾选的权限分配角色');
    $('#pms_ids').val(ids);
    hideSubmenu(true);
}
//******* 分配角色   === END === %%%%%%%

// ******* 添加  === start ===
$('#add_node_btn').on('click',function(e){
	addClick();
});

function addClick(){
	hideSubmune2();
	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
    nodes=treeObj.getSelectedNodes(true);
    if(nodes.length == 0){
    	showMessage('请选择父权限');
    	return;
    }
    showSubmenu(true);
	if(nodes[0].id == '0'){
	    $('#legend_title').text('在根节点添加权限');
    }else{
    	$('#legend_title').text('添加：在【'+nodes[0].name+'】权限下面添加子权限');
    }
	$('#pid').val(nodes[0].id);
	$('#accessUri').val('#');
	$('#pmsName').val('');
	$('#actionTyle').val('add');
	hideMessage();
}
//******* 添加  === END ===


// ******* 修改 ======
$('#modify_node_btn').on('click',function(e){
	modifyClick();
});

function modifyClick(){
	hideSubmune2();
	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
    nodes=treeObj.getSelectedNodes(true);
    if(nodes.length == 0){
    	showMessage('请选择要修改的权限!');
    	return;
    }
	if(nodes[0].id == '0'){
		showMessage('此节点不能修改');
		$(".submenu").slideUp('fast');
		return ;
    }else{
    	$('#legend_title').text('修改：【'+nodes[0].name+'】权限');
    }
	$('#pid').val(nodes[0].id);
	$('#actionTyle').val('update');
	$('#accessUri').val(nodes[0].accessUri);
	$('#pmsName').val(nodes[0].name);
	showSubmenu(true);
	hideMessage();
}
//******* 修改  ==  END  ====

function showSubmenu(b){
	$(".submenu").slideDown('fast');
	if(b){
		$(".submenu2").slideUp('fast');
	}
}	

function hideSubmenu(b){
	$(".submenu").slideUp('fast');
	if(b){
		$(".submenu2").slideDown('fast');
	}
}

function hideSubmune2(){
	isConfiging = false;
	$(".submenu2").slideUp('fast');
}	


	
function showMessage(msg){
	$('#span_message').text(msg);
	$('#show_message').show();
}
function hideMessage(){
	$('#show_message').hide();
}

function onClick(e,treeId,treeNode){
    var actionTyle = $('#actionTyle').val();
    
   	if(actionTyle == 'update'){
   		modifyClick();
   	}
   	if(actionTyle == 'add'){
   		addClick();
   	}
}

function onCheck(e,treeId,treeNode){
	if(isConfiging){
	   configRoleClick();
	}
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
			url:"${ctx}/pms/getPmsForTree",
			autoParam:["id", "pId", "name"],
			otherParam:{"xxx":"aaa"}
		},
        callback:{
            onClick:onClick,
            onCheck:onCheck
        }
};

</script>