package com.douzon.bookmall.vo;

public class MemberVo {

	private long no;
	private String name;
	private String phoneNumber;
	private String eMail;
	private String passWord;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Override
	public String toString() {
		return "고객번호: " + no + ", 고객이름: " + name + ", 전화번호: " + phoneNumber + ", 이메일: " + eMail
				+ ", 비밀번호: " + passWord;
	}
	
}
