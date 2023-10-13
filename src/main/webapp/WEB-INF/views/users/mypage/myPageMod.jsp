<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
	
		$('#checkPw').keyup(function() {
	
			if ($('#userPw').val() != $('#checkPw').val()) {
				$('#checkPw').text(' ');
				$('#realOut').prop("disabled", true);
			} else {
				$('#checkPw').text(' ');
				$('#realOut').prop("disabled", false);
			}
		});
	});
	
	function fn_outConfirm() {
		if (confirm("회원 탈퇴를 진행하시겠습니까?")) {
			$('#checkPw').prop("disabled", false);
			document.getElementById("checkPw").style.display = "inline-block";
			
			document.getElementById("realOut").style.display = "inline-block";
			
		}
	}
	
	function fn_userOut() {
		if(confirm("회원 탈퇴를 진행합니다.")){
			var userId = "${sessionScope.user.userId}"
			var userPw = "${sessionScope.user.userPw}"
		
			var user = {
					userId: userId,
					userPw: userPw
			};
			
			$.ajax({
				type : "DELETE",
				url : "${contextPath}/user/req/out",
				contentType : "application/json",
				data : JSON.stringify(user),
				success : function(result, textStatus) {
	  				alert(result);
	  				alert(textStatus);
					if(result == 'success'){
						alert("그동안 이용해주셔서 감사합니다.");
						document.location = "${contextPath}/main"
					}else{
						alert("회원탈퇴에 실패했습니다. 다시 시도해주세요.");
						return false;
					}
				
				},
				error : function(data, textStatus) {
					alert("에러가 발생했습니다.");
					ㅣ
				},
				complete : function(data, textStatus) {
		
				}
			}); //end ajax	 
		}//if end
		
	}//function end
		
		
</script>
<c:if test='${msg == "UpdateSuccess" }'>
	<script>
		window.onload = function() {
			alert("수정이 완료되었습니다.");
		}
	</script>
</c:if>

<c:if test="${empty sessionScope.user}">
	<script>
		document.location = "${contextPath}/main";
	</script>
</c:if>


<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
<div class="container" style="margin-top: 60px">
	<!-- 내용을 작성하는 부분 입니다. -->
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="container justify-content-center">
				<h3>마이 페이지</h3>
			</div>
			<!-- 회원 수정 폼 시작  -->
			<form method="POST" action="${contextPath}/user/myPage/update">
				<div class="card shadow">
					<div class="card-body">
						<div class="form-group mb-3">
							<label for="username">아이디</label>
							<div class="input-group">
								<input type="text" id="inputId " value="${user.userId}"
									class="form-control" disabled /> <input type="hidden"
									name="userId" value="${user.userId}" />
							</div>
						</div>

						<div class="form-group mb-3">
							<label for="password">비밀번호</label> <input type="password"
								id="userPw" name="userPw" value="${user.userPw}"
								class="form-control" required />
						</div>

						<div class="form-group mb-3">
							<label for="full_name">이름</label> <input type="text"
								id="full_name" name="userName" value="${user.userName}"
								class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="birthdate">생년월일</label> <input type="date"
								id="birthdate" name="userBirth" value="${user.userBirth}"
								class="form-control" disabled />
						</div>
						<div class="form-group mb-3">
							<label for="tel">전화번호</label> <input type="text" id="tel"
								name="userTel" placeholder="010-0000-0000"
								value="${user.userTel}" class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="email">이메일</label> <input type="email" id="email"
								name="userEmail" value="${user.userEmail}"
								class="form-control" required />
						</div>
						<div class="form-group mb-3">
							<label for="userDate">회원가입일</label> <input type="Date" id="email"
								name="userDate" value="${user.userDate}"
								class="form-control" disabled />
						</div>
						<div class="d-flex justify-content-between align-items-center">
							<div class="form-group mb-3">
								<div class="text-right">
									<button type="submit" class="btn btn-warning" id="signupBtn">수정하기</button>
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="text-right">
									<button type="reset" class="btn btn-warning" id="reset">다시입력</button>
								</div>
							</div>
							<div class="form-group mb-3">
							<div class="text-right">
								<button type="button" class="btn btn-danger" id="reset" onclick="fn_outConfirm()">회원탈퇴</button>
								
							</div>
						</div>
						</div>
						<div class="d-flex justify-content-between align-items-center">
							<div class="form-group mb-3">
								<input type="password" name="userPw" size = 60 class="form-control" id="checkPw" placeholder="탈퇴를 위한 비밀번호 재확인"
									style="display: none;" id="checkPwd" disabled/>
							</div>
							<div class="form-group mb-3">
								<input type="button" class="btn btn-danger" value="탈퇴하기" id="realOut" style="display: none;" disabled 
								onclick="fn_userOut()"/>
							</div>
						</div>
						

					</div>
				</div>
			</form>
		</div>
		<div class="col-sm-3"></div>
	</div>


</div>
<!-- 내용 끝나는 부분 -->


