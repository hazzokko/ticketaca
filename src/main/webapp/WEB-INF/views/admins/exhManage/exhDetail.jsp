<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" isELIgnored="false"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="exh" value="${exhVO}" />
<script type="text/javascript">
	function fn_exh_mod(exhNo){
		var url = "${contextPath}/admin/updateExh/" + exhNo;
	    document.location.href = url;
	 
	}
	function fn_adminListExhs(){
		var url = "${contextPath}/admin/listExhs"
		document.location.href = url;
	}
</script>
<c:if test="${sessionScope.adm == null}">
	<jsp:forward page="${contextPath}/admin/main" />
</c:if>
	<div class="container" style="margin-top: 100px">

		<!--  이 부분에 내용이 들어갑니다. -->
		<!-- 상세보기 시작 -->
		<div class="container">
		
				<!-- 전시 작품명 -->
				<h1 class="my-4">${exh.exhName}</h1>
	
				<!-- Portfolio Item Row -->
				<div class="row">	
					<c:forEach var="imageFileName" items="${map.fileList}">
						<img src="${contextPath}/download?imageFileName=${imageFileName}" />
						<br><br><br>
					</c:forEach>
					<div class="col-md-5 text-center">
						<img class="img-fluid" src="${contextPath}/download?exhNo=${exh.exhNo}&exhThumbImg=${exh.exhThumbImg}" alt="상품 이미지 입니다." width="80%">
						<%-- <img class="img-fluid" src="${contextPath}/download?exhNo=${exh.exhNo}&exhThumbImg=${exh.exhThumbImg}" alt="상품 이미지 입니다." width="80%"> --%>
						<%-- <img class="img-fluid" src=" ${contextPath}/download/${exh.exhNo}&image=${exh.exhThumbImg}" alt="상품 이미지 입니다."> --%>
					</div>
	
					<div class="col-md-6">
						<h3 class="my-3">장소</h3>
						<h5>${exh.exhPlace}</h5>
						
						<h3 class="my-3">전시기간</h3>
						<h5>${exh.exhStrDate} ~ ${exh.exhEndDate}</h5>
						
						<h3 class="my-3">관람연령 : 전체관람가</h3>
						<h5>전체관람가</h5>
						
						<h3 class="my-3">가격</h3>
						<ul>
							<li>성인 : ${exh.exhAPrice} 원</li>
							<li>청소년 : ${exh.exhYPrice} 원</li>
							<li>어린이 : ${exh.exhCPrice} 원</li>
						</ul>

							<input type="button" class="btn btn-warning" value="전체 목록 보기"
							style="margin-left:20px;" onclick="fn_adminListExhs()">
							
							<input type="button" class="btn btn-warning" value="수정"
							style="margin-left:20px;" onclick="fn_exh_mod(${exh.exhNo})">

					</div>	
				</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</div>