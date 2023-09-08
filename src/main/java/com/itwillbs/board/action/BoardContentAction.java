package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardWriteAction_execute() 호출 ");
		
		// 전달정보 저장 bno
		
		

		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		// DAO - 조회수 1증가 ()
		BoardDAO dao = new BoardDAO();
		dao.updateReadCount(bno);
		System.out.println(" M : 조회수 1증가! ");
		
		
		// DAO - 특정글 정보 조회(bno)
		BoardDTO dto =  dao.getBoard(bno);
		
		
		
		// request 영역에 정보 저장
		request.setAttribute("dto", dto);
		
	//	request.setAttribute("pageNum", pageNum);
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp?pageNum"+pageNum);
		forward.setRedirect(false);
		
		
		return forward;
	}

}
