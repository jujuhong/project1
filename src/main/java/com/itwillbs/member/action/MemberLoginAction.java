package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("M : MemberLoginAction_execute() 호출 ");
		
		// pro.jsp 페이지의 동작 수행
		
		// 한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		// 전달정보를 저장(ID,PW)
	    String id= request.getParameter("id");
	    String pw= request.getParameter("pw");
		
	    //DAO 객체 생성 - userCheak(id,pw);
	    MemberDAO dao = new MemberDAO();
	int result =    dao.userCheak(id,pw);
	    System.out.println("M : 로그인 결과("+result+")");
		// DB에서 전달된 정보에 따른 페이지 이동
		if(result == -1 ) {
			//비회원 --> 페이지 이동(JS)
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("  <script> ");
			out.println(" alert('회원정보 없음'q ); ");
			out.println(" history.back();  ");
			out.println("  </script> ");
			
			out.close();
	        // 컨트롤러의 페이지 이동 막음
			return null;
			
		} else if(result == 0 ) {
			 // 회원이지만 비번오류 --> 페이지이동 (JS)
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("  <script> ");
			out.println(" alert('비밀번호오류'); ");
			out.println(" history.back();  ");
			out.println("  < /script> ");
			
			out.close();
	        // 컨트롤러의 페이지 이동 막음
			return null;
			
			
		}else {
			//result == 1
			// 로그인 성공 --> 페이지 이동(forward)
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			// 페이지 이동(forward)
			ActionForward foward = new ActionForward();
			foward.setPath("./Main.me");
			foward.setRedirect(true);
			
			System.out.println("M : "+foward);
			
			return foward;
			
		} //else
	    
	
		
	}

}
