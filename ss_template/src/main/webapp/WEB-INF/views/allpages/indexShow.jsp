<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div >
<!-- upper main stats -->
        <div id="main-stats">
            <div class="row stats-row">
                <div class="col-md-3 col-sm-3 stat">
                    <div class="data">
                        <span class="number">2457</span>
                        访问
                    </div>
                    <span class="date">今天</span>
                </div>
                <div class="col-md-3 col-sm-3 stat">
                    <div class="data">
                        <span class="number">3240</span>
                        用户
                    </div>
                    <span class="date">2013年11月</span>
                </div>
                <div class="col-md-3 col-sm-3 stat">
                    <div class="data">
                        <span class="number">322</span>
                        订单
                    </div>
                    <span class="date">本周</span>
                </div>
                <div class="col-md-3 col-sm-3 stat last">
                    <div class="data">
                        <span class="number">$2,340</span>
                        销售金额
                    </div>
                    <span class="date">最近30天</span>
                </div>
            </div>
        </div>
        <!-- end upper main stats -->
        
        <div id="pad-wrapper">

            <div class="row" style="height:300px">
                
                <div class="col-md-12">
                    <div id="pvuvChart" style="height:300px"></div>
                </div>
               
            </div>
            
        </div>
        
</div>
<script src="${ctx }/static/echarts/echarts.js"></script>
<script type="text/javascript">
$(function () {
	require.config({
	      paths: {
	          echarts: '${ctx }/static/echarts'
	      }
	});
  
	getpvuvChartData();
	
	// ajax向服务端获取数据
	function getpvuvChartData(){
		$.ajax({
            type: "POST",
            url: "${ctx }/kpi/getPVData",
            data: {period:'period', date:'date'},
            dataType: "json",
            success: function(data,textStatus){
               showChart(data);
            },
            error : function() {    
                alert("获取pvuv数据异常！");    
            } 
        });
	}
	
	// 加载图形
	function showChart(data){
		 require(
	      [
	          'echarts',
	          'echarts/chart/bar',
	          'echarts/chart/line'
	      ],
	      function (ec) {
	          var obj = document.getElementById('pvuvChart');
	          var myChart = ec.init(obj);
	          var option = createOption(data);
	          myChart.setOption(option);
	      });
	}
	
	// 组装图表的数据 
	function createOption(jsonData){
		var legendData = jsonData.legend.split(',');
		var xAxisData = jsonData.xAxis.split(',');
		
		var seriesData = new Array();
		var seriesArr = jsonData.series;
		var index = 0;
		$.each(seriesArr,function(name,value){
			var obj = {
					   name:legendData[index],
					   type:'line',
					   data:value.split(',')
					  };
			seriesData.push(obj);
			index = index + 1;
		});
		
		var option = {
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:legendData
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : xAxisData
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : seriesData
          };
		return option;
	}
	
});
</script>