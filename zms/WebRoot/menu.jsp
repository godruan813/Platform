<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%  
  Object username = session.getAttribute("user");  
  if(null == username){  
      response.sendRedirect("login.jsp");  
  }  
%>  

<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	
	<script>

	    
	function disapp(){
		$("#swipe").stop(true);
		$("#swipe").css("right","2px");
		$("#swipe").animate({right:'-410px'},"slow");
		$("#swipe").val("dis");
	}
	
		BUI.use('common/main',function(){
	    	var config;
	    		$.ajax({    
			        type:'post',        
			        url:'getMenu.action',    
			        data:'',    
			        cache:false,    
			        dataType:'json',    
			        success:function(data){
			       	    config = eval("(" + data + ")");
			        	new PageUtil.MainPage({
	           				 modulesConfig : config
	        			});
			        },
		  	  	});    
	        //var config = [{id:'1',menu:[{text:'系统管理',items:[{id:'12',text:'机构管理',href:'${pageContext.request.contextPath}/getAllStudentByCondition.action'}]},{text:'xx管理',items:[{id:'13',text:'jj管理',href:'${pageContext.request.contextPath}/getAllStudentByCondition.action'}]}]}];
	    });
	var tasks;
	var running=[];
	
	$(function() {
  	  	getStatus();
  	  	setInterval("getStatus()",10000);
	});
	
	function getStatus(){
		var pids="<%=session.getAttribute("notice")%>";
		if(pids==''){
			
		}
		else{
		$.ajax({    
	        type:'post',        
	        url:'getRunning.action',    
	        data:'pids='+pids,    
	        cache:false,    
	        dataType:'json',    
	        success:function(data){
	        	 tasks = eval("(" + data + ")");
	        	 running=[];
				 for(i in tasks){
				 	var task=tasks[i];
				 	var computer=task['computer'];
				 	var stasks=task['sTasks'];
				 	for(j in stasks){
				 		var stask=stasks[j];
				 		var status=stask['status'];
				 		if(status!='运行中'){
					 		running.push(task);
				 		}
				 	}
				 }	
				 addNotice();
	        },
  	  	});
		}
	}
	
	function addNotice(){
		if(running.length>0){
			var notice=$("#notice");
			notice.children().remove();
			for(i in running){
				var task=running[i];
				var taskName=task['taskname'];
				var pid=task['id'];
				var sTask=task['sTasks'][0];
				var report=sTask['reportHref'];
				var child=$("<div></div>");
				//child.attr('id','parent'); 
				child.attr('class','chd'); 
				var a=$("<a href='"+report+"' target=_blank></a>");
				var content="你的任务名为<b style='font-size:15px'>["+taskName+"]</b>的任务已运行完毕,点击此处查看";
				a.html(content);
				var span=$("<span style='float:right;color:red' onclick=cancel(this,pid)>取消通知</span>");
				child.append(a);
				child.append(span);
				notice.append(child);
			}
				$("#swipe").animate({right:'2px'},"slow");
				 $("#swipe").val("play");
		}
	}
	
	function cancel(eS,pid){
		$(eS).parent().remove();
		var notice=$("#notice");
		var pids=$('#pid').attr('data');
		var arr=[];
		arr=pids.split(',');
		arr.splice(arr.indexOf(pid),1);
		var nd=arr.join(',');
		$('#pid').attr('data',nd);
		if(notice.children().size()<=0){
			disapp();
		}
	}
	
	function getRep(rep){
		window.open(rep);
	}
	</script>
</head>
<body>

<div class="header">
    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>
    <div class="dl-log">欢迎您,<span class="dl-log-user"> <s:property value="#session.user.nickname" /></span><a href="logout.action" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">信息管理</div></li>		
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">业务管理</div></li>

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
</div>
<div id="swipe" style="">
	<div
		style="width:100%; height:18px;font-size:14px;padding:5px 5px 0px 5px">
		<B>最新生成报告</B><a id="close" onclick="disapp()" style=""></a>
	</div>
	<div id="notice" style=""></div>
</div>
<div id="footer" style="width:100%;position:fixed;bottom:0;left:0;margin:0;background:#bbb;height:24px;line-height:24px;padding-top:5px;">
	<input type="hidden" id="pid" data="35,34">
	<table style="width:100%">
		<tr>
			<td style="width:50%"></td>
			<td style="text-align:right;padding-right:20px"><b>@Author-Rsd</b></td>
		</tr>
	</table>
</div>
</body>
</html>