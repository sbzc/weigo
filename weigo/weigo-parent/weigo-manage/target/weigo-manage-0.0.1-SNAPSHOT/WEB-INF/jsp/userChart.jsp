<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户</title>
    <!-- 引入 echarts.js -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/echarts.min.js"></script>
       <!--引入自定义的js  -->
    <script src="/js/mychart.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div align="center">
                                        选择日期：<input type="date" id="selectDate">&nbsp;<button onclick="startChart()">查看</button>
    </div>
    <div align="center">
    <div id="main" style="width: 700px;height:400px;" ></div>
    </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        
        $(function () {
        	startChart();
		});
        function startChart() {
        	var date = $("#selectDate").val();
        	showChartZheXiangTu("/user/charMessage","用户增加趋势图",date);
		}
        
    </script>
</body>
</html>