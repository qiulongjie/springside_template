<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- <div class="container"> -->


<div id="pad-wrapper" class="datatables-page">

<div class="page-header">
    <h3>角色管理<small>角色列表</small></h3>
</div>

<div class="row">
    <div class="col-md-1">
	  <a class="btn btn-default btn-sm" href="${ctx}/role/add?act_index=${act_index}" role="button">
	   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新建
	  </a>
    </div>
	<div class="col-md-11">
	</div>
</div>
<br>

 <div class="row">
   <div class="col-md-12">
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<table id="contentTable" class="display">
		<thead>
		    <tr>
		        <th>角色编码</th>
		        <th>角色名称</th>
		        <th>角色描述</th>
		        <th>角色状态</th>
		        <th>创建时间</th>
		        <th>创建用户</th>
		        <th>管理</th>
		    </tr>
		</thead>
		<tbody>
		<%-- <c:forEach items="${roles}" var="role">
			<tr>
				<td><a href="${ctx}/role/update/${role.roleCode}?act_index=${act_index}">${role.roleName}</a></td>
				<td>${role.roleDesc}</td>
				<td>
					<c:if test="${role.roleStatus=='0'}">失效</c:if>
					<c:if test="${role.roleStatus=='1'}">有效</c:if>	
				</td>
				<td>
					<fmt:formatDate value="${role.createTime}" pattern="yyyy.MM.dd HH:mm:ss" />
				</td>
				<td>${role.createUser}</td>
				<td><a href="${ctx}/role/delete/${role.roleCode}?act_index=${act_index}" onclick="return confirm('警告！！确定删除该角色吗？')">删除</a></td>
			</tr>
		</c:forEach> --%>
		</tbody>
	</table>
</div>
</div>
</div>

<script type="text/javascript">
 $(function () {
	 
	 setTimeout(hideMsg,3000);
	    
     function hideMsg(){
    	$('#message').hide('slow');
     }
	 
	 $('#contentTable').dataTable({
		 "sPaginationType": "full_numbers",
         "bFilter":false,
         "bSort" : false,// 排序
         "bLengthChange": false,
         "processing": true,
         "serverSide": true,
         "ajax": {
             "url": "${ctx}/role/getRoleData",
             "type": "post",
             "data":{}
         },
         "columnDefs": [ 
              {
        	    "targets": 1,
        	    "data": "",
        	    "render": function ( data, type, full, meta ) {
        	      return '<a href="${ctx}/role/update/'+full.role_code+'?act_index=${act_index}">'+data+'</a>';
        	     }
        	  } ,
        	  {
          	    "targets": 6,
          	    "data": "",
          	    "render": function ( data, type, full, meta ) {
          	      return "<a href='${ctx}/role/delete/"+full.role_code+"?act_index=${act_index}' onclick='return confirm(&quot;警告！！确定删除该角色吗？&quot;)'>删除</a>";
          	    }
          	  } ],
         "columns": [
                      { "data": "role_code","visible": false},
                      { "data": "role_name"},
                      { "data": "role_desc"},
                      { "data": "role_status"},
                      { "data": "create_time"},
                      { "data": "create_user"},
                      { "data": null}
                 ],
	   	 "oLanguage": {
	            "sProcessing": "正在加载中......",
	            "sLengthMenu": "每页显示 _MENU_ 条记录",
	            "sZeroRecords": "对不起，查询不到相关数据！",
	            "sEmptyTable": "表中无数据存在！",
	            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
	            "sSearch": "搜索",
	            "oPaginate": {
	                "sFirst": "首页",
	                "sPrevious": "上一页",
	                "sNext": "下一页",
	                "sLast": "末页"
	            }
	   	 }
     });
 });
</script>