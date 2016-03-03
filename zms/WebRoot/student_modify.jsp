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
    
    <title>My JSP 'student_add.jsp' starting page</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript">
				$(document).ready(function(){
				  	var gender='${student.gender}';
				  	var rad=$("input[name='student.gender']");
				  	if(gender=='1'){
				  		$("input[name='student.gender'][value='1']").attr("checked","true");
				  	}
				  	else{
				  		$("input[name='student.gender'][value='0']").attr("checked","true");
				  	}
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
			<input type="hidden" name="student.pk" value="${student.pk}"/>
			<table class="table table-bordered table-hover m10">
				<tr>
					<td>
						id：
					</td>
					<td>
						<input class="input" type="text" name="student.sid" value="${student.sid}"onblur=""
							onFocus=""/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						name：
					</td>
					<td>
						<input class="input" type="text" name="student.name" onblur=""
							onFocus="" value="${student.name}"/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						gender：
					</td>
					<td>
						<input type="radio" name="student.gender" value="1" onblur=""/>
						男
						<input type="radio" name="student.gender" value="0" onblur=""/>
						女
						<span id="stSex1"></span>
					</td>
				</tr>
				<tr>
					<td>
						age：
					</td>
					<td>
						<input class="input" type="text" name="student.age" onblur="" value="${student.age}"/>
						<span id="stAge1"></span>
					</td>
				</tr>
			</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="提交" class="btn btn-primary">
			&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="javascript:window.location.href='getAllStudentByCondition.action'"
				value="返回" class="btn btn-success">
		</form>
	</div>
  </body>
</html>
