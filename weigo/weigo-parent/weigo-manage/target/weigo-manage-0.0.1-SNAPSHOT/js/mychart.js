 function showChar(myurl,mydate) {
          var date = mydate;
    	  var myChartzhu = echarts.init(document.getElementById('zhuxingtu'));
          var myChartbin = echarts.init(document.getElementById('bintu'));
          var myChartduizhe = echarts.init(document.getElementById('duizhexiangtu'));
	        var myChart = echarts.init(document.getElementById('main'));
          $.ajax({
  			url:myurl,
  			dataType:"json",
  			data:{"qDate":date},
  			success:function(data){
  				var keyArr=[];
  				var valueArr=[];
  				//堆折线图的x轴名
  				var keyZheName=[];
  				var valueZheArry=[];
  				var visitorMessage = data[0].values;
  				var len=visitorMessage.length;
  				for(var i =0;i<len;i++){
  					keyArr[i]=visitorMessage[i].namePage;
  					valueArr[i]=visitorMessage[i].count;
  				}
  				//柱形图
  				  var optionzhu = {
  				            title: {
  				                text: "柱形图"
  				            },
  				            tooltip: {},
  				            legend: {
  				                data:[]
  				            },
  				            xAxis: {
  				            	  type: 'category',
  				                  data:keyArr
  				            },
  				            yAxis: {
  				            	 type: 'value'
  				            },
  				            series: [{
  				                name: '',
  				                type: 'bar',
  				                data: valueArr
  				            }]
  				        };
  				//饼图
  				var optionbin = {
  			        	    title: {
  			        	        text: "饼图",
  			        	        subtext: '',
  			        	        left: 'center'
  			        	    },
  			        	    tooltip: {
  			        	        trigger: 'item',
  			        	        formatter: '{a} <br/>{b} : {c} ({d}%)'
  			        	    },
  			        	    legend: {
  			        	        orient: 'vertical',
  			        	        left: 'left',
  			        	        data:  keyArr
  			        	    },
  			        	    series: [
  			        	        {
  			        	            name: '',
  			        	            type: 'pie',
  			        	            radius: '55%',
  			        	            center: ['50%', '60%'],
  			        	            data: [
  			        	                {value: valueArr[0], name:keyArr[0]},
  			        	                {value: valueArr[1], name:keyArr[1]},
  			        	                {value: valueArr[2], name:keyArr[2]},
  			        	                {value: valueArr[3], name:keyArr[3]},
  			        	                {value: valueArr[4], name:keyArr[4]},
  			        	                {value: valueArr[5], name:keyArr[5]}
  			        	                
  			        	            ],
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
  				
  				//准备数据。搭建堆折线图
  				for(var i =1;i<data.length;i++){
					keyZheName[i-1]=data[i].keyname;
				}
  				for(var i=0;i<len;i++){
  					var valueTmpi = [];
  					for(j=1;j<data.length;j++){
  						valueTmpi[j-1] = data[j].values[i].count;
  					}
  					var valueZheArrTmp={};
  					valueZheArrTmp["name"]=keyArr[i];
  					valueZheArrTmp["type"]="line";
  					valueZheArrTmp["stack"]="总量";
  					valueZheArrTmp["areaStyle"]= "{}",
  					 
  					valueZheArrTmp["data"]=valueTmpi;
  					valueZheArry[i]=valueZheArrTmp;
  				}
  				  //开始搭建
  				 myChartzhu.setOption(optionzhu);
			       myChartbin.setOption(optionbin); 
			      //初始化数据
  				var  optionduizhe = {
  			        	    title: {
  			        	        text: "堆叠区域图"
  			        	    },
  			        	  tooltip: {
  			                trigger: 'axis',
  			                axisPointer: {
  			                    type: 'cross',
  			                    label: {
  			                        backgroundColor: '#6a7985'
  			                    }
  			                }
  			            },
  			        	    legend: {
  			        	        data: keyArr
  			        	    },
  			        	    grid: {
  			        	        left: '3%',
  			        	        right: '4%',
  			        	        bottom: '3%',
  			        	        containLabel: true
  			        	    },
  			        	    toolbox: {
  			        	        feature: {
  			        	            saveAsImage: {}
  			        	        }
  			        	    },
  			        	    xAxis: {
  			        	        type: 'category',
  			        	        boundaryGap: false,
  			        	        data: keyZheName
  			        	    },
  			        	    yAxis: {
  			        	        type: 'value'
  			        	    },
  			        	    series: valueZheArry
  			        	};
  				     //开始搭建
			        myChartduizhe.setOption(optionduizhe); 
  				
  				//准备数据
  				var sourceData = [];
  				sourceData[0]=[];
  				sourceData[0][0]="product";
  				for(var i=0;i<keyZheName.length;i++){
  					sourceData[0][i+1]=keyZheName[i];
  				}
  				for(i=0;i<keyArr.length;i++){
  					sourceData[i+1]=[];
  					sourceData[i+1][0]=keyArr[i];
  	  					for(j=1;j<data.length;j++){
  	  					sourceData[i+1][j] = data[j].values[i].count;
  	  					}
  	  					
  	  				
  				}
  				
  				//初始化
  				var  option = {
			                legend: {},
			                tooltip: {
			                    trigger: 'axis',
			                    showContent: false
			                },
			                dataset: {
			                    source: sourceData
			                },
			                xAxis: {type: 'category'},
			                toolbox: {
  			        	        feature: {
  			        	            saveAsImage: {}
  			        	        }
  			        	    },
			                yAxis: {gridIndex: 0},
			                grid: {top: '55%'},
			                series: [
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
			                    {
			                        type: 'pie',
			                        id: 'pie',
			                        radius: '30%',
			                        center: ['50%', '25%'],
			                        label: {
			                            formatter: '{b}: {@'+keyZheName[0]+'} ({d}%)'
			                        },
			                        encode: {
			                            itemName: 'product',
			                            value:keyZheName[0] ,
			                            tooltip: '2012'
			                        }
			                    }
			                ]
			            };
                        //设置悬浮饼图变化
			            myChart.on('updateAxisPointer', function (event) {
			                var xAxisInfo = event.axesInfo[0];
			                if (xAxisInfo) {
			                    var dimension = xAxisInfo.value + 1;
			                    myChart.setOption({
			                        series: {
			                            id: 'pie',
			                            label: {
			                                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
			                            },
			                            encode: {
			                                value: dimension,
			                                tooltip: dimension
			                            }
			                        }
			                    });
			                }
			            });
                        //开始搭建
			            myChart.setOption(option);
  			}
  		});
          // 指定图表的配置项和数据
	}
 function showChartZheXiangTu(myurl,mytitle,mydate) {
	 var myChart = echarts.init(document.getElementById('main'));
     $.ajax({
			url:myurl,
			dataType:"json",
			data:{"qDate":mydate},
			success:function(data){
				var keyArr=[];
				var valueArr=[];
				var visitorMessage = data.values;
				var len=visitorMessage.length;
				for(var i =0;i<len;i++){
					keyArr[i]=visitorMessage[i].namePage;
					valueArr[i]=visitorMessage[i].count;
				}
				
				 option = {
						  title: {
				                text:mytitle
				            },
				            tooltip: {},
				            legend: {
				                data:['页面访客量']
				            },
			        	    xAxis: {
			        	        type: 'category',
			        	        data: keyArr
			        	    },
			        	    toolbox: {
			        	        feature: {
			        	            saveAsImage: {}
			        	        }
			        	    },
			        	    yAxis: {
			        	        type: 'value'
			        	    },
			        	    series: [{
			        	        data: valueArr,
			        	        type: 'line'
			        	    }]
			        	};
							        // 使用刚指定的配置项和数据显示图表。
							        myChart.setOption(option);		
						
			        // 指定图表的配置项和数据
			}
     });
}