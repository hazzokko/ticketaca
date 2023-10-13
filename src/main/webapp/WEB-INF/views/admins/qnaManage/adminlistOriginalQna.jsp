<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 글 상세 jsp</title>
</head>
<c:if test="${sessionScope.adm == null}">
	<jsp:forward page="${contextPath}/admin/main" />
</c:if>
<body>
<div class="container" style="margin-top: 60px">
<div class="row">		
	<div class="col-sm-3">
	</div>
	<div class="col-sm-6">
		<div class="container justify-content-center">
		<h3>문의 글 내용</h3>
		</div>
		<form action="${contextPath}/qna/admin/adminWriteQnaForm" method="POST">
		<div class="card shadow">
			<div class="card-body">
			
				<div class="form-group mb-3">
				<label for="userId">고객 아이디</label>
				<input type="text" name="userId" value="${adminDetail.userId}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="title">제목</label>
				<input type="text" name="qnaTitle" value="${adminDetail.qnaTitle}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="qnaDate">작성일</label>
				<input type="text" name="qnaDate" value="${adminDetail.qnaDate}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="qnaCate">문의 유형</label>
				<input type="text" name="qnaCate" value="${adminDetail.qnaCate}" class="form-control" readonly />
				</div>
				
				<div class="form-group mb-3">
				<label for="content">글 내용</label>
				<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
				cols="50" rows="5" id="content" name="qnaContent" readonly>${adminDetail.qnaContent}</textarea>
				</div>
				
				
				<div class="form-group mb-3">
				<label for="reply">답변 내용</label>
				<c:if test="${selectPno.qnaPNo != 0}">
					<c:set var="qnaContent" value="${selectPno.qnaContent}"></c:set>
				</c:if>
				<textarea class="form-control" style="width:100%; height:200px; resize:none;" 
				cols="50" rows="5" readonly>${qnaContent}</textarea>
				</div>
				
				
				<div class="form-group d-flex justify-content-end align-items-center">
	            <div class="mb-3">
		            <div class="text-right">
					<input type="hidden" value="${adminDetail.qnaNo}" name="Detail_qnaNo" />
			            
			            <button class="btn btn-outline-secondary btn-lg" 
			             id="back" type="button" onclick="location.href='${contextPath}/qna/admin/adminlistQna'" 
			             value="돌아가기">돌아가기</button>
			            
			            <c:if test="${adminDetail.admAnswer == 'F'}">
							<button class="btn btn-secondary btn-lg btn-lg" 
              			id="submit" type="submit" value="답글 달기">답글 달기</button>
						</c:if>
			            
<!-- 			            <button class="btn btn-secondary btn-lg btn-lg"  -->
<!--               			id="submit" type="submit" value="답글 달기">답글 달기</button>   -->
			             
				            </div>
			            </div>
		            </div>
				
			
			</div>
		</div>
		</form>
	</div>
</div>	
</div>



	
	