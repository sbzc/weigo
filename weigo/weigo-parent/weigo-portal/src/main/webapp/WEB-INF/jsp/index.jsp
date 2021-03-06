<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
			<title>微购主页</title>
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
	 <body class="common-home res layout-1">
			<div id="wrapper" class="wrapper-fluid banners-effect-10">
				 <!-- Header Container  -->
				<jsp:include page="commons/head.jsp"></jsp:include>
				 <!-- Main Container  -->
				 <div id="content">
						<div class="custom-scoll hidden-sm hidden-md hidden-xs">
				<div class="custom-html">
					<div class="scoll-cate list_diemneo">
						<ul id="nav-scroll">
							<li class="neo1"><a href="#box-link1"><span>Hot deal</span></a></li>
							<li class="neo2"><a href="#box-link2"><span>Spa</span></a></li>
							<li class="neo3"><a href="#box-link3"><span>Fashion</span></a></li>
							<li class="neo4"><a href="#box-link4"><span>Travel</span></a></li>
							<li class="neo5"><a href="#box-link5"><span>Digital</span></a></li>
						
						</ul>
					</div>
				</div>
			</div>
						<div class="so-page-builder">
							 <div class="container page-builder-ltr">
									<div class="row row_a90w  row-style ">
										 <!-- Menu left-->
										 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 col_vnxd  menu-left">
												<div class="row row_f8gy  ">
													 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_gafz col-style megamenu-style-dev megamenu-dev">
															<div class="responsive">
																 <div class="so-vertical-menu no-gutter">
																		<nav class="navbar-default">
																			 <div class=" container-megamenu  container   vertical  ">
																					<div id="menuHeading">
																						 <div class="megamenuToogle-wrapper">
																								<div class="megamenuToogle-pattern">
																									 <div class="container">
																											<div><span></span><span></span><span></span></div>
																											<span class="title-mega">
																											商品分类 
																											</span>
																									 </div>
																								</div>
																						 </div>
																					</div>
																					
																					<div class="vertical-wrapper">
																						 <span id="remove-verticalmenu" class="fa fa-times"></span>
																						 <div class="megamenu-pattern">
																								<div class="container">
																									 <ul class="megamenu" data-transition="slide" data-animationtime="300">
																									 <c:forEach items="${portalMessage.cats }" var="cat">
																									 <c:if test="${ cat.childTbItemCat==null}">
																											<li class="item-vertical  style1">
																												 <p class="close-menu"></p>
																												 <a href="http://47.103.218.192:8083/search.html?q=${cat.name }" class="clearfix">
																												 <span>
																												 <strong><img src="image/catalog/demo/menu/icon/icon-6.png" alt="">${cat.name }</strong>
																												 </span>
																												 </a>
																											</li>
																									</c:if>
																									<c:if test="${cat.childTbItemCat!=null}">
																											<li class="item-vertical  vertical-style3 with-sub-menu hover">
																										<p class="close-menu"></p>
																										<a  href="http://47.103.218.192:8083/search.html?q=${cat.name }"  class="clearfix">
																										<span>
																										<strong><img src="image/catalog/demo/menu/icon/icon-2.png" alt="">${cat.name }</strong>
																										</span>
																										<b class="fa fa-caret-right"></b>
																										</a>
																										<div class="sub-menu" data-subwidth="65">
																										<div class="content">
																											<div class="row">
																											<div class="col-sm-5">
																												<div class="html item-1">
																												
																												<ul>
																												<c:forEach items="${cat.childTbItemCat}" var="catChild">
																													<li class=""><a  href="http://47.103.218.192:8083/search.html?q=${catChild.name }"  title="Hotel &amp; Resort">${catChild.name}</a></li>
																													</c:forEach>
																												</ul>
																												</div>
																											</div>
																											<div class="col-sm-7">
																												<div class="html ">
																												<div class="row img-banner">
																													<a href="#"><img src="image/catalog/demo/menu/ver-img-1.jpg" alt="banner"></a>
																												</div>
																												</div>
																											</div>
																											</div>
																										</div>
																										</div>
																									</li>
																						</c:if>
																								</c:forEach>
																											<li class="loadmore"><i class="fa fa-plus-square"></i><span class="more-view"> More Categories</span></li>
																									 </ul>
																								</div>
																						 </div>
																					</div>
																			 </div>
																		</nav>
																 </div>
															</div>
													 </div>
												</div>
										 </div>
										 <!--- SLider right-->  
										 <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 col_anla  slider-right">
												<div class="row row_ci4f  ">
													 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_dg1b block block_1">
															<div class="module sohomepage-slider so-homeslider-ltr  ">
																 <div class="modcontent">
																		<div id="sohomepage-slider1">
																			 <div id="sohomepage-sliderBigpic" class="so-homeslider yt-content-slider full_slider owl-drag" data-rtl="yes" data-autoplay="yes" data-autoheight="no" data-delay="4" data-speed="0.6" data-margin="10" data-items_column00="1" data-items_column0="1" data-items_column1="1" data-items_column2="1"  data-items_column3="1" data-items_column4="1" data-arrows="yes" data-pagination="yes" data-lazyload="yes" data-loop="yes" data-hoverpause="yes">
																			 
																			 <c:forEach items="${portalMessage.bigPics }" var="bigPic">
																					<div class="item">
																						 <a href="${bigPic.href}" title="${bigPic.alt}" target="_self">
																						 <img class="responsive" src="${bigPic.src}" alt="${bigPic.alt}">
																						 </a>
																						 <div class="sohomeslider-description">
																						 </div>
																					</div>
																				</c:forEach>	
																			 </div>
																		</div>
																 </div>
															</div>
													 </div>
													 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_hmsd block block_2">
															<div class="home1-banner-1 clearfix">
																 <div class="item-1 col-lg-6 col-md-6 col-sm-6 banners">
																		<div >
																			 <a title="${portalMessage.bigPicBolws[0].alt}" href="${portalMessage.bigPicBolws[0].href}">
																			 <img  src="${portalMessage.bigPicBolws[0].src}" alt="${portalMessage.bigPicBolws[0].alt}">
																			 </a>
																		</div>
																 </div>
																 <div class="item-2 col-lg-6 col-md-6 col-sm-6 banners">
																		<div>
																			 <a title="${portalMessage.bigPicBolws[1].alt}" href="${portalMessage.bigPicBolws[1].href}">
																			 <img  src="${portalMessage.bigPicBolws[1].src}" alt="${portalMessage.bigPicBolws[1].alt}">
																			 </a>
																		</div>
																 </div>
															</div>
													 </div>
												</div>
										 </div>
									</div>
							 </div>
							 <section id="box-link1" class="section-style">
									<div class="container page-builder-ltr">
										 <div class="row row-style row_a1">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_a1c  block block_3 title_neo1">
													 <div class="module so-deals-1tr home1_deals so-deals">
															<div class="head-title">
																 <h2 class="modtitle font-ct">
																		<span>Hot Deals</span>
																 </h2>
																 <div class="cs-item-timer">
																		<div class="Countdown-1"></div>
																 </div>
															</div>
															<div class="modcontent">
																 <div class="so-deal modcontent products-list grid clearfix clearfix preset00-3 preset01-3 preset02-2 preset03-2 preset04-1  button-type1  style2">
																		<div class="category-slider-inner products-list yt-content-slider" data-rtl="yes" data-autoplay="yes" data-autoheight="no" data-delay="4" data-speed="0.6" data-margin="30" data-items_column00="3" data-items_column0="3" data-items_column1="3" data-items_column2="2"  data-items_column3="2" data-items_column4="1" data-arrows="no" data-pagination="no" data-lazyload="yes" data-loop="yes" data-hoverpause="yes">
																			 <c:forEach items="${ portalMessage.items}" var="item" varStatus="status" >
																			 <div class="item">
																					<div class="transition product-layout">
																						 <div class="product-item-container ">
																								<div class="left-block so-quickview">
																									 <div class="image">
																											<a class="lt-image" href="/item/showProductMain/${item.id }.html" target="_self" title="${item.title}">
																											<img height="100%" src="${item.image}" alt="${item.title}">
																											</a>
																											<div class="text-location"><span>华立学院</span></div>
																									 </div>
																									 <div class="box-label">
																											<span class="label-product label-sale">${status.index+1 }</span>
																									 </div>
																									 <div class="button-group">
																											<div class="button-inner so-quickview">
																											<a class="btn-button btn-quickview quickview quickview_handler" href="/item/showProductMin/${item.id }.html" title="Quick View" data-title="Quick View" data-fancybox-type="iframe">
																											<i class="fa fa-search"></i>
																											</a>
																											<input  type="hidden" id="itemNum"  value="1">
																											<button class="addToCart btn-button" type="button" data-toggle="tooltip" title="" onclick="cart.add('${item.id}');" data-original-title="Add to cart">
																											<span class="hidden">Add to cart</span>
																											</button>
																											</div>
																									 </div>
																								</div>
																								<div class="right-block">
																									 <div class="caption">
																											<h4><a href="/item/showProductMain/${item.id }.html" title="查看详情" target="_self">
																											    <c:if test="${item.sellPoint!=null&&item.sellPoint.length()<=30}"> ${item.sellPoint} </c:if>
																											<c:if test="${item.sellPoint!=null&&item.sellPoint.length()>30}">${item.sellPoint.substring(0,30)} ...</c:if>
																											</a></h4>
																											<div class="total-price clearfix">
																											<div class="price price-left"><span class="price-new">${item.price/100}</span><span class="price-old">${item.price/100+100}</span></div>
																											<div class="price-sale price-right"><span class="discount 123">${item.username}</span></div>
																											</div>
																									 </div>
																								</div>
																						 </div>
																					</div>
																			 </div>
																	     </c:forEach>
																		</div>
																 </div>
															</div>
													 </div>
													 <div>
															<div class="home1-banner-2 clearfix">
																 <div class="item-1 col-lg-6 col-md-6 col-sm-6 banners">
																		      <a title="${portalMessage.hostItemBolws[0].alt}" href="${portalMessage.hostItemBolws[0].href}">
																			 <img  src="${portalMessage.hostItemBolws[0].src}" alt="${portalMessage.hostItemBolws[0].alt}">
																			 </a>
																 </div>
																 <div class="item-2 col-lg-6 col-md-6 col-sm-6 banners">
																		<div>
																			  <a title="${portalMessage.hostItemBolws[1].alt}" href="${portalMessage.hostItemBolws[1].href}">
																			 <img  src="${portalMessage.hostItemBolws[1].src}" alt="${portalMessage.hostItemBolws[1].alt}">
																			 </a>
																		</div>
																 </div>
															</div>
													 </div>
												</div>
										 </div>
									</div>
							 </section>
							 <section id="box-link2" class="section-style">
							 
									<div class="container page-builder-ltr">
										 <div class="row row-style row_a2">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col_1bi4  col-style block block_5 title_neo2">
													 <div class="module so-listing-tabs-ltr default-nav clearfix img-float label-1 home-lt1">
															<div class="head-title font-ct">
																 <h2 class="modtitle">Spa &amp; Massage</h2>
															</div>
															<div class="modcontent">
																 <div id="so_listing_tabs_1" class="so-listing-tabs first-load">
																		<div class="ltabs-wrap">
																			 <div class="ltabs-tabs-container" data-delay="300" data-duration="600" data-effect="starwars" data-ajaxurl="" data-type_source="0" data-lg="4" data-md="3" data-sm="3" data-xs="2" data-margin="0">
																					<!--Begin Tabs-->
																					<div class="ltabs-tabs-wrap">
																						 <span class="ltabs-tab-selected"></span>
																						 <span class="ltabs-tab-arrow">▼</span>
																						 <div class="item-sub-cat">
																								<ul class="ltabs-tabs cf">
																									 <li class="ltabs-tab tab-sel" data-category-id="1" data-active-content=".items-category-1"> <span class="ltabs-tab-label">最新商品</span> </li>
																									 <li class="ltabs-tab " data-category-id="2" data-active-content=".items-category-2"> <span class="ltabs-tab-label">送快递</span> </li>
																								
																								</ul>
																						 </div>
																					</div>
																					<!-- End Tabs-->
																			 </div>
										                                            
																			 <div class="wap-listing-tabs ltabs-items-container products-list grid">
																					<!--Begin Items-->
																					<div class="ltabs-items ltabs-items-selected items-category-1" data-total="16">
																						 <div class="ltabs-items-inner ltabs-slider">
																						 
																						 
																						  <c:forEach items="${ portalMessage.items}" var="item" varStatus="status" >
																						   
																						    <c:if test="${status.index%2==0}">
																							<div class="ltabs-item">
																							</c:if>
																								<div class="item-inner product-layout transition product-grid">
																									<div class="product-item-container" style="height: 435px">
																										<div class="left-block">
																										<div class="image product-image-container" style="height: 270px">
																											<a class="lt-image" href="/item/showProductMain/${item.id }.html" target="_self" title="${item.title}">
																											<img height="100%" src="${item.image}" alt="${item.title}">
																											</a>
																										</div>
																										<%-- <div class="box-label"><span class="label-product label-sale">${item.username}</span></div> --%>
																										</div>
																										<div class="right-block">
																										<div class="caption">
																											<h4><a href="/item/showProductMain/${item.id }.html" title="查看详情" target="_self">
																											    <c:if test="${item.sellPoint!=null&&item.sellPoint.length()<=30}"> ${item.sellPoint} </c:if>
																											<c:if test="${item.sellPoint!=null&&item.sellPoint.length()>30}">${item.sellPoint.substring(0,30)} ...</c:if>
																											</a></h4>
																											<div class="total-price clearfix">
																											<div class="price price-left"><span class="price-new">${item.price/100}</span><span class="price-old">${item.price/100+100}</span></div>
																											<div class="price-sale price-right"><span class="discount 123">${item.username}</span></div>
																											</div>
																										</div>
																										<div class="button-group">
																											<div class="button-inner so-quickview">
																											<a class="btn-button btn-quickview quickview quickview_handler" href="/item/showProductMin/${item.id }.html" title="Quick View" data-title="Quick View" data-fancybox-type="iframe">
																											<i class="fa fa-search"></i>
																											</a>
																											<input  type="hidden" id="itemNum"  value="1">
																											<button class="addToCart btn-button" type="button" data-toggle="tooltip" title="" onclick="cart.add('${item.id}');" data-original-title="Add to cart">
																											<span class="hidden">Add to cart</span>
																											</button>
																											</div>
																										</div>
																										</div>
																									</div>
																									</div>
																								 <c:if test="${status.index%2!=0}">
																							</div>
																							       </c:if>
																								
																							
																						  	 
																							</c:forEach>
																							
																							
																					</div>
																					<div class="ltabs-items items-category-2 grid" data-total="16">
																						 <div class="ltabs-loading"></div>
																					</div>
																					
																			 </div>
																		</div>
																 </div>
															</div>
													 </div>
												</div>
										 </div>
									</div>
							 </section>
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
																			 <a href="https://www.facebook.com/SmartAddons.page" class="facebook" target="_blank">
																					<i class="fa fa-facebook"></i>
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
			
			
	 </body>
</html>