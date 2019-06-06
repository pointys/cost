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
                window.open("document/showPdf?file_pdf_url=${file_pdf_url}");
              });
	</script>
</head>
<body>
${tip}
</body>
</html>