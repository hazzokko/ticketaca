<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="exh" value="${exhVO}" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	
	$(document).ready(function(){
		
		$('#file1').change(function(){
			var filename = $('#file1').val().split('/').pop().split('\\').pop();
			$('#exhThumbImg').attr("value",filename);
			console.log(filename);
		});
		
		$('#file2').change(function(){
			var filename = $('#file2').val().split('/').pop().split('\\').pop();
			$('#exhDetailImg').attr("value",filename);
			console.log(filename);
		});
		
	});
	
</script>
<c:if test="${sessionScope.adm == null}">
	<jsp:forward page="${contextPath}/admin/main" />
</c:if>
<!-- 전시 등록 폼 시작 -->

<div class="container my-5">
  <div class="row justify-content-center">
    <div class="col-lg-10">
      <div class="card border-0 rounded-3 shadow-lg">
        <div class="card-body p-4">
          <div class="text-center ">
            <div class="h1 fw-light my-5">전시 세부 내용 수정</div>
<!--             <p class="mb-4 text-muted">A functional, Bootstrap 5 contact form with validation built using SB Forms. Try it out!</p> -->
<!--           </div> -->

          <form name="newExh" action="${contextPath}/admin/updateExh" class="form-horizontal" method="post">

			<input type="hidden" name="exhNo" value="${exh.exhNo}" />
			
            <!-- 전시명 -->
            <div class="form-floating mb-3">
              <input class="form-control" name="exhName" type="text" value="${exh.exhName}" />
              <label for="전시명">전시명</label>
            </div>

            <!-- 전시 장소 -->
            <div class="form-floating mb-3">
              <input class="form-control" name="exhPlace" type="text" value="${exh.exhPlace}" />
              <label for="전시 장소">전시 장소</label>
            </div>
            
            <!-- 전시장 주소 -->
            <div class="form-floating mb-3">
              <input class="form-control" name="exhAddress" type="text" value="${exh.exhAddress}" />
              <label for="전시장 주소">전시장 주소</label>
            </div>
            
            <!-- 전시 기간 -->
            <br>
            <div class="input-group mb-3">
  				<span class="input-group-text">전시시작</span>
  				<input class="form-control" name="exhStrDate" type="date" value="${exh.exhStrDate}" />
  				<span class="input-group-text">전시종료</span>
  				<input class="form-control" name="exhEndDate" type="date" value="${exh.exhEndDate}" />
			</div>
            <br>
            
            <div class="h6 fw-light mb-3 text-left">입장권 가격</div>
            <div class="input-group mb-4">
  				<span class="input-group-text">성인</span>
  				<input class="form-control" name="exhAPrice" type="text" value="${exh.exhAPrice}" />
  				<span class="input-group-text">청소년</span>
  				<input class="form-control" name="exhYPrice" type="text" value="${exh.exhYPrice}" />
  				<span class="input-group-text">어린이</span>
  				<input class="form-control" name="exhCPrice" type="text" value="${exh.exhCPrice}" />
			</div>
            
            <!-- 전시 내용 -->
            <div class="h6 fw-light mb-3 text-left">전시 내용</div>
            <div class="form-floating mb-3">
            	<textarea class="form-control" style="width:100%; height:200px; resize:none;" cols="50" rows="5" name="exhContent">${exh.exhContent}</textarea>
              <label for="전시 내용">전시 내용</label>
            </div>
            
            <!-- 입장권 판매 수량 -->
            <div class="h6 fw-light mb-3 text-left">입장권 판매 수량</div>
            <div class="form-floating mb-3">
<!--               <label for="maxCnt">입장권 판매 수량</label> -->
              <input class="form-control" name="exhMaxCnt" type="text" value="${exh.exhMaxCnt}" />
            </div>
            
            <!-- 전시 상태 -->
            <div class="form-group row">
				<label class="col-sm-2">전시 상태</label>
				<div class="com-sm-5">
					<input type="radio" name="exhAbleBuy" value="진행" ${(exh.exhAbleBuy == '예정') ? 'checked' : ''}> 예정
					<input type="radio" name="exhAbleBuy" value="진행" ${(exh.exhAbleBuy == '진행') ? 'checked' : ''}> 진행
					<input type="radio" name="exhAbleBuy" value="마감" ${(exh.exhAbleBuy == '마감') ? 'checked' : ''}> 마감
					<input type="radio" name="exhAbleBuy" value="종료" ${(exh.exhAbleBuy == '종료') ? 'checked' : ''}> 종료
				</div>
			</div>
			<div class="form-group row">
					<label class="col-sm-2">게시 여부</label>
					<div class="com-sm-5">
						<input type="radio" name="exhPostStatus" value="T" ${(exh.exhPostStatus == 'T') ? 'checked' : ''}> 게시
						<input type="radio" name="exhPostStatus" value="F" ${(exh.exhPostStatus == 'F') ? 'checked' : ''}> 미게시
					</div>
				</div>
            </div>
            
            <!-- 상품 대표 이미지 -->
            <div class="form-floating mb-3 ">
            <div class="input-group mb-4">
              <span class="input-group-text">상품 대표 이미지</span>
              <!-- <input class="form-control " name="file1" type="file" id="file1" placeholder="상품 대표 이미지"  /> -->
              <input type="text" name="exhThumbImg" value="${exh.exhThumbImg}" />
              <img class="img-fluid" src="${contextPath}/download?exhNo=${exh.exhNo}&exhThumbImg=${exh.exhThumbImg}" alt="상품 이미지 입니다." width="50%">
            </div>
            </div>
            
            <!-- 상품 세부 이미지 -->
            <div class="form-floating mb-3">
            <div class="input-group mb-4">
              <span class="input-group-text">상품 세부 이미지</span>
              <!-- <input class="form-control" name="file2" type="file" id="file2" placeholder="상품 세부 이미지"  /> -->
              <input type="text" name="exhDetailImg" value="${exh.exhDetailImg}" />
              <img class="img-fluid" src="${contextPath}/download?exhNo=${exh.exhNo}&exhThumbImg=${exh.exhDetailImg}" alt="상품 이미지 입니다." width="50%">
            </div>
            </div>
            
           
            <!-- 수정하기 -->
            <div class="d-grid">
              <button class="btn btn-secondary btn-lg" type="submit" value="등록">수정 반영하기</button>
            </div>
          </form>
          <!-- End of contact form -->

        </div>
      </div>
    </div>
  </div>
</div>