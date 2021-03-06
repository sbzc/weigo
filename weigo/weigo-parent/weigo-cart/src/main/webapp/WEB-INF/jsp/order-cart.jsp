<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" /> 
    <meta name="format-detection" content="telephone=no" />  
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>订单结算页 -微购商城</title>
    <link rel="shortcut icon" type="image/png" href="/images/favicon-16x16.png"/>
	<!--结算页面样式-->	
	<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
    <link type="text/css" rel="stylesheet"  href="/css/order-commons.css" source="widget"/>	
   <link rel="stylesheet" type="text/css" href="/css/purchase.base.2012.css" />
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/base.js"></script>	
	<script type="text/javascript" src="/js/order.common.js"></script>
	<script type="text/javascript" src="/js/jquery.checkout.js"></script>
	<script type="text/javascript" src="/layer/layer.js"></script>
</head>	<body id="mainframe">
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->

<div class="w" id="headers">
		<div id="logo"><a href="/"><img alt="易购商城" src="/images/ego-logo.gif"/></a></div>
		<ul class="step" id="step2">
			<li class="fore1">1.我的购物车<b></b></li>
			<li class="fore2">2.填写核对订单信息<b></b></li>
			<li class="fore3">3.成功提交订单</li>
		</ul>
		<div class="clr"></div>
</div>

<form id="orderForm" class="hide" action="/order/create.html" method="post">
		<c:forEach items="${cartList }" var="cart" varStatus="status">
			<c:set var="totalPrice"  value="${ totalPrice + (cart.price * cart.num)}"/>
			<input type="hidden" name="orderItems[${status.index}].itemId" value="${cart.id}"/>
			<input type="hidden" name="orderItems[${status.index}].num" value="${cart.num }"/>
			<input type="hidden" name="orderItems[${status.index}].price" value="${cart.price}"/>
			<input type="hidden" name="orderItems[${status.index}].totalFee" value="${cart.price * cart.num}"/>
			<input type="hidden" name="orderItems[${status.index}].title" value="${cart.title}"/>
			<input type="hidden" name="orderItems[${status.index}].picPath" value="${cart.images[0]}"/>
		</c:forEach>
		<input type="hidden" name="payment" value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2"  value="${totalPrice/100 }"/>"/>
		<input type="hidden" id="addressId" name="endId"  value=""/>		
			
</form>

<!-- main -->
<div id="container">
	<div id="content" class="w">
		<div class="m">
			<div class="mt">
				<h2>填写并核对订单信息</h2>
			</div>
			<div class="mc">
				<div class="checkout-steps">
<div class="step-tit">
	<h3>收货人信息</h3>
	<div class="extra-r">
		<a href="#none" class="ftx-05" onclick="use_NewConsignee()">新增收货地址</a>
		<script type="text/javascript">
		    
		    	 function use_NewConsignee(){
		    			layer.open({
		    			  type: 2 //Page层类型
		    			  ,area: ['800px', '500px']
		    			  ,title: '新增地址'
		    			  ,shade: 0.6 //遮罩透明度
		    			  ,maxmin: true //允许全屏最小化
		    			  ,anim: 4 //0-6的动画形式，-1不开启
		    			  ,content:'http://47.103.218.192:8080/manage/address'
		    			   ,end:function() {
		    				        location.reload();
		    				}
		    			});  
		    		}
			
		
		</script>
	</div>
</div>
<div class="step-cont">
	<div class="consignee-list" id="consignee-list1">
		<a href="#none" id="prev" class="prev arrow-btns"></a>
		<a href="#none" id="next" class="next arrow-btns"></a>
		<div id="consignee1" class="list-cont ui-switchable-body">
            <div id="consignee-ret"></div>
   			<ul class="ui-switchable-panel-main" id="consignee-list" >
				<!---->
				<c:forEach items="${addressList}" var="address">
				<li onclick="selectThisAddress('${address.id}')" class="ui-switchable-panel"  style="cursor: pointer;">
					<div class="consignee-item"
						id="${address.id }">
						<b></b>
						<div class="user-name">
							<div class="fl">
								<strong limit="4">${address.username }</strong>&nbsp;&nbsp;收
							</div>
							<div class="fr">${address.phone }</div>
							<div class="clr"></div>
						</div>
						<div class="mt10" > 广东工业大学华立学院</div>
						<div class="adr-m" >${address.addressname }</div>
						<div>524059</div>
						<div class="op-btns ar">
							 <a href="javascript:void(0);" onclick="editAddress('${address.id}')" class="ftx-05 mr10 edit-consignee" >编辑</a>
							<a href="javascript:void(0);" class="ftx-05 del-consignee" onclick="deleteAddress('${address.id}')">删除</a>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

<script  type="text/javascript">
//删除地址
function deleteAddress(id) {
	$.ajax({ 
		url:'http://47.103.218.192:8080/manage/address/delete?id='+id, 
		type:'post', 
		dataType:'jsonp', 
		jsonpCallback:'ab12312321c', 
		jsonp:'callback', 
		success:function(data){ 
			layer.msg(data.msg,{time:1500,icon:data.code},function(){
				if(data.code == 1){
					 location.reload();
				}
			});
	    } 
	});

}

//修改地址
function editAddress(id) {
	
			layer.open({
			  type: 2 //Page层类型
			  ,area: ['800px', '500px']
			  ,title: '新增地址'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 4 //0-6的动画形式，-1不开启
			  ,content:'http://47.103.218.192:8080/manage/showAddress?id='+id
			   ,end:function() {
				        location.reload();
				}
			});  
		
}

//选择地址
    function selectThisAddress(id) {
    	var selectid=$("#addressId").val();
    	if(selectid!=""&&selectid!=id){
    		$("#"+selectid).removeClass('item-selected');
    	}
		$("#addressId").val(id);
		$("#"+id).addClass('item-selected');
	}
</script>
<div id="shipAndSkuInfo">
  <div id="payShipAndSkuInfo">
    <div class="step-tit">
	<h3>支付方式</h3>
</div>
<div class="step-cont">
	<div class="payment-list" id="">
		<div class="list-cont">
			<ul id="payment-list">
				<li style="cursor: pointer;">
					<div class="payment-item item-selected online-payment ">
						<b></b> 货到付款
					</div>
				</li>
			</ul>	
		</div>
	</div>
</div>
<div class="step-tit">
	<h3>送货清单</h3>
	<div class="extra-r">
					<a href="/cart/cart.html" id="cartRetureUrl" class="return-edit ftx-05">返回修改购物车</a>
			</div>
</div>
<div class="step-cont" id="skuPayAndShipment-cont">
	<!--添加商品清单  -->
<div class="shopping-lists" id="shopping-lists"> 
<div class="shopping-list ABTest">
	<div class="goods-list"  style="width: 90%">
     <!--配送方式-->
    <h4 class="vendor_name_h" id="0">商家：微购商城</h4>		         
    <div class="goods-suit goods-last">
	 <c:forEach items="${cartList }" var="cart">
		<div class="goods-item goods-item-extra">

			<div class="p-img">
				<a target="_blank" href="http://47.103.218.192:8082/item/showProductMain/${cart.id }.html" >
					<img src="${cart.images[0]}" alt=""/>
				</a>
			</div>
			<div class="goods-msg">
				<div class="p-name">
					<a href="http://47.103.218.192:8082/item/showProductMain/${cart.id }.html" target="_blank">
						${cart.title } 
					</a>
				</div>
				<div class="p-price" align="right">
					<!--增加预售金额显示 begin   预售分阶段支付类型（1：一阶梯全款支付；2：一阶梯定金支付(全款或定金可选)；3：三阶梯仅定金支付） -->
					<strong>￥<fmt:formatNumber
							groupingUsed="false" maxFractionDigits="2"
							minFractionDigits="2" value="${cart.price / 100 }" /></strong>
					<!--增加预售金额显示 end-->
					<span class="ml20"> x${cart.num} </span> 
					<span class="ml20 p-inventory" >
					<c:if test="${cart.enough }">
						有货
					</c:if>
					<c:if test="${not cart.enough }">
						<span class="myenough" style="color:red">无货</span>
					</c:if>
										
					</span>
				</div>
				
			</div>
			<div class="clr"></div>
		</div>
	</c:forEach>
	</div>                   
</div>
<!--goods-list 结束-->

<div class="clr"></div>
<div class="freight-cont">
	<strong class="ftx-01" style="color: #666" >免运费</strong>
</div>
</div>	
				
</div>
</div>  
</div>
</div>			
<!--  /widget/invoice-step/invoice-step.tpl -->
<div class="step-tit">
	<h3>发票信息</h3>
</div>
<div class="step-content">
	<div id="part-inv" class="invoice-cont">
		<span class="mr10"> 普通发票（电子） &nbsp; </span><span class="mr10">
			个人 &nbsp; </span><span class="mr10"> &nbsp; </span> 明细 <a href="#none"
			class="ftx-05 invoice-edit" onclick="edit_Invoice()">修改</a>
	</div>
</div>
		<div class="order-summary">
			<!--  预售 计算支付展现方式 begin -->
			<div class="statistic fr">
				<div class="list">
					<span>
						总商品金额：
					</span> 
					<em class="price" id="warePriceId">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></em>
				</div>
				<div class="list">
					<span>运费：</span> <em class="price" id="freightPriceId">
						￥0.00</em>
				</div>
				<div class="list">
					<span>应付总额：</span> <em class="price" id="sumPayPriceId">
						￥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></em>
				</div>
			</div>
			<div class="clr"></div>
		</div>
	</div>
</div>
<!--/ /widget/order-summary/order-summary.tpl -->
					
<!--  /widget/checkout-floatbar/checkout-floatbar.tpl -->
<div class="trade-foot">
  <div id="checkout-floatbar" class="group">
    <div class="ui-ceilinglamp checkout-buttons">
      <div class="sticky-placeholder hide" style="display: none;">
      </div>
      <div class="sticky-wrap">
      	<div class="inner">
          <button type="submit" class="checkout-submit btn-1" 
          		  id="order-submit">
          	提交订单
          </button>
                    <span class="total">应付总额：<strong id="payPriceId">￥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></strong>
          </span>
                    <span id="checkCodeDiv"></span>
          <div class="checkout-submit-tip" id="changeAreaAndPrice" style="display: none;">
            由于价格可能发生变化，请核对后再提交订单
          </div>
          <div style="display:none" id="factoryShipCodShowDivBottom" class="dispatching">
            部分商品货到付款方式：先由易购配送“提货单”并收款，然后厂商发货。
          </div>
        </div>
        <span id="submit_message" style="display:none" class="submit-error" ></span>
		  	<div class="submit-check-info" id="submit_check_info_message" style="display:none"></div>
    	</div>
    </div>
  </div>
  
        </div>
      </div>
    </div>
</div>
	<jsp:include page="commons/footer.jsp" />
<script type="text/javascript">
$(function(){
	$("#order-submit").click(function(){
		var selectid=$("#addressId").val();
		if($(".myenough").length>0){
			layer.tips('商品库存不足', '#order-submit', {
				  tips: [1, 'red'],
				  time: 4000
				});
		}else 
			if(selectid==""){
				layer.tips('请选择收货地址!!!', '#order-submit', {
					  tips: [1, 'red'],
					  time: 4000
					});
		}else{
			$("#orderForm").submit();
		}
		return false;
	})
})
</script>
	</body>
</html>