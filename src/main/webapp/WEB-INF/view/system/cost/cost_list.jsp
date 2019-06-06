<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>   
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>	   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	
	//实现全选和全不选
	$("#all").click(function(){
		var checked = this.checked;
		$("input[name=ids]").each(function(){
			this.checked = checked;
		});
	});
	
	//普通按钮提交表单
	$("#del").click(function(){
			//获取选中的复选框
			var ids = $("input[name=ids]:checked");
		
			if(ids.length == 0){
				alert("请选择删除的费用!");
				return;
			}
		
			if(confirm("确认要删除！")){
				$("#f1").attr("action","system/costDelete");
				$("#f1").submit();//提交表单
			}
		
	});
	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>费用管理</li>
			<li>费用查询</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${info }</h4>
    </div>
	<form action="system/costQuery" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<label>费用编号:</label> 
				<input type="text" class="form-control" name="costId" value="${cost.costId==0?'':cost.costId }" placeholder="请输入费用编号" />
				<label>费用名称:</label>
				<input type="text" class="form-control" name="costName" value="${cost.costName }"  placeholder="请输入费用名称" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> <a
				href="system/costAdd" class="btn btn-success">添加费用</a>
			<input type="button" class="btn btn-warning" id="del" value="删除费用">
		
		</div>
		
		<div class="row" style="padding: 15px;">
		<%--	<d:table class="table table-hover table-condensed" name="costList" pagesize="10" requestURI="system/costQuery">
				<d:column property="ck" title="<input type='checkbox'  id='all'  />"></d:column>
				<d:column property="costId" title="费用编号"></d:column>
				<d:column property="costName" title="费用名称"></d:column>
				<d:column property="costDesc" title="费用描述"></d:column>
				<!-- 用href跳转 costId传参 -->
				<d:column value="修改" title="修改" href="system/costUpdate"  paramId="costId" paramProperty="costId"></d:column>
			</d:table>--%>

			<%--bootstarp表单样式--%>
			<table class="table table-hover table-condensed">
				<tr>
					<td><input type="checkbox" id="all"></td>
					<td>费用编号</td>
					<td>费用名称</td>
					<td>费用描述</td>
					<td>操作</td>
				</tr>
			<c:forEach items="${costList}" var="cost">
				<tr>
					<!-- 这里的ck为cost实体类的复选框属性 -->
					<td><input type="checkbox" name="ids" value="${cost.costId}"></td>
					<td>${cost.costId}</td>
					<td>${cost.costName}</td>
					<td>${cost.costDesc}</td>
					<td><a href="${pageContext.request.contextPath}/system/costUpdate?costId=${cost.costId}">修改</a></td>
			</c:forEach>

		</div>
	</form>
</body>
</html>