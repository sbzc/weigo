<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
 <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="lib/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="lib/bootstrap/css/animate.min.css" rel="stylesheet">
<link href="lib/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>

</head>

<body class="gray-bg">
     <input type="hidden" id="addressId" name="startId" value=""/>
     <input type="hidden" id="orderItemId" name="orderItemId" value="${orderItemId}">

    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInUp">
                <ul class="notes">
                <c:forEach items="${addressList}" var="address">
                    <li >
                        <div id="${address.id }" onclick="selectThisAddress('${address.id}')" style="background-color:white;">
                            <small>
                            ${address.updated }
                            </small>
                            <h4>广东工业大学华立学院</h4>
                            <p>${address.addressname }</p>
                             <span>姓名：${address.username }</span><br><br>
                            <span>电话：${address.phone }</span>
                            
                            <a href="javascript:void(0);" onclick="deleteAddress('${address.id}')" title="删除该地址"><i class="fa fa-trash-o " style="margin-right: 20px"></i></a>
                            <a href="/manage/showAddress?id=${address.id}" title="修改该地址"><i class="glyphicon glyphicon-pencil "></i></a>
                        </div>
                    </li>
                  </c:forEach>  
                    <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>操作</h4>
                            <p>一个人只能有5个地址：收货地址就是发货地址::黄色背景代表你选择的发货地址</p>
                            <a href="/manage/address" class="btn btn-sm btn-primary"
													>添加地址</a>
						  
				             <button id="submitFrom"  type="button" onclick="findDispatcher()" class="btn btn-sm btn-primary">招募</button>
                        </div>
                    </li>
                   
                </ul>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>
<script type="text/javascript">




function deleteAddress(id) {
	
	$.get("/manage/address/delete?id="+id,function(data) {
		layer.msg(data.msg,{time:1500,icon:data.code},function(){
			
			if(data.code == 1){
				parent.layer.closeAll();
			}
		});
	});     
}

function findDispatcher() {
	var addressId=$("#addressId").val();
	var orderItemId=$("#orderItemId").val();
	if(addressId==""){
		layer.tips('请选择发货地址!!!', '#submitFrom', {
			  tips: [1, 'red'],
			  time: 4000
			});
	}else{
		$.get("/logistics/insertLogistics?startId=" + addressId + "&orderItemId=" + orderItemId,function(data) {
			layer.msg(data.msg,{time:1500,icon:data.code},function(){
				
				if(data.code == 1){
					parent.layer.closeAll();
				}
			});
		});     
	}
}

//选择地址
function selectThisAddress(id) {
	
	var selectid=$("#addressId").val();
	if(selectid!=""&&selectid!=id){
		$("#"+selectid).css("background-color","white");
	}
	$("#addressId").val(id);
	$("#"+id).css("background-color","yellow");
}

</script>


</body>

</html>