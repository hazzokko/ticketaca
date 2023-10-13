<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
 	th {
 	font-size: 10px;
 }
</style>
<c:if test="${empty sessionScope.adm}">
	<script>
		document.location = "${contextPath}/admin/main";
	</script>
</c:if>	

</head>
<div class="container mt-9" style="margin-top: 60px">



		<div class="row">
		<!-- 메뉴 제목 -->
		<h3>주문 목록</h3>

		<!-- 주문 목록 시작 -->
		<div class="container-fluid">
		<div class="card shadow">
		<div class="card-body">
		<table class="table table-hover table-responsive" id='board_list'>
			<thead>
				<tr>
				<!-- 분류 이름 -->
				<th class="text-center d-none d-md-table-cell">INDEX</th>
				<th class="text-center d-none d-md-table-cell">주문번호</th>
				<th class="text-center d-none d-md-table-cell">구매일자</th>
				<th class="text-center d-none d-md-table-cell">유저ID</th>
				<th class="text-center d-none d-md-table-cell">성함</th>
				<th class="text-center d-none d-md-table-cell">전화번호</th>
				<th class="text-center d-none d-md-table-cell">전시명</th>
				<th class="text-center d-none d-md-table-cell">시작일자</th>
				<th class="text-center d-none d-md-table-cell">종료일자</th>
				<th class="text-center d-none d-md-table-cell">성인</th>
				<th class="text-center d-none d-md-table-cell">청소년</th>
				<th class="text-center d-none d-md-table-cell">어린이</th>
				<th class="text-center d-none d-md-table-cell">총수량</th>
				<th class="text-center d-none d-md-table-cell">총액</th>
				<th class="text-center d-none d-md-table-cell">결제수단</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="order" items="${allList}" varStatus="status">				
				<tr>
				<td class="text-center d-none d-md-table-cell">${status.count}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordNo}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordDate}</td>
				<td class="text-center d-none d-md-table-cell">${order.userId}</td>
				<td class="text-center d-none d-md-table-cell">${order.userName}</td>
				<td class="text-center d-none d-md-table-cell">${order.userTel}</td>
				<td class="text-center d-none d-md-table-cell">${order.exhName}</td>
				<td class="text-center d-none d-md-table-cell">${order.exhStrDate}</td>
				<td class="text-center d-none d-md-table-cell">${order.exhEndDate}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordAQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordYQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordCQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordTQnt}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordPrice}</td>
				<td class="text-center d-none d-md-table-cell">${order.ordPay}</td>					
				</tr>
				
			</c:forEach>	
			</tbody>
		</table>
		
<%-- 	</c:when> --%>
<%-- </c:choose> --%>

<form method="get" action="${contextPath}/order/searchOrder">
	<div class="context mb-5 mt-5">
		<select class="form-control" id="type" name="type" style="width:100px; float:left; margin-left:20px;">
			<option value="orderNo">주문번호</option>
			<option value="exhNo">작품번호</option>
			<option value="userId">아이디</option>
		</select>
		<div class="aa">
		<input type="text" name="keyword" class="form-control" style="width:200px;float:left; margin-left:20px;"/>			
		<input type="submit" value="검색" class="btn btn-primary" style="width:80px; float:left; margin-left:20px;"/>
		</div>
	</div>
</form>

</div>
</div>
</div>
</div>
</div>



