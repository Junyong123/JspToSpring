<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<ul class="nav nav-sidebar">
	<li><a href="${pageContext.request.contextPath}/board">게시판 생성</a></li>
</ul>

<ul class="nav nav-sidebar">
	<!-- boardList는 session영역에 있는 값을 가져오는데 맨처음 login에서 초기 전체게시판리스트를 session에 설정하고, 
		 board에서 등록이나 수정시에 다시 전체게시판리스트를 session에 설정 -->
	<c:forEach items="${sessionScope.boardList}" var="board">
		<c:if test="${board.use_exist == 'Y'}">
		    <li><a href="${pageContext.request.contextPath}/posting?board_num=${board.board_num}">${board.board_name}</a></li>
		</c:if>
	</c:forEach>
</ul>