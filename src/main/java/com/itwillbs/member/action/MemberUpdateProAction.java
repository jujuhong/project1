package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateProAction_execute() 호출 ");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리 => 필터사용 (생략)
		
		// 수정할 정보를 저장(파라메터)를 DTO 객체에 저장
	//	System.out.println("M : 이름 : "+request.getParameter("name"));
	MemberDTO dto = new MemberDTO();
	
	dto.setId(request.getParameter("id"));
	dto.setPw(request.getParameter("pw"));
	dto.setName(request.getParameter("name"));
	dto.setEmail(request.getParameter("email"));
	dto.setGender(request.getParameter("gender"));
	dto.setAge(Integer.parseInt(request.getParameter("age")));
	
	System.out.println(" M : 수정할 데이터 ");
	System.out.println(" M : "+dto);
	
		// DAO 객체 - 정보 수정메서드
	   MemberDAO dao = new MemberDAO();
	   dao.memberUpdate(dto);
		
		// 수정 메서드에 따른 결과에 페이지 이동
		
	 
		
		return null;
	}

}
