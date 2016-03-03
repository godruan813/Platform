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
		
		function addTask(eA){
			var id=$(eA).attr('id');
				$.ajax({    
			        type:'post',        
			        url:'addSTask.action',    
			        data:'cases.id='+id,    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       		if(data=='success'){
			       			//alert('任务添加成功');
			       		}
			       		else{
			       			alert('任务已被添加过');
						}
		       		    $(eA).html('从任务列表删除&nbsp;&nbsp;');
					    $(eA).css('color','red');
						$(eA).attr('onclick','delTask(this)');
		        	}    
		  	  	});   
	         }
	         
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
			       			//alert('任务删除成功');
			       		}
			       		else{
			       			alert('任务列表不存在该任务');
			       		}
		       		    $(eA).html('添加至任务列表&nbsp;&nbsp;');
		       		    $(eA).css('color','blue');
						$(eA).attr('onclick','addTask(this)');
		        	}    
		  	  	});   
	         }
		
		
		var module=null;

		function gotoTask(){
			module=(module==null?1:module);
			window.location.href='taskAll.jsp?module='+module;
		}
		
		
		function delFromFavo(eA){
			var id=$(eA).attr('data');
			$.ajax({    
		        type:'post',        
		        url:'deleteFavo.action',    
		        data:'favo.caseIds='+id,    
		        cache:false,    
		        dataType:'json',    
		        success:function(data){
		       		if(data=='success'){
		       			window.location.href='getAllFavoByCondition.action';
		       		}
	        	}    
	  	  	}); 
		}
		
		function backToList(){
			module=(module==null?'1':module);
			window.location.href='${pageContext.request.contextPath}/ztree.jsp?cases.module='+module;
		}
		
		function getQueryString(name) { 
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
			var r = window.location.search.substr(1).match(reg); 
			if (r != null){ 
				return unescape(r[2]); 
			}
			return null; 
		} 
		
		window.onload=function(){
			module=getQueryString("module");
		};
		
	</script>
	<style>

		#caseTable{border-bottom:1px solid #DDDDDD}
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
  
  <body>
  		<div>
	  		<ol class="breadcrumb">
	  		<li>收藏夹管理</li>
    		<s:iterator value="#request.module" id="m" status="sta">
    			<s:if test="#sta.last">
    				<li><b><s:property value='m'/></b></li>
    			</s:if>
    			<s:else>
    			    <li><s:property value='m'/></li>
    			</s:else>
			</s:iterator>
			</ol>
  		</div>
		<form class="form-inline definewidth m10" role="form"action="getAllFavoByCondition.action" method="post">
		    		<div>
		    			用例id:<input type="text" name="cases.qId"  value="<s:property value="#session.condition.qId"/>"/>
		    			<span><input type="submit" value="查询" class="btn btn-inverse"/></span>
		    	    	<span><input type="button" value="新增" onclick="show()"class="btn btn-success"></span>
						<span style="float:right;position:relative;top:-5px;margin-right:20px">
		    	       		<input type="button" value="任务列表" class="btn btn-info" onclick="gotoTask()" />
		    	       		&nbsp;
							<input id="back" type="button" value="返回" class="btn btn-default" onclick="backToList()">		    	    
		    	       	</span>
		    	        <input type="hidden" name="cases.module" value="<s:property value="#request.moduleId"/>"/>
		    	    </div>
    	</form>
    	
    	<div style="height:550px;border-bottom:1px solid #9A9A9A">
    	<table  id="caseTable" class="table table-striped table-hover m10" >
    		<tr>
    			<th style="width:16%">用例id</th>
    			<th style="width:16%">类名</th>
    			<th style="width:16%">方法名</th>
    			<th style="width:16%">功能描述</th>
    			<th style="width:16%">开发人员</th>
    			<th style="width:20%">操作</th>
    		</tr>
    		<s:if test="#request.case.size()==0">
    			<tr><td colspan="10"style="text-align:center">暂无任何记录</td></tr>
    		</s:if>
    		<s:else>
    		<s:iterator value="#request.case" id="case" status="sta">
    		<tr>
    			<td>
    				<s:property value="#case.id"/>
    			</td>
    			<td>
    				<s:property value="#case.className"/>
    			</td>
    			<td>
    				<s:property value="#case.methodName"/>
    			</td>
    			<td>
    				<s:property value="#case.detail"/>
    			</td>
    			<td>
    				<s:property value="#case.developer"/>
    			</td>
    			<td>
					 <input type="hidden" name="id" value="<s:property value="#case.id"/>"/>
    				 <div style="font-size:9">
    				 <s:set var="isExist" value="0"></s:set>  
 					 <s:iterator value="#session.sTask" id="task" status="sta">
 					 	<s:if test="#task.id==#case.id">
 					 		<s:set var="isExist" value="1"></s:set>  
 					 	</s:if>
 					 </s:iterator>
 					  	<s:if test="#isExist==1">
 					 		<a id="<s:property value="#case.id"/>" onclick="delTask(this)" style="color:red">从任务列表删除&nbsp;&nbsp;</a>
 					 	</s:if>
 					 	<s:else>
  							<a id="<s:property value="#case.id"/>" onclick="addTask(this)" style="color:blue">添加至任务列表&nbsp;&nbsp;</a>
 					 	</s:else>
 					 	<a data='<s:property value="#case.id"/>'onclick="delFromFavo(this)">从收藏夹删除</a>
  					 </div>
    			</td>    			
    		</tr>
    		</s:iterator>
    		 </s:else>
    		</table>
    		</div>
    		<table style="width:90%">
    		     <tr>
    			 	<td>共${page.totalPage}页,共${page.totalCount}条记录</td>
    			 	<td align="center" style="text-align:center;width:80%;height:80px">
    			 		<ul class="pagination">
    			 		<li>
    			 			<a href='getAllFavoByConditionP.action?&currentPage=1'>首页</a>
    			 		</li>
    			 		<c:choose>
    			 			<c:when test="${page.currentPage==1}">
    			 				<li><span>上一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllFavoByConditionP.action?&currentPage=${page.currentPage-1}'>上一页</a></li>
    			 			</c:otherwise>
    			 		</c:choose>
    			 		<c:choose>
    			 		    <c:when test="${page.totalPage>=10&&page.currentPage-5<=0}">
    			 		    	<c:set var="start" value="1"></c:set>
    			 		    	<c:set var="end" value="10"></c:set>
    			 			</c:when>
    			 		    <c:when test="${page.totalPage<=10&&page.currentPage-5<=0}">
    			 		   	    <c:set var="start" value="1"></c:set>
    			 		    	<c:set var="end" value="${page.totalPage}"></c:set>
    			 			</c:when>
    			 			<c:when test="${page.currentPage-5>=1&&page.currentPage+5<=page.totalPage}">
    			 				<c:set var="start" value="${page.currentPage-5}"></c:set>
    			 		    	<c:set var="end" value="${page.currentPage+5}"></c:set>
    			 			</c:when>
    			 			<c:when test="${page.currentPage+5>=page.totalPage}">
    			 				<c:set var="start" value="${page.totalPage-10}"></c:set>
    			 		    	<c:set var="end" value="${page.totalPage}"></c:set>
    			 			</c:when>
    			 		</c:choose>
    			 		<c:forEach var="i" begin="${start}" end="${end}" step="1">
    			 				<c:choose>
	    			 				<c:when test="${page.currentPage==i}">
	    			 					<li><span><b>${i}</b></span></li>
	    			 				</c:when>
	    			 				<c:otherwise>
										<li><a href='getAllFavoByConditionP.action?&currentPage=${i}'>${i}</a></li>
	    			 				</c:otherwise>
    			 				</c:choose>
						</c:forEach>
    			 		<c:choose >
    			 			<c:when test="${page.currentPage>=page.totalPage}">
    			 				<li><span>下一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllFavoByConditionP.action?&currentPage=${page.currentPage+1}'>下一页</a></li>
    			 			</c:otherwise>
    			 		</c:choose>
    			 		<li><a href='getAllFavoByConditionP.action?&currentPage=${page.totalPage}'>尾页</a></li>
    			 		</ul>
    			 		</td>
    			 	</tr>
    	</table>

  </body>
</html>
