<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/common.css">

<body>
	<%
		String msg = (String) request.getAttribute("msg");
		if (msg == null) {
			msg = "";
		}
	%>
	<div class="panel window" style="display: block; width: 350px; left: 50%; top: 35%; z-index: 9001; cursor: default;margin-left:-175px;">
		<div class="panel-header panel-header-noborder window-header"
			style="width: 343px;">
			<div class="panel-title">欢迎使用驴妈妈测试平台管理系统</div>
			<div class="panel-tool"></div>
		</div>
		<div id="w"
			class="easyui-window panel-body panel-body-noborder window-body"
			title="" closable="false" collapsible="false" minimizable="false"
			maximizable="false"
			style="width: 341px; height: 171px; background: rgb(250, 250, 250);">
			<form id="ff" action="log.action" method="post">
				<div style="color:red;" class="message">
					<label><s:actionerror/></label>
				</div>
				<div style="margin:20px;">
					<span for="username" class="login">用户名：</span> <input id="username"
						class="easyui-validatebox validatebox-text validatebox-invalid"
						type="text" name="user.username" required="true">
				</div>
				<div style="margin:20px;">
					<span for="passport" class="login">密 码：</span> <input id="password"
						class="easyui-validatebox validatebox-text" type="password"
						name="user.password" required="true">
				</div>
				<div style="margin:20px;">
					<span class="login"> </span> <input type="submit" value="登录">
					<input type="reset" value="取消">
				</div>
			</form>
		</div>
	</div>
	<div class="window-shadow"
		style="display: block; left: 505.5px; top: 62px; z-index: 9000; width: 355px; height: 206px;"></div>
</body>
</html>
