package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSMethod;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardUpdateProAction_execute() 호출");
		
		String pageNum = request.getParameter("pageNum");
		
		// 전달정보 저장 
		BoardDTO dto = new BoardDTO();
		
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setPass(request.getParameter("pass"));
		dto.setContent(request.getParameter("content"));
		
		// DAO - 정보수정메서드 호출
		BoardDAO dao = new BoardDAO();
		int result =  dao.updateBoard(dto);
		
		// 수정 처리 결과에 따른 페이지 이동 (JS)
		if(result == 0 ) {
			JSMethod.alertBack(response, "비밀번호 오류!");
			
			return null;
		}
		if(result == -1) {
			JSMethod.alertBack(response, "글정보 없음!!");
			return null;
			
		}
		//result == 1
			JSMethod.alertLocation(response, "수정완료", "./BoardList.bo?pageNum="+pageNum);
		
		
		return null;
	}

}
