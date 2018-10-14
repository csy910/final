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
	<form action="${pageContext.servletContext.contextPath }/changed.do" class="form-signin" method="post">
		<img class="mb-4" src="../../assets/brand/bootstrap-solid.svg" alt=""
			width="72" height="72">
		<h1 class="h3 mb-3 font-weight-normal">GROUP WARE</h1>
		<label for="inputPassword" class="sr-only">기존 비밀번호 입력</label> <input
			type="password" id="inputPassword" class="form-control" name="getPass1"
			placeholder="기존 비밀번호 입력" required autofocus> <label
			for="inputPassword" class="sr-only">새 비밀번호 입력</label> <input
			type="password" id="inputPassword" class="form-control" name="getPass2"
			placeholder="새 비밀번호 입력" required autofocus> <label
			for="inputPassword" class="sr-only">새 비밀번호 재입력</label> <input
			type="password" id="inputPassword" class="form-control" name="getPass3"
			placeholder="새 비밀번호 재입력" required autofocus>
			<c:if test="${!empty err }">
			<div class="alert alert-danger" role="alert">
				입력하신 비밀번호가 일치하지 않습니다.
			</div>
			</c:if>
			<c:if test="${!empty err1 }">
			<div class="alert alert-danger" role="alert">
				기존의 비밀번호가 일치하지 않습니다.
			</div>
			</c:if>
		<button class="btn btn-lg btn-primary btn-block" type="submit">비밀번호 변경</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2018 MOCKING CORP</p>
	</form>
</body>
</html>