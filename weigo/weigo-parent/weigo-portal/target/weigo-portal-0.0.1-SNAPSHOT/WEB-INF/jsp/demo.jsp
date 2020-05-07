<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>demo</title>

 <link href="/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
     <script src="/js/jquery-2.2.4.min.js"></script>
    <script src="/css/bootstrap/js/bootstrap.min.js"></script>
     <script src="/js/bootstrap-paginator.js"></script>
     <script>
     $(function() {
    	 
    	 $('#pageLimit').bootstrapPaginator({
				currentPage: 1,//当前的请求页面。
				totalPages: 20,//一共多少页。
				size:"normal",//应该是页眉的大小。
				bootstrapMajorVersion: 3,//bootstrap的版本要求。
				alignment:"right",
				numberOfPages:5,//一页列出多少数据。
				itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
					switch (type) {
					case "first": return "首页";
					case "prev": return "上一页";
					case "next": return "下一页";
					case "last": return "尾页";
					case "page": return page;}
				},
				onPageClicked:function(event, originalEvent, type,page){
					alert(page);
				}
				
		});
	});
			
</script>
</head>
<body>
<div id="example" style="text-align: center"> <ul id="pageLimit"></ul> </div>
</body>
</html>