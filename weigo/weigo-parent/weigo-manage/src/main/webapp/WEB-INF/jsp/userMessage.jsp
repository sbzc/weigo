<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户信息</title>
<style type="text/css">
.panel{ background-color:#fff; border:solid 1px transparent}
	.panel-header{ border-bottom:solid 1px transparent; padding:8px 15px; font-size:14px; font-weight:700}/*面板标题*/
	.panel-body{ padding:15px}/*面板内容*/
	.panel-footer{background-color: #f5f5f5;border-top: 1px solid #ddd;padding:5px 20px}/*面板页脚*/
/*主要面板*/
.panel-primary{border-color:#5a98de}
.panel-primary > .panel-header{ border-color:#5a98de; background-color:#5a98de; color:#fff}
</style>
</head>
<body>
      <div>
          <div class="panel panel-primary">
	           <div class="panel-body">
	                                     姓名：${userMessage.username }<br><br>
	                                      角色：${userMessage.rolename }<br><br>
	                                     电话：${userMessage.phone }<br><br>
	                                     邮箱：${userMessage.email }<br><br>
	                                   被评价数：${userMessage.evaluateCount }<br><br>
	           </div>
          </div>
      
      </div>
</body>
</html>