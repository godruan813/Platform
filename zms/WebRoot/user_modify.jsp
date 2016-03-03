<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_add.jsp' starting page</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript">
				$(document).ready(function(){
				});
	</script>
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
	<div style="padding:10px 0px 0px 20px">
		<form class="form-signin" action="modifyStudent.action" method="post">
			<input type="hidden" name="user.id" value="${user.id}"/>
			<table class="table table-bordered table-hover m10">
				<tr>
					<td>
						id：
					</td>
					<td>
						<input class="input" type="text" name="user.id" value="${user.id}"onblur=""
							onFocus="" readonly="true" style="background: gainsboro"/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						用户名:
					</td>
					<td>
						<input class="input" type="text" name="user.username" onblur=""
							onFocus="" value="${user.username}"/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						昵称:
					</td>
					<td>
						<input class="input" type="text" name="user.nickname" onblur="" value="${user.nickname}"/>
						<span id="stAge1"></span>
					</td>
				</tr>
				<tr>
					<td>
						职位:
					</td>
					<td>
						<input class="input" type="text" name="user.position" onblur="" value="${user.position}"/>
						<span id="stAge1"></span>
					</td>
				</tr>
				<tr>
					<td>
						部门:
					</td>
					<td>
						<input class="input" type="text" name="user.dept" onblur="" value="${user.dept}"/>
						<span id="stAge1"></span>
					</td>
				</tr>
			</table>
			<div style="text-align:right;margin-right:100px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="提交" class="btn btn-primary">
				&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="javascript:window.location.href='getAllUserByCondition.action'"
				value="返回" class="btn btn-success">
			</div>
		</form>
	</div>
  </body>
</html>
