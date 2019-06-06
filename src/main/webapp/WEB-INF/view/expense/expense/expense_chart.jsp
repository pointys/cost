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
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>报销管理</li>
			<li>费用报表</li>
		</ul>
	<div class="col-xs-8" >
		<div class="panel panel-default" >
			<div class="panel-heading"  >
				<span class="glyphicon glyphicon-refresh"></span>柱状图
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
				<table class="salaryChart" style="display: none;" data-graph-container-before="1" data-graph-height="250" data-graph-type="column">
					<thead>
					<tr>
						<th>报销类别</th>
						<th>报销总额</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${eCList }" var="c">
						<tr>
							<td>
								<c:if test="${c.costId==1}">
									飞机费用
								</c:if>
								<c:if test="${c.costId==2}">
									大保健费用
								</c:if>
								<c:if test="${c.costId==3}">
									住宿费用
								</c:if>
								<c:if test="${c.costId==5}">
									aa费用
								</c:if>
								<c:if test="${c.costId==6}">
									ooo费用
								</c:if>
								<c:if test="${c.costId==7}">
									飞机费用2
							</c:if>
								<c:if test="${c.costId==8}">
									费用test
								</c:if>
							</td>
							<td>${c.expenseTotal }</td>
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