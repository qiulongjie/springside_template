<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
        
        <div id="pad-wrapper">

            <!-- statistics chart built with jQuery Flot -->
            <div class="row chart">
                <div class="col-md-12">
                    <h4 class="clearfix">
                         <div class="btn-group pull-right">
                            <button class="glow left" onclick="flush()">日</button>
                            <button class="glow middle active">月</button>
                            <button class="glow right">年</button>
                        </div>
                    </h4>
                </div>
                <div class="col-md-12">
                    <div id="statsChart"></div>
                </div>
               
            </div>
            <div class="row">
             <div class="col-md-12" style="height:500px">
                    <div id="mainMap" style=" height:500px">
                    </div>
             </div>
            </div>
            <!-- end statistics chart -->
            
        </div>
<script src="${ctx }/static/echarts/echarts.js"></script>
    
    <script type="text/javascript">
    $(function () {
        
        
    });
    // Step:3 conifg ECharts's path, link to echarts.js from current page.
    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
   
    require.config({
        paths: {
            echarts: '${ctx }/static/echarts'
        }
    });
    
    // Step:4 require echarts and use it in the callback.
    // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
    
    var data = '2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3';
    var data1 = data.split(',');
    var legendData = ['蒸发量','降水量'];
    var xAxisData = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line',
            'echarts/chart/pie',
            'echarts/chart/map'
        ],
        function (ec) {
            //--- 折柱 ---
            var myChart = ec.init(document.getElementById('statsChart'));
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
                    series : [
                        {
                            name:'蒸发量',
                            type:'bar',
                            data:data1
                        },
                        {
                            name:'降水量',
                            type:'bar',
                            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                        }
                    ]
                };
            myChart.setOption(option);
            
            // --- 地图 ---
            var myChart2 = ec.init(document.getElementById('mainMap'));
            myChart2.setOption({
                tooltip : {
                    trigger: 'item',
                    formatter: '{b}'
                },
                series : [
                    {
                        name: '中国',
                        type: 'map',
                        mapType: 'china',
                        selectedMode : 'multiple',
                        itemStyle:{
                            normal:{label:{show:true}},
                            emphasis:{label:{show:true}}
                        },
                        data:[
                            {name:'广东',selected:true}
                        ]
                    }
                ]
            });
        }
    );
    
    function flush(){
    	
    	require(
    	        [
    	            'echarts',
    	            'echarts/chart/bar',
    	            'echarts/chart/line'
    	        ],
    	        function (ec) {
    	            //--- 折柱 ---
    	            var myChart = ec.init(document.getElementById('statsChart'));
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
    	                    series : [
    	                        {
    	                            name:'蒸发量',
    	                            type:'bar',
    	                            data:data1
    	                        }
    	                    ]
    	                };
    	            myChart.setOption(option);
    	        }
    	    );
    }
    
    </script>
        
</div>
