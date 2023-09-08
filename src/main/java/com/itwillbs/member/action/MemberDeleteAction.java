package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberDeleteAction_execute 호출! ");
		
		// 세션정보제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 정보 저장(비밀번호저장)
		String pw = request.getParameter("pw");
		
		// DAO객체 - 회원정보 지원주는 메서드 부르기 memberDelete(id,pw);
		MemberDAO dao = new MemberDAO();
		dao.memberDelete(id,pw);
		
		
		// 처리 결과에 따른 페이지 이동(-1,0,1)(JS)
		
		
		
		
		return null;
	}

}
