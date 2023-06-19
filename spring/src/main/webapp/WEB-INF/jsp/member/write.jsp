<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 등록</h1>
	<form action="insert.do" method="post" enctype="multipart/form-data">
		아이디 : <input type="text" name="id"><br> 
		비밀번호 : <input
			type="password" name="pwd"><br> 
			이름 : <input type="text"
			name="name"><br> 
			이메일 : <input type="text" name="email"><br>
		성별 : <input type="radio" name="gender" value="0">남 
		<input type="radio" name="gender" value="1" checked>여<br> 
			사진 :
		<input type="file" name="filename"><br> 취미 : <input
			type="checkbox" name="hobby" value="1">게임 <input
			type="checkbox" name="hobby" value="2">영화 <input
			type="checkbox" name="hobby" value="3">독서 <input
			type="checkbox" name="hobby" value="4">낚시
		<!-- 이렇게 해두면 따로 처리 안해도 알아서 배열로 전송이 되니까 배열로 처리를 하면 된다~~ -->
		<!-- 그러고 파라미터로 받아서 서비스로 넘겨주면 된다~~ -->
		<input type="submit" value="가입">
	</form>
</body>
</html>