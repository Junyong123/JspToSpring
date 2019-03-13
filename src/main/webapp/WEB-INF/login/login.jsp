<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="${pageContext.request.contextPath}/login" method="post">
			<input type="text" id="userId" name="userId"/> <br>
			<input type="password" id="pass" name="pass"/> <br>
			<label><input type="checkbox" id="rememberme" value="rememberme">remember me</label> <br>
			
			<input type="button" id="signin" value="LOGIN"/>
		</form>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/js.cookie.js"></script> <!-- cookie 관련된 기능 처리에 필요 -->
	<script>
		//초기화 데이터
		function initdata(){
			$("#userId").val("brown");
			$("#pass").val("brown1234");
		}
	
		$(document).ready(function(){
			initdata();
			
			//userId 쿠키 값이 있을경우 userId input에 설정
	  		if(Cookies.get("userId")){
	  			$("#userId").val(Cookies.get("userId"));
	  			$("#rememberme").prop("checked", true); //체크박스 속성은 prop 사용
	  		}
			
			//signin button 클릭 이벤트 핸들러
	  		$("#signin").on("click", function(){
	  			// 1.rememberme 체크박스가 체크 되었을 경우
	  			//   사용자 아이디 input에 저장된 값을
	  			//   cookie이름 : userId / cookieValue : 입력된 값으로 쿠키를 생성
	  			//   유효기간 30일로 설정하는 로직
	  			// 2.rememberme 체크박스가 체크 되어있지 않을경우
	  			//   cookie이름 : userId --> cookie 삭제
	  			
	  			//rememberme 체크박스 체크 된 경우
	  			if($("#rememberme").prop("checked")){
	  				Cookies.set("userId", $("#userId").val(), {expires : 30}); //이미 정의된 js.cookie.js 파일에서 set 메서드를 사용하는방법(name, value, 유효기간)
	  				Cookies.set("rememberme", "y", {expires : 30});
	  			}else{
	  				Cookies.remove("userId");
	  				Cookies.remove("rememberme");
	  			}
	  			
	  			$("form").submit(); //form이 하나이면 form 자체를 의미하는 코드(태그가 페이지에 한개일때 적용가능)
	  			
	  		});
			
		});
	</script>
</body>
</html>