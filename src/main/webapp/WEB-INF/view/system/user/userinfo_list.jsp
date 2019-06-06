<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>用户管理-用户查询</title>
    <link href="resource/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="resource/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="resource/js/bootstrap.min.js"></script>
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
                    $("#f1").prop("action", "${pageContext.request.contextPath}/system/deleteUsers");
                    //jquery提交form表单
                    $("#f1").submit();
                }
            });

        })

    </script>
</head>
<body>
<div>
    <ul class="breadcrumb" style="margin: 0px;">
        <li>系统管理</li>
        <li>用户管理</li>
        <li>用户查询</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/system/userQuery" id="f1" class="form-inline" method="post">
    <div class="row alert alert-info" style="margin: 0px; padding: 5px;">
        <div class="form-group">
            <label>用户账号:</label>
            <input type="text" class="form-control" name="userAccount" value="${user.userAccount==null?'':user.userAccount }"
                   placeholder="请输入用户账号"/>
            <label>用户姓名:</label>
            <input type="text" class="form-control" name="userName" value="${user.userName }" placeholder="请输入用户姓名"/>
        </div>
        <input type="submit" class="btn btn-danger" value="查询"> <a
            href="${pageContext.request.contextPath}/system/addUser" class="btn btn-success">添加用户</a>
        <input type="button" class="btn btn-warning" id="del" value="删除用户">
    </div>
    <div class="row" style="padding: 15px;">
        <%--bootstarp表单样式--%>
        <table class="table table-hover table-condensed">
            <tr>
                <td><input type="checkbox" id="all"></td>
                <td>用户编号</td>
                <td>用户姓名</td>
                <td>用户性别</td>
                <td>用户年龄</td>
                <td>用户电话</td>
                <td>用户角色</td>
                <td>用户账户</td>
                <td>用户密码</td>
                <td>用户薪资</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <!-- 这里的ck为用户实体类的复选框属性 -->
                    <td><input type="checkbox" name=ids value="${user.userId}"></td>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.userSex}</td>
                    <td>${user.userAge}</td>
                    <td>${user.userPhone}</td>
                    <td>${user.roleName}</td>
                    <td>${user.userAccount}</td>
                    <td>${user.userPwd}</td>
                    <td>${user.userSalary}</td>
                    <td><a href="${pageContext.request.contextPath}/system/updateUser?userId=${user.userId}">修改</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="paginationDiv">
        当前第${userList.pageNum}页
        <a href="${pageContext.request.contextPath}/system/userQuery?pageNum=${userList.navigateFirstPage}&pageSize=${userList.pageSize}">首页</a>
        <a href="${pageContext.request.contextPath}/system/userQuery?pageNum=${userList.prePage}&pageSize=${userList.pageSize}">上一页</a>
        <a href="${pageContext.request.contextPath}/system/userQuery?pageNum=${userList.nextPage}&pageSize=${userList.pageSize}">下一页</a>
        <a href="${pageContext.request.contextPath}/system/userQuery?pageNum=${userList.navigateLastPage}&pageSize=${userList.pageSize}">尾页</a>
        总共${userList.pages}页
    </div>
</form>
</body>
</html>