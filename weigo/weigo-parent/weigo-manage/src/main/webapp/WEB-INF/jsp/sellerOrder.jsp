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
								<c:forEach items="${orderItems}" var="orderItem">
									<div id="vertical-timeline"
										class="vertical-container light-timeline">

										<div class="vertical-timeline-block">
											<div class="vertical-timeline-icon navy-bg">
												<i class="fa fa-briefcase"></i>
											</div>

											<div class="vertical-timeline-content">
												<h2>
													<a title="查看商品详情" target="_blank"
														href="http://47.103.218.192:8082/item/showProductMain/${orderItem.itemId }.html"">
														${orderItem.title }</a>X${orderItem.num }
												</h2>
												<p>
													<c:if test="${orderItem.status==0}">
                                                                                                                                                                         商品待发货中。。。。。
                                            </c:if>
													<c:if test="${orderItem.status==1}">
                                                                                                                                                                         商品 <font color="red">退货中 </font> 。。。。。
                                            </c:if>
                                            
                                            <c:if test="${orderItem.status==2}">
                                                  <font>商品已退货。。订单完成</font>&nbsp;<font color="red">注意：退货的商品数量不会恢复。可自己设置</font>
                                            </c:if>
                                         <c:if test="${orderItem.status==3}">
                                                                                                           等待快递员接单中           。。。。。 <a href="javascript:void(0);" onclick="myIsDispatcher('${orderItem.id}','0')">亲自配送</a>    
                                            </c:if>
                                             <c:if test="${orderItem.status==4}">
                                                                                                                                快递员派送中。。。。<a href="javascript:void(0);" onclick="dispatcherMessage('${orderItem.id}')">查看快递员信息</a>
                                            </c:if>
                                             <c:if test="${orderItem.status==5}">
                                                                                                                                                       已签收。。。。待用户确认收货
                                            </c:if>
                                              <c:if test="${orderItem.status==6}">
                                                                                                                                                       订单已完成。。。。
                                            </c:if>
												</p>
												
                                                <c:if test="${orderItem.status==0||orderItem.status==3}">
													<button style="margin-right: 20px" type="button" class="btn btn-sm btn-danger"
														onclick="updateOrderItem('${orderItem.id}','1')">取消订单</button>
												</c:if>
												<c:if test="${orderItem.status==1}">
													<button style="margin-right: 20px" type="button" class="btn btn-sm btn-danger"
														onclick="updateOrderItem('${orderItem.id}','2')">确认退货</button>
												</c:if>
												<c:if test="${orderItem.status==0}">
													<button style="margin-right: 20px" type="button" class="btn btn-sm btn-info"
														onclick="findDispatcher('${orderItem.id}')">招募快递员</button>
												</c:if>
												 <c:if test="${orderItem.status==3}">
                                           <button style="margin-right: 20px" type="button" class="btn btn-sm btn-info"
														onclick="myIsDispatcher('${orderItem.id}','3')">撤销招募</button>                                                                      
                                            </c:if>
												<a href="javascript:void(0);" class="btn btn-sm btn-primary"
													style="margin-right: 20px" onclick="userMessage('${orderItem.clientId}')">买家信息</a> <span
													class="vertical-date"> <br> 更新时间： <small>
														<fmt:formatDate value="${orderItem.updated }"
															pattern="yyyy-MM-dd HH:mm:ss" />
												</small>
         &nbsp;  &nbsp;   订单总价<strong style="color: red;">
                                  ￥<fmt:formatNumber groupingUsed="false" minFractionDigits="2" value="${orderItem.totalFee / 100 }" />
         </strong><b>/元</b>

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
	
	//查看快递员信息
	function dispatcherMessage(orderItemId) {
		layer.open({
			  type: 2,
			  skin: 'layui-layer-rim', //加上边框
			  area: ['450px', '340px'], //宽高
			  content:'/user/dispatcherMessage?orderItemId='+orderItemId
			});
	}
	
	//查看买家信息
	function userMessage(uid) {
		layer.open({
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['450px', '340px'], //宽高
		  content:'/user/userMessage?id='+uid
		});
}
	
	function myIsDispatcher(orderItemId,status) {
		var q="";
		if(status==3){
			q="撤销将自己配送！确认吗"
		}else{
			q="确认自己配送吗？"
		}
		
		layer.confirm(q,function(index){
			$.get("/logistics/updateLogistics?orderItemId=" + orderItemId,function(data) {
				layer.msg(data.msg,{time:1500,icon:data.code},function(){
					
					if(data.code == 1){
						location.reload();
					}
				});
			});     
	     });
	}
	
	
	//招募快递员
	function findDispatcher(orderItemId) {
		layer.open({
			  type: 2 //Page层类型
			  ,area: ['80%', '600px']
			  ,title: '选择发货地址'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 4 //0-6的动画形式，-1不开启
			  ,content:'/sellerOrder/selectAddress/list?orderItemId='+orderItemId
			  ,end:function() {
			        location.reload();
			}
			});  
	}
	
	//ststus=1代表退货
	function updateOrderItem(orderId,status) {
		var q="";
		if(status==2){
			q="批准退货？";
		}else{
			q="确定取消订单吗？"
		}
		status=2;
		layer.confirm(q,function(index){
		$.get("/user/orderItem/update?status=" + status + "&id=" + orderId,function(data) {
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
