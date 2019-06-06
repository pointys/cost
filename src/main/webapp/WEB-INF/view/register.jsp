<%--
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/styles.css">
    <style type="text/css">
        body, td, th {
            font-family: "Source Sans Pro", sans-serif;
        }

        body {
            background-color: #2B2B2B;
        }
    </style>
</head>
<body>


<div class="wrapper">

    <div class="container">
        <h1>Register</h1>
        <h4 style="display: ${tip == null ? none : inline} ">${tip}</h4>
        <form action="${pageContext.request.contextPath}/system/doRegister" class="form" method="post">
            <input type="text" placeholder="Email" name="userEmail">
            <input type="text" placeholder="Account" name="userAccount" >
            <input type="password" placeholder="Password" name="userPwd">
            <button type="submit" id="login-button">注册</button>
        </form>
    </div>

    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
    if (top.location !== self.location) {
        top.location = self.location;
    }
</script>
</body>
</html>--%>
