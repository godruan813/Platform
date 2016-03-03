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
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">  -->
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/style.css" type="text/css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	
	
	<script type="text/javascript">
		function delTask(eA){
			var id=$(eA).attr('id');
				$.ajax({    
			        type:'post',        
			        url:'delSTask.action',    
			        data:'cases.id='+id,    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       		if(data=='success'){
							$(eA).parent().parent().parent().remove();
							$("#counts").html($("#counts").html()-1);
			       			//alert('任务删除成功');
			       		}
			       		else{
			       			alert('任务列表不存在该任务');
			       		}
		        	}    
		  	  	});   
		}
		
		function delAll(){
			$.ajax({    
		        type:'post',        
		        url:'delAllSTask.action',    
		        cache:false,    
		        dataType:'json',    
		        success:function(data){
		       		if(data=='success'){
						alert('已全部移除');
						module==null?1:module;
						window.location.href='taskAll.jsp?module='+module;
		       			//alert('任务删除成功');
		       			//$("#back").attr("onclick","history.back(-1)");
		       			//alert($("#back").attr("onclick"));
		       		}
	        	}    
	  	  	});   
		}
		
				
		function excute(){
				var cases=[];
				var input;
				var caseId=[];
				cases=document.getElementsByName("caseId");
				for(var i=0;i<cases.length;i++){
					var id=cases[i].value;
					caseId.push(id);
				}
				var ids=caseId.join(',');
				$.ajax({    
			        type:'post',        
			        url:'excute.action',    
			        data:'caseId='+ids+'&operate=start',    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       		var res=eval("(" + data + ")");
			       		var pid=res['pid'];
			       		var old=window.top.document.getElementById('pid').getAttribute('data');
			       		if(old==''){
			       			window.top.document.getElementById('pid').setAttribute('data',pid);
			       		}
			       		else{
			       			window.top.document.getElementById('pid').setAttribute('data',old+","+pid);
			       		}
		        	}    
		  	  	});
		}
		
		function getQueryString(name) { 
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
			var r = window.location.search.substr(1).match(reg); 
			if (r != null){ 
				return unescape(r[2]); 
			}
			return null; 
		} 
		
		/** 保存module参数*/
		function backToList(){
			module=(module==null?1:module);
			window.location.href='${pageContext.request.contextPath}/autocase_ztree.jsp?cases.module='+module;
		}

		var module=null;
		
		window.onload=function(){
			module=getQueryString("module");
		};
	</script>
	<style>
		    .tab{top:7px;text-align:center;width:105px;font-size:8px;border:1px solid black;height:25px;line-height:20px;display:inline-block;padding:3px 3px;position:relative;cursor:pointer;margin-right:-1px}
		   	.active{font-size:14px;top:0px;height:35px;line-height:25px;font-weight:bold;background:peachpuff;}
		    html {width:100%;height:100%;margin:0 auto;overflow:hidden;}
	        body {width:100%;height:100%;margin:0 auto;overflow:hidden;}
			iframe {border:none}
		    #caseTable tr th{font-size:12px}
		    #caseTable tr td{font-size:9px}
		    
				        
	</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="overflow:hidden">
  		<div>
	  		<ol class="breadcrumb">
	  			<li>任务管理</li>
			</ol>
  		</div>
   		<div style="text-align:right;margin-right:20px">
   	    	<span><input type="button" value="运行" onclick="excute()"class="btn btn-success"></span>
   	    	<span><input type="button" value="移除所有任务" onclick="delAll()"class="btn btn-warning"></span>
   	    	<span><input id="back" type="button" value="返回" onclick="backToList()"class="btn btn-default"></span>		    	    
   	    </div>
    	<table  id="caseTitle" class="table table-striped table-hover m10" style="margin-top:25px;margin-bottom:0">
    	    <tr>
    			<th style="width:12%">用例ID</th>
    			<th style="width:16%">类名</th>
    			<th style="width:16%">方法名</th>
    			<th style="width:16%">功能描述</th>
    			<th style="width:16%">开发人员</th>
    			<th style="width:20%">操作</th>
    		</tr>
    	</table>
    	<div style="height:550px;border-bottom:1px solid #9A9A9A;overflow:auto">
    	<table  id="caseTable" class="table table-hover m10" style="margin-top:-1px">
    		<s:if test="#session.sTask.size()==0">
    			<tr><td colspan="10"style="text-align:center">暂无任何记录</td></tr>
    		</s:if>
    		<s:else>
    		<s:iterator value="#session.sTask" id="cases" status="sta">
    		<tr>
    			<td style="width:12%">
    				<s:property value="#cases.id"/>
    			</td>
    			<td style="width:16%">
    				<s:property value="#cases.className"/>
    			</td>
    			<td style="width:16%">
    				<s:property value="#cases.methodName"/>
    			</td>
    			<td style="width:16%">
    				<s:property value="#cases.detail"/>
    			</td>
    			<td style="width:16%">
    				<s:property value="#cases.developer"/>
    			</td>
    			<td style="width:20%">
    				<div>
					 	<input type="hidden" name="caseId" value="<s:property value="#cases.id"/>"/>
  						<a id="<s:property value="#cases.id"/>" name="caseIds" onclick="delTask(this)" style="color:blue">从任务列表删除&nbsp;&nbsp;</a>
 					 	<a>其他</a>
  					 </div>
    			</td>    			
    		</tr>
    		</s:iterator>
    		 </s:else>
    		</table>
		</div>
    	<table>
    		<tr><td>共<span id="counts"><s:property value="#session.sTask.size()"/></span>条任务</td></tr>
    	</table>
  </body>
</html> 
