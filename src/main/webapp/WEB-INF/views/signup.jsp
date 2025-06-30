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
	<h1>회원가입</h1>
	<form method="post" action="/signup" id="signupForm">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" id="id" name="id"><button type="button" id="findId">중복확인</button></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="pw" name="pw"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" id="pw2" name="pw2"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" id="birth" name="birth"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" id="email" name="email"></td>
			</tr>
		</table>
		<button type="button" id="btn">회원가입</button>
	</form>
</body>
<script>
	$('#findId').click(function(){
		$.ajax({
			type:'get',
			url:'/findSameId/'+$('#id').val(),
			success:function(data){
				if(data == true){
					alert('사용가능 아이디');
				}else{
					alert('사용불가 아이디');
					$('#id').val('');
				}
			}
		});
	
	});
</script>
</html>