<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>	   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	<!-- 1.导包 My97DatePicker
	     2.引入js文件WdatePicker.js
	     3.绑定点击jsp时间的事件 
	     -->
<script type="text/javascript"
	src="resource/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript">
$(function(){
	

	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>报销管理</li>
			<li>报销查询</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${info }</h4>
    </div>
	<form action="expense/expenseQuery" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
			<label>报销人:</label> <input type="text" class="form-control"     name="userName" value="${expense.userName }" placeholder="请输入报销人姓名" />
			<label>开始时间:</label> <input type="text" class="form-control " type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="startDate" value="${expense.startDate}" placeholder="请输入开始时间" />
			<label>结束时间:</label> <input type="text" class="form-control" name="endDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${expense.endDate }" placeholder="请输入结束时间" />	
			<label>报销原因:</label> <input type="text" class="form-control"     name="expenseName" value="${expense.expenseName }" placeholder="请输入报销原因" />
			 <label>报销状态:</label>
			<select class="form-control" name="expenseState">
				<option value="">请选择</option>
				<option value="1" ${expense.expenseState=='1'?'selected':'' } >经理未审核</option>
				<option value="2" ${expense.expenseState=='2'?'selected':'' }>经理审核通过</option>
				<option value="3" ${expense.expenseState=='3'?'selected':'' }>财务审核通过</option>
				<option value="-1" ${ea.expenseState=='-1'?'selected':'' }>经理审核未通过</option>
				<option value="-2" ${expense.expenseState=='-2'?'selected':''}>财务审核未通过</option>
			</select>
                <input type="submit" class="btn btn-danger" value="查询">
            </div>

		</div>
		
		<div class="row" style="padding: 15px;">


				<table class="table table-hover table-condensed">
					<tr>
						<td>报销编号</td>
						<td>报销人</td>
						<td>报销原因</td>
						<td>报销日期</td>
						<td>报销状态</td>
						<td>报销总金额</td>
						<td>查看详情</td>
					</tr>
					<c:forEach items="${expenseList}" var="expense">
					<tr>
						<td>${expense.expenseId}</td>
						<td>${expense.userName}</td>
						<td>${expense.expenseName}</td>
						<td><fmt:formatDate value="${expense.expenseDate}" pattern="yyyy年MM月dd日"/></td>
						<td>
							<c:if test="${expense.expenseState==0}">
								未提交审核
							</c:if>
							<c:if test="${expense.expenseState==1}">
								待经理审核
							</c:if>
							<c:if test="${expense.expenseState==2}">
								经理审核通过
							</c:if>
							<c:if test="${expense.expenseState==3}">
								财务审核通过
							</c:if>
							<c:if test="${expense.expenseState==-1}">
								经理审核不通过
							</c:if>
							<c:if test="${expense.expenseState==-2}">
								财务审核不通过
							</c:if>
						</td>
						<td>${expense.expenseTotal}</td>
						<td><a href="${pageContext.request.contextPath}/expense/showExpenseDetail?expenseId=${expense.expenseId}">查看详情</a></td>
					</tr>
				</c:forEach>
				</table>
		</div>
	</form>
</body>
</html>