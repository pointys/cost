<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    //获取绝对路径路径
    String path = request.getContextPath();

    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title></title>
    <link href="resource/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="resource/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
<div>
    <ul class="breadcrumb" style="margin: 0px;">
        <li>系统管理</li>
        <li>我的薪资查询</li>

    </ul>
</div>
<div class="row" style="padding: 15px;">
    <table class="table table-hover table-condensed">
            <tr>
                <td>薪资发放编号</td>
                <td>发放月份</td>
                <td>基本薪资</td>
                <td>薪资提成</td>
                <td>发放时间</td>
            </tr>
        <c:forEach items="${salaryList}" var="salary">
            <tr>
                <td>${salary.salaryId}</td>
                <td><fmt:formatDate value="${salary.salaryMonth}" pattern="yyyy-MM"/></td>
                <td>${salary.salaryBasic}</td>
                <td>${salary.salaryComm}</td>
                <td><fmt:formatDate value="${salary.salaryDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
            </c:forEach>
        </table>
</div>
</body>
</html>