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
		ArrayList<String> classList = (ArrayList<String>) session.getAttribute("myClass");//班级集合
		//ArrayList<Student> studentC = (ArrayList<Student>) session.getAttribute("myStudentC");//每个班学生集合
		//ArrayList<Student> studentP = (ArrayList<Student>) session.getAttribute("myStudentP");//每页班学生集合
		//Integer pageSize = 2;//设计每页显示的条数
		//Integer PageTotal = 0;//总页数-
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

	<!-- Modal -->
	<div style="width: 1100px; MARGIN-LEFT: -400PX;" id="myModal"
		class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">学生详情信息</h3>
		</div>
		<div class="modal-body" STYLE="HEIGHT: 200PX;">
			<div>
				<table class="table table-bordered">
					<tr>
						<th>studentnum</th>
						<th>myclass</th>
						<th>course</th>
						<th>facetoface</th>
						<th>write</th>
						<th>computer</th>
						<th>average</th>
						<th>teacher</th>
						<th>teacherSpeak</th>
						<th>classWorker</th>
						<th>classWorkerSpeak</th>
						<th>date</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a href="#myModal1" role="button" data-toggle="modal">修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td><a>修改</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>

	<!-- Modal -->
	<div style="margin-top: 100px; width: 300px;" id="myModal1"
		class="modal hide fade" tabindex="-2" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Modal header</h3>
		</div>
		<div class="modal-body" style="height: 350px;"></div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn ">Save changes</button>
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
							<a id="studentM" href="javascript:;">学生管理</a>
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
							<a id="classM" href="javascript:;">课程管理</a>
						</h3>
						<div>
							<a href="javascript:;">我的课程</a>
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
					<li id="M" class="active">学生管理</li>
				</ul>
				<div style="margin-left: 33px;">
					<h2 id="table_title" style="display: inline;">学生信息列表</h2>
					<a href="javascript:;" class="btn btn-danger pull-right">添加</a>

					<table id="tab" class="table table-hover">
						<tr id="tab_0">
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>所在班级</th>
							<th>综合学分</th>
							<th>状态</th>
							<td>操作</td>
						</tr>
						<tr id="tab_1">

						</tr>
					</table>
					<div id="page" class="pagination pull-right">
						<ul>
							<li><a id="pageUp" href="javascript:;">上一页</a></li>
							<li><a id="pageNow" href="javascript:;" value="1">1</a></li>
							<li><a id="pageDown" href="javascript:;">下一页</a></li>
							<li><input type="text" id="goto" style="width: 20px"></li>
							<li><input type="submit" id="go" value="go"></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--通过ajax返回总页数  -->
	<p id="pageT" hidden></p>
	<!--通过ajax返回班级 号 -->
	<p id="classN" hidden></p>


	<!-- Placed at the end of the document so the pages load faster -->
	<script src="main/assets/js/jquery-1.9.0.min.js" type="text/javascript"></script>

	<!--点击班级查看学生  -->
	<c:forEach var="temp" items="${myClass }">
		<script type="text/javascript">
			$(function() {
				var title = $("#tab_0").html()//学生标题栏
				var pageN = 1;
				$("#${temp}")
						.click(
								function() {
									var myClass = $('#${temp}').html();
									$
											.ajax({
												type : 'post',
												url : 'find',
												data : {
													classNo : $('#${temp}')
															.html(),
													type : 1,
													pageNow : pageN
												},
												dataType : "json",
												success : function(data) {
													var Data = data;
													for ( var key in Data) {
														//alert(key)
														$("#pageT").html(key)//为隐藏标签赋key值分页用
														var arr = Data[key];
														$("#tab").empty();
														$("#tab").append(title)
														for (var i = 0; i < arr.length; i++) {
															s = "<tr><td>"
																	+ arr[i].num
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].name
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].sex
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].myClass
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].credit
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].state
																	+ "</td>"
																	+ "<td >"
																	+ "<a id="+arr[i].num+" value="+arr[i].num+" href='#myModal' data-toggle='modal'>"
																	+ "详情"
																	+ "</a>"
																	+ "</td>"
																	+ "</tr>"
															$("#tab").append(s)
															$("#classN")
																	.html(
																			arr[i].myClass)//为隐藏标签赋值分页用	
															$("#" + arr[i].num)
																	.on(
																			'click',
																			function() {
																				var nn = $(
																						this)
																						.attr(
																								"value");
																				alert(nn)
																			})
														}
													}
													$("#pageNow")
															.html(
																	"1/"
																			+ $(
																					"#pageT")
																					.html());
												}
											})
								})
			})
		</script>
	</c:forEach>


	<script type="text/javascript">
		$(function() {
			var stdM = $("#studentM").html()//菜单值
			var claM = $("#classM").html()//菜单值
			$("#studentM").click(function() {//点击修给导航栏方法
				$("#M").html(stdM)
				$("#table_title").html("学生信息列表")
			})
			$("#classM").click(function() {//点击修给导航栏方法
				$("#M").html(claM)
				$("#table_title").html("课程信息列表")
			})

			var title = $("#tab_0").html()//学生标题栏			
			$("#pageUp")
					.click(
							//点击向上分页        
							function() {
														
									classNow = $("#classN").html()//当前选中班级
									$("#goto").val("");//goto框滞空
									now = $("#pageNow").html().substring(0, 1);//当前页
									total = Number($("#pageT").html());//总页数
									now = now - 1;
									if (now < 1) {//作分页限定
										now = 1;
									}
								if (classNow != "") {//判断是否返回了数据
									$("#tab").empty();
									$("#tab").append(title)
									$("#pageNow").html(now + "/" + total)//给pageNow再赋值
									$
											.ajax({
												type : 'post',
												url : 'find',
												data : {
													classNo : classNow,
													type : 1,
													pageNow : now
												},
												dataType : "json",
												success : function(data) {
													var Data = data;
													for ( var key in Data) {
														$("#pageT").html(key)
														var arr = Data[key];
														for (var i = 0; i < arr.length; i++) {
															s = "<tr><td>"
																	+ arr[i].num
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].name
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].sex
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].myClass
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].credit
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].state
																	+ "</td>"
																	+ "<td >"
																	+ "<a id="+arr[i].num+" value="+arr[i].num+" href='#myModal' data-toggle='modal'>"
																	+ "详情"
																	+ "</a>"
																	+ "</td>"
																	+ "</tr>"
															$("#tab").append(s)
															$("#classN")
																	.html(
																			arr[i].myClass)
															$("#" + arr[i].num)
																	.on(
																			'click',
																			function() {
																				var nn = $(
																						this)
																						.attr(
																								"value");
																				alert(nn)
																			})
														}
													}
												}
											})
								}

							})
			
			$("#pageDown").click(
							//点击向下分页
							function() {
								classNow = $("#classN").html()//当前选中班级
								$("#goto").val("");//goto框滞空
								now = $("#pageNow").html().substring(0, 1);//当前页
								total = Number($("#pageT").html());//总页数
								now = now + 1;
								if (now == 2 && total == "") {
									now = 1;
								} else if (now > total) {
									now = total;
								}

								if (classNow != "") {//作分页限定
									$("#tab").empty();
									$("#tab").append(title)
									//alert(title);
									$("#pageNow").html(now + "/" + total)//给pageNow再赋值
									$
											.ajax({
												type : 'post',
												url : 'find',
												data : {
													classNo : classNow,
													type : 1,
													pageNow : now
												},
												dataType : "json",
												success : function(data) {
													var Data = data;
													for ( var key in Data) {
														$("#pageT").html(key)
														var arr = Data[key];
														for (var i = 0; i < arr.length; i++) {
															s = "<tr><td>"
																	+ arr[i].num
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].name
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].sex
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].myClass
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].credit
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].state
																	+ "</td>"
																	+ "<td >"
																	+ "<a id="+arr[i].num+" value="+arr[i].num+" href='#myModal' data-toggle='modal'>"
																	+ "详情"
																	+ "</a>"
																	+ "</td>"
																	+ "</tr>"
															$("#tab").append(s)
															$("#classN")
																	.html(
																			arr[i].myClass)
															$("#" + arr[i].num)
																	.on(
																			'click',
																			function() {
																				var nn = $(
																						this)
																						.attr(
																								"value");
																				alert(nn)
																			})
														}
													}
												}
											})
								}

							})

			$("#go").click(
							function() {
								classNow = $("#classN").html()//当前选中班级
								now = Number($("#goto").val());//当前页
								total = Number($("#pageT").html());//总页数
								if (now > total)
									now = total;
								if (classNow != "") {//作分页限定
									$("#tab").empty();
									$("#tab").append(title)
									//alert(title);
									$("#pageNow").html(now + "/" + total)//给pageNow再赋值
									$
											.ajax({
												type : 'post',
												url : 'find',
												data : {
													classNo : classNow,
													type : 1,
													pageNow : now
												},
												dataType : "json",
												success : function(data) {
													var Data = data;
													for ( var key in Data) {
														$("#pageT").html(key)
														var arr = Data[key];
														for (var i = 0; i < arr.length; i++) {
															s = "<tr><td>"
																	+ arr[i].num
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].name
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].sex
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].myClass
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].credit
																	+ "</td>"
																	+ "<td>"
																	+ arr[i].state
																	+ "</td>"
																	+ "<td >"
																	+ "<a id="+arr[i].num+" value="+arr[i].num+" class='info' href='#myModal' data-toggle='modal'>"
																	+ "详情"
																	+ "</a>"
																	+ "</td>"
																	+ "</tr>"
															$("#tab").append(s)
															$("#classN")
																	.html(
																			arr[i].myClass)
															$("#" + arr[i].num)
																	.on(
																			'click',
																			function() {
																				var nn = $(
																						this)
																						.attr(
																								"value");
																				alert(nn)
																			})
														}
													}
												}
											})
								}
							})
			
		})
	</script>
	<script src="main/assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="main/assets/js/jquery-ui-1.10.0.custom.min.js"
		type="text/javascript"></script>
	<script src="main/assets/js/google-code-prettify/prettify.js"
		type="text/javascript"></script>
	<script src="main/assets/js/docs.js" type="text/javascript"></script>
	<script src="main/assets/js/demo.js" type="text/javascript"></script>

</body>
</html>