<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
alert('${msg}');

<c:if test ="${empty url}">
	/* 만약에 비어있으면~ 실행해라 */
history.back(); 
	/* history.back()를 하면 이전페이지로 돌아가도 이전 데이터들은 남아있음 */
</c:if>

<c:if test ="${!empty url}">
location.href='${url}';
</c:if>
</script>