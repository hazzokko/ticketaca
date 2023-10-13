<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %>
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
            <div class="h1 fw-light my-5">신규 전시 등록</div>
<!--             <p class="mb-4 text-muted">A functional, Bootstrap 5 contact form with validation built using SB Forms. Try it out!</p> -->
          </div>

          <form name="newExh" action="${contextPath}/admin/insertExh" class="form-horizontal" method="POST" enctype="multipart/form-data" >

			<input type="hidden" name="exhNo" value="${exhNo}" />
			
            <!-- 전시명 -->
            <div class="form-floating mb-3">
              <input class="form-control" name="exhName" type="text" placeholder="전시작품 명" required />
              <label for="전시명">전시명</label>
            </div>

            <!-- 전시 장소 -->
            <div class="form-floating mb-3">
              <input class="form-control" name="exhPlace" type="text" placeholder="전시장소" required />
              <label for="전시 장소">전시 장소</label>
            </div>
            
            <!-- 전시장 주소 -->
            <div class="form-floating mb-3">
              <input class="form-control" name="exhAddress" type="text" placeholder="전시장 주소" required />
              <label for="전시장 주소">전시장 주소</label>
            </div>
            
            <!-- 전시 기간 -->
            <br>
            <div class="input-group mb-3">
  				<span class="input-group-text">전시시작</span>
  				<input class="form-control" name="exhStrDate" type="date" placeholder="전시 시작 일자" required />
  				<span class="input-group-text">전시종료</span>
  				<input class="form-control" name="exhEndDate" type="date" placeholder="전시 종료 일자" required />
			</div>
            <br>
            
            <div class="h6 fw-light mb-3 text-left">입장권 가격</div>
            <div class="input-group mb-4">
  				<span class="input-group-text">성인</span>
  				<input class="form-control" name="exhAPrice" type="text" placeholder="성인 가격" required />
  				<span class="input-group-text">청소년</span>
  				<input class="form-control" name="exhYPrice" type="text" placeholder="청소년 가격" required />
  				<span class="input-group-text">어린이</span>
  				<input class="form-control" name="exhCPrice" type="text" placeholder="어린이 가격" required />
			</div>
            
            <!-- 전시 내용 -->
            <div class="h6 fw-light mb-3 text-left">전시 내용</div>
            <div class="form-floating mb-3">
            	<textarea class="form-control" style="width:100%; height:200px; resize:none;" cols="50" rows="5" name="exhContent"></textarea>
              <label for="전시 내용">전시 내용</label>
            </div>
            
            <!-- 입장권 판매 수량 -->
            <div class="h6 fw-light mb-3 text-left">입장권 판매 수량</div>
            <div class="form-floating mb-3">
<!--               <label for="maxCnt">입장권 판매 수량</label> -->
              <input class="form-control" name="exhMaxCnt" type="text" placeholder="입장권 판매 수량" required />
            </div>
            
            <!-- 전시 상태 -->
            <div class="d-flex mb-3 justify-content-start align-items-center">
            <div class="h6 fw-light mb-3 text-left">전시 상태</div>
            <div class="form-floating col-3 mb-3" style="margin-left: 15px;">
             
              <select class="form-select" name="exhAbleBuy" > 				
  				<option ></option>
  				<option value="예정">예정</option>
  				<option value="진행">진행</option>
  				<option value="마감">마감</option>
  				<option value="종료">종료</option>			
				</select>
			</div> 
			<div class="h6 fw-light mb-3 text-left" style="margin-left: 15px;">게시 여부</div>
				<div class="form-floating col-3 mb-3" style="margin-left: 15px;">
				
				<select class="form-select" name="exhPostStatus" > 				
  				<option ></option>
  				<option value="T">게시</option>
  				<option value="F">미게시</option>			
				</select>	 
            </div>
            </div>
            
            <!-- 상품 대표 이미지 -->
            <div class="form-floating mb-3 ">
            <div class="input-group mb-4">
              <span class="input-group-text">상품 대표 이미지</span>
              <input class="form-control " name="file1" type="file" id="file1" placeholder="상품 대표 이미지"  />
              <input class="form-control " name="exhThumbImg" id="exhThumbImg" type="hidden" />
            </div>
            </div>
            
            <!-- 상품 세부 이미지 -->
            <div class="form-floating mb-3">
            <div class="input-group mb-4">
              <span class="input-group-text">상품 세부 이미지</span>
              <input class="form-control" name="file2" type="file" id="file2" placeholder="상품 세부 이미지"  />
              <input class="form-control" name="exhDetailImg" id="exhDetailImg" type="hidden" />
            </div>
            </div>
            
           
            <!-- 등록하기 -->
            <div class="d-grid">
              <button class="btn btn-secondary btn-lg" type="submit" value="등록">등록</button>
              <br>
              <button class="btn btn-secondary btn-lg" type="reset" value="다시 입력">다시 입력</button>
            </div>
          </form>
          <!-- End of contact form -->

        </div>
      </div>
    </div>
  </div>
</div>