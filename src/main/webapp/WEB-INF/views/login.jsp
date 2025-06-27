<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="login" id="loginForm">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="id" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="pw" name="pw"></td>
			</tr>
		</table>
	</form>
	
</body>
</html>