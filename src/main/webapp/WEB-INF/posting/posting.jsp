<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1 class="page-header">게시판</h1>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>게시글 번호</th>
							<th>제목</th>
							<th>작성자아이디</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${postingList}" var="posting">
							<c:choose>
								<%-- 삭제된 게시글 처리 --%>
								<c:when test="${posting.delete_exist == 'Y'}">
									<tr>
										<td>${posting.posting_num}</td>
										<td>삭제된 게시글입니다.</td>
										<td>${posting.posting_userid}</td>
										<td><fmt:formatDate value="${posting.posting_date}" pattern="yyyy-MM-dd"/></td>
									</tr>
								</c:when>
								
								<c:otherwise>
									<tr class="postingTr" data-postingnum="${posting.posting_num}"> <%-- 해당 게시글 클릭했을때 정보얻기위해 필요 --%>
										<td>${posting.posting_num}</td>
										<td>
											<c:if test="${posting.parentposting_num != null}">
												<%-- indent 처리(level을 얻어와서 세팅한다음 반복문으로 공백표시) --%>
												<c:set var="level" value="${posting.posting_level}"/>
												<c:forEach begin="1" end="${level}">
													&nbsp;
												</c:forEach>
												→
											</c:if>
											${posting.posting_title}
										</td>
										<td>${posting.posting_userid}</td>
										<td><fmt:formatDate value="${posting.posting_date}" pattern="yyyy-MM-dd"/></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
				
				<nav style="text-align: center;">
					<ul class="pagination">
						<%-- 첫번째 페이지 --%>
						<c:choose>
							<c:when test="${page == '1'}">
								<li class="disabled">
									<a aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/posting?board_num=${board_num}&page=1"
									aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						
						<%-- 뒤로가기 페이지 --%>
						<c:choose>
							<c:when test="${page == '1'}">
								<li class="disabled">
									<a aria-label="Previous">
										<span aria-hidden="true">&lt;</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/posting?board_num=${board_num}&page=${page-1}"
									aria-label="Previous">
										<span aria-hidden="true">&lt;</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						
						<%-- 페이지(네비게이션도 10개로 제한하며 시작페이지와 끝페이지, 마지막페이지가 해당되는 페이지의 시작페이지와 끝페이지를 가지고 세팅) --%>
						<c:choose>
							<c:when test="${startPage == lastPageStartPage}"> <%-- 마지막페이지가 해당되는 페이지의 시작페이지이면 다음로직 실행 --%>
								<c:forEach begin="${lastPageStartPage}" end="${lastPage}" var="i">
									<c:set var="active" value="" />
									<c:if test="${i == page}">
										<c:set var="active" value="active" />
									</c:if>
									<li class="${active}">
										<a href="${pageContext.request.contextPath}/posting?board_num=${board_num}&page=${i}">${i}</a>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach begin="${startPage}" end="${endPage}" var="i">
									<c:set var="active" value="" />
									<c:if test="${i == page}">
										<c:set var="active" value="active" />
									</c:if>
									<li class="${active}">
										<a href="${pageContext.request.contextPath}/posting?board_num=${board_num}&page=${i}">${i}</a>
									</li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
						<%-- 앞으로가기 페이지 --%>
						<c:choose>
							<c:when test="${page == (lastPage)}">
								<li class="disabled">
									<a aria-label="Next">
										<span aria-hidden="true">&gt;</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/posting?board_num=${board_num}&page=${page+1}"
									aria-label="Next">
										<span aria-hidden="true">&gt;</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>

						<%-- 마지막 페이지 --%>
						<c:choose>
							<c:when test="${page == (lastPage)}">
								<li class="disabled">
									<a aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/posting?board_num=${board_num}&page=${lastPage}"
									aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
				
				<form id="frmIns" action="${pageContext.request.contextPath}/postingInsert" method="get">
					<input type="hidden" name="board_num" value="${board_num}"/>
					<input type="button" id="btnIns" value="새글등록"/>
				</form>
				
				<form id ="frmDetail" action="${pageContext.request.contextPath}/postingDetail" method="get">
					<input type="hidden" id="posting_num" name="posting_num"/>
				</form>
				
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			//해당 게시글 클릭시 이벤트
			$(".postingTr").on("click", function(){
				var posting_num = $(this).data("postingnum");
				
				$("#posting_num").val(posting_num);
				$("#frmDetail").submit();
			});
			
			//새글등록 이벤트
			$("#btnIns").on("click", function(){
				$("#frmIns").submit();
			});
		});
	</script>
</body>
</html>