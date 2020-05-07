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

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
<style type="text/css">
.Huifold .item{ position:relative}
.Huifold .item h4{margin:0;font-weight:bold;position:relative;border-top: 1px solid #fff;font-size:15px;line-height:22px;padding:7px 10px;background-color:#eee;cursor:pointer;padding-right:30px}
.Huifold .item h4 b{position:absolute;display: block; cursor:pointer;right:10px;top:7px;width:16px;height:16px; text-align:center; color:#666}
.Huifold .item .info{display:none;padding:10px}

.star-bar{font-size:0; line-height:0}
.star-bar .star{display:inline-block;text-align:center}
/*中*/
.size-M img{ width:24px;height:24px}
/*小*/
.size-S img{width:16px;height:16px}

body{ background-color:#71C5F2}
.share_weibo_wp{width:480px; padding:20px; background-color:#fff; margin-left:auto; margin-right:auto; margin-top:20px;-khtml-border-radius:10px;-ms-border-radius:10px;-o-border-radius:10px;-moz-border-radius:10px;-webkit-border-radius:10px;border-radius:10px;behavior: url(ie-css3.htc)}
.count_txt{color: #B5B5B5;text-align: right}
.count_txt strong {font-family: georgia;font-size: 24px; padding: 0 2px}
.inputstyle {height: 75px;line-height: 18px;overflow-x: hidden;overflow-y: auto;width: 472px}
</style>
<script type="text/javascript">



jQuery.Huifold = function(obj,obj_c,speed,obj_type,Event){
	if(obj_type == 2){
		$(obj+":first").find("b").html("-");
		$(obj_c+":first").show()}
	$(obj).bind(Event,function(){
		if($(this).next().is(":visible")){
			if(obj_type == 2){
				return false}
			else{
				$(this).next().slideUp(speed).end().removeClass("selected");
				$(this).find("b").html("+")}
		}
		else{
			if(obj_type == 3){
				$(this).next().slideDown(speed).end().addClass("selected");
				$(this).find("b").html("-")}else{
				$(obj_c).slideUp(speed);
				$(obj).removeClass("selected");
				$(obj).find("b").html("+");
				$(this).next().slideDown(speed).end().addClass("selected");
				$(this).find("b").html("-")}
		}
	})}
	
	
function checkLength(which) {
	  var maxChars = 120;
	  if (which.value.length > maxChars)
	  which.value = which.value.substring(0,maxChars);
	  var curr = maxChars - which.value.length;
	$(which).siblings("div").find("span").find("#currentLength").html(curr.toString());
	  
	  }
</script>

<script type="text/javascript">
$(function(){
	$(".star-bar").raty({
		hints: ['1','2', '3', '4', '5'],//自定义分数
		starOff: 'iconpic-star-S-default.png',//默认灰色星星
		starOn: 'iconpic-star-S.png',//黄色星星
		path: '/static/h-ui/images/star',//可以是相对路径
		number: 5,//星星数量，要和hints数组对应
		showHalf: true,
		targetKeep : true,
		click: function (score, evt) {//点击事件
			$(this).parent().find("#evaluateScore").val(score);
			$(this).parent().find("#result-1").html('你的评分是'+score+'分');
			
		   
		}
	});
	
	});


$(function(){
	$.Huifold("#Huifold1 .item h4","#Huifold1 .item .info","fast",1,"click"); /*5个参数顺序不可打乱，分别是：相应区,隐藏显示的内容,速度,类型,事件*/
});
</script>

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
														href="http://47.103.218.192:8082/item/showProductMain/${orderItem.itemId }.html">
														${orderItem.title }</a>X${orderItem.num }
												</h2>
												<p>
													<c:if test="${orderItem.status==0}">
                                                                                                                                                                         商品待发货中。。。。。
                                            </c:if>
													<c:if test="${orderItem.status==1}">
                                                                                                                                                                         商品 <font
															color="red">退货中 </font> 。。。。。
                                            </c:if>
                                            <c:if test="${orderItem.status==2}">
                                                   
                                                  <font>商品退货了。。订单完成</font>
                                            </c:if>
                                             <c:if test="${orderItem.status==3}">
                                                                                                                                                       等待快递员接单中           。。。。。
                                            </c:if>
                                             <c:if test="${orderItem.status==4}">
                                                                                                                                                        快递员派送中。。。。<a href="javascript:void(0);" onclick="dispatcherMessage('${orderItem.id}')">查看快递员信息</a>
                                            </c:if>
                                             <c:if test="${orderItem.status==5}">
                                                                                                                                                       商品已签收。。。。
                                            </c:if>
                                              <c:if test="${orderItem.status==6}">
                                                                                                                                                       订单已完成。。。。
                                            </c:if>
												</p>

												<c:if test="${orderItem.status==1}">
													<button type="button" class="btn btn-sm btn-danger"
														onclick="updateOrderItem('${orderItem.id}','0')">撤销退货</button>
												</c:if>
												<c:if test="${orderItem.status==0||orderItem.status==3}">
													<button type="button" class="btn btn-sm btn-danger"
														onclick="updateOrderItem('${orderItem.id}','1')">退货</button>
												</c:if>
												<c:if test="${orderItem.status==2||orderItem.status==6&&orderItem.buyerRate!=1}">
                                                      
                                                          <ul id="Huifold1" class="Huifold" >
														  <li class="item">
														    <h4>评价卖家<b>+</b></h4>
														    <div class="info"> 
														    <div class="cl share_weibo_wp" >
																	  <div class="cl">
																	    <div class="clearfix">
																		<span id="star-1" class="star-bar size-M f-l mr-10 va-m"></span>
																		&nbsp;&nbsp;<strong id="result-1" class="f-l va-m"></strong>
																		<input type="hidden" id="evaluateScore" value="1">
																		
																	   </div >
																	    <span class="r count_txt">还能输入<strong id="currentLength">120</strong>字</span>
																	  </div>
																	  <textarea onkeyup="checkLength(this);" id="evaluateMsg" class="textarea radius inputstyle" name="" cols="" rows="" placeholder="输入对商家的评价。。。。是对商家哦" ></textarea>
																	  <button type="button" id="submitEvaluate" onclick="clientEvaluate('${orderItem.id}',this)" class="btn btn-sm btn-info" style="margin-right: 40%">提交</button>
																	</div>
														     </div>
														  </li>
														</ul>
                                                        
												</c:if>
												<c:if test="${orderItem.status==5}">
													<button type="button" class="btn btn-sm btn-info"
														onclick="updateOrderItem('${orderItem.id}','6')">确认收货</button>
												</c:if>
												<a href="javascript:void(0);" class="btn btn-sm btn-primary"
													style="margin-right: 20px" onclick="userMessage('${orderItem.sellerId}')">卖家信息</a> <span
													class="vertical-date"> <br> 更新时间： <small>
														<fmt:formatDate value="${orderItem.updated }"
															pattern="yyyy-MM-dd HH:mm:ss" />
												</small>
                                                  &nbsp;  &nbsp;   订单总价<strong style="color: red;">
                                  ￥<fmt:formatNumber groupingUsed="false" minFractionDigits="2" value="${orderItem.totalFee  / 100 }" />
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
	//查看卖家信息
	function userMessage(uid) {
		layer.open({
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['450px', '340px'], //宽高
		  content:'/user/userMessage?id='+uid
		});
}
	//买家评论
	function clientEvaluate(OrderItemId,thisObj) {
		
		var evaluateMsg= $(thisObj).siblings("textarea").val();
		evaluateMsg=evaluateMsg.trim()
		var evaluateScore=$(thisObj).siblings("div").find("div").find("input").val();
		if(evaluateMsg.length == 0){
			layer.tips('不可为空', thisObj, {
				  tips: [1, 'red'],
				  time: 4000
				});
		}else{
			layer.confirm("确认提交该评论吗",function(index){
				$.post("/user/evaluateSeller",
						{"evaluatemsg":evaluateMsg ,
					     "evaluatescore":evaluateScore,
					     "OrderItemId":OrderItemId},function(data) {
					layer.msg(data.msg,{time:1500,icon:data.code},function(){
						
						if(data.code == 1){
							location.reload();
						}
					});
				});     
			});
			
			
		}
		
	}
	
	
	//ststus=1代表退货
	function updateOrderItem(orderId,status) {
		var q="";
		if(status==1){
			q="退货";
		}else if(status==0){
			q="撤销退货"
		}
		layer.confirm("确认"+q+"吗？",function(index){
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
