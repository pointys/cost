<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'index.jsp' starting page</title>
	<script type="text/javascript" src="resource/js/jquery.min.js"></script>
	<script type="text/javascript">

        $(function(){


                window.open("http://www.runoob.com");


            // viewer.html即为项目引用pdf.js文件夹中viewer.html的路径
            <%--$("#pdfIframe").attr("src","pdfjs-2.0.943-dist/web/viewer.html?file="+ encodeURIComponent("${pageContext.request.contextPath}/document/showPdf"));--%>
        });
	</script>
</head>
<body>

	<iframe id="pdfIframe" width="100%" height="100%"></iframe>

</body>
</html>