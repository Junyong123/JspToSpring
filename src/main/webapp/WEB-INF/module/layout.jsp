<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/css/dashboard.css" rel="stylesheet">
	
	 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
  </head>

  <body>
	<tiles:insertAttribute name="header"/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
        	<tiles:insertAttribute name="left"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
         	<tiles:insertAttribute name="content"/>
        </div>
      </div>
    </div>

	<script>
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function(){
			console.log("document ready");
			
			//사용자 tr 태그 클릭시 이벤트 핸들러
// 			$(".userTr").click(function(){
// 			});
			
			$(".userTr").on("click", function(){
				console.log("userTr click");
				//클릭한 userTr태그의 userId 값을 출력
// 				console.log($(this).children()[1].innerText);
// 				console.log("data-userid : " + $(this).data("userid"));
				
				var userId = $(this).data("userid");
				
				// 1.document
// 				document.location = "/user?userId=" + userId;
				
				// 2.form
				$("#userId").val(userId);
// 				$("#frm").attr("action", "/user"); //속성바꿀때 사용
				$("#frm").submit();
				
			});
			
		});
	</script>
	
	<%
		pageContext.getRequest().equals(request);
		pageContext.getSession().equals(session);
		
		// 1
		request.getContextPath();
		((HttpServletRequest)pageContext.getRequest()).getContextPath();
		application.getContextPath();
		
		// 2
		((HttpServletRequest)pageContext.getRequest()).getContextPath();
		pageContext.getServletContext().getContextPath();
		
		// el은 request나 context에 직접 접근이 불가능하다
	%>
	
	<form id ="frm" action="${pageContext.servletContext.contextPath }/user" method="get">
		<input type="hidden" id="userId" name="userId"/>
	</form>
  </body>
</html>