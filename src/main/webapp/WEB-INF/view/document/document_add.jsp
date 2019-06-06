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
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>文档管理-文档添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">
    $(function () {
        $('#file_desc').keyup(function() {
            var len=this.value.length
            $('#text-count').text(len);
        })
    })
/*    $(function () {
        $("sub").click(function () {
            $.ajax({
                url: "/upload",
                type: "post",
                dataType: "json",
                success: function (tip) {
                    alert(tip);
                },
                error: function (errorMsg) {
                    alert("接收失败");
                }
            });

        });
    })*/
</script>
<style type="text/css">.error{color: red;}</style>

</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>文档管理</li>
			<li>文档添加</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${tip }</h4>
    </div>
<form action="document/doUpload" id="f1" method="post" class="form-horizontal" enctype="multipart/form-data">>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">选择文档</label>
					<div class="col-xs-3 ">
						<input type="file" class="form-control" name="files" multiple="multiple" required>支持各类office文件与文本文件,非此类上传将被过滤,禁止上传同一文件
					</div>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">文档描述</label>
					<div class="col-xs-9 ">
						<textarea rows="10" class="form-control" name="file_desc"
								  onkeyup="this.value=this.value.substring(0, 500)"
								  placeholder="最多可输入500个字" required  id="file_desc" maxlength="500"></textarea>
						<span id="text-count" value="">0</span>/500字
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->
		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="上传" />
			</div>
		</div>
	</form>
</body>
</html>