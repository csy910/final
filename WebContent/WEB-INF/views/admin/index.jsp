<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/signin.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<title>그룹웨어</title>

<body class="text-center">
	<form action="${pageContext.servletContext.contextPath }/login.do" class="form-signin" method="post">
		<img class="mb-4" src="../../assets/brand/bootstrap-solid.svg" alt=""
			width="72" height="72">
		<h1 class="h3 mb-3 font-weight-normal">GROUP WARE</h1>
		<label for="inputId" class="sr-only">사원 아이디</label> <input
			type="text" id="inputId" class="form-control" name="getId"
			placeholder="사원 아이디" required autofocus> <label
			for="inputPassword" class="sr-only">사원 비밀번호</label> <input
			type="password" id="inputPassword" class="form-control" name="getPass"
			placeholder="사원 비밀번호" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				로그인 유지
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">로 그 인</button>
		<c:if test="${!empty err }">
			<div class="alert alert-danger" role="alert">
				등록되지 않은 아이디이거나,<br/> 아이디 또는 비밀번호를 잘못 입력하셨습니다.
			</div>
		</c:if>
		<p class="mt-5 mb-3 text-muted">&copy; 2018 MOCKING CORP</p>
	</form>
</body>
</html>