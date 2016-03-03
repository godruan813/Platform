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
		
		function gotoTask(){
			module==null?1:module;
			window.location.href='taskAll.jsp?module='+module;
		}
		function addToFavo(eA){
			var id=$(eA).attr('data');
			$.ajax({    
		        type:'post',        
		        url:'addFavo.action',    
		        data:'favo.caseIds='+id,    
		        cache:false,    
		        dataType:'json',    
		        success:function(data){
		       		if(data=='success'){
		       	        $(eA).html('从收藏夹删除&nbsp;&nbsp;');
		       		    $(eA).css('color','red');
						$(eA).attr('onclick','delFromFavo(this)');
		       		}
	        	}    
	  	  	}); 
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
		       	        $(eA).html('添加至收藏夹&nbsp;&nbsp;');
		       		    $(eA).css('color','blue');
						$(eA).attr('onclick','addToFavo(this)');
		       		}
	        	}    
	  	  	}); 
		}
		
		function modify(id,m){
			window.parent.location.href="gotoModifyCase.action?cases.id="+id+"&m="+m;
		}
		
		function deleteCase(id){
			if(confirm("您确定要删除吗?")){
				$.ajax({    
			        type:'post',        
			        url:'deleteCaseJ.action',    
			        data:'cases.id='+id,    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       		alert("删除成功");
			       		window.location.reload();   
		        	}    
		  	  	});   
	         }
		}
		
		$(function() {
			$("#allbox").change(function(){
				var hasSelected=$("#allbox").prop("checked");
				$("input[name=choice]").each(function(i){
					if(hasSelected){
						$(this).prop("checked","checked");;
					}
					else{
						$(this).removeAttr("checked");
					}
				});
			});
		});
		
		function deleteAll(){
			if(confirm("您确定要删除吗?")){
				var boxs=[];
				$("input[name=choice]").each(function(i){
					if($(this).prop("checked")){
						boxs.push($(this).val());
					}
				});
				var para=boxs.join(',');
				$.ajax({    
			        type:'post',        
			        url:'deleteAllCaseJ.action',    
			        data:'cid='+para,    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       		alert("删除成功");
			       		window.location.reload();   
		        	}    
		  	  	});  
			}
		}
		
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
	  		<li>自动化Case管理</li>
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
		<form class="form-inline definewidth m10" role="form"action="getAllfCaseByCondition.action" method="post">
		    		<div>
		    			用例id:<input type="text" name="cases.id" />
		    			&nbsp;&nbsp;
		    			<span><input type="submit" value="查询" class="btn btn-inverse"/></span>
		    			&nbsp;&nbsp;
		    			<span><input type="button" value="批量删除" class="btn btn-danger" onclick="deleteAll()"/></span>
		    	        <input type="hidden" name="cases.moduleId" value="<s:property value="#request.moduleId"/>"/>
		    	    </div>
    	</form>
    	
    	<div style="height:550px;border-bottom:1px solid #9A9A9A">
    	<table  id="caseTable" class="table table-striped table-hover m10" >
    		<tr>
    			<th style="width:5%"><input id="allbox" type="checkbox"/></th>
    			<th style="width:16%">用例id</th>
    			<th style="width:16%">标题</th>
    			<th style="width:16%">创建人</th>
    			<th style="width:16%">功能描述</th>
    			<th style="width:16%">最后修改时间</th>
    			<th style="width:20%">操作</th>
    		</tr>
    		<s:if test="#request.case.size()==0">
    			<tr><td colspan="10"style="text-align:center">暂无任何记录</td></tr>
    		</s:if>
    		<s:else>
    		<s:iterator value="#request.case" id="case" status="sta">
    		<tr>
    			<td>
    				<input type="checkbox" name="choice" value="<s:property value='#case.id'/>"/>
    			</td>
    			<td>
    				<s:property value="#case.id"/>
    			</td>
    			<td>
    				<s:property value="#case.title"/>
    			</td>
    			<td>
    				<s:property value="#case.creater"/>
    			</td>
    			<td>
    				<s:property value="#case.detail"/>
    			</td>
    			<td>
    			    <s:date name="#case.lastModify" format="yyyy-MM-dd HH:mm:ss" />
    			</td>
    			<td>
					 <input type="hidden" name="id" value="<s:property value="#case.id"/>"/>
    				 <div style="font-size:9">
 					 	<a onclick="modify('<s:property value="#case.id"/>','<s:property value="#case.moduleId"/>')">修改</a>
 					 	&nbsp;
 					 	<a onclick="deleteCase('<s:property value="#case.id"/>')">删除</a>
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
    			 			<a href='getAllfCaseByConditionP.action?&currentPage=1'>首页</a>
    			 		</li>
    			 		<c:choose>
    			 			<c:when test="${page.currentPage==1}">
    			 				<li><span>上一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllfCaseByConditionP.action?&currentPage=${page.currentPage-1}'>上一页</a></li>
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
										<li><a href='getAllfCaseByConditionP.action?&currentPage=${i}'>${i}</a></li>
	    			 				</c:otherwise>
    			 				</c:choose>
						</c:forEach>
    			 		<c:choose >
    			 			<c:when test="${page.currentPage>=page.totalPage}">
    			 				<li><span>下一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllfCaseByConditionP.action?&currentPage=${page.currentPage+1}'>下一页</a></li>
    			 			</c:otherwise>
    			 		</c:choose>
    			 		<li><a href='getAllfCaseByConditionP.action?&currentPage=${page.totalPage}'>尾页</a></li>
    			 		</ul>
    			 		</td>
    			 	</tr>
    	</table>

  </body>
</html>
