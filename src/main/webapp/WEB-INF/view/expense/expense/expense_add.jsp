<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">

	$(function(){
		
		//保存未提交
		$("#but").click(function(){
			$("#expenseState").val("0");
			$("#f1").submit();
		});
		//保存并提交
        $("#bts").click(function(){
            var costIds = $("input[id=costId]:checked");
            if(costIds.length==0){
                alert("请选择费用明细");
			}else{
                $("#f1").attr("action","expense/expenseAdd");
                $("#f1").submit();//提交表单
			}
        });

		//选择费用明细
		$("#selected").click(function(){
			//获取第一个表格
			var tab1 = $("#tab1");
			//获取选中复选框  数组
			var costIds = $("input[id=costId]:checked");
			//总金额
			var expenseTotal = $("#expenseTotal");
			//遍历数组
			costIds.each(function(){
				//$(this) 当前复选框 
				//$(this).parent() 该复选框的上一级<td>
				//$(this).parent().parent() 找到上一级tr
				//.find("td"); 找到tr下面td数组[]
				var td = $(this).parent().parent().find("td");
				
				/* <tr>
				      <td>复选框</td>
				      <td>costId</td>
				      <td>costName</td>
				      <td>costDesc</td>
				   </tr>
				*/
				//jq:  costId=$(td[1]).html();
			/* 	var costId=td[1].innerHTML    //js方式拿到第二个数组的内容
				var costName=td[2].innerHTML  //...
				
				//将拿到的id和name添加到id为tab1的空表单中，这样就能把选择的复选框内容在该空表单中显示
			 	var trHtml="<tr>"+    //带有name属性的input文本框 是为了后台获取前台输入的值
				              "<td>"+costId+"<input type='hidden' name='costIds' value='"+costId+"''"+"<td>"+
				              "<td>"+costName+"<td>"+//后台不需要获取
				              "<td>"+"<input type='text' value='0' id='money' name='detailMoneys' class='form-control' style='width:100px'>"+"<td>"+
				              "<td>"+"<input type='text' name='detailDescs' class='form-control'>"+"<td>"+
				              "<td>"+"<a href='javascript:void(0)' class='btn btn-danger' onclick='del(this)' >删除</a>"+"<td>"+
				           "</tr>"

				$("#tab1").append(trHtml);  */
	           tab1.append("<tr>"+
								"<td>"+td[1].innerHTML+"<input type='hidden' name='costIds' value='"+td[1].innerHTML+"' ></td>"+
								"<td>"+td[2].innerHTML+"</td>"+
								"<td><input type='text' value='0' id='money' name='detailMoneys' class='form-control' style='width:100px'></td>"+
								"<td><input type='text' name='detailDescs' class='form-control'></td>"+
								"<td><a href='javascript:void(0)' class='btn btn-danger' onclick='del(this)' >删除</a></td>"+
							" </tr>");  
				
				//给文本框绑定事件 只能定义在这个地方
				$("input[id=money]").blur(function(){//焦点移开事件
					var sum = 0;
					$("input[id=money]").each(function(){//循环函数
						
						sum+=parseFloat($(this).val());//money转型再相加
					});
					
					expenseTotal.val(sum);//将计算好的sum赋值给总金额
					
				});
				
			});
			
			//隐藏
			$("#myModal").modal("hide");
		});
		
	})
	
	function del(obj){
		if(confirm("确定要删除该费用")){
			//获取tr
			var tr = $(obj).parent().parent();
			//删除tr包括tr里面td
			tr.remove();
		}
	}


</script>	

</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>报销管理</li>
			<li>报销单</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert"
		style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
		<h4 align="center" style="color: red">${tip }</h4>
	</div>
	<%--<form action="expense/expenseAdd" id="f1" method="post"--%>
	<form action="#" id="f1" method="post"
		class="form-horizontal">
		<input type="hidden" name="userId" id="userId" value="${userInfo.userId }"/>
		<input type="hidden" name="expenseState" id="expenseState" value="1"/>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销原因</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" required name="expenseName"
							value="${expense.expenseName }" placeholder="请输入报销原因" />
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销总金额</label>
					<div class="col-xs-5 ">
						<input type="text" class="form-control" readonly="readonly" id="expenseTotal" name="expenseTotal"
							value="${expense.expenseTotal==null?'0':expense.expenseTotal }" placeholder="请输入报销总金额" />
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销详情</label>
					<div class="col-xs-9 ">
						<textarea rows="10" class="form-control" name="expenseDesc" cols=""
							placeholder="请输入报销详情">${expense.expenseDesc }</textarea>
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">
			报销单明细
			<button type="button" class="btn btn-primary btn-xs"
				data-toggle="modal" data-target="#myModal">选择费用明细</button>
		</h5>
		<table class="table" id="tab1" >
			<tr>
				<th>费用编号</th>
				<th>费用名称</th>
				<th>具体金额</th>
				<th>费用说明</th>
				<th>删除</th>
			</tr>
		
		</table>
				<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="button" id="but" class="btn btn-primary" value="保存未提交" /> 
				<input type="submit" class="btn btn-success" id="bts" value="保存并提交" />
			</div>

		</div>

	</form>
	
	<!-- 弹出框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">费用明细</h4>
      </div>
      <div class="modal-body">
        	<table class="table" id="tab2">
        		<tr>
        			<th></th>
        			<th>费用编号</th>
        			<th>费用名称</th>
        			<th>费用描述</th>
        		</tr>
        		<c:forEach items="${costList }" var="cost">
        			<tr>	
        				<td> <input type="checkbox" id="costId" value="${cost.costId }"/> </td>
        				<td>${cost.costId }</td>
        				<td>${cost.costName }</td>
        				<td>${cost.costDesc }</td>
        			</tr>
        		</c:forEach>
        		
        	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="selected">选择</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>