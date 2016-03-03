<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">  -->
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/style.css" type="text/css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/res/common.css" type="text/css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/ztree/jquery.ztree.excheck-3.5.js"></script>
<link
	href="${pageContext.request.contextPath }/jquery/ztree/zTreeStyle.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
html {
	width: 100%;
	height: 100%;
	margin: 0 auto;
	overflow: hidden;
}

body {
	width: 100%;
	height: 100%;
	margin: 0 auto;
	overflow: hidden;
}
</style>
<script type="text/javascript">
	// 初始化ztree菜单
	$(function() {
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : redirectMain
			}
		};
		// 基本功能菜单加载
		$.ajax({
			url : 'getZtree.action',
			type : 'POST',
			data :'',
			cache:false,  
			dataType : 'json',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#treeMenu"), setting, zNodes);
				var module=getQueryString("cases.module");
				if(module==null){
					module='1';
				}
				var iframe=document.getElementById('mainFrame');
				iframe.src="${pageContext.request.contextPath}/getAllCaseByCondition.action?cases.moduleId="+module;
				var treeObj=$.fn.zTree.getZTreeObj('treeMenu');//参数为tree控件的id
				treeObj.expandAll(true); 
			    var node = treeObj.getNodeByParam("id",module);
				treeObj.selectNode(node);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
			});

		});
	
		function gotoTask(){
			var src=document.getElementById('mainFrame').src;
			var module=src.split('cases.moduleId=')[1];
			window.location.href='task_my.jsp?module='+module;
		}
		
		function goToFavo(){
			var src=document.getElementById('mainFrame').src;
			var module=src.split('cases.moduleId=')[1];
			window.location.href='getAllFavoByCondition.action?module='+module;
		}
	
		function redirectMain(event, treeId, treeNode, clickFlag) {
			var iframe=document.getElementById('mainFrame');
			var module=treeNode.module;
			var parentModule=treeNode.parentModule;
			iframe.src="${pageContext.request.contextPath}/getAllCaseByCondition.action?cases.moduleId="+module;
		}
	
		function getQueryString(name) { 
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
			var r = window.location.search.substr(1).match(reg); 
			if (r != null){ 
				return unescape(r[2]); 
			}
			return null; 
		} 
		
	
</script>
</head>
<body style="margin:0;height:100%;width:100%;overflow:hidden;">
	<div style="position:relative;float:left;width:20%;height:100%">
		<div id="treeMenu" class="ztree"></div>
	</div>
	<div style="position:relative;float:left;width:80%;height:100%">
		<iframe id="mainFrame" name="main" style="width:99%;height:99%;"></iframe>
	</div>
	<script>
</script>
</body>
</html>