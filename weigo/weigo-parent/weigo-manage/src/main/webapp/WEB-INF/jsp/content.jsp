<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
<meta charset="utf-8">
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

<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<div class="easyui-panel" title="Nested Panel" data-options="width:'100%',minHeight:500,noheader:true,border:false" style="padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:false" style="width:250px;padding:5px">
           <ul id="contentCategory" class="easyui-tree">
    </ul>
        </div>
        <div data-options="region:'center'" style="padding:5px">
            <table class="easyui-datagrid" id="contentList" data-options="toolbar:contentListToolbar,singleSelect:false,collapsible:true,pagination:true,method:'get',pageSize:20,url:'/content/query/list',queryParams:{categoryId:0}">
		    <thead>
		        <tr>
		            <th data-options="field:'id',width:30">ID</th>
		            <th data-options="field:'title',width:120">内容标题</th>
		            <th data-options="field:'subTitle',width:100">内容子标题</th>
		            <th data-options="field:'titleDesc',width:120">内容描述</th>
		            <th data-options="field:'url',width:60,align:'center',formatter:EGO.formatUrl">内容连接</th>
		            <th data-options="field:'pic',width:50,align:'center',formatter:EGO.formatUrl">图片</th>
		            <th data-options="field:'pic2',width:50,align:'center',formatter:EGO.formatUrl">图片2</th>
		            <th data-options="field:'created',width:130,align:'center',formatter:EGO.formatDateTime">创建日期</th>
		            <th data-options="field:'updated',width:130,align:'center',formatter:EGO.formatDateTime">更新日期</th>
		        </tr>
		    </thead>
		</table>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	var tree = $("#contentCategory");
	var datagrid = $("#contentList");
	tree.tree({
		onClick : function(node){
			if(tree.tree("isLeaf",node.target)){
				datagrid.datagrid('reload', {
					categoryId :node.id
		        });
			}
		}
	});
});
var contentListToolbar = [{
    text:'新增',
    iconCls:'icon-add',
    handler:function(){
    	var node = $("#contentCategory").tree("getSelected");
    	if(!node || !$("#contentCategory").tree("isLeaf",node.target)){
    		$.messager.alert('提示','新增内容必须选择一个内容分类!');
    		return ;
    	}
    	TT.createWindow({
			url : "/manage/content-add"
		}); 
    }
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
    	var ids = TT.getSelectionsIds("#contentList");
    	if(ids.length == 0){
    		$.messager.alert('提示','必须选择一个内容才能编辑!');
    		return ;
    	}
    	if(ids.indexOf(',') > 0){
    		$.messager.alert('提示','只能选择一个内容!');
    		return ;
    	}
		TT.createWindow({
			url : "/manage/content-edit",
			onLoad : function(){
				var data = $("#contentList").datagrid("getSelections")[0];
				$("#contentEditForm").form("load",data);
				
				// 实现图片
				if(data.pic){
					$("#contentEditForm [name=pic]").after("<a href='"+data.pic+"' target='_blank'><img src='"+data.pic+"' width='80' height='50'/></a>");	
				}
				if(data.pic2){
					$("#contentEditForm [name=pic2]").after("<a href='"+data.pic2+"' target='_blank'><img src='"+data.pic2+"' width='80' height='50'/></a>");					
				}
				
				contentEditEditor.html(data.content);
			}
		});    	
    }
},{
    text:'删除',
    iconCls:'icon-remove',
    handler:function(){
    	var ids = TT.getSelectionsIds("#contentList");
    	if(ids.length == 0){
    		$.messager.alert('提示','未选中商品!');
    		return ;
    	}
    	$.messager.confirm('确认','确定删除ID为 '+ids+' 的内容吗？',function(r){
    	    if (r){
    	    	var params = {"ids":ids};
            	$.post("/content/delete",params, function(data){
        			if(data.code==1){
        				$.messager.alert('提示','删除内容成功!',undefined,function(){
        					$("#contentList").datagrid("reload");
        				});
        			}else{
        				$.messager.alert('提示','删除内容失败!',undefined,function(){
        					$("#contentList").datagrid("reload");
        				});
        			}
        		});
    	    }
    	});
    }
}];
$(function(){
	var oldMenuName = "";
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",
		onContextMenu: function(e,node){
            e.preventDefault();
            $(this).tree('select',node.target);
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        //加载完毕后全部展开
        onLoadSuccess:function(node,data){
            var t = $(this);
            if(data){
                $(data).each(function(index,d){
                    if(this.state == 'closed'){
                       t.tree('expandAll');
                    }
                });
            }
        },
        onBeforeEdit:function(node){
        	oldMenuName=node.text;
        },
        onAfterEdit : function(node){
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.code==1){
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id
            			});
        				$.messager.alert('提示','创建'+node.text+' 分类成功!');
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        				_tree.tree("remove",node.target);
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text},function(data){
        			if(data.code==1){
        				$.messager.alert('提示','修改'+node.text+' 分类成功!');
        			}else{
        				$.messager.alert('提示','修改'+node.text+' 分类失败!');
        				_tree.tree("update",{
            				target : node.target,
            				text:oldMenuName
            			});
        			}
        		});
        	}
        }
	});
});
function menuHandler(item){
	var tree = $("#contentCategory");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',
                id : 0,
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete/",{parentId:node.parentId,id:node.id},function(data){
					if(data.code==1){
						tree.tree("remove",node.target);
					}else{
						$.messager.alert('提示','删除'+node.text+'分类失败!');
					}
				});	
			}
		});
	}
}
</script>

 
</body>
</html>