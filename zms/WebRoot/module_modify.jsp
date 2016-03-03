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
    
    <title>My JSP 'module_add.jsp' starting page</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
		});
				
		function submitForm(act,id){
			var form=document.getElementById('moduleForm');
			form.action = act;
			form.submit();
			alert('操作成功');
			parent.location.href="${pageContext.request.contextPath}/module_ztree.jsp?module.id="+id;
			//parent.location.reload();
		}
		
		function submitDelete(id,module){
			var s=$("a[title='"+module+"']", window.parent.document);
			var length=s.next().length;
			if(length>0){
				if(window.confirm("该节点下含有子节点,是否全部删除", "删除确认")){

				}
				else{
					return;
				}
			}
			$.ajax({    
		        type:'post',        
		        url:'deleteModule.action',    
		        data:'module.id='+id,    
		        cache:false,    
		        dataType:'json',    
		        success:function(data){
		       		if(data=='success'){
		       			alert('删除成功');
		       			parent.location.href="${pageContext.request.contextPath}/module_ztree.jsp";
		       		}
	        	}    
	  	  	});   
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
		<form id="moduleForm"class="form-signin" action="modifyModule.action" method="post">
			<table class="table table-bordered table-hover m10">
				<tr>
					<td>
						节点id：
					</td>
					<td>
						<input class="input" type="text" name="module.id" value="${module.id}"onblur=""
							onFocus=""  style="background: gainsboro" readonly="true"/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						父节点id:
					</td>
					<td>
						<input class="input" type="text" name="module.parent" onblur=""
							onFocus="" value="${module.parent}"  style="background: gainsboro" readonly="true"/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						模块名称:
					</td>
					<td>
						<input class="input" type="text" name="module.name" onblur="" value="${module.name}"/>
						<span id="stAge1"></span>
					</td>
				</tr>
			</table>
			<div style="margin-right:50px;float:right">
				<input type="button" value="保存" class="btn btn-primary" onclick="submitForm('modifyModule.action','${module.id}');">
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="删除" class="btn btn-danger"  onclick="submitDelete(${module.id},'${module.name}');">
			</div>
		</form>
	</div>
  </body>
</html>
