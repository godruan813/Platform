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
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

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
<link
	href="${pageContext.request.contextPath }/res/common.css"
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
	
	var choosed='1';
	
	$(function() {
		var mId=getQueryString("module.id");
		if(mId!=null&&!mId==''){
			var h="${pageContext.request.contextPath}/getModule.action?module.id="+mId;
			$("#mainFrame").attr('src',h);
			choosed=mId;
		}
		var setting = {
		    view:{
                addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
            },
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : redirectMain,
			}
		};
		
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='添加子模块' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeMenu");
				show(treeNode.id,treeNode.name);
				//zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeMenu");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		
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
				var iframe=document.getElementById('mainFrame');
				var treeObj=$.fn.zTree.getZTreeObj('treeMenu');//参数为tree控件的id
				treeObj.expandAll(true); 
			    var node = treeObj.getNodeByParam("id",choosed);
				treeObj.selectNode(node);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
			});
		});
	
		function gotoTask(){
			var src=document.getElementById('mainFrame').src;
			var module=src.split('cases.module=')[1];
			window.location.href='taskAll.jsp?module='+module;
		}
	
		function redirectMain(event, treeId, treeNode, clickFlag) {
			var iframe=document.getElementById('mainFrame');
			var module=treeNode.module;
			var parentModule=treeNode.parentModule;
			iframe.src="${pageContext.request.contextPath}/getModule.action?module.id="+module;
		}
	
		function getQueryString(name) { 
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
			var r = window.location.search.substr(1).match(reg); 
			if (r != null){ 
				return unescape(r[2]); 
			}
			return null; 
		} 
		
		function show(id,name){
				document.getElementById("bg").style.display ="block";  
			    document.getElementById("show").style.display ="block";
			    $("#parentId").val(id);
			    $("#parentName").val(name);
		}
		function hidediv() {  
		    document.getElementById("bg").style.display ='none';  
		    document.getElementById("show").style.display ='none'; 
		}
		
		function addroot(){
			alert("123")
		}
		
	
</script>
</head>
<body style="margin:0;height:100%;width:100%;overflow:hidden;">
	<div style="float:left;width:12%;height:90%;">
	<div style="width:100%;height:90%;float:left;">
		<div id="treeMenu" class="ztree"></div>
	</div>
	<div  onclick="show('0','')" style="cursor:pointer;display:inline-block;width:100%;height:30px;background:blanchedalmond;text-align:center;line-height:30px;border-top:1px solid black;border-bottom:1px solid black" >添加根节点</div>
	</div>
	<div style="position:relative;float:right;width:88%;height:100%">
		<iframe id="mainFrame"
			src="${pageContext.request.contextPath}/getModule.action?module.id=1"
			name="main" style="width:99%;height:99%;"></iframe>
	</div>
	<div id="bg"></div>  
	<div id="show">
		<a id="shut" onclick="hidediv()"></a>
		<form class="form-signin" action="addModule.action" method="post">
			<input id="parentId" class="input" type="hidden" name="module.parent" onblur=""/>
			<table class="table table-bordered table-hover m10">
				
				<tr>
					<td>
						父节点:
					</td>
					<td>
						<input id="parentName" class="input" type="text"  onblur=""
							onFocus="" style="background: gainsboro" readonly="true"/>
						<span id="stName1"></span>
					</td>
				</tr>
				<tr>
					<td>
						模块名称
					</td>
					<td>
						<input class="input" type="text" name="module.name" onblur="" />
						<span id="stAge1"></span>
					</td>
				</tr>
			</table>
			<input type="submit" value="提交" class="btn btn-primary" style="margin-right:50px;float:right">
			&nbsp;&nbsp;&nbsp;
		</form>
	</div>
</body>
</html>