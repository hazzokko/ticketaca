<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %> 
 <style>
   .text-right {
    text-align: right;
	}
</style>
<div class="container mt-9" style="margin-top: 60px">

		<!--  이 부분에 내용이 들어갑니다. -->
		
		<div class="row">
			<div class="col-sm-2">
				<h3 class="mt-4">전시 분류</h3>
				<hr>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/admin/listExhs">전체 전시</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/admin/listExhsOngoing">진행 중 전시</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/admin/listExhsNearingDeadline">마감 임박</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/admin/listExhsUpcoming">예정 전시</a></li>
					<li class="nav-item"><a class="nav-link text-body" href="${contextPath}/admin/listExhsClosing">종료 전시</a></li>
				<!-- <li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li> -->
				</ul>
<!-- 				<h3 class="mt-4">지역별 분류</h3> -->
<!-- 				<hr> -->
<!-- 				<ul class="nav nav-pills flex-column"> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">서울</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">경기</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">인천</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">부산</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">대구</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">광주</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">충청</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link text-body" href="#">제주</a></li> -->
<!-- 				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li> -->
<!-- 				</ul> -->
			</div>
			<div class="col-sm-10">
				<div class="card shadow">
					<div class="card-body">
						<h4 class="card-title">마감 임박 전시 목록</h4>
						<table class="table table-hover" id='board_list'>
							<thead>
								<tr>
									<th class="text-center d-none d-md-table-cell">구분</th>
									<th class="text-center d-none d-md-table-cell">전시명</th>
									<th class="text-center d-none d-md-table-cell">장소</th>
									<th class="text-center d-none d-md-table-cell">시작일</th>
									<th class="text-center d-none d-md-table-cell">종료일</th>
								</tr>
							</thead>
					<c:choose>
						<c:when test="${empty exhsList}">											
							<tbody>
								<tr  height="10">
      								<td colspan="4">
         								<p align="center">
											<b><span style="font-size:9pt;">등록된 전시가 없습니다.</span></b>
        								</p>
      								</td>  
    							</tr>
							</tbody>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${!empty exhsList}">
							<c:forEach var="exh" items="${exhsList}">										
							<tbody>
								<tr>
									<td class="text-center d-none d-md-table-cell">${exh.exhPostStatus}</td>
									<%-- <td class="text-center d-none d-md-table-cell"><a href="${contextPath}/admin/detailExh/${exh.exhNo}/${exh.exhThumbImg}">${exh.exhName}</a></td> --%>
									<td class="text-center d-none d-md-table-cell"><a href="${contextPath}/admin/detailExh?exhNo=${exh.exhNo}&exhThumbImg=${exh.exhThumbImg}">${exh.exhName}</a></td>
									<td class="text-center d-none d-md-table-cell">${exh.exhPlace}</td>
									<td class="text-center d-none d-md-table-cell">${exh.exhStrDate}</td>
									<td class="text-center d-none d-md-table-cell">${exh.exhEndDate}</td>
									<td class="text-center d-none d-md-table-cell"><a href="${contextPath}/admin/updateExh/${exh.exhNo}">수정</a></td>
									<td class="text-center d-none d-md-table-cell"><a href="${contextPath}/admin/deleteExh/${exh.exhNo}">삭제</a></td>
								</tr>
							</tbody>
							</c:forEach>
						</c:when>
					</c:choose>
						</table>
						<div class="text-right"> <%-- ############ --%>
							<a href="${contextPath}/admin/insertExh">
						        		<input type="button" class="btn btn-primary" value="신규 전시 등록">
						    	</a>
						    <a href="${contextPath}/admin/listExhs">
						        		<input type="button" class="btn btn-outline-secondary" value="전체 목록 보기">
						    </a>
						</div>
						<!--  더미 페이징 기능 -->
						<ul class="pagination justify-content-center ">
							<li class="page-item"><a class="page-link text-dark" href="#">이전</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">1</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">2</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">3</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">4</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">5</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">6</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">7</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">8</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">9</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">10</a></li>
							<li class="page-item"><a class="page-link text-dark" href="#">다음</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>	