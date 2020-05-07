<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
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
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstarp-table/bootstrap-table.min.css" />



<title>用户管理</title>
</head>
<body>
<div class="pd-30">

	 <span id="toolbar" class="l">
	 <a href="javascript:;" onclick="permissionadd()" class="btn btn-primary radius">
	 <i class="Hui-iconfont">&#xe600;</i> 添加权限</a></span>	


     
  
  <table   id="dataTable" ></table>
  
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/bootstarp-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="lib/bootstarp-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">



$(function() {
	
	$('#dataTable').bootstrapTable({
		   method:'post',
		  url: 'permission/list.do',
		  pagination: true,
		  search: true,
			responseHandler: function(res) { 
				/*
					res: 后台分页对象PageInfo返回对应的json对象
					res.list : 结果集
					res.total : 总记录数
				*/
				var data =  {rows: res.list,total: res.total};
			
				return data;
			},
			pagination: true,
			toolbar: "#toolbar",//顶部显示的工具条（添加和批量删除的）
			contentType: 'application/x-www-form-urlencoded',//条件搜索的时候ajax请求给后台数据的数据类型（条件搜索post提交必须设置）
			search: true,//是否显示搜索框
			pageNumber: 1,//默认的页面 第一页
			pageSize: 10,//默认的每页条数
			pageList:[10,25,50,100],//每页能显示的条数
			sidePagination: "server",//是否是服务器分页，每次请求都是对应的10条数据，下一页发送ajax请求
			paginationHAlign: 'right', //底部分页条
			showToggle: true, //是否显示详细视图和列表视图的切换按钮
			sortable: true,
			sortOrder: "asc",                   //排序方式
			//showColumns: true,//是否显示 内容列下拉框
			//cardView: true, //是否显示详细视图
			//showColumns: true, //是否显示所有的列
			showRefresh: true, //是否显示刷新按钮
		  columns: [ //表格显示数据对应的表头设置，
				{checkbox: true},
				{field: 'permissionId',title: '编号'}, 
				{field: 'name',title: '权限名'}, 
				{field: 'type',title: '权限类型',formatter:myPermissionType},
				{field: 'expression',title: '权限表达式'},
				{field: 'url',title: '权限地址'},
				{field:'pName',title:"父权限"},
				{field:"permissionId",title:"操作",formatter:operationFormatter,align:'center'}
			],

	        queryParams: function(params) { 
				//此方法在用户分页或者搜索的时候回自动发送ajax请求调用，并把对应的参数传递给后台
				var jsonData =  {
					pageNum: params.offset / params.limit + 1,
					pageSize: params.limit, //页面大小
					keyword: params.search
				};
				
				return jsonData;
			}
		});
})
function myPermissionType(value,row,index,field) {
	if(value=="menu")
		return "<span style='color:red'>菜单权限</span>";
	return "普通权限";
}

function operationFormatter(value,row,index,field){
    //console.log(value,row,index,field);
	
	var html = "<span onclick='permissiondel("+value+")' style='color:red;cursor: pointer;' class='glyphicon glyphicon-trash'></span>&nbsp;&nbsp;";
	 html += "<span onclick='permissionedit("+value+")' style='color:blue;cursor: pointer;' class='glyphicon glyphicon-pencil'></span>";
	
	return html;
}


/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*权限-增加*/
function permissionadd(){
	layer_show("添加权限","permission/permissionEdit.do","900","");
}
/*权限-删除*/
function permissiondel(permissionId){ 
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'permission/delete.do?permissionId='+permissionId,
			dataType: 'json',
			success: function(data){
				//$(obj).parents("tr").remove();
				//alert(data.code)
				if(data.code==1){
					layer.msg(data.msg,{icon:6,time:1500});
					refreshTable();
				}else if(data.code==2){
					layer.msg(data.msg,{icon:0,time:2500});
				}else{
					layer.msg(data.msg,{icon:5,time:1500});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			}
		});		
	});
}

function refreshTable(){
	$("#dataTable").bootstrapTable("refresh");
}

/*权限-编辑*/
function permissionedit(permissionId){
	layer_show("权限编辑","permission/permissionEdit.do?permissionId="+permissionId,"900","");
}


</script>


</body>
</html>
    