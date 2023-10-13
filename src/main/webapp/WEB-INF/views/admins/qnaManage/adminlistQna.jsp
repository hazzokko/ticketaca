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
<title>admin Q&A 목록 jsp</title>
</head>
<c:if test="${sessionScope.adm == null}">
	<jsp:forward page="${contextPath}/admin/main" />
</c:if>
<body>
	<div class="container mt-9" style="margin-top: 60px">
		<div class="row">
		<div class="container-fluid">
			<div class="card shadow">
			<div class="card-body">
			<table class="table table-hover" id='board_list'>
			
			<thead>
			<tr>
			<th class="text-center d-none d-md-table-cell">글번호</th>
			<th class="text-center d-none d-md-table-cell">작성 아이디</th>
			<th class="text-center d-none d-md-table-cell">제목</th>
			<th class="text-center d-none d-md-table-cell">작성일</th>
			<th class="text-center d-none d-md-table-cell">답글 여부</th>
			</tr>
			</thead>
			
			
			<tbody>
			<c:if test="${empty adminlist}">
				<td colspan="5" style="text-align: center;">등록 문의글이 없습니다.</td>
			</c:if>
			<c:if test="${not empty adminlist}">
			<c:forEach var="adminqna" items="${adminlist}" varStatus="adminStatus">
			<tr>
			<td class="text-center d-none d-md-table-cell">${adminStatus.count}</td>
			<td class="text-center d-none d-md-table-cell">${adminqna.userId}</td>
			<td class="text-center d-none d-md-table-cell">
				<a href="${contextPath}/qna/admin/admindetailQna?qnaNo=${adminqna.qnaNo}">
				${adminqna.qnaTitle}
				</a>
			</td>
			<td class="text-center d-none d-md-table-cell">${adminqna.qnaDate}</td>
			<td class="text-center d-none d-md-table-cell">
				<c:choose>
				<c:when test="${adminqna.admAnswer eq 'F' }">
					답변 대기
				</c:when>
				<c:otherwise>
					답변 완료
				</c:otherwise>
				</c:choose>
			</td>
			
			</tr>
			</c:forEach>
			</c:if>
			</tbody>
			
			</table>
			<br>
				
			</div>
			</div>
		</div>
		</div>	
	</div>