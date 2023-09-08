package com.itwillbs.member.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

/**
 *   MemberJoinAction - 회원가입에 필요한 동작 처리
 *   insertPro.jsp 페이지의 역할 수행
 *   
 *   1) 정보전달 받기(+한글인코딩)
 *   2) 객체정보를 사용해서 파라메터 저장
 *   3) DAO 객체 생성 - 회원가입 메서드 
 *   4) 페이지 이동(로그인 페이지)  
 *   
 *   JSP 내장객체를 사용 X (기본자바객체 POJO) 
 *   
 *   C -> M 호출 -> 작업처리 (DB처리) -> (티켓) -> C
 *   
 *   
 */
public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 System.out.println("M : MemberJionAction_execute() 호출 ");
	 
	 
	 // 한글처리
	     request.setCharacterEncoding("UTF-8");
	 // 정보전달 받기(+한글인코딩)
	 //  2) 객체정보를 사용해서 파라메터 저장
	             // String id = request.getParameter("id");
	            // System.out.println("M : id="+id);
	     //MemberDTO객체 생성
	     MemberDTO dto = new MemberDTO();
	     
	     dto.setId(request.getParameter("id"));
	     dto.setPw(request.getParameter("pw"));
	     dto.setName(request.getParameter("name"));
	     dto.setGender(request.getParameter("gender"));
	     dto.setEmail(request.getParameter("email"));
	     dto.setAge(Integer.parseInt(request.getParameter("age")));
	     
	     dto.setRedgdate(new Date(System.currentTimeMillis()));
	     
	     System.out.println("M : "+dto);
	
	 //  3) DAO 객체 생성 - 회원가입 메서드 
	     MemberDAO dao = new MemberDAO();
	     dao.insertMember(dto);
	     
	 //  4) 페이지 이동(로그인 페이지)
	     // => 페이지 이동은 무조건 컨트롤러에서!
	     // => 페이지 이동정보만 저장(티켓 생성)
         ActionForward forward = new ActionForward();	     
	     forward.setPath("./MemberLogin.me");
	     forward.setRedirect(true);
	 
	    System.out.println("M : "+forward);
	    System.out.println("M : 실제 페이지 이동 ㄴㄴ 페이지 지동에 필요한 티켓만 생성");
	    
		return forward;
	}

	
}
