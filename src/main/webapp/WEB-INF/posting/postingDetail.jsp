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
				<h1 class="page-header">게시글 상세조회</h1>
				
				<form id="frmUpd" action="${pageContext.request.contextPath}/postingUpdate"
					  class="form-horizontal" method="get">
					<input type="hidden" name="board_num" value="${postingVO.board_num}"/>
					<input type="hidden" id="type" name="type" value=""/>
					<div class="form-group">
						<label for="posting_title" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="posting_title" name="posting_title"
								placeholder="제목" value="${postingVO.posting_title}" readonly/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="posting_content" class="col-sm-2 control-label">내용</label>
						<div class="col-sm-10">
							${postingVO.posting_content}
						</div>
					</div>
					
					<div class="form-group">
						<label for="attachfile" class="col-sm-2 control-label">첨부파일</label>
						
						<%-- 파일명을 가지는 라벨을 동적으로 생성하기 위해 id값을 부여해서 jquery html 속성을 사용 --%>
						<div id="file" class="col-sm-6">
							<c:forEach items="${attachList}" var="attach">
								<a href="${pageContext.request.contextPath}/attach?attach_num=${attach.attach_num}">
									${attach.filename}
								</a> <br>
							</c:forEach>
						</div>
						
						<%-- 수정, 삭제는 작성한 회원만 가능 --%>
						<%-- 수정이나 삭제버튼 클릭시 해당 게시글번호 넘겨줌 --%>
						<c:if test="${sessionScope.userVO.userId == postingVO.posting_userid}">
							<input type="hidden" name="posting_num" value="${postingVO.posting_num}"/>
							<input id="btnUpd" type="button" value="수정"/>
							<input id="btnDel" type="button" value="삭제"/>
						</c:if>
						
						<%-- 답글버튼 클릭시 해당 게시글번호를 넘겨줘서 부모게시글번호에 세팅해야함 --%>
						<input type="hidden" name="parentposting_num" value="${postingVO.posting_num}"/>
						<input id="btnIns" type="button" value="답글"/>
					</div>
				
					<%-- 댓글 목록 출력해야함 --%>
					<div class="form-group">
						<label for="reply_content" class="col-sm-2 control-label">댓글</label>
						<div class="col-sm-10">
							<c:forEach items="${replyList}" var="reply" varStatus="status">
								<input type="hidden" class="reply_num" name="reply_num" value="${reply.reply_num}"/>
								<c:choose>
									<c:when test="${reply.delete_exist == 'Y'}">
										<label>삭제된 댓글입니다.</label>
										<label>
											[${reply.reply_userid}/<fmt:formatDate value="${reply.reply_date}" pattern="yyyy-MM-dd"/>]
										</label>
									</c:when>
									<c:otherwise>
										<label>${reply.reply_content}</label>
										<label>
											[${reply.reply_userid}/<fmt:formatDate value="${reply.reply_date}" pattern="yyyy-MM-dd"/>]
										</label>
										<c:if test="${sessionScope.userVO.userId == reply.reply_userid}">
											<input class="btnUpdReply" type="button" value="댓글수정" onclick="replyNum(${reply.reply_num})"/>
											<input class="btnDelReply" type="button" value="댓글삭제" onclick="replyNum(${reply.reply_num})"/>
										</c:if>
									</c:otherwise>
								</c:choose>
								<br>
							</c:forEach>
						</div>
					</div>
					<%--  --%>
					
					<div class="form-group">
						<label for="attachfile" class="col-sm-2 control-label"></label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="reply_content"/>
						</div>
						<input id="btnInsReply" type="button" value="댓글저장"/>
					</div>
					
				</form>
				
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<script src="/SE2/js/HuskyEZCreator.js"></script>
	<script type="text/javascript">
		///////////////////////////////////////////////
		//해당 수정버튼의 reply_num값을 구하기위해 사용되는 함수
		var reply_num = "";
		function replyNum(n){
			reply_num = n;
		}
		///////////////////////////////////////////////
	
		$(document).ready(function() {
			//수정버튼 클릭이벤트
			$("#btnUpd").on("click", function(){
				$("#type").val("1");
				$("#frmUpd").submit();
			});
			
			//삭제버튼 클릭이벤트
			$("#btnDel").on("click", function(){
				$("#type").val("2");
				$("#frmUpd").submit();
			});
			
			//답글버튼 클릭이벤트
			$("#btnIns").on("click", function(){
				$("#frmUpd").attr("action", "${pageContext.request.contextPath}/postingInsert"); //속성바꿀때 사용
				$("#frmUpd").submit();
			});
			
			//댓글 등록버튼 클릭이벤트
			$("#btnInsReply").on("click", function(){
				$("#type").val("1");
				$("#frmUpd").attr("action", "${pageContext.request.contextPath}/reply");
				$("#frmUpd").attr("method", "post");
				$("#frmUpd").submit();
			});
			
			//댓글 삭제버튼 클릭이벤트
			$(".btnDelReply").on("click", function(){
				$("#type").val("2");
				$(".reply_num").val(reply_num); //해당 삭제버튼의 해당 댓글번호
				$("#frmUpd").attr("action", "${pageContext.request.contextPath}/reply");
				$("#frmUpd").attr("method", "post");
				$("#frmUpd").submit();
			});
			
			//댓글 수정버튼 클릭이벤트
			$(".btnUpdReply").on("click", function(){
				$(".reply_num").val(reply_num);
				$("#frmUpd").attr("action", "${pageContext.request.contextPath}/replyUpdate");
				$("#frmUpd").submit();
			});
		});
	</script>
</body>
</html>