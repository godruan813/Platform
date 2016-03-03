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
		function show(){
				document.getElementById("bg").style.display ="block";  
			    document.getElementById("show").style.display ="block";
			$.ajax({    
		        type:'post',        
		        url:'getTasks.action', 
		        data:'pt.id=1',  
		        cache:false,    
		        dataType:'json',    
		        success:function(data){
					var jsonArr=eval("("+data+")");
					for(var o in jsonArr){
						var jsonObj=jsonArr[o];
						var creater=jsonObj['creater'];
						var ip=jsonObj['ip'];
						var startTimeObj=eval(jsonObj['startTime']);
						var endTimeObj=eval(jsonObj['endTime']);
						var startTime=startTime==null?"":getTime(startTimeObj);
						var endTime=endTime==null?"":getTime(endTimeObj);
					}
	        	}    
	  	  	}); 
		}
		function hidediv() {  
		    document.getElementById("bg").style.display ='none';  
		    document.getElementById("show").style.display ='none'; 
		}

		function getTime(timeObj){
			var year=1900+parseInt(timeObj['year']);
			var month=(1+parseInt(timeObj['month']))<10?'0'+(1+parseInt(timeObj['month'])):(1+parseInt(timeObj['month']));
			var date=timeObj['date']<10?'0'+timeObj['date']:timeObj['date'];
			var hours=timeObj['hours']<10?'0'+timeObj['hours']:timeObj['hours'];
			var minutes=timeObj['minutes']<10?'0'+timeObj['minutes']:timeObj['minutes'];
			var seconds=timeObj['seconds']<10?'0'+timeObj['seconds']:timeObj['seconds'];
			return year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
		}
				

	</script>
	<style>
		 #bg{ display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: black;  z-index:1001;  -moz-opacity: 0.7;  opacity:.70;  filter: alpha(opacity=70);}  
    	 #show{display: none;  position: absolute;  top: 12%;  left: 18%;  width: 58%;  height: 65%;  padding: 3px;  border: 8px solid #E8E9F7;  background-color: white;  z-index:1002;  overflow: auto;}  
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
		 <form class="form-inline definewidth m10" role="form" action="getAllPTaskByCondition.action" method="post">
		    		<div>
		    			创建者:<input type="text" name="tasks.creater"/>
		    			任务名:<input type="text" name="tasks.taskName"/>
		    			<span><input type="submit" value="查询" class="btn btn-inverse"/></span>
		    	    	<span><input type="button" value="新增" onclick="show()"class="btn btn-success"></span>
		    	    </div>
		    </form>
    	</div>
    	<div style="height:550px;border-bottom:1px solid black">
    	<table  id="taskTable" class="table table-striped table-hover m10" >
    		<tr>
    			<th style="width:16%">任务名</th>
    			<th style="width:5%">用例数量</th>
    			<th style="width:5%">机器IP</th>
    			<th style="width:5%">机器状态</th>
    			<th style="width:5%">创建者</th>
    			<th style="width:5%">运行环境</th>
    			<th style="width:5%">浏览器类型</th>
    			<th style="width:10%">执行时间</th>
    			<th style="width:20%">操作</th>
    		</tr>
    		<s:if test="#request.task.size()==0">
    			<tr><td colspan="10"style="text-align:center">暂无任何记录</td></tr>
    		</s:if>
    		<s:else>
    		<s:iterator value="#request.ptask" id="ptask" status="sta">
    		<tr>
    			<td>
    				<s:property value="#ptask.taskname"/>
    			</td>
    			<td>
    				<s:property value='#ptask.caseIds.split(",").length'/>
    			</td>

    			<td>
    				<s:property value="#ptask.computer.ip"/>
    			</td>
    			<td>
    				<s:property value="#ptask.computer.computerStatus"/>
    			</td>
    			<td>
    				<s:property value="#ptask.creater"/>
    			</td>
    			<td>
    				<s:if test="#ptask.env==1">
 					 		仿真环境
 					</s:if>
 					<s:elseif test="#ptask.env==0">
 					 		线上环境
 					</s:elseif>
 					 <s:elseif test="#ptask.env==2">
 					 		79环境
 					</s:elseif>
 				</td>
 			    <td>
    				<s:property value="#ptask.browser"/>
    			</td>
 				<td>
    				<s:date name="#ptask.opertime" format="yyyy-MM-dd HH:mm:ss" />
    			</td>
    			<td>
					 <input type="hidden" name="id" value="<s:property value="#ptask.id"/>"/>
    				 <div style="">
    					 <a onclick="show()">查看</a>
    					 &nbsp;&nbsp;
    					 <a onclick="show()">终止任务</a>
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
    			 			<a href='getAllPTaskByConditionP.action?&currentPage=1'>首页</a>
    			 		</li>
    			 		<c:choose>
    			 			<c:when test="${page.currentPage==1}">
    			 				<li><span>上一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllPTaskByConditionP.action?&currentPage=${page.currentPage-1}'>上一页</a></li>
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
										<li><a href='getAllPTaskByConditionP.action?&currentPage=${i}'>${i}</a></li>
	    			 				</c:otherwise>
    			 				</c:choose>
						</c:forEach>
    			 		<c:choose >
    			 			<c:when test="${page.currentPage>=page.totalPage}">
    			 				<li><span>下一页</span></li>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<li><a href='getAllPTaskByConditionP.action?&currentPage=${page.currentPage+1}'>下一页</a></li>
    			 			</c:otherwise>
    			 		</c:choose>
    			 		<li><a href='getAllPTaskByConditionP.action?&currentPage=${page.totalPage}'>尾页</a></li>
    			 		</ul>
    			 		</td>
    			 	</tr>
    	</table>
		<div id="bg"></div>  
		<div id="show">
				<a id="shut" onclick="hidediv()"></a>
				
		</div>
  </body>
</html>
