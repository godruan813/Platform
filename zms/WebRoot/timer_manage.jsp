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
    
    <title>用户管理</title>
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
		function getStu(){
			$.ajax({    
	        type:'post',        
	        url:'json.action',    
	        data:'',    
	        cache:false,    
	        dataType:'json',    
	        success:function(data){
	        alert(data);    
        		}    
  	  		});    
		}
		function show(){
				document.getElementById("bg").style.display ="block";  
			    document.getElementById("show").style.display ="block";
			    document.getElementById("toolbar").style.display ='block';  
		}
		function hidediv() {  
	  	 	 document.getElementById("bg").style.display ='none';  
	   		 document.getElementById("show").style.display ='none';  
	    	 document.getElementById("toolbar").style.display ='none';  
		}
		
		function deleteUser(id){
			if(confirm("您确定要删除吗?")){
				$.ajax({    
			        type:'post',        
			        url:'deleteUserJ.action',    
			        data:'timer.id='+id,    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       		alert("删除成功");
			       		window.location.reload();   
		        	}    
		  	  	});   
	         }
		}
		
		function gotoModifyTimer(){
				window.location.href='gotoModifyTimer.action';
		}
	</script>
	<style>
		
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
		    <form class="form-inline definewidth m10" role="form"action="getAllTimerByCondition.action" method="post">
		    		<div>
		    		  	任务名:<input type="text" name="timer.name"/>
		    			&nbsp;&nbsp;&nbsp;&nbsp;
		    			<span><input type="submit" value="查询" class="btn btn-inverse"/></span>
		    	    	<span><input type="button" value="新增" onclick="gotoModifyTimer()"class="btn btn-success"></span>
		    	    </div>
		    </form>
    	</div>
    	<div style="height:450px;border-bottom:1px solid black">
    	<table  class="table table-striped table-hover m10" >
    		<tr>
    			<th style="width:5%">id</th>
    			<th style="width:15%">名称</th>
    			<th style="width:15%">时间公式</th>
    			<th style="width:10%">上次运行时间</th>
    			<th style="width:10%">下次运行时间</th>
    			<th style="width:5%">状态</th>
    			<th style="width:5%">运行环境</th>
    			<th style="width:15%">操作</th>
    		</tr>
    		<s:iterator value="#request.timer" id="timer" status="sta">
    		<tr>
    			<td>
    				<s:property value="#timer.id"/>
    			</td>
    			<td>
    				<s:property value="#timer.name"/>
    			</td>
    			<td>
    				<s:property value="#timer.expression"/>
    			</td>    			
    			<td>
    				<s:date name="#timer.lastexcute" format="yyyy-MM-dd HH:mm:ss" />
    			</td>  
    			<td>
    				<s:date name="#timer.nextexcute" format="yyyy-MM-dd HH:mm:ss" />
    			</td> 
    			<td>
    				<s:if test="#timer.status==1">
 					 		启用
 					</s:if>
 					<s:elseif test="#timer.status==0">
 					 		停用
 					</s:elseif>
    			</td>    
    			<td>
    				<s:if test="#timer.env==1">
 					 		仿真环境
 					</s:if>
 					<s:elseif test="#timer.env==0">
 					 		线上环境
 					</s:elseif>
 					 <s:elseif test="#ptask.env==2">
 					 		79环境
 					</s:elseif>
    			</td>      			  			 
    			<td>
    				 <a id="<s:property value="#timer.id"/>" href="gotoModifyTimer.action?timer.id=<s:property value="#timer.id"/>"onclick="">启用</a>
    				 <a id="<s:property value="#timer.id"/>" href="gotoModifyTimer.action?timer.id=<s:property value="#timer.id"/>"onclick="">修改</a>
    				 <a id="<s:property value="#timer.id"/>" onclick="deleteUser(this.id)">删除</a>
    			</td>    			
    		</tr>
    		</s:iterator>
    		</table>
    		</div>
    		<table style="width:90%">
    		     <tr>
    			 	<td>共${page.totalPage}页,共${page.totalCount}条记录</td>
    			 </tr>
    			 <tr>
    			 	<td align="center" style="text-align:center;width:80%;height:80px">
    			 		<ul class="pagination">
    			 		<li>
    			 			<a href='getAllUserByConditionP.action?&currentPage=1'>首页</a>
    			 		</li>
    			 		<c:choose>
    			 			<c:when test="${page.currentPage==1}">
    			 				<li><span>上一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllUserByConditionP.action?&currentPage=${page.currentPage-1}'>上一页</a></li>
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
										<li><a href='getAllUserByConditionP.action?&currentPage=${i}'>${i}</a></li>
	    			 				</c:otherwise>
    			 				</c:choose>
						</c:forEach>
    			 		<c:choose >
    			 			<c:when test="${page.currentPage>=page.totalPage}">
    			 				<li><span>下一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllUserByConditionP.action?&currentPage=${page.currentPage+1}'>下一页</a></li>
    			 			</c:otherwise>
    			 		</c:choose>
    			 		<li><a href='getAllUserByConditionP.action?&currentPage=${page.totalPage}'>尾页</a></li>
    			 		</ul>
    			 		</td>
    			 	</tr>
    	</table>
 <div id="bg"></div>  
 <div id="toolbar">	
<span>
<a id="shut" onclick="hidediv()"></a></span>
</div>  
 <div id="show" style="border-top:none;">
</div>
  </body>
</html>
