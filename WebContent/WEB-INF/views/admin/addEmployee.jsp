<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>사원등록</h2>
<c:if test="${!empty err }">
<div class="alert alert-danger" role="alert">
   	DB 처리중에 문제가 발생하였습니다.
</div>
</c:if>
<form action="${pageContext.servletContext.contextPath }/admin/employee/add.do" method="post">
	<div class="form-group">
		<label>이름 (*)</label> <input name="name"
			type="text" class="form-control" placeholder="등록할 사원 이름">
	</div>
	<div class="form-group">
		<label>부서 및 직책 (*)</label>
		<div class="row">
			<div class="col">
				<select class="form-control" name="did">
					<c:forEach var="d" items="${dep }">
						<option value="${d.DID }">${d.DNAME }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col">
				<select class="form-control" name="pid">
					<c:forEach var="p" items="${pos }">
						<option value="${p.PID }">${p.PNAME }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label>입사일 (*)</label> <input
			type="date" class="form-control" name="joindate">
	</div>
	<div class="form-group">
		<button type="submit"  class="form-control btn btn-outline-primary">사원등록</button>
	</div>
</form>