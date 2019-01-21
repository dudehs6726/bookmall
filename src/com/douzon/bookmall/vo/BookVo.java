package com.douzon.bookmall.vo;

public class BookVo {
	private long no;
	private String name;
	private int price;
	private long categoryNo;
	private String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	@Override
	public String toString() {
		return "책 번호: " + no + ", 책 이름: " + name + ", 책 가격: " + price + ", 카테고리 명:" + categoryName;
	}
	
}
