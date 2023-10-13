<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
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
		<h3>문의 글 내용</h3>
		</div>
		<form action="${contextPath}/qna/user/userModifyQnaFrom">
		<div class="card shadow">
			<div class="card-body">
			
				<div class="form-group mb-3">
				<label for="title">제목</label>
				<input type="text" name="qnaTitle" value="${userDetail.qnaTitle}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="qnaDate">작성일</label>
				<input type="text" name="qnaDate" value="${userDetail.qnaDate}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="qnaCate">문의 유형</label>
				<input type="text" name="qnaCate" value="${userDetail.qnaCate}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="content">글 내용</label>
				<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
				cols="50" rows="5" id="content" name="qnaContent" readonly>${userDetail.qnaContent}</textarea>
				</div>
				
				<input type="hidden" value="${userDetail.qnaNo}" name="qnaNo" />
				<div class="form-group mb-3">
				<label for="reply">답변 내용</label>
				<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
				cols="50" rows="5" readonly>${DetailContent.qnaContent}</textarea>
				</div>
				
				<input type="hidden" name="userId" value="${userDetail.userId}" />
				<div class="form-group d-flex justify-content-end align-items-center">
                    <div class="mb-3">
                    <div class="text-right">
                       <button type="button" class="btn btn-outline-secondary" 
                       id="button" onclick="location.href='${contextPath}/qna/user/userListQna'">
                       목록</button>
                       <button type="submit" class="btn btn-warning" id="submit">수정</button>                                    
                    </div>
                </div>
                </div>
							
			</div>
		</div>
		</form>
	</div>
</div>	
</div>
	