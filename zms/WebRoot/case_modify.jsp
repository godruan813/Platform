<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'student_add.jsp' starting page</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/common.css"
	type="text/css" rel="stylesheet"></link>
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var id=getQueryString('cases.id');
		if(id=='-1'){
			//是新增
			$("#f1").attr('action','addCase.action');
		}
	});
	
	function postInsert(cur){
		var index=$(cur).parent().parent().parent().index();
		var num=index+2;
		var tr = "<tr class='text-center'><td class='stepID'>"+num+"</td><td><textarea name='steps' id='steps[]' rows='4' class='form-control'></textarea></td><td><textarea name='expects' id='expects[]' rows='4' class='form-control'></textarea></td>"+
		"<td><div class='btn-group-vertical' id='btns'>"+
		"<input type='button' tabindex='-1' class='btn btn-default btn-add' onclick='preInsert(this)'value='之前添加'>"+
		"<input type='button' tabindex='-1' class='btn btn-default btn-add' onclick='postInsert(this)'value='之后添加'>"+
		"<input type='button' tabindex='-1'class='btn btn-default btn-delete' onclick='deleteRow(this)'value='删除'></div></td></tr>";
		$("#ct tr:eq("+index+")").after(tr);
		reIndex();
		//$("table tr:eq(2)").after(tr);
	}
	
	function preInsert(cur){
		var index=$(cur).parent().parent().parent().index();
		var num=index+2;
		var tr = "<tr class='text-center'><td class='stepID'>"+num+"</td><td><textarea name='steps' id='steps[]' rows='4' class='form-control'></textarea></td><td><textarea name='expects' id='expects[]' rows='4' class='form-control'></textarea></td>"+
		"<td><div class='btn-group-vertical' id='btns'>"+
		"<input type='button' tabindex='-1' class='btn btn-default btn-add' onclick='preInsert(this)'value='之前添加'>"+
		"<input type='button' tabindex='-1' class='btn btn-default btn-add' onclick='postInsert(this)'value='之后添加'>"+
		"<input type='button' tabindex='-1'class='btn btn-default btn-delete' onclick='deleteRow(this)'value='删除'></div></td></tr>";
		$("#ct tr:eq("+index+")").before(tr);
		reIndex();
		//$("table tr:eq(2)").after(tr);
	}
	
	function deleteRow(cur){
		var nums=$("#ct tr").length;
		if(nums==2){
			 return;
		}
		$(cur).parent().parent().parent().remove();
		reIndex();
 	}
	
	function reIndex(){
		var t=$("ct");
		$("#ct tr").each(function(i){
				if(i>0){
					$(this).find("td").eq(0).text(i);
				}
		});
	}
	
	function back(){
		var module=getQueryString('m');
		window.location.href='case_ztree.jsp?module='+module;
	}
	
	function sub(){
		var actionurl=$('#f1').attr('action');
		  $.ajax({
                 url:actionurl,
                 data:$("#f1").serialize(),
                 type:"post",
                 success:function(data){//ajax返回的数据
                 	alert("操作成功");
                 	if(actionurl=='addCase.action'){
                 		back();	
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

<body style="padding-bottom:40px">
	<div
		style="padding:10px 0px 0px 20px;width:98%;border:1px solid #ddd">
		<div
			style="margin-left:auto;margin-right: auto;width:80%;border:1px solid #ddd">
			<div class="titlebar">建用例</div>
			<form id="f1" class="form-horizontal" role="form" style="padding-top:20px" action="modifyCase.action" method="post">
				<input type="hidden" name="cases.moduleId" value="${cases.mm.id}">
				<div class="form-group">
					<label for="cases.module" class="col-sm-2 control-label">模块</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="cases.modules.name"
							value="${cases.mm.name}" readonly="true" style="background:gainsboro">
					</div>
				</div>
				<div class="form-group">
					<label for="cases.title" class="col-sm-2 control-label">用例标题</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="cases.title"
							placeholder="请输入用例标题" value="${cases.title}">
					</div>
				</div>
				<div class="form-group">
					<label for="cases.type" class="col-sm-2 control-label">用例类型</label>
					<div class="col-sm-10">
						<s:select list="#{'1':'功能测试','2':'接口测试','3':'性能测试','4':'安全测试'}" name="cases.type" cssClass="form-control"></s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="cases.keyword" class="col-sm-2 control-label">关键词</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="cases.keyword"
							placeholder="请输入关键词" value="${cases.keyword}">
					</div>
				</div>
				<div class="form-group">
					<label for="cases.priority" class="col-sm-2 control-label">优先级</label>
					<div class="col-sm-10">
						<s:select list="#{'1':'1','2':'2','3':'3','4':'4'}" name="cases.priority" cssClass="form-control"></s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="cases.condtion" class="col-sm-2 control-label">用例前置条件</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows=4 name="cases.precondition">${cases.precondition}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">用例步骤</label>
					<div class="col-sm-10">
						<table id="ct" class="table" style="border:1px solid #ddd">
								<tr class="center" style="background-color:#ddd">
									<th class="">编号</th>
									<th>步骤</th>
									<th class="">预期</th>
									<th class="">操作</th>
								</tr>
	    						<s:if test="#request.step.size()==0">
								 	<tr id="row1" class="text-center">
										<td class="stepID strong">1</td>
										<td><textarea name="steps" id="steps[]" rows="4" class="form-control"></textarea></td>
										<td><textarea name="expects" id="expects[]" rows="4" class="form-control"></textarea></td>
										<td>
											<div class="btn-group-vertical" id="btns">
												<input type="button" tabindex="-1" class="btn btn-default btn-add" onclick="preInsert(this)"value="之前添加">
												<input type="button" tabindex="-1"class="btn btn-default btn-add" onclick="postInsert(this)"value="之后添加">
												<input type="button" tabindex="-1"class="btn btn-default btn-delete" onclick="deleteRow(this)"value="删除">
											</div>
										</td>
									</tr>
								 </s:if>
								 <s:else>
									 <s:iterator value="#request.step" id="casestep" status="sta">
										<tr id="row1" class="text-center">
											<td class="stepID strong"><s:property value="#sta.index+1" /></td>
											<td><textarea name="steps" id="steps[]" rows="4" class="form-control" ><s:property value="#casestep.stepinfo" /></textarea></td>
											<td><textarea name="expects" id="expects[]" rows="4" class="form-control" ><s:property value="#casestep.expect" /></textarea></td>
											<td>
												<div class="btn-group-vertical" id="btns">
													<input type="button" tabindex="-1" class="btn btn-default btn-add" onclick="preInsert(this)"value="之前添加">
													<input type="button" tabindex="-1"class="btn btn-default btn-add" onclick="postInsert(this)"value="之后添加">
													<input type="button" tabindex="-1"class="btn btn-default btn-delete" onclick="deleteRow(this)"value="删除">
												</div>
											</td>
										</tr>
									 </s:iterator>
								</s:else>	 
						</table>
					</div>
				</div>
				<div style="text-align:center">
				<input type="hidden" name="cases.id" value="${cases.id}" />
				<input type="hidden" name="cases.creater" value="${session.user.nickname}" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="提交" class="btn btn-primary" onclick="sub()">
				&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="back()"
					value="返回" class="btn btn-success">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
