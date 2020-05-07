<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>H+ 后台主题UI框架 - 时间轴</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="lib/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="lib/bootstrap/css/animate.min.css" rel="stylesheet">
<link href="lib/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
</head>

<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="wrapper wrapper-content">
				<div class="row animated fadeInRight">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="text-center float-e-margins p-md">
								<span>预览：</span> <a href="#" class="btn btn-xs btn-primary"
									id="lightVersion">浅色</a> <a href="#"
									class="btn btn-xs btn-primary" id="darkVersion">深色</a>
							</div>
							<div class="" id="ibox-content">
								<c:forEach items="${tbOrderShippings}" var="tbOrderShipping">
									<div id="vertical-timeline"
										class="vertical-container light-timeline">

										<div class="vertical-timeline-block">
											<div class="vertical-timeline-icon navy-bg">
												<i class="fa fa-briefcase"></i>
											</div>

											<div class="vertical-timeline-content">
												<h2>
													<a title="查看商品详情" target="_blank"
														href="http://47.103.218.192:8082/item/showProductMain/${tbOrderShipping.itemId }.html">
														${tbOrderShipping.itemTitle }</a>X${tbOrderShipping.num }
												</h2>
												<p>
												
														<div style="float: left;width: 35%">
														<span>发货地址：</span>  ${tbOrderShipping.startAddress }    <br>    <br> 
		                                                 <span>电话:</span>    ${tbOrderShipping.startPhone }     <br> <br> 
		                                                 <span>姓名:</span>    ${tbOrderShipping. startName }        
														</div>
		                                                <div style="float: left;">
		                                                </div>
		                                                 <span>收货地址： </span> ${tbOrderShipping.endAddress } <br> <br> 
		                                                    <span>电话: </span> ${tbOrderShipping.endPhone }<br> <br> 
		                                                    <span>姓名:</span> ${tbOrderShipping.endName } 
		                                       </p>      
                                               
											
												
												
												<c:if test="${tbOrderShipping.status==4}">
													<button type="button"  class="btn btn-sm btn-danger"
														onclick="dispatcherReset('${tbOrderShipping.orderItemId}','3')">退单</button>
													<button style="margin-right: 20px" type="button" class="btn btn-sm btn-info"
														onclick="dispatcherSingNo('${tbOrderShipping.orderItemId}')">签收</button>
												</c:if>
												
													 <span class="vertical-date"> <br> 更新时间： <small>
														<fmt:formatDate value="${tbOrderShipping.updated }"
															pattern="yyyy-MM-dd HH:mm:ss" />
															
												</small>
                                              &nbsp;  &nbsp;   需要定金<strong style="color: red;">
                                  ￥<fmt:formatNumber groupingUsed="false" minFractionDigits="2" value="${tbOrderShipping.price / 100 }" />
         </strong><b>/元</b>  &nbsp;  &nbsp;  &nbsp;  &nbsp; 
                                                        <c:if test="${tbOrderShipping.status==5}">
													   <font style="color:  green;">配送完成</font>
												     </c:if>
												     <c:if test="${tbOrderShipping.status!=5}">
													   <font style="color: red;">待配送。。。</font>
												     </c:if>
												</span>
											</div>
										</div>
									</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>



	<!-- 自定义js -->
	<script src="js/content.js?v=1.0.0"></script>
	

	<script>
	function dispatcherReset(orderItemId,status) {
		
		layer.confirm("确认要退单吗？",function(index){
		$.get("/logistics/dispatcherReset?orderItemId="+orderItemId,function(data) {
			layer.msg(data.msg,{time:1500,icon:data.code},function(){
				if(data.code == 1){
					location.reload();
				}
			});
		  });     
	   });
	}
	function dispatcherSingNo(orderItemId) {
		layer.confirm("确定签收吗？",function(index){
			$.get("/logistics/dispatcherSingNo?orderItemId="+orderItemId,function(data) {
				layer.msg(data.msg,{time:1500,icon:data.code},function(){
					if(data.code == 1){
						location.reload();
					}
				});
			  });     
		   });
		
	}
	
		$(document).ready(function() {

			// Local script for demo purpose only
			$('#lightVersion').click(function(event) {
				event.preventDefault()
				$('#ibox-content').removeClass('ibox-content');
				$('#vertical-timeline').removeClass('dark-timeline');
				$('#vertical-timeline').addClass('light-timeline');
			});

			$('#darkVersion').click(function(event) {
				event.preventDefault()
				$('#ibox-content').addClass('ibox-content');
				$('#vertical-timeline').removeClass('light-timeline');
				$('#vertical-timeline').addClass('dark-timeline');
			});

		});
	</script>

</body>

</html>
