<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- <link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" /> --%>

<div id="pad-wrapper" class="datatables-page">

<div class="page-header">
    <h3>用户管理<small>用户列表</small></h3>
</div>

 <div class="row">
   <div class="col-md-12">
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<table id="contentTable" class="display">
<!-- 	<table id="contentTable" class="table table-striped table-condensed"> -->
		<thead>
		    <tr>
		        <th>ID</th>
		        <th>登录名</th>
		        <th>用户名</th>
		        <th>注册时间</th>
		        <th>管理</th>
		    </tr>
		</thead>
		<tbody>
		<%-- <c:forEach items="${users.content}" var="user">
			<tr>
				<td><a href="${ctx}/admin/user/update/${user.id}?act_index=${act_index}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>
					<fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
				<td>
				 <a href="${ctx}/admin/user/delete/${user.id}?act_index=${act_index}" onclick="return confirm('警告！！确定要删除该用户吗？')">删除</a>
				 &nbsp;&nbsp;
				 <a href="${ctx}/admin/user/configRole/${user.id}?act_index=${act_index}" >配置角色</a>
				</td>
			</tr>
		</c:forEach> --%>
		</tbody>
	</table>
	
<%-- <tags:pagination page="${users}" paginationSize="10"/> --%>

</div>
</div>
</div>
<script type="text/javascript">
 $(function () {
	 
$('#contentTable').dataTable({
 "sPaginationType": "full_numbers",
       "bFilter":false,
       "bSort" : false,// 排序
       "bLengthChange": false,
       "processing": true,
       "serverSide": true,
       "ajax": {
           "url": "${ctx}/admin/user/getUserData",
           "type": "post",
           "data":{}
       },
       "columnDefs": [ 
            {
      	    "targets": 1,
      	    "data": "loginName",
      	    "render": function ( data, type, full, meta ) {
      	      return '<a href="${ctx}/admin/user/update/'+full.id+'?act_index=${act_index}">'+data+'</a>';
      	     }
      	  } ,
      	  {
        	    "targets": 4,
        	    "data": "",
        	    "render": function ( data, type, full, meta ) {
        	      return "<a href='${ctx}/admin/user/delete/"+full.id+"?act_index=${act_index}' onclick='return confirm(&quot;警告！！确定要删除该用户吗？&quot;)'>删除</a>" +
        	    		  "&nbsp;&nbsp;"+
        	    		  "<a href='${ctx}/admin/user/configRole/"+full.id+"?act_index=${act_index}' >配置角色</a>";
        	    }
        	  } ],
       "columns": [
                    { "data": "id","visible": false},
                    { "data": "loginName"},
                    { "data": "name"},
                    { "data": "registerDate"},
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