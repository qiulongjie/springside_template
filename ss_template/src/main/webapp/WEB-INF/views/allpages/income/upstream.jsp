<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" /> --%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
.myBox {
	border: 1px solid #DAE3E9;
	padding: 10px 10px 10px 10px;
	margin: 0px 0px 10px 0px;
	border-radius: 5px;
}
</style>
<div id="pad-wrapper" class="datatables-page">
  <div class="row">
	<div class="row myBox">
		<div class="row">
		  <div class="col-md-4">
			  <div class="input-group">
			    <span class="input-group-addon">开始月份：</span>
			    <input id="start_month" value="" type="text" class="form-control input-datepicker">
			  </div>
		  </div>
		  <div class="col-md-4">
			  <div class="input-group">
			    <span class="input-group-addon">结束月份：</span>
			    <input id="end_month" value="" type="text" class="form-control input-datepicker">
			  </div>
		  </div>
		  <div class="col-md-4">
		  </div>
		  
	  </div>
  <br>
	  <div class="row">
		  <div class="col-md-4">
		      <div class="input-group">
			    <span class="input-group-addon">签约公司：</span>
			    <!-- <input id="sign_company" type="text" class="form-control"> -->
			    <select id="sign_company" class="form-control">
	                    <option></option>
	                    <option value="掌众信息">掌众信息</option>
	                    <option value="掌动科技">掌动科技</option>
	                    <option value="掌众传媒">掌众传媒</option>
	                <%--  <c:forEach items="${roles}" var="role">
	                    <option value="${role.roleCode }" ${role.selected }>${role.roleName }</option>
	                 </c:forEach> --%>
	            </select>
			  </div>
		  </div>
		  <div class="col-md-4">
		      <div class="input-group">
			    <span class="input-group-addon">广告主：&nbsp;&nbsp;</span>
			    <input id="ad" type="text" class="form-control" />
			  </div>
		  </div>
		  <div class="col-md-4">
		 	 <input id="query_data" type="button" value="&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;" class="btn btn-primary" />
		  </div>
	  </div>
   </div>
	<br>
	
	<table id="contentTable" class="row-border hover order-column">
		<thead>
		    <tr>
		        <th>月份</th>
		        <th>签约公司</th>
		        <th>广告主</th>
		        <th>业务类型</th>
		        <th>业务</th>
		        <th>信息费</th>
		        <th>单价</th>
		        <th>结算金额</th>
		        <th>备注</th>
		    </tr>
		</thead>
	</table>
	
 </div>
</div>
<script>
$(document).ready(function() {
	
	var table = $('#contentTable').dataTable({
				         "sPaginationType": "full_numbers",
				         "bFilter":false,
				         "bSort" : false,// 排序
				         "bLengthChange": false,
				         "processing": true,
				         "serverSide": true,
				         "ajax": {
				             //"url": "${ctx}/income/upstream/getData?m1="+$('#start_month').val()+"&m2="+$('#end_month').val()+"&company="+$('#sign_company').val()+"&ad="+$('#ad').val(),
				             "url": "${ctx}/income/upstream/getData",
				             "type": "post",
				             "data":function ( d ) {
				                 d.date = new Date();
				                 d.m1 = $('#start_month').val();
				                 d.m2 = $('#end_month').val();
				                 d.company = $('#sign_company').val();
				                 d.ad = $('#ad').val();
				             }
				         },
				         "columns": [
				                      { "data": "statis_month"},
				                      { "data": "sige_company"},
				                      { "data": "advertiser"},
				                      { "data": "b_type"},
				                      { "data": "business"},
				                      { "data": "info_fee"},
				                      { "data": "unit_price"},
				                      { "data": "final_amount"},
				                      { "data": "remark"}
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
	
	
	$('.input-datepicker').datepicker({
		language: 'cn',
		startView:'year',
		minView: 'year',
		maxView: 'year',
		autoclose: true, 
        format: 'yyyymm'
	});
	
	$('#query_data').click(function(e){
		/*//var url = "${ctx}/income/upstream/getData?m1="+$('#start_month').val()+"&m2="+$('#end_month').val()+"&company="+$('#sign_company').val()+"&ad="+$('#ad').val();
		url=encodeURI(url);
		url=encodeURI(url); //两次编码
		$("#contentTable").DataTable().ajax.url(url).load(); */
		$("#contentTable").DataTable().ajax.reload(null,true);
	});
	
});

</script>
