package com.itwillbs.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;


public class MemberListAction implements Action {

	private Object session;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberListAction_execute() 호출");
		
		// 세션제어(로그인, 관리자)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// DAO 객체 - getMemberList() 
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberDTO> memberList =  dao.getMemberList();
		
		// request 영역에 저장
		request.setAttribute("memberList", memberList);
		
		// 페이지 이동
		forward.setPath("./member/list.jsp");
		forward.setRedirect(false);
		
		
		
		
		
		return forward;
	}

}
