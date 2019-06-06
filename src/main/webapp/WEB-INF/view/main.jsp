<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    //获取绝对路径路径
    String path = request.getContextPath();

    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>工作台</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="resource/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="resource/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="resource/js/bootstrap.min.js"></script>
    <!--
       highchartTable用法
         1.导入js文件highcharts.js、query.highchartTable.js
         2.jsp文件引入js脚本<script></>
         3.定义table表格，必须有<thead></>  <tbody></>
         4.初始化容器   	$(document).ready(function() {
                              $('table.salaryChart').highchartTable();
                                                });
     -->
    <script type="text/javascript" src="resource/js/highcharts.js"></script>
    <script type="text/javascript" src="resource/js/jquery.highchartTable.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //初始化，将那个一个table转换为图表
            $('table.salaryChart').highchartTable();
        });
    </script>
</head>
<body>
<div style="padding:0px; margin:0px;">
    <ul class="breadcrumb" style=" padding:0px; padding-left:20px;" >
        <li ><a href="#">首页</a></li>
        <li>工作台</li>
    </ul>
</div>
<div class="row">
    <div class="col-xs-6" >
        <div class="panel panel-default" >
            <div class="panel-heading"  >
                <span class="glyphicon glyphicon-refresh"></span>hello
            </div>
            <div class="panel-body">
                <!--定义table表格
                         data-graph-container-before="1"表示该table是一个报表容器
                         data-graph-height="250"表示报表高度
                          data-graph-type="column"
                          column:柱形图
                          spline:折线图
                          area:区域图
                          pie:饼状图,使用饼状图的要注意只能描述最后一个类型
                  -->
                <table class="salaryChart" style="display: none;" data-graph-container-before="1" data-graph-height="250" data-graph-type="area">
                    <thead>
                    <tr>
                        <th>月份</th>
                        <th>底薪</th>
                        <th>提成</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${salaryChart }" var="sc">
                        <tr>
                            <td><fmt:formatDate value="${sc.salaryMonth }" type="both" pattern="yyyy-MM"/></td>
                            <td>${sc.salaryBasicTotal }</td>
                            <td>${sc.salaryCommTotal }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-xs-6" >
        <div class="panel panel-default" >
            <div class="panel-heading"  >
                <span class="glyphicon glyphicon-refresh"></span>world
            </div>
            <div class="panel-body">
                <table class="salaryChart" style="display: none;" data-graph-container-before="1" data-graph-height="250" data-graph-type="column">
                    <thead>
                    <tr>
                        <th>月份</th>
                        <th>底薪</th>
                        <th>提成</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${salaryChart }" var="sc">
                        <tr>
                            <td><fmt:formatDate value="${sc.salaryMonth }"  type="both" pattern="yyyy-MM"/></td>
                            <td>${sc.salaryBasicTotal }</td>
                            <td>${sc.salaryCommTotal }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


</div>
</body>
</html>