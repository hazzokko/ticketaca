<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<script type="text/javascript">
	function sendwrite() {
		document.location = "${contextPath}/qna/user/userWriteForm";
	}
 	
</script>
<c:if test="${sessionScope.user == null}">
	<jsp:forward page="${contextPath}/main" />
</c:if>
<style>
	td_cell{
	text-align:left;
	}
</style>
</head>
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
			<th class="text-center d-none d-md-table-cell">제목</th>
			<th class="text-center d-none d-md-table-cell">작성일</th>
			</tr>
			</thead>
			
			
			<tbody>
			<c:if test="${empty userListQna}">
				<td colspan="3" style="text-align: center;">문의글이 없습니다.</td>
			</c:if>
			<c:if test="${not empty userListQna}">
			<c:forEach var="qna" items="${userListQna}" varStatus="qnanum">
			<tr>
			<td class="text-center d-none d-md-table-cell">${qnanum.count}</td>
			<td class="text-center d-none d-md-table-cell" id="td_cell">
				<c:choose>
				<c:when test='${qna.level > 1 }'>
					<span style="padding-left: 10px"></span>
					<span style="font-size: 12px;">[답변]</span>
					<b>${qna.qnaTitle}</b>
					</c:when>
				<c:otherwise>
					<a href="${contextPath}/qna/user/userDetailQna?userId=${qna.userId}&qnaNo=${qna.qnaNo}">
					${qna.qnaTitle} 
					</a>
				</c:otherwise>
				</c:choose></td>
			<td class="text-center d-none d-md-table-cell">${qna.qnaDate}</td>
			</tr>
			</c:forEach>
			</c:if>
			</tbody>
			
			</table>
			<br>
				<button class="btn btn-warning" onclick="sendwrite()">문의하기</button>
			</div>
			</div>
		</div>
		</div>	
	</div>
	