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
<meta charset="UTF-8">
<title>初始化系统</title>
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstarp-table/bootstrap-table.min.css" />
</head>
<body>
  <div align="center" style="padding-top: 30px">
   <div id="solrMessage" >
           
     </div>
   <div id="solrBuutonId" style="height: 100px">
           <button class="btn btn-warning btn-rounded"  onclick="restSolr()">初始化solr</button>
   </div>
   <div >
          <button class="btn btn-warning btn-rounded"  onclick="deleteRedis()">清空redis</button>
   </div>
  </div>
   
    <!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!-- 搜索框 -->
<script type="text/javascript">

function deleteRedis() {
	layer.confirm('确定清空缓存吗？',function(index){
	        $.get("/admin/redis/deleteAll",function(data){
			      layer.msg(data.msg,{time: 2000, icon:data.code});
		   });
	 });
}
function restSolr() {
	layer.confirm('确定初始化solr吗？',function(index){
		layer.close(index);
		$("#solrBuutonId").html('<img title="正在初始化solr" alt="正在初始化solr" src="/static/h-ui/images/loading/loading-b.gif" >');
		$.ajax({ 
			url:'http://47.103.218.192:8083/sorl/init', 
			type:'post', 
			async:false, 
			dataType:'jsonp', 
			success:function(data){ 
		            if(data.code==1){
		            	 $("#solrMessage").html('<label style="color:green;">'+data.msg+'</label>');
		            }else{
		            	 $("#solrMessage").html('<label style="color:red;">'+data.msg+'</label>');
		            }
		            $("#solrBuutonId").html('  <button class="btn btn-warning btn-rounded" onclick="restSolr()">初始化solr</button>');
		    }
		 
		});
		
		
		
	 });
}


</script>

</body>
</html>