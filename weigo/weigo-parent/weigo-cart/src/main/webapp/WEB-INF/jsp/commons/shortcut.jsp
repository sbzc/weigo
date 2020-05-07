<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<div id="shortcut-2013">
	<div class="w">
		<ul class="fl lh">
			<li class="fore1 ld"><a href="http://47.103.218.192:8082/portal/index">回到首页</a> </li>
		</ul>
		<ul class="fr lh">
			<li class="fore1">您好！<span style="color: red;"> <shiro:principal property="username"></shiro:principal> </span>&nbsp;&nbsp;欢迎来到微购</li>
			
			
		</ul>
		<span class="clr"></span>
	</div>
</div>