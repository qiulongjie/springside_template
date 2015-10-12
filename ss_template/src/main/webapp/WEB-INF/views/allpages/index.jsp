<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

            <div class="row chart" style="height:300px">
                
                <div class="col-md-12">
                    <div id="pvuvChart" style="height:300px"></div>
                </div>
                <div class="col-md-12">
                    <div id="pvuvChart2" style="height:300px"></div>
                </div>
               
            </div>
            
        </div>
        
        
        
</div>
<script src="${ctx }/static/echarts/echarts.js"></script>
<script type="text/javascript">
    $(function () {
        
    });
   
    require.config({
        paths: {
            echarts: '${ctx }/static/echarts'
        }
    });
    var data = '2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3';
    var data1 = data.split(',');
    var legendData = ['蒸发量','降水量'];
    var xAxisData = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line'
        ],
        function (ec) {
            //--- 折柱 ---
            var obj = document.getElementById('pvuvChart');
            var myChart = ec.init(obj);
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
            var myChart2 = ec.init(document.getElementById('pvuvChart2'));
            var option2 = {
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
            myChart2.setOption(option2);
        }
    );
</script>