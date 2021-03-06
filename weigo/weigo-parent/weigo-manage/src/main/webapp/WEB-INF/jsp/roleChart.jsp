<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>访问量图表</title>
    <!-- 引入 echarts.js -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/echarts.min.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div align="center">
    <div id="main" style="width: 700px;height:400px;" ></div>
    </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        
        $.ajax({
			url:"/role/chartMessage",
			dataType:"json",
			success:function(data){
				
				var keyArry = [];
				var dataArry = [];
				var chartMessage = data.values;
				var len = chartMessage.length;
				for(var i=0;i<len;i++){
					keyArry[i] = chartMessage[i].namePage;
					dataArry[i] =  {"value":chartMessage[i].count , "name": chartMessage[i].namePage};
				}
				console.log(keyArry);
				console.log(dataArry);
				option = {
		        	    title: {
		        	        text: '微购商城角色分布',
		        	        subtext: '',
		        	        left: 'center'
		        	    },
		        	    tooltip: {
		        	        trigger: 'item',
		        	        formatter: '{a} <br/>{b} : {c} ({d}%)'
		        	    },
		        	    toolbox: {
		        	        feature: {
		        	            saveAsImage: {}
		        	        }
		        	    },
		        	    legend: {
		        	        orient: 'vertical',
		        	        left: 'left',
		        	        data: keyArry
		        	    },
		        	    series: [
		        	        {
		        	            name: '访问来源',
		        	            type: 'pie',
		        	            radius: '55%',
		        	            center: ['50%', '60%'],
		        	            data: dataArry,
		        	            emphasis: {
		        	                itemStyle: {
		        	                    shadowBlur: 10,
		        	                    shadowOffsetX: 0,
		        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		        	                }
		        	            }
		        	        }
		        	    ]
		        	};
						        // 使用刚指定的配置项和数据显示图表。
						        myChart.setOption(option);	
			}
        })
        
        	
			
        // 指定图表的配置项和数据
       
    </script>
</body>
</html>