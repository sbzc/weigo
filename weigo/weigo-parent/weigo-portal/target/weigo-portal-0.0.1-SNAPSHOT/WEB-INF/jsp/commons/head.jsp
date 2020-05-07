<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
               <header id="header" class=" typeheader-1">
						<!-- Header Top -->
						<div class="header-top hidden-compact">
							 <div class="container">
									<div class="row">
										 <div class="col-lg-3 col-xs-6 header-logo ">
												<div class="navbar-logo">
													 <a href="/portal/index"><img src="/image/catalog/demo/logo/ego-logo.gif" alt="Your Store" width="147" height="40" title="Your Store"></a>
												</div>
										 </div>
										 <div class="col-lg-7 header-sevices">
												<div class="module html--sevices ">
													 <div class="clearfix sevices-menu" align="center">
															<h1>欢迎来到微购商城</h1>
													 </div>
												</div>
										 </div>
										 <div class="col-lg-2 col-xs-6 header-cart">
												<div class="shopping_cart">
													 <div id="cart" class="btn-shopping-cart">
															<a title="我的购物车" data-loading-text="Loading... " href="javascript:viod(0);" onclick="showMyCart()" class="btn-group top_cart dropdown-toggle" data-toggle="dropdown">
																		<span class="handle pull-left" ></span>
																		
																 
															</a>
															<ul class="dropdown-menu pull-right shoppingcart-box">
																 <li class="content-item" id="itemcatuser">
																		<script type="text/javascript">
																		
																		function deleteCartItem(id) {
																			//删除购物车
																			$.ajax({
																				url:'/checkLogin',
																				type:'post',
																				success:function(data){
																					if(data.code==1){
																						$.ajax({
																							url:'http://47.103.218.192:8084/cart/delete/'+id+'.action',
																							type:'post',
																							dataType:'jsonp',
																							success:function(data){
																								alert(data.msg)
																							}
																						});
																					}else{
																						location.href="isnologin";
																					}
																				}
																			});
																		}
																		//加载购物车
																		function showMyCart() {
																			$("#itemcatuser").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加载中。。。");
																			$.ajax({
																				url:'/checkLogin',
																				type:'post',
																				success:function(data){
																					if(data.code==1){
																						$.ajax({
																							url:'http://47.103.218.192:8084/cart/showCart.action',
																							type:'post',
																							dataType:'jsonp',
																							success:function(data){
																								$("#itemcatuser").html("");
																								var i;
																								var sellPoint;
																								if(data.length==0){
																									$("#itemcatuser").html("&nbsp;&nbsp;&nbsp;&nbsp;购物车空空如也 ...");
																								}
																								for(i=0;i<data.length;i++){
																									if(data[i].sellPoint==null){
																										sellPoint="没有描述";
																									}else{
																										sellPoint=data[i].sellPoint;
																									}
																									console.log(data[i].images[0]);
																									var tablestart=' <table class="table table-striped" style="margin-bottom:10px;"><tbody><tr><td class="text-center size-img-cart">';
																									var tbodyBlowa='<a href="product.html"><img src="'+data[i].images[0]+'"  alt="无法显示" title="'+data[i].title+'" class="img-thumbnail"></a> </td>';
																									var small='  <td class="text-left">'+sellPoint.substring(0,5)+'...</td>';
																									var tdnum='<td class="text-right">x'+data[i].num+'</td>';
																									var tdprivce='<td class="text-right">$'+(data[i].num*data[i].price)/100+'</td>';
																									var tddelete=' <td class="text-center"> <button type="button" title="删除" onclick="deleteCartItem('+data[i].id+')" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></button></td>';
																									var tableend='</tr></tbody></table>';
																									$("#itemcatuser").append(tablestart+tbodyBlowa+small+tdnum+tdprivce+tddelete+tableend);
																								}
																								
																							}
																						});
																					}else{
																						location.href="isnologin";
																					}
																				}
																			});
																			
																			
																			
																		}
																		
																		
																		</script>
																 </li>
																 <li>
																		<div class="checkout clearfix">
																			 <a href="http://47.103.218.192:8084/cart/cart.html" id="goMyCart"  class="btn btn-view-cart inverse">去购物车结算</a>
																		</div>
																 </li>
															</ul>
													 </div>   
												</div>
										 </div>
									</div>
							 </div>
						</div>
						<!-- //Header Top -->
						<!-- Header center -->
						<div class="header-center">
							 <div class="container">
									<div class="row">
										 <!-- Menuhome -->
										 <div class="col-lg-8 col-md-8 col-sm-1 col-xs-3 header-menu">
												<div class="megamenu-style-dev megamenu-dev">
													 <div class="responsive">
															<nav class="navbar-default">
																 <div class="container-megamenu horizontal">
																		<div class="navbar-header">
																			 <button type="button" id="show-megamenu" data-toggle="collapse" class="navbar-toggle">
																			 <span class="icon-bar"></span>
																			 <span class="icon-bar"></span>
																			 <span class="icon-bar"></span>
																			 </button>
																		</div>
																		<div class="megamenu-wrapper">
																			 <span id="remove-megamenu" class="fa fa-times"></span>
																			 <div class="megamenu-pattern">
																					<div class="container">
																						 <ul class="megamenu" data-transition="slide" data-animationtime="500">
																								
																								<li class="">
																									 <p class="close-menu"></p>
																									 <a href="http://47.103.218.192:8082/portal/index" class="clearfix">
																									 <strong>
																									首页
																									 </strong>
																									 </a>
																								</li>
																								<li class="">
																									 <p class="close-menu"></p>
																									 <a href="/dispatcher/show/list" class="clearfix">
																									 <strong>
																									 接单
																									 </strong>
																									 </a>
																								</li>
																								<li class="">
																									 <p class="close-menu"></p>
																									 <a href="http://47.103.218.192:8084/cart/cart.html" class="clearfix">
																									 <strong>
																									我的购物车
																									 </strong>
																									 </a>
																								</li>
																								<li class="">
																									 <p class="close-menu"></p>
																									 <a href="http://47.103.218.192:8080/manage/index" class="clearfix">
																									 <strong>
																									个人中心
																									 </strong>
																									 </a>
																								</li>
																						 </ul>
																					</div>
																			 </div>
																		</div>
																 </div>
															</nav>
													 </div>
												</div>
										 </div>
										 <!--Searchhome-->  
										 <div class="col-lg-4 col-md-4 col-sm-11 col-xs-9 header-search">
												<div id="sosearchpro" class="sosearchpro-wrapper so-search ">
													 <form method="GET" action="http://47.103.218.192:8083/search.html">
															<div id="search0" class="search input-group form-group">
																 <input class="autosearch-input form-control" type="text" size="50" autocomplete="off" placeholder="输入要查找的商品" name="q">
																 
																 <span class="input-group-btn">
																 <button type="submit" class="button-search btn btn-default btn-lg" ><i class="fa fa-search"></i><span class="hidden">Search</span></button>
																 </span>
															</div>
													 </form>
												</div>
										 </div>
									</div>
							 </div>
						</div>
						<!-- //Header center -->
						<div class="header-form hidden-compact">
							
							
							<div class="button-user">
								<div class="user-info asd">
									<a href="http://47.103.218.192:8080/manage/index">Login</a>
								</div>
							</div>
						</div>
				 </header>
				 <!-- //Header Container  -->

				<div class="modal fade in" id="so_sociallogin" tabindex="-1" role="dialog" aria-hidden="true" >
						<div class="modal-dialog block-popup-login">
								<a href="javascript:void(0)" title="Close" class="close close-login fa fa-times-circle" data-dismiss="modal"></a>
						</div>
				</div>
</body>
</html>