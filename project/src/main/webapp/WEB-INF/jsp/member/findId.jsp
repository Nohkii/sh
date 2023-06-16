<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <title>Document</title>
    <link rel="stylesheet" href="/test/css/style.css"/>
    <link rel="stylesheet" href="/test/css/contents.css"/>
    <script src="/test/js/common.js"></script>
    <script>
    	function findId() {
    		if ($("#name").val().trim() == '') {
    			alert('이름을 입력하세요');
    			$("#name").focus();
    			return false;
    		}
    		if ($("#email").val().trim() == '') {
    			alert('이메일을 입력하세요');
    			$("#email").focus();
    			return false;
    		}
    		$.ajax({
    			url : 'findId.do',
    			method:'post',
    			data : {
    				name : $("#name").val(),
    				email : $("#email").val()
    			},
    			success : function(res) {
    				if (res == '') {
    					$("#msg").text("해당 회원이 존재하지 않습니다.");
    				} else {
    					$("#msg").text("아이디는 '"+res+"' 입니다.");
    				}
    			}
    		})
    		return false;
    	}
    </script>
</head>
<body>
    <div class="wrap">
        <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
        <form action="findId.do" method="post" id="board1" name="board1" onsubmit="return findId();"><!-- header에서 id="board"이미 사용중이라서 board2로 함 -->
            <div class="sub">
                <div class="size">
                    <h3 class="sub_title">아이디 찾기</h3>
                    
                    <div class="member">
                        <div class="box">
                            <fieldset class="login_form">
                                <ul>
                                    <li><input type="text" id="name" name="name" placeholder="이름"></li>
                                    <li><input type="text" id="email" name="email" placeholder="이메일"></li>
                                    <li id="msg"></li>
                                </ul>
                                <div class="login_btn"><input type="submit" value="아이디 찾기" alt="아이디 찾기" /></div>
                            </fieldset>
                            <div class="btnSet clear">
                                <div>
                                    <a href="login.do" class="btn">로그인</a> 
                                    <a href="findPwd.do" class="btn">비밀번호 찾기</a>
                                </div>
                            </div>
                        </div>
                    </div>
        
                </div>
            </div>
        </form>
        <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </div>
</body>
</html>