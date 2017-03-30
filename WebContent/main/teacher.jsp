<%@page import="com.bc.stdsys.entitys.Deanery"%>
<%@page import="com.bc.stdsys.entitys.Master"%>
<%@page import="com.bc.stdsys.entitys.ClassWorker"%>
<%@page import="com.bc.stdsys.entitys.Teacher"%>
<%@page import="com.bc.stdsys.entitys.Student"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*,com.bc.*"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setCharacterEncoding("utf-8");
%>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>北京财经专修学院</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A preview of the jQuery UI Bootstrap theme">
<meta name="author" content="Addy Osmani">
<!-- Le styles -->
<link href="main/assets/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css"
	href="main/css/custom-theme/jquery-ui-1.10.0.custom.css"
	rel="stylesheet" />
<link type="text/css" href="main/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<!--[if IE 7]>
            <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
            <![endif]-->
<!--[if lt IE 9]>
            <link rel="stylesheet" type="text/css" href="css/custom-theme/jquery.ui.1.10.0.ie.css"/>
            <![endif]-->
<link href="main/assets/css/docs.css" rel="stylesheet">
<link href="main/assets/js/google-code-prettify/prettify.css"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Le fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="main/assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="main/apple-touch-icon-precomposed" sizes="114x114"
	href="main/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="main/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="main/assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="main/assets/ico/favicon.png">
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar"
	data-twttr-rendered="true">
	<%
		Teacher teacher = (Teacher) session.getAttribute("user");
		ArrayList<String> classList = (ArrayList<String>) session.getAttribute("myClass");
	%>

	<!-- Navbar
        ================================================== -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand"
					href="http://github.com/addyosmani/jquery-ui-bootstrap">BeiCai.Com</a>
				<ul class="nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">用户操作 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">登陆</a></li>
						</ul></li>
				</ul>
				<form class="navbar-search ">
					<input type="text" class="search-query" placeholder="Search">
					<input class="btn btn-danger" style="margin-top: -1px;"
						type="submit" value="查询">
				</form>

			</div>
		</div>
	</div>


	<!-- Subhead
        ================================================== -->
	<div class="container">
		<div class="row" style="border: 1px solid；padding-left:100px;">
			<div class="span3">
				<section id="accordion">
				<div class="page-header">
					<h1>操作</h1>
				</div>

				<div id="menu-collapse">
					<div>
						<h3>
							<a href="#">学生管理</a>
						</h3>
						<div id="studentManager">
							<%
								if (classList != null && classList.size() > 0) {
									for (int i = 0; i < classList.size(); i++) {
							%>
							<a href="javascript:;" id="<%=classList.get(i)%>"><%=classList.get(i)%></a><br>
							<%
								}
								}
							%>
						</div>
					</div>
					<div>
						<h3>
							<a href="#">课程管理</a>
						</h3>
						<div>
							<a href="">我的课程</a>
						</div>
					</div>
				</div>
				</section>

			</div>
			<div class="span9">
				<ul class="breadcrumb"
					style="margin-left: 40px; margin-top: 20px; display: block">
					<li><a href="#">北财学生管理系统</a> <span class="divider">/</span></li>
					<li><a href="#">操作</a> <span class="divider">/</span></li>
					<li class="active">学生管理</li>
				</ul>
				<div style="margin-left: 33px;">
					<h2 style="display: inline;">学生信息列表</h2>
					<a href="" class="btn btn-danger pull-right">添加</a>
					<table class="table table-hover">
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>所在班级</th>
							<th>综合学分</th>
							<th>任课教师</th>
							<th>状态</th>
							<td>操作</td>
						</tr>
					</table>
					<div class="pagination pull-right">
						<ul>
							<li><a href="javascript:;">上一页</a></li>
							<li><a href="javascript:;">1</a></li>
							<li><a href="javascript:;">2</a></li>
							<li><a href="javascript:;">3</a></li>
							<li><a href="javascript:;">4</a></li>
							<li><a href="javascript:;">5</a></li>
							<li><a href="javascript:;">下一页</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="main/assets/js/jquery-1.9.0.min.js" type="text/javascript"></script>
	<c:forEach var="temp" items="${myClass }">
		<script type="text/javascript">
			$(function() {
				$("#${temp}").click(function() {
					//alert($("#${temp}").html())
					var myClass = $('#${temp}').html();
					$.ajax({
						type : 'post',
						url : 'find',
						data : {
							classNo : $('#${temp}').html(),
							type : 1,
							pageNow : 1
						},
						dataType : "json",
						success : function(data) {
							//alert(data);
							var Data = data;
							for ( var key in Data) {
								var arr = Data[key];
								for (var i = 0; i < arr.length; i++) {
									//alert(arr[i].name);
									
									
									
									
									
									
								}
							}
						}
					})
				})

			})
		</script>
	</c:forEach>
	<script src="main/assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="main/assets/js/jquery-ui-1.10.0.custom.min.js"
		type="text/javascript"></script>
	<script src="main/assets/js/google-code-prettify/prettify.js"
		type="text/javascript"></script>
	<script src="main/assets/js/docs.js" type="text/javascript"></script>
	<script src="main/assets/js/demo.js" type="text/javascript"></script>




</body>
</html>