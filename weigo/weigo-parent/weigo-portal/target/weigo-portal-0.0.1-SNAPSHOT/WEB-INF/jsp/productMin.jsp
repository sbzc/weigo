<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
<base href="<%=basePath%>">
    <!-- Basic page needs
         ============================================ -->
      <title>TopDeal</title>
      <meta charset="utf-8">
      <meta name="keywords" content="html5 template, best html5 template, best html template, html5 basic template, multipurpose html5 template, multipurpose html template, creative html templates, creative html5 templates" />
      <meta name="description" content="SuperMarket is a powerful Multi-purpose HTML5 Template with clean and user friendly design. It is definite a great starter for any eCommerce web project." />
      <meta name="author" content="Magentech">
      <meta name="robots" content="index, follow" />
      <!-- Mobile specific metas
         ============================================ -->
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
      <!-- Favicon
         ============================================ -->
      <link rel="shortcut icon" type="image/png" href="ico/favicon-16x16.png"/>
      <!-- Libs CSS
         ============================================ -->
      <link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/3.3.5/css/bootstrap.min.css">
      <link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
      <link href="js/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
      <link href="js/owl-carousel/owl.carousel.css" rel="stylesheet">
      <link href="css/themecss/lib.css" rel="stylesheet">
      <link href="js/jquery-ui/jquery-ui.min.css" rel="stylesheet">
      <link href="js/minicolors/miniColors.css" rel="stylesheet">
      <!-- Theme CSS
         ============================================ -->
      <link href="css/themecss/so_searchpro.css" rel="stylesheet">
      <link href="css/themecss/so_megamenu.css" rel="stylesheet">
      <link href="css/themecss/so-categories.css" rel="stylesheet">
      <link href="css/themecss/so-listing-tabs.css" rel="stylesheet">
      <link href="css/themecss/so-category-slider.css" rel="stylesheet">
      <link href="css/themecss/so-newletter-popup.css" rel="stylesheet">
      <link href="css/footer/footer1.css" rel="stylesheet">
      <link href="css/header/header1.css" rel="stylesheet">
      <link id="color_scheme" href="css/theme.css" rel="stylesheet">
      <link href="css/responsive.css" rel="stylesheet">
	  <link href="css/quickview/quickview.css" rel="stylesheet">
      <!-- Google web fonts
         ============================================ -->
      <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700" rel="stylesheet" type="text/css">
      <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet" type="text/css">
      <style type="text/css">
         body{font-family: Roboto, sans-serif;}
      </style>

</head>

<body class="loaded page-quickview">
    
   
    
    <div id="wrapper">
    
	<!-- Main Container  -->
	
	
	<div class="product-detail">
		<div id="product-quick" class="product-info">
			<div class="product-view row">
				<div class="left-content-product ">
					
						
							<div class="content-product-left class-honizol col-sm-5">
							
								<div class="large-image">
									 <!-- <div class="box-label">
										<span class="label-product label-sale">
										-30%
										</span> 
									   </div> -->
									<img class="product-image-zoom" src="${item.images[0] }" data-zoom-image="${item.images[0] }" title="官方认证">
								</div>
								<div id="thumb-slider" class="full_slider category-slider-inner products-list yt-content-slider" data-rtl="no" data-autoplay="no" data-pagination="no" data-delay="4" data-speed="0.6" data-margin="10" data-items_column0="3" data-items_column1="3" data-items_column2="3" data-items_column3="3" data-items_column4="2" data-arrows="yes" data-lazyload="yes" data-loop="no" data-hoverpause="yes">
										  <c:forEach items="${item.images }" varStatus="status" var="image">
										  <div class="image-additional">
											 <a data-index="${status.index}" class="img thumbnail " data-image="${image}" title="官方已认证">
											 <img src="${image }" title="官方认证" alt="加载失败">
											 </a>
										  </div>
										  </c:forEach>
								</div>
							</div>
						   <div class="content-product-right col-sm-7">
								<div class="title-product">
									<h1>${item.title}</h1>
								</div>
								<div class="row">
									<div class="col-sm-6 col-xs-12">
										<div class="box-review">
											<div class="rating">
												<div class="rating-box">
												<c:forEach begin="1" end="${item.roleId }">
													<span class="fa fa-stack"><i class="fa fa-star-o fa-stack-1x"></i></span>
													</c:forEach>													
												</div>
												<a class="reviews_button"  onclick="$('a[href=\'#tab-review\']').trigger('click'); return false;">${item.roleId }星用户</a>
											</div>
											
										</div>
										<div class="product_page_price price" >
											<span class="price-new"><span id="price-special">￥${item.price/100}</span></span>
											<span class="price-old" id="price-old">￥${item.price/100+100}</span>
										</div>
									</div>
									
								</div>
							 
								<div class="short_description form-group">
									<h3>OverView</h3><p>商品编号：${item.id }</p>
								</div> 
								<div id="product">
									<h3>发布者：${item.username }</h3>
									<div class="form-group required ">
										<div id="input-option224">
											<div class="radio  radio-type-button">
												<label>
													发布时间
												</label>
											</div>
											<div class="radio  radio-type-button">
												<label>
													<fmt:formatDate value="${item.updated }" pattern="yyyy-MM-dd HH:mm:ss"/>
												</label>
											</div>
										</div>
									</div>
								
								</div>
								<div class="box-cart clearfix">
									<div class="form-group box-info-product">
									   <div class="option quantity">
										  <div class="input-group quantity-control" unselectable="on" style="user-select: none;">
											 <input class="form-control" type="text" id="itemNum" name="quantity" value="1">
											 <input type="hidden" name="product_id" value="108">
											 <span class="input-group-addon product_quantity_down fa fa-caret-down"></span>
											 <span class="input-group-addon product_quantity_up fa fa-caret-up"></span>
										  </div>
									   </div>
									   <div class="cart">
										  <input type="button" onclick="cart.add('${item.id}');" value="加入购物车" data-loading-text="Loading..." id="button-cart" class="btn btn-mega btn-lg ">
									   </div>
									</div>
									<div class="clearfix"></div>
								 </div>
							
						   </div>
						
					
				</div>
			</div>
		</div>
	</div>
		
	<!-- //Main Container -->


</div>


<!-- End Color Scheme
============================================ -->



<!-- Include Libs & Plugins
============================================ -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="http://cdn.bootstrapmb.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/themejs/so_megamenu.js"></script>
<script type="text/javascript" src="js/owl-carousel/owl.carousel.js"></script>
<script type="text/javascript" src="js/slick-slider/slick.js"></script>
<script type="text/javascript" src="js/themejs/libs.js"></script>
<script type="text/javascript" src="js/unveil/jquery.unveil.js"></script>
<script type="text/javascript" src="js/countdown/jquery.countdown.min.js"></script>
<script type="text/javascript" src="js/dcjqaccordion/jquery.dcjqaccordion.2.8.min.js"></script>
<script type="text/javascript" src="js/datetimepicker/moment.js"></script>
<script type="text/javascript" src="js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/modernizr/modernizr-2.6.2.min.js"></script>
<script type="text/javascript" src="js/minicolors/jquery.miniColors.min.js"></script>
<script type="text/javascript" src="js/jquery.nav.js"></script>
<script type="text/javascript" src="js/quickview/jquery.magnific-popup.min.js"></script>
<!-- Theme files
 ============================================ -->
<script type="text/javascript" src="js/themejs/application.js"></script>
<script type="text/javascript" src="js/themejs/homepage.js"></script>
<script type="text/javascript" src="js/themejs/custom_h1.js"></script>
<script type="text/javascript" src="js/themejs/addtocart.js"></script>  
 <script type="text/javascript">
 /*    <div id="thumb-slider" class="full_slider category-slider-inner products-list yt-content-slider" data-rtl="no" data-autoplay="no" data-pagination="no" data-delay="4" data-speed="0.6" data-margin="10" data-items_column0="3" data-items_column1="3" data-items_column2="3" data-items_column3="3" data-items_column4="2" data-arrows="yes" data-lazyload="yes" data-loop="no" data-hoverpause="yes">
	  <c:forEach items="${item.images }" varStatus="status" var="image">
	  <div class="image-additional">
		 <a data-index="${status.index}" class="img thumbnail " data-image="${image}" title="官方已认证">
		 <img src="${image }" title="官方认证" alt="加载失败">
		 </a>
	  </div>
	  </c:forEach>
</div> */
    </script>



</body>
</html>