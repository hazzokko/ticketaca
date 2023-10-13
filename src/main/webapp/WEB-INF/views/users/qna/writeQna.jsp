<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

</head>
<c:if test="${sessionScope.user == null}">
	<jsp:forward page="${contextPath}/main" />
</c:if>
<body>
<div class="container" style="margin-top: 60px">
<div class="row">
	<div class="col-sm-3">
	</div>
	<div class="col-sm-6">
		<div class="container justify-content-center">
		<h3>문의 글 등록</h3>
		</div>
		
		<form action="${contextPath}/qna/user/userWriteQna" method="post">
		<div class="card shadow">
		<div class="card-body">
			
			<div class="form-group mb-3">
			<label for="title">제목</label>
			<input type="text" name="qnaTitle" class="form-control" required />
			</div>
			
<!-- 			<div class="form-group mb-3"> -->
<!-- 			<label for="title">작성일</label> -->
<!-- 			<input type="text" value="" class="form-control" disabled /> -->
<!-- 			</div> -->
			
			<div class="form-group mb-3">
			<label for="title">문의유형</label>
				<select class="form-select" name="qnaCate" required>
	  				<option disabled>문의유형 선택</option>
	  				<option value="상품문의">상품문의</option>
	  				<option value="회원문의">회원문의</option>
	  			</select>
			</div>
			
			<div class="form-group mb-3">
			<label for="title">글 내용</label>
			<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
			cols="50" rows="5" name="qnaContent" ></textarea>
			</div>
			
			<div class="form-group d-flex justify-content-end align-items-center">
	            <div class="mb-3">
		            <div class="text-right">
					<input type="hidden" name="userId" value="${sessionScope.user.userId}">
			            
			            <button class="btn btn-warning btn-lg" 
			             id="submit" type="submit" value="작성완료">작성완료</button>
			            
			            <button class="btn btn-danger btn-lg" 
			             id="reset" type="reset" value="초기화">초기화</button>
			             
			             <button class="btn btn-outline-secondary btn-lg" 
			             id="back" type="button" onclick="location.href='${contextPath}/qna/user/userListQna'" 
			             value="돌아가기">돌아가기</button>
		            </div>
	            </div>
            </div>
            
			
		</div>
		</div>
		</form>
		
	</div>	
</div>
</div>