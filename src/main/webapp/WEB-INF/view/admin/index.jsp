<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
    <link href="/resource/css/all.min.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="/resource/css/sb-admin.css" rel="stylesheet">
</head>
<body >

<!-- 后台管理系统顶部 -->
	<jsp:include page="top.jsp" />

	<div id="wrapper">

		<!-- 后台管理系统左部菜单 -->
		<jsp:include page="left.jsp" />
		<!-- 中间内容显示区域 -->
		<div id="content-wrapper">
		
		中间区域
		</div>
	</div>

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
		
	<script type="text/javascript">
$(function(){
	//为导航栏添加点击事件
	$(".nav-link").click(function(){
		//获取点击的url
		var url =$(this).attr("data");
		//在中间区域加载url
		$("#content-wrapper").load(url);
		
	})
	
})

</script>	
	
	

   
</body>
</html>