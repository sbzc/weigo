<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单数据图表</title>
    <!-- 引入 echarts.js -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/echarts.min.js"></script>
    <!--引入自定义的js  -->
    <script src="/js/mychart.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
   <div align="center">
      <h4>微购商城商品订单数据分析</h4>
    </div>
    <div style="width: 55%;float: left;">
    <div id="zhuxingtu" style="width: 100%;height:400px;" ></div>
    </div>
    <div style="width: 44%;float: left;">
    <div id="bintu" style="width: 100%;height:400px;" ></div>
    </div>
    <div align="center">
                                        选择日期：<input type="date" id="selectDate">&nbsp;<button onclick="startChart()">查看</button>
    </div>
      <div style="width: 100%; float: left; padding-left: 10%" >
    <div id="duizhexiangtu" style="width: 80%;height:400px;" ></div>
    </div>
    <div style="width: 200px">
    </div>
    <div  style="width: 100%; float: left; padding-left: 10%">
    <div id="main" style="width: 80%;height:500px;" ></div>
    </div>
    <script type="text/javascript">
    $(function () {
    	startChart();
	});
    
    function startChart() {
    	var date = $("#selectDate").val();
    	showChar("/item/chartMessage",date);
	}
    
    </script>
</body>
</html>