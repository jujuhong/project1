package com.itwillbs.member.db;

import java.sql.Date;

/**
 *  MemberDTO : 디비에 저장되는 테이블의 데이터를 저장하는 클래스
 *  (Data Transfer Object) -> DB에서 JSP,Java 로 데이터를 운반하는 객체
 *  
 *  MemberBean(자바빈) --> memberDTO
 *  
 */
public class MemberDTO {
    
	private String id;
	private String pw;
	private String name;
	private String gender;
	private int age;
	private String email;
	private Date redgdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRedgdate() {
		return redgdate;
	}
	public void setRedgdate(Date redgdate) {
		this.redgdate = redgdate;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", email=" + email + ", redgdate=" + redgdate + "]";
	}
	
	
	
}
