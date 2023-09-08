package com.itwillbs.board.action;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class boardFileDownAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardFileDownAction2_execute 호출 ");
		
		// 파일 다운로드 --> 페이지 출력
		
		// 첨부파일의 이름을 저장
		String fileName = request.getParameter("fileName");
		System.out.println(" M : 다운로드할 파일명 : "+fileName);
		
		//filedownload.jsp 내용 추가
	//	String fileName = (String)request.getAttribute("fileName"); 위에 있음
		  
		// 다운로드 위치 == 업로드 위치
		  String savePath = "upload";		
		  
		  // 실제 위치정보 
		  // request.getRealPath(path)
		  ServletContext CTX = request.getServletContext();
		  String downloadPath = CTX.getRealPath(savePath);
		  
		  System.out.println(" 다운로드 위치 : "+downloadPath);
		
		  String filePath = downloadPath + "\\" + fileName;
		  
		  System.out.println(" 다운로드할 파일의 위치 : "+filePath);
		  
		  // 파일 다운로드 설정
		  
		  // 파일을 한번에 많이 읽고 사용가능한 배열 => 버퍼
		  byte[] b = new byte[4096];  // 4KB 배열
		  
		  // 파일정보를 읽어서 사용가능 하도록 하는 객체
		  FileInputStream fis = new FileInputStream(filePath);
		  
		  // 다운로드할 파일의 마임타입을 설정
		  String MimeType = request.getServletContext().getMimeType(filePath);
		  System.out.println("MimeType : "+MimeType);
		  
		  if(MimeType == null){
			 MimeType = "application/octet-stream";
			 // -> 잘 알려지지 않은 이진파일
		  }
		  
		  // 응답정보 데이터 처리를 수행
		   response.setContentType(MimeType);
		
		    // 파일 인코딩
		     
		     // 사용자의 브라우저 정보 체크
		     String agent = request.getHeader("User-Agent");
		     boolean ieBrowser
		       = (agent.indexOf("MSIE") > -1 ) || (agent.indexOf("Trident") > -1 );
		     
		     // 파일명이 한글일때 처리
		     if(ieBrowser){ // ie브라우저 일때
		    	fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		     }else{ // ie브라우저 아닐때
		    	 fileName = new String(fileName.getBytes("UTF-8"),"iso-88598-1");
		     }
		     
		     // 모든 다운로드 파일의 형태를 일반화시킴
		     response.setHeader("Content-Disposition", "attachment; filename="+fileName);
			  
		     // 기존의 JSP-out 내장객체와의 충돌 해결
		//     out.clear();
		//     out = pageContext.pushBody();
		     
			// 다운로드 시작
			ServletOutputStream out2 = response.getOutputStream();
			
			int data = 0;
			
			while( (data=fis.read(b,0,b.length)) != -1 ){ // 파일이 끝나기 전까지 계속
				out2.write(b,0,data);
				
			}
			out2.flush();
			out2.close();
			fis.close();
			
		
		return null;    //여기서 페이지 이동은 없다
	}

}
