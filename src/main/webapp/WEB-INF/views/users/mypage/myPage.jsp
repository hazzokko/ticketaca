<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	function fn_checkPw() {
		var inputPw = $("#floatingPassword").val();
		if (inputPw == '') {
			alert("비밀번호를 입력하세요");
			return;
		}
		if (inputPw == '${sessionScope.user.userPw}') {
			document.location = '${contextPath}/user/myPage/mod';
		} else {
			alert("비밀번호를 확인해주세요.");
			return;
		}
	}
</script>
<c:if test="${empty sessionScope.user}">
	<script>
		document.location = "${contextPath}/main";
	</script>
</c:if>

<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
<div class="container" style="margin-top: 60px">
	<!-- 내용을 작성하는 부분 입니다. -->
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card border-0 shadow rounded-3 my-5">
					<div class="card-body p-4 p-sm-5">
						<!-- 로그인 칸 제목 -->
						<h5 class="card-title text-center mb-5 fw-light fs-5">회원정보
							수정을 위해 <br><br>비밀번호를 입력해주세요..</h5>


						<!-- 비밀번호 입력 칸 -->
						<div class="form-floating mb-3">
							
								<c:if test="${sessionScope.user != null}">
									<input type="password" name="userPw" class="form-control"
										id="floatingPassword" placeholder="비밀번호">
									<label for="floatingPassword">Password</label>
						</div>
						<br>
						<div class="d-grid">
							<button class="btn btn-warning btn-login text-uppercase fw-bold"
								type="button" value="확인" onclick="fn_checkPw()">정보수정하기</button>
						</div>
					
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>


</div>
<!-- 내용 끝나는 부분 -->


