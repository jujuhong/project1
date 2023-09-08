package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println( "M : BoardWriteAction_execute() 호출");

	    // 업로드할 폴더 생성 -upload (가상경로)
 
		// 실제파일이 업로드 되는경로
		String realPath =  request.getRealPath("/upload");
		System.out.println("M : realPath : "+realPath);
		
		// 파일 크기 
		int maxSize = 10 * 1024 * 1024;
		
		// 1)파일 업로드 수행 --> MultipartRequest 객체 생성
		 MultipartRequest multi
		  = new MultipartRequest(
				  request,
				  realPath,
				  maxSize,
				  "UTF-8",
				  new DefaultFileRenamePolicy()
				  );
		 
		 System.out.println("M : 파일업로드 성공! ");
		
		// 2)전달정보 --> 파라메타 저장(DTO)
		 
		
		 
		 BoardDTO dto = new BoardDTO();
		 dto.setName(multi.getParameter("name"));
		 dto.setPass(multi.getParameter("pass"));
		 dto.setSubject(multi.getParameter("subject"));
		 dto.setContent(multi.getParameter("content"));
		 dto.setFile(multi.getFilesystemName("file"));
		 
		 dto.setIp(request.getRemoteAddr());
		//DAO - 일반 글쓰기 메서드 호출 (+파일)
		 BoardDAO dao = new BoardDAO();
		 dao.insertBoard(dto);
		//페이지 이동(./BoardList.bo)
		 
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
