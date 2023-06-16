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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <title>Document</title>
    <link rel="stylesheet" href="/test/css/style.css"/>
    <link rel="stylesheet" href="/test/css/contents.css"/>
    <script src="/test/js/common.js"></script>
    <script>
    function idCheck() {
    	if ($("#id").val().trim() == "") {
    		alert("아이디를 입력하세요");
    		$("#id").focus();
    	} else {
	    	$.ajax({
	    		url:'dupCheck.do?id='+$("#id").val(),
	    		success : function(res) {
	    			if (res == 'usable') {
	    				alert('사용가능');
	    			} else {
	    				alert('사용불가');
	    				$("#id").val("");
	    			}
	    		}
	    	});
    	}
    }
    function goSave() {
    	if ($("#id").val().trim() == '') {
    		alert('아이디를 입력하세요');
    		$("#id").focus();
    		return;
    	}
    	let con = true;
    	$.ajax({
    		url:'dupCheck.do?id='+$("#id").val(),
    		async:false,
    		success : function(res) {
    			if (res == 'notusable') {
    				alert('아이디가 중복되었습니다.');
    				$("#id").val("");
    				con = false;
    			}
    		}
    	});
    	if (!con) return;
    	if ($("#pwd").val().trim() == '') {
    		alert('비밀번호를 입력하세요');
    		$("#pwd").focus();
    		return;
    	}
    	if ($("#pwd_check").val().trim() == '') {
    		alert('비밀번호 확인을 입력하세요');
    		$("#pwd_check").focus();
    		return;
    	}
    	if ($("#pwd").val() != $("#pwd_check").val()) {
    		alert("비밀번호와 비밀번호 확인값이 다릅니다.");
    		$("#pwd_check").val("");
    		$("#pwd_check").focus();
    		return;
    	}
    	if ($("#name").val().trim() == '') {
    		alert('이름을 입력하세요');
    		$("#name").focus();
    		return;
    	}
    	if ($("#email").val().trim() == '') {
    		alert('이메일을 입력하세요');
    		$("#email").focus();
    		return;
    	}
    	if ($("#gender").val() == '0') {
    		alert('성별을 선택하세요');
    		$("#gender").focus();
    		return;
    	}
    	if (confirm('가입하시겠습니까?')) {
	    	$("#frm").submit();    		
    	}
    }
    $(function() {
        $( "#birthday" ).datepicker({
             changeMonth: true, 
             dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
             dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
             monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
             monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
             dateFormat:"yy-mm-dd"
      });
    });

    </script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    function zipcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    addr += extraAddr;
	                
	                } else {
	                    //document.getElementById("sample6_extraAddress").value = '';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zipcode').value = data.zonecode;
	                document.getElementById("addr1").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("addr2").focus();
	            }
	        }).open();
	    }
	</script>
</head>
<body>
    <div class="wrap">
        <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">회원가입</h3>
                <form name="frm" id="frm" action="regist.do" method="post" enctype="multipart/form-data">
                <table class="board_write">
                    <caption>회원가입</caption>
                    <colgroup>
                        <col width="20%" />
                        <col width="*" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>*아이디</th>
                            <td>
                                <input type="text" name="id" id="id" class="inNextBtn" style="float:left;">
                                <span class="id_check"><a href="javascript:;" onclick="idCheck();" class="btn bgGray" style="float:left; width:auto; clear:none;">중복확인</a></span>
                            </td>
                        </tr>
                        <tr>
                            <th>*비밀번호</th>
                            <td><input type="password" name="pwd" id="pwd" style="float:left;"> <span class="ptxt">비밀번호는 숫자, 영문 조합으로 8자 이상으로 입력해주세요.</span> </td>
                        </tr>
                        <tr>
                            <th>*비밀번호<span>확인</span></th>
                            <td><input type="password" name="pwd_check" id="pwd_check" style="float:left;"></td>
                        </tr>
                        <tr>
                            <th>*이름</th>
                            <td><input type="text" name="name" id="name" style="float:left;"> </td>
                        </tr>
                        <tr>
                            <th>*이메일</th>
                            <td><input type="text" name="email" id="email" style="float:left;"> </td>
                        </tr>
                        <tr>
                            <th>*성별</th>
                            <td>
                            <select name="gender" id="gender">
                            <option value="0">선택</option>
                            <option value="1">남성</option>
                            <option value="2">여성</option>
                            </select> 
                            </td>
                        </tr>
                        <tr>
                            <th>생년월일</th>
                            <td><input type="text" name="birthday" id="birthday" style="float:left;" readonly> </td>
                        </tr>
                        <tr>
                            <th>전화번호</th>
                            <td>
                                <input type="text" name="tel" id="tel" value=""  maxlength="15" style="float:left;">
                            </td>
                        </tr>
                        <tr>
                            <th>우편번호</th>
                            <td>
                                <input type="text" name="zipcode" id="zipcode" value=""  maxlength="15" style="float:left;">
                                <span class="id_check"><a href="javascript:zipcode();"  class="btn bgGray" style="float:left; width:auto; clear:none; height:32px;">우편번호검색</a></span>
                            </td>
                        </tr>
                        <tr>
                            <th>주소1</th>
                            <td>
                                <input type="text" name="addr1" id="addr1" value=""  style="float:left;width:100%;">
                            </td>
                        </tr>
                        <tr>
                            <th>주소2</th>
                            <td>
                                <input type="text" name="addr2" id="addr2" value=""  style="float:left;width:100%;">
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>
                <!-- //write--->
                <div class="btnSet clear">
                    <div><a href="javascript:;" class="btn" onclick="goSave();">가입</a> <a href="javascript:;" class="btn" onclick="history.back();">취소</a></div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </div>
</body>
</html>