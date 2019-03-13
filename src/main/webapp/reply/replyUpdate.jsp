<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<h1 class="page-header">댓글수정</h1>
				<form id="frmUpd" action="${pageContext.request.contextPath}/replyUpdate" method="post">
					<input type="hidden" name="reply_num" value="${replyVO.reply_num}"/>
					<textarea rows="10" cols="150" name="reply_content">${replyVO.reply_content}</textarea> <br>
					<input type="button" id="btnUpd" value="저장"/>
				</form>
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<script src="/SE2/js/HuskyEZCreator.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		//댓글 수정 버튼 클릭이벤트
		$("#btnUpd").on("click", function(){
			$("#frmUpd").submit();
		});
	});
	</script>
</body>
</html>