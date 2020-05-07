<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
<base href="<%=basePath%>">

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" type="image/png" href="/static/favicon-16x16.png"/>

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<title>微购后台系统</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:void(0);">微购后台系统</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a style="color: red" href="http://47.103.218.192:8082/portal/index">回到商城首页</a>	
			</span> 
			
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
			     
					<li>
					欢迎你：<font color="red"><shiro:principal property="username"/></font>
					 </li>
				<li class="dropDown dropDown_hover">
					<a href="javascript:void(0);" class="dropDown_A"> <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:void(0);" onClick="myselfinfo()">个人信息</a></li>
						<li><a href="logout.do">退出</a></li>
				</ul>
			</li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
		<shiro:hasPermission name="item">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe620;</i> 商品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
							<li><a data-href="/manage/itemPage" data-title="商品列表" href="javascript:void(0)">商品列表</a></li>
							
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="user">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe62d;</i> 角色管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
							<li><a data-href="/manage/rolePage" data-title="角色列表" href="javascript:void(0)">角色列表</a></li>
					</ul>
				</dd>
			</dl>
             </shiro:hasPermission>
             
             <shiro:hasPermission name="permission">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe602;</i> 权限管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
							<li><a data-href="/manage/permissionPage" data-title="权限列表" href="javascript:void(0)">权限列表</a></li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<shiro:hasPermission name="user">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe60a;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						    <li><a data-href="/admin/user/userAdd" data-title="添加用户" href="javascript:void(0)">添加用户</a></li>
							<li><a data-href="/manage/userPage" data-title="用户列表" href="javascript:void(0)">用户列表</a></li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<shiro:hasPermission name="page">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe61d;</i> 系统设计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
							<li><a data-href="/manage/content" data-title="页面管理列表" href="javascript:void(0)">页面管理列表</a></li>
							<li><a data-href="/manage/catEdit" data-title="商品类目" href="javascript:void(0)">商品类目</a></li>
							<li><a data-href="/manage/solrAndRedis" data-title="初始化系统" href="javascript:void(0)">其他</a></li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<shiro:hasPermission name="chart">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe61c;</i> 数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
							<li><a data-href="/manage/userLoginVisitorPage" data-title="登录记录" href="javascript:void(0)">登录记录</a></li>
							<li><a data-href="/manage/visitorChart" data-title="页面访问" href="javascript:void(0)">页面访问</a></li>
							<li><a data-href="/manage/orderChart" data-title="订单分析" href="javascript:void(0)">订单数据</a></li>
							<li><a data-href="/manage/roleChart" data-title="角色分布" href="javascript:void(0)">角色分布</a></li>
							<li><a data-href="/manage/itemAddChart" data-title="添加商品趋势" href="javascript:void(0)">添加商品趋势</a></li>
							<li><a data-href="/manage/userChart" data-title="用户增加趋势" href="javascript:void(0)">用户增加趋势</a></li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<shiro:hasPermission name="seller">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe66a;</i> 
				商家管理<c:if test="${countMap.sellerOrderItemCount!=0}">&nbsp;&nbsp;<span class="label label-warning pull-right">${countMap.sellerOrderItemCount}</span></c:if> 
				<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
					<shiro:hasPermission name="/item/save">
						   <li><a data-href="/manage/itemAdd" data-title="发布商品" href="javascript:void(0)">发布商品</a></li>
					</shiro:hasPermission>
						   <li><a data-href="/manage/userItemPage" data-title="我的商品" href="javascript:void(0)">我的商品</a></li>
						   
							<li>
							<a data-href="/seller/OrderItem/list" data-title="订单列表" href="javascript:void(0)">订单列表<c:if test="${countMap.sellerOrderItemCount!=0}">&nbsp;&nbsp;<span  class="label label-warning pull-right">${countMap.sellerOrderItemCount}</span></c:if></a>
							</li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<shiro:hasPermission name="client">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe705;</i> 
				客户中心<c:if test="${countMap.clientOrderItemCount!=0}">&nbsp;&nbsp;<span class="label label-warning pull-right">${countMap.clientOrderItemCount}</span></c:if>  
				<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
							<li>
							<a data-href="/user/buyitem/list" data-title="我购买的商品" href="javascript:void(0)">我购买的商品<c:if test="${countMap.clientOrderItemCount!=0}">&nbsp;&nbsp;<span title="待评价的商品" class="label label-warning pull-right">${countMap.clientOrderItemCount}</span></c:if></a>
							
							</li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<shiro:hasPermission name="dispatcher">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe669;</i> 
				快递管理<c:if test="${countMap.shippingConut!=0}">&nbsp;&nbsp;<span class="label label-warning pull-right">${countMap.shippingConut}</span></c:if>
				<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						    <li>
						    <a data-href="/logistics/dispatcherOrder" data-title="物流订单" href="javascript:void(0)">物流订单<c:if test="${countMap.shippingConut!=0}">&nbsp;&nbsp;<span class="label label-warning pull-right">${countMap.shippingConut}</span></c:if></a>
						    
						    </li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
		</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="manage/welcome">首页</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="manage/welcome"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">

/*个人信息*/
function myselfinfo(){
	/*用户-编辑*/
	
		layer_show("个人信息","/user/myUserEdit","1000","500");
		 
	
}



</script> 

</body>
</html>