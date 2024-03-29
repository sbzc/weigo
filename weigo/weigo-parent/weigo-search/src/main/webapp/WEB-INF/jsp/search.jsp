<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
<base href="<%=basePath%>">
      <!-- Basic page needs
         ============================================ -->
      <title>微购商城:${query}</title>
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
      <link href="css/themecss/so_sociallogin.css" rel="stylesheet">
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

<body class="res layout-1">
    
    <div id="wrapper" class="wrapper-fluid banners-effect-10">
    

    <!-- Header Container  -->
    <jsp:include page="commons/head.jsp"></jsp:include>
    <!-- //Header Container  -->
           
   
	<!-- Main Container  -->
	<div style="height: 20px">
	</div>
	
	<div class="container product-detail">
		<div class="row">
			<div id="content" class="col-md-12 col-sm-12 col-xs-12">
				<div class="products-category">
					<div class="products-category">
						<div class="product-filter filters-panel">
							<div class="row">
								<div class="col-sm-2 view-mode hidden-sm hidden-xs">
									<div class="list-view">
										<button class="btn btn-default grid active" data-view="grid" data-toggle="tooltip"  data-original-title="Grid"><i class="fa fa-th"></i></button>
                                        <button class="btn btn-default list" data-view="list" data-toggle="tooltip" data-original-title="List"><i class="fa fa-th-list"></i></button>
									</div>
								</div>
							<div>
							<span>搜索到了：</span><span style="color: red;">${count }</span>&nbsp;条记录
							</div>
							
							</div>
						</div>
						<div class="products-list grid row number-col-3 so-filter-gird">
					<c:forEach items="${itemList }" var="item" >
							<div class="product-layout  col-lg-4 col-md-4 col-sm-6 col-xs-6">
								<div class="product-item-container">
									<div class="left-block">
										<div class="product-image-container  second_img " style="height: 350px">
											<a href="http://47.103.218.192:8082/item/showProductMain/${item.id }.html" title="${item.title }">
											<img style="height: 100%" src="${item.images[0]} " alt="${item.title }" title="${item.title }">
											
											</a>
										<!-- 	<div class="box-label">
												<span class="label-product label-sale">
													Sale
												</span>
											</div>  -->
										</div>
										
									</div>
									<div class="right-block">
										<div class="caption">
											<h4 style="height: 40px"><a href="http://47.103.218.192:8082/item/showProductMain/${item.id }.html" title="查看详情" target="_self">
											${item.title }
																											</a></h4>
											<div class="total-price">
												<div class="price price-left">
													<span class="price-new">￥${item.price/100 } </span> <span class="price-old">￥${item.price/100+100 } </span>
												</div>
												<div class="price-sale price-right">
													<span class="discount">
														${item.username}
													</span>
												</div>
											</div>
											<div class="description item-desc hidden">
												<p>${item.sellPoint} </p>
											</div>
											<div class="list-block hidden">
												<button class="addToCart" type="button" data-toggle="tooltip" title="" onclick="cart.add('${item.id}');" data-original-title="Add to Cart "><span>Add to Cart </span></button>
											</div>
										</div>
										<div class="button-group">
										<a class="btn-button btn-quickview quickview quickview_handler" href="http://47.103.218.192:8082/item/showProductMin/${item.id }.html" title="Quick View" data-title="Quick View" data-fancybox-type="iframe">
											<i class="fa fa-search"></i>
										</a>
											<button class="addToCart btn-button" type="button" data-toggle="tooltip" title="" onclick="cart.add('${item.id}');" data-original-title="Add to Cart"><span class="hidden">加入购物车 </span></button>
										</div>
									</div>
								</div>
							</div>
								
								
							</c:forEach>
							<input  type="hidden" id="itemNum"  value="1">
						   <input type="hidden" id="searchQuery" value="${query}">
						   <input type="hidden" id="searchPage" value="${page}">
						   
							
						</div>
						
						<div id="example" style="text-align: center"> <ul id="pageLimit"></ul> </div>
					</div>
					
				</div>
			</div>
			
		</div>
	</div>
		
	<!-- //Main Container -->
	   
   

    <!-- Footer Container -->
    <footer class="footer-container typefooter-1">

		<div class="footer-has-toggle" id="collapse-footer">
			<div class="so-page-builder">
				<div class="container-fluid page-builder-ltr">
					<div class="row row_sh6r  footer--top  row-color ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_971a  float_none container">

							<div class="row row_dmol  ">

								<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 col_hcbx block--newletter">

									<div class="module news-letter">
										<div class="so-custom-default newsletter" style="width:100%     ; background-color: #f0f0f0 ; ">

											<div class="btn-group title-block">
												<div class="popup-title page-heading">
													<i class="fa fa-paper-plane-o"></i> Sign up to Newsletter
												</div>
												<div class="newsletter_promo">And receive <span>$29</span>coupon for first shopping</div>
											</div>
											<div class="modcontent block_content">
												<form method="post" id="signup" name="signup" class="form-group form-inline signup send-mail">
													<div class="input-group form-group required">
														<div class="input-box">
															<input type="email" placeholder="Your email address..." value="" class="form-control" id="txtemail" name="txtemail" size="55">
														</div>
														<div class="input-group-btn subcribe">
															<button class="btn btn-primary" type="submit" onclick="return subscribe_newsletter();" name="submit">
																<i class="fa fa-envelope hidden"></i>
																<span>Subscribe</span>
															</button>
														</div>
													</div>
												</form>

											</div>
											<!--/.modcontent-->

										</div>

									</div>

								</div>
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 col_h1e7 block--social">
									<div class="footer-social">
										<h3 class="block-title">Follow us</h3>
										<div class="socials">

											<a href="https://www.facebook.com/SmartAddons.page" class="facebook" target="_blank"><i class="fa fa-facebook"></i>
												<p>on</p>
												<span class="name-social">Facebook</span>
											</a>

											<a href="https://twitter.com/smartaddons" class="twitter" target="_blank">
												<i class="fa fa-twitter"></i>
												<p>on</p>
												<span class="name-social">Twitter</span>
											</a>
											<a href="https://plus.google.com/u/0/+SmartAddons-Joomla-Magento-WordPress/posts" class="google" target="_blank">
												<i class="fa fa-google-plus"></i>
												<p>on</p>
												<span class="name-social">Google +</span>
											</a>

											<a href="#" class="dribbble" target="_blank"><i class="fa fa-dribbble" aria-hidden="true"></i></a>

											<a href="#" class="instagram" target="_blank">
												<i class="fa fa-instagram" aria-hidden="true"></i>
												<p>on</p>
												<span class="name-social">Instagram</span>
											</a>

										</div>
									</div>

								</div>
							</div>

						</div>

					</div>
				</div>

				<div class="container-fluid page-builder-ltr">
					<div class="row row_z1do  footer--center  row-color ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_x6fe  float_none container">

							<div class="row row_wprs  ">

								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_yb5e footer--link">
									<h3 class="title-footer">
										spa & massage
									</h3>
									<ul class="links">
										<li>
											<a href="#">Makeup</a>
										</li>
										<li>
											<a href="#"> Skin Care</a>
										</li>
										<li>
											<a href="#"> Hair Care</a>
										</li>
										<li>
											<a href="#"> Tools & Accessories</a>
										</li>
										<li>
											<a href="#"> Perfume & Cologne</a>
										</li>
										<li>
											<a href="#"> More to Explore</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_1tke footer--link">
									<h3 class="title-footer">
										fashion & accessories
									</h3>
									<ul class="links">
										<li>
											<a href="#">Clothing</a>
										</li>
										<li>
											<a href="#"> Shoes</a>
										</li>
										<li>
											<a href="#"> Jewelry</a>
										</li>
										<li>
											<a href="#"> Watches</a>
										</li>
										<li>
											<a href="#"> Handbags & Walletss</a>
										</li>
										<li>
											<a href="#"> Accessories</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_ldmi footer--link">

									<h3 class="title-footer">
										travel & vacation
										</h3>
									<ul class="links">
										<li>
											<a href="#">Top 10 Hotels</a>
										</li>
										<li>
											<a href="#"> Travel Tour</a>
										</li>
										<li>
											<a href="#"> Bundle Deal</a>
										</li>
										<li>
											<a href="#"> Top Fights</a>
										</li>
										<li>
											<a href="#"> Vacation Retals</a>
										</li>
										<li>
											<a href="#"> Cruise</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_uoai footer--link">
									<h3 class="title-footer">
										electronics
										</h3>
									<ul class="links">
										<li>
											<a href="#">Home Audio & Theater</a>
										</li>
										<li>
											<a href="#"> Camera</a>
										</li>
										<li>
											<a href="#"> Photo & Video</a>
										</li>
										<li>
											<a href="#"> Cell Phones</a>
										</li>
										<li>
											<a href="#"> Headphones</a>
										</li>
										<li>
											<a href="#"> Video Games</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_77lh footer--link">
									<h3 class="title-footer">
										sport & Entertaiment
										</h3>
									<ul class="links">
										<li>
											<a href="#">Yoga & Fitness</a>
										</li>
										<li>
											<a href="#"> Games</a>
										</li>
										<li>
											<a href="#"> Cinema</a>
										</li>
										<li>
											<a href="#"> Studio</a>
										</li>
										<li>
											<a href="#"> Music</a>
										</li>
										<li>
											<a href="#"> Shopping</a>
										</li>
									</ul>

								</div>
							</div>

						</div>

					</div>
				</div>

				<div class="container-fluid page-builder-ltr">
					<div class="row row_mvtd  footer--center2  row-color ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_6fdl  float_none container">

							<div class="row row_hwmc  ">

								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_6ps1 footer--link">

									<h3 class="title-footer">
										food & restaurant
									</h3>
									<ul class="links">
										<li>
											<a href="#">Fast Food</a>
										</li>
										<li>
											<a href="#"> Buffet</a>
										</li>
										<li>
											<a href="#"> Café</a>
										</li>
										<li>
											<a href="#"> Drink</a>
										</li>
										<li>
											<a href="#"> Cake</a>
										</li>
										<li>
											<a href="#"> Restaurant</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_qprt footer--link">

									<h3 class="title-footer">
										health & beauty
									</h3>
									<ul class="links">
										<li>
											<a href="#">Makeup</a>
										</li>
										<li>
											<a href="#"> Skin Care</a>
										</li>
										<li>
											<a href="#"> Hair Care</a>
										</li>
										<li>
											<a href="#"> Tools & Accessories</a>
										</li>
										<li>
											<a href="#"> Perfume & Cologne</a>
										</li>
										<li>
											<a href="#"> More to Explore</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_jzhl footer--link">

									<h3 class="title-footer">
										Book Stationery
										</h3>
									<ul class="links">
										<li>
											<a href="#">Award Winners</a>
										</li>
										<li>
											<a href="#"> Bargain Books</a>
										</li>
										<li>
											<a href="#"> Best Books of 2016</a>
										</li>
										<li>
											<a href="#"> Best Books of the Month</a>
										</li>
										<li>
											<a href="#"> Books in Spanish</a>
										</li>
										<li>
											<a href="#"> Children Books</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_kefr footer--link">
									<h3 class="title-footer">
										Real house
									</h3>
									<ul class="links">
										<li>
											<a href="#">Makeup</a>
										</li>
										<li>
											<a href="#"> Skin Care</a>
										</li>
										<li>
											<a href="#"> Hair Care</a>
										</li>
										<li>
											<a href="#"> Tools & Accessories</a>
										</li>
										<li>
											<a href="#"> Perfume & Cologne</a>
										</li>
										<li>
											<a href="#"> More to Explore</a>
										</li>
									</ul>

								</div>
								<div class="col-lg-15 col-md-15 col-sm-4 col-xs-6 col_mtmx footer--link">
									<h3 class="title-footer">
										Mom & baby care
									</h3>
									<ul class="links">
										<li>
											<a href="#">Award Winners</a>
										</li>
										<li>
											<a href="#"> Bargain Books</a>
										</li>
										<li>
											<a href="#"> Best Books of 2016</a>
										</li>
										<li>
											<a href="#"> Best Books of the Month</a>
										</li>
										<li>
											<a href="#"> Books in Spanish</a>
										</li>
										<li>
											<a href="#"> Children Books</a>
										</li>
									</ul>

								</div>
							</div>

						</div>

					</div>
				</div>

				<div class="container-fluid page-builder-ltr">
					<div class="row row_qof8  footer--center3  row-color ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_up4v  float_none ">

							<div class="row row_fymn  ">

								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_1yf0">
									<div class="contactinfo">
										<h4 class="title-footer">Our Contact</h4>
										<p>They key is to have every key, the key to open every door. We will never see them</p>
										<div class="content-footer">
											<div class="address">
												<label><i class="fa fa-map-marker" aria-hidden="true"></i></label>
												<span>100 S Manhattan St, Amarillo, TX 79104, North America</span>
											</div>
											<div class="phone">
												<label><i class="fa fa-phone" aria-hidden="true"></i></label>
												<span>( +123 )4 567 890  -  ( +123 )4 567 899</span>
											</div>
											<div class="email">
												<label><i class="fa fa-envelope"></i></label>
												<a href="#">Contact@TopDeals.Com</a>
											</div>
										</div>
									</div>
									<div class="payment-html">
										<div>
											<a class="app-1" href="#">google store</a>
											<a class="app-2" href="#">apple store</a>
											<a class="app-3" href="#">window store</a>
										</div>
									</div>

								</div>
							</div>

						</div>

					</div>
				</div>

			</div>

		</div>

		<div class="footer-toggle hidden-lg hidden-md">
			<a class="showmore collapsed" data-toggle="collapse" href="#collapse-footer" aria-expanded="false" aria-controls="collapse-footer">
				<span class="toggle-more"><i class="fa fa-angle-double-down"></i>Show More</span>
				<span class="toggle-less"><i class="fa fa-angle-double-up"></i>Show less</span>
			</a>
		</div>

		<div class="footer-bottom ">
			<div class="container">
				<div class="row">
					<div class="col-md-7  col-sm-7 copyright">
						So TopDeal © 2016 - 2018. All Rights Reserved. Designed by <a href="http://www.bootstrapmb.com" target="_blank">OpenCartWorks.Com</a>

					</div>

					<div class="col-md-5 col-sm-5 paymen">
						<img src="image/catalog/demo/payment/payments-1.png" alt="imgpayment">
					</div>

				</div>
			</div>
		</div>

	</footer>
    <!-- //end Footer Container -->


</div>
<div class="back-to-top"><i class="fa fa-angle-up"></i></div>

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
<script type="text/javascript" src="js/themejs/nouislider.js"></script>

  <script src="/js/bootstrap-paginator.js"></script>
     <script>
     $(function() {
    	 var page = '${page}';
    	 var totalPages = '${totalPages}';
    	 var q ='${query}';
    	 
    	 $('#pageLimit').bootstrapPaginator({
    		
				currentPage: page,//当前的请求页面。
				totalPages: totalPages,//一共多少页。
				size:"normal",//应该是页眉的大小。
				bootstrapMajorVersion: 3,//bootstrap的版本要求。
				alignment:"right",
				numberOfPages:5,//一页列出多少数据。
				itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
					switch (type) {
					case "first": return "首页";
					case "prev": return "上一页";
					case "next": return "下一页";
					case "last": return "尾页";
					case "page": return page;}
				},
				onPageClicked:function(event,originalEvent,type,page){
					location.href="search.html?page="+page+"&q="+q;
				}
				
		});
	});
			
</script>


</body>
</html>