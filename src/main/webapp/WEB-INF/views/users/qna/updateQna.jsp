<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function() {
	$("#modfiyDetail").click(function() {
		const qnaNo = ${userDetail.qnaNo};
		const qnaTitle = $('#O_title').val();
		const qnaContent = $('#O_content').val();
		
		var qna = {
			'qnaNo': qnaNo,
			'qnaTitle': qnaTitle,
			'qnaContent': qnaContent
		};
		
		$.ajax({
			type: "PUT",
			url: "${contextPath}/qna/user/userModifyQna",
			contentType: "application/json",
			data: JSON.stringify(qna),
			success: function(data, textStatus) {
				alert("질문이 수정되었습니다.");
			},
			error: function(data, textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete: function(data, textStatus) {
				alert("목록으로 돌아갑니다.");
				location.href="${contextPath}/qna/user/userListQna?userId=${userDetail.userId}";
			}
		});
	});
});
</script>
<c:if test="${sessionScope.user == null}">
	<jsp:forward page="${contextPath}/main" />
</c:if>
</head>
<body>
<div class="container" style="margin-top: 60px">
<div class="row">
	<div class="col-sm-3">
	</div>
	<div class="col-sm-6">
		<div class="container justify-content-center">
		<h3>문의 글 수정</h3>
		</div>
		
		<div class="card shadow">
		<div class="card-body">
			
			<div class="form-group mb-3">
			<label for="title">제목</label>
			<input type="text" name="qnaTitle" id="O_title" value="${userDetail.qnaTitle}" class="form-control" />
			</div>
			
			<div class="form-group mb-3">
			<label for="qnaDate">작성일</label>
			<input type="text" value="${userDetail.qnaDate}" class="form-control" disabled />
			</div>
			
			<div class="form-group mb-3">
			<label for="qnaCate">문의유형</label>
			<input type="text" value="${userDetail.qnaCate}" class="form-control" disabled />
			</div>
			
			<div class="form-group mb-3">
			<label for="content">글 내용</label>
			<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
			cols="50" rows="5" id="O_content" >${userDetail.qnaContent}</textarea>
			</div>
			
			<div class="d-grid">
            <button class="btn btn-warning btn-lg" 
             id="modfiyDetail" type="button" value="수정하기">수정하기</button>
           	</div>
			
		</div>
		</div>
		
	</div>	
</div>
</div>		
</body>
</html>


