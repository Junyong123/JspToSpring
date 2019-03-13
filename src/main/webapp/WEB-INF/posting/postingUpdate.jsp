<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<h1 class="page-header">게시판수정</h1>
				
				<form id="frmIns" action="${pageContext.request.contextPath}/postingUpdate"
					  class="form-horizontal" method="post" enctype="multipart/form-data">
					<input type="hidden" name="posting_num" value="${postingVO.posting_num}"/>
					<div class="form-group">
						<label for="posting_title" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="posting_title" name="posting_title"
								placeholder="제목" value="${postingVO.posting_title}"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="posting_content" class="col-sm-2 control-label">내용</label>
						<div class="col-sm-10">
							<textarea id="smarteditor" name="posting_content" rows="10" cols="100" style="width:766px; height:412px;">
								${postingVO.posting_content}
							</textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label for="attachfile" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-7">
							<%-- 파일명을 가지는 라벨을 동적으로 생성하기 위해 id값을 부여해서 jquery html 속성을 사용 --%>
							<%-- 기존의 파일리스트 --%>
							<c:forEach items="${attachList}" var="attach">
								<label>${attach.filename}</label>
								<a href="${pageContext.request.contextPath}/attachDelete?posting_num=${postingVO.posting_num}&attach_num=${attach.attach_num}">x</a>
								<br>
							</c:forEach>
							
							<%-- 새로 추가된 파일리스트 --%>
							<div id="file"></div>
						</div>
						
						<%-- 파일속성은 value값 문자를 지정할 수 없기때문에 file속성이 들어간 태그의 id값과 일치하는 label을 만들어서 문자를 따로 지정해야함 --%>
						<label for="attach">┽</label>
						<input id="attach" name="attach" type="file" multiple style="display: none;"/>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input id="btnIns" type="button" value="저장"/>
						</div>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<%-- 스마트에디터 부분 --%>
	<script src="/SE2/js/HuskyEZCreator.js"></script>
	<script type="text/javascript">
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
	
	// 필수값 Check
	function validation(){
		var title = $("#posting_title").val().trim();
		if(title == ""){
			alert("제목을 입력하세요.");
			$("#posting_title").focus();
			return false;
		}
		
		var contents = $.trim(oEditors[0].getContents());
		if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}
	
		return true;
	}
	
	$(document).ready(function() {
		// Editor Setting
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors, // 전역변수 명과 동일해야 함.
			elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
			sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
			fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true, 
			}
		});
		
		//첨부파일 이벤트
		$("#attach").on("change", function(){
			var attach = document.getElementById("attach");
			var files = attach.files;
			var filenameLabel = "";
			
			//파일 개수제한
			if(files.length > 5){
				alert("첨부파일은 5개까지 가능합니다.");
				return;
			}
			
			for(var i=0; i<files.length; i++){
				console.log(files[i].name);
				filenameLabel += "<label>"+files[i].name+"</label> <br>"; //동적으로 파일명을 가지고 라벨 생성
			}
			
			//파일명을 가지는 라벨을 동적으로 생성하기 위해 id값을 부여해서 jquery html 속성을 사용
			$("#file").html(filenameLabel);
		});
	
		//전송버튼 클릭이벤트
		$("#btnIns").on("click", function(){
			if(confirm("저장하시겠습니까?")) {
				// id가 smarteditor인 textarea에 에디터에서 대입
				oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
				
				// 이부분에 에디터 validation 검증
				if(validation()) {
					$("#frmIns").submit();
				}
			}
		});
		
	});
	</script>
</body>
</html>