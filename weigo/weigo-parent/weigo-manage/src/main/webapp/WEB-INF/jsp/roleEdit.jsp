<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<head>
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
<title>添加角色</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" method="post" id="roleForm" action="${role!=null ?  'role/update.do':'role/insert.do'} ">
	<input type="hidden" name="roleId" value="${role.roleId }">
     
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input  type="text" class="input-text" value="${role.rolename }" placeholder="输入角色名称" id="rolename" name="rolename">
		</div>
	</div>
	
	
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色描述</label>
		<div class="formControls col-xs-8 col-sm-9">
			<textarea name="remark"  class="textarea"  placeholder="角色说明"  >${role.remark}</textarea>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>角色权限</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" name="permissionIds" id="permissionIds">
		<ul id="treeDemo" class="ztree"></ul>
		
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
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

var setting = {
		
		check: {enable: true},
		data: {
			simpleData: {
				enable: true,
				pIdKey:'parentId',
				idKey:'permissionId'
			}
		},
		
		async: {
			enable: true,
			url:"permission/getPermissionAll.do",
			dataFilter: filter
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};
    
    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		var permissionIds = "${role.permissionIds}";
		
		var permissionIdsArray = permissionIds.split(",");
		
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		
		for(var i=0;i<permissionIdsArray.length;i++){
			
			var node = treeObj.getNodeByParam("permissionId", permissionIdsArray[i], null);
			//console.log(node);
			
			//让其节点选中
			treeObj.checkNode(node, true, false);
		}
		
		
	}
    
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].open = true;
		}
		return childNodes;
	}

	function getCheckedData(){
		
		//获取zTree对象 ，（不是jquery的id选择的器，没有 #）
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		
		//获取所有选中的节点
		var nodes = treeObj.getCheckedNodes(true);
		//声明一个数组，存放节点的id
		var permissionIdsArr = [];
		for(var i=0;i<nodes.length;i++){
			var node = nodes[i];
			//将节点id添加到数组中
			permissionIdsArr.push(node.permissionId);
		}
		
		//console.log(permissionIdsArr);
		/*
			将数组转换成字符串 :
				1,toString方法自定将数组以逗号分割转换成字符串
				2,join("连接符")方法将数组以分割转换成字符串
		*/
		//var permissionIdsStr = permissionIdsArr.toString();
		var permissionIdsStr = permissionIdsArr.join(",");
		
		//将获取的数据设置表单的 权限数据对应的隐藏域
		$("#permissionIds").val(permissionIdsStr);		
	}

$(function(){
	
	$.fn.zTree.init($("#treeDemo"), setting);
	
	$("#roleForm").validate({
		//规则
		rules:{
			name:{
				required:true,
				minlength:4,
				maxlength:16,
				remote: {
				    url: "role/checkUsername.do", //后台处理程序
				    type: "post",                  //数据发送方式
				    dataType: "json",              //接受数据格式   
				    data: {                        //要传递的数据
				        name: function() {
				            return $("#name").val();
				        }
				    }
				}
			},
			expression:{
				required:true,
			}
		},
		//提示信息
		messages:{
			name:{
				required:"权限名不可为空",
				minlength:"最小四位",
				maxlength:"最长16位",
				remote:"该权限名已经存在"
			},
			expression:{
				required:"权限表达式不可为空",
			}
		},
			//校验成功回调函数
			submitHandler:function(form){	
				getCheckedData();
				$(form).ajaxSubmit(function(data){
					//console.log(data);
					
					layer.msg(data.msg,{time:1500,icon:data.code},function(){
						
						if(data.code == 1){
							//调用父页面的刷新表格方法（一定要先调用，再关闭）
							parent.refreshTable();
							//让父页面关闭所有弹出层
							parent.layer.closeAll();
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