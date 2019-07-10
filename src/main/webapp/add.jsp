<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="user.do?method=addUser" method="post">
		姓名：<input type="text" name="uname"><br>
		工资：<input type="text" name="umoney"><br>
		生日：<input type="text" name="ubirth"><br>
		<button>添加</button>
	</form>
</body>
</html>