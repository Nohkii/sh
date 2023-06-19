<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원목록 (로그인:${loginSession })</h1>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>이메일</th>
		<th>가입일</th>
	</tr>
<c:forEach var="vo" items="${list}">
	<tr>
		<td>${vo.id }</td>
		<td>${vo.pwd }</td>
		<td>${vo.name }</td>
		<td>${vo.email }</td>
		<td>${vo.regdate }</td>
	</tr>
</c:forEach>
</table>

<a href="javascript:move();">회원 등록</a>

<script>
function move() {
	<c:if test="${empty loginSession }">
	alert('로그인후 사용 가능합니다.');
	</c:if>
	<c:if test="${!empty loginSession }">
	location.href="write.do";
	</c:if>
}
</script>







</body>
</html>