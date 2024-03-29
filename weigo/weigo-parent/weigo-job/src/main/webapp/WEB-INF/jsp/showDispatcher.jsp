<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>快递员接单</title>
     <link rel="shortcut icon" type="image/png" href="/images/favicon-16x16.png"/>
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="css/themecss/so_megamenu.css" rel="stylesheet">
    	<link id="color_scheme" href="css/theme.css" rel="stylesheet">
<link href="/css/header1.css" rel="stylesheet">
<style type="text/css">

.panel{ background-color:#fff; border:solid 1px transparent}
	.panel-header{ border-bottom:solid 1px transparent; padding:8px 15px; font-size:14px; font-weight:700}/*面板标题*/
	.panel-body{ padding:15px}/*面板内容*/


.panel-primary{border-color:#5a98de}
.panel-primary > .panel-header{ border-color:#5a98de; background-color:#5a98de; color:#fff}
</style>
</head>
<body>
<jsp:include page="commons/head.jsp"></jsp:include>
<div style="height: 60px;text-align: center;">
<br>
<br >
                                <font color="red;">亲爱的快递员：系统建议每单两块钱,可多多和商家协商</font>
</div>
<c:forEach items="${shippingMessages }" var="shippingMessage">
              <div style="width: 100%;height: 160px">
  <div style="width: 80%;margin-left: 5%;height: 150px;float: left;">
       <div class="panel panel-primary" style="width: 30%;float: left;height: 98%">
	<div class="panel-header">发货者：${shippingMessage.startName }</div>
	<div class="panel-body" >
	发货地址：${shippingMessage.startAddress }<br><br>
	电话：${shippingMessage.startPhone }
	</div>
       </div>
       
     <div style="float: left;width:39%;text-align: center;height: 98%" >
     
	         <span>
	                              发布时间：<fmt:formatDate value="${shippingMessage.updated }" pattern="yyyy-MM-dd HH:mm:ss" />
			 </span><br>
	       <a href="http://localhost:8082/item/showProductMain/${shippingMessage.itemId}.html" title="${shippingMessage.itemTitle }"> <img  src="${shippingMessage.image }" height="90%"></a>
	</div>
        <div class="panel panel-primary" style="width: 30%;float: left;height: 98%">
	<div class="panel-header">收货者：${shippingMessage.endName }</div>
	<div class="panel-body" >
	收货地址：${shippingMessage.endAddress }<br><br>
	电话：${shippingMessage.endPhone }
	</div>
       </div>
  </div>
  
  <div style="float: left; width:15%;height: 150px; text-align: center;">
          <b>接单需垫付</b><br>
         <strong style="color: red;">
                                  ￥<fmt:formatNumber groupingUsed="false" minFractionDigits="2" value="${shippingMessage.privce / 100 }" />
         </strong><b>/元</b>
         <br>
        <button class="btn btn-primary " style="margin-top:25px" onclick="takeOrders('${shippingMessage.orderItemId}')" >接单</button>
  </div>
  </div>  
 
<hr style="width: 90%;margin-left: 5%;">
</c:forEach>
<script src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/plugins/layer/layer.js"></script>
<script type="text/javascript">
function takeOrders(orderItemId) {
	$.get("/dispatcher/takeOrders?orderItemId="+orderItemId,function(data) {
		layer.msg(data.msg,{time:1500,icon:data.code},function(){
			if(data.code == 1){
				location.reload();
			}
		});
		
	})
}

</script>
</body>
</html>