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
    
    <title>My JSP 'timer_add.jsp' starting page</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript">
				$(document).ready(function(){
				});
				
	function back(){
		window.location.href='getAllTimerByCondition.action';
	}
	
	function sub(){
		var tid=$('#tid').val();
		  $.ajax({
                 url:'modifyTimer.action',
                 data:$("#f1").serialize(),
                 type:"post",
                 success:function(data){//ajax返回的数据
                 	alert("操作成功");
                 	if(tid=='0'){
                 		back();	
                 	}
                 }
            }); 
	}
	
	function sel(){
		document.getElementById("bg").style.display ="block";  
	    document.getElementById("show").style.display ="block";
	    document.getElementById("toolbar").style.display ='block';  
	}
	
	function hidediv() {  
	  	 	 document.getElementById("bg").style.display ='none';  
	   		 document.getElementById("show").style.display ='none';  
	    	 document.getElementById("toolbar").style.display ='none';  
		}
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
		<form id="f1" class="form-signin" action="modifyTimer.action" method="post">
			<div>						
			</div>
			<input id="tid" type="hidden" name="timer.id" value="${timer.id}"/>
			<table class="table table-bordered table-hover m10">
				<tr>
					<td>
						任务名：
					</td>
					<td>
						<input class="input" type="text" name="timer.name" value="${timer.name}"onblur=""
							onFocus="" />
					</td>
				</tr>
				<tr>
					<td>
						时间表达式:
					</td>
					<td>
						<input class="input" type="text" name="timer.expression" onblur=""
							onFocus="" value="${timer.expression}"/>
					</td>
				</tr>
				<tr>
					<td>状态</td>
					<td>
						<s:select list="#{'1':'启用','0':'停用'}" name="timer.status"  theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
						用例id:
					</td>
					<td>
						<input class="input" type="text" name="timer.caseId" onblur="" value="${timer.caseId}"/>
						<span style="border:1px solid black;display:inline-block;width:50px;text-align:center;background:antiquewhite;cursor:pointer" onclick="sel()">选择</span>
					</td>
				</tr>
				<tr>
					<td>
						运行环境:
					</td>
					<td>
						<s:select list="#{'0':'线上环境','1':'仿真环境'}" name="timer.env" theme="simple"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						运行浏览器:
					</td>
					<td>
						<s:select list="#{'*firefox':'*firefox','*ie':'*ie'}" name="timer.browser" theme="simple"></s:select>
					</td>
				</tr>				
			</table>
			<div style="text-align:right;margin-right:100px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="提交" class="btn btn-primary" onclick="sub()">
				&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="back()"
				value="返回" class="btn btn-success">
			</div>
		</form>
	</div>
		<div id="bg"></div>  
			<div id="show">
				<a id="shut" onclick="hidediv()"></a>
				<iframe src="timer_autocase_ztree.jsp" style="width:100%;height:100%"></iframe>
		</div>
  </body>
</html>
