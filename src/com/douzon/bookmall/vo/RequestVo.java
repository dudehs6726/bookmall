package com.douzon.bookmall.vo;

public class RequestVo {
	private long no;
	private int amount;
	private String address;
	private long memberNo;
	private String memberName;
	private String eMail;
	
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	@Override
	public String toString() {
		return "주문번호: " + no + ", 주문자명: " + memberName + ", 주문자이메일: " + eMail + ", 결제금액: " + amount + ", 배송지: "
				+ address;
	}
	
	
}
