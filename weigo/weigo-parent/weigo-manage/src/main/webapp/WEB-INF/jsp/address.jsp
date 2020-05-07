<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="addressForm" method="post" action="${address!=null ? 'manage/address/update':'/address/insert'}">
	<!-- 隐藏域，修改的时候根据id修改 -->
	<input type="hidden" name="id" value="${address.id}">
	<!-- 权限id数据 -->
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>收货地址</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  value="${address.addressname}" placeholder="请输收货地址" id="addressname" name="addressname">
		</div>
	</div>
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>收货人</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  value="${address.username}" placeholder="请输入收货人姓名" id="username" name="username">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>收货人电话</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  value="${address.phone}" placeholder="请输入角色名称" id="phone" name="phone">
		</div>
	</div>
	
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript">
	
	$(function(){
	/* 使用表单校验插件 */
		$("#addressForm").validate({
			//规则
			rules: {
				phone:{
					required:true,
					isMobile:true
				},
				addressname:{
					required:true,
					maxlength:25
				},
				username:{
					required:true,
					maxlength:5
				}
			},
			//校验失败提示消息
			messages:{
				phone:{
					required:"电话不能为空",
				},
				addressname:{
					required:"收货地址不能为空",
					maxlength:"不可超过25个字"
				},
				username:{
					required:"收货人不可为空",
					maxlength:"不可超过五个字符"
				}
			},
			//校验成功回调函数
			submitHandler:function(form){
				$(form).ajaxSubmit(function(data){
					layer.msg(data.msg,{time:1500,icon:data.code},function(){
						if(data.code == 1){
							 location.reload();
						}
					});
				});
			}
			
		});
		
		
	});
	
	


</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>