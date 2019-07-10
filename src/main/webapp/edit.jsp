<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="user.do?method=editUser" method="post">
		<input type="text" name="uid" value="${user.uid }"><br>
		姓名：<input type="text" name="uname" value="${user.uname }"><br>
		工资：<input type="text" name="umoney" value="${user.umoney }"><br>
		生日：<input type="text" name="ubirth" value="${user.ubirth }"><br>
		<button>修改</button>
	</form>
</body>
</html>