<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function query() {

		var url = "/user/selects?username=" + $("[name='username']").val();
		$("#content-wrapper").load(url);
	}
</script>

</head>
<body>
   
   
   
	<div class="form-group form-inline">
		用户名:<input type="text" class="form-control" name="username"
			value="${username }"> &nbsp;
		<button type="button" class="btn btn-warning" onclick="query()">查询</button>
	</div>

<table class="table table-bordered">

		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>注册时间</td>
			<td>用户状态</td>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="i">

			<tr>
				<td>${i.index+1 }</td>
				<td>${user.username }</td>
				<td>${user.nickname }</td>
				<td><fmt:formatDate value="${user.birthday }"
						pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${user.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${user.locked==0?"正常":"禁用" }</td>
			</tr>

		</c:forEach>
	</table>
<div>
		${pages }
	</div>
<script type="text/javascript" src="/resource/js/page.js">
</script>


   
   
   
</body>
</html>