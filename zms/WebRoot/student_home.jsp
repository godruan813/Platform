<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'student_home.jsp' starting page</title>
     <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css1/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css1/bootstrap-responsive.mmin.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
		<div class="row-fluid">
			<div class="span12">
				<div class="head">
					<div class="headLeft">
					</div><br/>
					<div class="headRight">
						欢迎管理员：
						<font color="red">${user.name}</font>&nbsp;&nbsp;&nbsp;
						 <i class="icon-time"></i>&nbsp;&nbsp;<font id="today"></font>
					</div>
				</div>
			</div>
		</div> 
		<div class="row-fluid">
			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
					   <a class="brand" href="http://www.baidu.com" target="main"><i class="icon-home"></i>&nbsp;首页</a>
						<ul class="nav">
							<li><a href="getAllStudentByCondition.action" target="main"><i class="icon-pencil"></i>&nbsp;信息管理</a></li>
							<li><a href="showAllStudents"><i class="icon-book"></i>&nbsp;信息维护 </a></li>
							<li><a href="pre?method=preupdatetepwd"><i class=" icon-cog"></i>&nbsp;修改密码</a></li>
							<li><a onclick="check()"><i class="icon-user"></i>&nbsp;退出系统</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
