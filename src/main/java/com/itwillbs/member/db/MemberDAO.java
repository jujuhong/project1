package com.itwillbs.member.db;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * MemberDAO : 데이터를 처리하는 객체 (DB처리) (Data Access Object)
 *
 */
public class MemberDAO {

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결 메서드 - getCon()
	private Connection getCon() throws Exception {
		// Context 객체정보 생성
		Context initCTX = new InitialContext();
		// 필요한정보(DB연결정보) 가져오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc6");
		// 디비 연결
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! " + con);

		return con;
	}
	// 디비연결 메서드 - getCon()

	// 디비 자원해제 메서드 - closeDB()
	public void closeDB() {
		try {
			if (rs != null)	rs.close();
			if (pstmt != null)	pstmt.close();
			if (con != null)	con.close();
			
			System.out.println(" DAO : 자원해제 완료! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드 - closeDB()
	
	// 회원가입 처리하는 메서드 - insertMember()
	public void insertMember(MemberDTO dto) {
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성 & pstmt객체
			sql = "insert into itwill_member(id,pw,name,age,gender,email,regdate) "
					+ "values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getGender());
			pstmt.setString(6, dto.getEmail());
			pstmt.setDate(7, dto.getRegdate());			
			//4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 회원가입 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
	}	
	// 회원가입 처리하는 메서드 - insertMember()
	
	// 로그인 정보 체크메서드 - userCheck(id,pw)
	public int userCheck(String id,String pw) {
		int result = -1;
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성(select) & pstmt 객체 
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터 처리	
			if(rs.next()) {
				// 회원
				if(pw.equals(rs.getString("pw"))) {
					// 본인
					result = 1;
				}else {
					// 본인아님
					result = 0;
				}
			}else {
				// 비회원	
				result = -1;
			}
			System.out.println(" DAO : 로그인체크 완료 ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}	
	// 로그인 정보 체크메서드 - userCheck(id,pw)
	
	// 회원정보 조회 - getMemberInfo(id);
	public MemberDTO getMemberInfo(String id) {
		MemberDTO dto = null;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리		
			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setRegdate(rs.getDate("regdate"));				
			}
			System.out.println(" DAO : 회원정보 조회 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	// 회원정보 조회 - getMemberInfo(id);
	
	
	// 회원정보 수정 - memberUpdate(dto)
	public int memberUpdate(MemberDTO dto) {
		int result = -1;
		
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성(select) & pstmt 객체
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터 처리
			if(rs.next()) {
				// 회원
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 비밀번호 O
					//3. sql 작성(update) & pstmt 객체
					sql = "update itwill_member set name=?,age=?,gender=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getName());
					pstmt.setInt(2, dto.getAge());
					pstmt.setString(3, dto.getGender());
					pstmt.setString(4, dto.getId());
					
					//4. sql 실행
					result = pstmt.executeUpdate();
					// result = 1;
				}else {
					// 비밀번호 X  0
					result = 0;
				}
			}else {
				// 비회원 -1
				result = -1;
			}
			
			System.out.println(" DAO : 정보수정완료 "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}	
	// 회원정보 수정 - memberUpdate(dto)
	
	// 회원정보 삭제 - memberDelete(id,pw)
	public int memberDelete(String id,String pw) {
		int result = -1;
		
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성(select) & pstmt 객체
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터 처리
			if(rs.next()) {
				// 회원
				if(pw.equals(rs.getString("pw"))) {
					// 비밀번호 O
					//3. sql 작성(delete) & pstmt 객체
					sql = "delete from itwill_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					
					//4. sql 실행
					result = pstmt.executeUpdate();
					// result = 1;
				}else {
					// 비밀번호 X  0
					result = 0;
				}
			}else {
				// 비회원 -1
				result = -1;
			}
			
			System.out.println(" DAO : 정보수정완료 "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return result;		
	}
	// 회원정보 삭제 - memberDelete(id,pw)
	
	
	// 회원목록(관리자) - getMemberList()
	public ArrayList<MemberDTO> getMemberList(){
		ArrayList<MemberDTO> memberList= new ArrayList<MemberDTO>();
		try {
			//1.2 디비연결
			con =getCon();
			//3. sql 작성(select) & pstmt 객체 
			sql = "select * from itwill_member";
			pstmt = con.prepareStatement(sql);
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 
			while(rs.next()) {
				// rs -> DTO -> List
				MemberDTO dto = new MemberDTO();
				
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPw(rs.getString("pw"));
				dto.setRegdate(rs.getDate("regdate"));
				
				// DTO -> list
				memberList.add(dto);				
			}// while
			
			System.out.println(" DAO : 회원목록 저장완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}	
		
		return memberList;
	}	
	// 회원목록(관리자) - getMemberList()
	
	

}// DAO
