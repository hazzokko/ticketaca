
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%-- <%@ page import="com.ticketaka.sts.service" %> --%>
<%@ page import="com.ticketaca.sts.vo.ExhVO" %>

<% 
// 	UserService userService = new UserService();
// 	List<ExhVO> exhsList = new ArrayList<ExhVO>();
// 	exhsList = userService.listExhs();
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TICKETAKA ADMIN</title>

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
	
	function fn_admin_login() {
		document.location = '${contextPath}/admin/loginForm'
	}
	function fn_admin_logout() {
		document.location = '${contextPath}/admin/logout'
	}

	function checkSession() {
        <c:if test="${empty sessionScope.adm}">
            alert("로그인이 필요한 서비스입니다.");
        </c:if>
    }
	function fn_quit() {
		alert("퇴사에 실패하였습니다.");
		alert("니가 나가면 어디 취업이 될거 같아?");
		alert("여기만한 곳 없다.");
	}
</script>

<c:choose>
	<c:when test='${msg == "loginFail" }'>
		<script>
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  alert("아이디/비밀번호를 확인해주세요.");
				  }, 100);
				});
		</script>
	</c:when>
	<c:when test='${msg == "loginSuccess" }'>
		<script>
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  document.getElementById("logout").style.display = "inline-block";
						document.getElementById("welcome").style.display = "inline-block";
						document.getElementById("login").style.display = "none";
				  }, 100);
				});
		</script>
	</c:when>
	<c:when test='${msg == "logout"}'>
		<script>			
			window.addEventListener("load", function() {
				  setTimeout(function() {
					  alert("로그아웃 되었습니다.");
						document.getElementById("welcome").style.display = "none";
						document.getElementById("login").style.display = "inline-block";
				  }, 100);
				});
		</script>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${not empty sessionScope.adm}">
		<script>
			window.onload = function(){
				document.getElementById("exhList1").href = "${contextPath}/admin/listExhs";
				document.getElementById("userList").href = "${contextPath}/admin/user/list";
				document.getElementById("payManage").href = "${contextPath}/order/orderAllList";
				document.getElementById("qna").href = "${contextPath}/qna/admin/adminlistQna";
				
			}
		</script>
	</c:when>
	<c:when test="${empty sessionScope.adm}">
		<script>
			window.onload = function(){
				document.getElementById("userList").href = "";
				document.getElementById("payManage").href = "";
				document.getElementById("qna").href = "";
				document.getElementById("exhList").href = "";
			}
		</script>
	</c:when>
</c:choose>
</head>
<body>
	<div class="container-fluid">
	<div class="p-4 bg-secondary text-white text-center">
	<div class="row">
	<div class="col-3" >
	<a href="${contextPath}/admin/main">
    <img src="${contextPath}/resources/image/head_ticketaka.png" width="200px" alt="mainIcon">
	</a>
	</div>
	<div class="col-6">
		<form action="${contextPath}/admin/mainSearch.do" class="d-flex">
			<input class="form-control me-2	" size="100" name="keyword" type="text" placeholder="전시검색" list="exhList">
			<button class="btn btn-primary" type="submit">Search</button>
				<datalist id="exhList">
 					<c:forEach var="exh" items="" end="4"> <%-- <%=exhsList %> --%>
						<option value="name"/>    <%-- ${exh.name} --%>
					</c:forEach>
				</datalist>
		</form>
	</div>
	
	<c:if test="${empty sessionScope.adm}">
	<div class="col-3">
	<input type="button" class="btn btn-outline-light" value="로그인" 
	onclick="fn_admin_login()">
	</div>
	</c:if>
	<c:if test="${not empty sessionScope.adm}">
	<div class="col-2">
	<input type="button" class="btn btn-outline-light" value="로그아웃" 
	onclick="fn_admin_logout()">
	</div>
	</c:if>
	</div>
	</div>
	</div>
	
	<div class="container-fluid">
        <nav class="navbar navbar-expand-sm bg-secondary justify-content-center ">
        	
            <ul class="nav">
  
                <li class="nav-item"><a class="nav-link text-light" id="userList" href="" onclick="checkSession()">유저관리</a></li>
				<li class="nav-item"><a class="nav-link text-light" id="payManage" href="" onclick="checkSession()">결제내역</a></li>
				<li class="nav-item"><a class="nav-link text-light" id="exhList1" href="" onclick="checkSession()">전시목록</a></li>
				<li class="nav-item"><a class="nav-link text-light" id="qna" href="" onclick="checkSession()">Q&A</a></li>
				<li class="nav-item">
				<button class="btn btn-light" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#demo">사내📞</button>
				</li>

            </ul>
           
        </nav>
    </div>


	<div class="offcanvas offcanvas-start" id="demo">
		<div class="offcanvas-header">
			<h1 class="offcanvas-title">사내연락망📞</h1>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body">
			<p>2207 : 영업팀</p>
			<p>2209 : 홍보팀</p>
			<p>2210 : 인사팀</p>
			<p>2211 : 시설팀</p>
			<p>2211 : 보안팀</p>
			<p>9999 : 경비실</p>
			<button class="btn btn-secondary" type="button" onclick="fn_quit()">퇴사하기</button>
		</div>
	</div>