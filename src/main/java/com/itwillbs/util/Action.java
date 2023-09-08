package com.itwillbs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  추상 메서드 : { body }가 없는 메서드
 *    => 실행하는 코드가 없는 메서드 
 *    
 *  
 *  인터페이스는 객체 생성 X 
 *     --> 인터페이스 구성요소 : 상수, 추상메서드
 *   
 *   인터페이스 안에 있는 추상메서드를 사용하는 방법? 
 *   오버라이딩 전제조건 상속
 *    ==> 오버라이딩(일반 오버라이딩과 차이 있음 강제성 있음)
 *    
 *   
 */

public interface Action {
    // 메서드 선언 => 인터페이스 안에 있는 메서드는 모두 다 추상메서드
	//                 public, abstract 키워드 생략가능
	// public /*abstract*/ ActionForward execute();
	public  ActionForward execute(HttpServletRequest request,
			                       HttpServletResponse response) throws Exception;
	
	
	
	
	
	
	
}
