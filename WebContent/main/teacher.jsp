<%@page import="com.bc.stdsys.entitys.MyClass"%>
<%@page import="com.bc.stdsys.entitys.Course"%>
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

<body id="body" data-spy="scroll" data-target=".bs-docs-sidebar"
	data-twttr-rendered="true">
	<%
		Teacher teacher = (Teacher) session.getAttribute("user");
		ArrayList<String> classList = (ArrayList<String>) session.getAttribute("myClass");//班级名称集合
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
							<li><a href="login?quit=q" name="quitLogin" >退出登陆</a></li>
							<li><a id="changePassWord" href='#myModal3'
								data-toggle='modal'>修改密码</a></li>
						</ul></li>
				</ul>
				<form class="navbar-search ">
					<input type="text" class="search-query" placeholder="请输入学生姓名">
					<input class="btn btn-danger" style="margin-top: -1px;"
						type="submit" value="查询">
				</form>

			</div>
		</div>
	</div>

	<!-- Modal -->
	<div style="width: 1100px; MARGIN-LEFT: -400PX;" id="myModal"
		class="modal hide fade" tabindex="0" role="dialog" dialog="false"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">学生详情信息</h3>
		</div>
		<div class="modal-body" STYLE="HEIGHT: 200PX;">
			<!-- Modal -->
			<div style="margin-top: 100px; width: 300px; display: none"
				id="myModal1" class="modal hide fade " tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">请输入本月成绩</h3>
				</div>
				<div class="modal-body" style="height: 350px;">

					<p>项目答辩:</p>
					<input id="mod1_project" type="text"><br>
					<p>笔试:</p>
					<input id="mod1_write" type="text"><br>
					<p>机试:</p>
					<input id="mod1_computer" type="text"><br>
					<p>教师评价:</p>
					<input id="mod1_teacherspeak" type="text"><br>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					<button class="btn " id="save_0" data-dismiss="modal">保存</button>
				</div>
			</div>

			<div>
				<table class="table table-bordered" id="score_info">
					<tr>
						<th>学号</th>
						<th>班级</th>
						<th>课程</th>
						<th>答辩</th>
						<th>笔试</th>
						<th>机试</th>
						<th>平均分</th>
						<th>教师</th>
						<th>教师评价</th>
						<th>班主任</th>
						<th>班主任评价</th>
						<th>日期</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


	<div style="margin-top: 100px; width: 300px;" id="myModal2"
		class="modal hide fade" tabindex="-2" role="dialog" dialog="false"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">请输入学生信息</h3>
		</div>
		<div class="modal-body" style="height: 350px;">

			<p>姓名:</p>
			<input id="mod2_stdName" type="text"><br>
			<p>学号:</p>
			<input id="mod2_stdNum" type="text"><br>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn " id="add_0" data-dismiss="modal"
				aria-hidden="true">添加</button>
		</div>
	</div>
	<div style="margin-top: 100px; width: 300px;" id="myModal3"
		class="modal hide fade" tabindex="-2" role="dialog" dialog="false"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">修改密码</h3>
		</div>
		<div class="modal-body" style="height: 350px;">

			<p>密码:</p>
			<input id="mod3_passOld" type="text"><br>
			<p>新密码:</p>
			<input id="mod3_passNew" type="text"><br>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn " id="change_password" data-dismiss="modal"
				aria-hidden="true">修改</button>
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
						<div id="studentManager" class="studentManager">
							<%
								if (classList != null && classList.size() > 0) {
									for (int i = 0; i < classList.size(); i++) {
							%>
							<a class="studentManager_1" href="javascript:;"
								id="<%=classList.get(i)%>"><%=classList.get(i)%></a><br>
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
							<a href="javascript:;" id="myCourse">我的课程</a>
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
					<a id="add" class="btn btn-danger pull-right" href='#myModal2'
						role='button' data-toggle='modal'>添加</a>
<a id="ceshi" class="btn btn-danger pull-right"
						role='button' data-toggle='modal'>测试</a>
					<table id="tab" class="table table-hover">
						<tr id="tab_0">
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>所在班级</th>
							<th>综合学分</th>
							<th>状态</th>
							<th>操作</th>
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
	<script type="text/javascript">
		$(function() {
			var stdM = $("#studentM").html()//菜单值
			var claM = $("#classM").html()//菜单值
			var title = $("#tab_0").html()//学生标题栏	
			var now = Number($("#pageNow").html().substring(0, 1));//当前页
			var total = Number($("#pageT").html());//总页数
			var myType;
			$("#body").on('click',"#pageUp,#pageDown,#go,#allStudent,.studentManager_1",
					function() {//班级管理模块&学生管理模块
						classNow = $("#classN").html()//当前选中班级
						id = $(this).attr("id");
						if (id == "pageUp") {//上翻
							myType=4;
							$("#goto").val("");//goto框滞空
							now = now - 1;
							if (now < 1) {//作分页限定
								now = 1;
							}
						} else if (id == "pageDown") {//下翻
							myType=4;
							$("#goto").val("");//goto框滞空
							now = now + 1;
							if (now >= total) {
								now = total;
							}
						} else if (id == "go") {//goto
							myType=4;
							now = Number($("#goto").val())
							if (now == '')
								now = Number($("#pageNow").html()
										.substring(0, 1));//当前页
							if (now > total) {
								now = total;
							} else if (now < 1) {
								now = 1
							}
						} else {
							myType=1;
							$("#pageUp,#pageDown,#go,#goto,#pageNow").show();//显示翻页按钮
							now = 1;
							classNow = id;
							$("#classN").html(classNow);//给隐藏标签赋值
							$("#table_title").html(id);
							$("#classN").show();
						}
						if (classNow != "") {
							$.ajax({
										type : 'post',
										url : 'find',
										data : {
											classNo : classNow,
											type : myType,
											pageNow : now
										},
										dataType : "json",
										success : function(data) {

											var Data = data;
											for ( var key in Data) {
												var keyVal = Number(key)
												total = keyVal;
												if (now > total) { //如果当前页大于总页数则限定当前页为最后一页
													now = total;
												}
												$("#pageT").html(keyVal)//为隐藏总页数赋值
												$("#pageNow").html(now+ "/"+ total);
												var arr = Data[key];
												$("#tab").empty();//tab滞空重新赋值
												$("#tab").append("<tr id=tab_0>"+ title+ "</tr>")//tab添加标题栏		
												for (var i = 0; i < arr.length; i++) {
													s = "<tr id=tab_1 class=std_inf><td>"
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
															+ "<a id=d"+arr[i].num+" value="+arr[i].num+" href='javascript:;'>"
															+ "/删除"
															+ "</a>"
															+ "</td>"
															+ "</tr>"
													$("#tab").append(s)
													$("#" + arr[i].num).on('click',function() {//显示详情
																		var stdNum = $(this).attr("value");//得到学生的学号
																		$.ajax({//查出该生的历史信息
																					type : 'post',
																					url : 'find',
																					data : {
																						stdN : stdNum,
																						type : 3
																					},
																					dataType : "json",
																					success : function(
																							data) {
																						var Data = data
																						for ( var key in Data) {
																							var arr = Data[key];//得到数据
																							$("#score_info").empty();
																							$("#score_info").append(
																											"<tr>"//添加标题
																													+ "<th>学号</th>"
																													+ "<th>班级</th>"
																													+ "<th>课程</th>"
																													+ "<th>答辩</th>"
																													+ "<th>笔试</th>"
																													+ "<th>机试</th>"
																													+ "<th>平均分</th>"
																													+ "<th>教师</th>"
																													+ "<th>教师评价</th>"
																													+ "<th>班主任</th>"
																													+ "<th>班主任评价</th>"
																													+ "<th>日期</th>"
																													+ "<th>操作</th>"
																													+ "</tr>");
																							for (var i = 0; i < arr.length; i++) {
																								if (i == (arr.length - 1)) {//最后一行可操作(由管理员更新)
																									$("#score_info").append(
																													"<tr>"
																															+ "<td id="+arr[i].date+" class=update>"//给最后一行赋id(用当前时间作id)
																															+ arr[i].studentNum
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].myClass
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].course
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].faceToFace
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].writeScore
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].computer
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].average
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].teacher
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].teacherSpeak
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].classWorker
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].classWorkerSpeak
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].date
																															+ "</td>"
																															+ "<td>"
																															+ "<a href='#myModal1' role='button' data-toggle='modal'>修改</a>"
																															+ "</td></tr>");
																								} else {
																									$("#score_info").append("<tr>"
																															+ "<td>"
																															+ arr[i].studentNum
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].myClass
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].course
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].faceToFace
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].writeScore
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].computer
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].average
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].teacher
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].teacherSpeak
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].classWorker
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].classWorkerSpeak
																															+ "</td>"
																															+ "<td>"
																															+ arr[i].date
																															+ "</td><td>"
																															+ "</td></tr>");
																								}
																							}
																						}
																					}
																				})
																	})
													//删除本班某一学生
													$("#d" + arr[i].num).on('click',function() {
																		var nn = $(this).attr("id");
																		var stdNum = nn.substring(1,nn.length);//得到该学生的id
																		now = $("#pageNow").html().substring(0,1);//得到当前页
																		classNow = $("#classN").html()//当前选中班级
																		if (classNow != 'allStudent') {//将该学生所在班级清空
																			$.ajax({
																						type : 'post',
																						url : 'delete',
																						data : {
																							stdN : stdNum,
																							type : 1,
																							classNo : classNow,
																							pageNow : now
																						},
																						dataType : "json",
																						success : function(
																								data) {//?修改！将返回数据进行处理
																							//$("#go").click();
																						}
																					})
																			//$("#pageDown").trigger('click');//改(注意success外边调用)
																		} else {//删除数据库中的学生
																			$.ajax({
																						type : 'post',
																						url : 'delete',
																						data : {
																							stdN : stdNum,
																							type : 2,
																							classNo : classNow,
																							pageNow : now
																						},
																						dataType : "json",
																						success : function(data) {//将返回数据进行处理

																						}
																					})
																		}
																		$("#pageDown").trigger('click');//(注意success外边调用)
																	})
												}
											}
										}
									})
						}
					})
			//为该班级添加一个学生
			$("#add_0").click(function() {
				stdName1 = $("#mod2_stdName").val();
				stdNum = $("#mod2_stdNum").val();
				classNow = $("#classN").html();//得到当前班级
				now = $("#pageNow").html().substring(0, 1);//得到当前页
				if (stdName1 != '' && stdNum != '') {
					$.ajax({
						type : 'post',
						url : 'add',
						data : {
							stdN : stdNum,
							stdName : stdName1,
							type : 1,
							classNo : classNow,
							pageNow : now
						},
						dataType : "json",
						success : function(data) {
							var Data = data;
							for ( var key in Data) {
								var aaa = Data[key];
								if (aaa == "该生不存在") {
									alert(aaa)
								} else {
									alert("添加成功！")
									$("#pageDown").click();//添加成功后调用#pageDown的点击事件进行刷新
								}
							}

						}
						//$("#pageDown").trigger();//添加成功后调用#pageDown的点击事件进行刷新
						//$("#pageUp").trigger();//添加成功后调用#pageUp的点击事件进行刷新
					})
				}
				//$("#pageDown").trigger("click");//添加成功后调用#pageDown的点击事件进行刷新
				//$("#pageUp").trigger("click");//添加成功后调用#pageUp的点击事件进行刷新

			})
			//修改学生当月的成绩
			$("#save_0").click(
					function() {
						project = Number($("#mod1_project").val())
						write = Number($("#mod1_write").val())
						computer = Number($("#mod1_computer").val())
						studentnum = Number($(".update").html())
						date1 = $(".update").attr("id")
						teacherspeak = $("#mod1_teacherspeak").val()
						if (project != '' && write != '' && computer != ''
								|| teacherspeak != '') {
							$.ajax({
								type : 'post',
								url : 'change',
								data : {
									pj : project,
									wr : write,
									cp : computer,
									ts : teacherspeak,
									no : studentnum,
									date : date1,
									type : 1,
								},
								dataType : "json",
								success : function(data) {
									var Data = data;
									for ( var key in Data) {
										var attr = Data[key];
										if (attr != '') {
											alert(attr);
										}
									}
								}
							})
						}
					})
			$("#studentM").click(function() {//点击修改导航栏方法
				$("#add").show();//隐藏添加按钮
				$("#pageUp,#pageDown,#go,#goto,#pageNow").show();//隐藏翻页按钮
				$("#M").html(stdM)//让导航栏显示当前菜单
				$("#table_title").html("学生信息列表")
				$("#tab").empty()//清空tab下的列表
				$("#pageUp,#pageDown,#go,#goto,#pageNow").hide();//隐藏翻页按钮
			})
			$("#classM").click(function() {//点击修给导航栏方法
				$("#add").hide();//隐藏添加按钮
				$("#M").html(claM)//让导航栏显示当前菜单
				$("#pageUp,#pageDown,#go,#goto,#pageNow").hide();//隐藏翻页按钮
				$("#table_title").html("课程信息列表");
				$("#tab_0").empty();//清空标题栏
				$(".std_inf").remove();//删除学生列表
				//alert($("#tab").html());
			})
			$("#myCourse").click(function() {//点击获取班级信息
								//定义课程标题栏
								if ($("#tab_0").html() == "") {
									var courseTitle = "<th>课程名称</th><th>班级</th><th>教师</th><th>开课日期</th>"
									$("#tab_0").append(courseTitle);//添加标题栏
									$.ajax({
												type : 'post',
												url : 'find',
												data : {
													type : 2,
												},
												dataType : "json",
												success : function(data) {
													var Data = data;
													for ( var key in Data) {
														var aaa = Data[key]
														for (var i = 0; i < aaa.length; i++) {
															$("#tab")
																	.append(
																			"<tr id=tab_1 class=std_inf><td>"
																					+ aaa[i].name
																					+ "</td>"
																					+ "<td>"
																					+ aaa[i].myClass
																					+ "</td>"
																					+ "<td>"
																					+ aaa[i].teacher
																					+ "</td>"
																					+ "<td>"
																					+ aaa[i].date
																					+ "</td></tr>")
														}
													}
												}
											})
								}
							})
			$("#change_password").click(function() {//用户修改密码
				var old_pass = $("#mod3_passOld").val();
				var new_pass = $("#mod3_passNew").val();
				$.ajax({
					type : 'post',
					url : 'change',
					data : {
						old_p : old_pass,
						new_p : new_pass,
						type : 2,
					},
					dataType : "json",
					success : function(data) {
						var Data = data;
						for ( var key in Data) {
							var attr = Data[key];
							if (attr != '') {
								$("#mod3_passOld").val("");
								$("#mod3_passNew").val("");
								alert(attr);
							}
						}
					}
				})

			})
$("#ceshi").click(function(){
	alert($("#pageNow").html().substring(0, 1));
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