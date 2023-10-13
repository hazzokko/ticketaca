<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ticketaca.sts.service.ExhServiceImpl" %>
<%@ page import="com.ticketaca.sts.vo.ExhVO" %>
<% request.setCharacterEncoding("UTF-8");%>



<!-- 여기에 통합 시에 추가할거 있음... 총명 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TICKETAKA</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function fn_user_join(){
		
		document.location='${contextPath}/user/signForm'
	}
	function fn_user_login(){
		
		document.location='${contextPath}/user/loginForm'
	}
	function fn_user_logout(){
		
		document.location='${contextPath}/user/logout'
	}

	function checkSession() {
         <c:if test="${sessionScope.user == null}">
             alert("로그인이 필요한 서비스입니다.");
         </c:if>
     }
</script>

<c:choose>
	<c:when test='${msg == "signUpUser" }'>
		<script type="text/javascript">
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  alert("회원가입이 완료 되었습니다. 로그인 해주세요 😃");
				  }, 100);
				});
		</script>
	</c:when>
	<c:when test='${msg == "signUpFail" }'>
		<script type="text/javascript">		
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  alert("회원가입에 실패하였습니다. 다시 시도 해주세요 🙏");
				  }, 100);
				});
		</script>
	</c:when>

	<c:when test='${msg == "userOut" }'>
		<script type="text/javascript">
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  alert("탈퇴한 회원입니다.");
				  }, 100);
				});
		</script>
	</c:when>
	
	<c:when test='${msg == "loginSuccess" || sessionScope.user != null}'>
		<script type="text/javascript">
		window.addEventListener("load", function() {
			  setTimeout(function() {
				  document.getElementById("welcome").style.display = "inline-block";
					document.getElementById("signIn").style.display = "none";
					document.getElementById("login").style.display = "none";
					document.getElementById("logout").style.display = "inline-block";
			  }, 100);
			});
	
		</script>
	</c:when>
	
	<c:when test='${msg == "logout"}'>
		<script type="text/javascript">
		window.addEventListener("load", function() {
			  setTimeout(function() {
				  alert("로그아웃 되었습니다.");
				document.getElementById("welcome").style.display = "none";
				document.getElementById("signIn").style.display = "inline-block";
				document.getElementById("login").style.display = "inline-block";
			  }, 100);
			});
	
		
		</script>
	</c:when>
	
	<c:when test='${msg == "userOutSuccess" }'>
		<script>
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  alert("정상적으로 탈퇴되었습니다.\n그동안 이용해주셔서 감사합니다.");
						document.getElementById("welcome").style.display = "none";
						document.getElementById("signIn").style.display = "inline-block";
						document.getElementById("login").style.display = "inline-block";
				  }, 100);
				});
		</script>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${not empty sessionScope.user}">
		<script>
			window.onload = function(){
				document.getElementById("myPage").href = "${contextPath}/user/myPage";
				document.getElementById("zzimList").href = "${contextPath}/zzim/list";
				document.getElementById("payHistory").href = "${contextPath}/order/userOrder";
				document.getElementById("qna").href = "${contextPath}/qna/user/userListQna";
		
			
			}
		</script>
	</c:when>
	<c:when test="${empty sessionScope.user}">
		<script>
			window.onload = function(){
				document.getElementById("myPage").href = "";
				document.getElementById("zzimList").href = "";
				document.getElementById("payHistory").href = "";
				document.getElementById("qna").href = "";

			}
		</script>
	</c:when>
</c:choose>
</head>
<body>

	<div class="container-fluid">
		<div class="p-4 bg-warning text-white text-center">
			<div class="row">
				<div class="col-3">
					<a href="${contextPath}/"> 
						<img src="${contextPath}/resources/image/head_ticketaka.png" width="200px" alt="mainIcon">
					</a>
				</div>
				<div class="col-6">
					<form action="${contextPath}/user/exh/search" class="d-flex">
						<input class="form-control me-2	" size="100" name="keyword" type="text" placeholder="전시검색" list="exhsList3">
						<button class="btn btn-danger" type="submit">Search</button>
<!-- 						<datalist id="exhsList3"> -->
<%-- 							<c:forEach var="exh" items="<%=exhsList3%>" end="4"> --%>
<%-- 								<option value="${exh.exhName}" /> --%>
<%-- 							</c:forEach> --%>
<!-- 						</datalist> -->
					</form>
				</div>

				<c:if test="${empty sessionScope.user}">
					<div class="col-3">
						<input type="button" class="btn btn-outline-dark" value="회원가입"
							style="margin-left: 20px;" onclick="fn_user_join()"> <input
							type="button" class="btn btn-outline-dark" value="로그인"
							onclick="fn_user_login()">
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<div class="col-1">
						<p id="welcome">반갑습니다.<br>${sessionScope.user.userName} 님 😊</p>
					</div>
					<div class="col-1">
						<input type="button" class="btn btn-outline-dark" value="로그아웃"
							onclick="fn_user_logout()">
					</div>
				</c:if>
			</div>
		</div>
	</div>


	<div class="container-fluid">
        <nav class="navbar navbar-expand-sm bg-danger justify-content-center ">
        	
            <ul class="nav">
            	<li class="nav-item"><a class="nav-link text-light" id="exhList" href="${contextPath}/user/listExhs">전시회 목록</a></li>
                <li class="nav-item"><a class="nav-link text-light" id="myPage" href="" onclick="checkSession()" >마이페이지</a></li>
                <li class="nav-item"><a class="nav-link text-light" id="payHistory" href="" onclick="checkSession()">결제내역</a></li>
                <li class="nav-item"><a class="nav-link text-light" id="zzimList" href="" onclick="checkSession()">찜</a></li>
                <li class="nav-item"><a class="nav-link text-light" id="qna" href="" onclick="checkSession()">Q&A</a></li>

                <li class="nav-item"><button class="btn btn-warning" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo">TOP3</button></li>
            </ul>
           
        </nav>
    </div>
    
	
	<div class="offcanvas offcanvas-start" id="demo">
		<div class="offcanvas-header">
			<h1 class="offcanvas-title">지금 핫한 전시 TOP3🔥</h1>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body">
			<p>다시보다: 한국근현대미술전</p>
			<p>문도 멘도: 판타스틱 시티 라이프</p>
			<p>하리보 골드베렌 100주년 생일 기념전</p>
			<button class="btn btn-secondary" type="button">A Button</button>
		</div>
	</div>