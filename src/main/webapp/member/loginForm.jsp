<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>loginForm.jsp(MVC)</h1>
	
	<fieldset>
	  <legend> 로그인 </legend>
	  <form action="./MemberLoginAction.me" method="POST">
	    <input type="hidden" name="oldurl" 
	          value="<%=request.getParameter("oldurl")%>">
	    아이디 : <input type="text" name="id"> <br>
	    비밀번호 : <input type="password" name="pw"> <hr>
	    <input type="submit" value="로그인">
	    <input type="button" value="회원가입" 
	           onclick=" location.href='./MemberJoin.me'; " >
	  </form>
	</fieldset>
	
	
	
	
	

</body>
</html>