<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>   
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript">
$(function(){

	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>财务管理</li>
			<li>薪资查询</li>
			
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${tip }</h4>
    </div>
	<form action="finance/querySalary" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<label>领取人姓名:</label> 
				<input type="text" class="form-control" name="userName" value="${salaryRecord.userName }" placeholder="请输入领取人姓名" />
				<label>发放月份:</label>
				<input type="text" class="form-control" name="salaryMonth" value="${salaryRecord.salaryMonth }" onClick="WdatePicker({dateFmt:'yyyy-MM'})"  placeholder="请输入费用月份" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 
		
		</div>
		
		<div class="row" style="padding: 15px;">
			<table class="table table-hover table-condensed">
				<tr>
					<td>薪资编号</td>
					<td>领取人姓名</td>
					<td>发放月份</td>
					<td>基本薪资</td>
					<td>薪资提成</td>
					<td>发放时间</td>
				</tr>
				<c:forEach items="${salaryList}" var="salary">
					<tr>
						<td>${salary.salaryId}</td>
						<td>${salary.userName}</td>
						<td><fmt:formatDate value="${salary.salaryMonth}" pattern="yyyy-MM"/></td>
						<td>${salary.salaryBasic}</td>
						<td>${salary.salaryComm}</td>
						<td><fmt:formatDate value="${salary.salaryDate}" type="both"/></td>
						</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>