<%@ page language="java"   pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="600">
		<tr>
			<td>序号</td>
			<td>名字</td>
			<td>money</td>
			<td>生日</td>
			<td>删除</td>
			<td>编辑</td>
		</tr>
		<c:forEach items="${userList }" var="user" varStatus="i">
			<tr>
				<td>${((pb.pageNum-1)*pb.pageRow+i.index+1) }</td>
				<td>${user.uname }</td>
				<td>${user.umoney }</td>
				<td>${user.ubirth }</td>
				<td><a href="user.do?method=delUser&uid=${user.uid }">删除</a></td>
				<td><a href="user.do?method=editForm&uid=${user.uid }">编辑</a></td>
			</tr>
		</c:forEach>
		<tr align="center">
			<td colspan="6">
				<a href="user.do?pageNum=1">首页</a>
				<a href="user.do?pageNum=${pb.pageNum==1?1:pb.pageNum-1 }">上一页</a>
				<a href="user.do?pageNum=${pb.pageNum==pb.countPage?pb.countPage:pb.pageNum+1 }">下一页</a>
				<a href="user.do?pageNum=${pb.countPage }">尾页</a>
			</td>
		</tr>
	</table>
	<button onclick="location.href='add.jsp'">增加</button>
</body>
</html>