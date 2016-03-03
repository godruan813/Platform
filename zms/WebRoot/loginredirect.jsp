<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"
   + request.getServerName() + ":" + request.getServerPort()
   + path + "/";
%>
<html>
 <head>
  <script type="text/javascript">
   alert('请先登录');
   window.top.location.href="<%=basePath%>login.jsp";
  </script>
 </head>
</html>