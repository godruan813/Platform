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
			        data:'user.id='+id,    
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
		    <form class="form-inline definewidth m10" role="form"action="getAllStudentByCondition.action" method="post">
		    		<div>
		    			学号:<input type="text" name="student.sid"/>
		    			姓名:<input type="text" name="student.name"/>
		    			<span><input type="submit" value="查询" class="btn btn-inverse"/></span>
		    	    	<span><input type="button" value="新增" onclick="show()"class="btn btn-success"></span>
		    	    </div>
		    </form>
    	</div>
    	<div style="height:450px;border-bottom:1px solid black">
    	<table  class="table table-striped table-hover m10" >
    		<tr>
    			<th>id</th>
    			<th>name</th>
    			<th>gender</th>
    			<th>age</th>
    			<th>operation</th>
    		</tr>
    		<s:iterator value="#request.student" id="student" status="sta">
    		<tr>
    			<td>
    				<s:property value="#student.sid"/>
    			</td>
    			<td>
    				<s:property value="#student.name"/>
    			</td>
    			<td>
    				<s:if test='1==#student.gender'>男</s:if>
    				<s:else>女</s:else>
    			</td> 
    			<td>
    				 <s:property value="#student.age"/>
    			</td>  
    			<td>
    				 <a id="<s:property value="#student.pk"/>" href="gotoModifyStudent.action?student.sid=<s:property value="#student.sid"/>"onclick="">修改</a>
    				 <a id="<s:property value="#student.pk"/>" onclick="deleteUser(this.id)">删除</a>
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
    			 			<a href='getAllStudentByConditionP.action?&currentPage=1'>首页</a>
    			 		</li>
    			 		<c:choose>
    			 			<c:when test="${page.currentPage==1}">
    			 				<li><span>上一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllStudentByConditionP.action?&currentPage=${page.currentPage-1}'>上一页</a></li>
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
										<li><a href='getAllStudentByConditionP.action?&currentPage=${i}'>${i}</a></li>
	    			 				</c:otherwise>
    			 				</c:choose>
						</c:forEach>
    			 		<c:choose >
    			 			<c:when test="${page.currentPage>=page.totalPage}">
    			 				<li><span>下一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllStudentByConditionP.action?&currentPage=${page.currentPage+1}'>下一页</a></li>
    			 			</c:otherwise>
    			 		</c:choose>
    			 		<li><a href='getAllStudentByConditionP.action?&currentPage=${page.totalPage}'>尾页</a></li>
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
	<div style="padding:10px 0px 0px 20px">
		<form class="form-signin" action="addStudent.action" method="post">
			<table >
				<tr>
					<td>
						id：
					</td>
					<td>
						<input class="input" type="text" name="student.sid" onblur=""
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
							onFocus=""/>
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
						<input class="input" type="text" name="student.age" onblur=""/>
						<span id="stAge1"></span>
					</td>
				</tr>
			</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="提交" class="btn btn-primary">
			&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="hidediv()"
				value="返回" class="btn btn-success">
		</form>
	</div>
</div>
  </body>
</html>
