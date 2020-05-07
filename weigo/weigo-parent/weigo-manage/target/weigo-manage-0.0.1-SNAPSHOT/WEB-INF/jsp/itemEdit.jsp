<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
<meta charset="utf-8">
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<!-- 引入ego -->
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="/css/egou.css" />
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
</head>
<body>
<div style="padding:10px 10px 10px 10px">
	<form id="itemeEditForm" class="itemForm" method="post">
		<input type="hidden" value="${item.id }" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            <a title="点击更换商品类别" href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<input type="hidden" name="cid" id="cid" style="width: 280px;"></input>	
	            </td>
	        </tr>
	        <tr>
	            <td>商品标题:</td>
	            <td><input class="easyui-textbox" type="text" value="${item.title }" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="easyui-textbox" name="sellPoint" value="${item.sellPoint }"  data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品价格:</td>
	            <td><input class="easyui-numberbox" type="text" value="${item.price }"  name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr>
	        <tr>
	            <td>库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" value="${item.num }" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	            	<div id="images" class="images">        		
	            	  	<ul>
	            	  				<c:forEach items="${item.images }" var="image">
	            	  				<li><a href="${image }" target="_blank">
	            	  				    <img src="${image }" width="80" height="50"></a>
	            	  				</li>
	            	  				</c:forEach>
	            	  	</ul>        	
	            	  			</div>
	                <input type="hidden" value="${item.image }" name="image"/>
	            </td>
	        </tr>
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc">${item.desc }</textarea>
	            </td>
	        </tr>
	        
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	var itemEditEditor ;
	$(function(){
		
		//实例化编辑器
		itemEditEditor = EGO.createEditor("#itemeEditForm [name=desc]");
         EGO.init({fun:function(node){
		}});
         var cidStr= "${item.cidStr }"; 
         var cid= "${item.cid }"; 
         //回显类目
         $(".itemCat").html(cidStr);
         $("#cid").val(cid);
	});
	function submitForm(){
		if(!$('#itemeEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		$("#itemeEditForm [name=price]").val(eval($("#itemeEditForm [name=priceView]").val()) * 100);
		itemEditEditor.sync();
		$.post("/rest/item/update",$("#itemeEditForm").serialize(), function(data){
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
</script>
</body>
</html>