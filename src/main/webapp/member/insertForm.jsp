<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
   // 회원가입정보가 잘 입력되었는지 체크하는 함수
  function check(){
	  var id = document.fr.id.value;
	  var pw=document.fr.pw.value;
	  var email=document.fr.email.value;
	  
	  if(id == ""){
		  alert("아이디를 입력하세요");
		  document.fr.id.focus();
		  return false;
	  }
	 if(pw == ""){
		 alert("비번을 입력하세요");
		 document.fr.pw.focus();
		 return false;
	 }
	 if(email == ""){
		 alert("이메일입력해라");
		 document.fr.email.focus();
		 return false;
	 }
	  }
	  // 비밀번호
	  //이메일도 아이디와 동일하게 만들어라
  
  


</script>
</head>
<body>
     <h1>insertForm.jsp (MVC)</h1>
     <h2> ITWILL-회원가입(정보 입력->다음페이지(처리)전달)</h2>
     <fieldset>
        <form action="./MemberJoinAction.me" method="post" name="fr" onsubmit="return check();">
           아이디 : <input type="text" name="id"><br>
           비밀번호 : <input type = "password" name="pw"><br>
           이름 : <input type = "text" name ="name"><br>
           나이 : <input type="text" name="age"><br >
           성별 : <input type="radio" name="gender" value="남">남
                     <input type="radio" name="gender" value="여">여<br>
           이메일 : <input type="email" name="email"><br>
           
           <input type="submit" value="회원가입">
        </form>
     </fieldset>
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
</body>
</html>