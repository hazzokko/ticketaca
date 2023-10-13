<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- head에 taglib 들어있습니다. 추가 작성 금지. -->
<script type="text/javascript">

$(document).ready(function() {

	const radioA = document.querySelector('#card');
	const radioB = document.querySelector('#kakaoPay');

	radioA.addEventListener("click", function() {
		  alert("card 옵션을 선택했습니다.");
		  document.getElementById("cardNum1").disabled=null;
		  document.getElementById("cardNum1").style.display = "inline-block";
		  document.getElementById("cardNum2").disabled=null;
		  document.getElementById("cardNum2").style.display = "inline-block";
		  document.getElementById("cardNum3").disabled=null;
		  document.getElementById("cardNum3").style.display = "inline-block";
		  document.getElementById("cardNum4").disabled=null;
		  document.getElementById("cardNum4").style.display = "inline-block";
		  document.getElementById("cardYY").disabled=null;
		  document.getElementById("cardYY").style.display = "inline-block";
		  document.getElementById("cardMM").disabled=null;
		  document.getElementById("cardMM").style.display = "inline-block";
		  document.getElementById("cardCVC").disabled=null;
		  document.getElementById("cardCVC").style.display = "inline-block";
		 
			
		 	document.getElementById("payQR").style.display = "none";
		});
	radioB.addEventListener("click", function() {
		  alert("kakaoPay 옵션을 선택했습니다.");
		  document.getElementById("cardNum1").setAttribute("disabled", "true");
		  document.getElementById("cardNum1").style.display = "none";
		  document.getElementById("cardNum2").setAttribute("disabled", "true");
		  document.getElementById("cardNum2").style.display = "none";
		  document.getElementById("cardNum3").setAttribute("disabled", "true");
		  document.getElementById("cardNum3").style.display = "none";
		  document.getElementById("cardNum4").setAttribute("disabled", "true");
		  document.getElementById("cardNum4").style.display = "none";
		  document.getElementById("cardYY").setAttribute("disabled", "true");
		  document.getElementById("cardYY").style.display = "none";
		  document.getElementById("cardMM").setAttribute("disabled", "true");
		  document.getElementById("cardMM").style.display = "none";
		  document.getElementById("cardCVC").setAttribute("disabled", "true");
		  document.getElementById("cardCVC").style.display = "none";
			
		 	document.getElementById("payQR").style.display = "inline-block";
		});
	
});
</script>
<c:if test="${empty sessionScope.user}">
	<script>
		document.location = "${contextPath}/main";
	</script>
</c:if>	
<!-- 내용 시작. container에 크기를 지정하거나 추가 div 컨테이너 생성 후 제작하세요. -->
<div class="container" style="margin-top: 60px">
	<!-- 내용을 작성하는 부분 입니다. -->
	<div class="row">		
		<div class="col-sm-3">
		</div>
			<div class="col-sm-6">
				<div class="container justify-content-center">
				<h3>결제 입력</h3>
				</div>
				<!-- 회원 가입 폼 시작  -->
				<form action="${contextPath}/order/addOrder" method="post">
				<div class="card shadow">
					<div class="card-body">					
						<div class="form-group mb-3">
							<label for="username">결제수량</label>
							<div class="input-group">
								<input type="text" id="ordTQnt" name="" value="${order.ordAQnt + order.ordYQnt + order.ordCQnt}" class="form-control" disabled />
							</div>
						</div>
						<div class="form-group mb-3">
							<label for="username">전시명</label>
							<div class="input-group">
								<input type="text" id="exhName" name="" value="${order.exhName}" class="form-control" disabled />
							</div>
						</div>
						<div class="form-group ">
							<p id="checkResult" style="color: blue;"></p>
						</div>
						<div class="form-group mb-3">
							<label for="password">성인수량</label>
							<input type="text" id="ordAQnt" name="ordAQnt" value="${order.ordAQnt}" class="form-control" readonly />
						</div>
						<div class="form-group mb-3">
							<label for="password2">청소년 수량</label>
							<input type="text" id="ordYQnt" name="ordYQnt" value="${order.ordYQnt}"  class="form-control" readonly/>
						</div>
						<div class="form-group mb-3">
							<label for="password2">어린이 수량</label>
							<input type="text" id="ordCQnt" name="ordCQnt" value="${order.ordCQnt}"  class="form-control" readonly/>
						</div>
						<div class="form-group ">
							<p id="checkPw" style="color: blue;"></p>
						</div>
						<div class="form-group mb-3">
							<label for="full_name">구매자명</label>
							<input type="text" id="userName" name="" value="${order.userName}" class="form-control" disabled />
						</div>
						<div class="form-group mb-3">
						    <label for="birthdate">결제금액</label>
						    <input type="text" id="ordPrice" name="ordPrice" value="${order.ordPrice}" class="form-control" readonly />
						</div>
						<div class="form-group mb-3">
						    <label for="birthdate">결제수단</label><br>
						    
						    <input type="radio" id="card" name="ordPay" value="card"  required />
  								<label for="card">카드</label>
						    <input type="radio" id="kakaoPay" name="ordPay" value="kakaoPay" required />
							  	<label for="kakaoPay">카카오페이</label>
					
					
						</div>
						<div class="form-group mb-3">
							<label for="tel"></label>
							<input type="text" id="cardNum1" name="" maxlength='4' placeholder="4자리" class="form-control" style="display: none;" disabled required />
							<input type="password" id="cardNum2" name="" maxlength='4' placeholder="4자리" class="form-control" style="display: none;" disabled required />
							<input type="password" id="cardNum3" name="" maxlength='4' placeholder="4자리" class="form-control" style="display: none;" disabled required />
							<input type="text" id="cardNum4" name="" maxlength='4' placeholder="4자리" class="form-control" style="display: none;" disabled required />
							<input type="text" id="cardMM" name="" maxlength='4' placeholder="MM" class="form-control" style="display: none;" disabled required />
							<input type="text" id="cardYY" name="" maxlength='4' placeholder="YY" class="form-control" style="display: none;" disabled required />
							<input type="password" id="cardCVC" name="" maxlength='3' placeholder="CVC" class="form-control" style="display: none;" disabled required />
						</div>
						<div class="form-group mb-3">
							<label for="tel"></label>
							<img src="${contextPath}/resources/image/kakaoPay.png" id="payQR" style="display: none; width: 50%" />
						</div>
<!-- 						<div class="form-group mb-3"> -->
<!-- 							<label for="email">이메일</label> -->
<!-- 							<input type="email" id="email" name="" class="form-control" required /> -->
<!-- 						</div> -->
						<div class="form-group d-flex justify-content-end align-items-center">
                            <div class="mb-3">
                                <div class="text-right">
                                	<input type="hidden" name="exhNo" value="${order.exhNo}" />
                                	<button type="reset" class="btn btn-outline-secondary" id="reset">다시입력</button>
                                    <button type="submit" class="btn btn-warning" id="signupBtn">결제하기</button>                                    
                                </div>
                            </div>
                        </div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
	</div>
	</div>
		
	
	</div> <!-- 내용 끝나는 부분 -->
	