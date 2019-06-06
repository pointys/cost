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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript"
	src="resource/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
        $(function () {
            //绑定全选按钮选中事件
            $("#all").click(function () {  //全选按钮点击事件
                //var allChecked=this.checked;	//js获取所有多选框的checked属性,选择为true
                var allChecked = $(this).prop('checked');//jq返回true或false

                //获取下面复选框中name值为ids的数组
                var all = $("input[name=ids]");
                all.each(function () {//循环所有复选框
                    this.checked = allChecked;//设置复选框属性
                });
            });

            //绑定点击删除按钮事件
            $("#del").click(function () {

                //获取所有已经选中的name属性为ids的input标签
                var cks = $("input[name=ids]:checked");

                if (cks.length < 1) { //一个选中的都没有
                    alert("请选择要删除的用户");//警告弹窗
                    return;

                    /* 			retrun true； 返回正确的处理结果。
                                return false；分会错误的处理结果，终止处理。
                                return；把控制权返回给页面。 */
                }
                //confirm(arg) 点击确定时返回true继续执行下面代码,点击取消返回false不执行
                if (confirm("确认要删除吗？")) {//确认弹窗
                    //修改form表单的action地址
                    $("#f1").prop("action","${pageContext.request.contextPath}/document/documentDelete");
                    //jquery提交form表单
                    $("#f1").submit();
                }
            });

            $("#covertView").click(function () {
                alert("文件需进行格式转换请耐心wait...")
            });
        })

	</script>
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>文档管理</li>
			<li>文档列表</li>
		</ul>
	</div>
<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
    <h4 align="center" style="color: red">${tip }</h4>
</div>
	<form action="${pageContext.request.contextPath}/document/documentQuery" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<label>文件名称:</label> <input type="text" class="form-control" name="file_name" value="${document.file_name }" placeholder="请输入文件名称" />
				<label>上传用户:</label> <input type="text" class="form-control" name="userName" value="${document.userName }" placeholder="请输入上传者姓名" />
			<label>上传日期从:</label> <input type="text" class="form-control" name="beginDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${document.beginDate }" placeholder="请输入开始时间" />
			<label>至:</label> <input type="text" class="form-control" name="endDate"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${document.endDate }" placeholder="请输入结束时间" />
				<label>文件类型:</label>
				<select class="form-control" name="file_type">
					<option value="">请选择</option>
					<option value="doc" ${document.file_type=='doc'?'selected':'' } >doc</option>
					<option value="docx" ${document.file_type=='docx'?'selected':'' }>docx</option>
					<option value="xlsx" ${document.file_type=='xlsx'?'selected':'' }>xlsx</option>
					<option value="pdf" ${document.file_type=='pdf'?'selected':'' }>pdf</option>
					<option value="txt" ${document.file_type=='txt'?'selected':''}>txt</option>
				</select>
			</div>
			<input type="submit" class="btn btn-danger" value="查询">
			<input type="button" class="btn btn-warning" id="del" value="删除文档">
		</div>
		
		<div class="row" style="padding: 15px;">
		<table class="table table-hover table-condensed">
			<tr>
				<td><input type="checkbox" id="all"></td>
				<td align="center">文档编号</td>
				<td align="center" class="col-lg-2">文档名称</td>
				<td align="center">文档大小</td>
				<td align="center">上传日期</td>
				<td align="center" class="col-lg-2">文档地址</td>
				<td align="center">上传用户</td>
				<td colspan="2" align="center">操作</td>
			</tr>
			<c:forEach items="${documentList}" var="document">
				<tr>
					<!-- 这里的ck为用户实体类的复选框属性 -->
					<td><input type="checkbox" name=ids value="${document.file_id}"></td>
					<td align="center">${document.file_id}</td>
					<td align="center">${document.file_name}</td>
					<td align="center">
						<fmt:parseNumber value="${document.file_size}" var="a" />
						<fmt:parseNumber value="1048578"  var="b"/>
						<fmt:formatNumber value="${a/b}" pattern="##.##" minFractionDigits="2"></fmt:formatNumber>M
					</td>
					<td align="center"><fmt:formatDate value="${document.file_date}" pattern="yyyy年MM月dd日"/></td>
					<td align="center">${document.file_url}</td>
					<td align="center">${document.userName}</td>
					<td align="center" id="covertView"><a href="${pageContext.request.contextPath}/document/documentWatch?file_id=${document.file_id}">预览</a></td>
					<td align="center"><a href="${pageContext.request.contextPath}/document/documentDownload?file_url=${document.file_url}">下载</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</form>
</body>
</html>