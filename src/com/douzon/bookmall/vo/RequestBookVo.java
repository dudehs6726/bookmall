package com.douzon.bookmall.vo;

public class RequestBookVo {
	
	private long bookNo;
	private String bookName;
	private int count;
	
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "도서번호: " + bookNo + ", 도서제목: " + bookName + ", 수량: " + count;
	}
	
	

}
