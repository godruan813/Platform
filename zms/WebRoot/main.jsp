<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
	      function check() {
	      if(confirm("您确定要退出吗?")){
		        window.location.href = "logout.action";
	           }
	           }
         </script>
		<script type="text/javascript">
    </script>
    <title>测试管理系统</title>
    
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
      <div >
	         <jsp:include page="student_home.jsp"></jsp:include>
	         <div>
	         	<iframe src="${pageContext.request.contextPath}/getAllStudentByCondition.action" name="main" style="width:99%;height:88%"></iframe>
	         </div>
      </div>
      
  </body>
</html>
