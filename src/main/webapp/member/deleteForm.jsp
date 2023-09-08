<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>deleteForm.jsp(MVC)</h1>
		
		<h2> 회원정보 삭제(탈퇴) </h2>
		<%
		 // 로그인 성공시만 실행
		 %>
		 <c:if test="${empty sessionScope.id }">
		    <c:redirect rul="./MemberLogin.me"/>  
		 </c:if>
		
		
		<fieldset>
			<form action="./MemberDeleteAction.me" method="post">
			 <!--  <input type="hidden" name="id"> -->
			 비밀번호 : <input type="password" name="pw">
			 <input type="submit" value="탈퇴하기">	
			 
			</form>		
		</fieldset>
		
		
		
		
		
		
		
		
		
		
		
		
</body>
</html>