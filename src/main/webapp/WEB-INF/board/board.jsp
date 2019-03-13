<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">

</head>
<body>
	<%@ include file="/header.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">게시판 생성</h1>
				<form id="frmIns" action="${pageContext.request.contextPath}/board" method="post">
					<input type="hidden" name="type" value="1"/>
					<label>게시판이름</label>
					<label><input type="text" id="board_name_ins" name="board_name_ins"/></label>
					<label>
						<select id="use_exist_ins" name="use_exist_ins">
							<option value="Y">사용</option>
							<option value="N">미사용</option>
						</select>
					</label>
					<label><input id="btnIns" type="button" value="생성"/></label> <br>
					<label>===============================================</label> <br>
				</form>
				<c:forEach items="${boardList}" var="board">
					<form class="frmUpd" action="${pageContext.request.contextPath}/board" method="post">
						<input type="hidden" name="type" value="2"/>
						<label>게시판이름<input type="hidden" name="board_num_upd" value="${board.board_num}"/></label>
						<label><input type="text" name="board_name_upd" value="${board.board_name}"/></label>
						<label>
							<select class="use_exist_upd" name="use_exist_upd">
								<option value="Y">사용</option>
								<option value="N" <c:if test="${board.use_exist == 'N'}">selected</c:if>>미사용</option>
							</select>
						</label>
						<label><input type="button" class="btnUpd" value="수정"/></label> <br>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			//생성버튼 이벤트
			$("#btnIns").on("click", function(){
				if($("#board_name_ins").val().trim() == ""){
					alert("게시판 이름을 입력해주세요!");
					$("#board_name_ins").focus();
					return false;
				}else{
					$("#frmIns").submit();
				}
			});
			
			//수정버튼 이벤트
			$(".btnUpd").on("click", function(){
				$(this).parents(".frmUpd").submit(); //선택한 수정버튼의 부모 form 객체 - 중요!!!
			});
			
			//select box 이벤트
			$(".use_exist_upd").on("change", function(){
				$(this).parents(".frmUpd").submit(); //선택한 수정버튼의 부모 form 객체 - 중요!!!
			});
		});
	</script>
</body>
</html>