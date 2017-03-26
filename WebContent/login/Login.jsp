<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>北京财经专修学院学生管理系统登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">


<!-- CSS 
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
	-->
<link rel="stylesheet" href="login/assets/css/reset.css">
<link rel="stylesheet" href="login/assets/css/supersized.css">
<link rel="stylesheet" href="login/assets/css/style.css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<title>北财学信系统登陆</title>
</head>
<body>
	<div class="page-container">
		<h1>北财学生管理系统</h1>
		<!--  	<strong><h1></h1></strong>-->
		<form action="login" method="post">

			<input type="text" name="username" class="username"
				placeholder="姓名"> 
			<input type="password" name="passWord"
				class="password" placeholder="密码"> 
			<input type="text" name="user"
				class="user" placeholder="登陆身份（教师、班主任、管理员、院长）">
			<button type="submit">点击登陆</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
		<!-- Javascript -->
		<script src="login/assets/js/jquery-1.8.2.min.js"></script>
		<script src="login/assets/js/supersized.3.2.7.min.js"></script>
		<script src="login/assets/js/supersized-init.js"></script>
		<script src="login/assets/js/scripts.js"></script>
</body>
</html>