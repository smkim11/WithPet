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
				<td>
					<input type="text" id="id" name="id"><button type="button" id="findId">중복확인</button>
					<span style="color:red" id="idMsg">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="pw" name="pw">
					<span style="color:red" id="pwMsg" ></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="pw2" name="pw2">
					<span style="color:red" id="pw2Msg">
				</td>
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
					$('#idMsg').text('사용가능 아이디.');
				}else{
					$('#idMsg').text('사용불가 아이디.');
					$('#id').val('');
				}
			}
		});
	});
	
	$('#pw').blur(function(){
		if($('#pw').val().length<8){
			$('#pwMsg').text('비밀번호는 8자 이상입니다.');
			$('#pw').val('');
		}
	});
	$('#pw2').blur(function(){
		if($('#pw').val() !== $('#pw2').val()){
			$('#pw2Msg').text('비밀번호가 일치하지 않습니다.');
			$('#pw2').val('');
		}
	});
</script>
</html>