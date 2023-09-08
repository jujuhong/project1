<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      <h1>index.jsp</h1>
      Model 2 에서 실행가능한 유일한 JSP페이지
      Model 2 프로젝트의 시작지점
      index.jsp  -->  MemberController 연결
      
      <%
     //   response.sendRedirect("./busan.me");
// response.sendRedirect("./MemberLogin.me");
     // response.sendRedirect("./Main.me");
     
     response.sendRedirect("./BoardWrite.bo");
     // response.sendRedirect("./BoardList.bo");
     
      %>
      
      
      
</body>
</html>