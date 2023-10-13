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
		$("#insertContent").click(function() {
			const qnaNo = $('#U_qnaNO').val()
			const qnaContent = $('#Q_content').val()
			const qnaTitle = $('#U_title').val()
			const qnaCate = $('#U_category').val()
			const userId = $('#U_id').val()
			
			var qna = {
				'qnaNo' : qnaNo, 
				'qnaContent' : qnaContent,
				'qnaTitle' : qnaTitle,
				'qnaCate' : qnaCate,
				'userId' : userId
			};
			
			$.ajax({
				type : "POST",
				url : "${contextPath}/qna/admin/adminWriteQna",
				contentType : "application/json",
				data : JSON.stringify(qna),
				success : function(data, textStatus) {
					alert("답글이 작성되었습니다.");
				},
				error : function(data, textStatus) {
					alert("에러가 발생했습니다.");
				},
				complete : function(data, textStatus){
					alert("목록으로 돌아갑니다.");	
					location.href="${contextPath}/qna/admin/adminlistQna";
				}
			});
		});
	});
	
</script>
<c:if test="${sessionScope.adm == null}">
	<jsp:forward page="${contextPath}/admin/main" />
</c:if>
</head>
<body>
<div class="container" style="margin-top: 60px">
<div class="row">
	<div class="col-sm-3">
	</div>
	<div class="col-sm-6">
		<div class="container justify-content-center">
		<h3>문의 글 답글</h3>
		</div>
		
		<div class="card shadow">
		<div class="card-body">
		
			<div class="form-group mb-3">
			<label for="userId">고객 아이디</label>
			<input type="text" name="userId" id ="U_id" value="${adminDetail.userId}" class="form-control" readonly />
			</div>
			
			<div class="form-group mb-3">
			<label for="title">제목</label>
			<input type="text" name="qnaTitle" id="U_title" value="${adminDetail.qnaTitle}" class="form-control" readonly/>
			</div>
			
			<div class="form-group mb-3">
			<label for="qnaDate">작성일</label>
			<input type="text" value="${adminDetail.qnaDate}" class="form-control" readonly />
			</div>
			
			<div class="form-group mb-3">
			<label for="qnaCate">문의유형</label>
			<input type="text" value="${adminDetail.qnaCate}" id="U_category" class="form-control" readonly />
			</div>
			
			<div class="form-group mb-3">
			<label for="content">글 내용</label>
			<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
			cols="50" rows="5"  readonly>${adminDetail.qnaContent}</textarea>
			</div>
			
			<div class="form-group mb-3">
			<label for="content">답변 내용</label>
			<c:if test="${selectPno.qnaPNo != 0}">
				<c:set var="qnaContent" value="${selectPno.qnaContent}"></c:set>
			</c:if>
			<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
			cols="50" rows="5" id="Q_content" >${qnaContent}</textarea>
			</div>
			
			<div class="form-group d-flex justify-content-end align-items-center">
	            <div class="mb-3">
		            <div class="text-right">
					<!--  <input type="hidden" name="userId" value="one"> -->
			        <input type="hidden" id="U_qnaNO" value="${adminDetail.qnaNo}" />
			            
			            <button class="btn btn-outline-secondary btn-lg" 
			             id="back" type="button" onclick="location.href='${contextPath}/qna/admin/adminlistQna'" 
			             value="돌아가기">돌아가기</button>
			            
			            
			            <button class="btn btn-scondary btn-lg" 
             			id="insertContent" type="button" value="답변작성">답변작성</button>  
			             
		            </div>
	            </div>
            </div>
			
		</div>
		</div>
		
	</div>	
</div>
</div>
</body>
</html>